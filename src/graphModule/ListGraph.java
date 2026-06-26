package graphModule;

import ListModule.SImpleList;
import ListModule.SimpleArrayList;
import dictionaryModule.SimpleArrayDictionary;

// Implementación del grafo usando lista de adyacencia.
// Internamente usa un diccionario donde cada clave es un vértice
// y su valor es la lista de aristas que salen de ese vértice.
public class ListGraph<T> implements Graph<T> {

    // Diccionario: vértice -> lista de aristas salientes
    SimpleArrayDictionary<T, SImpleList<Edge<T>>> adjacencyList;

    public ListGraph() {
        adjacencyList = new SimpleArrayDictionary<T, SImpleList<Edge<T>>>();
    }

    // Devuelve todos los vértices del grafo (las claves del diccionario)
    @Override
    public SImpleList<T> getVertices() {
        return adjacencyList.keys();
    }

    // Agrega un vértice nuevo con lista de aristas vacía.
    // Si ya existe, no hace nada y devuelve false.
    @Override
    public boolean addVertex(T vertex) {
        if (containsVertex(vertex)) {
            return false;
        }
        adjacencyList.put(vertex, new SimpleArrayList<Edge<T>>());
        return true;
    }

    // Elimina un vértice y todas las aristas que apuntan hacia él.
    // Primero recorre todos los vértices y les saca la arista hacia el eliminado.
    @Override
    public boolean removeVertex(T vertex) {
        if (!containsVertex(vertex)) {
            return false;
        }
        // Eliminar todas las aristas entrantes al vértice
        SImpleList<T> vertices = getVertices();
        int size = size();
        for (int i = 0; i < size; i++) {
            removeEdge(vertices.get(i), vertex);
        }
        // Eliminar el vértice del diccionario
        adjacencyList.remove(vertex);
        return true;
    }

    // Agrega una arista dirigida de vertex1 a vertex2 con el peso dado.
    // Si alguno de los vértices no existe, lo crea automáticamente.
    // Si la arista ya existe con distinto peso, actualiza el peso.
    @Override
    public boolean addEdge(T vertex1, T vertex2, int weight) {
        // Crea los vértices si no existen
        addVertex(vertex1);
        addVertex(vertex2);

        Edge<T> edge = getEdge(vertex1, vertex2);
        if (edge == null) {
            // La arista no existe: se crea
            adjacencyList.get(vertex1).add(new Edge<T>(vertex2, weight));
            return true;
        }
        if (edge.weight != weight) {
            // La arista existe pero con distinto peso: se actualiza
            edge.weight = weight;
            return true;
        }
        // La arista ya existe con el mismo peso: no hay cambio
        return false;
    }

    // Elimina la arista dirigida de vertex1 a vertex2.
    // Recorre la lista de vecinos de vertex1 hasta encontrarla.
    @Override
    public boolean removeEdge(T vertex1, T vertex2) {
        if (!adjacencyList.containsKey(vertex1) || !adjacencyList.containsKey(vertex2)) {
            return false;
        }

        SImpleList<Edge<T>> neighbors = adjacencyList.get(vertex1);
        int size = neighbors.size();
        for (int i = 0; i < size; i++) {
            if (neighbors.get(i).to.equals(vertex2)) {
                neighbors.remove(i);
                return true;
            }
        }
        return false;
    }

    // Devuelve la lista de aristas que salen del vértice dado
    @Override
    public SImpleList<Edge<T>> getNeighbors(T vertex) {
        return adjacencyList.get(vertex);
    }

    // Verifica si el vértice existe en el diccionario
    @Override
    public boolean containsVertex(T vertex) {
        return adjacencyList.containsKey(vertex);
    }

    // Verifica si existe una arista dirigida de vertex1 a vertex2
    @Override
    public boolean containsEdge(T vertex1, T vertex2) {
        return getEdge(vertex1, vertex2) != null;
    }

    // Devuelve el peso de la arista entre vertex1 y vertex2.
    // Lanza excepción si la arista no existe.
    @Override
    public int getWeight(T vertex1, T vertex2) {
        Edge<T> edge = getEdge(vertex1, vertex2);
        if (edge != null) {
            return edge.weight;
        } else {
            throw new IllegalArgumentException("Edge does not exist");
        }
    }

    // Devuelve la cantidad de vértices (tamaño del diccionario)
    @Override
    public int size() {
        return adjacencyList.size();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    // Vacía el grafo eliminando todos los vértices y aristas
    @Override
    public void clear() {
        adjacencyList.clear();
    }

    // Busca y devuelve la arista dirigida de 'from' a 'to'.
    // Devuelve null si no existe o si alguno de los vértices no está en el grafo.
    private Edge<T> getEdge(T from, T to) {
        if (!containsVertex(from) || !containsVertex(to)) {
            return null;
        }

        SImpleList<Edge<T>> neighbors = adjacencyList.get(from);
        int edgeSize = neighbors.size();
        for (int i = 0; i < edgeSize; i++) {
            if (neighbors.get(i).to.equals(to)) {
                return neighbors.get(i);
            }
        }
        return null;
    }
}
