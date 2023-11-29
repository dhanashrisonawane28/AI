import java.util.*;

public class RomaniaMapAStar {

    private static Map<String, List<Neighbor>> romaniaMap = new HashMap<>();

    public static void main(String[] args) {
        initializeMap();

        String startCity = "Arad";
        String goalCity = "Bucharest";

        List<String> path = aStarSearch(romaniaMap, startCity, goalCity);

        if (path != null) {
            System.out.println("Path from " + startCity + " to " + goalCity + ": " + path);
        } else {
            System.out.println("No path found from " + startCity + " to " + goalCity);
        }
    }

    private static void initializeMap() {
        romaniaMap.put("Arad", Arrays.asList(
                new Neighbor("Zerind", 75),
                new Neighbor("Timisoara", 118),
                new Neighbor("Sibiu", 140)
        ));
    
        romaniaMap.put("Zerind", Arrays.asList(
                new Neighbor("Arad", 75),
                new Neighbor("Oradea", 71)
        ));
    
        romaniaMap.put("Timisoara", Arrays.asList(
                new Neighbor("Arad", 118),
                new Neighbor("Lugoj", 111)
        ));
    
        romaniaMap.put("Sibiu", Arrays.asList(
                new Neighbor("Arad", 140),
                new Neighbor("Oradea", 151),
                new Neighbor("Fagaras", 99),
                new Neighbor("Rimnicu Vilcea", 80)
        ));
    
        romaniaMap.put("Oradea", Arrays.asList(
                new Neighbor("Zerind", 71),
                new Neighbor("Sibiu", 151)
        ));
    
        romaniaMap.put("Lugoj", Arrays.asList(
                new Neighbor("Timisoara", 111),
                new Neighbor("Mehadia", 70)
        ));
    
        romaniaMap.put("Fagaras", Arrays.asList(
                new Neighbor("Sibiu", 99),
                new Neighbor("Bucharest", 211)
        ));
    
        romaniaMap.put("Rimnicu Vilcea", Arrays.asList(
                new Neighbor("Sibiu", 80),
                new Neighbor("Pitesti", 97),
                new Neighbor("Craiova", 146)
        ));
    
        romaniaMap.put("Mehadia", Arrays.asList(
                new Neighbor("Lugoj", 70),
                new Neighbor("Drobeta", 75)
        ));
    
        romaniaMap.put("Drobeta", Arrays.asList(
                new Neighbor("Mehadia", 75),
                new Neighbor("Craiova", 120)
        ));
    
        romaniaMap.put("Craiova", Arrays.asList(
                new Neighbor("Drobeta", 120),
                new Neighbor("Rimnicu Vilcea", 146),
                new Neighbor("Pitesti", 138)
        ));
    
        romaniaMap.put("Pitesti", Arrays.asList(
                new Neighbor("Rimnicu Vilcea", 97),
                new Neighbor("Craiova", 138),
                new Neighbor("Bucharest", 101)
        ));
    
        romaniaMap.put("Bucharest", Arrays.asList(
                new Neighbor("Fagaras", 211),
                new Neighbor("Pitesti", 101),
                new Neighbor("Urziceni", 85),
                new Neighbor("Giurgiu", 90)
        ));
    
        romaniaMap.put("Urziceni", Arrays.asList(
                new Neighbor("Bucharest", 85),
                new Neighbor("Vaslui", 142),
                new Neighbor("Hirsova", 98)
        ));
    
        romaniaMap.put("Giurgiu", Arrays.asList(
                new Neighbor("Bucharest", 90)
        ));
    
        romaniaMap.put("Vaslui", Arrays.asList(
                new Neighbor("Urziceni", 142),
                new Neighbor("Iasi", 92)
        ));
    
        romaniaMap.put("Hirsova", Arrays.asList(
                new Neighbor("Urziceni", 98),
                new Neighbor("Eforie", 86)
        ));
    
        romaniaMap.put("Eforie", Arrays.asList(
                new Neighbor("Hirsova", 86)
        ));
    
        romaniaMap.put("Iasi", Arrays.asList(
                new Neighbor("Vaslui", 92),
                new Neighbor("Neamt", 87)
        ));
    
        romaniaMap.put("Neamt", Arrays.asList(
                new Neighbor("Iasi", 87)
        ));
    }
    

    private static List<String> aStarSearch(Map<String, List<Neighbor>> graph, String startCity, String goalCity) {
        PriorityQueue<Node> openList = new PriorityQueue<>(Comparator.comparingInt(node -> node.fCost));
        Map<String, Integer> gCosts = new HashMap<>();
        Map<String, String> arrivedFrom = new HashMap<>();

        openList.add(new Node(startCity, 0, estimateCost(startCity, goalCity)));
        gCosts.put(startCity, 0);

        while (!openList.isEmpty()) {
            Node current = openList.poll();
            String currentCity = current.city;

            if (currentCity.equals(goalCity)) {
                return reconstructPath(arrivedFrom, currentCity);
                
            }

            for (Neighbor neighbor : graph.getOrDefault(currentCity, Collections.emptyList())) {
                String neighborCity = neighbor.city;
                int tentativeG = gCosts.get(currentCity) + neighbor.distance;

                if (!gCosts.containsKey(neighborCity) || tentativeG < gCosts.get(neighborCity)) {
                    gCosts.put(neighborCity, tentativeG);
                    arrivedFrom.put(neighborCity, currentCity);
                    int fCost = tentativeG + estimateCost(neighborCity, goalCity);
                    openList.add(new Node(neighborCity, tentativeG, fCost));
                }
            }
        }

        return null; // No path found
    }

    private static int estimateCost(String city, String goalCity) {
        // Define heuristic values for all cities
        Map<String, Integer> heuristicValues = new HashMap<>();
        heuristicValues.put("Arad", 366);
        heuristicValues.put("Zerind", 374);
        heuristicValues.put("Timisoara", 329);
        heuristicValues.put("Sibiu", 253);
        heuristicValues.put("Oradea", 380);
        heuristicValues.put("Lugoj", 244);
        heuristicValues.put("Fagaras", 176);
        heuristicValues.put("Rimnicu Vilcea", 193);
        heuristicValues.put("Mehadia", 241);
        heuristicValues.put("Drobeta", 242);
        heuristicValues.put("Craiova", 160);
        heuristicValues.put("Pitesti", 100);
        heuristicValues.put("Bucharest", 0); // Bucharest is the goal, so the heuristic value is 0
        heuristicValues.put("Urziceni", 80);
        heuristicValues.put("Giurgiu", 77);
        heuristicValues.put("Vaslui", 199);
        heuristicValues.put("Hirsova", 151);
        heuristicValues.put("Eforie", 161);
        heuristicValues.put("Iasi", 226);
        heuristicValues.put("Neamt", 234);
    
        // Return the heuristic value for the given city
        return heuristicValues.getOrDefault(city, 0);
    }
    

    private static List<String> reconstructPath(Map<String, String> arrivedFrom, String current) {
        List<String> path = new ArrayList<>();
        while (arrivedFrom.containsKey(current)) {
            path.add(current);
            current = arrivedFrom.get(current);
        }
        Collections.reverse(path);
        return path;
    }

    static class Neighbor {
        String city;
        int distance;

        Neighbor(String city, int distance) {
            this.city = city;
            this.distance = distance;
        }
    }

    static class Node {
        String city;
        int gCost; // Cost from the start node to this node
        int fCost; // gCost + heuristic estimate to the goal node

        Node(String city, int gCost, int fCost) {
            this.city = city;
            this.gCost = gCost;
            this.fCost = fCost;
        }
    }
}
