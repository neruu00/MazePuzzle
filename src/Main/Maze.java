package Main;

import javax.swing.*;
import java.awt.*;

import java.awt.event.*;

public class Maze extends JFrame {
    private static final long serialVersionUID = 1L;
    
	private final int SIZE = 8;
    private JButton[][] buttons = new JButton[SIZE][SIZE];
    private int[][] grid = new int[SIZE][SIZE]; // 0:Empty, 1:Wall
    private Point startNode = new Point(0, 0);
    private Point endNode = new Point(7, 7);
    

    private int editMode = 1; 
    private final String apiInfo = "... 1 : 벽 토글   2: 출발지 설정   3: 도착지 설정";
    private JLabel statusLabel = new JLabel("현재 모드: [1] 벽 토글"+apiInfo);

    public Maze() {
        setTitle("Maze Finder");
        setLayout(new BorderLayout());

        JPanel gridPanel = new JPanel(new GridLayout(SIZE, SIZE));
        
        /**
         * render Tile
         * 1. make tile JButton
         * 2. set Font Style
         * 3. add Event Listener
         */
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                int r = i, c = j;
                buttons[i][j] = new JButton();
                buttons[i][j].setFont(new Font("SansSerif", Font.BOLD, 12));
                buttons[i][j].addActionListener(e -> handleTileClick(r, c));
                gridPanel.add(buttons[i][j]);
            }
        }

        /**
         * Select Mode
         * 1: Empty/Wall Toggle
         * 2: Start Point
         * 3: End Point
         */
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_1: 
                    	editMode = 1; 
                    	statusLabel.setText("현재 모드: [1] 벽 토글"+apiInfo); 
                    	break;
                    case KeyEvent.VK_2: 
                    	editMode = 2; 
                    	statusLabel.setText("현재 모드: [2] 출발지 설정"+apiInfo); 
                    	break;
                    case KeyEvent.VK_3: 
                    	editMode = 3; 
                    	statusLabel.setText("현재 모드: [3] 도착지 설정"+apiInfo); 
                    	break;
                }
            }
        });
        this.setFocusable(true); // set Focus for Keyboard Event

        updateUI();

        // Control Panel
        JPanel controlPanel = new JPanel();
        JComboBox<String> algoBox = new JComboBox<>(new String[]{"BFS", "DFS"});
        JButton runBtn = new JButton("탐색 시작");
        runBtn.addActionListener(e -> runSolver(algoBox.getSelectedIndex() == 0 ? new BFSFinder() : new DFSFinder()));
        
        controlPanel.add(algoBox);
        controlPanel.add(runBtn);

        add(statusLabel, BorderLayout.NORTH);
        add(gridPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);

        setSize(700, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    /**
     *  Set Mode
     * FIXME - tile toggle issue
     * mode 1 toggle tile when tile state start point or end point
     * It's unintended function, but program works well
     * so, I didn't fix it
     */
    private void handleTileClick(int r, int c) {
        if (editMode == 1) grid[r][c] = grid[r][c] == 1 ? 0 : 1;
        else if (editMode == 2) { startNode = new Point(r, c); grid[r][c] = 0; }
        else if (editMode == 3) { endNode = new Point(r, c); grid[r][c] = 0; }
        updateUI();
        this.requestFocusInWindow(); // Keyboard focus again after Click event
    }

    /**
     * why set color didn't work? ass hole
     */
    private void updateUI() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (i == startNode.x && j == startNode.y) {
                    buttons[i][j].setBackground(Color.GREEN);
                    buttons[i][j].setText("START");
                } else if (i == endNode.x && j == endNode.y) {
                    buttons[i][j].setBackground(Color.RED);
                    buttons[i][j].setText("END");
                } else if (grid[i][j] == 1) {
                    buttons[i][j].setBackground(Color.DARK_GRAY);
                    buttons[i][j].setText("WALL");
                    buttons[i][j].setForeground(Color.BLACK);
                } else {
                    buttons[i][j].setBackground(Color.WHITE);
                    buttons[i][j].setText("");
                }
            }
        }
    }

    private void runSolver(PathFinder finder) {
        updateUI(); // initialize last path
        SearchResult result = finder.findPath(grid, startNode, endNode);
        if (result != null) {
            for (Point p : result.path) {
                if (!p.equals(startNode) && !p.equals(endNode)) {
                    buttons[p.x][p.y].setBackground(Color.YELLOW);
                    buttons[p.x][p.y].setText("●");
                }
            }
            JOptionPane.showMessageDialog(this, "방문 노드: " + result.visitedCount + ", 최단 거리: " + result.distance);
        } else {
            JOptionPane.showMessageDialog(this, "도착할 수 없습니다.");
        }
    }

    public static void main(String[] args) {
        new Maze();
    }
}