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

package campuspaths;

import campuspaths.utils.CORSFilter;
import com.google.gson.Gson;
import pathfinder.CampusMap;
import pathfinder.datastructures.Path;
import pathfinder.datastructures.Point;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import java.util.Map;

public class SparkServer {
    public static void main(String[] args) {
        CORSFilter corsFilter = new CORSFilter();
        corsFilter.apply();
        CampusMap newMap = new CampusMap();

        // listBuilding endpoint
        Spark.get("/listBuilding", new Route() {
            @Override
            public Object handle(Request request, Response response){
                Map<String, String> map = newMap.buildingNames();
                Gson gson = new Gson();
                return gson.toJson(map);
            }
        });
        Spark.get("/campusPaths", new Route() {
            @Override
            public Object handle(Request request, Response response) {
                String start = request.queryParams("start");
                String end = request.queryParams("end");
                Path<Point> path = newMap.findShortestPath(start, end);
                Gson gson = new Gson();
                return gson.toJson(path);
            }
        });
    }

}
