package contract

interface CacheInterface<K, V> {
    fun get(key: K): V?

    fun set(key: K, value: V)

    fun clear()
}