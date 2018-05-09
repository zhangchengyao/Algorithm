import java.io.*;

class Solution{
	public static void main(String[] args){
		Solution solution = new Solution();
		int[] numbers = null;
		try{
			numbers = solution.getInputData("IntegerArray.txt");
		}catch(IOException e){
			e.printStackTrace();
		}
		
		int[] tmp = new int[numbers.length];
		long num_inverse = solution.mergeSortAndCountInv(numbers, 0, numbers.length-1, tmp);
		System.out.println(num_inverse);
	}
	long mergeSortAndCountInv(int[] a, int left, int right, int[] tmp){
		if(left==right) return 0;
		int middle = (left+right)/2;
		long totalInverse = mergeSortAndCountInv(a, left, middle, tmp) + mergeSortAndCountInv(a, middle+1, right, tmp);
		totalInverse += mergeAndCountInv(a ,left, right, tmp);
		return totalInverse;
	}
	long mergeAndCountInv(int[] a, int left, int right, int[] tmp){
		long res = 0;
		int middle = (left+right)/2;
		int i = left;
		int j = middle+1;
		int k = left;
		while(i<=middle && j<=right){
			if(a[i]<=a[j]){
				tmp[k++] = a[i++];
			}else{
				tmp[k++] = a[j++];
				res += middle+1-i;
			}
		}
		while(i<=middle){
			tmp[k++] = a[i++];
		}
		while(j<=right){
			tmp[k++] = a[j++];
		}
		for(k=left;k<=right;k++){
			a[k] = tmp[k];
		}
		return res;
	}
	int[] getInputData(String fileName) throws IOException {  
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		int totalLines = 0;
		String line;
		while((line = br.readLine())!=null){
			totalLines ++;
		}

		br = new BufferedReader(new FileReader(fileName));
		int[] res = new int[totalLines];
		int i = 0;
		while((line = br.readLine())!=null){
			res[i++] = Integer.parseInt(line);
		}
		br.close();
		return res;
	}
}