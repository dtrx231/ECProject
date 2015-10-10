package ec.main;
import java.util.Random;
import java.util.Arrays;

/**
 *  @author Dane
 *  @created 10-02-2015
 */


public class EcOperand extends EcNode {

	private String[] operands;
	
	public EcOperand(String[] operands) {
		this.operands = operands;
		Random rn = new Random();
		this.data = operands[rn.nextInt(operands.length)];
	}
	
	public EcOperand(String x) {
		this.data = "x";
	}
	
	public void createRandomNode(int height) {
		//nothing
	}
	
	public String toString() {
		return this.data;
	}
	
	public double calculateOutput(int input) {
		if (this.data.equals("x")) {
			return input;
		}
		else {
			return Integer.parseInt(this.data);
		}
	}
	
	public boolean search(String data) {
		if (this.data.equalsIgnoreCase(data)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void mutate(int depth) {
		//todo
	}
	
	public void mutate(String data,int depth) {
		
		if (Arrays.asList(this.operands).contains(data)) {
			if (depth == 1) {
				this.data = data;
			}
		}
	}
	
	public int getDepth() {
		return 0;
	}
	

}