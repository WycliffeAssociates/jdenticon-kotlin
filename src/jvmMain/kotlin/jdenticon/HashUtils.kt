package jdenticon

import java.math.BigInteger
import java.security.MessageDigest

actual fun computeHash(value: String): String {
    val digest = MessageDigest.getInstance("SHA-1").run {
        reset()
        update(value.toByteArray())
        digest()
    }
    return BigInteger(1, digest).toString(16)
}

actual fun Float.format(digits: Int): String {
    return String.format("%.${digits}f", this)
}