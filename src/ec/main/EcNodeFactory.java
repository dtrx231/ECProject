package ec.main;
import java.util.Random;
import java.io.IOException;

/**
*  @author Dane
*  @created 10-10-2015
*/


/*
* Factory class to randomly retrun either a EcOperator or EcOperand instance
*/

public class EcNodeFactory {
	
	/*
	*	Randomly generate a random number between 0-50. If the number is <= 25 return an EcOperator instance. If the number 
	*	is < 25 return an EcOperand instance. Or if node type is specified, return that type of node.
	*/
	public static EcNode getNode() {
		
		Random rn = new Random();
		Integer ranInt = rn.nextInt(50)+1;
		if (ranInt <= 25) {
			try {
				return new EcOperator(EcPropertyValues.getInstance().operators);
			} catch (IOException e) {
				String[] operators = {"+","-","*","/"};
				return new EcOperator(operators);
			}
		}
		else {
			try {
				return new EcOperand(EcPropertyValues.getInstance().operands);
			} catch (IOException e) {
				String[] operands = {"1","2","3","4","5","6","7","8","9","x"};
				return new EcOperand(operands);
			}
		}
		
	}
	
	public static EcNode getNode(String type) {
		
		if (type.equalsIgnoreCase("ECOPERAND")) {
			try {
				return new EcOperand(EcPropertyValues.getInstance().operands);
			} catch (IOException e) {
				String[] operands = {"1","2","3","4","5","6","7","8","9","x"};
				return new EcOperand(operands);
			}
		}
		else if (type.equalsIgnoreCase("ECOPERATOR")) {
			try {
				return new EcOperator(EcPropertyValues.getInstance().operators);
			} catch (IOException e) {
				String[] operators = {"+","-","*","/"};
				return new EcOperator(operators);
			}
		}
		else {
			return null;
		}

	}
}