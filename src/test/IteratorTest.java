package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import ec.main.EcTree;
import ec.nodes.EcNode;

public class IteratorTest {

	@Test
	public void test() {
		EcTree ecTree1 = TestUtils.createExampleTree();
		
		String ecTree1DataExpected[] = {"/","-","*","x","x","1","2"};
		List<String> ecTree1DataActual = new ArrayList<>();
		for (EcNode node : ecTree1) {
			ecTree1DataActual.add(node.getData());
		}
		assertArrayEquals("Iterator order should be" , ecTree1DataExpected , ecTree1DataActual.toArray());
	}

}
