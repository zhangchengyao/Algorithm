import java.io.*;
import java.util.*;

class Solution{
	Map<Integer, List<Integer>> edges = new HashMap<>();
	List<List<Integer>> backup = new ArrayList<>();

	public static void main(String[] args){
		Solution solution = new Solution();
		try{
			solution.createGraph("kargerMinCut.txt");
		}catch(IOException e){
			e.printStackTrace();
		}
		int n = solution.edges.size();
		int min = Integer.MAX_VALUE;
		for(int i=0;i<Math.log(n) * Math.pow(n, 2);i++){
			min = Math.min(min, solution.minCut());
			solution.recoverGraph();
			if(i%1001==0) System.out.println("i = " + i + "current min = " + min);
		}
		System.out.println(min);
	}
	public void recoverGraph(){
		for(int i=0;i<edges.size();i++){
			edges.put(i+1, new ArrayList<>(backup.get(i)));
		}
	}
	public void createGraph(String file) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line;
		while((line=br.readLine())!=null){
			String[] vertices = line.split("	");
			List<Integer> tmp = new ArrayList<>();
			for(int i=1;i<vertices.length;i++){
				tmp.add(Integer.parseInt(vertices[i]));
			}
			edges.put(Integer.parseInt(vertices[0]), new ArrayList(tmp));
			backup.add(new ArrayList(tmp));
		}
	}
	public int minCut(){
		Map<Integer, List<Integer>> dump = new HashMap<>(edges);
		int n = dump.size(); // num of vertices
		int v1 = 0; 
		int v2 = 0;
		for(int i=0;i<n-2;i++){
			v1 = (int)(Math.random()*dump.size());
			Set<Integer> keys = dump.keySet();
			Iterator<Integer> it = keys.iterator();
			for(int j=0;j<v1;j++){
				it.next();
			}
			v1 = it.next();
			v2 = dump.get(v1).get((int)(Math.random()*dump.get(v1).size()));
			while(dump.get(v1).contains(v2)) dump.get(v1).remove((Integer)v2);
			while(dump.get(v2).contains(v1)) dump.get(v2).remove((Integer)v1);
			// System.out.println("v1= "+v1+" v2= "+v2);
			while(!dump.get(v2).isEmpty()){
				int cur = dump.get(v2).remove(0);
				dump.get(v1).add(cur);
				dump.get(cur).add(v1);
				dump.get(cur).remove((Integer)v2);
			}
			dump.remove(v2);
		}
		return dump.get(v1).size();
	}
}