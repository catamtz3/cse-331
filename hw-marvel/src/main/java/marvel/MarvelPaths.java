package marvel;
import graph.MapClass;
import graph.Nodes;

import java.io.IOException;
import java.util.*;

/**
 * MarvelPaths takes in data from a file and builds a graph filled with the connections
 * between characters in that graph. Allows you to find the shortest path between any two characters.
 */

public class MarvelPaths {
    /**
     * Main method that takes in input from the user to find the shortest path between
     * two nodes until they want to quit.
     * @param args: ignore (call to make main method)
     * @throws IOException if there's an error while parsing the file
     */
    public static void main (String[] args) throws IOException {
        Scanner console = new Scanner(System.in);
        String user = userChoice(console);
        System.out.print("Please input a file to parse through: ");
        String fileName = console.nextLine();
        MapClass<String, String, String> marvelMap = loadGraph(fileName);
        while (!user.equals("q")){
            System.out.print("Please enter the first characters name: ");
            String charOne = console.nextLine();
            System.out.print("Please enter the second characters name: ");
            String charTwo = console.nextLine();
            String result = "Path from " + charOne + " to " + charTwo + ": ";
            Set<Nodes<String, String, String>> ab = BFS(marvelMap, charOne, charTwo);
            if (marvelMap.contains(charOne) && marvelMap.contains(charTwo)){
                if (ab!= null) {
                    for (Nodes<String, String, String> edge : ab) {
                        result += "\n" + charOne + " to " + edge.getB() +
                                " via " + edge.getL();
                        charOne = edge.getB();
                    }
                }
                result += "no path found.";
            } else {
                System.out.print("One of these heroes doesn't exist. Please enter another first characters name: ");
                charOne = console.nextLine();
                System.out.print("Please enter another second characters name: ");
                charTwo = console.nextLine();
            }
            System.out.println(result);
        }
        System.out.print("Goodbye! Stay safe (especially in the spider verse) :P");
        console.close();
    }

    /**
     * Builds a graph with the given data in a file
     * @param fileName the file that will be read
     * @return MapClass map constructed from the file
     * @throws IOException if an error occurs while reading the file
     * @spec.requires that the file exists
     * @spec.effects builds a graph
     */
    public static MapClass<String, String, String> loadGraph(String fileName) throws IOException {
        MapClass<String, String, String> marvelMap = new MapClass<>();
        Map<String, Set<String>> readFile = new HashMap<>();
        Set<String> allNames = new HashSet<>();
        MarvelParser.parseData(fileName, readFile, allNames);
        for (String character : allNames){
            marvelMap.addNode(character);
        }
        for (String label : readFile.keySet()){
            Set<String> character = readFile.get(label);
            for (String i : character){
                for (String j : character){
                    if (!(i.equals(j))) {
                        marvelMap.addEdge(i, j, label);
                        marvelMap.addEdge(j, i, label);
                    }
                }
            }
            String[] charArray = character.toArray(new String[character.size()]);
            for (int i = 0; i < charArray.length; i++){
                for (int j = 1; j < charArray.length; i++){
                    String initial = charArray[i];
                    String secondary = charArray[j];
                    marvelMap.addEdge(initial, secondary, label);
                    marvelMap.addEdge(secondary, initial, label);
                }
            }
        }
        return marvelMap;
    }

    /**
     * Asks the user if they want to quit the program or continue
     * @param console: takes in the users responses
     * @return i if user wants to find a path between two characters;
     *         else returns q for the user to quit.
     */
    public static String userChoice(Scanner console){
            System.out.print("This program takes in two characters names from the Marvel universe and determines" +
                    " if theres a link between them. Enter (i) to input character names, or (q) to quit the program. ");
            String choice = console.nextLine();
            while (!choice.equalsIgnoreCase("i") && !choice.equalsIgnoreCase("q")) {
                System.out.print("This program takes in two characters names from the Marvel universe and determines" +
                        " if theres a link between them. Enter (i) to input character names, or (q) to quit the program. ");
                choice = console.nextLine();
            }
            if (choice.equalsIgnoreCase("i")){
                return "i";
            } else {
                return "q";
         }
    }

/**
    start = starting node
    dest = destination node
    Q = queue, or "worklist", of nodes to visit: initially empty
    M = map from nodes to paths: initially empty.

        // Each key in M is a visited node.
        // Each value is a path from start to that node.
        // A path is a list; generally, this is a list of node and edge data together (in
        // whatever data structure makes sense for you) because we want to include
        // both the nodes along the path and the specific edges you take to get to them.

    Add start to Q
    Add start to M (start mapped to an empty list/path)
    while Q is not empty:
    dequeue next node n
    if n is dest
        return the path associated with n in M
    for each edge e=⟨n,m⟩:
            if m is not in M, i.e. m has not been visited:
    let p be the path n maps to in M
    let p' be the path formed by appending e to p
    add m to p to M
    add m to Q
 * @param marvelMap: the original map with all the super heroes and comic books
 * @param start node of the path
 * @param end node of the path
 * @return a set of Nodes that have been visited through the path */
// If the loop terminates, then no path exists from start to dest.
// The implementation should indicate this to the client. Note that
// BFS returns the path with the fewest number of edges.
    public static Set<Nodes<String, String, String>> BFS(MapClass<String, String, String> marvelMap, String start, String end){
        if (marvelMap.contains(start) && marvelMap.contains(end) && marvelMap != null
            && start != null && end != null) {
            Queue<String> path = new LinkedList<>();
            Map<String, Set<Nodes<String, String, String>>> visited = new HashMap<>();
            path.add(start);
            visited.put(start, new HashSet<>());
            while (!path.isEmpty()) {
                String target = path.remove();
                if (target.equals(end)) {
                    return visited.get(target);
                }
            }
            List<Nodes<String, String, String>> order = new ArrayList<>(marvelMap.getEdges(start));
            Comparator<Nodes> compareOrder = new Comparator<Nodes>() {
                @Override
                public int compare(Nodes o1, Nodes o2) {
                    if (!o1.getB().equals(o2.getB())) {
                        o1.getB().compareTo(o2.getB());
                    }
                    if (!(o1.getL().equals(o2.getL()))) {
                        return o1.getL().compareTo(o2.getL());
                    }
                    return 0;
                }

            };
                for (Nodes<String,String,String> comp : order){
                String pop = comp.getB();
                if(!(visited.containsKey(pop))){
                    path.add(pop);
                    Set<Nodes<String, String, String>> oldPath = visited.get(pop);
                    Set<Nodes<String, String, String>> newPath = new TreeSet<>(oldPath);
                    newPath.add(comp);
                    visited.put(end, newPath);
                }
            }
            return visited.get(start);
        }
        return null;
    }
}
