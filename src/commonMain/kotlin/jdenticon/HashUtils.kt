package jdenticon

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

        fun keepOrCreateHash(hashOrValue: String): String {
            return when (isValidHash(hashOrValue)) {
                true -> hashOrValue
                false -> computeHash(hashOrValue)
            }
        }

    }

}

expect fun computeHash(value: String): String