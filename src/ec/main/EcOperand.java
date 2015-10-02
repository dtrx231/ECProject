package ec.main;

/**
 *  @author Dane
 *  @created 10-02-2015
 */


public class EcOperand extends EcNode {

	public EcOperand() {
		this.setType();		
	}
	
	private void setType() {
		this.type = "Operand";
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

}