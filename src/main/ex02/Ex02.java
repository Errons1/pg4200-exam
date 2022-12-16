package ex02;

import java.util.*;

/*
*
* I have written in more detail in my written aswere and README.md that there are some misscomunication on
* the pictures and example given.
*
* From my logical standpoint of a transport nettwork the transport is going back and forth on the route.
* Meaning that the graph implementation is undirectinal
* and allows me to travel whereever the edges connects to a vertexes.
*
*
*
* */

public class Ex02<V> extends UndirectedGraph<V>{

    UndirectedGraph<V> metro;
    UndirectedGraph<V> tram;

    public Ex02 (UndirectedGraph<V> metro, UndirectedGraph<V> tram){
        this.metro = metro;
        this.tram = tram;
    }

    public List<V> findPath(V start, V end){
        if (!(graph.containsKey(start) || graph.containsKey(end))) return Collections.emptyList();
        if (start.equals(end)) throw new IllegalArgumentException();

//        Making a super list over all the list of lists for later check of whitch is shortest.
        List<List<V>> allPaths = new ArrayList<>();

        allPaths.add(metro.findPathDFS(start, end));
        allPaths.add(tram.findPathDFS(start, end));
        if (allPaths.get(0).size() > 0 || allPaths.get(1).size() > 0) return findShortestPath(allPaths);

//        I dont know how to do the last part where the code can jump between graphs to complite the search.

        return null;
    }



    private List<V> findShortestPath(List<List<V>> allPaths){
//        Initiate tmp to make fore easy to write
        List<V> tmp = allPaths.get(0);

        for (List<V> l : allPaths) {
            if (tmp.size() > l.size()) tmp = l;
        }

//        return shortest tmp found
        return tmp;
    }


    public V selectNodeRandomly(){
        List<V> keys;
        Random rand = new Random();
        keys = List.copyOf(graph.keySet()); // convert the key set into a list
        int sel = rand.nextInt(keys.size()); // select a random number between 0 and the max size of the list
        return keys.get(sel); // return the entry for the randomly selected number
    }

}


