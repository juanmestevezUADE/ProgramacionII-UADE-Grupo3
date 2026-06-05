package priorityqueueModule;

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

    @Override
    public void enqueue(E element, int priority ) {
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
        priorities = new int[DEFAULT_CAPACITY];
        size = 0;
    }

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

