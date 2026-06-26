package dictionaryModule;

import ListModule.SImpleList;
import ListModule.SimpleArrayList;

// Implementación de diccionario usando una lista doblemente enlazada.
// Cada nodo guarda un par clave-valor y punteros al anterior y siguiente.
public class SimpleLinkedDictionary<K, V> implements SimpleDictionary<K, V> {

    // Nodo interno: guarda la clave, el valor y los punteros de la lista doble
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

    // Si la clave ya existe, actualiza su valor y devuelve el anterior.
    // Si no existe, agrega un nuevo nodo al final y devuelve null.
    @Override
    public V put(K key, V value) {
        try {
            if (containsKey(key)) {
                // Buscar el nodo con esa clave y actualizar su valor
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
                // Clave nueva: agregar al final de la lista
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

    // Busca el nodo con la clave dada y lo elimina reconectando los adyacentes
    @Override
    public boolean remove(K key) {
        try {
            if (containsKey(key)) {
                Node current = head;
                while (current != null) {
                    if (current.key.equals(key)) {
                        if (current.prev == null) {
                            // Es la cabeza: el nuevo head es el siguiente
                            head = current.next;
                            if (head != null) head.prev = null;
                        } else if (current.next == null) {
                            // Es la cola: el nuevo tail es el anterior
                            tail = current.prev;
                            tail.next = null;
                        } else {
                            // Nodo del medio: reconectar anterior con siguiente
                            current.prev.next = current.next;
                            current.next.prev = current.prev;
                        }
                        size--;
                        return true;
                    }
                    current = current.next;
                }
            }
        } catch (NullPointerException e) {
            System.out.println("Key cannot be null");
        } catch (Exception e) {
            System.out.println("Element could not be removed");
        }
        return false;
    }

    // Recorre la lista buscando un nodo con la clave dada
    @Override
    public boolean containsKey(K key) {
        try {
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

    // Busca la clave y devuelve el valor del nodo correspondiente
    @Override
    public V get(K key) {
        try {
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

    // Recorre la lista y copia todas las claves a una nueva lista
    @Override
    public SImpleList<K> keys() {
        SImpleList<K> result = new SimpleArrayList<K>(size);
        Node current = head;
        while (current != null) {
            result.add(current.key);
            current = current.next;
        }
        return result;
    }

    // Recorre la lista y copia todos los valores a una nueva lista
    @Override
    public SImpleList<V> values() {
        SImpleList<V> result = new SimpleArrayList<V>(size);
        Node current = head;
        while (current != null) {
            result.add(current.value);
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

    // Desconecta todos los nodos
    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }
}
