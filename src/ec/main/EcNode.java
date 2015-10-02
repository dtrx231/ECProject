package ec.main;

/**
 *  @author Duy
 *  @created 09-29-2015
 */

public class EcNode {
	EcNode leftChild;
	EcNode rightChild;
	String data;
	protected double output;
	protected String type;
	
	
	
	public String getType() {
		return this.type;
	}
	
	private void setType() {
		//TO BE IMPLEMENTED BY SUBCLASSES
	}
		
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
}
