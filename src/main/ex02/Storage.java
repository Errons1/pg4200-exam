package ex02;

import java.util.*;

public class Storage {

//    //    Two graphs represting two network with same stations
//    UndirectedGraph<V> tram = new UndirectedGraph<>();
//    UndirectedGraph<V> metro = new UndirectedGraph<>();
//    int counter = 0;
//
//    /*
//     * Finds shortest path with priority:
//     * 1. A path with no changes of transport type, if one exists.
//     * 2. A path with one change of transport type.
//     * 3. Return empy list of no route is possible.
//     * */
//    public List<V> findPath(V start, V end){
//        if (!(tram.graph.containsKey(start) || tram.graph.containsKey(end))) return Collections.emptyList();
//        if (!(metro.graph.containsKey(start) || metro.graph.containsKey(end))) return Collections.emptyList();
//        if (start.equals(end)) throw new IllegalArgumentException();
//
//        List<List<V>> allPaths = new ArrayList<>();
//        Set<V> vertexVisted = new HashSet<>();
//        Stack<V> stackPath = new Stack<>();
//
//        return findShortestFromList(findShortestPath(start, end, allPaths, vertexVisted, stackPath, "tram"));
//    }
//
//    private List<List<V>> findShortestPath(V vertex, V end, List<List<V>> allPaths, Set<V> vertexVisted, Stack<V> stackPath, String call){
//        stackPath.push(vertex);
//
////            If the end of journey is "end" make it an Arraylist
//        if (stackPath.peek().equals(end)){
//            List<V> path = new ArrayList<>(stackPath);
//            allPaths.add(path);
//        }
//
////        If tmp is empty it wont loop
////        Checks all trams options
//        if (call.equals("tram")){
//            for (V v : tram.getAdjacents(vertex)){
////                Prevent looping by only iterate non visited vertexes
//                if (!(vertexVisted.contains(v))){
//                    vertexVisted.add(vertex);
//                    counter++;
//                    findShortestPath(v, end, allPaths, vertexVisted, stackPath, "tram");
//                }
//            }
//        }
//
////        Checks all metro options
//        if (call.equals("metro")){
//            for (V v : metro.getAdjacents(vertex)){
////                Prevent looping by only iterate non visited vertexes
//                if (!(vertexVisted.contains(v))){
//                    vertexVisted.add(vertex);
//                    findShortestPath(v, end, allPaths, vertexVisted, stackPath, "metro");
//                }
//            }
//        }
//
//        stackPath.pop();
//        vertexVisted.remove(vertex);
//        return allPaths;
//    }
//
//
//
//    private List<V> findShortestFromList(List<List<V>> list){
//        List<V> tmp = null;
//        boolean tmpInitiated = false;
//        for (List<V> l : list){
//            if (!tmpInitiated){
//                tmp = l;
//                tmpInitiated = true;
//            }
//            else if (tmp.size() > l.size()) tmp = l;
//        }
//        return tmp;
//    }
//
//    public void makeNetwork(V v){
//        tram.addVertex(v);
//        if (tram.graph.size() > 1){
//            while (true){
//                V tmp = selectNodeRandomly(tram.graph);
//                if (!v.equals(tmp)){
//                    tram.addEdge(v, tmp);
//                    break;
//                }
//            }
//        }
//
//        metro.addVertex(v);
//        if (tram.graph.size() > 1) {
//            while (true) {
//                V tmp = selectNodeRandomly(metro.graph);
//                if (!v.equals(tmp)) {
//                    metro.addEdge(v, tmp);
//                    break;
//                }
//            }
//        }
//    }
//
//    public V selectNodeRandomly(Map<V, Set<V>> vertex){
//        List<V> keys;
//        Random rand = new Random();
//        keys = List.copyOf(vertex.keySet()); // convert the key set into a list
//        int sel = rand.nextInt(keys.size()); // select a random number between 0 and the max size of the list
//        return keys.get(sel); // return the entry for the randomly selected number
//    }
}
