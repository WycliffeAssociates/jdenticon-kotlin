package jdenticon

import java.math.BigInteger
import java.security.MessageDigest

// Mirrors the functionality at:
// https://github.com/dmester/jdenticon/blob/54fb9c1d1d66d5eb6849583cca219ae6ab986ee5/src/common/hashUtils.js

class HashUtils {

    companion object {

        // hash argument must be only hexadecimal characters, and minimum length of 11 characters
        private val MINIMUM_HEX_STRING_REGEX = "^[0-9a-fA-F]{11,}$".toRegex()

        /**
         * Inputs a value that might be a valid hash string for Jdenticon and returns it
         * if it is determined valid, otherwise a false value is returned.
         */
        fun isValidHash(hashCandidate: String): Boolean {
            return MINIMUM_HEX_STRING_REGEX.matches(hashCandidate)
        }

        /**
         * Computes a hash for the specified value. Currently SHA1 is used. This function
         * always returns a valid hash.
         */
        fun computeHash(value: String): String {
            val digest = MessageDigest.getInstance("SHA-1").run {
                reset()
                update(value.toByteArray())
                digest()
            }
            return BigInteger(1, digest).toString(16)
        }

        fun keepOrCreateHash(hashOrValue: String): String {
            return when (isValidHash(hashOrValue)) {
                true -> hashOrValue
                false -> computeHash(hashOrValue)
            }
        }

    }

}
