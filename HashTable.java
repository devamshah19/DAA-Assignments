class Node {
    int key;
    int value;
    Node prev;
    Node next;

    Node(int key, int value) {
        this.key = key;
        this.value = value;
        this.prev = null;
        this.next = null;
    }
}

class DoublyLinkedList {
    Node head;
    Node tail;

    public DoublyLinkedList() {
        head = null;
        tail = null;
    }

    public void append(int key, int value) {
        Node newNode = new Node(key, value);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
    }

    public Integer remove(int key) {
        Node current = head;
        while (current != null) {
            if (current.key == key) {
                if (current.prev != null) current.prev.next = current.next;
                if (current.next != null) current.next.prev = current.prev;
                if (current == head) head = current.next;
                if (current == tail) tail = current.prev;
                return current.value;
            }
            current = current.next;
        }
        return null;
    }

    public Integer get(int key) {
        Node current = head;
        while (current != null) {
            if (current.key == key) {
                return current.value;
            }
            current = current.next;
        }
        return null;
    }

    public boolean isEmpty() {
        return head == null;
    }
}

class HashTable {
    private DoublyLinkedList[] table;
    private int size;
    private int capacity;

    public HashTable(int initialCapacity) {
        this.capacity = initialCapacity;
        this.size = 0;
        this.table = new DoublyLinkedList[capacity];
        for (int i = 0; i < capacity; i++) {
            table[i] = new DoublyLinkedList();
        }
    }

    private int hash(int key) {
        double A = (Math.sqrt(5) - 1) / 2;
        return (int) (capacity * ((key * A) % 1));
    }

    public void insert(int key, int value) {
        int index = hash(key);
        DoublyLinkedList list = table[index];

        if (list.get(key) == null) {
            list.append(key, value);
            size++;
            checkLoadFactor();
        } else {
            list.remove(key);
            list.append(key, value);
        }
    }

    public Integer remove(int key) {
        int index = hash(key);
        DoublyLinkedList list = table[index];
        Integer removedValue = list.remove(key);
        if (removedValue != null) {
            size--;
            checkLoadFactor();
        }
        return removedValue;
    }

    public Integer get(int key) {
        int index = hash(key);
        return table[index].get(key);
    }

    private void checkLoadFactor() {
        double loadFactor = (double) size / capacity;
        if (loadFactor > 0.75) {
            resize(capacity * 2);
        } else if (loadFactor < 0.25 && capacity > 8) {
            resize(capacity / 2);
        }
    }

    private void resize(int newCapacity) {
        DoublyLinkedList[] oldTable = table;
        table = new DoublyLinkedList[newCapacity];
        for (int i = 0; i < newCapacity; i++) {
            table[i] = new DoublyLinkedList();
        }
        capacity = newCapacity;
        size = 0;

        for (DoublyLinkedList list : oldTable) {
            Node current = list.head;
            while (current != null) {
                insert(current.key, current.value);
                current = current.next;
            }
        }
    }

    public static void main(String[] args) {
        HashTable hashTable = new HashTable(10);
        
        hashTable.insert(1, 19);
        hashTable.insert(2, 24);
        hashTable.insert(3, 37);
        
        System.out.println(hashTable.get(1));
        System.out.println(hashTable.get(2));
        hashTable.remove(2);
        System.out.println(hashTable.get(2));  
    }
}
