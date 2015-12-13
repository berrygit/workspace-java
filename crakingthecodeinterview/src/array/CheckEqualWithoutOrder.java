package array;

public class CheckEqualWithoutOrder {

	public static void main(String[] args) {
		CheckEqualWithoutOrder test = new CheckEqualWithoutOrder();
		
//		System.out.println(test.compareTwoStringWithoutOrder1("", ""));
//		System.out.println(test.compareTwoStringWithoutOrder1(null, null));
//		System.out.println(test.compareTwoStringWithoutOrder1("abc", "cba"));
//		System.out.println(test.compareTwoStringWithoutOrder1(" ", ""));
//		System.out.println(test.compareTwoStringWithoutOrder1(null, "bcd"));
//		System.out.println(test.compareTwoStringWithoutOrder1("bcd", null));
//		System.out.println(test.compareTwoStringWithoutOrder1("abaab", "aabba"));
		
		System.out.println(test.compareTwoStringWithoutOrder2("", ""));
		System.out.println(test.compareTwoStringWithoutOrder2(null, null));
		System.out.println(test.compareTwoStringWithoutOrder2("abc", "cba"));
		System.out.println(test.compareTwoStringWithoutOrder2(" ", ""));
		System.out.println(test.compareTwoStringWithoutOrder2(null, "bcd"));
		System.out.println(test.compareTwoStringWithoutOrder2("bcd", null));
		System.out.println(test.compareTwoStringWithoutOrder2("abaab", "aabba"));
	}
	
	public boolean  compareTwoStringWithoutOrder1(String first,String second) {
		
		if(first==null||second==null||first.length()!=second.length())
		{
			return false;
		}
		
		String sortedFirst = sortString(first);
		String sortedSecond = sortString(second);
		
		if(sortedFirst==null || sortedSecond==null)
		{
			return false;
		}
		
		return sortedFirst.equals(sortedSecond);
	}
	
	public String sortString(String message)
	{
		if(message==null)
		{
			return null;
		}
		
		char[] charSet = message.toCharArray();
		java.util.Arrays.sort(charSet);
		
		return new String(charSet);
	}
	
	public boolean compareTwoStringWithoutOrder2(String first,String second) {
		
		if(first == null || second == null || first.length() != second.length()) {
			return false;
		}
		
		int[] charCount = new int[128];
		
		for(int i=0; i<first.length();i++)	{
			int position = first.charAt(i);
			charCount[position]++;
		}
		
		for(int i=0;i<second.length();i++) {
			int position = second.charAt(i);
			if(--charCount[position]<0) {
				return false;
			}
		}
		
		return true;
	}
}
