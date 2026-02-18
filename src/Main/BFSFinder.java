package Main;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

class BFSFinder implements PathFinder {
    public SearchResult findPath(int[][] grid, Point start, Point end) {
        int rows = grid.length, cols = grid[0].length;
        Queue<Point> queue = new LinkedList<>();
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
        return null;
    }

    protected List<Point> getNeighbors(Point p, int[][] grid) {
        List<Point> neighbors = new ArrayList<>();
        int[] dx = {0, 0, 1, -1}, dy = {1, -1, 0, 0};
        for (int i = 0; i < 4; i++) {
            int nx = p.x + dx[i], ny = p.y + dy[i];
            if (nx >= 0 && nx < 8 && ny >= 0 && ny < 8 && grid[nx][ny] != 1) {
                neighbors.add(new Point(nx, ny));
            }
        }
        return neighbors;
    }

    private SearchResult buildResult(int visited, Map<Point, Point> parents, Point end) {
        List<Point> path = new ArrayList<>();
        Point curr = end;
        while (curr != null) {
            path.add(curr);
            curr = parents.get(curr);
        }
        return new SearchResult(visited, path.size() - 1, path);
    }
}