import java.util.stream.Stream

class Exercise3 {

    companion object {
        fun functional(start: Int, end: Int): List<Pair<Int, Int>> {
            return Stream
                .iterate(
                    Pair(0, start), // Cuando empieza.
                    { it.first < end }, // Cuando termina.
                    { Pair(it.first + 1, if (it.first % 3 == 1) it.second + 1 else it.second + it.first) }) // Siguiente
                .toList().sortedBy { it.first }
        }

        fun iterativeWhile(start: Int, end: Int): List<Pair<Int, Int>> {
            var current: Pair<Int, Int> = Pair(0, start)
            val result: MutableList<Pair<Int, Int>> = mutableListOf()
            while (current.first < end) {
                result += Pair(current.first, current.second)
                current = Pair(
                    current.first + 1,
                    if (current.first % 3 == 1) current.second + 1 else current.second + current.first
                )

            }
            return result.sortedBy { it.first }
        }

        fun recursiveFinal(start: Int, end: Int): List<Pair<Int, Int>> {
            return recursiveFinal(end, mutableListOf(), Pair(0, start)).sortedBy { it.first }
        }

        private fun recursiveFinal(end: Int, list: List<Pair<Int, Int>>, current: Pair<Int, Int>): List<Pair<Int, Int>> {
            if (current.first < end) return recursiveFinal(
                end,
                list + current,
                Pair(
                    current.first + 1,
                    if (current.first % 3 == 1) current.second + 1 else current.second + current.first
                )
            )
            return list
        }

        fun recursiveNoFinal(start: Int, end: Int): List<Pair<Int, Int>> {
            return recursiveNoFinal(end, Pair(0, start)).sortedBy { it.first }
        }

        private fun recursiveNoFinal(end: Int, current: Pair<Int, Int>): List<Pair<Int, Int>> {
            if (current.first < end) return recursiveNoFinal(
                end,
                Pair(
                    current.first + 1,
                    if (current.first % 3 == 1) current.second + 1 else current.second + current.first
                )
            ) + current
            return listOf()
        }
    }
}
