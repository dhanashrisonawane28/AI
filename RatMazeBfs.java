import java.util.*;

public class RatMazeBfs {

    static int[][] maze;
    static int N, M;

    public static Set<int[]> findPath(int[][] ipMaze) {
        maze = ipMaze;
        N = maze.length;
        M = maze[0].length;

        // List<int[]> path = new ArrayList<>();
        Set<int[]> path = new LinkedHashSet<>();
        boolean[][] visited = new boolean[N][M];

        if (bfs(0, 0, path, visited)) {
            return (path);
        }
        return null;
    }

    public static boolean bfs(int row, int col, Set<int[]> path, boolean[][] visited) {

        if (row < 0 || col < 0 || row >= N || col >= M || maze[row][col] == 1 || visited[row][col]) {
            return false;
        }

        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] { row, col });
        visited[row][col] = true;

        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            row = cell[0];
            col = cell[1];

            if (row == N - 1 && col == M - 1) {
                return true;
            }

            int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
            for (int[] direction : directions) {
                int nextRow = row + direction[0];
                int nextCol = col + direction[1];

                if (isValidCell(nextRow, nextCol) && !visited[nextRow][nextCol]) {
                    queue.add(new int[] { nextRow, nextCol });
                    path.add(new int[] { row, col });
                    visited[nextRow][nextCol] = true;
                }
            }
        }
        path.clear();
        return false;
    }

    private static boolean isValidCell(int row, int col) {
        return row >= 0 && row < N && col >= 0 && col < M && maze[row][col] == 0;
    }

    // public static boolean bfs(int row, int col, Set<int[]> path, boolean[][]
    // visited) {

    // if (row < 0 || col < 0 || row >= N || col >= M || maze[row][col] == 1 ||
    // visited[row][col]) {
    // return false;
    // }
    // Queue<int[]> queue = new LinkedList<>();
    // queue.add(new int[] { row, col });
    // visited[row][col] = true;

    // while (!queue.isEmpty()) {
    // int[] cell = queue.poll();
    // row = cell[0];
    // col = cell[1];

    // if (row == N - 1 && col == M - 1) {
    // return true;
    // }

    // if (row + 1 < N && maze[row + 1][col] == 0 && !visited[row + 1][col]) {
    // queue.add(new int[] { row + 1, col });
    // path.add(cell);
    // visited[row + 1][col] = true;
    // }

    // if (col + 1 < M && maze[row][col + 1] == 0 && !visited[row][col + 1]) {
    // queue.add(new int[] { row, col + 1 });
    // path.add(cell);
    // visited[row][col + 1] = true;
    // }

    // if (row - 1 >= 0 && maze[row - 1][col] == 0 && !visited[row - 1][col]) {
    // queue.add(new int[] { row - 1, col });
    // path.add(cell);
    // visited[row - 1][col] = true;
    // }

    // if (col - 1 >= 0 && maze[row][col - 1] == 0 && !visited[row][col - 1]) {
    // queue.add(new int[] { row, col - 1 });
    // path.add(cell);
    // visited[row][col - 1] = true;
    // }
    // }

    // path.clear();
    // return false;
    // }

    public static void main(String[] args) {
        int[][] maze = {
                { 0, 1, 0, 0 },
                { 0, 1, 1, 0 },
                { 0, 0, 1, 0 },
                { 1, 0, 0, 0 },
        };

        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                System.out.print(" " + maze[i][j] + " ");
            }
            System.out.println();
        }

        Set<int[]> path = findPath(maze);
        if (path != null) {
            System.out.println("Path found!!");
            for (int[] temp : path) {
                System.out.println(temp[0] + "," + temp[1]);
            }
        } else {
            System.out.println("Path Not Found!");
        }
    }

}
