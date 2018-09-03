import java.io.*;

class Solution{
	public static void main(String[] args){
		Vertex[] vertices = null;
		int[] parent = null;
		int[] size = null;
		MinHeap mh = null;
		int cluster = 0;
		Solution solution = new Solution();
		try{
			BufferedReader br = new BufferedReader(new FileReader("clustering1.txt"));
			int num_nodes = Integer.parseInt(br.readLine());
			vertices = new Vertex[num_nodes];
			parent = new int[num_nodes];
			size = new int[num_nodes];
			mh = new MinHeap((num_nodes+1)*num_nodes/2);
			cluster = num_nodes;
			String line;
			while((line = br.readLine())!=null){
				String[] edge = line.split(" ");
				int tail = Integer.parseInt(edge[0]);
				int head = Integer.parseInt(edge[1]);
				int cost = Integer.parseInt(edge[2]);
				if(vertices[tail-1]==null) vertices[tail-1] = new Vertex(tail);
				if(vertices[head-1]==null) vertices[head-1] = new Vertex(head);
				vertices[tail-1].addEdge(new Edge(vertices[tail-1], vertices[head-1], cost));
			}
			for(int i=0;i<num_nodes;i++){
				parent[i] = i+1;
				size[i] = 1;
			}
			for(int i=0;i<num_nodes;i++){
				Vertex v = vertices[i];
				for(Edge e:v.edges){
					mh.insert(e);
				}
			}
		}catch(IOException e){
			e.printStackTrace();
		}
		Edge edge = null;
		while(cluster>4){
			edge = mh.extract_min();
			while(solution.find(parent, edge.tail.label)==solution.find(parent, edge.head.label)){
				edge = mh.extract_min();
			}
			solution.union(parent, size, edge.tail.label, edge.head.label);
			cluster--;
		}
		edge = mh.extract_min();
		while(solution.find(parent, edge.tail.label)==solution.find(parent, edge.head.label)){
			edge = mh.extract_min();
		}
		System.out.println(edge.cost);
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
}