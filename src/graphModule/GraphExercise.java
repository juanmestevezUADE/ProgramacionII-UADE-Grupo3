package graphModule;

import application.Exercise;
import ListModule.SImpleList;
import java.util.Scanner;
import java.text.Normalizer;

// Ejercicio de consola que simula un GPS sobre un mapa de ciudades representado como grafo.
// El grafo es dirigido y ponderado: cada arista tiene una dirección y un peso (distancia en km).
// Al iniciar, carga un mapa predefinido y permite al usuario editarlo y calcular caminos mínimos.
public class GraphExercise extends Exercise {

    // Grafo de ciudades, donde los vértices son nombres de ciudades (String)
    private ListGraph<String> graph;

    public GraphExercise(Scanner scanner) {
        super(scanner);
        graph = new ListGraph<>();
        loadDefaultGraph();
        System.out.println("\n=== GPS - City Map ===");
        printGraph();
    }

    // Normaliza un texto: elimina acentos y convierte a minúsculas.
    // Esto permite que el usuario escriba "Córdoba", "cordoba" o "CORDOBA" y sean equivalentes.
    private String normalize(String s) {
        // Descompone caracteres acentuados en letra base + marca diacrítica
        String normalized = Normalizer.normalize(s, Normalizer.Form.NFD);
        // Elimina las marcas diacríticas (acentos, tildes, etc.)
        return normalized.replaceAll("\\p{M}", "").toLowerCase();
    }

    // Carga un mapa predefinido de ciudades argentinas con distancias aproximadas en km.
    // Todos los nombres están en minúsculas y sin acentos para coincidir con la normalización.
    private void loadDefaultGraph() {
        graph.addEdge("buenos aires", "rosario", 300);
        graph.addEdge("buenos aires", "la plata", 60);
        graph.addEdge("rosario", "cordoba", 400);
        graph.addEdge("rosario", "santa fe", 170);
        graph.addEdge("cordoba", "mendoza", 700);
        graph.addEdge("cordoba", "santa fe", 230);
        graph.addEdge("mendoza", "san luis", 250);
        graph.addEdge("la plata", "mar del plata", 380);
        graph.addEdge("santa fe", "parana", 30);
    }

    // Imprime todas las aristas del grafo en formato "origen -> destino: peso km"
    private void printGraph() {
        System.out.println("\nCurrent graph:");
        SImpleList<String> vertices = graph.getVertices();
        int vSize = vertices.size();
        boolean hasEdges = false;

        // Recorrer cada vértice y listar sus aristas salientes
        for (int i = 0; i < vSize; i++) {
            String from = vertices.get(i);
            SImpleList<Edge<String>> neighbors = graph.getNeighbors(from);
            int eSize = neighbors.size();
            for (int j = 0; j < eSize; j++) {
                Edge<String> edge = neighbors.get(j);
                System.out.println("  " + from + " -> " + edge.to + ": " + edge.weight + " km");
                hasEdges = true;
            }
        }

        if (!hasEdges) {
            System.out.println("  (empty graph)");
        }
    }

    // Muestra el menú y ejecuta la opción elegida por el usuario
    @Override
    protected void exerciseLogic() {
        System.out.println("\nOptions:" +
                "\nvg: View graph" +
                "\nae: Add edge" +
                "\nre: Remove edge" +
                "\nac: Add city" +
                "\nrc: Remove city" +
                "\nsp: Calculate shortest path" +
                "\nmm: Back to main menu");

        String input = scanner.nextLine().trim();

        switch (input) {
            case "mm":
                running = false;
                break;
            case "vg":
                printGraph();
                break;
            case "ae":
                addEdge();
                break;
            case "re":
                removeEdge();
                break;
            case "ac":
                addVertex();
                break;
            case "rc":
                removeVertex();
                break;
            case "sp":
                calculateShortestPath();
                break;
            default:
                System.out.println("Invalid option, try again.");
                break;
        }
    }

    // Pide al usuario origen, destino y distancia, y agrega la arista al grafo.
    // Si los vértices no existen, se crean automáticamente.
    // Si la arista ya existe con el mismo peso, no hace nada.
    private void addEdge() {
        System.out.print("Origin city: ");
        String from = normalize(scanner.nextLine().trim());
        System.out.print("Destination city: ");
        String to = normalize(scanner.nextLine().trim());

        if (from.isEmpty() || to.isEmpty()) {
            System.out.println("City names cannot be empty.");
            return;
        }

        System.out.print("Distance (km): ");
        String weightStr = scanner.nextLine().trim();
        try {
            int weight = Integer.parseInt(weightStr);
            if (weight <= 0) {
                System.out.println("Distance must be greater than 0.");
                return;
            }
            boolean added = graph.addEdge(from, to, weight);
            if (added) {
                System.out.println("Edge added/updated: " + from + " -> " + to + ": " + weight + " km");
            } else {
                System.out.println("Edge already exists with that weight.");
            }
        } catch (NumberFormatException e) {
            // El usuario ingresó texto donde se esperaba un número
            System.out.println("Invalid distance, please enter an integer.");
        }
    }

    // Pide al usuario origen y destino, y elimina la arista entre ellos.
    private void removeEdge() {
        System.out.print("Origin city: ");
        String from = normalize(scanner.nextLine().trim());
        System.out.print("Destination city: ");
        String to = normalize(scanner.nextLine().trim());

        if (!graph.containsVertex(from) || !graph.containsVertex(to)) {
            System.out.println("One or both cities do not exist in the graph.");
            return;
        }

        boolean removed = graph.removeEdge(from, to);
        if (removed) {
            System.out.println("Edge removed: " + from + " -> " + to);
        } else {
            System.out.println("Edge does not exist.");
        }
    }

    // Agrega una ciudad (vértice) al grafo sin aristas.
    private void addVertex() {
        System.out.print("City name: ");
        String city = normalize(scanner.nextLine().trim());

        if (city.isEmpty()) {
            System.out.println("City name cannot be empty.");
            return;
        }

        boolean added = graph.addVertex(city);
        if (added) {
            System.out.println("City added: " + city);
        } else {
            System.out.println("City already exists.");
        }
    }

    // Elimina una ciudad y todas las aristas que la involucran.
    private void removeVertex() {
        System.out.print("City name to remove: ");
        String city = normalize(scanner.nextLine().trim());

        if (!graph.containsVertex(city)) {
            System.out.println("City does not exist in the graph.");
            return;
        }

        graph.removeVertex(city);
        System.out.println("City removed: " + city);
    }

    // Calcula y muestra el camino más corto entre dos ciudades usando Dijkstra.
    // El camino se muestra en orden de origen a destino con el formato:
    // ciudad1 -> ciudad2 -> ... -> ciudadN
    private void calculateShortestPath() {
        System.out.print("Origin city: ");
        String from = normalize(scanner.nextLine().trim());
        System.out.print("Destination city: ");
        String to = normalize(scanner.nextLine().trim());

        if (!graph.containsVertex(from) || !graph.containsVertex(to)) {
            System.out.println("One or both cities do not exist in the graph.");
            return;
        }

        // obtenerCamino devuelve la lista en orden inverso (destino -> origen)
        SImpleList<String> camino = Dijkstra.obtenerCamino(graph, from, to);

        if (camino.isEmpty()) {
            // No hay camino entre las dos ciudades (grafos desconectados)
            System.out.println("No path available between " + from + " and " + to);
        } else {
            System.out.println("Shortest path from " + from + " to " + to + ":");
            // Recorrer al revés para mostrar de origen a destino
            for (int i = camino.size() - 1; i >= 0; i--) {
                System.out.print(camino.get(i));
                if (i > 0) {
                    System.out.print(" -> ");
                }
            }
            System.out.println();
        }
    }
}
