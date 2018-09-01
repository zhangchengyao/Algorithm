class Edge{
	int tail;
	int head;
	int cost;
	Edge(int tail, int head, int cost){
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