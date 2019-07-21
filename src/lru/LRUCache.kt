package lru

import contract.CacheInterface
import java.util.*

class LRUCache<K, V>(private var capacity: Int) : CacheInterface<K, V> {
    private var cache = HashMap<K, Node<K, V>>()
    private var head: Node<K, V>? = null
    private var end: Node<K, V>? = null

    override fun clear() {
        cache.clear()
        head = null
        end = null
    }

    override fun get(key: K): V? {
        if (cache.containsKey(key)) {
            val n = cache[key]!!
            deleteNode(n)
            pushToHead(n)
            return n.value
        }

        return null
    }

    override fun set(key: K, value: V) {
        if (cache.containsKey(key)) {
            // update the old value
            val old = cache[key]!!
            old.value = value
            deleteNode(old)
            pushToHead(old)
            return
        }

        val newNode = Node(key, value)
        if (cache.size >= capacity) {
            cache.remove(end!!.key)
            // remove last node
            deleteNode(end!!)
            pushToHead(newNode)

        } else {
            pushToHead(newNode)
        }

        cache[key] = newNode
    }


    private fun deleteNode(node: Node<K, V>) {
        if (node.prev == null) {
            head = node.next
        } else {
            node.prev!!.next = node.next
        }

        if (node.next == null) {
            end = node.prev
        } else {
            node.next!!.prev = node.prev
        }
    }

    private fun pushToHead(node: Node<K, V>) {
        node.next = head
        node.prev = null

        if (head != null) {
            head!!.prev = node
        }

        head = node

        if (end == null) {
            end = head
        }
    }
}