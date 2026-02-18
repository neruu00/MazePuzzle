package Main;

import java.awt.Point;
import java.util.*;

interface PathFinder {
    SearchResult findPath(int[][] grid, Point start, Point end);
}

class SearchResult {
    int visitedCount;
    int distance;
    List<Point> path;

    SearchResult(int visitedCount, int distance, List<Point> path) {
        this.visitedCount = visitedCount;
        this.distance = distance;
        this.path = path;
    }
}