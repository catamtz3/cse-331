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

package pathfinder;

import graph.MapClass;
import pathfinder.datastructures.Path;
import pathfinder.datastructures.Point;
import pathfinder.parser.CampusBuilding;
import pathfinder.parser.CampusPath;
import pathfinder.parser.CampusPathsParser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static pathfinder.parser.CampusPathsParser.parseCampusPaths;

public class CampusMap {
    private Map<String, String> buildingNames;
    MapClass<Point, Point, Double> campusMap;
    List<CampusBuilding> campusBuildings;

    /**
     * A graph of the UW campus and all the buildings on it
     *
     *  * @spec.field nodes are represented by points
     *  *
     *  * @spec.field edges are weighted by the distances between Nodes
     *
     */

    // Rep Invariant:
    // nodes != null, edges != null, graph != null
    // if a node is included in an edge, the node must exist

    // Abstract Function:
    // empty graph: []
    // graph with nodes [a, b, c, ...]
    // graph with nodes and edges [a[ab ac] b[ba] ...]

    public CampusMap(){
        campusMap = new HashMap<Point, Point, Double>();
        buildingNames = new HashMap<>();
        campusBuildings = CampusPathsParser.parseCampusBuildings("campus_buildings.csv");
        List<CampusPath> campusPaths = parseCampusPaths("campus_paths.tsv");
        for (CampusBuilding b : campusBuildings){
            buildingNames.put(b.getShortName(), b.getLongName());
        }
        for (CampusPath p : campusPaths){
            Point a = new Point(p.getX1(), p.getY1());
            Point b = new Point(p.getX2(), p.getY2());
            campusMap.addNode(a);
            campusMap.addNode(b);
            campusMap.addEdge(a, b, p.getDistance());
        }
    }

    /**
     * returns true if the short name of a building exists
     * @param shortName of a building
     * @return true if it exists, else returns false
     */
    public boolean shortNameExists(String shortName) {
        return buildingNames.containsKey(shortName);
    }

    /**
     * returns the long name of a building based off of its short name
     * @param shortName of a building
     * @return long name of the building
     */
    public String longNameForShort(String shortName) {
        return buildingNames.get(shortName);
    }

    /**
     *
     * @return all of the names of the buildings
     */
    public Map<String, String> buildingNames() {
        return buildingNames;
    }

    /**
     *
     * @param startShortName the starting building
     * @param endShortName the ending building
     * @return the shortest path between the two buildings
     */
    public Path<Point> findShortestPath(String startShortName, String endShortName) {
        if (shortNameExists(startShortName) && shortNameExists(endShortName)){
            double x1 = 0.0;
            double x2 = 0.0;
            double y1 = 0.0;
            double y2 = 0.0;
            for (CampusBuilding a : campusBuildings){
                if (a.getShortName().equals(startShortName)) {
                    x1 = a.getX();
                    y1 = a.getY();
                }
                if (a.getShortName().equals(endShortName)){
                    x2 = a.getX();
                    y2 = a.getY();
                }
            }
            Point a = new Point(x1, y1);
            Point b = new Point(x2, y2);
            return WeightedGraph.dijkstraAlgorithm(campusMap, a, b);
        }
        return null;
    }
}
