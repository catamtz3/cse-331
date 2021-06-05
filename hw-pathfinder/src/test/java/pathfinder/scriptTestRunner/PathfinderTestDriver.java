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

package pathfinder.scriptTestRunner;

import graph.MapClass;
import graph.Nodes;
import pathfinder.CampusMap;
import pathfinder.WeightedGraph;
import pathfinder.datastructures.Path;
import pathfinder.datastructures.Point;

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

/**
 * This class implements a test driver that uses a script file format
 * to test an implementation of Dijkstra's algorithm on a graph.
 */
public class PathfinderTestDriver {

    private final Map<String, MapClass<String, String, Double>> newGraph =
            new HashMap<String, MapClass<String, String, Double>>();
    private final PrintWriter output;
    private final BufferedReader input;

    // Leave this constructor public
    public PathfinderTestDriver(Reader r, Writer w) {
        input = new BufferedReader(r);
        output = new PrintWriter(w);
    }

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
        MapClass<String, String, Double> a = newGraph.get(graphName);
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
        MapClass<String, String, Double> a = newGraph.get(graphName);
        DecimalFormat d = new DecimalFormat("0.000");
        double y = Double.parseDouble(edgeLabel);
        a.addEdge(parentName, childName, y);
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
        MapClass<String, String, Double> a = newGraph.get(graphName);
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
        MapClass<String, String, Double> a = newGraph.get(graphName);
        String result = "the children of " + parentName + " in " + graphName + " are: ";
        List<Nodes<String, String, Double>> sortLabels = new ArrayList<Nodes<String, String, Double>>(a.getChildren(parentName));
        Comparator<Nodes<String,String,Double>> edgeComparator = (o1, o2) -> {
            if (!o1.getL().equals(o2.getL())) {
                return o1.getL().compareTo(o2.getL());
            } else {
                return o1.getB().compareTo(o2.getB());
            }
        };

        sortLabels.sort(edgeComparator);
        for (Nodes<String, String, Double> b : sortLabels){
            result += b.toString() + " ";
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
        MapClass<String, String, Double> a = newGraph.get(graphName);
        if ((!a.contains(parentName)) && (!a.contains(childName))) {
            output.println("unknown: " + parentName);
            output.println("unknown: " + childName);
        } else if ((!a.contains(childName))) {
            output.println("unknown: " + childName);
        } else if (!(a.contains(parentName))) {
            output.println("unknown: " + parentName);
        }
        if (a.contains(parentName) && a.contains(childName)) {
            String result = "path from " + parentName + " to " + childName + ":";
            Path<String> smallest = WeightedGraph.dijkstraAlgorithm(a, parentName, childName);
            for (Path<String>.Segment b : smallest) {
                result += "\n" + b.getStart() + " to " + b.getEnd() +" with weight " + b.getCost();
            }
            output.println(result + "\n" + "total cost: " + smallest.getCost());
        }
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