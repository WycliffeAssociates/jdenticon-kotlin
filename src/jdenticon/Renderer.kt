package jdenticon

interface Renderer {
    fun setBackground(fillColor: String)
    fun beginShape(color: String)
    fun endShape()
    fun addPolygon(points: List<Point>)
    fun addCircle(point: Point, diameter: Float, counterClockwise: Boolean)
    fun finish()
}