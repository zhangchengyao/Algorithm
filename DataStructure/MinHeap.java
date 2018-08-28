public class MinHeap {
    private int[] data;
    private int max_num;
    private int num;

    public MinHeap(int max_num){
        this.max_num = max_num;
        data = new int[max_num];
        num = 0;
    }

    public void insert(int val){
        data[num] = val;
        num++;
        bubble_up(num-1);
    }

    public int extract_min(){
        int min = data[0];
        data[0] = data[num-1];
        num--;
        bubble_down(0);
        return min;
    }

    public int get_min(){
        return data[0];
    }

    public boolean isEmpty(){
        return num==0;
    }

    private void bubble_up(int start){
        int tmp = data[start];
        int cur = start;
        while(cur>0 && tmp<data[(cur-1)/2]){
            data[cur] = data[(cur-1)/2];
            cur = (cur-1)/2;
        }
        data[cur] = tmp;
    }

    private void bubble_down(int start){
        int tmp = data[start];
        int cur = start;
        while(cur*2+1<num){
            int child = cur*2+1;
            if(child+1<num && data[child+1]<data[child]){
                child++;
            }
            if(tmp>data[child]){
                data[cur] = data[child];
                cur = child;
            }else{
                break;
            }
        }
        data[cur] = tmp;
    }
}
