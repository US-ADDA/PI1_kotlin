import java.util.stream.Stream
import kotlin.math.absoluteValue
import kotlin.math.pow

class Exercise4 {

    companion object {
        fun functional(n: Double, e: Double): Double {
            val pair = Stream.iterate(
                Pair(0.0, n) // Definimos el comienzo como 0 (nos dicen que se calcula la raíz cúbica de un número positivo) y el valor del que queremos saber la raíz cúbica.
            ) {
                val middle = it.first.plus(it.second).div(2) // Calculamos el valor medio.
                // Analizamos donde puede estar aplicando búsqueda binaria (de inicio a medio y de medio a fin).
                if (middle.pow(3) > n && it.first.pow(3) < n) Pair(it.first, middle)
                else if (middle.pow(3) < n && it.second.pow(3) > n) Pair(middle, it.second)
                else it
            }.filter { it.first.plus(it.second).div(2).pow(3).minus(n).absoluteValue < e.pow(3) }.findFirst() // Indicamos que termine cuando el error al cubo sea mayor que la resta del medio al cubo con el valor del que qeremos saber su rai´z cúbica
                .orElse(Pair(0.0, 0.0))
            return pair.first.plus(pair.second).div(2)
        }

        fun iterativeWhile(n: Double, e: Double): Double {
            var current = Pair(0.0, n)
            var middle = n.div(2)
            while (middle.pow(3).minus(n).absoluteValue > e.pow(3)) {
                current = if (middle.pow(3) > n && current.first.pow(3) < n) Pair(current.first, middle)
                else if (middle.pow(3) < n && current.second.pow(3) > n) Pair(middle, current.second)
                else current
                middle = current.first.plus(current.second).div(2)
            }
            return middle
        }

        fun recursiveFinal(n: Double, e: Double): Double {
            return recursiveFinal(n, e, Pair(0.0, n), n.div(2))
        }

        private fun recursiveFinal(n: Double, e: Double, current: Pair<Double, Double>, middle: Double): Double {
            return if (middle.pow(3).minus(n).absoluteValue <= e.pow(3)) middle
            else {
                val auxCurrent = if (middle.pow(3) > n && current.first.pow(3) < n) Pair(current.first, middle)
                else if (middle.pow(3) < n && current.second.pow(3) > n) Pair(middle, current.second)
                else current
                val auxMiddle = auxCurrent.first.plus(auxCurrent.second).div(2)
                recursiveFinal(n, e, auxCurrent, auxMiddle)
            }
        }
    }


}
