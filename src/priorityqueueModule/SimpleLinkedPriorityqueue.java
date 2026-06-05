package priorityqueueModule;

public class SimpleLinkedPriorityqueue<E> implements SimplePriorityqueue<E> {
    
        // Clase interna para los nodos
    private static class Node<E> {
        E data;
        Node<E> next;
        int priority;

        Node(E data, int priority) {
            this.data = data;
            this.priority = priority;
            this.next = null;
        }
    }

    private Node<E> head;
    private int size;

    public SimpleLinkedPriorityqueue() {
        this.head = null;
        this.size = 0;
    }

    @Override
    public void enqueue(E element, int priority) {
        Node<E> newNode = new Node<>(element, priority);
        if (isEmpty()) {
            head = newNode;
        } else {
            Node<E> current = head;
            Node<E> previous = null;

            // Encontrar la posición correcta para insertar el nuevo nodo
            while (current != null && current.priority >= priority) {
                previous = current;
                current = current.next;
            }

            if (previous == null) {
                // Insertar al principio
                newNode.next = head;
                head = newNode;
            } else {
                // Insertar en medio o al final
                previous.next = newNode;
                newNode.next = current;
            }
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
            head = null; // Si la cola quedó vacía, limpiamos el head
        }
        return element;
    }

    public int getHighestPriority() {
        if (isEmpty()) {
            throw new IllegalStateException("Priority queue is empty");
        }
        return head.priority;
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
