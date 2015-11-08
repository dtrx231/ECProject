package ec.nodes;

/**
 *  @author Dane
 *  @created 10-02-2015
 */

public class EcDivideOperator extends EcOperator{
	
	@Override
	public double calculateOutput(int input) {
		if (rightChild.data.equals("0")) {
			output = -9999;
		}
		else {
			output += (leftChild.calculateOutput(input) / rightChild.calculateOutput(input));
		}
		return output;
		
	}
}