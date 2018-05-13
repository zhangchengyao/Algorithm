import java.io.*;

class Solution{
	public static void main(String[] args){
		Solution solution = new Solution();
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			while(true){
				String mode = br.readLine();
				if(mode.equals("-1")) break;
				int[] input = solution.getInputData("QuickSort.txt");
				System.out.println(solution.quickSortCountCmp(input, 0, input.length-1, Integer.parseInt(mode)));
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	int quickSortCountCmp(int[] array, int left, int right, int mode){
		if(left>=right) return 0;
		int count = right - left;
		int pivot = getPivot(array, left, right, mode);
		int i = left+1;
		int j = left+1;
		while(j<=right){
			if(array[j]<pivot){
				swap(array, i, j);
				i++;
			}
			j++;
		}
		swap(array, left, i-1);
		count += quickSortCountCmp(array, left, i-2, mode);
		count += quickSortCountCmp(array, i, right, mode);
		return count;
	}
	int getPivot(int[] array, int left, int right, int mode){
		int pivot = 0;
		switch(mode){
			case 1: 
			pivot = array[left];
			break;
			case 2: 
			pivot = array[right];
			swap(array, left, right);
			break;
			case 3: 
			int index = median3(array, left, right);
			pivot = array[index];
			swap(array, left, index);
			break;
			default: break;
		}
		return pivot;
	}
	int median3(int[] array, int left, int right){
		int middle = (left+right)/2;
		if(array[left]>array[middle]){
			if(array[left]<=array[right]){
				return left;
			}else{
				if(array[middle]>array[right]) return middle;
				else return right;
			}
		}else{
			if(array[middle]<=array[right]){
				return middle;
			}else{
				if(array[right]>array[left]) return right;
				else return left;
			}
		}
	}
	void swap(int[] array, int x, int y){
		int tmp = array[x];
		array[x] = array[y];
		array[y] = tmp;
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