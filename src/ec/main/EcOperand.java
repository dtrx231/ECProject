package ec.main;
import java.util.Random;

/**
 *  @author Dane
 *  @created 10-02-2015
 */


public class EcOperand extends EcNode {

	//SHOULD COME FROM SETTINGS
	private String[] operands = {"0","1","2","3","4","5","6","7","8","9","x"};
	
	public EcOperand() {
		Random rn = new Random();
		this.data = operands[rn.nextInt(operands.length)];
	}
	
	public EcOperand(String x) {
		this.data = "x";
	}
	
	public String toString() {
		return this.data;
	}
	
	public double calculateOutput(int input) {
		if (this.data.equals("x")) {
			return input;
		}
		else {
			return Integer.parseInt(this.data);
		}
	}

}