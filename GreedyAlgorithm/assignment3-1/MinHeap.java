class MinHeap{
	Tree[] data;
	int num;

	MinHeap(int max_num){
		data = new Tree[max_num];
		num = 0;
	}

	void insert(Tree t){
		data[num] = t;
		num++;
		bubbleUp(num-1);
	}

	Tree extract_min(){
		Tree t = data[0];
		data[0] = data[--num];
		bubbleDown(0);
		return t;
	}

	void bubbleUp(int start){
		Tree tmp = data[start];
		int index = start;
		while(index>0){
			int parent = (index-1)/2;
			if(tmp.weight<data[parent].weight){
				data[index] = data[parent];
				index = parent;
			}else{
				break;
			}
		}
		data[index] = tmp;
	}
	void bubbleDown(int start){
		Tree tmp = data[start];
		int index = start;
		while((index*2+1)<num){
			int child = index*2+1;
			if(child+1<num && data[child].weight>data[child+1].weight) child++;
			if(tmp.weight>data[child].weight){
				data[index] = data[child];
				index = child;
			}else{
				break;
			}
		}
		data[index] = tmp;
	}
}