package priorityqueueModule;

// Implementación de cola de prioridad (max-heap) usando dos arreglos paralelos:
// uno para los elementos y otro para sus prioridades.
// Los elementos se mantienen ordenados de mayor a menor prioridad al insertar,
// por lo que dequeue() siempre saca el de mayor prioridad en O(1).
public class SimpleArrayPriorityqueue<E> implements SimplePriorityqueue<E> {

    private E[] array;
    private int[] priorities;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    @SuppressWarnings("unchecked")
    public SimpleArrayPriorityqueue() {
        this.array = (E[]) new Object[DEFAULT_CAPACITY];
        this.priorities = new int[DEFAULT_CAPACITY];
        this.size = 0;
    }

    // Si los arreglos están llenos, los duplica en capacidad copiando los datos
    @SuppressWarnings("unchecked")
    private void resize() {
        if (size == array.length) {
            int newCapacity = array.length * 2;
            E[] newArray = (E[]) new Object[newCapacity];
            int[] newPriorities = new int[newCapacity];
            for (int i = 0; i < size; i++) {
                newArray[i] = array[i];
                newPriorities[i] = priorities[i];
            }
            array = newArray;
            priorities = newPriorities;
        }
    }

    // Inserta el elemento en la posición correcta para mantener el orden descendente de prioridades.
    // Desplaza los elementos de menor prioridad hacia la derecha para hacer lugar.
    @Override
    public void enqueue(E element, int priority) {
        resize();
        int insertIndex = size;
        for (int i = size; i > 0 && priorities[i - 1] < priority; i--) {
            array[i] = array[i - 1];
            priorities[i] = priorities[i - 1];
            insertIndex = i - 1;
        }
        array[insertIndex] = element;
        priorities[insertIndex] = priority;
        size++;
    }

    // Elimina y devuelve el elemento de mayor prioridad (posición 0).
    // Desplaza todos los demás una posición a la izquierda.
    @Override
    public E dequeue() {
        if (isEmpty()) {
            return null;
        }
        E element = array[0];
        array[0] = null;
        priorities[0] = 0;
        for (int i = 0; i < size - 1; i++) {
            array[i] = array[i + 1];
            priorities[i] = priorities[i + 1];
        }
        size--;
        return element;
    }

    // Devuelve el elemento de mayor prioridad sin eliminarlo
    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return array[0];
    }

    // Reinicia la cola con arreglos vacíos de capacidad por defecto
    @Override
    public void clear() {
        @SuppressWarnings("unchecked")
        E[] newArray = (E[]) new Object[DEFAULT_CAPACITY];
        array = newArray;
        priorities = new int[DEFAULT_CAPACITY];
        size = 0;
    }

    // Devuelve la prioridad más alta (la del elemento en posición 0)
    public int getHighestPriority() {
        if (isEmpty()) {
            throw new IllegalStateException("Priority queue is empty");
        }
        return priorities[0];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
