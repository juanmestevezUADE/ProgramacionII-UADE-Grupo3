package setModule;

// Interfaz que define las operaciones básicas de un conjunto (sin elementos duplicados).
// E es el tipo de dato de los elementos.
public interface SimpleSet<E> {

    // Agrega el elemento si no existe ya. Devuelve true si se agregó.
    public boolean add(E element);

    // Elimina el elemento del conjunto. Devuelve true si existía.
    public boolean remove(Object object);

    // Devuelve true si el elemento existe en el conjunto
    public boolean contains(Object object);

    // Vacía el conjunto
    public void clear();

    // Devuelve true si el conjunto no tiene elementos
    public boolean isEmpty();

    // Devuelve la cantidad de elementos en el conjunto
    public int size();

    // Devuelve un arreglo con todos los elementos del conjunto
    public E[] toArray();

    // Devuelve un nuevo conjunto con todos los elementos de ambos conjuntos (A ∪ B)
    public SimpleSet<E> unionWith(SimpleSet<E> other);

    // Devuelve un nuevo conjunto con los elementos que están en ambos conjuntos (A ∩ B)
    public SimpleSet<E> intersectionWith(SimpleSet<E> other);

    // Devuelve un nuevo conjunto con los elementos de este que NO están en 'other' (A - B)
    public SimpleSet<E> differenceWith(SimpleSet<E> other);
}
