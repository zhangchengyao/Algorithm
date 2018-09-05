import java.io.*;

class Solution{
	public static void main(String[] args){
		int num_symbols = 0;;
		MinHeap mh = null;
		try{
			BufferedReader br = new BufferedReader(new FileReader("huffman.txt"));
			num_symbols = Integer.parseInt(br.readLine());
			mh = new MinHeap(num_symbols);
			String line;
			int i = 0;
			while((line=br.readLine())!=null){
				int weight = Integer.parseInt(line);
				Tree t = new Tree(new Node(++i), weight);
				t.height = 0;
				mh.insert(t);
			}
		}catch(IOException e){
			e.printStackTrace();
		}
		for(int i=1;i<num_symbols;i++){
			Tree t1 = mh.extract_min();
			Tree t2 = mh.extract_min();
			Node root = new Node(0);
			root.left = t1.root;
			root.right = t2.root;
			Tree merged = new Tree(root, t1.weight+t2.weight);
			merged.height = Math.max(t1.height, t2.height) + 1;
			mh.insert(merged);
		}
		Tree final_tree = mh.extract_min();
		int min_length = new Solution().minDepth(final_tree.root);
		System.out.println("Max length = "+final_tree.height);
		System.out.println("Min length = "+min_length);
	}
	private int minDepth(Node root){
		if(root==null) return 0;
		if(root.left==null && root.right==null) return 0;
		if(root.left!=null && root.right==null) return minDepth(root.left)+1;
		if(root.left==null && root.right!=null) return minDepth(root.right)+1;
		return Math.min(minDepth(root.left), minDepth(root.right))+1;
	}
}