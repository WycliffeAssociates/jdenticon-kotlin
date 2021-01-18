package jdenticon

class SvgRenderer(target: SvgWriter) :Renderer {

    var _pathsByColor = HashMap<String, SvgPath>()
    var _target = target
    var size = target.size
    lateinit var _path: SvgPath

    override fun setBackground(fillColor: String) {
        var re = Regex("^(#......)(..)?")
        var match = re.matchEntire(fillColor)?.groups?.get(1)?.value
        var opacityMatch = re.matchEntire(fillColor)?.groups?.get(2)?.value
        var opacity = opacityMatch?.let {
            opacityMatch.toInt(16) / 255f
        } ?: 1f
        var colorMatch = re.matchEntire(fillColor)?.groups?.get(1)?.value
        var color = colorMatch?.let {
            colorMatch
        } ?: "000000"
        this._target.setBackground(color, opacity)
    }

    override fun beginShape(color: String) {
        if (this._pathsByColor[color] == null) {
            this._pathsByColor[color] = SvgPath()
        }
        this._path = this._pathsByColor[color]!!
    }

    override fun endShape() {

    }

    override fun addPolygon(points: List<Point>) {
        this._path.addPolygon(points)
    }

    override fun addCircle(point: Point, diameter: Float, counterClockwise: Boolean) {
        this._path.addCircle(point, diameter, counterClockwise)
    }

    override fun finish() {
        /*
            Set is not ordered, so we can have different order of _pathsByColor.keys
            across different versions (for example, kotlin-js and kotlin-jvm iterate this set
            in a different order). To make tests stable we have to
            sort keys. It does not affect performance alot, because there are
            pretty small amount of keys.
        */
        for (color in this._pathsByColor.keys.sorted()) {
            this._target.append(color, this._pathsByColor[color]!!.dataString)
        }
    }
}