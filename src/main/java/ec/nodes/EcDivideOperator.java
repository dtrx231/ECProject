package ec.nodes;

/**
 *  @author Dane
 *  @created 10-02-2015
 */

public class EcDivideOperator extends EcOperator{
	
	@Override
	public double calculateOutput(double input) {
		double output = 0;
		output += (this.leftChild.calculateOutput(input) / this.rightChild.calculateOutput(input));
		if (Double.isInfinite(output) || Double.isNaN(output)) {
			output = 9999.0;
		}
		return output;
	}
	
	@Override
	public EcNode clone() {
		EcDivideOperator clone = new EcDivideOperator();
		clone.setData(this.data);
		clone.setOutput(this.output);
		clone.setLeftChild(this.getLeftChild().clone());
		clone.setRightChild(this.getRightChild().clone());
		return clone;
	}
}
