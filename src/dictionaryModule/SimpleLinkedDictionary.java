package dictionaryModule;

public class SimpleLinkedDictionary<K,V> implements SimpleDictionary<K,V> {

    private class Node {
        K key;
        V value;
        Node next;
        Node prev;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
            this.prev = null;
        }
    }

    private Node head; // primer nodo
    private Node tail; // último nodo
    private int size;

    public SimpleLinkedDictionary() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public V put(K key, V value) {
        try{
            if(containsKey(key)){
                Node current = head;
                while (current != null) {
                    if (current.key.equals(key)) {
                        V oldValue = current.value;
                        current.value = value;
                        return oldValue;
                    }
                    current = current.next;
                }
            } else {
                Node newNode = new Node(key, value);
                if (isEmpty()) {
                    head = newNode;
                    tail = newNode;
                } else {
                    tail.next = newNode;
                    newNode.prev = tail;
                    tail = newNode;
                }
                size++;
                return null;
            }
        } catch (NullPointerException e) {
            System.out.println("Neither Key nor value can be null");
        } catch (Exception e) {
            System.out.println("Element could not be added");
        }
        return null;
    }

    @Override
    public boolean remove(K key) {
        try{
            if(containsKey(key)){
                Node current = head;
                while(current != null) {
                    if (current.key.equals(key)) {
                        if(current.prev == null){
                            head = current.next;
                        }
                        else if(current.next == null){
                            tail = current.prev;
                        }else{
                            current.prev.next = current.next;
                            current.next.prev = current.prev;
                        }
                        size--;
                        return true;
                    }
                    current = current.next;
                }
            }
        }catch (NullPointerException e) {
            System.out.println("Key cannot be null");
        } catch (Exception e) {
            System.out.println("Element could not be removed");
        }
        return false;
    }

    @Override
    public boolean containsKey(K key) {
        try{
            Node current = head;
            while (current != null) {
                if (current.key.equals(key)) {
                    return true;
                }
                current = current.next;
            }
        } catch (NullPointerException e) {
            System.out.println("Key cannot be null");
        } catch (Exception e) {
            System.out.println("Element could not be found");
        }
        return false;
    }

    @Override
    public V get(K key) {
        try{
            Node current = head;
            while (current != null) {
                if (current.key.equals(key)) {
                    return current.value;
                }
                current = current.next;
            }
        } catch (NullPointerException e) {
            System.out.println("Key cannot be null");
        } catch (Exception e) {
            System.out.println("Element could not be found");
        }
        return null;
    }

    @Override
    public K[] keys() {
        @SuppressWarnings("unchecked")
        K[] result = (K[]) new Object[size];
        Node current = head;
        int i = 0;
        while (current != null) {
            result[i++] = current.key;
            current = current.next;
        }
        return result;
    }

    @Override
    public V[] values() {
        @SuppressWarnings("unchecked")
        V[] result = (V[]) new Object[size];
        Node current = head;
        int i = 0;
        while (current != null) {
            result[i++] = current.value;
            current = current.next;
        }
        return result;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

}
