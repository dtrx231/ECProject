package ec.util;

import java.util.Random;

import ec.nodes.EcNode;

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
	
	public static boolean byChance(int prob) {
		if (rn.nextInt(prob) == 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static void copyChildNodes(EcNode source, EcNode target)  {
		target.setLeftChild(source.getLeftChild());
		target.setRightChild(source.getRightChild());
	}
	public static void mutateLeftChild(EcNode parent) {
		EcNode tempLeft = parent.getLeftChild().mutate();
		EcUtils.copyChildNodes(parent.getLeftChild(), tempLeft);
		parent.setLeftChild(tempLeft);
	}
	public static void mutateRightChild(EcNode parent) {
		EcNode tempRight = parent.getRightChild().mutate();
		EcUtils.copyChildNodes(parent.getRightChild(), tempRight);
		parent.setRightChild(tempRight);
	}
	
	
}
