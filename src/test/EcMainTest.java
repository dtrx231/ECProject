package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Random;

import org.junit.Test;

import ec.behaviors.selection.EcPopulationDefaultSelectionMode;
import ec.main.EcMain;
import ec.main.EcPopulation;
import ec.main.EcTree;
import ec.util.EcPropertyValues;

public class EcMainTest {

	final Double INPUT[] = {-3.0,-2.0,-1.0,0.0,1.0,2.0,3.0};
	final Double OUTPUT[] = {4.0,1.5,0.0,-0.5,0.0,1.5,4.0} ;
	final int SELECTION_PROB = EcPropertyValues.getInstance().getProbSelection();
	final int POPULATION_SIZE =  EcPropertyValues.getInstance().getPopulationSize();
	final double TARGET_FITNESS = 1.0;

	@Test
	public void test() {
		
		
		EcTree ecTree = TestUtils.createExampleTree();
		System.out.println("Target function");
		ecTree.displayTree();
		assertEquals("Checking calculateOutput() 1 " , 4,  ecTree.getRoot().calculateOutput(-3), 0.000000000000001 );
		assertEquals("Checking calculateOutput() 2 " , -0.5,  ecTree.getRoot().calculateOutput(0), 0.000000000000001 );
		assertEquals("Fitness of target function should be 0" , 0,  ecTree.calculateFitness(INPUT, OUTPUT), 0.000000000000001 );
		assertEquals("Fitness of target function should be set", 0 , ecTree.getFitness(), 0.000000000000001);
		Random rn = new Random();
		for ( int i = 0; i < 10 ; i++) {
			System.out.println(rn.nextInt(2));
		}
		
		// Test basic population operations
		
		EcPopulation pop = new EcPopulation();
		EcMain.fillUpPopulation(pop.getCurrentPopulation());
		assertEquals("Population size check", POPULATION_SIZE , pop.getCurrentPopulation().size());
		for (EcTree e : pop.getCurrentPopulation()) {
			e.calculateFitness(INPUT, OUTPUT);
		}
		pop.setSelectionMode(new EcPopulationDefaultSelectionMode(pop));
		assertTrue("Selection mode check", pop.getSelectionMode() instanceof EcPopulationDefaultSelectionMode);
		pop.doSelection();
		assertEquals("Population size check", SELECTION_PROB, pop.getNextPopulation().size());
		EcMain.fillUpPopulation(pop.getNextPopulation());
		assertEquals("Population size check", POPULATION_SIZE , pop.getNextPopulation().size());
		pop.setCurrentPopulation(pop.getNextPopulation());
		assertEquals("Population size check", POPULATION_SIZE, pop.getCurrentPopulation().size());
		for (EcTree e : pop.getCurrentPopulation()) {
			e.calculateFitness(INPUT, OUTPUT);
		}
		
		
		// Test basic population 
		pop.displayCurrentPopulation();
	}
	
	@Test
	public void testMainLoop() {
		boolean targetFitnessReached = false;
		EcTree targetFunction = new EcTree();
		EcPopulation pop = new EcPopulation();
		EcMain.fillUpPopulation(pop.getCurrentPopulation());
		pop.setSelectionMode(new EcPopulationDefaultSelectionMode(pop));
		assertTrue("Selection mode check", pop.getSelectionMode() instanceof EcPopulationDefaultSelectionMode);
		while (!targetFitnessReached) {
			// calculate fitness
			for (EcTree tree : pop.getCurrentPopulation()) {
				tree.displayTree();
				if( tree.calculateFitness(INPUT,OUTPUT) <= TARGET_FITNESS) {
					targetFitnessReached = true;
					targetFunction = tree;
					break;
				}
			}
			// no need to do crossover and mutation if target function is found
			if (targetFitnessReached) {
				break;
			}
			pop.doSelection();
			assertEquals("Population size check", SELECTION_PROB, pop.getNextPopulation().size());
			pop.doCrossover();
			pop.doMutation();
			//pop.doClone();
			// fill up the population
			EcMain.fillUpPopulation(pop.getNextPopulation());
			assertEquals("Population size check", POPULATION_SIZE , pop.getNextPopulation().size());
			pop.setCurrentPopulation(pop.getNextPopulation());
			assertEquals("Population size check", POPULATION_SIZE, pop.getCurrentPopulation().size());
			pop.setNextPopulation(new ArrayList<EcTree>());
			
		}
		System.out.println("THIS IS THE TARGET FUNCTION");
		targetFunction.displayTree();
	}

}
