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
  email: "a@example.com", provider: 0, nickName: "alice", photoUrl: null, groupId: null,
  points: 0, credits: 10, logged: true, ...over,
});

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
  const today = "2026-09-30";
  const challenge = (over = {}) => ({
    date: today,
    questions: [{ question: "q", answers: ["a", "b"], correctAnswer: "a", category: "ANY", difficulty: "MEDIUM", quizType: "MULTIPLE" }],
    ...over,
  });

  test("the first device publishes the day's shared round", async () => {
    const db = as(alice).firestore();
    await assertSucceeds(setDoc(doc(db, "dailyChallenges", today), challenge()));
    await assertSucceeds(getDoc(doc(db, "dailyChallenges", today)));
  });

  test("anonymous clients neither read nor publish", async () => {
    const db = as(null).firestore();
    await assertFails(getDoc(doc(db, "dailyChallenges", today)));
    await assertFails(setDoc(doc(db, "dailyChallenges", today), challenge()));
  });

  test("once published, the day is immutable — the losing device just reads it back", async () => {
    await seed((c) => setDoc(doc(c.firestore(), "dailyChallenges", today), challenge()));
    const db = as(alice).firestore();
    await assertFails(setDoc(doc(db, "dailyChallenges", today), challenge({ questions: [] })));
    await assertFails(updateDoc(doc(db, "dailyChallenges", today), { questions: [] }));
    await assertFails(deleteDoc(doc(db, "dailyChallenges", today)));
    await assertSucceeds(getDoc(doc(db, "dailyChallenges", today)));
  });

  test("rejects a mismatched date, an empty or oversized round, and unknown fields", async () => {
    const db = as(alice).firestore();
    await assertFails(setDoc(doc(db, "dailyChallenges", today), challenge({ date: "2020-01-01" })));
    await assertFails(setDoc(doc(db, "dailyChallenges", today), challenge({ questions: [] })));
    await assertFails(setDoc(doc(db, "dailyChallenges", today), challenge({ questions: Array(21).fill(challenge().questions[0]) })));
    await assertFails(setDoc(doc(db, "dailyChallenges", today), challenge({ createdBy: alice })));
    await assertFails(setDoc(doc(db, "dailyChallenges", "not-a-date"), challenge({ date: "not-a-date" })));
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
