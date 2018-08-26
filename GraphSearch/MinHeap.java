import java.util.HashMap;

public class MinHeap {
    private int[] data;
    private int max_num;
    private int num;

    public MinHeap(int max_num){
        this.max_num = max_num;
        data = new int[max_num];
        num = 0;
    }

    public int insert(int val, HashMap<Integer, Integer> labelToIndex, HashMap<Integer, Integer> indexToLabel){
        data[num] = val;
        num++;
        return bubble_up(num-1, labelToIndex, indexToLabel);
    }

    public int delete(int index, HashMap<Integer, Integer> labelToIndex, HashMap<Integer, Integer> indexToLabel){
        int v = data[index];
        data[index] = data[num-1];
        int label = indexToLabel.get(num-1);
        num--;
        bubble_down(index, label, labelToIndex, indexToLabel);
        return v;
    }

    public int extract_min(HashMap<Integer, Integer> labelToIndex, HashMap<Integer, Integer> indexToLabel){
        return delete(0, labelToIndex, indexToLabel);
    }

    private int bubble_up(int start, HashMap<Integer, Integer> labelToIndex, HashMap<Integer, Integer> indexToLabel){
        int tmp = data[start];
        int cur = start;
        while(cur>0 && tmp<data[(cur-1)/2]){
            data[cur] = data[(cur-1)/2];
            labelToIndex.put(indexToLabel.get((cur-1)/2), cur);
            indexToLabel.put(cur, indexToLabel.get((cur-1)/2));
            cur = (cur-1)/2;
        }
        data[cur] = tmp;
        return cur;
    }

    private void bubble_down(int start, int label, HashMap<Integer, Integer> labelToIndex, HashMap<Integer, Integer> indexToLabel){
        int tmp = data[start];
        int cur = start;
        while(cur*2+1<num){
            int child = cur*2+1;
            if(child+1<num && data[child+1]<data[child]){
                child++;
            }
            if(tmp>data[child]){
                data[cur] = data[child];
                labelToIndex.put(indexToLabel.get(child), cur);
                indexToLabel.put(cur, indexToLabel.get(child));
                cur = child;
            }else{
                break;
            }
        }
        data[cur] = tmp;
        labelToIndex.put(label, cur);
        indexToLabel.put(cur, label);
    }
//    public static void main(String[] args){
//        MinHeap heap = new MinHeap(20);
//        for(int i=0;i<15;i++){
//            int value = (int)(Math.random()*100);
//            heap.insert(value);
//        }
//        heap.extract_min();
//        heap.extract_min();
//        heap.delete(2);
//        heap.delete(3);
//        heap.delete(4);
//        for(int j=0;j<heap.num;j++){
//            System.out.print(heap.data[j]+" ");
//        }
//    }
}
