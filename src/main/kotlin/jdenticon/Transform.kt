package jdenticon

class Transform(x: Float, y: Float, size: Float, rotation: Float) {

    val _x = x
    val _y = y
    val _size = size
    val _rotation = rotation


    fun transformPoint(x: Float, y: Float, w: Float? = null, h: Float? = null): Point {
        val right = this._x + this._size
        val bottom = this._y + this._size
        val height = if (h != null) h else 0f
        val width = if (w != null) w else 0f
        return if (this._rotation == 1f) Point(right - y - height, this._y + x) else
            if (this._rotation == 2f) Point(right - x - width, bottom - y - height) else
                if (this._rotation == 3f) Point(this._x + y, bottom - x - width) else
                    Point(this._x + x, this._y + y)
    }

    companion object {
        fun noTransform(): Transform {
            return Transform(0f, 0f, 0f, 0f)
        }
    }
}
