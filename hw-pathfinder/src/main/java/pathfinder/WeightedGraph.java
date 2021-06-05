package pathfinder;
import graph.MapClass;
import graph.Nodes;
import pathfinder.datastructures.Path;

import java.util.*;

public class WeightedGraph {
    public static <A> Path<A> dijkstraAlgorithm(MapClass<A, A, Double> graphParse, A start, A end){
        if (start != null && end != null && graphParse.contains(end) && graphParse.contains(start)){
            Comparator<Path<A>> pathComparator = Comparator.comparingDouble(Path::getCost);
            Queue<Path<A>> path = new PriorityQueue<Path<A>>(pathComparator);
            Path<A> beginning = new Path<>(start);
            Set<A> done = new HashSet<>();
            path.add(beginning);
            while (!path.isEmpty()){
                Path<A> mini = path.remove();
                A miniEnd =  mini.getEnd();
                if(miniEnd.equals(end)){
                    return mini;
                }
                if (done.contains(miniEnd)){
                    continue;
                }
                for (Nodes<A, A, Double> i: graphParse.getEdges(miniEnd)){
                    if (!done.contains(i.getB())){
                        Path<A> newPath = mini.extend(i.getB(), i.getL());
                        path.add(newPath);
                    }
                }
                done.add(miniEnd);
            }
        }
        return null;
    }
}
