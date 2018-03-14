import kotlin.math.floor

class IconGenerator(renderer: SvgRenderer, hash: String, x: Float, y: Float, size: Int, padding: Int?, config: Map<String, Any>) {

    var _hash = hash
    val _renderer = renderer

    // Calculate padding
    var _padding = size * (if(padding != null) padding.toFloat() else .08f)
    var _size = size - (padding ?: 0) * 2

    var graphics = Graphics(renderer)

    // Calculate cell size and ensure it is an integer
    var cell = floor(size / 4f)

    // Since the cell size is integer based, the actual icon will be slightly smaller than specified => center icon
    var _x = x + floor((padding ?: 0) + size / 2f - cell * 2f);
    var _y = y + floor((padding ?: 0) + size / 2f - cell * 2f);

    // AVAILABLE COLORS
    var hue = hash.substring(-7).toInt(16) / 0xfffffff

    // Available colors for this icon
    var availableColors = ColorTheme(hue, config)

    // The index of the selected colors
    var selectedColorIndexes = ArrayList<Int>()
    var index = 0

    fun renderShape(colorIndex: Int, shapes: List<(Graphics, Float, Int?) -> Unit>, index: Int, rotationIndex: Int, positions: Array<Array<Int>>) {
        var r = if(rotationIndex != null)_hash.elementAt(rotationIndex).toString().toInt(16) else 0
        var shape = shapes[_hash.elementAt(index).toString().toInt(16) % shapes.size]

        _renderer.beginShape(availableColors[selectedColorIndexes[colorIndex]])
        for (i in 0 until positions.size) {
            graphics._transform = Transform(
                    _x + positions[i][0] * cell,
                    _y + positions[i][1] * cell,
                    cell,
                    ((r++ % 4).toFloat())
            )
            shape(graphics, cell, i)
        }

        _renderer.endShape();
    }

    fun isDuplicate(values: List<Color>) : Boolean {
        if (values.indexOf(index) >= 0) {
            for (i in 0 until values.length) {
                if (selectedColorIndexes.indexOf(values[i]) >= 0) {
                    return true
                }
            }
        }
    }

    for (var i = 0; i < 3; i++) {
        index = parseInt(hash.charAt(8 + i), 16) % availableColors.length;
        if (isDuplicate([0, 4]) || // Disallow dark gray and dark color combo
                isDuplicate([2, 3])) { // Disallow light gray and light color combo
            index = 1;
        }
        selectedColorIndexes.push(index);
    }

    // ACTUAL RENDERING
    // Sides
    renderShape(0, shapes.outer, 2, 3, [[1, 0], [2, 0], [2, 3], [1, 3], [0, 1], [3, 1], [3, 2], [0, 2]]);
    // Corners
    renderShape(1, shapes.outer, 4, 5, [[0, 0], [3, 0], [3, 3], [0, 3]]);
    // Center
    renderShape(2, shapes.center, 1, null, [[1, 1], [2, 1], [2, 2], [1, 2]]);

    renderer.finish();
};