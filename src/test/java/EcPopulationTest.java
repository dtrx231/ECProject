import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import ec.main.EcPopulation;
import ec.main.EcTree;
import ec.nodes.EcNode;
import ec.nodes.EcNodeFactory;
import ec.nodes.EcOperand;
import ec.nodes.EcOperator;
import ec.util.EcPropertyValues;

public class EcPopulationTest {

	@Test
	public void testForceX() {
		System.out.println("Test ForceX");
		EcOperator root = EcNodeFactory.createRandomOperator();
		EcOperator l1 = EcNodeFactory.createRandomOperator();
		EcOperand l2 = new EcOperand("3");
		EcOperator l3 = EcNodeFactory.createRandomOperator();
		EcOperand l4 = new EcOperand("5");
		EcOperand l5 = new EcOperand("7");
		EcOperand l6 = new EcOperand("9");
		
		root.setLeftChild(l1);
		root.setRightChild(l2);
		l1.setLeftChild(l3);
		l1.setRightChild(l4);
		l3.setLeftChild(l5);
		l3.setRightChild(l6);
		
		String tree1 = root.toString();
		
		EcOperator root2 = EcNodeFactory.createRandomOperator();
		EcOperator l12 = EcNodeFactory.createRandomOperator();
		EcOperand l22 = new EcOperand("1");
		EcOperator l32 = EcNodeFactory.createRandomOperator();
		EcOperand l42 = new EcOperand("5");
		EcOperand l52 = new EcOperand("9");
		EcOperand l62 = new EcOperand("9");
		
		root2.setLeftChild(l22);
		root2.setRightChild(l12);
		l12.setLeftChild(l32);
		l12.setRightChild(l42);
		l32.setLeftChild(l52);
		l32.setRightChild(l62);
		
		String tree2 = root2.toString();
		
		EcPopulation pop = new EcPopulation();
		ArrayList<EcTree> trees = new ArrayList<EcTree>();
		EcTree one = new EcTree(root);
		EcTree two = new EcTree(root2);
		trees.add(one);
		trees.add(two);
		
		pop.setNextPopulation(trees);
		pop.forceX();
		
		System.out.println(tree1);
		System.out.println(pop.getNextPopulation().get(0).getRoot().toString());
		System.out.println(tree2);
		System.out.println(pop.getNextPopulation().get(1).getRoot().toString());
		
		assertTrue(pop.getNextPopulation().get(0).hasX());
		assertTrue(pop.getNextPopulation().get(1).hasX());
	}
	
	@Test
	public void testPruneTrees() {
		System.out.println("Test PruneTrees");
		EcOperator root = EcNodeFactory.createRandomOperator();
		EcOperator l1 = EcNodeFactory.createRandomOperator();
		EcOperand l2 = new EcOperand("3");
		EcOperator l3 = EcNodeFactory.createRandomOperator();
		EcOperand l4 = new EcOperand("5");
		EcOperand l5 = new EcOperand("7");
		EcOperand l6 = new EcOperand("9");
		root.setLeftChild(l1);
		root.setRightChild(l2);
		l1.setLeftChild(l3);
		l1.setRightChild(l4);
		l3.setLeftChild(l5);
		l3.setRightChild(l6);
		
		EcOperator root2 = EcNodeFactory.createRandomOperator();
		EcOperand l22 = new EcOperand("1");
		EcOperand l62 = new EcOperand("9");
		root2.setLeftChild(l62);
		root2.setRightChild(l22);
		
		EcPopulation pop = new EcPopulation();
		ArrayList<EcTree> trees = new ArrayList<EcTree>();
		EcTree one = new EcTree(root);
		EcTree two = new EcTree(root2);
		trees.add(one);
		trees.add(two);
		
		pop.setNextPopulation(trees);
		pop.pruneTrees(3);
		
		for (int a = 0; a < pop.getNextPopulation().size(); a++) {
			System.out.println(pop.getNextPopulation().get(a).getRoot().toString() + " " + pop.getNextPopulation().get(a).getRoot().getDepth());
		}
		assertTrue(pop.getNextPopulation().size()==0);
	}
	
	@Test
	public void testDisplayPopulation() {
		System.out.println("Test DisplayPopulation");
		
		EcPopulation pop = new EcPopulation();
		while (pop.getCurrentPopulation().size() < 10) {
			EcTree ecTree = new EcTree(5);
			pop.getCurrentPopulation().add(ecTree);
		}
		pop.displayCurrentPopulation();
		assertTrue(pop.getCurrentPopulation().size()==10);
		
		while (pop.getNextPopulation().size() < 5) {
			EcTree ecTree = new EcTree(5);
			pop.getNextPopulation().add(ecTree);
		}
		
		pop.displayNextPopulation();
		assertTrue(pop.getNextPopulation().size()==5);
	}

}
