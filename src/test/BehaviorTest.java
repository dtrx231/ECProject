package test;

import static org.junit.Assert.*;

import org.junit.Test;

import ec.nodes.EcNode;
import ec.nodes.EcNodeFactory;
import ec.nodes.EcOperand;
import ec.nodes.EcOperator;

public class BehaviorTest {

	@Test
	public void testMutationDefault() {
		EcNode n1 = EcNodeFactory.createRandomOperator();
		EcNode n2 = EcNodeFactory.createRandomOperand();
		EcNode p1 = EcNodeFactory.createRandomOperator();
		p1.setLeftChild(n1);
		p1.setRightChild(n2);
		assertTrue(p1.getLeftChild() instanceof EcOperator);
		assertTrue(p1.getRightChild() instanceof EcOperand);
		assertTrue(p1.getLeftChild() == n1);
		assertTrue(p1.getRightChild() == n2);
		System.out.println("Before mutation");
		System.out.println("Left Child : " + p1.getLeftChild().getData());
		System.out.println("Right Child : " + p1.getRightChild().getData());
		
		p1.setLeftChild(p1.getLeftChild().mutate());
		p1.setRightChild(p1.getRightChild().mutate());
		assertTrue(p1.getLeftChild() instanceof EcOperator);
		assertTrue(p1.getRightChild() instanceof EcOperand);
		assertTrue(p1.getLeftChild() != n1);
		assertTrue(p1.getRightChild() != n2);
		System.out.println("After mutation");
		System.out.println("Left Child : " + p1.getLeftChild().getData());
		System.out.println("Right Child : " + p1.getRightChild().getData());
		
		// test calculate output after mutation
		EcNode n3 = EcNodeFactory.createRandomOperand();
		EcNode n4 = EcNodeFactory.createRandomOperand();
		EcNode p2 = EcNodeFactory.createRandomOperator();
		p2.setLeftChild(n3);
		p2.setRightChild(n4);
		double before = p2.calculateOutput(6);
		System.out.println("************************");
		System.out.println("Before mutation");
		System.out.println("Parent : " + p2.getData());
		System.out.println("Left Child : " + p2.getLeftChild().getData());
		System.out.println("Right Child : " + p2.getRightChild().getData());
		System.out.println("Output : " + before);
		
		p2.setLeftChild(p2.getLeftChild().mutate());
		p2.setRightChild(p2.getRightChild().mutate());
		double after = p2.calculateOutput(6);
		System.out.println("After mutation");
		System.out.println("Parent : " + p2.getData());
		System.out.println("Left Child : " + p2.getLeftChild().getData());
		System.out.println("Right Child : " + p2.getRightChild().getData());
		System.out.println("Output : " + after);
		
		
		
		
	}

}
