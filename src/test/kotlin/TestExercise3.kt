import com.google.common.io.Files
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.File
import java.nio.charset.StandardCharsets

class TestExercise3 {
    private var lines: List<Pair<Int, Int>>? = null
    private var correctResult: List<List<Pair<Int, Int>>>? = null
    private val regex: String = ","

    @BeforeEach
    fun setup() {
        val file: File = File("src/test/resources/PI1E3_DatosEntrada.txt")
        try {
            lines = Files.readLines(file, StandardCharsets.UTF_8)
                .map { line: String ->
                    val aux: List<String> = line.split(regex)
                    Pair(aux[0].toInt(), aux[1].toInt())
                }
            correctResult = lines!!.map { Exercise3.functional(it.first, it.second) }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @Test
    fun testIterativeWhile() {
        val result: List<List<Pair<Int, Int>>> = lines!!.map { Exercise3.iterativeWhile(it.first, it.second) }
        assertEquals(correctResult, result, "Iterative while is not working")
    }

    @Test
    fun testRecursiveFinal() {
        val result: List<List<Pair<Int, Int>>> = lines!!.map { Exercise3.recursiveFinal(it.first, it.second) }
        assertEquals(correctResult, result, "Recursive final is not working")
    }

    @Test
    fun testRecursiveNoFinal() {
        val result: List<List<Pair<Int, Int>>> = lines!!.map { Exercise3.recursiveNoFinal(it.first, it.second) }
        assertEquals(correctResult, result, "Recursive no final is not working")
    }
}
