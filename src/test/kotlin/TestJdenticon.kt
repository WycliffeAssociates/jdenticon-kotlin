import jdenticon.Jdenticon
import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.File
import java.nio.file.Files

class TestJdenticon {

    fun safeFilename(filename: String): String = if (filename.isEmpty()) "_" else filename

    fun readSvgFromTestResourceDir(filename: String): String {
        val svgFile = File(javaClass.classLoader.getResource("svg-output/${safeFilename(filename)}.svg").file)
        return Files.readAllBytes(svgFile.toPath()).let { bytes -> String(bytes, Charsets.UTF_8) }
    }

    @Test
    fun `produces expected SVG output`() {
        val hashList = listOf(
            "Alice",
            "Bob",
            "deadbeef",
            "deadbeef123",
            "0123456789ABCDEF",
            "f49cf6381e322b147053b74e4500af8533ac1e4c"
        )
        hashList.forEach { hash ->
            val svg = Jdenticon.toSvg(hash, 100)
            //writeSvgToHomeDir(hash, svg)
            val expectedSvg = readSvgFromTestResourceDir(hash)
            assertEquals(expectedSvg, svg)
        }
    }

}
