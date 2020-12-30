import jdenticon.Jdenticon
import org.junit.Assert.assertEquals
import org.junit.Assert.fail
import org.junit.Test
import java.lang.IllegalArgumentException

class TestJdenticon {

    @Test
    fun `check hex string regex correctness`() {
        val regex = Jdenticon.REQUIRED_HEX_STRING_REGEX

        // these fail to match
        assertEquals(false, regex.matches(""))
        assertEquals(false, regex.matches("0"))
        assertEquals(false, regex.matches("1"))
        assertEquals(false, regex.matches("A"))
        assertEquals(false, regex.matches("f"))
        assertEquals(false, regex.matches("0123456789"))
        assertEquals(false, regex.matches("01234abdce"))
        assertEquals(false, regex.matches("56789FEDCB"))
        assertEquals(false, regex.matches("1122334455ZZYYXX"))

        // these are valid matches
        assertEquals(true, regex.matches("0123456789A"))
        assertEquals(true, regex.matches("0123456789ABCDEF"))
        assertEquals(true, regex.matches("0123456789abcdef"))
    }

    fun expectIllegalArgumentException(failMessage: String, fn: () -> Unit) {
        try {
            fn()
            fail(failMessage)
        }
        catch (e: IllegalArgumentException) {
            // correct exception is thrown
        }
    }

    @Test
    fun `throws exception when hash string is not valid`() {
        expectIllegalArgumentException("should not allow empty hash") { Jdenticon.toSvg("", 80, null) }
        expectIllegalArgumentException("should not allow short hash") { Jdenticon.toSvg("1", 80, null) }
        expectIllegalArgumentException("should not allow less than 11 character hash") { Jdenticon.toSvg("0123456789", 80, null) }
        expectIllegalArgumentException("should not allow non hexadecimal hash") { Jdenticon.toSvg("0123456789zzzzzz", 80, null) }
    }

    @Test
    fun `accepts valid hash strings`() {
        try {
            Jdenticon.toSvg("0123456789abcdef", 80, null)
            Jdenticon.toSvg("0123456789ABCDEF", 80, null)
        }
        catch (e: IllegalArgumentException) {
            fail(e.message)
        }
    }

}
