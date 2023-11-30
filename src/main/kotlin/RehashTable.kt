package hashtable

import hahaCode
import kotlin.math.absoluteValue
import kotlin.time.measureTimedValue

class RehashTable(size: Int) {
    private val keys = arrayOfNulls<String?>(size)

    fun insert(key: String) {
        var attempt = 0
        var hash = hash(key)

        while (keys[hash] != null) {
            // Коллизия, используем рехеширование
            attempt++
            hash = rehash(hash, attempt)
        }

        keys[hash] = key
    }

    fun search(key: String): Int? {
        var attempt = 0
        var hash = hash(key)

        while (keys[hash] != null) {
            if (keys[hash] == key)
                return attempt + 1 // Возвращаем количество попыток

            attempt++
            hash = rehash(hash, attempt)
            if (hash < 0) return null
        }

        return null
    }

    fun test() {
        val result = measureTimedValue { search("RadiantEphemeralSerenade") }

        println("Время выполнения поиска для \"Рехэширования\": ${result.duration.inWholeMicroseconds} микросекунд")
        println(); println()
    }

    private fun hash(key: String): Int {
        return key.hahaCode() % keys.size
    }

    private fun rehash(initialHash: Int, attempt: Int): Int {
        return (initialHash + attempt * attempt) % keys.size
    }
}