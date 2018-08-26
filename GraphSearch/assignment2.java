import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Solution {
    static class Edge{
        int tail;
        int head;
        int weight;

        public Edge(int tail, int head, int weight){
            this.tail = tail;
            this.head = head;
            this. weight = weight;
        }
    }
    static class Graph{
        ArrayList<Edge> edges;
        int[] shortestPath;

        public Graph(String filname, int num_vertices){
            edges = new ArrayList<>();
            shortestPath = new int[num_vertices];
            initGraph(filname);
        }
        private void initGraph(String filename){
            try {
                BufferedReader br = new BufferedReader(new FileReader(filename));
                String line;
                while((line=br.readLine())!=null){
                    String[] items = line.trim().split("\t");
                    int from = Integer.parseInt(items[0]);
                    int to;
                    int weight;
                    for(int i=1;i<items.length;i++){
                        String[] e = items[i].split(",");
                        to = Integer.parseInt(e[0]);
                        weight = Integer.parseInt(e[1]);
                        addEdge(from, to, weight);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        void addEdge(int from, int to, int weight){
            edges.add(new Edge(from, to, weight));
        }

        public int[] Dijkstra(int startingVertex){
            HashSet<Integer> X = new HashSet<>();
            X.add(startingVertex);
            for(int i=1;i<shortestPath.length;i++){
                int min = 1000000;
                int cur = 0;
                for(Edge e:edges){
                    if(X.contains(e.tail) && !X.contains(e.head)){
                        // crossing edges
                        int dijkstra_greedy_score = shortestPath[e.tail-1] + e.weight;
                        if(dijkstra_greedy_score<min){
                            shortestPath[e.head-1] = dijkstra_greedy_score;
                            min = dijkstra_greedy_score;
                            cur = e.head;
                        }
                    }
                }
                X.add(cur);
            }
            return shortestPath;
        }
    }

    public static void main(String[] args){
        Graph graph = new Graph("dijkstraData.txt",200);
        int[] targets = {7,37,59,82,99,115,133,165,188,197};
        int[] shortestPath = graph.Dijkstra(1);
        for(int i=0;i<targets.length;i++){
            System.out.print(shortestPath[targets[i]-1]+",");
        }
    }
}
