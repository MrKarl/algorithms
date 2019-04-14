import kotlin.test.assertFalse
import kotlin.test.assertTrue

interface Sort<T> {
    fun sort(arr: List<T>, target: Int, sortBy: (() -> Unit)? = null): List<T>
}

interface Search {
    fun isInArray(target: Int, arr: List<Int>): Boolean
}

class BinarySearch: Search {
    override fun isInArray(target: Int, arr: List<Int>): Boolean {
        val sortedArray = arr.sorted()
        val firstIdx = 0
        val lastIdx = sortedArray.size - 1

        var currentFirst = firstIdx
        var currentLast = lastIdx

        while (currentFirst <= currentLast) {
            val idx = (currentLast+currentFirst)/2
            val value = sortedArray[idx]

            when {
                value == target -> {
                    return true
                }
                value > target -> {
                    currentLast = idx - 1
                }
                value < target -> {
                    currentFirst = idx + 1
                }
            }
        }

        return false
    }
}

fun main(args: Array<String>) {
    val search = BinarySearch()

    // ordered array
    val didFound1 = search.isInArray(2, listOf(1,2,3,4,5,6,7,8,9))
    val didFound2 = search.isInArray(-1, listOf(1,2,3,4,5,6,7,8,9))
    val didFound3 = search.isInArray(1, listOf(1,2,3,4,5,6,7,8))

    // unordered array
    val didFound4 = search.isInArray(9, listOf(9,8,7,6,5,4,3,2,1))

    assertTrue(didFound1)
    assertFalse(didFound2)
    assertTrue(didFound3)
    assertTrue(didFound4)
}