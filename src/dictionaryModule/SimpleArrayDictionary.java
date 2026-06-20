package dictionaryModule;

import ListModule.SImpleList;
import ListModule.SimpleArrayList;
public class SimpleArrayDictionary<K, V> implements SimpleDictionary<K, V> {

    private K[] keys;
    private V[] values;
    private static final int DEFAULT_CAPACITY = 4;
    private int size;

    @SuppressWarnings("unchecked")
    public SimpleArrayDictionary() {
        keys = (K[]) new Object[DEFAULT_CAPACITY];
        values = (V[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public V put(K key, V value) {
        try {
            if (containsKey(key)) {
                int index = indexOfKey(key);
                V oldValue = values[index];
                values[index] = value;
                return oldValue;
            } else {
                if (size == keys.length) {
                    resize();
                }
                keys[size] = key;
                values[size] = value;
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
        try {
           if(containsKey(key)){
                int index = indexOfKey(key);
                keys[index] = keys[size - 1]; // Reemplaza la clave a eliminar con la última clave
                values[index] = values[size - 1]; // Reemplaza el valor a eliminar con el último valor
                keys[size - 1] = null; // Elimina la referencia a la última clave
                values[size - 1] = null; // Elimina la referencia al último valor
                size--;
                return true;
            } else {
                return false; // Clave no encontrada
           } 
           
        } catch (NullPointerException e) {
            System.out.println("Neither Key cannot be null");
        } catch (Exception e) {
            System.out.println("Element could not be removed");
        }
        return false;
    }

    @Override
    public boolean containsKey(K key) {
        try{
            for (int i = 0; i < size; i++) {
                if (keys[i].equals(key)) {
                    return true;
                }
            }
            return false;
        }catch(NullPointerException e){
            System.out.println("Key cannot be null");
            return false;
        }catch(Exception e){
            System.out.println("Key could not be checked for existence");
        }
        return false;
    }

    @Override
    public V get(K key) {
        try {
            for (int i = 0; i < size; i++) {
                if (keys[i].equals(key)) {
                    return values[i];
                }
            }
            return null;
        } catch (NullPointerException e) {
            System.out.println("Key cannot be null");
            return null;
        } catch (Exception e) {
            System.out.println("Value could not be retrieved");
        }
        return null;
    }

    @Override
    public SImpleList<K> keys() {
        if (size == 0) {
            return null;
        }
        SImpleList<K> result = new SimpleArrayList<>(size);
        for (int i = 0; i < size; i++) {
            result.add(keys[i]);
        }
        return result;
    }

    @Override
    public SImpleList<V> values() {
        if (size == 0) {
            return null;
        }

        SImpleList<V> result = new SimpleArrayList<V>(size);
        for (int i = 0; i < size; i++) {
            result.add(values[i]);
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

    @SuppressWarnings("unchecked")
    @Override
    public void clear() {
        keys = (K[]) new Object[DEFAULT_CAPACITY];
        values = (V[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        if (size == keys.length) {
            int newCapacity = keys.length * 2;
            K[] newKeys = (K[]) new Object[newCapacity];
            V[] newValues = (V[]) new Object[newCapacity];
            for (int i = 0; i < size; i++) {
                newKeys[i] = keys[i];
                newValues[i] = values[i];
            }
            keys = newKeys;
            values = newValues;
        }
    }

    private int indexOfKey(K key) {
        for (int i = 0; i < size; i++) {
            if (keys[i].equals(key)) {
                return i;
            }
        }
        return -1;
    }
}
