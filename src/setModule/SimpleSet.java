package setModule;

public interface SimpleSet<E> {
    public boolean add(E element);
    public boolean remove(Object object);
    public boolean contains(Object object);
    public void clear();
    public boolean isEmpty();
    public int size();
    public E[] toArray();
    public SimpleSet<E> unionWith(SimpleSet<E> other);
    public SimpleSet<E> intersectionWith(SimpleSet<E> other);
    public SimpleSet<E> differenceWith(SimpleSet<E> other);   
}
