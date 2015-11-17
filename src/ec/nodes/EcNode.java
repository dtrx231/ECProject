package ec.nodes;

/**
 *  @author Duy
 *  @created 09-29-2015
 */

public abstract class EcNode {
		
	protected EcNode leftChild;
	protected EcNode rightChild;
	protected String data;
	protected double output;
	
	public boolean checkData(String data) {
		if (this.data.equals(data)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public abstract boolean search(String data);
	public abstract void spawnRandomNode(int height); 
	public abstract double calculateOutput(double input);
	public abstract EcNode clone();
	public abstract int getDepth();
	public abstract void mutate();
	public void setOutput(double output) {
		this.output=output;
	}
	
	public double getOutput() {
		return this.output;		
	}
	
	public void setLeftChild(EcNode node) {
		this.leftChild = node;
	}
	
	public EcNode getLeftChild() {
		return this.leftChild;
	}
	
	public void setRightChild(EcNode node) {
		this.rightChild = node;
	}
	
	public EcNode getRightChild() {
		return this.rightChild;
	}
	
	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
}
