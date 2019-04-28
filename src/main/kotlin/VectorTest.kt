fun main() {
    var vector = MyVector(1.0, 2.0, 9.1)
    var vector2 = MyVector(5.5, 1.8, 3.0)
    val sumVector = vector + vector2
    println(sumVector)
    println(vector - vector2)
    println(vector * 3.0)
    println(3.0 * vector)
    println(vector / 2.0)
    println(MyVector.zero(10))
    println(MyVector(3.0,4.0).norm())
    println(MyVector(3.toDouble(),4.toDouble()).normalize())
    println(MyVector(3.toDouble(),4.toDouble()).normalize().norm())
    println(vector * vector2)
}




