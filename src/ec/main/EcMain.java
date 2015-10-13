package ec.main;
import java.io.IOException;

/**
 *  @author Duy
 *  @created 09-29-2015
 */

public class EcMain {
	
	public static void main(String[] args) throws IOException {
		System.out.println();
		System.out.println(EcPropertyValues.getInstance().toString());
		System.out.println();
		System.out.println();
		System.out.println("*************************");
		
		for (int i = 0; i < EcPropertyValues.getInstance().populationSize ; i++) {
			EcTree ecTree = new EcTree(EcPropertyValues.getInstance().maxHeight);
			ecTree.displayTree();
			System.out.println();		
		}	
		
		EcPopulation pop = new EcPopulation();
		EcTree targetFunction = new EcTree();
		double targetFitness = 1.0;
		boolean targetFitnessReached = false;
		double[] trainingData =  {-3.0,-2.0,-1.0,0.0,1.0,2.0,3.0};
		
		//initialize the population
		for (int i = 0; i < EcPropertyValues.getInstance().populationSize; i++) {
			EcTree ecTree = new EcTree(EcPropertyValues.getInstance().maxHeight);
			pop.getCurrentPopulation().add(ecTree);
		}
		
		while (!targetFitnessReached) {
			// calculate fitness
			for (EcTree tree : pop.getCurrentPopulation()) {
				if( tree.calculateFitness(trainingData) <= targetFitness) {
					targetFitnessReached = true;
					targetFunction = tree;
					break;
				}
			}
			// no need to do crossover and mutation if target function is found
			if (targetFitnessReached) {
				break;
			}
			pop.doCrossover();
			pop.doMutation();
			pop.doSelection();
			pop.doClone();
			pop.setCurrentPopulation(pop.getNextPopulation());
			
		}
		
		targetFunction.displayTree();
	}

}
