import hashtable.ListTable
import hashtable.RandomTable
import java.io.File
import kotlin.math.absoluteValue
import kotlin.math.pow
import kotlin.time.measureTimedValue

private const val SIZE = 123
private const val FILE_PATH = "C:\\Users\\Wladislaw\\Desktop\\input.txt"

fun main() {
    //Получаем данный из файла
    val wordsInFile = readLinesFromFile(FILE_PATH)

    // Создаём пустые таблицы
    val randomTable = RandomTable(SIZE)
    val listTable = ListTable(SIZE)

    // Заполняем таблицы и тестируем время для вставка
    val timeInsertList = measureTimedValue {
        wordsInFile.forEach { word ->
            randomTable.insert(word)
        }
    }
    println()
    println("Время выполнения вставки для \"Рандомный список\": ${timeInsertList.duration.inWholeMicroseconds} микросекунд")
    val timeInsertRehash = measureTimedValue {
        wordsInFile.forEach { word ->
            listTable.insert(word)
        }
    }
    println("Время выполнения вставки для \"Простой список\": ${timeInsertRehash.duration.inWholeMicroseconds} микросекунд")
    println()

    // Тестируем время необходимое для поиска
    randomTable.test()
    listTable.test()

    // Поиск
    while (true) {
        print("Введите строку для поиска: ")
        val input: String = readln()
        val listSearchRepeats = randomTable.search(input)
        val rehashRepeats = listTable.search(input)
        println("Количество попыток для \"Рандомный список\": ${listSearchRepeats ?: "Не найдено совпадений"} ")
        println("Количество попыток для \"Простой список\": ${rehashRepeats ?: "Не найдено совпадений"}")
        println()
    }
}

fun String.hahaCode(): Int {
    var hash = 0

    for (i in indices)
        hash += get(i).code * 31.toDouble().pow((length - i - 1).toDouble()).toInt()

    return hash.absoluteValue
}

fun readLinesFromFile(filePath: String): List<String> {
    val lines = mutableListOf<String>()
    try {
        File(filePath).forEachLine { lines.add(it) }
    } catch (e: Exception) {
        println("Ошибка при чтении файла: ${e.message}")
    }
    return lines
}