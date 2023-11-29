import java.util.List;
import java.util.ArrayList;

public class RatMazeDfs {

    static int[][] maze;
    static int N, M;

    public static List<int[]> findPath(int[][] ipMaze) {
        maze = ipMaze;
        N = maze.length;
        M = maze[0].length;

        List<int[]> path = new ArrayList<>();
        boolean[][] visited = new boolean[N][M];

        if (dfs(0, 0, path, visited)) {
            return path;
        }
        return null;
    }

    public static boolean dfs(int row, int col, List<int[]> path, boolean[][] visited) {
        // check row , col in bound and block, visited
        if (row < 0 || col < 0 || row >= N || col >= M || maze[row][col] == 1 || visited[row][col]) {
            return false;
        }

        // add to path if 0,0 and mark visited
        path.add(new int[] { row, col });
        visited[row][col] = true;

        if (row == N - 1 && col == M - 1) {
            return true;
        }
        if (dfs(row + 1, col, path, visited) || dfs(row, col + 1, path, visited) || dfs(row - 1, col, path, visited)
                || dfs(row, col - 1, path, visited)) {
            return true;
        }

        path.remove(path.size() - 1);// remove last visited cell as it's not in path
        return false;
    }

    public static void main(String[] args) {
        int[][] maze = {
                { 0, 1, 0, 0 },
                { 0, 0, 1, 0 },
                { 0, 0, 0, 0 },
                { 1, 0, 0, 0 },
        };

        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                System.out.print(" " + maze[i][j] + " ");
            }
            System.out.println();
        }

        List<int[]> path = findPath(maze);
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
