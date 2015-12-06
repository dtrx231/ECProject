import static org.junit.Assert.*;

import org.junit.Test;

import ec.nodes.EcAddOperator;
import ec.nodes.EcDivideOperator;
import ec.nodes.EcMultiplyOperator;
import ec.nodes.EcNode;
import ec.nodes.EcNodeFactory;
import ec.nodes.EcOperand;
import ec.nodes.EcOperator;

public class EcOperatorTest {
	
	@Test
	public void testToString() {
		EcOperator root = EcNodeFactory.createOperator("+");
		EcOperator l1 = EcNodeFactory.createOperator("-");
		EcOperator r1 = EcNodeFactory.createOperator("-");
		EcOperand l2 = EcNodeFactory.createOperand("2");
		EcOperand r2 = EcNodeFactory.createOperand("5");
		EcOperand l3 = EcNodeFactory.createOperand("1");
		EcOperand r3 = EcNodeFactory.createOperand("x");
		
		root.setLeftChild(l1);
		root.setRightChild(r1);
		l1.setLeftChild(l2);
		l1.setRightChild(r2);
		r1.setLeftChild(l3);
		r1.setRightChild(r3);
		
		String str = root.toString();
		System.out.println(str);
		assertTrue(str.equals("((2 - 5) + (1 - x))"));
	}

	@Test
	public void testSearch() {
		System.out.println("Test Search");
		EcOperator root = EcNodeFactory.createOperator("+");
		EcOperator l1 = EcNodeFactory.createOperator("-");
		EcOperator r1 = EcNodeFactory.createOperator("-");
		EcOperand l2 = EcNodeFactory.createOperand("2");
		EcOperand r2 = EcNodeFactory.createOperand("5");
		EcOperand l3 = EcNodeFactory.createOperand("1");
		EcOperand r3 = EcNodeFactory.createOperand("x");
		
		root.setLeftChild(l1);
		root.setRightChild(r1);
		l1.setLeftChild(l2);
		l1.setRightChild(r2);
		r1.setLeftChild(l3);
		r1.setRightChild(r3);
		
		assertTrue(root.search("+"));
		assertFalse(root.search("*"));
		assertTrue(root.search("X"));
		assertTrue(root.search("x"));
		assertTrue(root.search("-"));
		assertTrue(root.search("2"));
		assertTrue(root.search("5"));
		assertTrue(root.search("1"));
		assertFalse(root.search("9"));
		System.out.println(root.getDepth());
	}
	
	@Test 
	public void testSpawnRandomNode() {
		System.out.println("Test SpawnRandomNode");
		EcOperator r1 = EcNodeFactory.createOperator("/");
		r1.spawnRandomNode(1);
		assertTrue(r1.getLeftChild() instanceof EcOperand);
		assertTrue(r1.getRightChild() instanceof EcOperand);
		
		EcOperator r2 = EcNodeFactory.createOperator("*");
		r2.spawnRandomNode(5);
		assertTrue(r2.getDepth() <= 5);
		assertTrue(r2.getDepth() > 0);
		System.out.println(r2.getDepth());
	
	}
	
	@Test 
	public void testGetDepth() {
		System.out.println("Test GetDepth");
		EcOperator root = EcNodeFactory.createOperator("+");
		EcOperator l1 = EcNodeFactory.createOperator("-");
		EcOperand r1 = EcNodeFactory.createOperand("x");
		EcOperator l2 = EcNodeFactory.createOperator("*");
		EcOperand r2 = EcNodeFactory.createOperand("5");
		EcOperator l3 = EcNodeFactory.createOperator("/");
		EcOperand r3 = EcNodeFactory.createOperand("1");
		EcOperand l4 = EcNodeFactory.createOperand("9");
		EcOperand r4 = EcNodeFactory.createOperand("9");
		
		root.setLeftChild(l1);
		root.setRightChild(r1);
		l1.setLeftChild(l2);
		l1.setRightChild(r2);
		l2.setLeftChild(l3);
		l2.setRightChild(r3);
		l3.setLeftChild(l4);
		l3.setRightChild(r4);
		
		System.out.println(root.toString());
		System.out.println(root.getDepth());
		assertTrue(root.getDepth()==4);
		
		root = EcNodeFactory.createOperator("/");
		l4 = EcNodeFactory.createOperand("X");
		r1 = EcNodeFactory.createOperand("9");
		
		root.setLeftChild(l4);
		root.setRightChild(r1);
		
		System.out.println(root.toString());
		System.out.println(root.getDepth());
		assertTrue(root.getDepth()==1);
		
		root = EcNodeFactory.createOperator("+");
		l1 = EcNodeFactory.createOperator("-");
		EcOperator r5 = EcNodeFactory.createOperator("-");
		EcOperand l6 = EcNodeFactory.createOperand("5");
		r2 = EcNodeFactory.createOperand("5");
		
		root.setLeftChild(l1);
		root.setRightChild(r5);
		l1.setLeftChild(l6);
		l1.setRightChild(r2);
		r5.setLeftChild(l4);
		r5.setRightChild(r4);
		
		System.out.println(root.toString());
		System.out.println(root.getDepth());
		assertTrue(root.getDepth()==2);
		
	}
	
	@Test
	public void testMutate() {
		System.out.println("Test Mutate");
		EcOperator root = EcNodeFactory.createOperator("+");
		assertTrue(root instanceof EcAddOperator);
		EcNode newop = root.mutate();
		assertFalse(newop instanceof EcAddOperator);
		System.out.println(root.getData());
		System.out.println(newop.getData());
	}
	
	@Test 
	public void testEcDivideOperator() {
		EcOperator root = EcNodeFactory.createOperator("/");
		EcOperand l1 = EcNodeFactory.createOperand("9");
		EcOperand r1 = EcNodeFactory.createOperand("x");
		
		root.setLeftChild(l1);
		root.setRightChild(r1);
		
		double o1 = root.calculateOutput(0.0);
		double o2 = root.calculateOutput(0);
		double o3 = root.calculateOutput(3);
		assertTrue(o1==9999.0);
		assertTrue(o2==9999.0);
		assertTrue(o3==3);
		
		EcNode clone = root.clone();
		assertFalse(root==clone);
		assertTrue(root.getData().equals(clone.getData()));
		assertTrue(root.getLeftChild().getClass()==clone.getLeftChild().getClass());
		assertTrue(root.getRightChild().getClass()==clone.getRightChild().getClass());
		
		assertTrue(o1==clone.calculateOutput(0.0));
		assertTrue(o2==clone.calculateOutput(0));
		assertTrue(o3==clone.calculateOutput(3));
	}
	
	@Test
	public void testEcAddOperator() {
		EcOperator root = EcNodeFactory.createOperator("+");
		EcOperand l1 = EcNodeFactory.createOperand("9");
		EcOperand r1 = EcNodeFactory.createOperand("x");
		
		root.setLeftChild(l1);
		root.setRightChild(r1);
		
		double o1 = root.calculateOutput(2.0);
		double o2 = root.calculateOutput(9.0);
		
		assertTrue(o1==11);
		assertTrue(o2==18);
		
		EcNode clone = root.clone();
		assertFalse(root==clone);
		assertTrue(root.getData().equals(clone.getData()));
		assertTrue(root.getLeftChild().getClass()==clone.getLeftChild().getClass());
		assertTrue(root.getRightChild().getClass()==clone.getRightChild().getClass());
		
		assertTrue(o1==clone.calculateOutput(2.0));
		assertTrue(o2==clone.calculateOutput(9.0));
	}
	
	@Test
	public void testEcMultiplyOperator() {
		EcOperator root = EcNodeFactory.createOperator("*");
		EcOperand l1 = EcNodeFactory.createOperand("9");
		EcOperand r1 = EcNodeFactory.createOperand("x");
		
		root.setLeftChild(l1);
		root.setRightChild(r1);
		
		double o1 = root.calculateOutput(2.0);
		double o2 = root.calculateOutput(9.0);
		
		assertTrue(o1==18);
		assertTrue(o2==81);
		
		EcNode clone = root.clone();
		assertFalse(root==clone);
		assertTrue(root.getData().equals(clone.getData()));
		assertTrue(root.getLeftChild().getClass()==clone.getLeftChild().getClass());
		assertTrue(root.getRightChild().getClass()==clone.getRightChild().getClass());
		
		assertTrue(o1==clone.calculateOutput(2.0));
		assertTrue(o2==clone.calculateOutput(9.0));
	}
	
	@Test
	public void testEcSubtractOperator() {
		EcOperator root = EcNodeFactory.createOperator("-");
		EcOperand l1 = EcNodeFactory.createOperand("9");
		EcOperand r1 = EcNodeFactory.createOperand("x");
		
		root.setLeftChild(l1);
		root.setRightChild(r1);
		
		double o1 = root.calculateOutput(2.0);
		double o2 = root.calculateOutput(9.0);
		
		assertTrue(o1==7);
		assertTrue(o2==0);
		
		EcNode clone = root.clone();
		assertFalse(root==clone);
		assertTrue(root.getData().equals(clone.getData()));
		assertTrue(root.getLeftChild().getClass()==clone.getLeftChild().getClass());
		assertTrue(root.getRightChild().getClass()==clone.getRightChild().getClass());
		
		assertTrue(o1==clone.calculateOutput(2.0));
		assertTrue(o2==clone.calculateOutput(9.0));
	}
	
	@Test
	public void testCalculateOutput() {
		EcOperator root = EcNodeFactory.createRandomOperator();
		EcOperand l1 = EcNodeFactory.createOperand("9");
		EcOperand r1 = EcNodeFactory.createOperand("x");
		
		root.setLeftChild(l1);
		root.setRightChild(r1);
		
		double out = root.calculateOutput(3.0);
		
		if (root instanceof EcAddOperator) {
			assertTrue(out==12);
		}
		else if (root instanceof EcDivideOperator) {
			assertTrue(out==3);
		}
		else if (root instanceof EcMultiplyOperator) {
			assertTrue(out==27);
		}
		else {
			assertTrue(out==6);
		}
	}

}
