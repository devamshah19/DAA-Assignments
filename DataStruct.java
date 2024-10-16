public class DataStruct {
    public static class Stack {
        private Object[] items;
        private int top;
        private int capacity;

        public Stack(int capacity) {
            this.capacity = capacity;
            items = new Object[capacity];
            top = -1;
        }

        public boolean isEmpty() {
            return top == -1;
        }

        public void push(Object item) {
            if (top + 1 < capacity) {
                items[++top] = item;
            } else {
                throw new StackOverflowError("Stack is full");
            }
        }

        public Object pop() {
            if (!isEmpty()) {
                return items[top--];
            }
            throw new IndexOutOfBoundsException("Pop from empty stack");
        }

        public Object peek() {
            if (!isEmpty()) {
                return items[top];
            }
            throw new IndexOutOfBoundsException("Peek from empty stack");
        }

        public int size() {
            return top + 1;
        }
    }

    public static class Queue {
        private Object[] items;
        private int front, rear, capacity;

        public Queue(int capacity) {
            this.capacity = capacity;
            items = new Object[capacity];
            front = 0;
            rear = -1;
        }

        public boolean isEmpty() {
            return front > rear;
        }

        public void enqueue(Object item) {
            if (rear + 1 < capacity) {
                items[++rear] = item;
            } else {
                throw new IllegalStateException("Queue is full");
            }
        }

        public Object dequeue() {
            if (!isEmpty()) {
                return items[front++];
            }
            throw new IndexOutOfBoundsException("Dequeue from empty queue");
        }

        public Object front() {
            if (!isEmpty()) {
                return items[front];
            }
            throw new IndexOutOfBoundsException("Front from empty queue");
        }

        public int size() {
            return rear - front + 1;
        }
    }

    public static class SinglyLinkedList {
        private Node front;

        private class Node {
            Object data;
            Node next;

            Node(Object data) {
                this.data = data;
                this.next = null;
            }
        }

        public void insert(Object data) {
            Node newNode = new Node(data);
            newNode.next = front;
            front = newNode;
        }

        public void delete(Object key) {
            Node current = front;
            Node prev = null;

            if (current != null && current.data.equals(key)) {
                front = current.next;
                return;
            }

            while (current != null && !current.data.equals(key)) {
                prev = current;
                current = current.next;
            }

            if (current == null) return;

            prev.next = current.next;
        }

        public void display() {
            Node current = front;
            while (current != null) {
                System.out.print(current.data + " => ");
                current = current.next;
            }
            System.out.println("null");
        }
    }

    public static void main(String[] args) {
        System.out.println("Stack:");
        Stack stack = new Stack(5);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println("Top element: " + stack.peek());
        System.out.println("Popped element: " + stack.pop());
        System.out.println("Stack Status: " + (stack.isEmpty() ? "Empty" : "Not Empty"));

        System.out.println("\nQueue:");
        Queue queue = new Queue(5);
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        System.out.println("Front element: " + queue.front());
        System.out.println("Dequeued element: " + queue.dequeue());
        System.out.println("Queue Status: " + (queue.isEmpty() ? "Empty" : "Not Empty"));

        System.out.println("\nSingly Linked List:");
        SinglyLinkedList list = new SinglyLinkedList();
        list.insert(10);
        list.insert(20);
        list.insert(30);
        list.display();
        list.delete(20);
        list.display();
    }
}
