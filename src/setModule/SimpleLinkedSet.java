package setModule;

public class SimpleLinkedSet<E> implements SimpleSet<E> {

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

    private Node head; // primer nodo
    private Node tail; // último nodo
    private int size;

    public SimpleLinkedSet() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public boolean add(E element) {
        try {
            if (contains(element)) {
                return false; // No se permiten duplicados
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
            // Si contains no está implementado, asumimos que no hay duplicados
        }
        return false;
    }

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
            // Si contains no está implementado, no podemos encontrar el elemento
        }
        return false;
    }

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
            // Si contains no está implementado, asumimos que el elemento no existe
        }
        return false;
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        return size == 0;
    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        return size;
    }

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

    @Override
    public SimpleSet<E> unionWith(SimpleSet<E> other) {
        SimpleSet<E> unionSet = new SimpleLinkedSet<>();
        Node current = head;
        while (current != null) {
            unionSet.add(current.element);
            current = current.next;
        }
        for (E element : other.toArray()) {
            unionSet.add(element);
        }
        return unionSet;

    }

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

    private void removeAndReconnect(Node toRemove) {

        // Caso 1: es el unico elemento
        if (toRemove == head && toRemove == tail) {
            head = null;
            tail = null;
        }
        // caso 2: es la cabeza
        else if (toRemove == head) {
            toRemove.next.prev = null;
            head = toRemove.next;

        }
        // caso 3: es la cola
        else if (toRemove == tail) {
            tail = toRemove.prev;
            tail.next = null;
        }
        // caso 4: esta en el medio
        else {
            toRemove.next.prev = toRemove.prev;
            toRemove.prev.next = toRemove.next;
        }
        toRemove.next = null;
        toRemove.prev = null;
        size--;
    }

    public E get(int index){
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
