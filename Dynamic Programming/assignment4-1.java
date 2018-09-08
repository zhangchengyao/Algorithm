import java.io.*;

class Solution{
	public static void main(String[] args){
		int W = 0;
		int n = 0;
		int[] values = null;
		int[] weights = null;
		int[][] dp = null;
		try{
			BufferedReader br = new BufferedReader(new FileReader("knapsack1.txt"));
			String[] firstLine = br.readLine().split(" ");
			W = Integer.parseInt(firstLine[0]);
			n = Integer.parseInt(firstLine[1]);
			values = new int[n];
			weights = new int[n];
			dp = new int[n+1][W+1];
			String line;
			int i = 0;
			while((line=br.readLine())!=null){
				String[] info = line.split(" ");
				values[i] = Integer.parseInt(info[0]);
				weights[i] = Integer.parseInt(info[1]);
				i++;
			}
		}catch(IOException e){
			e.printStackTrace();
		}
		for(int i=1;i<dp.length;i++){
			for(int x=1;x<dp[0].length;x++){
				if(weights[i-1]>x){
					dp[i][x] = dp[i-1][x];
				}else{
					dp[i][x] = Math.max(dp[i-1][x], dp[i-1][x-weights[i-1]]+values[i-1]);
				}
			}
		}
		System.out.println(dp[n][W]);
	}
}