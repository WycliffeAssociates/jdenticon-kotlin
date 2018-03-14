class SvgWriter(size: Int) {
    val size = size
    var _s = "<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"" +
                size + "\" height=\"" + size + "\" viewBox=\"0 0 " +
                size + ' ' + size + "\" preserveAspectRatio=\"xMidYMid meet\">";

    fun setBackground(fillColor: String, opacity: Float?) {
        opacity?.let {
            this._s += "<rect width=\"100%\" height=\"100%\" fill=\"" +
            fillColor + "\" opacity=\"" + String.format("%02f", opacity) + "\"/>"
        }
    }

    fun append(color: String, dataString: String) {
        this._s +=  "<path fill=\"" + color + "\" d=\"" + dataString + "\"/>"
    }

    override fun toString() : String {
        return this._s + "</svg>"
    }
}