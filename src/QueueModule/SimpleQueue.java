package QueueModule;

// Interfaz que define las operaciones básicas de una cola (FIFO: First In, First Out).
// E es el tipo de dato de los elementos de la cola.
public interface SimpleQueue<E> {

    // Inserta el elemento al final de la cola
    public void enqueue(E element);

    // Elimina y devuelve el elemento al frente de la cola
    public E dequeue();

    // Devuelve el elemento al frente sin eliminarlo
    public E peek();

    // Vacía la cola
    public void clear();

    // Devuelve la cantidad de elementos en la cola
    public int size();

    // Devuelve true si la cola no tiene elementos
    public boolean isEmpty();
}
