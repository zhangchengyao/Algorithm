import java.io.*;
import java.util.*;

class Solution{
	List<List<Integer>> edges = new ArrayList<>(200);
	public static void main(String[] args){
		Solution solution = new Solution();
		try{
			solution.createGraph("kargerMinCut.txt");
		}catch(IOException e){
			e.printStackTrace();
		}
		solution.minCut();
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
			edges.add(new ArrayList(tmp));
		}
	}
	public void minCut(){
		List<List<Integer>> dump = new ArrayList(edges);
		int v1 = (int)(Math.random()*dump.size());
		int v2;
		while((v2=(int)(Math.random()*dump.size()))==v1){}
		
	}
}