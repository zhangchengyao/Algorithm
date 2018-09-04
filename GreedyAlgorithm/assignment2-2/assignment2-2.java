import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

class Solution{
    public static void main(String[] args){
        HashMap<String, Integer> map = new HashMap<>();
        int num_vertices = 0;
        int num_bits = 0;
        Solution solution = new Solution();
        int[] parent = null;
        int[] size = null;
        int num_cluster = 0;
        try{
            BufferedReader br = new BufferedReader(new FileReader("clustering_big.txt"));
            String[] firstLine = br.readLine().split(" ");
            num_vertices = Integer.parseInt(firstLine[0]);
            num_bits = Integer.parseInt(firstLine[1]);
            String line;
            int i = 1;
            while((line=br.readLine())!=null){
                String input = line.replaceAll(" ","");
                if(!map.containsKey(input)) map.put(input, i);
                i++;
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        num_cluster = map.size();
        parent = new int[num_vertices];
        size = new  int[num_vertices];
        for(int i=0;i<num_vertices;i++){
            parent[i] = i+1;
            size[i] = 1;
        }
        Set<String> keys = map.keySet();
        Iterator<String> it =keys.iterator();
        while(it.hasNext()){
            String cur = it.next();
            int cur_v = map.get(cur);
            for(int i=0;i<num_bits;i++){
                StringBuilder tmp = new StringBuilder(cur);
                if(tmp.charAt(i)=='1') tmp.replace(i, i+1, "0");
                else tmp.replace(i, i+1, "1");
                num_cluster = solution.check(map,tmp.toString(), parent, size, cur_v, num_cluster);
            }
            for(int i=0;i<num_bits;i++){
                for(int j=i+1;j<num_bits;j++){
                    StringBuilder tmp = new StringBuilder(cur);
                    if(tmp.charAt(i)=='1') tmp.replace(i, i+1, "0");
                    else tmp.replace(i, i+1, "1");
                    if(tmp.charAt(j)=='1') tmp.replace(j, j+1, "0");
                    else tmp.replace(j, j+1, "1");
                    num_cluster = solution.check(map,tmp.toString(), parent, size, cur_v, num_cluster);
                }
            }
        }
        System.out.println(num_cluster);

    }
    private int find(int[] parent, int x){
        while(parent[x-1]!=x){
            parent[x-1] = parent[parent[x-1]-1];
            x = parent[x-1];
        }
        return x;
    }
    private void union(int[] parent, int[] size, int x, int y){
        int root1 = find(parent, x);
        int root2 = find(parent, y);
        if(root1==root2) return;
        if(size[root1-1]>size[root2-1]){
            parent[root2-1] = root1;
            size[root1-1] += size[root2-1];
        }else{
            parent[root1-1] = root2;
            size[root2-1] += size[root1-1];
        }
    }
    private int check(HashMap<String, Integer> map, String str, int[] parent, int[] size, int cur_v, int num_cluster){
        if(map.containsKey(str)){
            int v = map.get(str);
            if(find(parent, v)!=find(parent, cur_v)){
                union(parent, size, cur_v, v);
                num_cluster--;
            }
        }
        return num_cluster;
    }
}