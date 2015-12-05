import static org.junit.Assert.*;

import org.junit.Test;

import ec.nodes.EcOperand;

public class EcOperandTest {

	@Test
	public void testEcOperand() {
		System.out.println();
		System.out.println("Test Instantiating EcOperand");
		
		EcOperand op1 = new EcOperand("x");
		System.out.println(op1.getData());
		assertTrue(op1.getData() == "x");
		
		EcOperand op2 = new EcOperand("1");
		System.out.println(op2.getData());
		assertTrue(op2.getData() == "1");
	}
	
	@Test
	public void testCalculateOutput() {
		System.out.println();
		System.out.println("Test Instantiating EcOperand");
		
		EcOperand op1 = new EcOperand("x");
		double input = 2.0;
		double output = op1.calculateOutput(input);
		System.out.println("Input: " + input);
		System.out.println("Output: " + output);
		
		assertTrue(input == output);
		
		EcOperand op2 = new EcOperand("9");
		double input2 = 3.0;
		double output2 = op2.calculateOutput(input);
		System.out.println("Input: " + input2);
		System.out.println("Output: " + output2);
		
		assertFalse(input2 == output2);
		
	}
	
	@Test
	public void testSearch() {
		System.out.println();
		System.out.println("Test Searching EcOperand");
		
		EcOperand op1 = new EcOperand("x");
		assertTrue(op1.search("X"));
		assertTrue(op1.search("x"));
		assertFalse(op1.search("7"));
		
		EcOperand op2 = new EcOperand("2");
		assertTrue(op2.search("2"));
		assertFalse(op2.search("3"));
		
	}

}


