import java.io.*;

class Solution{
	public static void main(String[] args){
		int W = 0;
		int n = 0;
		int[] values = null;
		int[] weights = null;
		int[] dp = null;
		try{
			BufferedReader br = new BufferedReader(new FileReader("knapsack_big.txt"));
			String[] firstLine = br.readLine().split(" ");
			W = Integer.parseInt(firstLine[0]);
			n = Integer.parseInt(firstLine[1]);
			values = new int[n];
			weights = new int[n];
			dp = new int[W+1];
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
		for(int i=1;i<=n;i++){
			for(int x=W;x>0;x--){
				if(weights[i-1]<=x){
					dp[x] = Math.max(dp[x], dp[x-weights[i-1]]+values[i-1]);
				}
			}
		}
		System.out.println(dp[W]);
	}
}