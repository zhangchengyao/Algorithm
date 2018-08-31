import java.io.*;
import java.util.*;

class Solution{
	public static void main(String[] args){
		HashSet<Long> set = new HashSet<>();
		int num = 0;
		try{
			BufferedReader br = new BufferedReader(new FileReader("2sum.txt"));
			String line;
			while((line=br.readLine())!=null){
				set.add(Long.parseLong(line));
			}
		}catch(IOException e){
			e.printStackTrace();
		}
		for(int i=-10000;i<=10000;i++){
			if(i%1000==0) System.out.println("Current i= "+i);
			Iterator<Long> it = set.iterator();
			while(it.hasNext()){
				if(set.contains(i-it.next())){
					num++;
					break;
				}
			}
		}
		System.out.println(num);
	}
}