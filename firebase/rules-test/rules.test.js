// Security rules tests. Each case mirrors a real operation of the app (data/firestore and
// data/realtime) or something a client must not be able to do. Run: npm test (starts the
// Firestore and Realtime Database emulators, see package.json).
import { readFileSync } from "node:fs";
import { after, before, beforeEach, describe, test } from "node:test";
import { assertFails, assertSucceeds, initializeTestEnvironment } from "@firebase/rules-unit-testing";
import { deleteDoc, doc, getDoc, getDocs, collection, orderBy, query, setDoc, updateDoc, where } from "firebase/firestore";
import { ref, get, remove, set, update } from "firebase/database";
import { ref as storageRef, deleteObject, getBytes, uploadBytes } from "firebase/storage";

const alice = "alice";
const bob = "bob";
const user = (over = {}) => ({
  provider: 0, nickName: "alice", photoUrl: null, groupId: null,
  points: 0, credits: 10, logged: true, ...over,
});
const maxInt = 2147483647; // UserDocument maps points and credits onto a Kotlin Int.

let env;

before(async () => {
  env = await initializeTestEnvironment({
    projectId: "demo-youknow",
    firestore: { rules: readFileSync("../firestore.rules", "utf8") },
    database: { rules: readFileSync("../database.rules.json", "utf8") },
    storage: { rules: readFileSync("../storage.rules", "utf8") },
  });
});
after(() => env.cleanup());
beforeEach(async () => {
  await env.clearFirestore();
  await env.clearDatabase();
});

const smallImage = () => new Uint8Array([0xff, 0xd8, 0xff, 0xe0]); // a few JPEG-ish bytes, well under the 5 MB cap
const bigImage = () => new Uint8Array(6 * 1024 * 1024); // over the 5 MB cap

const as = (uid) => (uid ? env.authenticatedContext(uid) : env.unauthenticatedContext());
const seed = (fn) => env.withSecurityRulesDisabled(fn);

describe("Firestore users", () => {
  test("a user creates, updates and deletes their own profile", async () => {
    const db = as(alice).firestore();
    await assertSucceeds(setDoc(doc(db, "users", alice), user()));
    await assertSucceeds(updateDoc(doc(db, "users", alice), { points: 30, credits: 5 }));
    await assertSucceeds(updateDoc(doc(db, "users", alice), { groupId: "g-alice" }));
    await assertSucceeds(updateDoc(doc(db, "users", alice), { groupId: null }));
    await assertSucceeds(updateDoc(doc(db, "users", alice), { nickName: "Ali", photoUrl: "https://x/y.png" }));
    await assertSucceeds(deleteDoc(doc(db, "users", alice)));
  });

  test("signed-in users read others (ranking, nickname search), anonymous clients do not", async () => {
    await seed((c) => setDoc(doc(c.firestore(), "users", bob), user({ nickName: "bob", points: 7 })));
    const db = as(alice).firestore();
    await assertSucceeds(getDoc(doc(db, "users", bob)));
    await assertSucceeds(getDocs(query(collection(db, "users"), orderBy("points"))));
    await assertSucceeds(getDocs(query(collection(db, "users"), where("nickName", "==", "bob"))));
    await assertFails(getDoc(doc(as(null).firestore(), "users", bob)));
  });

  test("nobody writes someone else's profile", async () => {
    await seed((c) => setDoc(doc(c.firestore(), "users", bob), user({ nickName: "bob" })));
    const db = as(alice).firestore();
    await assertFails(updateDoc(doc(db, "users", bob), { points: 9999 }));
    await assertFails(deleteDoc(doc(db, "users", bob)));
    await assertFails(setDoc(doc(db, "users", "carol"), user()));
  });

  test("profiles reject negative credits, unknown fields and wrong types", async () => {
    const db = as(alice).firestore();
    await assertFails(setDoc(doc(db, "users", alice), user({ credits: -1 })));
    await assertFails(setDoc(doc(db, "users", alice), user({ isAdmin: true })));
    await assertFails(setDoc(doc(db, "users", alice), user({ points: "lots" })));
  });

  test("profiles reject every value UserDocument can't map: everyone reads them in the ranking", async () => {
    const db = as(alice).firestore();
    await assertFails(setDoc(doc(db, "users", alice), user({ logged: "x" })));
    await assertFails(setDoc(doc(db, "users", alice), user({ provider: "x" })));
    await assertFails(setDoc(doc(db, "users", alice), user({ provider: 2 })));
    await assertFails(setDoc(doc(db, "users", alice), user({ provider: -1 })));
    await assertFails(setDoc(doc(db, "users", alice), user({ points: maxInt + 1 })));
    await assertFails(setDoc(doc(db, "users", alice), user({ credits: maxInt + 1 })));
    await assertFails(setDoc(doc(db, "users", alice), user({ nickName: 7 })));
    await assertFails(setDoc(doc(db, "users", alice), user({ photoUrl: 7 })));
    await assertFails(setDoc(doc(db, "users", alice), user({ groupId: {} })));

    await assertSucceeds(setDoc(doc(db, "users", alice), user({ provider: 1, points: maxInt, credits: maxInt })));
    await assertFails(updateDoc(doc(db, "users", alice), { logged: "x" }));
    await assertFails(updateDoc(doc(db, "users", alice), { points: maxInt + 1 }));
  });

  test("a new profile cannot carry an email, not even the user's own", async () => {
    const db = as(alice).firestore();
    await assertFails(setDoc(doc(db, "users", alice), user({ email: "a@example.com" })));

    await assertSucceeds(setDoc(doc(db, "users", alice), user()));
    await assertFails(updateDoc(doc(db, "users", alice), { email: "a@example.com" }));
  });

  test("a profile written by an old version keeps working until the migration removes its email", async () => {
    await seed((c) => setDoc(doc(c.firestore(), "users", alice), user({ email: "a@example.com" })));
    const db = as(alice).firestore();

    await assertSucceeds(updateDoc(doc(db, "users", alice), { points: 30 }));
    await assertFails(updateDoc(doc(db, "users", alice), { email: "other@example.com" }));

    await assertSucceeds(setDoc(doc(db, "users", alice), user()));
    await assertFails(setDoc(doc(db, "users", alice), user({ email: "a@example.com" })));
  });
});

describe("Firestore news and surveys", () => {
  test("news are read-only", async () => {
    await seed((c) => setDoc(doc(c.firestore(), "news", "n1"), { title_es: "t" }));
    const db = as(alice).firestore();
    await assertSucceeds(getDocs(collection(db, "news")));
    await assertFails(setDoc(doc(db, "news", "n2"), { title_es: "spam" }));
  });

  test("each user publishes only their own survey", async () => {
    const db = as(alice).firestore();
    await assertSucceeds(setDoc(doc(db, "surveys", alice), { authorFirebaseId: alice, authorNickName: "alice" }));
    await assertSucceeds(getDocs(collection(db, "surveys")));
    await assertFails(setDoc(doc(db, "surveys", bob), { authorFirebaseId: bob }));
    await assertFails(setDoc(doc(db, "surveys", alice), { authorFirebaseId: bob }));
    await assertFails(deleteDoc(doc(db, "surveys", alice)));
  });

  test("anything else is denied", async () => {
    await assertFails(setDoc(doc(as(alice).firestore(), "other", "x"), { a: 1 }));
  });
});

describe("Firestore daily challenges", () => {
  const day = (offset) => new Date(Date.now() + offset * 86_400_000).toISOString().slice(0, 10);
  const today = day(0);
  const question = (over = {}) => ({ question: "q", answers: ["a", "b"], correctAnswer: "a", category: "ANY", difficulty: "MEDIUM", quizType: "MULTIPLE", ...over });
  const challenge = (over = {}) => ({ date: today, questions: [question()], ...over });
  const publish = (db, date, data) => setDoc(doc(db, "dailyChallenges", date), data);
  const verified = (uid) => env.authenticatedContext(uid, { email_verified: true });

  test("the first device publishes the day's shared round", async () => {
    const db = verified(alice).firestore();
    await assertSucceeds(publish(db, today, challenge()));
    await assertSucceeds(getDoc(doc(db, "dailyChallenges", today)));
  });

  test("accepts the largest round the rules allow", async () => {
    // Exactly what the app publishes every day (5 questions, up to 4 answers), so it also guards the
    // budget of 1000 expressions per request: over it, this write is denied.
    const answers = ["a", "b", "c", "d"];
    const questions = Array(5).fill(question({ answers, correctAnswer: "d" }));
    await assertSucceeds(publish(verified(alice).firestore(), today, challenge({ questions })));
  });

  test("only verified accounts publish", async () => {
    await assertFails(publish(as(alice).firestore(), today, challenge()));
    await assertFails(publish(env.authenticatedContext(alice, { email_verified: false }).firestore(), today, challenge()));
  });

  test("only the current day can be published, with a day of clock skew", async () => {
    const db = verified(alice).firestore();
    for (const date of [day(1), day(-1)]) {
      await assertSucceeds(publish(db, date, challenge({ date })));
    }
    for (const date of [day(3), day(365), day(-3), "2099-01-01"]) {
      await assertFails(publish(db, date, challenge({ date })));
    }
    await assertFails(publish(db, "2026-13-45", challenge({ date: "2026-13-45" })));
  });

  test("rejects questions the app can't map or play", async () => {
    const db = verified(alice).firestore();
    const bad = [
      [1, 2],
      ["text"],
      [null],
      [{}],
      [question(), 1],
      [question({ question: 7 })],
      [question({ question: "" })],
      [question({ answers: "ab" })],
      [question({ answers: [1, 2], correctAnswer: 1 })],
      [question({ answers: ["a", 2] })],
      [question({ answers: ["a", "b", 3] })],
      [question({ answers: ["a", "b", "c", 4] })],
      [question({ answers: ["a"] })],
      [question({ answers: ["a", "b", "c", "d", "e"] })],
      [question({ correctAnswer: "z" })],
      [question({ correctAnswer: 1 })],
      [question({ category: 7 })],
      [question({ difficulty: null })],
      [question({ quizType: {} })],
      [question({ extra: true })],
      [question({ question: "q".repeat(1001) })],
      [{ question: "q", answers: ["a", "b"], correctAnswer: "a" }],
    ];
    for (const questions of bad) {
      await assertFails(publish(db, today, challenge({ questions })));
    }
  });

  test("a bad question is caught at any position", async () => {
    const db = verified(alice).firestore();
    for (let i = 0; i < 5; i++) {
      const questions = Array(5).fill(question());
      questions[i] = 1;
      await assertFails(publish(db, today, challenge({ questions })));
    }
  });

  test("anonymous clients neither read nor publish", async () => {
    const db = as(null).firestore();
    await assertFails(getDoc(doc(db, "dailyChallenges", today)));
    await assertFails(publish(db, today, challenge()));
  });

  test("once published, the day is immutable — the losing device just reads it back", async () => {
    await seed((c) => setDoc(doc(c.firestore(), "dailyChallenges", today), challenge()));
    const db = verified(alice).firestore();
    await assertFails(publish(db, today, challenge()));
    await assertFails(publish(db, today, challenge({ questions: [] })));
    await assertFails(updateDoc(doc(db, "dailyChallenges", today), { questions: [] }));
    await assertFails(deleteDoc(doc(db, "dailyChallenges", today)));
    await assertSucceeds(getDoc(doc(db, "dailyChallenges", today)));
  });

  test("rejects a mismatched date, an empty or oversized round, and unknown fields", async () => {
    const db = verified(alice).firestore();
    await assertFails(publish(db, today, challenge({ date: "2020-01-01" })));
    await assertFails(publish(db, today, challenge({ questions: [] })));
    await assertFails(publish(db, today, challenge({ questions: "x" })));
    await assertFails(publish(db, today, challenge({ questions: Array(6).fill(question()) })));
    await assertFails(publish(db, today, challenge({ createdBy: alice })));
    await assertFails(publish(db, "not-a-date", challenge({ date: "not-a-date" })));
  });
});

describe("Storage profile photos", () => {
  const path = (uid) => `profile_photos/${uid}`;

  test("a user uploads and reads their own profile photo", async () => {
    const storage = as(alice).storage();
    await assertSucceeds(uploadBytes(storageRef(storage, path(alice)), smallImage(), { contentType: "image/jpeg" }));
    await assertSucceeds(getBytes(storageRef(storage, path(alice))));
  });

  test("signed-in users read someone else's profile photo, anonymous clients do not", async () => {
    await seed((c) => uploadBytes(storageRef(c.storage(), path(bob)), smallImage(), { contentType: "image/jpeg" }));
    await assertSucceeds(getBytes(storageRef(as(alice).storage(), path(bob))));
    await assertFails(getBytes(storageRef(as(null).storage(), path(bob))));
  });

  test("nobody uploads, overwrites or deletes someone else's photo", async () => {
    await seed((c) => uploadBytes(storageRef(c.storage(), path(bob)), smallImage(), { contentType: "image/jpeg" }));
    const storage = as(alice).storage();
    await assertFails(uploadBytes(storageRef(storage, path(bob)), smallImage(), { contentType: "image/jpeg" }));
    await assertFails(deleteObject(storageRef(storage, path(bob))));
  });

  test("rejects a non-image and a file over 5 MB", async () => {
    const storage = as(alice).storage();
    await assertFails(uploadBytes(storageRef(storage, path(alice)), smallImage(), { contentType: "text/plain" }));
    await assertFails(uploadBytes(storageRef(storage, path(alice)), bigImage(), { contentType: "image/jpeg" }));
  });

  test("anonymous clients neither read nor write", async () => {
    const storage = as(null).storage();
    await assertFails(getBytes(storageRef(storage, path(alice))));
    await assertFails(uploadBytes(storageRef(storage, path(alice)), smallImage(), { contentType: "image/jpeg" }));
  });
});

describe("Realtime Database chat", () => {
  const chat = "chat-global-youknow";

  test("a user posts messages keyed by their uid and reads the chat", async () => {
    const db = as(alice).database();
    await assertSucceeds(set(ref(db, `${chat}/${alice}-1000`), { text: "hola", authorName: "alice" }));
    await assertSucceeds(get(ref(db, chat)));
  });

  test("messages cannot impersonate, edit, exceed 50 chars or carry extra fields", async () => {
    const db = as(alice).database();
    await assertFails(set(ref(db, `${chat}/${bob}-1000`), { text: "soy bob", authorName: "bob" }));
    await assertFails(set(ref(db, `${chat}/${alice}-1`), { text: "x".repeat(51), authorName: "alice" }));
    await assertFails(set(ref(db, `${chat}/${alice}-2`), { text: "hi", authorName: "alice", admin: true }));
    await seed((c) => set(ref(c.database(), `${chat}/${bob}-5`), { text: "original", authorName: "bob" }));
    await assertFails(set(ref(db, `${chat}/${bob}-5`), { text: "edited", authorName: "bob" }));
  });

  test("any signed-in client trims old messages (the app keeps the latest 100)", async () => {
    await seed((c) => set(ref(c.database(), `${chat}/${bob}-5`), { text: "old", authorName: "bob" }));
    await assertSucceeds(remove(ref(as(alice).database(), `${chat}/${bob}-5`)));
  });

  test("anonymous clients neither read nor write", async () => {
    const db = as(null).database();
    await assertFails(get(ref(db, chat)));
    await assertFails(set(ref(db, `${chat}/x-1`), { text: "hi", authorName: "x" }));
  });
});

describe("Realtime Database groups", () => {
  const groups = "groups-youknow";

  test("the leader creates a group keyed <name>-<leaderUid>", async () => {
    const db = as(alice).database();
    await assertSucceeds(set(ref(db, `${groups}/Team-${alice}`), { description: "d", members: [alice] }));
    await assertSucceeds(get(ref(db, groups)));
  });

  test("nobody creates a group in someone else's name or overwrites one", async () => {
    await seed((c) => set(ref(c.database(), `${groups}/Team-${bob}`), { description: "d", members: [bob] }));
    const db = as(alice).database();
    await assertFails(set(ref(db, `${groups}/Fake-${bob}`), { description: "d", members: [alice] }));
    await assertFails(set(ref(db, `${groups}/Team-${bob}`), { description: "mine now", members: [alice] }));
  });

  test("members join and leave by rewriting the list (set and updateChildren)", async () => {
    await seed((c) => set(ref(c.database(), `${groups}/Team-${bob}`), { description: "d", members: [bob] }));
    const db = as(alice).database();
    await assertSucceeds(set(ref(db, `${groups}/Team-${bob}/members`), [bob, alice]));
    await assertSucceeds(update(ref(db, `${groups}/Team-${bob}`), { members: [bob] }));
    await assertFails(set(ref(db, `${groups}/Ghost-${bob}/members`), [alice]));
  });

  test("the leader deletes the group; others only once it has no members", async () => {
    await seed(async (c) => {
      await set(ref(c.database(), `${groups}/Team-${bob}`), { description: "d", members: [bob] });
      await set(ref(c.database(), `${groups}/Empty-${bob}`), { description: "d" });
    });
    await assertFails(remove(ref(as(alice).database(), `${groups}/Team-${bob}`)));
    await assertSucceeds(remove(ref(as(alice).database(), `${groups}/Empty-${bob}`)));
    await assertSucceeds(remove(ref(as(bob).database(), `${groups}/Team-${bob}`)));
  });

  test("everything outside the app's paths is denied", async () => {
    await assertFails(set(ref(as(alice).database(), "admin/flag"), true));
    await assertFails(get(ref(as(alice).database(), "/")));
  });
});
