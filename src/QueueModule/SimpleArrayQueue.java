package QueueModule;

// Implementación de cola usando un arreglo dinámico.
// El frente de la cola es siempre la posición 0.
// Cuando el arreglo se llena, se duplica su capacidad automáticamente.
public class SimpleArrayQueue<E> implements SimpleQueue<E> {

    private E[] array;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    @SuppressWarnings("unchecked")
    public SimpleArrayQueue() {
        this.array = (E[]) new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    // Si el arreglo está lleno, crea uno nuevo con el doble de capacidad y copia los datos
    @SuppressWarnings("unchecked")
    private void resize() {
        int newCapacity = array.length * 2;
        E[] newArray = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newArray[i] = array[i];
        }
        array = newArray;
    }

    // Agrega el elemento al final de la cola
    @Override
    public void enqueue(E element) {
        if (size == array.length) {
            resize();
        }
        array[size] = element;
        size++;
    }

    // Elimina y devuelve el primer elemento (posición 0).
    // Desplaza todos los demás una posición a la izquierda.
    @Override
    public E dequeue() {
        if (isEmpty()) {
            return null;
        }
        E element = array[0];
        array[0] = null;
        for (int i = 0; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        size--;
        return element;
    }

    // Devuelve el primer elemento sin eliminarlo
    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return array[0];
    }

    // Reinicia la cola con un arreglo vacío de capacidad por defecto
    @Override
    public void clear() {
        @SuppressWarnings("unchecked")
        E[] newArray = (E[]) new Object[DEFAULT_CAPACITY];
        array = newArray;
        size = 0;
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
