package jdenticon

import java.lang.IllegalArgumentException

class Jdenticon {

    companion object {

        // hash argument must be only hexadecimal characters, and minimum length of 11 characters
        val REQUIRED_HEX_STRING_REGEX = "^[0-9a-fA-F]{11,}$".toRegex()

        /**
         * Draws an identicon as an SVG string.
         * @param hash - A hexadecimal hash string of at least 11 characters.
         * @param size - Icon size in pixels.
         * @param padding - Optional padding in percents. Extra padding might be added to center the rendered identicon.
         * @returns SVG string
         */
        fun toSvg(hash: String, size: Int, padding: Float? = null): String {
            REQUIRED_HEX_STRING_REGEX.matches(hash) || throw IllegalArgumentException("hash argument must be hexadecimal characters and at least length 11.")

            var writer = SvgWriter(size)
            var renderer = SvgRenderer(writer)
            IconGenerator(
                    renderer,
                    hash,
                    0f,
                    0f,
                    size.toFloat(),
                    padding,
                    getCurrentConfig()
            );
            return writer.toString()
        }

        /**
         * Gets the normalized current Jdenticon color configuration. Missing fields have default values.
         */
        fun getCurrentConfig(): Config {
            var backColor = "#FFFFFF"
            /**
             * Creates a lightness range.
             */
            fun lightness(configName: String, defaultMin: Float, defaultMax: Float): (Float) -> Float {
                var range = arrayOf(defaultMin, defaultMax)

                /**
                 * Gets a lightness relative the specified value in the specified lightness range.
                 */
                return fun(value: Float): Float {
                    var value2 = range[0] + value * (range[1] - range[0])
                    return if (value2 < 0) 0f else if (value2 > 1) 1f else value2
                }
            }

            return Config(
                    0.5f,
                    lightness("color", 0.4f, 0.8f),
                    lightness("grayscale", 0.3f, 0.9f),
                    Color.parse(backColor)
            )
        }
    }
}
