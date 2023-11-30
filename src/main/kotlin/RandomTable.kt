package hashtable

import hahaCode
import kotlin.random.Random
import kotlin.time.measureTimedValue

class RandomTable(size: Int) {
    private val table = arrayOfNulls<String?>(size)

    fun insert(key: String) {
        var attempt = 0
        var hash = hash(key)

        while (table[hash] != null) {
            // Коллизия, используем рехеширование
            attempt++
            hash = random(hash)
        }

        table[hash] = key
    }

    fun search(key: String): Int? {
        var attempt = 0
        var hash = hash(key)

        while (table[hash] != null) {
            if (table[hash] == key)
                return attempt + 1 // Возвращаем количество попыток

            attempt++
            hash = random(hash)
            if (hash < 0) return null
        }

        return null
    }

    fun test() {
        val result = measureTimedValue { search("RadiantEphemeralSerenade") }

        println("Время выполнения поиска для \"Рехэширования рандомный\": ${result.duration.inWholeMicroseconds} микросекунд")
        println(); println()
    }

    private fun hash(key: String): Int {
        return key.hahaCode() % table.size
    }

    private fun random(hash: Int): Int {
        val random = Random(hash)
        return random.nextInt(0, table.size)
    }
}