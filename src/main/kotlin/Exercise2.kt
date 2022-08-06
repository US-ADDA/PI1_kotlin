class Exercise2 {

    companion object {
        fun functional(lists: List<List<String>>): Map<Int, List<String>> {
            return lists.flatten() // Aplana la lista de listas, por eso flatten.
                .groupBy { it.length } // Agrupa por longitud de la palabra.
        }

        fun iterativeFor(lists: List<List<String>>): Map<Int, List<String>> {
            val result = mutableMapOf<Int, List<String>>()
            for (list in lists) {
                for (word in list) {
                    val length = word.length
                    result[length] = if (result.containsKey(length)) result[length]!! + word else mutableListOf(word)
                }
            }
            return result
        }

        fun iterativeWhile(lists: List<List<String>>): Map<Int, List<String>> {
            val result = mutableMapOf<Int, List<String>>()
            var i = 0
            while (i < lists.size) {
                val list = lists[i]
                for (word in list) {
                    val length = word.length
                    result[length] = if (result.containsKey(length)) result[length]!! + word else mutableListOf(word)
                }
                i++
            }
            return result
        }

        fun recursiveFinal(lists: List<List<String>>): Map<Int, List<String>> {
            return recursiveFinal1(lists, mutableMapOf(), 0)
        }

        private fun recursiveFinal1(
            lists: List<List<String>>,
            result: MutableMap<Int, List<String>>,
            i: Int
        ): MutableMap<Int, List<String>> {
            return if (lists.size == i) result else recursiveFinal1(lists, recursiveFinal2(lists[i], result, 0), i + 1)
        }

        private fun recursiveFinal2(
            lists: List<String>,
            result: MutableMap<Int, List<String>>,
            j: Int
        ): MutableMap<Int, List<String>> {
            return if (lists.size == j) result
            else {
                val word: String = lists[j]
                val length: Int = word.length
                result[length] = if (result.containsKey(length)) result[length]!! + word else mutableListOf(word)
                recursiveFinal2(lists, result, j + 1)
            }
        }


    }
}
