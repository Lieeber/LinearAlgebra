class MyMatrix {
    private var _values: MutableList<MutableList<Double>>

    constructor(vararg arg: MutableList<Double>) {
        check(arg)
        _values = arg.toMutableList()
    }

    constructor(arg: MutableList<MutableList<Double>>) {
        check(arg.toTypedArray())
        _values = arg
    }


    companion object {
        //创建零矩阵
        fun zero(row: Int, col: Int): MyMatrix {
            return MyMatrix(MutableList(row) { MutableList(col) { 0.0 } })
        }
    }

    //矩阵的形状
    fun shape(): String {
        return "(${_values.size}行, ${_values[0].size}列)"
    }

    //矩阵的行数
    fun rowNum(): Int {
        return _values.size
    }

    //矩阵的列数
    fun colNum(): Int {
        return _values[0].size
    }

    //矩阵的元素个数
    fun size(): Int {
        return colNum() * rowNum()
    }

    fun getItem(row: Int, col: Int): Double {
        if (row > rowNum() - 1 || col > colNum() - 1 || row < 0 || col < 0) {
            throw RuntimeException("角标越界了")
        }
        return _values[row][col]
    }

    //获取行向量
    fun getRowVector(row: Int): MyVector {
        return MyVector(_values[row])
    }

    //获取列向量
    fun getColVector(col: Int): MyVector {
        return MyVector(_values.map {
            it[col]
        } as MutableList<Double>)
    }


    /**
     * 矩阵相加
     */
    operator fun plus(other: MyMatrix): MyMatrix {
        if (shape() != other.shape()) {
            throw RuntimeException("两个矩阵的形状必须一样才能相加")
        }
        return MyMatrix(_values.mapIndexed { rowIndex, mutableList ->
            mutableList.mapIndexed { colIndex, item ->
                other._values[rowIndex][colIndex] + item
            }
        } as MutableList<MutableList<Double>>)
    }

    /**
     * 矩阵相减
     */
    operator fun minus(other: MyMatrix): MyMatrix {
        if (shape() != other.shape()) {
            throw RuntimeException("两个矩阵的形状必须一样才能相减")
        }
        return MyMatrix(_values.mapIndexed { rowIndex, mutableList ->
            mutableList.mapIndexed { colIndex, item ->
                item - other._values[rowIndex][colIndex]
            }
        } as MutableList<MutableList<Double>>)
    }

    /**
     * 矩阵与标量的乘法
     */
    operator fun times(i: Double): MyMatrix {
        return MyMatrix(_values.map { it.map { it * i } } as MutableList<MutableList<Double>>)
    }

    /**
     * 矩阵和矩阵的点乘
     */
    operator fun times(other: MyMatrix): MyMatrix {
        val m = MyMatrix(MutableList(this.rowNum()) { MutableList(other.colNum()) { 0.0 } })
        for (rowIndex in 0 until rowNum()) {
            for (colIndex in 0 until other.colNum()) {
                m._values[rowIndex][colIndex] = getRowVector(rowIndex) * other.getColVector(colIndex)
            }
        }
        return m
    }

    /**
     * 矩阵和向量的点乘
     */
    operator fun times(other: MyVector): MyVector {
        return MyVector(_values.map {
            it.mapIndexed { index, d -> d * other.list[index] }.reduce { acc, d -> acc + d }
        } as MutableList<Double>)
    }

    /**
     * 矩阵除以一个标量
     */
    operator fun div(d: Double): MyMatrix {
        return 1 / d * this
    }

    operator fun unaryPlus(): MyMatrix {
        return 1.0 * this
    }

    operator fun unaryMinus(): MyMatrix {
        return -1.0 * this
    }

    private fun check(arg: Array<out MutableList<Double>>) {
        if (arg.size === 0) {
            throw RuntimeException("矩阵不能为空")
        }
        if (arg[0].size === 0) {
            throw RuntimeException("矩阵不能有空行")
        }
        var preSize = arg[0].size
        arg.forEach {
            if (it.size !== preSize) {
                throw RuntimeException("矩阵每行必须长度相等")
            }
        }
    }

    override fun toString(): String {
        return "Matrix$_values"
    }
}

operator fun Double.times(m: MyMatrix): MyMatrix {
    return m * this

}