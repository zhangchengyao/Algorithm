import java.io.*;

class Solution{
	public static void main(String[] args){
		int num_vertices = 0;
		int[] vertices = null;
		int[] dp = null;
		try{
			BufferedReader br = new BufferedReader(new FileReader("mwis.txt"));
			num_vertices = Integer.parseInt(br.readLine());
			vertices = new int[num_vertices];
			dp = new int[num_vertices+1];
			String line;
			int i = 0;
			while((line=br.readLine())!=null){
				vertices[i++] = Integer.parseInt(line);
			}
		}catch(IOException e){
			e.printStackTrace();
		}
		dp[0] = 0;
		dp[1] = vertices[0];
		for(int i=2;i<dp.length;i++){
			dp[i] = Math.max(dp[i-1], dp[i-2]+vertices[i-1]);
		}
		int[] targets = {1, 2, 3, 4, 17, 117, 517, 997};
		StringBuilder sb = new StringBuilder();
		int i = dp.length-1;
		while(i>=1){
			if(dp[i]>dp[i-1]){
				sb.insert(0, "1");
				i -= 2;
				if(i>=0) sb.insert(0, "0");
			}
			else{
				sb.insert(0, "0");
				i--;
			}
		}
		for(i=0;i<targets.length;i++){
			System.out.print(sb.charAt(targets[i]-1));
		}
	}
}