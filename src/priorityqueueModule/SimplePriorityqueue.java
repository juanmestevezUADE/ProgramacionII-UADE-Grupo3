package priorityqueueModule;

// Interfaz que define las operaciones básicas de una cola de prioridad (max-heap).
// El elemento con mayor prioridad siempre es el primero en salir.
// E es el tipo de dato de los elementos.
public interface SimplePriorityqueue<E> {

    // Inserta el elemento con la prioridad dada
    public void enqueue(E element, int priority);

    // Elimina y devuelve el elemento con mayor prioridad
    public E dequeue();

    // Devuelve el elemento con mayor prioridad sin eliminarlo
    public E peek();

    // Devuelve el valor numérico de la prioridad más alta en la cola
    public int getHighestPriority();

    // Vacía la cola
    public void clear();

    // Devuelve la cantidad de elementos en la cola
    public int size();

    // Devuelve true si la cola no tiene elementos
    public boolean isEmpty();
}
