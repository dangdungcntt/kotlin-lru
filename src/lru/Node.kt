package lru

class Node<K, V>(var key: K, var value: V) {
    var prev: Node<K, V>? = null
    var next: Node<K, V>? = null
}