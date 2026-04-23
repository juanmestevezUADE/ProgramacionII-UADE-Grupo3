package QueueModule;

public class SimpleArrayQueue<E> implements SimpleQueue<E> {
    
    private E[] array;
    private int front; // Índice del primer elemento
    private int rear;  // Índice donde se insertará el próximo elemento
    private int size;  // Cantidad actual de elementos
    private static final int DEFAULT_CAPACITY = 10;

    @SuppressWarnings("unchecked")
    public SimpleArrayQueue() {
        this.array = (E[]) new Object[DEFAULT_CAPACITY];
        this.front = 0;
        this.rear = 0;
        this.size = 0;
    }

    // Método auxiliar para agrandar el arreglo si se llena
    @SuppressWarnings("unchecked")
    private void resize() {
        E[] newArray = (E[]) new Object[array.length * 2];
        for (int i = 0; i < size; i++) {
            newArray[i] = array[(front + i) % array.length];
        }
        array = newArray;
        front = 0;
        rear = size;
    }

    @Override
    public void enqueue(E element) {
        if (size == array.length) {
            resize();
        }
        array[rear] = element;
        rear = (rear + 1) % array.length;
        size++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            return null; // O podrías lanzar una excepción como IllegalStateException
        }
        E element = array[front];
        array[front] = null; // Ayuda al Garbage Collector
        front = (front + 1) % array.length;
        size--;
        return element;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return array[front];
    }

    @Override
    public void clear() {
        @SuppressWarnings("unchecked")
        E[] newArray = (E[]) new Object[DEFAULT_CAPACITY];
        array = newArray;
        front = 0;
        rear = 0;
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