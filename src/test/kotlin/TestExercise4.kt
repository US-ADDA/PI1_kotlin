import com.google.common.io.Files
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.File
import java.nio.charset.StandardCharsets
import kotlin.math.absoluteValue

class TestExercise4 {

    private var lines: List<Pair<Double, Double>>? = null
    private val regex: String = ","

    @BeforeEach
    fun setup() {
        val file: File = File("src/test/resources/PI1E4_DatosEntrada.txt")
        try {
            lines = Files.readLines(file, StandardCharsets.UTF_8)
                .map { line: String ->
                    val aux: List<String> = line.split(regex)
                    Pair(aux[0].toDouble(), aux[1].toDouble())
                }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @Test
    fun testFunctional() {
        val result: List<Double> = lines!!.map { Exercise4.functional(it.first, it.second) }
        for (i in result.indices) {
            assertTrue((result[i] - Math.cbrt(lines!![i].first)).absoluteValue < lines!![i].second)
        }
    }

    @Test
    fun testIterativeWhile() {
        val result: List<Double> = lines!!.map { Exercise4.iterativeWhile(it.first, it.second) }
        for (i in result.indices) {
            assertTrue((result[i] - Math.cbrt(lines!![i].first)).absoluteValue < lines!![i].second)
        }
    }

    @Test
    fun testRecursiveFinal() {
        val result: List<Double> = lines!!.map { Exercise4.recursiveFinal(it.first, it.second) }
        for (i in result.indices) {
            assertTrue((result[i] - Math.cbrt(lines!![i].first)).absoluteValue < lines!![i].second)
        }
    }
}
