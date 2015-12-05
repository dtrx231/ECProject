import static org.junit.Assert.*;

import org.junit.Test;

import ec.nodes.EcNode;
import ec.nodes.EcNodeFactory;
import ec.util.EcUtils;

public class EcUtilsTest {

	@Test
	public void testSetChildren() {
		System.out.println();
		System.out.println("Test Setting Children");
		EcNode root = EcNodeFactory.createRandomOperator();
		EcNode leaf1 = EcNodeFactory.createRandomOperator();
		EcNode leaf2 = EcNodeFactory.createRandomOperand();
		
		String dataLeaf1 = leaf1.getData();
		String dataLeaf2 = leaf2.getData();
		
		root.setLeftChild(leaf1);
		root.setRightChild(leaf2);
		
		System.out.println("Orig leaf: " + dataLeaf1);
		System.out.println("Root left child: " + root.getLeftChild().getData());
		System.out.println("Orig leaf: " + dataLeaf2);
		System.out.println("Root right child: " + root.getRightChild().getData());
		
		assertSame(root.getLeftChild(),leaf1);
		assertSame(root.getRightChild(),leaf2);
	}
	
	@Test 
	public void testMutate() {
		System.out.println();
		System.out.println("Test Mutation");
		EcNode root = EcNodeFactory.createRandomOperator();
		EcNode leaf1 = EcNodeFactory.createRandomOperator();
		EcNode leaf2 = EcNodeFactory.createRandomOperand();
		EcNode leaf4 = EcNodeFactory.createRandomOperand();
		EcNode leaf5 = EcNodeFactory.createRandomOperand();
		
		root.setLeftChild(leaf1);
		root.setRightChild(leaf2);
		leaf1.setLeftChild(leaf4);
		leaf1.setRightChild(leaf5);
		
		String orig = root.toString();
		System.out.println(orig);
		
		String dataLeaf1 = leaf1.getData();
		String dataLeaf2 = leaf2.getData();

		EcUtils.mutateLeftChild(root);
		EcUtils.mutateRightChild(root);
		
		String mutated = root.toString();
		System.out.println(mutated);
		
		assertFalse(root.getLeftChild().getData() == dataLeaf1);
		assertFalse(root.getRightChild().getData() == dataLeaf2);
		assertFalse(orig == mutated);
	}
	
	@Test
	public void testFlipCoin() {
		System.out.println();
		System.out.println("Test Flipcoin");
		//should fail 50% of time
		assertTrue(EcUtils.flipCoin());
	}
	
	@Test
	public void testByChance() {
		System.out.println();
		System.out.println("Test ByChance");
		
		assertTrue(EcUtils.byChance(1));
		assertFalse(EcUtils.byChance(1000));
	}

}
