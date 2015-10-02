package ec.main;

/**
 *  @author Duy
 *  @created 09-29-2015
 */

public class EcNode {
	EcNode leftChild;
	EcNode rightChild;
	String data;
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
	
	
	
}
