package array;

public class StringTest {
	public static void main(String[] args) {
		long begin = System.currentTimeMillis();
		String str = new String();
		for (int i = 0; i < 100; i++) {
			str += 'a';
		}
		System.out.println(System.currentTimeMillis() - begin);
	}
}
