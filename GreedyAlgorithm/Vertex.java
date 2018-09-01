import java.util.ArrayList;

class Vertex{
	int value;
	ArrayList<Edge> edges;
	Vertex(int value){
		this.value = value;
		edges = new ArrayList<>();
	}
	void addEdge(Edge e){
		edges.add(e);
	}
}