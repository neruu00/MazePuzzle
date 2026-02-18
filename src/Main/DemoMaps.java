package Main;

import java.awt.Point;

public class DemoMaps {
    
    public static class MapData {
        public int[][] grid;
        public Point start;
        public Point end;

        public MapData(int[][] grid, Point start, Point end) {
            this.grid = grid;
            this.start = start;
            this.end = end;
        }
    }

    public static MapData getSpiralMap() {
        int[][] grid = {
            {0,0,0,0,0,0,0,0},
            {0,1,1,1,1,1,1,0},
            {0,1,0,0,0,0,1,0},
            {0,1,0,1,1,0,1,0},
            {0,1,0,1,0,0,1,0},
            {0,1,0,1,1,1,1,0},
            {0,1,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0}
        };
        return new MapData(grid, new Point(0, 0), new Point(4, 4));
    }

    public static MapData getObstacleMap() {
        int[][] grid = new int[8][8];
        for(int i = 1; i < 7; i++) grid[3][i] = 1; // 가로막는 벽
        return new MapData(grid, new Point(1, 1), new Point(6, 6));
    }
}