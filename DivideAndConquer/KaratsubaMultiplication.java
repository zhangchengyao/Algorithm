import java.io.*;

class Solution{
	public static void main(String[] args){
		Solution solution = new Solution();
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String num1 = br.readLine().trim();
			String num2 = br.readLine().trim();
			System.out.println("Multiply Result: " + solution.multiply(num1, num2));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	String multiply(String num1, String num2){
		int len = num1.length();
		if(len==1){
			int res = Integer.parseInt(num1) * Integer.parseInt(num2);
			return res+"";
		}
		String a = num1.substring(0,len/2);
		String b = num1.substring(len/2, len);
		String c = num2.substring(0, len/2);
		String d = num2.substring(len/2, len);
		String tmp1 = multiply(a, c);
		String tmp2 = multiply(b, d);
		String tmp3 = multiply(a, d);
		String tmp4 = multiply(b, c);
		String tmp5 = add(tmp3, tmp4);
		StringBuilder sb1 = new StringBuilder(tmp1);
		StringBuilder sb2 = new StringBuilder(tmp5);
		for(int i=0;i<len;i++){
			sb1.append('0');
			if(i%2==0) sb2.append('0');
		}
		return add(add(sb1.toString(), sb2.toString()), tmp2);
	}

	String add(String num1, String num2){
		StringBuilder sb = new StringBuilder();
		int len1 = num1.length();
		int len2 = num2.length();
		if(len1>len2){
			StringBuilder tmpSb = new StringBuilder(num2);
			for(int i=len2;i<len1;i++){
				tmpSb.insert(0, '0');
			}
			num2 = tmpSb.toString();
		}else if(len2>len1){
			StringBuilder tmpSb = new StringBuilder(num1);
			for(int i=len1;i<len2;i++){
				tmpSb.insert(0, '0');
			}
			num1 = tmpSb.toString();
		}
		int carry = 0;
		int tmpSum = 0;
		for(int i=num1.length()-1;i>=0;i--){
			tmpSum = (num1.charAt(i)-'0') + (num2.charAt(i)-'0') + carry;
			carry = Math.max(tmpSum/10, 0);
			tmpSum %= 10;
			sb.append(tmpSum);
		}
		if(carry>0){
			sb.append(carry);
		}
		return sb.reverse().toString();
	}
}
