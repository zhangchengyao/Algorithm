import java.util.ArrayList;

class Vertex{
	int label;
	ArrayList<Edge> edges;

	Vertex(int label){
		this.label = label;
		edges = new ArrayList<>();
	}

	void addEdge(Edge e){
		edges.add(e);
	}
}