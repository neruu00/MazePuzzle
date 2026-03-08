package Main;

import java.awt.Point;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

class BFSFinder extends AbstractPathFinder {
	
	@Override
    public SearchResult findPath(int[][] grid, Point start, Point end) {
        Queue<Point> queue = new ArrayDeque<>();
        Map<Point, Point> parentMap = new HashMap<>();
        Set<Point> visited = new LinkedHashSet<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            Point curr = queue.poll();
            if (curr.equals(end)) return buildResult(visited.size(), parentMap, end);

            for (Point next : getNeighbors(curr, grid)) {
                if (!visited.contains(next)) {
                    visited.add(next);
                    parentMap.put(next, curr);
                    queue.add(next);
                }
            }
        }
        
        return null; // Impossible 
    }
}