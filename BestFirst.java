import java.util.*;
class BestFirst{
    // A Node class for GBFS Pathfinding
    static class Node{
        int v, weight;
        Node(int v, int weight){
            this.v=v;
            this.weight=weight;
        }
    }
    // pathNode class will help to store
    // the path from src to dest.
    static class pathNode{
        int node;
        pathNode parent;
        pathNode(int node, pathNode parent){
            this.node=node;
            this.parent=parent;
        }
    }

    // Function to add edge in the graph.
    static void addEdge(int u, int v, int weight){
        // Add edge u -> v with weight weight.
        adj.get(u).add(new Node(v, weight));
    }

    // Declaring the adjacency list;
    static List<List<Node>> adj;
    // Greedy best first search algorithm function
    private static List<Integer> GBFS(int h[]
            , int V, int src, int dest){
        /* This function returns a list of
            integers that denote the shortest
            path found using the GBFS algorithm.
            If no path exists from src to dest, we will return an empty list.
         */
        // Initializing openList and closeList.
        List<pathNode> openList = new ArrayList<>();
        List<pathNode> closeList = new ArrayList<>();

        // Inserting src in openList.
        openList.add(new pathNode(src, null));

        // Iterating while the openList
        // is not empty.

        while(!openList.isEmpty()){

            pathNode currentNode = openList.get(0);
            int currentIndex = 0;
            // Finding the node with the least 'h' value.

            for(int i = 0; i < openList.size(); i++){
                if(h[openList.get(i).node] <
                        h[currentNode.node]){
                    currentNode = openList.get(i);
                    currentIndex = i;
                }
            }

            // Removing the currentNode from
            // the openList and adding it in
            // the closeList.
            openList.remove(currentIndex);
            closeList.add(currentNode);

            // If we have reached the destination node.
            if(currentNode.node == dest){
                // Initializing the 'path' list.
                List<Integer> path = new ArrayList<>();
                pathNode cur = currentNode;

                // Adding all the nodes in the
                // path list through which we have
                // reached to dest.
                while(cur != null){
                    path.add(cur.node);
                    cur = cur.parent;
                }

                // Reversing the path, because
                // currently it denotes path
                // from dest to src.
                Collections.reverse(path);
                return path;
            }

            // Iterating over adjacents of 'currentNode'
            // and adding them to openList if
            // they are neither in openList or closeList.
            for(Node node: adj.get(currentNode.node)){
                for(pathNode x : openList){
                    if(x.node == node.v) continue;
                }
                for(pathNode x : closeList){
                    if(x.node == node.v) continue;
                }
                openList.add(new pathNode(node.v, currentNode));
            }
        }

        return new ArrayList<>();
    }
    public static void main(String args[]){
        // Initializing the adjacency list.
        adj=new ArrayList<>();

        /* Making the following graph
                    src = 0
                   / | \
                  /  |  \
                 1   2   3
                /\   |   /\
               /  \  |  /  \
              4    5 6 7   8
                      /
                     /
                   dest = 9
        */
        // Total number of vertices.
        int V = 10;
        for(int i = 0; i < V; i++)
            adj.add(new ArrayList<>());

        addEdge(0, 1, 2);
        addEdge(0, 2, 1);
        addEdge(0, 3, 10);
        addEdge(1, 4, 3);
        addEdge(1, 5, 2);
        addEdge(2, 6, 9);
        addEdge(3, 7, 5);
        addEdge(3, 8, 2);
        addEdge(7, 9, 5);

        // Defining the heuristic values for each node.
        int h[] = {20, 22, 21, 10,
                25, 24, 30, 5, 12, 0};
        List<Integer> path = GBFS(h, V, 0, 9);
        for(int i = 0; i < path.size() - 1; i++){
            System.out.print(path.get(i)+" --> ");
        }
        System.out.println(path.get(path.size()-1));
    }
}
