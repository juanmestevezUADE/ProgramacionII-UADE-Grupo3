package stackModule;

public class SimpleLinkedStack<E> implements SimpleStack<E> {

    @SuppressWarnings("unused")
    private class Node {
        E element;
        Node next;
        Node prev;

        public Node(E element) {
            this.element = element;
            this.next = null;
            this.prev = null;
        }
    }

    private int size;
    private Node head;
    private Node tail;

    public SimpleLinkedStack() {
        this.head = null;
        this.tail = null;
    }

    @Override
    public void push(E item) {
        // TODO Auto-generated method stub
        Node newNode = new Node(item);
        if (size == 0) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    @Override
    public E pop() {
        // TODO Auto-generated method stub
        if (size == 0) {
            throw new RuntimeException("Stack is empty");
        }
        E item = tail.element;
        if (head == tail){
            head = null;
            tail = null;
        } else {
            tail = tail.prev;
            tail.next = null;
        }
        size--;
        return item;
    }

    @Override
    public E peek() {
        // TODO Auto-generated method stub
        if (size == 0) {
            throw new RuntimeException("Stack is empty");
        }
        return tail.element;
    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        return size;
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub
        size = 0;
        head = null;
        tail = null;
    }

    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        return size == 0;
    }

}
