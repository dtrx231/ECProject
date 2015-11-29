package ec.nodes;
import java.util.Random;

import ec.util.EcPropertyValues;

/**
*  @author Dane
*  @created 10-10-2015
*  
*/

/*
* Factory class to randomly retrun either a EcOperator or EcOperand instance
*/

public class EcNodeFactory {
	public static final String OPERATOR = "OPERATOR";
	public static final String OPERAND = "OPERAND";
	public static final String ADD = "+";
	public static final String SUBTRACT = "-";
	public static final String DIVIDE = "/";
	public static final String MULTIPLY = "*";
	
	/*
	*	Randomly generate a random number between 0-50. If the number is <= 25 return an EcOperator instance. If the number 
	*	is < 25 return an EcOperand instance. Or if node type is specified, return that type of node.
	*/
	public static EcNode createRandomNode() {
		EcNode node;
		Random rn = new Random();
		Integer ranInt = rn.nextInt(50)+1;
		if (ranInt <= 25) {
			node = createRandomOperator();
		}
		else {
			node = createRandomOperand();	
		}	
		return node;
	}
	
	public static EcNode createRandomNode(String type) {
		EcNode node;
		switch(type) {
			case OPERATOR : node = createRandomOperator(); break;
			case OPERAND : node = createRandomOperand(); break;
			default: node = null; 
		}
		return node;
	}
	
	public static EcOperator createRandomOperator() {
		EcOperator operator;
		String[] operators = EcPropertyValues.getInstance().getOperators();
		Random rn = new Random();
		String operatorType = operators[rn.nextInt(operators.length)];
		switch(operatorType) {
		 	case ADD :      operator = new EcAddOperator(); operator.setData(ADD); break;
		 	case SUBTRACT : operator = new EcSubtractOperator();  operator.setData(SUBTRACT); break;
		 	case MULTIPLY : operator = new EcMultiplyOperator(); operator.setData(MULTIPLY); break;
		 	case DIVIDE :   operator = new EcDivideOperator(); operator.setData(DIVIDE); break;
		 	default:        operator = null; break;
		 }
		 return operator;
	}
	
	public static EcOperand createRandomOperand() {
		
	    String[] operands = EcPropertyValues.getInstance().getOperands();
		Random rn = new Random();
		String operandType = operands[rn.nextInt(operands.length)];
		return new EcOperand(operandType);
	}
	
	public static EcOperand createOperand(String operandType) {
		return new EcOperand(operandType);
	}
	
	public static EcOperator createOperator(String operatorType) {
		EcOperator operator;
		switch(operatorType) {
	 	case ADD :      operator = new EcAddOperator(); operator.setData(ADD); break;
	 	case SUBTRACT : operator = new EcSubtractOperator();  operator.setData(SUBTRACT); break;
	 	case MULTIPLY : operator = new EcMultiplyOperator(); operator.setData(MULTIPLY); break;
	 	case DIVIDE :   operator = new EcDivideOperator(); operator.setData(DIVIDE); break;
	 	default:        operator = null; break;
		}
		return operator;
	}
	
	public static String getRandomOperator() {
		String[] operators = EcPropertyValues.getInstance().getOperators();
		Random rn = new Random();
		return operators[rn.nextInt(operators.length)];
	}
	
	public static String getRandomOperand() {
		String[] operands = EcPropertyValues.getInstance().getOperands();
		Random rn = new Random();
		return operands[rn.nextInt(operands.length)];
	}
	
}