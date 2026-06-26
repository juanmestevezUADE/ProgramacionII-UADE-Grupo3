package stackModule;

// Interfaz que define las operaciones básicas de una pila (LIFO: Last In, First Out).
// E es el tipo de dato de los elementos de la pila.
public interface SimpleStack<E> {

    // Apila el elemento en el tope de la pila
    public void push(E item);

    // Desapila y devuelve el elemento del tope. Lanza excepción si está vacía.
    public E pop();

    // Devuelve el elemento del tope sin desapilarlo. Lanza excepción si está vacía.
    public E peek();

    // Devuelve la cantidad de elementos en la pila
    public int size();

    // Vacía la pila
    public void clear();

    // Devuelve true si la pila no tiene elementos
    public boolean isEmpty();
}
