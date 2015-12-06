import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import ec.main.EcMain;
import ec.main.EcPopulation;
import ec.main.EcTree;
import ec.nodes.EcNode;
import ec.util.EcTreeIterator;

public class IteratorTest {

	@Test
	public void test() {

		EcTree tree = new EcTree(10);
		int depth = tree.getRoot().getDepth();
		EcTreeIterator itr = new EcTreeIterator(tree.getRoot());
		
		System.out.println(tree.getRoot().toString());
		
		while(itr.hasNext()) {
			EcNode node = itr.next();
			String data = node.getData();
			System.out.println(data);
			assertTrue(tree.getRoot().search(data));
		}
		
	}

}
