package ec.main;
import java.util.Random;

/**
 *  @author Duy
 *  @created 09-29-2015
 */

public class EcNode {
	EcNode leftChild;
	EcNode rightChild;
	String data;
	protected double output;
		
	public void mutate() {
		//TODO;
	}
	
	public double getOutput() {
		return this.output;		
	}

	public double calculateOutput(int input) {
		//TO BE IMPLEMENTED BY SUBCLASSES
		return 0;
	}
	
	public boolean checkData(String data) {
		if (this.data.equals(data)) {
			return true;
		}
		else {
			return false;
		}
	}
}
