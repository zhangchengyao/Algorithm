class Edge{
	Vertex tail;
	Vertex head;
	int cost;

	Edge(Vertex tail, Vertex head, int cost){
		this.tail = tail;
		this.head = head;
		this.cost = cost;
	}

	int compareTo(Edge e){
		if(this.cost>=e.cost){
			return 1;
		}else{
			return -1;
		}
	}
}