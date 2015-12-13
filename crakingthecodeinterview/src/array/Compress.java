package array;

public class Compress {

	public static void main(String[] args) {
		Compress tt = new Compress();
		// System.out.println((int) 'a');
		// System.out.println(tt.compressString(null));
		// System.out.println(tt.compressString(""));
		// System.out.println(tt.compressString("abk;ldsajffejiao;fjie"));
		// System.out.println(tt.compressString("afewqyhnujm"));

		System.out.println(tt.compressString2(null).equals(""));
		System.out.println(tt.compressString2("").equals(""));
		System.out.println(tt.compressString2("asdbge").equals("asdbge"));
		System.out.println(tt.compressString2("a").equals("a"));
		System.out.println(tt.compressString2("aaabddddeesssaaaa").equals("a3b1d4e2s3a4"));
		System.out.println(tt.compressString2("aaaaaaaaaaa").equals("a11"));

	}

	public String compressString2(String str) {
		if (str == null || str.isEmpty()) {
			return "";
		}

		int compressLength = countCompressLength(str);
		if (compressLength > str.length()) {
			return str;
		}

		char[] charSet = new char[compressLength];

		int count = 1;
		int last = 0;
		int position = 0;

		for (int i = 1; i < str.length(); i++) {
			if (str.charAt(i) == str.charAt(last)) {
				count++;
			} else {
				charSet[position++] = str.charAt(last);

				String countStr = String.valueOf(count);
				for (char c : countStr.toCharArray()) {
					charSet[position++] = c;
				}

				count = 1;
				last = i;
			}
		}

		charSet[position++] = str.charAt(last);

		String countStr = String.valueOf(count);
		for (char c : countStr.toCharArray()) {
			charSet[position++] = c;
		}

		return new String(charSet);
	}

	private int countCompressLength(String str) {
		if (str == null || str.isEmpty()) {
			return 0;
		}

		int sumCount = 0;
		int count = 1;
		int last = 0;
		for (int i = 1; i < str.length(); i++) {
			if (str.charAt(i) == str.charAt(last)) {
				count++;
			} else {
				sumCount += String.valueOf(count).length() + 1;
				count = 1;
				last = i;
			}
		}

		return sumCount + 1 + String.valueOf(count).length();
	}

	public String compressString(String str) {

		if (str == null || str.isEmpty()) {
			return "";
		}

		int[] charSet = new int[128];

		for (int i = 0; i < str.length(); i++) {
			charSet[str.charAt(i)]++;
		}

		StringBuffer result = new StringBuffer();

		for (int i = 0; i < charSet.length; i++) {
			if (charSet[i] > 0) {
				result.append((char) i).append(charSet[i]);
			}
		}

		String compressedStr = new String(result);

		if (compressedStr.length() == str.length() * 2) {
			return str;
		} else {
			return compressedStr;
		}
	}
}
