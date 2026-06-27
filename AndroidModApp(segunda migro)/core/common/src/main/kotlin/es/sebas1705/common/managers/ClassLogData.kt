package es.sebas1705.common.managers

abstract class ClassLogData {
    val packageName: String = this::class.java.packageName
    val className: String = this::class.simpleName ?: "UnknownClass"
}
