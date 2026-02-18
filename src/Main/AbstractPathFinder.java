package Main;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

abstract class AbstractPathFinder implements PathFinder {
    static final int dx[] = {0, 0, 1, -1};
    static final int dy[] = {1, -1, 0, 0};
    
	protected List<Point> getNeighbors(Point p, int[][] grid) {
        List<Point> neighbors = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            int nx = p.x + dx[i], ny = p.y + dy[i];
            if (nx >= 0 && nx < 8 && ny >= 0 && ny < 8 && grid[nx][ny] != 1) {
                neighbors.add(new Point(nx, ny));
            }
        }
        return neighbors;
    }

	protected SearchResult buildResult(int visited, Map<Point, Point> parents, Point end) {
        List<Point> path = new ArrayList<>();
        Point curr = end;
        while (curr != null) {
            path.add(curr);
            curr = parents.get(curr);
        }
        return new SearchResult(visited, path.size() - 1, path);
    }
}
