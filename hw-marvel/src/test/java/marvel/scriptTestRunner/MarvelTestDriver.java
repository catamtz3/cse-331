/*
 * Copyright (C) 2021 Kevin Zatloukal.  All rights reserved.  Permission is
 * hereby granted to students registered for University of Washington
 * CSE 331 for use solely during Spring Quarter 2021 for purposes of
 * the course.  No other use, copying, distribution, or modification
 * is permitted without prior written consent. Copyrights for
 * third-party components of this work must be honored.  Instructors
 * interested in reusing these course materials should contact the
 * author.
 */

package marvel.scriptTestRunner;

import graph.MapClass;
import graph.Nodes;
import marvel.MarvelPaths;
import java.io.*;
import java.util.*;
/**
 * This class implements a testing driver which reads test scripts from
 * files for testing Graph, the Marvel parser, and your BFS algorithm.
 */
public class MarvelTestDriver {
    private final Map<String, MapClass<String, String, String>> newGraph =
            new HashMap<String, MapClass<String, String, String>>();
    private final PrintWriter output;
    private final BufferedReader input;

    public MarvelTestDriver(Reader r, Writer w) {
        input = new BufferedReader(r);
        output = new PrintWriter(w);
    }

    /**
     * @throws IOException if the input or output sources encounter an IOException
     * @spec.effects Executes the commands read from the input and writes results to the output
     **/
    // Leave this method public
    public void runTests() throws IOException {
        String inputLine;
        while ((inputLine = input.readLine()) != null) {
            if ((inputLine.trim().length() == 0) ||
                    (inputLine.charAt(0) == '#')) {
                // echo blank and comment lines
                output.println(inputLine);
            } else {
                // separate the input line on white space
                StringTokenizer st = new StringTokenizer(inputLine);
                if (st.hasMoreTokens()) {
                    String command = st.nextToken();

                    List<String> arguments = new ArrayList<>();
                    while (st.hasMoreTokens()) {
                        arguments.add(st.nextToken());
                    }

                    executeCommand(command, arguments);
                }
            }
            output.flush();
        }
    }

    
    private void executeCommand(String command, List<String> arguments) {
        try {
            switch (command) {
                case "CreateGraph":
                    createGraph(arguments);
                    break;
                case "LoadGraph":
                    loadGraph(arguments);
                    break;
                case "AddNode":
                    addNode(arguments);
                    break;
                case "FindPath":
                    findPath(arguments);
                    break;
                case "AddEdge":
                    addEdge(arguments);
                    break;
                case "ListNodes":
                    listNodes(arguments);
                    break;
                case "ListChildren":
                    listChildren(arguments);
                    break;
                default:
                    output.println("Unrecognized command: " + command);
                    break;
            }
        } catch (Exception e) {
            String formattedCommand = command;
            formattedCommand += arguments.stream().reduce("", (a, b) -> a + " " + b);
            output.println("Exception while running command: " + formattedCommand);
            e.printStackTrace(output);
        }
    }

    private void createGraph(List<String> arguments) {
        if (arguments.size() != 1) {
            throw new CommandException("Bad arguments to CreateGraph: " + arguments);
        }

        String graphName = arguments.get(0);
        createGraph(graphName);
    }

    private void createGraph(String graphName) {
        newGraph.put(graphName, new MapClass<>());
        output.println("created graph " + graphName);
    }

    private void addNode(List<String> arguments) {
        if (arguments.size() != 2) {
            throw new CommandException("Bad arguments to AddNode: " + arguments);
        }

        String graphName = arguments.get(0);
        String nodeName = arguments.get(1);

        addNode(graphName, nodeName);
    }

    private void addNode(String graphName, String nodeName) {
        MapClass<String, String, String> a = newGraph.get(graphName);
        a.addNode(nodeName);
        output.println("added node " + nodeName + " to " + graphName);
    }

    private void addEdge(List<String> arguments) {
        if (arguments.size() != 4) {
            throw new CommandException("Bad arguments to AddEdge: " + arguments);
        }

        String graphName = arguments.get(0);
        String parentName = arguments.get(1);
        String childName = arguments.get(2);
        String edgeLabel = arguments.get(3);

        addEdge(graphName, parentName, childName, edgeLabel);
    }

    private void addEdge(String graphName, String parentName, String childName,
                         String edgeLabel) {
        MapClass<String, String, String> a = newGraph.get(graphName);
        a.addEdge(parentName, childName, edgeLabel);
        output.println("added edge " + edgeLabel + " from " + parentName +
                " to " + childName + " in " + graphName);
    }

    private void listNodes(List<String> arguments) {
        if (arguments.size() != 1) {
            throw new CommandException("Bad arguments to ListNodes: " + arguments);
        }

        String graphName = arguments.get(0);
        listNodes(graphName);
    }

    private void listNodes(String graphName) {
        MapClass<String, String, String> a = newGraph.get(graphName);
        output.println(graphName + " contains: " + a.listNodes());
    }

    private void listChildren(List<String> arguments) {
        if (arguments.size() != 2) {
            throw new CommandException("Bad arguments to ListChildren: " + arguments);
        }

        String graphName = arguments.get(0);
        String parentName = arguments.get(1);
        listChildren(graphName, parentName);
    }

    private void listChildren(String graphName, String parentName) {
        MapClass<String, String, String> a = newGraph.get(graphName);
        String result = "the children of " + parentName + " in " + graphName + " are: ";
        List<Nodes<String, String, String>> sortLabels = new ArrayList<Nodes<String, String, String>>(a.getChildren(parentName));
        Comparator<Nodes<String,String,String>> edgeComparator = (o1, o2) -> {
            if (!o1.getB().equals(o2.getB())) {
                return o1.getB().compareTo(o2.getB());
            } else if (!o1.getL().equals(o2.getL())) {
                return o1.getL().compareTo(o2.getL());
            } else {
                return 0;
            }
        };

        sortLabels.sort(edgeComparator);
        for (Nodes<String, String, String> b : sortLabels){
            result += b.getB() + "(" + b.toString() + ") ";
        }
        output.println(result.trim());
    }

    private void findPath(List<String> arguments) {
        if (arguments.size() != 3) {
            throw new CommandException("Bad arguments to FindPath: " + arguments);
        }
        String graphName = arguments.get(0);
        String parentName = arguments.get(1);
        String childName = arguments.get(2);
        findPath(graphName, parentName, childName);
    }

    private void findPath(String graphName, String parentName, String childName) {
        MapClass<String, String, String> a = newGraph.get(graphName);
        if ((!a.contains(parentName)) && (!a.contains(childName))) {
            output.println("unknown: " + parentName);
            output.println("unknown: " + childName);
        } else if ((!a.contains(childName))) {
            output.println("unknown: " + childName);
        } else if (!(a.contains(parentName))) {
            output.println("unknown: " + parentName);
        }
        if (a.contains(parentName) && a.contains(childName)) {
            List<Nodes<String, String, String>> ab = MarvelPaths.BFS(a, parentName, childName);
            String result = "path from " + parentName + " to " + childName + ":";
            if (parentName.equals(childName)){
                result += "";
            } else if (ab.isEmpty()) {
                result += "\n" + "no path found";
            } else {
                for (Nodes<String, String, String> edge : ab) {
                    result += "\n" + parentName + " to " + edge.getB() +
                            " via " + edge.getL();
                    parentName = edge.getB();
                }
            }
            output.println(result);
        }
    }

    private void loadGraph(List<String> arguments) throws IOException {
        if (arguments.size() != 2) {
            throw new CommandException("Bad arguments to LoadGraph: " + arguments);
        }
        String graphName = arguments.get(0);
        String file = arguments.get(1);
        loadGraph(graphName, file);
    }

    private void loadGraph(String graphName, String file) throws IOException {
        MapClass<String, String, String> a = MarvelPaths.loadGraph(file);
        newGraph.put(graphName, a);
        output.println("loaded graph " + graphName);
    }


    /**
     * This exception results when the input file cannot be parsed properly
     **/
    static class CommandException extends RuntimeException {

        public CommandException() {
            super();
        }

        public CommandException(String s) {
            super(s);
        }

        public static final long serialVersionUID = 3495;
    }
}
