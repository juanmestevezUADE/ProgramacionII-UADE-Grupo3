package graphModule;
import ListModule.SImpleList;

public interface Graph<T> {
    public SImpleList<T> getVertices();
    public boolean addVertex(T vertex);
    public boolean removeVertex(T vertex);
    public boolean addEdge(T vertex1, T vertex2, int weight);
    public boolean removeEdge(T vertex1, T vertex2);
    public SImpleList<Edge<T>> getNeighbors(T vertex);
    public boolean containsVertex(T vertex);
    public boolean containsEdge(T vertex1, T vertex2);
    public int getWeight(T vertex1, T vertex2);
    public int size();
    public boolean isEmpty();
    public void clear();
}
