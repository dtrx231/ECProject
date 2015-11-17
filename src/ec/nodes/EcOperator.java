package ec.nodes;

import ec.util.EcUtils;

/**
 *  @author Dane
 *  @created 10-02-2015
 */

public abstract class EcOperator extends EcNode {
	
	public String toString() {
		return "(" + this.getLeftChild().toString() + " " + this.data + " " + this.getRightChild().toString() + ")";
	}
	
	public void spawnRandomNode(int height) {
		
		if (height == 1) {
			this.leftChild = EcNodeFactory.createRandomNode(EcNodeFactory.OPERAND);
			this.rightChild = EcNodeFactory.createRandomNode(EcNodeFactory.OPERAND);
		}
		else {
			this.leftChild = EcNodeFactory.createRandomNode();
			this.rightChild = EcNodeFactory.createRandomNode();
			this.leftChild.spawnRandomNode(height-1);
			this.rightChild.spawnRandomNode(height-1);
		}
		
	}
	
	public boolean search(String data) {
		if (this.data.equalsIgnoreCase(data)) {
			return true;
		} 
		else if(this.leftChild.search(data)) {
			return true;			
		}
		else if(this.rightChild.search(data)) {
			return true;
		} else {
			return false;
		}
	}
	
	public abstract double calculateOutput(double input);
	
	@Override
	public EcNode mutate() {
		EcNode newOp = EcNodeFactory.createRandomOperator();
		EcUtils.copyChildNodes(this, newOp);
		return newOp;
	}
	
}