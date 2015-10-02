package ec.main;

/**
 *  @author Dane
 *  @created 10-02-2015
 */

public class EcOperator extends EcNode {
	
	public EcOperator() {
		this.setType();	
	}
	
	private void setType() {
		this.type = "Operator";
	}
	
}