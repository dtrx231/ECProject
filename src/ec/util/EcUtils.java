package ec.util;

import java.util.Random;

public class EcUtils {
	private static final Random rn = new Random();
	public static boolean flipCoin() {
		if (rn.nextInt(2) == 0) {
			return false;
		}
		else {
			return true;
		}
	}
}
