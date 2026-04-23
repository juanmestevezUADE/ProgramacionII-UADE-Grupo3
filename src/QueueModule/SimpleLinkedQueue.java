package QueueModule;

public class SimpleLinkedQueue<E> implements SimpleQueue<E> {

    // Clase interna para los nodos
    private static class Node<E> {
        E data;
        Node<E> next;

        Node(E data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node<E> head;
    private Node<E> tail;
    private int size;

    public SimpleLinkedQueue() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    @Override
    public void enqueue(E element) {
        Node<E> newNode = new Node<>(element);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            return null;
        }
        E element = head.data;
        head = head.next;
        size--;
        
        if (isEmpty()) {
            tail = null; // Si la cola quedó vacía, limpiamos el tail
        }
        return element;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return head.data;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}