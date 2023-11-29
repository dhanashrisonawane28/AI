import java.util.*;
class dfs_bfs_1 {
    
    public static void dfs_recursion(int node, boolean vis[], ArrayList<ArrayList<Integer>> adj,ArrayList<Integer> ls) {
        
        //marking visited
        vis[node] = true;
        ls.add(node);
        
        //neighbour nodes
        for(Integer it: adj.get(node)) {
            if(vis[it] == false) {
                dfs_recursion(it, vis, adj, ls);
            }
        }
    }
 
    public ArrayList<Integer> dfsOfGraph(int V, ArrayList<ArrayList<Integer>> adj,int start) {
        
        boolean vis[] = new boolean[V+1];
        vis[start] = true; 
        ArrayList<Integer> ls = new ArrayList<>();
        dfs_recursion(start, vis, adj, ls); 
        return ls; 
    }

    //BFS
    public ArrayList<Integer> bfs(int V, ArrayList<ArrayList<Integer>> adj, int startNode_bfs) {
        ArrayList<Integer> bfs = new ArrayList<>();
        boolean vis[] = new boolean[V];
        Queue<Integer> q = new LinkedList<>();
    
        q.add(startNode_bfs);
        vis[startNode_bfs] = true;
    
        while (!q.isEmpty()) { 
            Integer node = q.poll();
            bfs.add(node);
    
            for (Integer it : adj.get(node)) {
                if (!vis[it]) {
                    vis[it] = true;
                    q.add(it);
                }
            }
        }
        return bfs;
    }
    
    
    
    public static void main(String args[]) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of nodes: ");
        int numNodes = scanner.nextInt();
        
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        for (int i = 0; i < numNodes; i++) {
            adj.add(new ArrayList<>());
        }

        
        
        System.out.print("Enter the number of edges: ");
        int numEdges = scanner.nextInt();
        
        for (int i = 0; i < numEdges; i++) {
            System.out.print("Enter edge " + (i + 1) + " (source destination): ");
            int source = scanner.nextInt();
            int destination = scanner.nextInt();
            adj.get(source).add(destination);
            // Assuming the graph is undirected, you might want to add this line:
            adj.get(destination).add(source);
        }
        
        System.out.print("Enter the starting node for DFS: ");
        int startNode_dfs = scanner.nextInt();
        
        System.out.print("DFS: ");
        dfs_bfs_1 graphTraversal = new dfs_bfs_1();
        ArrayList<Integer> ans = graphTraversal.dfsOfGraph(numNodes, adj, startNode_dfs);
        int n = ans.size();
        for (int i = 0; i < n; i++) {
            System.out.print(ans.get(i) + " ");
        }
        System.out.println();
        
        System.out.print("Enter the starting node for BFS: ");
        int startNode_bfs = scanner.nextInt();
        
        System.out.print("BFS: ");
        dfs_bfs_1 s2 = new dfs_bfs_1();
        ArrayList<Integer> ans2 = s2.bfs(numNodes, adj, startNode_bfs);
        int n2 = ans2.size();
        for (int i = 0; i < n2; i++) {
            System.out.print(ans2.get(i) + " ");
        }
    }        
}