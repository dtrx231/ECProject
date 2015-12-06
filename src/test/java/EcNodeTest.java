import static org.junit.Assert.*;

import org.junit.Test;

import ec.nodes.EcNode;
import ec.nodes.EcNodeFactory;
import ec.nodes.EcOperand;
import ec.nodes.EcOperator;

public class EcNodeTest {

	@Test
	public void test() {
		EcOperator o1 = EcNodeFactory.createOperator("+");
		EcOperator o2 = EcNodeFactory.createOperator("-");
		EcOperator o3 = EcNodeFactory.createOperator("/");
		EcOperator o4 = EcNodeFactory.createOperator("*");
		EcOperand o5 = EcNodeFactory.createOperand("0");
		EcOperand o6 = EcNodeFactory.createOperand("1");
		EcOperand o7 = EcNodeFactory.createOperand("2");
		EcOperand o8 = EcNodeFactory.createOperand("3");
		EcOperand o9 = EcNodeFactory.createOperand("4");
		EcOperand o10 = EcNodeFactory.createOperand("5");
		EcOperand o11 = EcNodeFactory.createOperand("6");
		EcOperand o12 = EcNodeFactory.createOperand("7");
		EcOperand o13 = EcNodeFactory.createOperand("8");
		EcOperand o14 = EcNodeFactory.createOperand("9");
		EcOperand o15 = EcNodeFactory.createOperand("x");
		
		assertTrue(o1.checkData("+"));
		assertTrue(o2.checkData("-"));
		assertTrue(o3.checkData("/"));
		assertTrue(o4.checkData("*"));
		assertTrue(o5.checkData("0"));
		assertTrue(o6.checkData("1"));
		assertTrue(o7.checkData("2"));
		assertTrue(o8.checkData("3"));
		assertTrue(o9.checkData("4"));
		assertTrue(o10.checkData("5"));
		assertTrue(o11.checkData("6"));
		assertTrue(o12.checkData("7"));
		assertTrue(o13.checkData("8"));
		assertTrue(o14.checkData("9"));
		assertTrue(o15.checkData("x")); 	
		
		EcNode node = EcNodeFactory.createRandomNode();
		
		if (node instanceof EcOperand) {
			assertTrue(node.getLeftChild()==null);
			assertTrue(node.getRightChild()==null);
			node.setData("1");
			assertTrue(node.getData().equals("1"));
		}
		else {
			EcOperand l1 = EcNodeFactory.createOperand("x");
			EcOperand r1 = EcNodeFactory.createOperand("4");
			node.setLeftChild(l1);
			node.setRightChild(r1);
			assertTrue(node.getLeftChild() instanceof EcOperand);
			assertTrue(node.getLeftChild()==l1);
			assertTrue(node.getLeftChild().getData().equals("x"));
			assertTrue(node.getRightChild() instanceof EcOperand);
			assertTrue(node.getRightChild()==r1);
			assertTrue(node.getRightChild().getData().equals("4"));
			node.setData("/");
			assertTrue(node.getData().equals("/"));
		}
		
		node.setOutput(45.55);
		assertTrue(node.getOutput()==45.55);
		

	}

}
