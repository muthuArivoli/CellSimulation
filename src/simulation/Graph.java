package simulation;

import cellsociety.Cell;

import java.util.*;

public class Graph<T extends Clonable> {
    private Map<T, List<T> > adjList;
    private int rowSize = 5;
    public Graph(){
        adjList = new LinkedHashMap<>();
    }

    public Graph(Graph<T> myGraph){
        for(T myVertex:myGraph.getVertices()){
            addVertex((T)myVertex.clone(),new ArrayList<>(adjList.get(myVertex)));
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

    public Collection graphToCollection() {
        ArrayList<ArrayList<T>> grid = new ArrayList<ArrayList<T>>();
        for(T c : adjList.keySet()){
            ArrayList<T> row = new ArrayList<T>();
            for(int i = 0; i < rowSize; i++){
                row.add(c);
            }
            grid.add(row);
        }
        return grid;
    }

}
