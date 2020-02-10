package simulation;


import java.util.*;

public class Graph<T extends Clonable> {
    private Map<T, List<T>> adjList;
    public Graph(){
        adjList = new LinkedHashMap<>();
    }

    public Graph(Graph<T> myGraph){
        this();
        for(T myVertex:myGraph.getVertices()){
            addVertex((T)myVertex.copy(),new ArrayList<>(myGraph.getAdjList().get(myVertex)));
        }
    }

    public Map<T, List<T>> getAdjList(){
        return adjList;
    }

    public void addEdge(T source, T dest){
        adjList.putIfAbsent(source, new ArrayList<T>());
        adjList.get(source).add(dest);
    }

    public void addVertex(T c,List<T> neighbors){
        adjList.put(c, neighbors);
    }

    public List<T> getNeighbors(T ind){
        return adjList.get(ind);
    }

    public Set<T> getVertices(){
        return adjList.keySet();
    }

}
