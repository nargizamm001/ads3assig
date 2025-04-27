public class MyHashTable<K, V> {
    private class HashNode<K, V> {
        public HashNode next;
        private K key;
        private V value;
        private HashNode<K, V> next;

        public HashNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "{" + key + " " + value + "}";
        }
    }

    private HashNode<K, V>[] chainArray;
    private int M = 11;
    private int size;

    public MyHashTable() {
        chainArray = new HashNode[M];
        size = 0;
    }

    public MyHashTable(int m) {
        M = m;
        chainArray = new HashNode[M];
        size = 0;
    }

    private int hash(K key) {
        return Math.abs(key.hashCode()) % M;
    }

    public void put(K key, V value) {
        int bucketIndex = hash(key);
        HashNode<K, V> head = chainArray[bucketIndex];

        while (head != null) {
            if (head.key.equals(key)) {
                head.value = value;
                return;
            }
            head = head.next;
        }

        size++;
        head = chainArray[bucketIndex];
        HashNode<K, V> newNode = new HashNode<>(key, value);
        newNode.next = head;
        chainArray[bucketIndex] = newNode;
    }

    public V get(K key) {
        int bucketIndex = hash(key);
        HashNode<K, V> head = chainArray[bucketIndex];

        while (head != null) {
            if (head.key.equals(key)) {
                return head.value;
            }
            head = head.next;
        }
        return null;
    }

    public V remove(K key) {
        int bucketIndex = hash(key);
        HashNode<K, V> head = chainArray[bucketIndex];
        HashNode<K, V> prev = null;

        while (head != null) {
            if (head.key.equals(key)) {
                break;
            }
            prev = head;
            head = head.next;
        }

        if (head == null) {
            return null;
        }

        size--;

        if (prev != null) {
            prev.next = head.next;
        } else {
            chainArray[bucketIndex] = head.next;
        }

        return head.value;
    }

    public boolean contains(V value) {
        for (int i = 0; i < M; i++) {
            HashNode<K, V> head = chainArray[i];
            while (head != null) {
                if (head.value.equals(value)) {
                    return true;
                }
                head = head.next;
            }
        }
        return false;
    }

    public K getKey(V value) {
        for (int i = 0; i < M; i++) {
            HashNode<K, V> head = chainArray[i];
            while (head != null) {
                if (head.value.equals(value)) {
                    return head.key;
                }
                head = head.next;
            }
        }
        return null;
    }
}
