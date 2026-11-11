// One-off production migration, run by the project owner, never by CI.
//
// Removes the `email` field from every users/{uid} document: any signed-in user can read those
// documents, and the email now lives only in Firebase Auth. It also reports documents the app could
// not map (written before the rules checked their shape); it never changes or deletes those.
//
// It lives in firebase/rules-test so it shares that package's dependencies and is tested against the
// emulator (migrations.test.js). Full procedure: docs/FIREBASE.md.
//
//   cd firebase/rules-test && npm ci
//   gcloud auth application-default login
//   node migrations/remove-user-emails.mjs --project youknow-tfg           # dry run, writes nothing
//   node migrations/remove-user-emails.mjs --project youknow-tfg --apply
//
// Safe to run again: documents without the field are skipped. Emails are never printed. Malformed
// documents are listed by id, and a users/ id is a uid, so keep that output private.
import { pathToFileURL } from "node:url";
import { parseArgs } from "node:util";
import { applicationDefault, initializeApp } from "firebase-admin/app";
import { FieldPath, FieldValue, getFirestore } from "firebase-admin/firestore";

const PAGE_SIZE = 400; // A write batch holds 500 at most.
const MAX_INT = 2147483647; // The app maps these fields onto a Kotlin Int.

const isCounter = (value) => Number.isInteger(value) && value >= 0 && value <= MAX_INT;
const isText = (value) => typeof value === "string" && value.trim().length > 0;

/** Why the app could not use this users/{uid} document; empty when it can. */
export function userProblems(user) {
  const problems = [];
  if (typeof user.nickName !== "string") problems.push("nickName");
  if (!isCounter(user.points)) problems.push("points");
  if (!isCounter(user.credits)) problems.push("credits");
  if ("provider" in user && !Number.isInteger(user.provider)) problems.push("provider");
  if ("logged" in user && typeof user.logged !== "boolean") problems.push("logged");
  for (const field of ["photoUrl", "groupId"]) {
    if (user[field] != null && typeof user[field] !== "string") problems.push(field);
  }
  return problems;
}

/** Why the app could not play this dailyChallenges/{date} document; empty when it can. */
export function challengeProblems(challenge) {
  if (!Array.isArray(challenge.questions) || challenge.questions.length === 0) return ["questions"];
  const playable = (question) =>
    isText(question?.question) &&
    Array.isArray(question.answers) &&
    question.answers.length >= 2 &&
    question.answers.every(isText) &&
    question.answers.includes(question.correctAnswer);
  return challenge.questions.every(playable) ? [] : ["questions"];
}

async function forEachPage(collection, handle) {
  let last = null;
  for (;;) {
    let page = collection.orderBy(FieldPath.documentId()).limit(PAGE_SIZE);
    if (last) page = page.startAfter(last);
    const { docs, size } = await page.get();
    if (size === 0) return;
    await handle(docs);
    last = docs[size - 1];
    if (size < PAGE_SIZE) return;
  }
}

/**
 * @param db Firestore instance of the Admin SDK (it bypasses the security rules).
 * @param apply Whether to write; without it nothing changes.
 * @returns What was found, with the ids of the documents the app can't use.
 */
export async function migrate(db, { apply = false } = {}) {
  const report = { users: 0, withEmail: 0, removed: 0, malformedUsers: [], challenges: 0, malformedChallenges: [] };

  await forEachPage(db.collection("users"), async (docs) => {
    const batch = db.batch();
    let writes = 0;
    for (const doc of docs) {
      const data = doc.data();
      report.users++;
      if (userProblems(data).length > 0) report.malformedUsers.push(doc.id);
      if (!("email" in data)) continue;
      report.withEmail++;
      batch.update(doc.ref, { email: FieldValue.delete() });
      writes++;
    }
    if (apply && writes > 0) {
      await batch.commit();
      report.removed += writes;
    }
  });

  await forEachPage(db.collection("dailyChallenges"), async (docs) => {
    for (const doc of docs) {
      report.challenges++;
      if (challengeProblems(doc.data()).length > 0) report.malformedChallenges.push(doc.id);
    }
  });

  return report;
}

async function main() {
  const { values } = parseArgs({
    options: { project: { type: "string" }, apply: { type: "boolean", default: false } },
  });
  if (!values.project) {
    console.error("Usage: node migrations/remove-user-emails.mjs --project <id> [--apply]");
    process.exit(2);
  }

  const app = initializeApp({ credential: applicationDefault(), projectId: values.project });
  const report = await migrate(getFirestore(app), { apply: values.apply });

  console.log(values.apply ? `Applied to ${values.project}.` : `Dry run on ${values.project}: nothing was written.`);
  if (!values.apply && report.withEmail > 0) console.log("Nothing changed: re-run with --apply to remove those emails (after the rules and the app are out, see docs/FIREBASE.md).");
  console.log(`users: ${report.users}, with email: ${report.withEmail}, email removed: ${report.removed}`);
  console.log(`dailyChallenges: ${report.challenges}`);
  console.log(`malformed users (${report.malformedUsers.length}): ${report.malformedUsers.join(", ")}`);
  console.log(`malformed dailyChallenges (${report.malformedChallenges.length}): ${report.malformedChallenges.join(", ")}`);
}

if (import.meta.url === pathToFileURL(process.argv[1]).href) await main();
