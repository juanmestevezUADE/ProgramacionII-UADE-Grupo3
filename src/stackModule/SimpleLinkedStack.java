package stackModule;

// Implementación de pila usando una lista doblemente enlazada.
// El tope de la pila es siempre el último nodo (tail).
// Push agrega al final, pop elimina del final.
public class SimpleLinkedStack<E> implements SimpleStack<E> {

    @SuppressWarnings("unused")
    private class Node {
        E element;
        Node next;
        Node prev;

        public Node(E element) {
            this.element = element;
            this.next = null;
            this.prev = null;
        }
    }

    private int size;
    private Node head; // primer nodo (fondo de la pila)
    private Node tail; // último nodo (tope de la pila)

    public SimpleLinkedStack() {
        this.head = null;
        this.tail = null;
    }

    // Agrega un nodo al final (tope de la pila)
    @Override
    public void push(E item) {
        Node newNode = new Node(item);
        if (size == 0) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    // Elimina y devuelve el nodo del tope (tail)
    @Override
    public E pop() {
        if (size == 0) {
            throw new RuntimeException("Stack is empty");
        }
        E item = tail.element;
        if (head == tail) {
            // Único elemento: la pila queda vacía
            head = null;
            tail = null;
        } else {
            tail = tail.prev;
            tail.next = null;
        }
        size--;
        return item;
    }

    // Devuelve el elemento del tope sin eliminarlo
    @Override
    public E peek() {
        if (size == 0) {
            throw new RuntimeException("Stack is empty");
        }
        return tail.element;
    }

    @Override
    public int size() {
        return size;
    }

    // Desconecta todos los nodos
    @Override
    public void clear() {
        size = 0;
        head = null;
        tail = null;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
