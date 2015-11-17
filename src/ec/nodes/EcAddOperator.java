package ec.nodes;

/**
 *  @author Dane
 *  @created 10-02-2015
 */

public class EcAddOperator extends EcOperator {

	@Override
	public double calculateOutput(double input) {
		double output = 0;
		output += (leftChild.calculateOutput(input) + rightChild.calculateOutput(input));
		return output;
		
	}

}
