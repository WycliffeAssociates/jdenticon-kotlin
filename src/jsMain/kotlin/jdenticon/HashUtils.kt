package jdenticon

@JsModule("js-sha1")
@JsNonModule
external fun sha1(arg: String): String

actual fun computeHash(value: String): String {
    return sha1(value)
}

actual fun Float.format(digits: Int): String {
    return this.asDynamic().toFixed(digits) as String
}