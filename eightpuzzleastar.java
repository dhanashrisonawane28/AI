import java.util.*;

//A* Search Algorithm for 8-Puzzle Problem
class Node implements Comparable<Node> {
    int[][] puzzle;
    Node parent; // Parent node in search tree
    String action; // Action taken to reach this node
    int costSoFar; // Cost to reach this node
    int estimatedCostToGoal; // Heuristic value
    int totalCost;

    public Node(int[][] puzzle, Node parent, String action) {
        this.puzzle = puzzle;
        this.parent = parent;
        this.action = action;
        this.costSoFar = parent == null ? 0 : parent.costSoFar + 1;
        this.estimatedCostToGoal = calculateHeuristic();
        this.totalCost = costSoFar + estimatedCostToGoal;
    }

    // Calculate heuristic value by counting number of misplaced tiles
    private int calculateHeuristic() {
        int totalDistance = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int value = puzzle[i][j];
                if (value != 0) {
                    int targetRow = (value - 1) / 3;
                    int targetCol = (value - 1) % 3;
                    totalDistance += Math.abs(i - targetRow) + Math.abs(j - targetCol);
                }
            }
        }
        return totalDistance;
    }

    public int compareTo(Node other) {
        // Compare total cost of each node to determine priority
        return Integer.compare(this.totalCost, other.totalCost);
    }
}

public class eightpuzzleastar {

    public static void main(String[] args) {

        int[][] initial = { { 2, 8, 3 }, { 1, 6, 4 }, { 7, 0, 5 } };
        int[][] goal = { { 1, 2, 3 }, { 8, 0, 4 }, { 7, 6, 5 } };
        System.out.println("Initial State:");
        print(initial);

        // Solve puzzle
        Node solution = solve(initial, goal);

        // Print solution path if found
        if (solution != null) {
            System.out.println("\nMoves:");
            List<String> moves = getMoveList(solution);
            for (String move : moves) {
                System.out.println("Move " + move);
            }

            System.out.println("\nGoal State:");
            print(goal);
        } else {
            System.out.println("No solution found");
        }
    }

    // Print puzzle grid
    static void print(int[][] puzzle) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(puzzle[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Solve puzzle using A* search
    public static Node solve(int[][] initial, int[][] goal) {
        // Initialize open and closed sets
        PriorityQueue<Node> openSet = new PriorityQueue<>();
        Set<String> closedSet = new HashSet<>();

        // Add initial node to open set
        Node startNode = new Node(initial, null, "");
        openSet.add(startNode);
        // Search loop for finding goal state
        while (!openSet.isEmpty()) {
            Node current = openSet.poll();
            closedSet.add(Arrays.deepToString(current.puzzle));

            if (Arrays.deepEquals(current.puzzle, goal)) {
                return current;
            }
            // Get blank tile position
            int zeroRow = 0, zeroCol = 0;

            outer: for (zeroRow = 0; zeroRow < 3; zeroRow++) {
                for (zeroCol = 0; zeroCol < 3; zeroCol++) {
                    if (current.puzzle[zeroRow][zeroCol] == 0) {
                        break outer;
                    }
                }
            }
            // Try moving blank tile in each direction
            int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
            String[] actions = { "Up", "Down", "Left", "Right" };
            for (int i = 0; i < 4; i++) {
                int newRow = zeroRow + directions[i][0];
                int newCol = zeroCol + directions[i][1];
                if (isValid(newRow, newCol)) {
                    // Create new puzzle state and add to open set if not visited before
                    int[][] newPuzzle = copyPuzzle(current.puzzle);
                    swapTiles(newPuzzle, zeroRow, zeroCol, newRow, newCol);
                    Node child = new Node(newPuzzle, current, actions[i]);
                    if (!closedSet.contains(Arrays.deepToString(newPuzzle))) {
                        openSet.add(child);
                    }
                }
            }
        }
        return null;
    }

    // Get list of moves from goal state to initial state by traversing parents
    public static List<String> getMoveList(Node solutionNode) {
        List<String> moves = new ArrayList<>();
        Node curr = solutionNode;
        // Traverse parents until initial state is reached
        while (curr.parent != null) {
            moves.add(curr.action);
            curr = curr.parent;
        }
        // Reverse list to get moves from initial state to goal state
        Collections.reverse(moves);
        return moves;
    }

    // Check if row and col are valid indices
    public static boolean isValid(int row, int col) {
        return row >= 0 && row < 3 && col >= 0 && col < 3;
    }

    // Make copy of puzzle state to avoid modifying original
    public static int[][] copyPuzzle(int[][] puzzle) {
        int[][] copy = new int[3][3];
        for (int i = 0; i < 3; i++) {
            System.arraycopy(puzzle[i], 0, copy[i], 0, 3);
        }
        return copy;
    }

    // Swap two tiles in the puzzle grid
    public static void swapTiles(int[][] puzzle, int row1, int col1, int row2, int col2) {
        int temp = puzzle[row1][col1];
        puzzle[row1][col1] = puzzle[row2][col2];
        puzzle[row2][col2] = temp;
    }
}