# The Room TypeConverter parses List<String> with Gson through an anonymous TypeToken subclass;
# R8 full mode strips its generic signature unless it is kept.
-keepattributes Signature
-keep class com.google.gson.reflect.TypeToken { *; }
-keep class * extends com.google.gson.reflect.TypeToken
