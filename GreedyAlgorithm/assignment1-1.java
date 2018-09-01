import java.io.*;

class Solution{
    static class Job{
        int weight;
        int length;
        double priority;
        Job(int weight, int length, int mode){
            this.weight = weight;
            this.length = length;
            priority = (mode==1?weight-length:(weight*1.0)/length);
        }
        boolean biggerThan(Job job){
            if(this.priority>job.priority){
                return true;
            }else if(this.priority==job.priority){
                return this.weight>=job.weight;
            }else{
                return false;
            }
        }
    }
    public static void main(String[] args){
        Solution solution = new Solution();
        int num_jobs = 0;
        Job[] jobs = null;
        try{
            BufferedReader br = new BufferedReader(new FileReader("jobs.txt"));
            String line;
            num_jobs = Integer.parseInt(br.readLine());
            jobs = new Job[num_jobs];
            int i = 0;
            while((line=br.readLine())!=null){
                String[] attr = line.split(" ");
                jobs[i] = new Job(Integer.parseInt(attr[0]), Integer.parseInt(attr[1]),1);
                i++;
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        solution.quickSort(jobs, 0, num_jobs-1);
        long sum = 0;
        long complete_time = 0;
        for(int i=num_jobs-1;i>=0;i--){
            complete_time += jobs[i].length;
            sum += jobs[i].weight * complete_time;
        }
        System.out.println(sum);
    }
    private void quickSort(Job[] jobs, int left, int right){
        if(left>right) return;
        Job pivot = getPivot(jobs, left, right);
        int i = left+1;
        int j = left+1;
        while(i<=right){
            if(pivot.biggerThan(jobs[i])){
                swap(jobs, j, i);
                j++;
            }
            i++;
        }
        swap(jobs, left, j-1);
        quickSort(jobs, left, j-2);
        quickSort(jobs, j, right);
    }
    private void swap(Job[] jobs, int x, int y){
        Job tmp = jobs[x];
        jobs[x] = jobs[y];
        jobs[y] = tmp;
    }
    private Job getPivot(Job[] jobs, int left, int right){
        Job pivot = median3(jobs, left, right);
        int middle = (left+right)/2;
        swap(jobs, left, middle);
        return pivot;
    }
    private Job median3(Job[] jobs, int left, int right){
        int middle = (left+right)/2;
        if(jobs[left].biggerThan(jobs[middle])){
            swap(jobs, left, middle);
        }
        if(jobs[left].biggerThan(jobs[right])){
            swap(jobs, left, right);
        }
        if(jobs[middle].biggerThan(jobs[right])){
            swap(jobs, middle, right);
        }
        return jobs[middle];
    }
}