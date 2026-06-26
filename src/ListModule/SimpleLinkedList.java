package ListModule;

// Implementación de lista doblemente enlazada.
// Cada nodo conoce su anterior y su siguiente, lo que permite recorrerla en ambos sentidos.
public class SimpleLinkedList<E> implements SImpleList<E> {

    // Nodo interno: guarda el elemento y los punteros al anterior y siguiente
    private class Node {
        E element;
        Node prev;
        Node next;

        public Node(E element) {
            this.element = element;
            this.prev = null;
            this.next = null;
        }
    }

    private Node head; // primer nodo de la lista
    private Node tail; // último nodo de la lista
    private int size;

    public SimpleLinkedList() {
        head = null;
        tail = null;
    }

    // Agrega un nodo al final de la lista
    @Override
    public boolean add(E element) {
        Node newNode = new Node(element);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
        return true;
    }

    // Inserta un nodo en la posición indicada, desplazando el nodo que estaba ahí hacia la derecha
    @Override
    public void add(int index, E element) {
        validateIndex(index);
        Node newNode = new Node(element);
        Node current = getNodeByIndex(index);

        // Conectar el nuevo nodo entre current.prev y current
        newNode.next = current;
        newNode.prev = current.prev;

        if (current.prev != null) {
            current.prev.next = newNode; // el nodo anterior apunta al nuevo
        } else {
            head = newNode; // si no hay anterior, el nuevo es la nueva cabeza
        }
        current.prev = newNode;
        size++;
    }

    // Elimina el nodo en la posición indicada y devuelve su elemento
    @Override
    public E remove(int index) {
        Node toRemove = getNodeByIndex(index);
        removeAndReconnect(toRemove);
        return toRemove.element;
    }

    // Busca el objeto y elimina el primer nodo que lo contiene
    @Override
    public boolean remove(Object object) {
        Node current = head;
        while (current != null) {
            if (current.element.equals(object)) {
                removeAndReconnect(current);
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // Desconecta todos los nodos liberando head y tail
    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    // Recorre la lista buscando el objeto dado
    @Override
    public boolean contains(Object object) {
        Node current = head;
        while (current != null) {
            if (current.element.equals(object)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public E get(int index) {
        return getNodeByIndex(index).element;
    }

    // Reemplaza el elemento en la posición indicada y devuelve el valor anterior
    @Override
    public E set(int index, E element) {
        Node current = getNodeByIndex(index);
        E old = current.element;
        current.element = element;
        return old;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    // Lanza excepción si el índice está fuera del rango válido
    private void validateIndex(int index) {
        try {
            if (index < 0 || index >= size) {
                throw new IndexOutOfBoundsException("Index fuera de rango");
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    // Busca el nodo en la posición 'index' de forma eficiente:
    // si el índice está en la primera mitad recorre desde head, si no desde tail.
    private Node getNodeByIndex(int index) {
        validateIndex(index);

        Node current;
        if (index < size / 2) {
            // Recorrer desde el inicio
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        } else {
            // Recorrer desde el final (más eficiente para índices cercanos al final)
            current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.prev;
            }
        }
        return current;
    }

    // Elimina un nodo y reconecta los nodos adyacentes entre sí.
    // Maneja 4 casos: único elemento, cabeza, cola, nodo del medio.
    private void removeAndReconnect(Node toRemove) {
        if (toRemove == head && toRemove == tail) {
            // Caso 1: único elemento en la lista
            head = null;
            tail = null;
        } else if (toRemove == head) {
            // Caso 2: eliminar la cabeza
            toRemove.next.prev = null;
            head = toRemove.next;
        } else if (toRemove == tail) {
            // Caso 3: eliminar la cola
            tail = toRemove.prev;
            tail.next = null;
        } else {
            // Caso 4: nodo en el medio, reconectar el anterior con el siguiente
            toRemove.next.prev = toRemove.prev;
            toRemove.prev.next = toRemove.next;
        }
        toRemove.next = null;
        toRemove.prev = null;
        size--;
    }
}
