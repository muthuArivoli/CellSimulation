package simulation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph<T> {
    private int V;
    private Map<T, List<T> > adjList;

    public Graph(){
        adjList = new HashMap<>();
    }

    public void addEdge(T source, T dest){
        adjList.putIfAbsent(source, new ArrayList<T>());
        adjList.putIfAbsent(dest, new ArrayList<T>());
        adjList.get(source).add(dest);
        adjList.get(dest).add(source);
    }

    public List<T> getNeighbors(T ind){
        return adjList.get(ind);
    }
}
