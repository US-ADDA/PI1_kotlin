import java.util.function.Function
import java.util.function.Predicate


class Exercise1 {

    companion object {

        fun functional(
            list: List<String>,
            stringPredicate: Predicate<String>,
            integerPredicate: Predicate<Int>,
            stringIntoInteger: Function<String, Int>
        ): Boolean {
            return list.filter { stringPredicate.test(it) }// Nos quedamos con aquellas que cumplen la condición de stringPredicate
                .map { stringIntoInteger.apply(it) } // Aplicamos la función stringIntoInteger a cada elemento de la lista
                .any { integerPredicate.test(it) } // Devolvemos si algún elemento cumple la condición de integerPredicate
        }

        fun iterativeFor(
            list: List<String>,
            stringPredicate: Predicate<String>,
            integerPredicate: Predicate<Int>,
            stringIntoInteger: Function<String, Int>
        ): Boolean {
            for (element in list) {
                if (stringPredicate.test(element) && integerPredicate.test(stringIntoInteger.apply(element))) return true
            }
            return false
        }

        fun iterativeWhile(
            list: List<String>,
            stringPredicate: Predicate<String>,
            integerPredicate: Predicate<Int>,
            stringIntoInteger: Function<String, Int>
        ): Boolean {
            var i = 0
            while (i < list.size) {
                if (stringPredicate.test(list[i]) && integerPredicate.test(stringIntoInteger.apply(list[i]))) return true
                i++
            }
            return false
        }

        fun recursiveFinal(
            list: List<String>,
            stringPredicate: Predicate<String>,
            integerPredicate: Predicate<Int>,
            stringIntoInteger: Function<String, Int>
        ): Boolean {
            return recursiveFinal(list, stringPredicate, integerPredicate, stringIntoInteger, false, 0)
        }

        private fun recursiveFinal(
            list: List<String>,
            stringPredicate: Predicate<String>,
            integerPredicate: Predicate<Int>,
            stringIntoInteger: Function<String, Int>,
            result: Boolean,
            i: Int
        ): Boolean {
            if (i == list.size) return result
            if (stringPredicate.test(list[i]) && integerPredicate.test(stringIntoInteger.apply(list[i]))) return true
            return recursiveFinal(list, stringPredicate, integerPredicate, stringIntoInteger, result, i + 1)
        }
    }
}
