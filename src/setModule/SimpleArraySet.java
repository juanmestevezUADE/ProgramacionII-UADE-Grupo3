package setModule;

public class SimpleArraySet<E> implements SimpleSet<E> {

    private E[] elements;
    private static final int DEFAULT_CAPACITY = 4;
    private int size;

    @SuppressWarnings("unchecked")
    public SimpleArraySet() {
        this.elements = (E[]) new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    @Override
    public boolean add(E element) {
        try {
            if (contains(element)) {
                return false; // No se permiten duplicados
            } else {
                resize();
                elements[size] = element;
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
            for (int i = 0; i < size; i++) {
                if (elements[i].equals(object)) {
                    elements[i] = elements[size - 1]; // Reemplaza el elemento a eliminar con el último elemento
                    elements[size - 1] = null; // Elimina la referencia al último elemento
                    size--;
                    return true;
                }
            }
            return false; // Elemento no encontrado
        } catch (Exception e) {
            System.out.println("Element could not be removed");
            // Si contains no está implementado, no podemos encontrar el elemento
        }
        return false;
    }

    @Override
    public boolean contains(Object object) {
        try {
            for (int i = 0; i < size; i++) {
                if (elements[i].equals(object)) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            System.out.println("Element could not be checked for existence");
            // Si contains no está implementado, asumimos que el elemento no existe
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void clear() {
        elements = (E[]) new Object[DEFAULT_CAPACITY];
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

    @Override
    @SuppressWarnings("unchecked")
    public E[] toArray() {
    E[] result = (E[]) new Object[size];
    for (int i = 0; i < size; i++) {
        result[i] = elements[i];
    }
    return result;
}

    @Override
    public SimpleSet<E> unionWith(SimpleSet<E> other) {
        SimpleSet<E> unionSet = new SimpleArraySet<>();
        for (int i = 0; i < size; i++) {
            unionSet.add(elements[i]);
        }
        for (E element : other.toArray()) {
            unionSet.add(element);
        }
        return unionSet;
    }

    @Override
    public SimpleSet<E> intersectionWith(SimpleSet<E> other) {

        SimpleArraySet<E> intersectionSet = new SimpleArraySet<>();
        for (int i = 0; i < size; i++) {
            if (other.contains(elements[i])) {
                intersectionSet.add(elements[i]);
            }
        }
        return intersectionSet;
    }

    @Override
    public SimpleSet<E> differenceWith(SimpleSet<E> other) {

        SimpleArraySet<E> differenceSet = new SimpleArraySet<>();
        for (int i = 0; i < size; i++) {
            if (!other.contains(elements[i])) {
                differenceSet.add(elements[i]);
            }
        }
        return differenceSet;
    }

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
