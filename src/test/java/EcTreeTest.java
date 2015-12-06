import static org.junit.Assert.*;

import org.junit.Test;

import ec.main.EcTree;
import ec.nodes.EcNodeFactory;
import ec.nodes.EcOperand;
import ec.nodes.EcOperator;

public class EcTreeTest {

	@Test
	public void test() {
		EcOperator root = EcNodeFactory.createOperator("*");
		EcOperand l1 = EcNodeFactory.createOperand("x");
		EcOperand r1 = EcNodeFactory.createOperand("7");
		
		root.setLeftChild(l1);
		root.setRightChild(r1);
		
		EcTree t1 = new EcTree(root);
		
		Double[] in1 = {3.0};
		Double[] out1 = {22.0};
		
		double o1 = t1.calculateFitness(in1,out1);
		t1.displayTree();
		
		assertTrue(o1 == 1.0);
		
		assertTrue(t1.hasX());
	}
	
	@Test
	public void testGetAndSet() {
		EcTree tree = new EcTree();
		tree.setFitness(34.5);
		assertTrue(tree.getFitness()==34.5);
		tree.setHeight(11);
		assertTrue(tree.getHeight()==11);
		EcOperator root = EcNodeFactory.createOperator("/");
		tree.setRoot(root);
		assertTrue(tree.getRoot()==root);
		assertTrue(tree.getRoot().getData().equals(root.getData()));
	}

}
