package array;

public class ReplaceSpace {

	public static void main(String[] args) {
		ReplaceSpace t = new ReplaceSpace();
		String tt = "da fa fda   fda                         ";
		System.out.println(t.replaceSpaces(tt.toCharArray(), 15));
		System.out.println(t.replaceSpaces(null, 0));
		System.out.println(t.replaceSpaces(tt.toCharArray(), 0));
	}
	
	public String replaceSpaces(char[] str, int length) {
		if(str==null||length<=0) {
			return null;
		}
		
		int count=0;
		
		for(int i=0;i<length;i++) {
			if(str[i]==' ') {
				count++;
			}
		}
		
		if(count ==0) {
			return null;
		}
	
		int position = length + count*2 -1;
		
		for(int i=length-1;i>=0;i--) {
			if(str[i]!=' '){
				str[position--]=str[i];
			}else{
				str[position--]='0';
				str[position--]='2';
				str[position--]='%';
			}
		}
		
		return new String(str);
	}
}
