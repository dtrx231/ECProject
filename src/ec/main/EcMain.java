package ec.main;
import java.io.IOException;
import java.util.List;

import ec.util.EcPropertyValues;


/**
 *  @author Duy
 *  @created 09-29-2015
 */

public class EcMain {
	
	public static void main(String[] args) throws IOException {
		
		long startTime = System.currentTimeMillis();
		
		EcPopulation pop = new EcPopulation();
		EcTree targetFunction = new EcTree();
		double targetFitness = 0.01;
		boolean targetFitnessReached = false;
		final Double INPUT[] = {-3.0,-2.0,-1.0,0.0,1.0,2.0,3.0};
		final Double OUTPUT[] = {4.0,1.5,0.0,-0.5,0.0,1.5,4.0} ;
		
		//initialize the population
		fillUpPopulation(pop.getCurrentPopulation());
		
		while (!targetFitnessReached) {
			// calculate fitness
			for (EcTree tree : pop.getCurrentPopulation()) {
				if( tree.calculateFitness(INPUT,OUTPUT) <= targetFitness) {
					targetFitnessReached = true;
					targetFunction = tree;
					break;
				}
				tree.displayTree();
			}
			// no need to do crossover and mutation if target function is found
			if (targetFitnessReached) {
				break;
			}
			pop.doSelection();
			pop.doCrossover();
			pop.doMutation();
			//pop.doClone();
			fillUpPopulation(pop.getNextPopulation());
			pop.setCurrentPopulation(pop.getNextPopulation());
		}
		
		long stopTime = System.currentTimeMillis();
		System.out.println("THIS IS THE TARGET FUNCTION");
		targetFunction.displayTree();
		System.out.println(" Total elapsed time: " +  (stopTime - startTime ) / 1000 + " seconds" );
		
	}
	
	public static void fillUpPopulation (List<EcTree> pop) {
		while (pop.size() < EcPropertyValues.getInstance().getPopulationSize()) {
			EcTree ecTree = new EcTree(EcPropertyValues.getInstance().getMaxHeight());
			if (ecTree.hasX()) {
				pop.add(ecTree);
			}
		}
	}
	

}
