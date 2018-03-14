import org.apache.commons.codec.digest.DigestUtils

fun main(args : Array<String>) {
    var hash = DigestUtils.sha1Hex("Joe")
    var svg = toSvg(hash, 512, 0f)
    println(svg)
}
