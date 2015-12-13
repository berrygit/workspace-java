package array;

public class CheckRepeat {

	public boolean isRepeatCharInString(String message) {
		
		if(message==null || "".equals(message))
		{
			return false;
		}
		
		boolean[] charFlags = new boolean[128];

		for (int i = 0; i < message.length(); i++) {

			if (charFlags[message.charAt(i)] == true) {
				return true;
			} else {
				charFlags[message.charAt(i)] = true;
			}
		}

		return false;
	}
	
	public static void main(String[] args) {
		CheckRepeat test = new CheckRepeat();
		System.out.println(test.isRepeatCharInString(""));
		System.out.println(test.isRepeatCharInString(null));
		System.out.println(test.isRepeatCharInString(" "));
		
		System.out.println(test.isRepeatCharInString("aa"));
		System.out.println(test.isRepeatCharInString("aaa"));
		System.out.println(test.isRepeatCharInString("afdefdas"));
		System.out.println(test.isRepeatCharInString("qwer1234567890tyuiop"));
	}

}
