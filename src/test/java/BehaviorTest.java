

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import org.junit.Test;

import ec.behaviors.crossover.EcPopulationCrossoverMode;
import ec.behaviors.crossover.EcPopulationDefaultCrossoverMode;
import ec.behaviors.mutation.EcPopulationDefaultMutationMode;
import ec.behaviors.selection.EcPopulationDefaultSelectionMode;
import ec.main.EcMain;
import ec.main.EcPopulation;
import ec.main.EcTree;
import ec.nodes.EcNode;
import ec.nodes.EcNodeFactory;
import ec.nodes.EcOperand;
import ec.nodes.EcOperator;

public class BehaviorTest {
	
	@Test
	public void testMutationDefault() {
		System.out.println("Test MutationMode");
		EcOperator tree1 = EcNodeFactory.createOperator("/");
		EcOperand t1_l1 = EcNodeFactory.createOperand("1");
		EcOperand t1_r1 = EcNodeFactory.createOperand("1");
		tree1.setLeftChild(t1_l1);
		tree1.setRightChild(t1_r1);
		
		EcOperator tree2 = EcNodeFactory.createOperator("*");
		EcOperand t2_l1 = EcNodeFactory.createOperand("2");
		EcOperand t2_r1 = EcNodeFactory.createOperand("2");
		tree2.setLeftChild(t2_l1);
		tree2.setRightChild(t2_r1);
		
		EcTree t1 = new EcTree(tree1);
		EcTree t2 = new EcTree(tree2);
		
		ArrayList<EcTree> trees = new ArrayList<EcTree>();
		trees.add(t1);
		trees.add(t2);
		
		EcPopulation pop = new EcPopulation();
		pop.setNextPopulation(trees);
		
		EcPopulationDefaultMutationMode mutationmode = new EcPopulationDefaultMutationMode(pop);
		
		String origTree1 = pop.getNextPopulation().get(0).getRoot().toString();
		String origTree2 = pop.getNextPopulation().get(1).getRoot().toString();
		
		System.out.println("Tree 1: " + origTree1);
		System.out.println("Tree 2: " + origTree2);
		
		mutationmode.mutate();
		
		String newTree1 = pop.getNextPopulation().get(0).getRoot().toString();
		String newTree2 = pop.getNextPopulation().get(1).getRoot().toString();
		
		System.out.println("Tree 1: " + newTree1);
		System.out.println("Tree 2: " + newTree2);
		
		assertFalse(origTree1.equals(newTree1));
		assertFalse(origTree2.equals(newTree2));
	}
	
	@Test
	public void testCrossoverDefault() {
		System.out.println("Test CrossoverMode");
		EcOperator tree1 = EcNodeFactory.createOperator("/");
		EcOperand t1_l1 = EcNodeFactory.createOperand("1");
		EcOperand t1_r1 = EcNodeFactory.createOperand("1");
		tree1.setLeftChild(t1_l1);
		tree1.setRightChild(t1_r1);
		
		EcOperator tree2 = EcNodeFactory.createOperator("*");
		EcOperand t2_l1 = EcNodeFactory.createOperand("2");
		EcOperand t2_r1 = EcNodeFactory.createOperand("2");
		tree2.setLeftChild(t2_l1);
		tree2.setRightChild(t2_r1);
		
		EcTree t1 = new EcTree(tree1);
		EcTree t2 = new EcTree(tree2);
		
		ArrayList<EcTree> trees = new ArrayList<EcTree>();
		trees.add(t1);
		trees.add(t2);
		
		EcPopulation pop = new EcPopulation();
		pop.setNextPopulation(trees);
		
		EcPopulationCrossoverMode crossoverMode = new EcPopulationDefaultCrossoverMode(pop);
		
		String origTree1 = pop.getNextPopulation().get(0).getRoot().toString();
		String origTree2 = pop.getNextPopulation().get(1).getRoot().toString();
		
		System.out.println("Tree 1: " + origTree1);
		System.out.println("Tree 2: " + origTree2);
		
		crossoverMode.crossover();
		
		String newTree1 = pop.getNextPopulation().get(0).getRoot().toString();
		String newTree2 = pop.getNextPopulation().get(1).getRoot().toString();
		
		System.out.println("Tree 1: " + newTree1);
		System.out.println("Tree 2: " + newTree2);
		
		assertFalse(origTree1.equals(newTree1));
		assertFalse(origTree2.equals(newTree2));
		
	}
	
	@Test
	public void testEcPopulationDefaultSelectionMode() {
		System.out.println("Test DefaultSelectionMode");
		EcPopulation pop = new EcPopulation();
		EcMain.fillUpPopulation(pop.getCurrentPopulation());
				
		final Double INPUT[] = {-3.0,-2.0,-1.0,0.0,1.0,2.0,3.0};
        final Double OUTPUT[] = {4.0,1.5,0.0,-0.5,0.0,1.5,4.0};
        
		for (EcTree tree : pop.getCurrentPopulation()) {
			tree.calculateFitness(INPUT,OUTPUT);
		}
		
		assertTrue(pop.getCurrentPopulation().size()==60);
		
		EcPopulationDefaultSelectionMode selection = new EcPopulationDefaultSelectionMode(pop);
		selection.select();
		
		assertTrue(pop.getNextPopulation().size()==45);
		
		pop.displayNextPopulation();
	}

}
