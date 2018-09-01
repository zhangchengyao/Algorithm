import java.io.*;
import java.util.ArrayList;

class Solution{
	public static void main(String[] args){
		try{
			BufferedReader br = new BufferedReader(new FileReader("edges.txt"));
			String[] firstLine = br.readLine().split(" ");
			int num_nodes = Integer.parseInt(firstLine[0]);
			int num_edges = Integer.parseInt(firstLine[1])*2;
			MinHeap mh = new MinHeap(num_edges);
			Vertex[] vertices = new Vertex[num_nodes];
			String line;
			while((line=br.readLine())!=null){
				String[] info = line.split(" ");
				int tail = Integer.parseInt(info[0]);
				int head = Integer.parseInt(info[1]);
				if(vertices[tail-1]==null) vertices[tail-1] = new Vertex(tail);
				if(vertices[head-1]==null) vertices[head-1] = new Vertex(head);
				vertices[tail-1].addEdge(new Edge(tail, head, Integer.parseInt(info[2])));
				vertices[head-1].addEdge(new Edge(head, tail, Integer.parseInt(info[2])));
			}
			long cost = 0;
			ArrayList<Integer> X = new ArrayList<>();
			int current_vertex = 1;
			X.add(current_vertex);
			ArrayList<Edge> edges = vertices[current_vertex-1].edges;
			for(Edge e:edges){
				mh.insert(e);
			}
			for(int i=1;i<num_nodes;i++){
				Edge current_edge = null;
				while(X.contains(current_vertex)){
					current_edge = mh.extract_min();
					current_vertex = current_edge.head;
				}
				cost += current_edge.cost;
				X.add(current_vertex);
				edges = vertices[current_vertex-1].edges;
				for(Edge e:edges){
					mh.insert(e);
				}
			}
			System.out.println(cost);
		}catch(IOException exception){
			exception.printStackTrace();
		}
	}	
}