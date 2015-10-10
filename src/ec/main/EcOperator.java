package ec.main;
import java.util.Random;
import java.util.Arrays;
/**
 *  @author Dane
 *  @created 10-02-2015
 */

public class EcOperator extends EcNode {
	
	private String[] operators;
	
	public EcOperator(String[] operators) {
		this.operators = operators;
		Random rn = new Random();
		this.data = operators[rn.nextInt(operators.length)];
	}
	
	public String toString() {
		return "(" + this.getLeftChild().toString() + " " + this.data + " " + this.getRightChild().toString() + ")";
	}
	
	public void createRandomNode(int height) {
		
		if (height == 1) {
			this.leftChild = EcNodeFactory.getNode("ECOPERAND");
			this.rightChild = EcNodeFactory.getNode("ECOPERAND");
		}
		else {
			this.leftChild = EcNodeFactory.getNode();
			this.rightChild = EcNodeFactory.getNode();
			this.leftChild.createRandomNode(height-1);
			this.rightChild.createRandomNode(height-1);
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
	
	public void mutate(int depth) {
		//todo
	}
	
	public void mutate(String data,int depth) {
		
		if (Arrays.asList(this.operators).contains(data)) {
			//operator
		}
		else {
			this.leftChild.mutate(data,Math.abs(depth-1));
			this.rightChild.mutate(data,Math.abs(depth-1));
		}
	}
	
	public int getDepth() {
		int depthLeft = 0;
		int depthRight = 0;
		depthLeft += 1 + this.leftChild.getDepth();
		depthRight += 1 + this.rightChild.getDepth();
		return Math.max(depthLeft,depthRight);
	}
	
}