package graphModule;

import dictionaryModule.SimpleDictionary;
import dictionaryModule.SimpleLinkedDictionary;
import ListModule.SImpleList;
import ListModule.SimpleArrayList;
import priorityqueueModule.SimpleLinkedPriorityqueue;
import setModule.SimpleArraySet;

// Implementación del algoritmo de Dijkstra para encontrar el camino más corto
// en un grafo dirigido y ponderado con pesos positivos.
public class Dijkstra {

    // Calcula las distancias mínimas desde 'startVertex' hacia todos los demás vértices.
    // Devuelve un diccionario donde cada vértice mapea a una arista que indica:
    //   - 'to': el vértice anterior en el camino más corto (de dónde viene)
    //   - 'weight': la distancia mínima acumulada desde el origen
    public static <T> SimpleDictionary<T, Edge<T>> calculateShortestPaths(ListGraph<T> graph, T startVertex) {

        // Diccionario de resultados: vértice -> (vértice anterior, distancia mínima)
        SimpleDictionary<T, Edge<T>> shortestPaths = new SimpleLinkedDictionary<>();

        // El vértice origen tiene distancia 0 y no tiene vértice anterior (null)
        shortestPaths.put(startVertex, new Edge<>(null, 0));

        // Todos los demás vértices arrancan con distancia "infinita"
        int initialDistance = 1000000;
        SImpleList<T> vertices = graph.getVertices();
        int gsize = vertices.size();
        for (int i = 0; i < gsize; i++) {
            if (!vertices.get(i).equals(startVertex)) {
                // null como vértice anterior indica que aún no se encontró camino
                shortestPaths.put(vertices.get(i), new Edge<>(null, initialDistance));
            }
        }

        // Cola de prioridad para procesar siempre el vértice con menor distancia primero.
        // Como la implementación es max-heap, usamos distancia negativa para simular min-heap:
        // menor distancia real = número negativo más alto = mayor prioridad en el heap.
        SimpleLinkedPriorityqueue<T> notVisited = new SimpleLinkedPriorityqueue<>();
        notVisited.enqueue(startVertex, 0);

        // Conjunto de vértices ya procesados (no se vuelven a evaluar)
        SimpleArraySet<T> visited = new SimpleArraySet<>();

        while (!notVisited.isEmpty()) {
            // Extraer el vértice no visitado con menor distancia acumulada
            T currentVertex = notVisited.dequeue();
            visited.add(currentVertex);

            // Revisar todos los vecinos del vértice actual
            SImpleList<Edge<T>> neighbors = graph.getNeighbors(currentVertex);
            int nsize = neighbors.size();
            for (int i = 0; i < nsize; i++) {
                Edge<T> edge = neighbors.get(i);
                T neighborVertex = edge.to;

                // Solo procesar vecinos que no hayan sido visitados
                if (!visited.contains(neighborVertex)) {
                    // Calcular la distancia pasando por el vértice actual
                    int newDistance = shortestPaths.get(currentVertex).weight + edge.weight;

                    // Si esta nueva distancia es menor a la conocida, actualizar
                    if (newDistance < shortestPaths.get(neighborVertex).weight) {
                        // Guardar que el camino más corto al vecino pasa por currentVertex
                        shortestPaths.put(neighborVertex, new Edge<>(currentVertex, newDistance));
                        // Encolar con prioridad negativa para que menor distancia = mayor prioridad
                        notVisited.enqueue(neighborVertex, -newDistance);
                    }
                }
            }
        }

        return shortestPaths;
    }

    // Devuelve la lista de vértices que forman el camino más corto entre origen y destino.
    // La lista viene en orden inverso (de destino a origen), por lo que al mostrarla
    // hay que recorrerla al revés.
    // Si no existe camino entre origen y destino, devuelve una lista vacía.
    public static <T> SImpleList<T> obtenerCamino(ListGraph<T> graph, T origen, T destino) {
        // Calcular todas las distancias mínimas desde el origen
        SimpleDictionary<T, Edge<T>> shortestPaths = calculateShortestPaths(graph, origen);

        SImpleList<T> camino = new SimpleArrayList<>();
        T currentVertex = destino;

        // Recorrer el mapa de "anteriores" hacia atrás desde destino hasta llegar a null
        while (currentVertex != null) {
            camino.add(currentVertex);
            currentVertex = shortestPaths.get(currentVertex).to;
        }

        // Si el último vértice agregado no es el origen, significa que no hay camino
        // (el destino es inalcanzable desde el origen)
        if (camino.isEmpty() || !camino.get(camino.size() - 1).equals(origen)) {
            return new SimpleArrayList<>();
        }

        return camino;
    }
}
