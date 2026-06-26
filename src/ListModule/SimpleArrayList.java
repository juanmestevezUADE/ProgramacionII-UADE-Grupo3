package ListModule;

// Implementación de lista usando un arreglo dinámico.
// Cuando el arreglo se llena, se duplica su capacidad automáticamente.
public class SimpleArrayList<E> implements SImpleList<E> {

    private E[] elements;
    private static final int DEFAULT_CAPACITY = 4; // capacidad inicial del arreglo
    private int size; // cantidad de elementos actuales

    @SuppressWarnings("unchecked")
    public SimpleArrayList() {
        elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    @SuppressWarnings("unchecked")
    public SimpleArrayList(int capacity) {
        elements = (E[]) new Object[capacity];
    }

    // Agrega el elemento al final de la lista
    @Override
    public boolean add(E element) {
        resize(); // agrandar el arreglo si está lleno
        elements[size] = element;
        size++;
        return true;
    }

    // Inserta el elemento en la posición 'index', desplazando los elementos siguientes
    @Override
    public void add(int index, E element) {
        validateIndex(index);
        resize();

        // Mover cada elemento una posición a la derecha para hacer lugar
        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
        }
        elements[index] = element;
        size++;
    }

    // Elimina y devuelve el elemento en la posición 'index'
    // Los elementos siguientes se desplazan una posición a la izquierda
    @Override
    public E remove(int index) {
        validateIndex(index);

        E removed = elements[index];
        elements[index] = null;

        // Desplazar elementos para tapar el hueco
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        elements[size - 1] = null; // limpiar la última posición
        size--;
        return removed;
    }

    // Busca el objeto en la lista y elimina su primera ocurrencia
    @Override
    public boolean remove(Object object) {
        boolean found = false;
        int index = 0;

        // Buscar el índice del elemento a eliminar
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(object)) {
                index = i;
                found = true;
            }
        }

        if (found) {
            elements[index] = null;
            for (int i = index; i < size - 1; i++) {
                elements[i] = elements[i + 1];
            }
            elements[size - 1] = null;
            size--;
        }

        return found;
    }

    // Reinicia la lista con un arreglo vacío de capacidad por defecto
    @SuppressWarnings("unchecked")
    @Override
    public void clear() {
        elements = (E[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    // Recorre la lista buscando el objeto dado
    @Override
    public boolean contains(Object object) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(object)) return true;
        }
        return false;
    }

    @Override
    public E get(int index) {
        validateIndex(index);
        return elements[index];
    }

    // Reemplaza el elemento en 'index' y devuelve el valor anterior
    @Override
    public E set(int index, E element) {
        E currentElement = get(index);
        elements[index] = element;
        return currentElement;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    // Lanza excepción si el índice está fuera del rango válido [0, size-1]
    private void validateIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index was less than 0, or equal/higher than size");
        }
    }

    // Si el arreglo está lleno, crea uno nuevo con el doble de capacidad y copia los datos
    @SuppressWarnings("unchecked")
    private void resize() {
        if (size == elements.length) {
            E[] newElements = (E[]) new Object[elements.length * 2];
            for (int i = 0; i < size; i++) {
                newElements[i] = elements[i];
            }
            elements = newElements;
        }
    }
}
