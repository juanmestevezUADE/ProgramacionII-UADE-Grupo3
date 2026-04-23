package ListModule;

public class SimpleLinkedList<E> implements SImpleList<E> {

	private class Node {
        E element;
        Node prev;
        Node next;

        public Node(E element) {
            this.element = element;
            this.prev = null;
            this.next = null;
        }
    }

    
    private Node head; // primer nodo
    private Node tail; // último nodo
    private int size;

    public SimpleLinkedList() {
        head = null;
        tail = null;
    }


	@Override
	public boolean add(E element) {
		// TODO Auto-generated method stub
		Node newNode = new Node(element);
		if(isEmpty()) {
			head = newNode;
			tail = newNode;
		} else {
			tail = newNode.prev;
			tail.next = newNode;
			tail = newNode;
		}
		size++;
		return true;
	}

	@Override
	public void add(int index, E element) {
		// TODO Auto-generated method stub
		validateIndex(index);
		Node newNode = new Node(element);
		Node current = getNodeByIndex(index);
		
		
		newNode.next = current;
		newNode.prev = current.prev;
		
		//si el anterior al nodo q corremos existe hacemos que apunte al nuevo
		if(current.prev != null) {
			current.prev.next = newNode;
		}//en su defecto sera la nueva cabeza
		else {
			head = newNode;
		}
		current.prev = newNode;
		size++;
		
	}

	@Override
	public E remove(int index) {
		// TODO Auto-generated method stub
		Node toRemove = getNodeByIndex(index);
		removeAndReconnect(toRemove);
		return toRemove.element;
	}

	@Override
	public boolean remove(Object object) {
		// TODO Auto-generated method stub
		Node current = head;
		while(current.next != null) {
			if(current.element.equals(object)) {
				removeAndReconnect(current);
				return true;
			}
			current = current.next;
		}
		
		
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		head = null;
		tail = null;
		size = 0;
	}

	@Override
	public boolean contains(Object object) {
		// TODO Auto-generated method stub
		
		Node current = head;
		
		while(current.next != null) {
			if(current.element.equals(object)) {
				return true;
			}
			current = current.next;
		}
		
		return false;
	}

	@Override
	public E get(int index) {
		// TODO Auto-generated method stub
		return getNodeByIndex(index).element;
	}

	@Override
	public E set(int index, E element) {
		// TODO Auto-generated method stub
		Node current = getNodeByIndex(index);
		E old = current.element;
		current.element = element;
		return old;
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
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index fuera de rango");
        }
    }
	
	private Node getNodeByIndex(int index) {
		
		//valido el index
		validateIndex(index);
		
		 Node current;
		 
		 /*Me fijo si esta en la primera mitad*/
		 if(index < size / 2) {
			 current = head;
			 for(int i = 0; i < index; i++) {
				 current = current.next;
			 }
		 }else {
			 current = tail;
			 for(int i = size; i > index; i--) {
				 current = current.prev;
			 }
		 }
		return current;
	}
	
	private void removeAndReconnect(Node toRemove) {
		
		//Caso 1: es el unico elemento
		if(toRemove == head && toRemove == tail) {
			head = null;
			tail = null;
		}
		//caso 2: es la cabeza
		else if(toRemove == head) {
			toRemove.next.prev = null;
			head = toRemove.next;
			
		}
		//caso 3: es la cola
		else if(toRemove == tail) {
			tail = toRemove.prev;
			tail.next = null;
		}
		//caso 4: esta en el medio
		else {
			toRemove.next.prev = toRemove.prev;
			toRemove.prev.next = toRemove.next;
		}
		toRemove.next = null;
		toRemove.prev = null;
		size--;
	}
}
