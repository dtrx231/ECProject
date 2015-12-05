import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

import ec.nodes.EcAddOperator;
import ec.nodes.EcDivideOperator;
import ec.nodes.EcMultiplyOperator;
import ec.nodes.EcNode;
import ec.nodes.EcNodeFactory;
import ec.nodes.EcOperand;
import ec.nodes.EcOperator;
import ec.nodes.EcSubtractOperator;
import ec.util.EcPropertyValues;

public class EcNodeFactoryTest {
	
	public static final String OPERATOR = "OPERATOR";
	public static final String OPERAND = "OPERAND";
	public static final String ADD = "+";
	public static final String SUBTRACT = "-";
	public static final String DIVIDE = "/";
	public static final String MULTIPLY = "*";

	@Test
	public void testCreateOperator() {
		
		EcOperator op1 = EcNodeFactory.createOperator(ADD);
		assertTrue(op1 instanceof EcAddOperator);
		assertTrue(op1.getData() == "+");
		
		EcOperator op2 = EcNodeFactory.createOperator(DIVIDE);
		assertTrue(op2 instanceof EcDivideOperator);
		assertTrue(op2.getData() == "/");
		
		EcOperator op3 = EcNodeFactory.createOperator(SUBTRACT);
		assertTrue(op3 instanceof EcSubtractOperator);
		assertTrue(op3.getData() == "-");
		
		EcOperator op4 = EcNodeFactory.createOperator(MULTIPLY);
		assertTrue(op4 instanceof EcMultiplyOperator);
		assertTrue(op4.getData() == "*");
		
	}
	
	@Test
	public void testCreateRandomOperator() {
		EcOperator op1 = EcNodeFactory.createRandomOperator();
		EcOperator op2 = EcNodeFactory.createRandomOperator();
		
		//These may be the same some times due to randomness
		System.out.println(op1.getClass());
		System.out.println(op2.getClass());
		
		assertFalse(op1.getClass() == op2.getClass());
		
	}
	
	@Test
	public void testCreateRandomNode() {

		EcNode n1 = EcNodeFactory.createRandomNode();
		EcNode n2 = EcNodeFactory.createRandomNode();
		
		System.out.println(n1.getClass());
		System.out.println(n2.getClass());
		
		assertFalse(n1.getClass() == n2.getClass());
		
		EcNode n3 = EcNodeFactory.createRandomNode(OPERATOR);
		EcNode n4 = EcNodeFactory.createRandomNode(OPERAND);
		
		System.out.println(n3.getClass());
		System.out.println(n4.getClass());
		
		assertTrue(n3 instanceof EcOperator);
		assertTrue(n4 instanceof EcOperand);
		
	}
	
	@Test
	public void testCreateRandomOperand() {

		EcNode n1 = EcNodeFactory.createRandomOperand();
		assertTrue(n1 instanceof EcOperand);
		
		EcNode n2 = EcNodeFactory.createOperand("1");
		assertTrue(n2 instanceof EcOperand);
		assertTrue(n2.getData()=="1");
		
	}
	
	@Test
	public void testGetRandomOperator() {

		String op1 = EcNodeFactory.getRandomOperator();
		String op2 = EcNodeFactory.getRandomOperator();
		System.out.println(op1);
		System.out.println(op2);
		//may fail due to randomness
		assertFalse(op1==op2);
		
	}
	
	@Test
	public void testGetRandomOperand() {

		String op1 = EcNodeFactory.getRandomOperand();
		String op2 = EcNodeFactory.getRandomOperand();
		System.out.println(op1);
		System.out.println(op2);
		//may fail due to randomness
		assertFalse(op1==op2);
		
	}

}
