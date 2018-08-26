import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Solution {
    static class Node{
        int value;
        int weight;
        Node(int value, int weight){
            this.value = value;
            this.weight = weight;
        }
    }
    static class Vertex{
        int value;
        LinkedList<Node> edges;
        Vertex(int value){
            this.value = value;
            edges = new LinkedList<>();
        }
        void addNode(Node node){
            edges.add(node);
        }
    }

    static class Graph{
        ArrayList<Vertex> vertices;
        int[] shortestPath;

        public Graph(String filname, int num_vertices){
            vertices = new ArrayList<>();
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
                    Vertex vertex = new Vertex(from);
                    for(int i=1;i<items.length;i++){
                        String[] e = items[i].split(",");
                        to = Integer.parseInt(e[0]);
                        weight = Integer.parseInt(e[1]);
                        vertex.addNode(new Node(to, weight));
                    }
                    vertices.add(vertex);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public int[] Dijkstra(int startingVertex){
            HashSet<Integer> X = new HashSet<>();
            MinHeap heap = new MinHeap(shortestPath.length);
            HashMap<Integer, Integer> labelToIndex = new HashMap<>();
            HashMap<Integer, Integer> indexToLabel = new HashMap<>();
            Vertex v = vertices.get(startingVertex-1);
            for(Node node:v.edges){
                int index = heap.insert(node.weight, labelToIndex, indexToLabel);
                labelToIndex.put(node.value, index);
                indexToLabel.put(index, node.value);
            }
            X.add(startingVertex);
            for(int i=1;i<shortestPath.length;i++){
                int cur = indexToLabel.get(0);
                shortestPath[cur-1] = heap.extract_min(labelToIndex, indexToLabel);
                X.add(cur);
                v = vertices.get(cur-1);
                for(Node node:v.edges){
                    if(!X.contains(node.value)){
                        int dijkstra_greedy_score = shortestPath[cur-1] + node.weight;
                        int index;
                        if(labelToIndex.containsKey(node.value)){
                            int key = heap.delete(labelToIndex.get(node.value), labelToIndex, indexToLabel);
                            index = heap.insert(Math.min(key, dijkstra_greedy_score), labelToIndex, indexToLabel);
                        }else{
                            index = heap.insert(dijkstra_greedy_score, labelToIndex, indexToLabel);
                        }
                        labelToIndex.put(node.value, index);
                        indexToLabel.put(index, node.value);
                    }
                }
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
