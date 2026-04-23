package stackModule;

public interface SimpleStack<E> {
    public void push(E item);
    public E pop();
    public E peek();
    public int size();
    public void clear();
    public boolean isEmpty();
}
