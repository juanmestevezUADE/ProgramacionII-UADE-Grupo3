package QueueModule;

public class SimpleArrayQueue<E> implements SimpleQueue<E> {
    
    private E[] array;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    @SuppressWarnings("unchecked")
    public SimpleArrayQueue() {
        this.array = (E[]) new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        int newCapacity = (size == array.length) ? array.length * 2 : array.length;
        E[] newArray = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newArray[i] = array[i];
        }
        array = newArray;
    }

    @Override
    public void enqueue(E element) {
        if (size == array.length) {
            resize();
        }
        array[size] = element;
        size++;
    }

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

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return array[0];
    }

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
