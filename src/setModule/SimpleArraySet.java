package setModule;

// Implementación de conjunto usando un arreglo dinámico.
// No permite duplicados: antes de agregar siempre verifica si el elemento ya existe.
// Al eliminar, reemplaza el elemento eliminado con el último para evitar desplazamientos.
public class SimpleArraySet<E> implements SimpleSet<E> {

    private E[] elements;
    private static final int DEFAULT_CAPACITY = 4;
    private int size;

    @SuppressWarnings("unchecked")
    public SimpleArraySet() {
        this.elements = (E[]) new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    // Agrega el elemento solo si no existe ya en el conjunto
    @Override
    public boolean add(E element) {
        try {
            if (contains(element)) {
                return false; // no se permiten duplicados
            } else {
                resize();
                elements[size] = element;
                size++;
                return true;
            }
        } catch (Exception e) {
            System.out.println("Element could not be added");
        }
        return false;
    }

    // Busca el objeto y lo elimina reemplazándolo con el último elemento.
    // Esta estrategia evita desplazar todos los elementos siguientes.
    @Override
    public boolean remove(Object object) {
        try {
            for (int i = 0; i < size; i++) {
                if (elements[i].equals(object)) {
                    elements[i] = elements[size - 1]; // llenar el hueco con el último
                    elements[size - 1] = null;
                    size--;
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            System.out.println("Element could not be removed");
        }
        return false;
    }

    // Recorre el arreglo buscando el objeto dado
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
        }
        return false;
    }

    // Reinicia el conjunto con un arreglo vacío de capacidad por defecto
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

    // Copia los elementos actuales a un nuevo arreglo del tamaño exacto
    @Override
    @SuppressWarnings("unchecked")
    public E[] toArray() {
        E[] result = (E[]) new Object[size];
        for (int i = 0; i < size; i++) {
            result[i] = elements[i];
        }
        return result;
    }

    // Devuelve un nuevo conjunto con todos los elementos de ambos conjuntos (A ∪ B).
    // Como add() ignora duplicados, simplemente agregamos todos de ambos.
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

    // Devuelve un nuevo conjunto con los elementos que están en ambos conjuntos (A ∩ B)
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

    // Devuelve un nuevo conjunto con los elementos de este que NO están en 'other' (A - B)
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
