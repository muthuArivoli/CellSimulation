package simulation;

import java.util.*;

public class Graph<T extends Clonable> {
    private Map<T, List<T> > adjList;
    public Graph(){
        adjList = new LinkedHashMap<>();
    }

    public Graph(Graph<T> myGraph){
        for(T myThing:myGraph.getVertices()){
            addVertex((T)myThing.clone(),adjList.get(myThing));
        }
    }

    public void addEdge(T source, T dest){
        adjList.putIfAbsent(source, new ArrayList<T>());
        adjList.putIfAbsent(dest, new ArrayList<T>());
        adjList.get(source).add(dest);
        adjList.get(dest).add(source);
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
