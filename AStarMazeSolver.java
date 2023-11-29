import java.util.*;

class Node {
    int x, y; // Coordinates of the cell
    int cost; // Cost to reach this cell from the start
    int heuristic; // Heuristic cost (estimated cost to the destination)
    Node parent; // Parent node in the path

    public Node(int x, int y, int cost, int heuristic, Node parent) {
        this.x = x;
        this.y = y;
        this.cost = cost;
        this.heuristic = heuristic;
        this.parent = parent;
    }
}

public class AStarMazeSolver {
    public static void main(String[] args) {
        char[][] maze = {
            {'S', '0', '1', '0', '0'},
            {'0', '1', '0', '1', '0'},
            {'0', '0', '0', '0', '1'},
            {'0', '1', '0', '0', '0'},
            {'0', '0', '0', '1', 'D'}
        };

        List<Node> path = findPath(maze);
        if (path != null) {
            System.out.println("Shortest path found:");
            for (Node node : path) {
                System.out.println("(" + node.x + ", " + node.y + ")");
            }
        } else {
            System.out.println("No path found.");                                                        
        }   
    }

    public static List<Node> findPath(char[][] maze) {
        int rows = maze.length;
        int cols = maze[0].length;
        
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // Up, Down, Left, Right
        
        PriorityQueue<Node> openSet = new PriorityQueue<>((a, b) -> (a.cost + a.heuristic) - (b.cost + b.heuristic));
        Map<String, Node> closedSet = new HashMap<>();
        
        Node startNode = null, endNode = null;
        
        // Find the start and destination nodes
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (maze[i][j] == 'S') {
                    startNode = new Node(i, j, 0, 8, null);
                    openSet.offer(startNode);
                } else if (maze[i][j] == 'D') {
                    endNode = new Node(i, j, 0, 0, null);
                }
            }
        }
        
        while (!openSet.isEmpty()) {
            Node current = openSet.poll();
            if (current.x == endNode.x && current.y == endNode.y) {
                // Destination reached; reconstruct the path
                List<Node> path = new ArrayList<>();
                while (current != null) {
                    path.add(current);
                    current = current.parent;
                }
                Collections.reverse(path);
                return path;
            }
            
            String currentKey = current.x + "-" + current.y;
            closedSet.put(currentKey, current);
            
            for (int[] dir : directions) {
                int newX = current.x + dir[0];
                int newY = current.y + dir[1];
                
                if (newX >= 0 && newX < rows && newY >= 0 && newY < cols && maze[newX][newY] != '1') {
                    String neighborKey = newX + "-" + newY;
                    if (!closedSet.containsKey(neighborKey)) {
                        boolean isRightObstacle = (newY + 1 < cols && maze[newX][newY + 1] == '1');
                        boolean isDownObstacle = (newX + 1 < rows && maze[newX + 1][newY] == '1');
                        
                        if (!isRightObstacle || !isDownObstacle) {
                            int newCost = current.cost + 1;
                            int heuristic = Math.abs(newX - endNode.x) + Math.abs(newY - endNode.y);
                            Node neighbor = new Node(newX, newY, newCost, heuristic, current);
                            openSet.offer(neighbor);
                        }
                    }
                }
            }
        }
        // No path found
        return null;
    }
}
