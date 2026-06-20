package graphModule;

public class Edge<T> {
    public T to;
    public int weight;

    public Edge(T to, int weight) {
        this.to = to;
        this.weight = weight;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals (Object other){
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        
        Edge<T> otherEdge = (Edge<T>) other;
        if(otherEdge.to == null) {
            if (to != null) {
                return false;
            }
        } else if (!otherEdge.to.equals(to)) {
            return false;
        }

        //
        if(otherEdge.weight != weight) {
            return false;
        }
        return to.equals(otherEdge.to);
    }
}
