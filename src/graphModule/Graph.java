package graphModule;

import ListModule.SImpleList;

// Interfaz que define las operaciones básicas de un grafo dirigido y ponderado.
// T es el tipo de dato de los vértices (ej: String para nombres de ciudades).
public interface Graph<T> {

    // Devuelve una lista con todos los vértices del grafo
    public SImpleList<T> getVertices();

    // Agrega un vértice al grafo. Devuelve true si se agregó, false si ya existía.
    public boolean addVertex(T vertex);

    // Elimina un vértice y todas sus aristas. Devuelve true si existía.
    public boolean removeVertex(T vertex);

    // Agrega una arista dirigida de vertex1 a vertex2 con un peso dado.
    // Si la arista ya existe, actualiza el peso. Devuelve true si hubo cambio.
    public boolean addEdge(T vertex1, T vertex2, int weight);

    // Elimina la arista dirigida de vertex1 a vertex2. Devuelve true si existía.
    public boolean removeEdge(T vertex1, T vertex2);

    // Devuelve la lista de aristas que salen del vértice dado (sus vecinos)
    public SImpleList<Edge<T>> getNeighbors(T vertex);

    // Verifica si el vértice existe en el grafo
    public boolean containsVertex(T vertex);

    // Verifica si existe una arista dirigida de vertex1 a vertex2
    public boolean containsEdge(T vertex1, T vertex2);

    // Devuelve el peso de la arista entre vertex1 y vertex2
    public int getWeight(T vertex1, T vertex2);

    // Devuelve la cantidad de vértices en el grafo
    public int size();

    // Devuelve true si el grafo no tiene vértices
    public boolean isEmpty();

    // Elimina todos los vértices y aristas del grafo
    public void clear();
}
