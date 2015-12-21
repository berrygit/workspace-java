
public class Prime {

	public static void main(String[] args) {
		Prime test = new Prime();

		// test.printPrimeBelow(0);
		// test.printPrimeBelow(-1);
		// test.printPrimeBelow(1);
		// test.printPrimeBelow(2);
		// test.printPrimeBelow(3);
		// test.printPrimeBelow(4);
		// test.printPrimeBelow(1000);
		test.printPrimeBelow(1000000);
	}

	public void printPrimeBelow(int num) {
		if (num < 2) {
			return;
		}

		boolean[] primeFlags = new boolean[num + 1];

		primeFlags[2] = true;

		for (int i = 3; i < primeFlags.length; i++) {
			if (i % 2 != 0) {
				primeFlags[i] = true;
			}
		}

		for (int i = 3; i < primeFlags.length; i++) {
			if (primeFlags[i] && isPrime(i)) {
				int offset = i + i;
				while (offset < primeFlags.length) {
					primeFlags[offset] = false;
					offset += i;
				}
			}
		}
		int count = 0;
		for (int i = 2; i < primeFlags.length; i++) {
			if (primeFlags[i]) {
				count++;
			}
		}

		System.out.println(count);
	}

	private boolean isPrime(int num) {

		if (num < 2) {
			return false;
		}

		int bound = (int) Math.sqrt((double) num);

		for (int i = 2; i <= bound; i++) {
			if (num % i == 0) {
				return false;
			}
		}

		return true;
	}
}
