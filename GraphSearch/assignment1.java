import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Solution {
    static class Graph{
        ArrayList<ArrayList<Integer>> original_edges;
        ArrayList<ArrayList<Integer>> reversed_edges;
        ArrayList<ArrayList<Integer>> modified_edges;
        ArrayList<Boolean> status;
        int[] finishing_time;
        int[] leader;
        int t = 0;

        public Graph(String filename, int num_vertices){
            original_edges = new ArrayList<>(num_vertices);
            reversed_edges = new ArrayList<>(num_vertices);
            modified_edges = new ArrayList<>(num_vertices);
            status = new ArrayList<>(num_vertices);
            for(int i=0;i<num_vertices;i++){
                original_edges.add(new ArrayList<>());
                reversed_edges.add(new ArrayList<>());
                modified_edges.add(new ArrayList<>());
                status.add(false);
            }
            finishing_time = new int[num_vertices];
            leader = new int[num_vertices];
            initGraph(filename);
        }
        private void initGraph(String filename){
            String line;
            int from, to;
            try{
                BufferedReader br = new BufferedReader(new FileReader(filename));
                while((line=br.readLine())!=null){
                    String[] items = line.trim().split(" ");
                    from = Integer.parseInt(items[0]);
                    to = Integer.parseInt(items[1]);
                    original_edges.get(from-1).add(to);
                    reversed_edges.get(to-1).add(from);
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }

        public void computeSCCs(){
            DFS_Loop(true);
            modifyGraph();
            for(int i=0;i<status.size();i++){
                status.set(i, false);
            }
            DFS_Loop(false);

            HashMap<Integer, Integer> count = new HashMap<>();
            for(int i=0;i<leader.length;i++){
                if(!count.containsKey(leader[i])){
                    count.put(leader[i], 1);
                }else{
                    count.put(leader[i], count.get(leader[i])+1);
                }
            }
            Collection<Integer> sccs = count.values();
            int[] res = new int[5];
            for(Integer item:sccs){
                int i = 0;
                while(i<5 && item<res[i]) i++;
                if(i<5){
                    for(int j=4;j>i;j--){
                        res[j] = res[j-1];
                    }
                    res[i] = item;
                }
            }
            for(int i=0;i<5;i++){
                System.out.println(res[i]);
            }
        }

        private void modifyGraph(){
            // replace original labels with finishing time
            for(int i=0;i<finishing_time.length;i++){
                modified_edges.set(finishing_time[i]-1, original_edges.get(i));
            }
            for(ArrayList<Integer> v:modified_edges){
                for(int i=0;i<v.size();i++){
                    v.set(i, finishing_time[v.get(i)-1]);
                }
            }
        }

        private void DFS_Loop(boolean reverse){
            for(int i = original_edges.size(); i>0; i--){
                if(!status.get(i-1)){
                    DFS(i, reverse);
                }
            }
        }

        private void DFS(int startNode, boolean reverse){
            Stack<Integer> stack = new Stack<>();
            stack.push(startNode);
            while(!stack.isEmpty()){
                int cur = stack.peek();
                status.set(cur-1, true);
                leader[cur-1] = startNode;
                ArrayList<Integer> adjs;
                if(!reverse){
                    adjs = modified_edges.get(cur-1);
                }else{
                    adjs = reversed_edges.get(cur-1);
                }
                for (Integer adj : adjs) {
                    if (!status.get(adj - 1)) {
                        stack.push(adj);
                    }
                }
                if(cur==stack.peek()){
                    stack.pop();
                    if(finishing_time[cur-1]==0){
                        t++;
                        finishing_time[cur-1] = t;
                    }
                }
            }
        }
    }

    public static void main(String[] args){
        Graph graph = new Graph("SCC.txt", 875714);
        graph.computeSCCs();
    }
}
