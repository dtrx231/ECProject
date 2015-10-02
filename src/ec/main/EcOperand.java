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

}