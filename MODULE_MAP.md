# Module Map

Quick module inventory grouped by layer.

## App

- `:app`

## Core

- `:core:common`
- `:core:designsystem`
- `:core:resources`
- `:core:ui`

## Data

- `:data:analytics`
- `:data:authentication`
- `:data:couchbase`
- `:data:datastore`
- `:data:files`
- `:data:firestore`
- `:data:realtime`
- `:data:repositories`
- `:data:retrofit`
- `:data:room`

## Domain

- `:domain:managers`
- `:domain:mappers`
- `:domain:models`
- `:domain:providers`
- `:domain:services`
- `:domain:usescases:analytics`
- `:domain:usescases:auth`
- `:domain:usescases:chat`
- `:domain:usescases:families`
- `:domain:usescases:fill`
- `:domain:usescases:groups`
- `:domain:usescases:mysteryNumber`
- `:domain:usescases:news`
- `:domain:usescases:opendb`
- `:domain:usescases:quiz`
- `:domain:usescases:settings`
- `:domain:usescases:survey`
- `:domain:usescases:user`
- `:domain:usescases:wordPass`

## Feature

- `:feature:main` — root navigation (`AppNav`), splash / network-error gate
- `:feature:auth` — menu, log in, sign up (nested `AuthNav`)
- `:feature:home` — tabbed home: main, profile, chat, play, groups (nested `HomeNav`)
- `:feature:game` — mystery number, quiz, word pass, families
- `:feature:guide` — first-run onboarding
- `:feature:settings`
- `:feature:survey`
- `:feature:splash`
- `:feature:networkerror`

## Notes

- Module source of truth: `settings.gradle.kts`.
- When adding/removing modules, update this file and `docs/module-checklist.md`.
