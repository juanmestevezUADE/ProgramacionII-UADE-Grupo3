package ListModule;

// Interfaz que define las operaciones básicas de una lista genérica.
// E es el tipo de dato de los elementos de la lista.
public interface SImpleList<E> {

    // Agrega el elemento al final de la lista. Devuelve true si tuvo éxito.
    public boolean add(E element);

    // Inserta el elemento en la posición indicada, desplazando los demás hacia la derecha
    public void add(int index, E element);

    // Elimina el elemento en la posición indicada y lo devuelve
    public E remove(int index);

    // Elimina la primera ocurrencia del objeto dado. Devuelve true si lo encontró.
    public boolean remove(Object object);

    // Vacía la lista
    public void clear();

    // Devuelve true si la lista contiene el objeto dado
    public boolean contains(Object object);

    // Devuelve el elemento en la posición indicada
    public E get(int index);

    // Reemplaza el elemento en la posición indicada y devuelve el valor anterior
    public E set(int index, E element);

    // Devuelve la cantidad de elementos en la lista
    public int size();

    // Devuelve true si la lista no tiene elementos
    public boolean isEmpty();
}
