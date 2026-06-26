package QueueModule;

// Implementación de cola usando una lista simplemente enlazada.
// El frente de la cola es head, el final es tail.
// enqueue agrega al tail, dequeue extrae del head.
public class SimpleLinkedQueue<E> implements SimpleQueue<E> {

    // Nodo interno: guarda el dato y el puntero al siguiente
    private static class Node<E> {
        E data;
        Node<E> next;

        Node(E data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node<E> head; // frente de la cola (próximo a salir)
    private Node<E> tail; // final de la cola (último en entrar)
    private int size;

    public SimpleLinkedQueue() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    // Agrega un nuevo nodo al final de la cola
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

    // Elimina y devuelve el nodo del frente (head)
    @Override
    public E dequeue() {
        if (isEmpty()) {
            return null;
        }
        E element = head.data;
        head = head.next;
        size--;

        if (isEmpty()) {
            tail = null; // si la cola quedó vacía, limpiar también el tail
        }
        return element;
    }

    // Devuelve el elemento del frente sin eliminarlo
    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return head.data;
    }

    // Desconecta todos los nodos
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
