package graphModule;

// Representa una arista (conexión) en el grafo.
// Guarda el vértice de destino y el peso (costo) de la conexión.
public class Edge<T> {
    public T to;       // vértice al que apunta esta arista
    public int weight; // peso o costo de la arista (ej: distancia en km)

    // Constructor: crea una arista hacia 'to' con un peso dado
    public Edge(T to, int weight) {
        this.to = to;
        this.weight = weight;
    }

    // Dos aristas son iguales si apuntan al mismo vértice y tienen el mismo peso
    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        Edge<T> otherEdge = (Edge<T>) other;

        // Comparación segura cuando 'to' puede ser null
        if (otherEdge.to == null) {
            if (to != null) {
                return false;
            }
        } else if (!otherEdge.to.equals(to)) {
            return false;
        }

        // También deben tener el mismo peso
        if (otherEdge.weight != weight) {
            return false;
        }
        return to.equals(otherEdge.to);
    }
}
