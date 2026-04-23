package stackModule;

public class SimpleArrayStack<E> implements SimpleStack<E>{

    private int size;
    private E[] stack; 
    private static final int DEFAULT_CAPACITY = 4;

    @SuppressWarnings("unchecked")
    public SimpleArrayStack(){
        stack = (E[]) new Object[DEFAULT_CAPACITY];
    }
    
    @SuppressWarnings("unchecked")
    public SimpleArrayStack(int capacity){
        stack = (E[]) new Object[capacity];
    }


    @Override
    public void push(E item) {
        // TODO Auto-generated method stub
        
        resize();
        stack[size] = item;
        size++;
    }

    @Override
    public E pop() {
        // TODO Auto-generated method stub
        if(size == 0) {
            throw new RuntimeException("Stack is empty");
        }
        E item = stack[size - 1];
        stack[size - 1] = null; 
        size--;
        return item;
    }

    @Override
    public E peek() {
        // TODO Auto-generated method stub
        if(size == 0) {
            throw new RuntimeException("Stack is empty");
        }
        return stack[size - 1];
    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        return size;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void clear() {
        // TODO Auto-generated method stub
        stack = (E[]) new Object[DEFAULT_CAPACITY];
		size = 0;
    }

    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        return size == 0;
    }

	@SuppressWarnings("unchecked")
	private void resize() {
		if(size == stack.length) {
		    E[] newStack = (E[]) new Object[stack.length * 2];
		    for(int i = 0; i < size; i++) {
		        newStack[i] = stack[i];
		    }
		    stack = newStack;
		}
	}
}
