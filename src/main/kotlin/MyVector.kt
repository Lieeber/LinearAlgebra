class MyVector() {
    var list: MutableList<Double> = mutableListOf()

    constructor(vararg args: Double) : this() {
        list = args.toMutableList()
    }

    constructor(args: MutableList<Double>) : this() {
        list = args
    }

    constructor(vector: MyVector) : this() {
        this.list = vector.list
    }

    companion object {
        fun zero(dim: Int): MyVector {
            return MyVector(MutableList(dim) { 0.0 })
        }
    }

    private val size
        get() = list.size

    /**
     * 求向量的模
     */
    fun norm(): Double {
        return Math.sqrt(list.sumByDouble { Math.pow(it, 2.toDouble()) })
    }

    /**
     * 求向量的单位向量
     */
    fun normalize(): MyVector {
        if (norm() === 0.0) {
            throw RuntimeException("零向量不能求单位向量")
        }
        return MyVector(this.list.map { it / norm() } as MutableList<Double>)
    }

    operator fun plus(other: MyVector): MyVector {
        if (size !== other.size) {
            throw RuntimeException("长度不一样的向量无法相加")
        }
        val mutableList = list.mapIndexed { index, item ->
            other.list[index] + item
        } as MutableList<Double>

        return MyVector(mutableList)
    }


    operator fun minus(other: MyVector): MyVector {
        if (size != other.size) {
            throw RuntimeException("长度不一样的向量无法相减")
        }
        return MyVector(list.mapIndexed { index, item ->
            item - other.list[index]
        } as MutableList<Double>)
    }

    /**
     * 向量的数乘
     */
    operator fun times(i: Double): MyVector {
        return MyVector(list.map {
            it * i
        } as MutableList<Double>)
    }

    /**
     * 向量的点乘
     */
    operator fun times(other: MyVector): Double {
        return list.mapIndexed { index, item ->
            item * other.list[index]
        }.sum()

    }


    override fun toString(): String {
        return list.toString()
    }

    operator fun div(i: Double): MyVector {
        return MyVector(this.list.map {
            it / i
        } as MutableList<Double>)
    }
}

/**
 * 向量的数乘
 */
inline operator fun Double.times(vector: MyVector): MyVector {
    return vector * this
}


