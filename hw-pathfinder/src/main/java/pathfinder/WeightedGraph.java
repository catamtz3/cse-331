package pathfinder;
import graph.MapClass;
import graph.Nodes;
import pathfinder.datastructures.Path;

import java.util.*;

public class WeightedGraph {
    public static <A> Path<A> dijkstraAlgorithm(MapClass<A, A, Double> graphParse, A start, A end){
        if (start != null && end != null && graphParse.contains(end) && graphParse.contains(start)){
            Queue<ArrayList<Nodes<A, A, Double>>> path = new PriorityQueue<>((o1, o2) -> {
                Nodes<A, A, Double> path1 = o1.get(o1.size() - 1);
                Nodes<A, A, Double> path2 = o2.get(o2.size() - 1);
                if (path1 - path2 > 0) {
                    return 1;
                } else if (path1 - path2 < 0) {
                    return -1;
                } else {
                    return 0;
                }
            });
            Set<A> done = new HashSet<>();
            List<Nodes<A, A, Double>> front = new ArrayList<>();
            front.add(new Nodes(start, end, 0.0));
            path.add(new ArrayList<>(front));
            while (!path.isEmpty()){
                Path<A> mini = path.remove();
                A miniEnd =  mini.get(mini.size() - 1).getB();
                if(miniEnd.equals(end)){
                    return mini;
                }
                if (done.contains(miniEnd)){
                    continue;
                }
                for (Nodes<A, A, Double> i: graphParse.getEdges(miniEnd)){
                    if (!done.contains(i.getB())){
                        List<Nodes<A, A, Double>> newPath = new ArrayList<>(mini);
                        Double newCost = i.getL();
                        newPath.add(new Nodes(i, i.getB(), newCost));
                    }
                }
                done.add(miniEnd);
            }
        }
        return null;
    }
}
