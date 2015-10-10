package ec.main;
import java.util.Random;

/**
 *  @author Duy
 *  @created 09-29-2015
 */

public abstract class EcNode {
	
	protected EcNode leftChild;
	protected EcNode rightChild;
	protected String data;
	protected double output;
		

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
	
	public boolean checkData(String data) {
		if (this.data.equals(data)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	abstract void mutate(int depth);
	abstract void mutate(String data,int depth);
	abstract int getDepth();
	abstract boolean search(String data);
	abstract void createRandomNode(int height); 
	abstract double calculateOutput(int input);
}
