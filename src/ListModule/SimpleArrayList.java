package ListModule;

public class SimpleArrayList<E> implements SImpleList<E> {

	private E[] elements;
	private static final int DEFAULT_CAPACITY = 4;
	private int size;
	
	@SuppressWarnings("unchecked")
	public SimpleArrayList() {
		elements = (E[]) new Object[DEFAULT_CAPACITY];
	}
	
	@SuppressWarnings("unchecked")
	public SimpleArrayList(int capacity) {
		elements = (E[]) new Object[capacity];
	}

	@Override
	public boolean add(E element) {
		// TODO Auto-generated method stub
		resize();
		elements[size] = element;
		size++;
		return true;
	}

	@Override
	public void add(int index, E element) {
		// TODO Auto-generated method stub
		
		/*valido el index*/
		validateIndex(index);
		
		resize();
		
		for(int i = size; i > index; i--) {
		    elements[i] = elements[i - 1];
		}
		elements[index] = element;
		size++;
	}

	@Override
	public E remove(int index) {
		// TODO Auto-generated method stub
		
		/*valido el index ingresado*/
		validateIndex(index);
		
		E removed = elements[index];
		elements[index] = null;
		for(int i = index; i < size - 1; i++) {
			elements[i] = elements[i + 1];
		}
		elements[size - 1] = null;
		size--;
		return removed;
	}

	@Override
	public boolean remove(Object object) {
		// TODO Auto-generated method stub
		boolean found = false;
		
		int index = 0;
		
		/*busco el indice del elemento coincidente*/
		for(int i = 0; i < size; i++) {
			if(elements[i].equals(object)) {
				index = i;
				found = true;
			}
		}
		
		
		if(found) {
			elements[index] = null;
			for(int i = index; i < size - 1; i++) {
				elements[i] = elements[i + 1];
			}
			elements[size - 1] = null;
			size--;
		}
		
		
		return found;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		elements = (E[]) new Object[DEFAULT_CAPACITY];
		size = 0;
	}

	@Override
	public boolean contains(Object object) {
		// TODO Auto-generated method stub
		boolean found = false;

		/*busco el elemento coincidente si lo hay*/
		for(int i = 0; i < size; i++) {
			if(elements[i].equals(object)) found = true;
		}
		return found;
	}

	@Override
	public E get(int index) {
		// TODO Auto-generated method stub
		validateIndex(index);
		return elements[index];
	}

	@Override
	public E set(int index, E element) {
		// TODO Auto-generated method stub
		E currentElement = get(index);
		elements[index] = element;
		return currentElement;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}
		
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size == 0;
	}
	
	private void validateIndex(int index) {
		if(index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index was less than 0, or equal/higher than size");
		}
	}

	@SuppressWarnings("unchecked")
	private void resize() {
		if(size == elements.length) {
		    E[] newElements = (E[]) new Object[elements.length * 2];
		    for(int i = 0; i < size; i++) {
		        newElements[i] = elements[i];
		    }
		    elements = newElements;
		}
	}
}
