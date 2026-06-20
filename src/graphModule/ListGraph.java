package graphModule;

import ListModule.SImpleList;
import ListModule.SimpleArrayList;
import dictionaryModule.SimpleArrayDictionary;

public class ListGraph<T> implements Graph<T> {
    SimpleArrayDictionary<T, SImpleList<Edge<T>>> adjacencyList;

    public ListGraph() {
        adjacencyList = new SimpleArrayDictionary<T, SImpleList<Edge<T>>>();
    }

    @Override
    public SImpleList<T> getVertices() {
        // TODO Auto-generated method stub
        return adjacencyList.keys();
    }

    @Override
    public boolean addVertex(T vertex) {
        // TODO Auto-generated method stub
        if(containsVertex(vertex)) {
            return false;
        }
        adjacencyList.put(vertex, new SimpleArrayList<Edge<T>>());
        return true;
    }

    @Override
    public boolean removeVertex(T vertex) {
        // TODO Auto-generated method stub
        if(!containsVertex(vertex)) {
            return false;
        }
        SImpleList<T> vertices = getVertices();
        int size = size();
        for (int i = 0; i < size; i++) {
            removeEdge(vertices.get(i), vertex);
        }
        adjacencyList.remove(vertex);
        return true;
    }

    @Override
    public boolean addEdge(T vertex1, T vertex2, int weight) {
        // TODO Auto-generated method stub
        addVertex(vertex1);
        addVertex(vertex2);

        Edge<T> edge = getEdge(vertex1, vertex2);
        if(edge == null) {
            adjacencyList.get(vertex1).add(new Edge<T>(vertex2, weight));
            return true;
        }
        if(edge.weight != weight) {
            edge.weight = weight;
            return true;
        }
        return false;

    }

    @Override
    public boolean removeEdge(T vertex1, T vertex2) {
        // TODO Auto-generated method stub
        if(adjacencyList.containsKey(vertex1) || adjacencyList.containsKey(vertex2)) {
            return false;
        }

        SImpleList<Edge<T>> neighbors = adjacencyList.get(vertex1);
        int size = neighbors.size();
        for (int i = 0; i < size; i++) {
            if(neighbors.get(i).to.equals(vertex2)) {
                neighbors.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public SImpleList<Edge<T>> getNeighbors(T vertex) {
        // TODO Auto-generated method stub
        return adjacencyList.get(vertex);
    }

    @Override
    public boolean containsVertex(T vertex) {
        // TODO Auto-generated method stub
        return adjacencyList.containsKey(vertex);
    }

    @Override
    public boolean containsEdge(T vertex1, T vertex2) {
        // TODO Auto-generated method stub
        return getEdge(vertex1, vertex2) != null;
    }

    @Override
    public int getWeight(T vertex1, T vertex2) {
        // TODO Auto-generated method stub
        Edge<T> edge = getEdge(vertex1, vertex2);
        if(edge != null) {
            return edge.weight;
        }
        else {
            throw new IllegalArgumentException("Edge does not exist");
        }
    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        return adjacencyList.size();
    }

    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        return size() == 0;
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub
        adjacencyList.clear();
    }
    
    private Edge<T> getEdge(T from, T to) {
        
        if(!containsVertex(from) || !containsVertex(to)) {
            return null;
        }

        SImpleList<Edge<T>> neighbors = adjacencyList.get(from);
        int edgeSize = neighbors.size();
        for (int i = 0; i < edgeSize; i++) {
            if(neighbors.get(i).to.equals(to)) {
                return neighbors.get(i);
            }
        }
        return null;
        
    }

} 
