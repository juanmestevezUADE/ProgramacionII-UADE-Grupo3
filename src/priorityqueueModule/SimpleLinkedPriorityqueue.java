package priorityqueueModule;

// Implementación de cola de prioridad (max-heap) usando una lista simplemente enlazada ordenada.
// Los nodos se insertan en orden descendente de prioridad,
// por lo que el head siempre es el elemento de mayor prioridad.
public class SimpleLinkedPriorityqueue<E> implements SimplePriorityqueue<E> {

    // Nodo interno: guarda el elemento, su prioridad y el puntero al siguiente
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

    private Node<E> head; // nodo con mayor prioridad (primero en salir)
    private int size;

    public SimpleLinkedPriorityqueue() {
        this.head = null;
        this.size = 0;
    }

    // Inserta el elemento en la posición correcta según su prioridad (orden descendente).
    // Recorre la lista hasta encontrar un nodo con menor prioridad e inserta antes de él.
    @Override
    public void enqueue(E element, int priority) {
        Node<E> newNode = new Node<>(element, priority);
        if (isEmpty()) {
            head = newNode;
        } else {
            Node<E> current = head;
            Node<E> previous = null;

            // Buscar la posición de inserción: el nuevo nodo va antes del primero con menor prioridad
            while (current != null && current.priority >= priority) {
                previous = current;
                current = current.next;
            }

            if (previous == null) {
                // El nuevo nodo tiene mayor prioridad que todos: pasa a ser el head
                newNode.next = head;
                head = newNode;
            } else {
                // Insertar en el medio o al final
                previous.next = newNode;
                newNode.next = current;
            }
        }
        size++;
    }

    // Elimina y devuelve el elemento de mayor prioridad (head)
    @Override
    public E dequeue() {
        if (isEmpty()) {
            return null;
        }
        E element = head.data;
        head = head.next;
        size--;

        if (isEmpty()) {
            head = null;
        }
        return element;
    }

    // Devuelve la prioridad más alta (la del head)
    public int getHighestPriority() {
        if (isEmpty()) {
            throw new IllegalStateException("Priority queue is empty");
        }
        return head.priority;
    }

    // Devuelve el elemento de mayor prioridad sin eliminarlo
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
