# Firestore maps these documents by reflection (toObject / set): R8 must keep their names, fields,
# no-arg constructor and the @PropertyName annotations, or release builds read empty documents and
# write obfuscated field names.
-keepattributes *Annotation*, Signature
-keep class es.sebas1705.firestore.documents.** { *; }
