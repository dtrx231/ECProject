package ec.main;
import java.util.Random;

/**
 *  @author Dane
 *  @created 10-02-2015
 */

public class EcOperator extends EcNode {
	
	//SHOULD COME FROM SETTINGS
	private String[] operators = {"+","-","/","*"};
	
	public EcOperator() {
		Random rn = new Random();
		this.data = operators[rn.nextInt(operators.length)];
	}
	
	public String toString() {
		return "(" + leftChild.toString() + " " + this.data + " " + rightChild.toString() + ")";
	}
	
	public double calculateOutput(int input) {
		
		double output = 0;
		
		if (this.data.equals("+")) {
			output += (leftChild.calculateOutput(input) + rightChild.calculateOutput(input));
		}
		else if (this.data.equals("-")) {
			output += (leftChild.calculateOutput(input) - rightChild.calculateOutput(input));
		}
		else if (this.data.equals("*")) {
			output += (leftChild.calculateOutput(input) * rightChild.calculateOutput(input));	
		}
		else {
			if (rightChild.data.equals("0")) {
				output = -9999;
			}
			else {
				output += (leftChild.calculateOutput(input) / rightChild.calculateOutput(input));
			}
		}
		return output;
	}
	
}