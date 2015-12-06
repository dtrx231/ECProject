import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import ec.behaviors.crossover.EcPopulationCrossoverMode;
import ec.behaviors.crossover.EcPopulationDefaultCrossoverMode;
import ec.behaviors.mutation.EcPopulationDefaultMutationMode;
import ec.behaviors.mutation.EcPopulationMutationMode;
import ec.behaviors.selection.EcPopulationDefaultSelectionMode;
import ec.behaviors.selection.EcPopulationSelectionMode;
import ec.main.EcMain;
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
	
	@Test
	public void testDoCrossover() {
		System.out.println("Test DoCrossover");
		//Tree 1
		EcOperator root1 = EcNodeFactory.createOperator("+");
		EcOperator r1l1 = EcNodeFactory.createOperator("+");
		EcOperand r1r1 = EcNodeFactory.createOperand("1");
		EcOperand r1l2 = EcNodeFactory.createOperand("1");
		EcOperand r1r2 = EcNodeFactory.createOperand("1");
		
		root1.setLeftChild(r1l1);
		root1.setRightChild(r1r1);
		r1l1.setLeftChild(r1l2);
		r1l1.setRightChild(r1r2);
		
		EcTree t1 = new EcTree(root1);
		
		//Tree 2
		EcOperator root2 = EcNodeFactory.createOperator("-");
		EcOperator r2l1 = EcNodeFactory.createOperator("-");
		EcOperand r2r1 = EcNodeFactory.createOperand("2");
		EcOperand r2l2 = EcNodeFactory.createOperand("2");
		EcOperand r2r2 = EcNodeFactory.createOperand("2");
		
		root2.setLeftChild(r2l1);
		root2.setRightChild(r2r1);
		r2l1.setLeftChild(r2l2);
		r2l1.setRightChild(r2r2);
		
		EcTree t2 = new EcTree(root2);
		
		ArrayList<EcTree> trees = new ArrayList<EcTree>();
		trees.add(t1);
		trees.add(t2);
		EcPopulation pop = new EcPopulation();
		pop.setNextPopulation(trees);
		
		String origT1 = t1.getRoot().toString();
		String origT2 = t2.getRoot().toString();
		
		double origOutT1 = t1.getRoot().calculateOutput(3.0);
		double origOutT2 = t2.getRoot().calculateOutput(3.0);
		
		assertFalse(origT1.equals(origT2));
		System.out.println(origT1);
		System.out.println(origOutT1);
		System.out.println(origT2);
		System.out.println(origOutT2);
		
		pop.doCrossover();
		
		String newT1 = t1.getRoot().toString();
		String newT2 = t2.getRoot().toString();
		
		double newOutT1 = t1.getRoot().calculateOutput(3.0);
		double newOutT2 = t2.getRoot().calculateOutput(3.0);
		
		System.out.println(newT1);
		System.out.println(newOutT1);
		System.out.println(newT2);
		System.out.println(newOutT2);

		
		//should fail 50% of time due to randomness
		assertFalse(origT1.equals(newT1));
		assertFalse(origOutT1==newOutT1 && origOutT2==newOutT2);
		assertFalse(origT2.equals(newT2));
		
	}
	
	@Test
	public void testDoMutation() {
		System.out.println("Test DoMutation");
		//Tree 1
		EcOperator root1 = EcNodeFactory.createOperator("+");
		EcOperator r1l1 = EcNodeFactory.createOperator("+");
		EcOperand r1r1 = EcNodeFactory.createOperand("1");
		EcOperand r1l2 = EcNodeFactory.createOperand("1");
		EcOperand r1r2 = EcNodeFactory.createOperand("1");
		
		root1.setLeftChild(r1l1);
		root1.setRightChild(r1r1);
		r1l1.setLeftChild(r1l2);
		r1l1.setRightChild(r1r2);
		
		EcTree t1 = new EcTree(root1);
		
		//Tree 2
		EcOperator root2 = EcNodeFactory.createOperator("-");
		EcOperator r2l1 = EcNodeFactory.createOperator("-");
		EcOperand r2r1 = EcNodeFactory.createOperand("2");
		EcOperand r2l2 = EcNodeFactory.createOperand("2");
		EcOperand r2r2 = EcNodeFactory.createOperand("2");
		
		root2.setLeftChild(r2l1);
		root2.setRightChild(r2r1);
		r2l1.setLeftChild(r2l2);
		r2l1.setRightChild(r2r2);
		
		EcTree t2 = new EcTree(root2);
		
		ArrayList<EcTree> trees = new ArrayList<EcTree>();
		trees.add(t1);
		trees.add(t2);
		EcPopulation pop = new EcPopulation();
		pop.setNextPopulation(trees);
		
		String origT1 = t1.getRoot().toString();
		String origT2 = t2.getRoot().toString();
		
		double origOutT1 = t1.getRoot().calculateOutput(3.0);
		double origOutT2 = t2.getRoot().calculateOutput(3.0);
		
		assertFalse(origT1.equals(origT2));
		System.out.println(origT1);
		System.out.println(origOutT1);
		System.out.println(origT2);
		System.out.println(origOutT2);
		
		pop.doMutation();
		
		String newT1 = t1.getRoot().toString();
		String newT2 = t2.getRoot().toString();
		
		double newOutT1 = t1.getRoot().calculateOutput(3.0);
		double newOutT2 = t2.getRoot().calculateOutput(3.0);
		
		System.out.println(newT1);
		System.out.println(newOutT1);
		System.out.println(newT2);
		System.out.println(newOutT2);

		
		//should fail 50% of time due to randomness
		assertFalse(origT1.equals(newT1) && origT2.equals(newT2));
		assertFalse(origOutT1==newOutT1 && origOutT2==newOutT2);
		
	}
	
	@Test
	public void testDoSelection() {
		System.out.println("Test DoSelection");
		ArrayList<EcTree> trees = new ArrayList<EcTree>();
		EcMain.fillUpPopulation(trees);
		EcPopulation pop = new EcPopulation();
		pop.setCurrentPopulation(trees);
		assertTrue(pop.getCurrentPopulation().size()==60);
		assertTrue(pop.getNextPopulation().size()==0);
		pop.doSelection();
		assertTrue(pop.getNextPopulation().size()==45);
	}
	
	@Test
	public void testGetBehaviors() {
		EcPopulation pop = new EcPopulation();
		
		EcPopulationCrossoverMode crossoverMode = pop.getCrossoverMode();
		EcPopulationMutationMode mutationMode = pop.getMutationMode();
		EcPopulationSelectionMode selectionMode = pop.getSelectionMode();
		
		assertTrue(crossoverMode instanceof EcPopulationDefaultCrossoverMode);
		assertTrue(mutationMode instanceof EcPopulationDefaultMutationMode);
		assertTrue(selectionMode instanceof EcPopulationDefaultSelectionMode);
		
	}

}
