class MinHeap{
	Edge[] edges;
	int num;
	MinHeap(int max_num){
		edges = new Edge[max_num];
		num = 0;
	}
	void insert(Edge edge){
		edges[num] = edge;
		num++;
		bubbleUp(num-1);
	}
	Edge extract_min(){
		Edge min = edges[0];
		edges[0] = edges[num-1];
		num--;
		bubbleDown(0);
		return min;
	}
	private void bubbleDown(int start){
		Edge tmp = edges[start];
		int index = start;
		while(index*2+1<num){
			int child = index*2+1;
			if(child+1<num && edges[child+1].compareTo(edges[child])<0) child++;
			if(tmp.compareTo(edges[child])>0){
				edges[index] = edges[child];
				index = child;
			}else{
				break;
			}
		}
		edges[index] = tmp;
	}
	private void bubbleUp(int start){
		Edge tmp = edges[start];
		int index = start;
		while(index>0){
			int parent = (index-1)/2;
			if(tmp.compareTo(edges[parent])<0){
				edges[index] = edges[parent];
				index = (index-1)/2;
			}else{
				break;
			}
		}
		edges[index] = tmp;
	}
}