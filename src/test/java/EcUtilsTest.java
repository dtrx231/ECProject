import static org.junit.Assert.*;

import org.junit.Test;

import ec.nodes.EcNode;
import ec.nodes.EcNodeFactory;
import ec.util.EcUtils;

public class EcUtilsTest {

	@Test
	public void test() {
		EcNode n1 = EcNodeFactory.createRandomOperator();
		EcNode n2 = EcNodeFactory.createRandomOperand();
		EcNode p1 = EcNodeFactory.createRandomOperator();
		p1.setLeftChild(n1);
		p1.setRightChild(n2);
		assertTrue(p1.getLeftChild() == n1);
		assertTrue(p1.getRightChild() == n2);
		EcNode p2 = EcNodeFactory.createRandomOperator();
		EcUtils.copyChildNodes(p1, p2);
		assertTrue(p2.getLeftChild() == n1);
		assertTrue(p2.getRightChild() == n2);
		
		EcUtils.mutateLeftChild(p1);
		assertTrue(p1.getLeftChild() != n1);
		assertTrue(p1.getRightChild() == n2);
		EcUtils.mutateRightChild(p1);
		assertTrue(p1.getLeftChild() != n1);
		assertTrue(p1.getRightChild() != n2);
	}

}
