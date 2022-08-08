import com.google.common.io.Files
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.File
import java.io.IOException
import java.nio.charset.StandardCharsets
import java.util.function.Function
import java.util.function.Predicate
import kotlin.test.assertEquals


class TestExercise1 {

    private var lines: List<List<String>>? = null
    private var correctResults: List<Boolean> = listOf(false, true, true, true, false, true, true, true, true)
    private var sep = ","
    private var stringPredicate: Predicate<String> =
        Predicate { it.contains("a") || it.contains("e") || it.contains("i") || it.contains("o") || it.contains("u") }
    private var integerPredicate: Predicate<Int> = Predicate { it % 2 == 0 }
    private var stringIntoInteger: Function<String, Int> = Function { it.length }

    @BeforeEach
    fun setup() {
        val file: File = File("src/test/resources/PI1E1_DatosEntrada.txt")
        try {
            lines = Files.readLines(file, StandardCharsets.UTF_8).map { it.split(sep) }
                .stream().skip(3).toList() // Si se eliminan las tres primeras líneas del test, esto sobra.
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    @Test
    fun testFunctional() {
        System.out.println("Test Functional")
        val functionalResults =
            lines?.map { Exercise1.functional(it, stringPredicate, integerPredicate, stringIntoInteger) }
        assertEquals(correctResults, functionalResults)
    }

    @Test
    fun testIterativeWhile() {
        val whileResults: List<Boolean> =
            lines!!.map { Exercise1.iterativeWhile(it, stringPredicate, integerPredicate, stringIntoInteger) }
        assertEquals(correctResults, whileResults, "Iterative while should return the correct results!!")
    }

    @Test
    fun testIterativeFor() {
        val forResults: List<Boolean> =
            lines!!.map { Exercise1.iterativeFor(it, stringPredicate, integerPredicate, stringIntoInteger) }
        assertEquals(correctResults, forResults, "Iterative for should return the correct results!!")
    }

    @Test
    fun testRecursiveFinal() {
        val recursiveResults: List<Boolean> =
            lines!!.map { Exercise1.recursiveFinal(it, stringPredicate, integerPredicate, stringIntoInteger) }
        assertEquals(correctResults, recursiveResults, "Recursive final should return the correct results!!")
    }
}
