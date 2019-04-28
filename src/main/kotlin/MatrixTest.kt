fun main() {
    val m1 = MyMatrix(
        mutableListOf(
            mutableListOf(1.5, 0.0),
            mutableListOf(0.0, 2.0)
        )
    )
    println(m1)

    val m2 =
        MyMatrix(
            mutableListOf(0.0, 4.0, 5.0),
            mutableListOf(0.0, 0.0, 3.0)
        )
    println(m2)

//    println(m1.shape())
//    println(m1.rowNum())
//    println(m1.colNum())
//    println(m1.size())
//    println(m1.getItem(2, 1))
//    println(m1.getColVector(2))
//    println(m1.getRowVector(1))
//
//    println(m1 + m2)
//    println(m1 - m2)
//
//    println(m1 * 3.0)
//    println(3.0 * m1)
//    println(m1 / 2.0)
//
//    println(+m1)
//    println(-m1)
//
//
//    println(MyMatrix.zero(3, 4))

    val v = MyVector(5.0, 3.0)
    println(m1 * v)
    println(m1 * m2)

    val a = MyMatrix(mutableListOf(1.0, 2.0), mutableListOf(3.0, 4.0))
    val b = MyMatrix(mutableListOf(5.0, 6.0), mutableListOf(7.0, 8.0))
//矩阵相乘不满足交换律
    println(a * b)
    println(b * a)

}


