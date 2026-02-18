package Main;

import java.awt.Point;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

class DFSFinder extends BFSFinder {
    public SearchResult findPath(int[][] grid, Point start, Point end) {
        Stack<Point> stack = new Stack<>();
        Map<Point, Point> parentMap = new HashMap<>();
        Set<Point> visited = new LinkedHashSet<>();

        stack.push(start);
        while (!stack.isEmpty()) {
            Point curr = stack.pop();
            if (!visited.contains(curr)) {
                visited.add(curr);
                if (curr.equals(end)) return super.findPath(grid, start, end);
                for (Point next : getNeighbors(curr, grid)) {
                    if (!visited.contains(next)) {
                        parentMap.put(next, curr);
                        stack.push(next);
                    }
                }
            }
        }
        return null; // 경로 없음
    }
}