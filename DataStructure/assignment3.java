import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Solution {
    public static void main(String[] args){
        String filename = "Median.txt";
        MaxHeap h_low = new MaxHeap(10000);
        MinHeap h_high = new MinHeap(10000);
        int i = 1;
        long median_sum = 0;
        try{
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line;
            while((line=br.readLine())!=null){
                int val = Integer.parseInt(line);
                if(i%2 != 0){
                    // the number should be put into heap low
                    if(!h_high.isEmpty() && val>h_high.get_min()){
                        int tmp = h_high.extract_min();
                        h_high.insert(val);
                        h_low.insert(tmp);
                    }else{
                        h_low.insert(val);
                    }
                    i++;
                }else{
                    // the number should be put into heap high
                    if(val<h_low.get_max()){
                        int tmp = h_low.extract_max();
                        h_low.insert(val);
                        h_high.insert(tmp);
                    }else{
                        h_high.insert(val);
                    }
                    i++;
                }
                System.out.println((i-1)+": current median = " + h_low.get_max());
                median_sum += h_low.get_max();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(median_sum%10000);
    }
}
