import com.google.common.io.Files
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.File
import java.io.IOException
import java.nio.charset.StandardCharsets

class TestExercise2 {

    private var lines: List<List<String>>? = null
    private var correctResult: Map<Int, List<String>>? = null
    private val sep: String = ","
    private val test: String = "1"

    @BeforeEach
    fun setup() {
        val file: File = File("src/test/resources/PI1E2_DatosEntrada$test.txt")
        try {
            lines = Files.readLines(file, StandardCharsets.UTF_8).map { it.split(sep) }
            correctResult = Exercise2.functional(lines!!)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    @Test
    fun testIterativeWhile() {
        val result = Exercise2.iterativeWhile(lines!!)
        assertEquals(correctResult, result, "The result is not correct!!!")
    }

    @Test
    fun testIterativeFor() {
        val result = Exercise2.iterativeFor(lines!!)
        assertEquals(correctResult, result, "The result is not correct!!!")
    }

    @Test
    fun testRecursiveFinal() {
        val result = Exercise2.recursiveFinal(lines!!)
        assertEquals(correctResult, result, "The result is not correct!!!")
    }
}
