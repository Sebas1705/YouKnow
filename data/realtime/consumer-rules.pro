# Realtime Database maps these JSON classes by reflection (getValue / setValue): keep their names,
# fields and no-arg constructor so release builds do not read or write obfuscated keys.
-keepattributes Signature
-keep class es.sebas1705.realtime.jsons.** { *; }
