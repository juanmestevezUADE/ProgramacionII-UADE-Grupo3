package setModule;

// Implementación de conjunto usando una lista doblemente enlazada.
// No permite duplicados: antes de agregar siempre verifica si el elemento ya existe.
public class SimpleLinkedSet<E> implements SimpleSet<E> {

    // Nodo interno: guarda el elemento y punteros al anterior y siguiente
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

    private Node head; // primer nodo del conjunto
    private Node tail; // último nodo del conjunto
    private int size;

    public SimpleLinkedSet() {
        head = null;
        tail = null;
        size = 0;
    }

    // Agrega el elemento al final solo si no existe ya en el conjunto
    @Override
    public boolean add(E element) {
        try {
            if (contains(element)) {
                return false; // no se permiten duplicados
            } else {
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
        } catch (Exception e) {
            System.out.println("Element could not be added");
        }
        return false;
    }

    // Busca el objeto y elimina el nodo que lo contiene
    @Override
    public boolean remove(Object object) {
        try {
            Node current = head;
            boolean found = false;
            while (current != null && !found) {
                if (current.element.equals(object)) {
                    removeAndReconnect(current);
                    found = true;
                } else {
                    current = current.next;
                }
            }
            return found;
        } catch (Exception e) {
            System.out.println("Element could not be removed");
        }
        return false;
    }

    // Recorre la lista buscando el objeto dado
    @Override
    public boolean contains(Object object) {
        try {
            Node current = head;
            while (current != null) {
                if (current.element.equals(object)) {
                    return true;
                }
                current = current.next;
            }
            return false;
        } catch (Exception e) {
            System.out.println("Element could not be checked for existence");
        }
        return false;
    }

    // Desconecta todos los nodos
    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    // Copia los elementos a un arreglo recorriendo la lista de head a tail
    @SuppressWarnings("unchecked")
    @Override
    public E[] toArray() {
        E[] array = (E[]) new Object[size];
        Node current = head;
        int index = 0;
        while (current != null) {
            array[index] = current.element;
            current = current.next;
            index++;
        }
        return array;
    }

    // Devuelve un nuevo conjunto con todos los elementos de ambos (A ∪ B)
    @Override
    public SimpleSet<E> unionWith(SimpleSet<E> other) {
        SimpleSet<E> unionSet = new SimpleLinkedSet<>();
        Node current = head;
        while (current != null) {
            unionSet.add(current.element);
            current = current.next;
        }
        for (E element : other.toArray()) {
            unionSet.add(element); // add ignora duplicados automáticamente
        }
        return unionSet;
    }

    // Devuelve un nuevo conjunto con los elementos que están en ambos (A ∩ B)
    @Override
    public SimpleSet<E> intersectionWith(SimpleSet<E> other) {
        SimpleSet<E> intersectionSet = new SimpleLinkedSet<>();
        Node current = head;
        while (current != null) {
            if (other.contains(current.element)) {
                intersectionSet.add(current.element);
            }
            current = current.next;
        }
        return intersectionSet;
    }

    // Devuelve un nuevo conjunto con los elementos de este que NO están en 'other' (A - B)
    @Override
    public SimpleSet<E> differenceWith(SimpleSet<E> other) {
        SimpleSet<E> differenceSet = new SimpleLinkedSet<>();
        Node current = head;
        while (current != null) {
            if (!other.contains(current.element))
                differenceSet.add(current.element);
            current = current.next;
        }
        return differenceSet;
    }

    // Elimina un nodo y reconecta los adyacentes. Maneja 4 casos: único, cabeza, cola, medio.
    private void removeAndReconnect(Node toRemove) {
        if (toRemove == head && toRemove == tail) {
            // Caso 1: único elemento
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
            // Caso 4: nodo en el medio
            toRemove.next.prev = toRemove.prev;
            toRemove.prev.next = toRemove.next;
        }
        toRemove.next = null;
        toRemove.prev = null;
        size--;
    }

    // Devuelve el elemento en la posición indicada (recorre desde head)
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.element;
    }
}
