package ec.main;

/**
 *  @author Dane
 *  @created 10-02-2015
 */

public class EcOperator extends EcNode {
	
	public EcOperator() {
		this.setType();	
	}
	
	private void setType() {
		this.type = "Operator";
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
				output += (leftChild.calculateOutput(input) / 1);
			}
			else {
				output += (leftChild.calculateOutput(input) / rightChild.calculateOutput(input));
			}
		}
		return output;
	}
	
}