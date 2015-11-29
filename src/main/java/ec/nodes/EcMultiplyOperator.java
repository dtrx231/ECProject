package ec.nodes;

/**
 *  @author Dane
 *  @created 10-02-2015
 */

public class EcMultiplyOperator extends EcOperator{
	
	@Override
	public double calculateOutput(double input) {
		double output = 0;
		output += (this.leftChild.calculateOutput(input) * this.rightChild.calculateOutput(input));	
		return output;
	}
	
	@Override
	public EcNode clone() {
		EcMultiplyOperator clone = new EcMultiplyOperator();
		clone.setData(this.data);
		clone.setOutput(this.output);
		clone.setLeftChild(this.getLeftChild().clone());
		clone.setRightChild(this.getRightChild().clone());
		return clone;
	}
}
