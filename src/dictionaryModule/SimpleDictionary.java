package dictionaryModule;

import ListModule.SImpleList;

// Interfaz que define las operaciones básicas de un diccionario (mapa clave-valor).
// K es el tipo de la clave, V es el tipo del valor asociado.
public interface SimpleDictionary<K, V> {

    // Asocia la clave con el valor dado. Si la clave ya existía, actualiza el valor
    // y devuelve el valor anterior. Si es nueva, devuelve null.
    public V put(K key, V value);

    // Elimina la entrada con la clave dada. Devuelve true si existía.
    public boolean remove(K key);

    // Devuelve true si el diccionario contiene la clave dada
    public boolean containsKey(K key);

    // Devuelve el valor asociado a la clave, o null si no existe
    public V get(K key);

    // Devuelve una lista con todas las claves del diccionario
    public SImpleList<K> keys();

    // Devuelve una lista con todos los valores del diccionario
    public SImpleList<V> values();

    // Devuelve la cantidad de entradas en el diccionario
    public int size();

    // Devuelve true si el diccionario no tiene entradas
    public boolean isEmpty();

    // Elimina todas las entradas del diccionario
    public void clear();
}
