package ec.main;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import ec.util.EcPropertyValues;



/**
 *  @author Duy
 *  @created 09-29-2015
 */

public class EcMain {
	
	public static void main(String[] args) {
		List<String> ecArgs = new ArrayList<>();
		ecArgs.addAll(Arrays.asList(args));
		long startTime = System.nanoTime();
		
		EcPopulation pop = new EcPopulation();
		EcTree targetFunction = new EcTree();
		double targetFitness = 0.01;
		boolean targetFitnessReached = false;
		// TODO: move to config file
		final Double INPUT[] = {-3.0,-2.0,-1.0,0.0,1.0,2.0,3.0};
		final Double OUTPUT[] = {4.0,1.5,0.0,-0.5,0.0,1.5,4.0};
		
		int fitnessPlateau = 0;
		double currentTopFitness = 0;
		double previousTopFitness = 0;
		final int startOver = 5000;
		
		List <Double> fitnesses = new ArrayList<Double>();
		
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
			}
			// no need to do crossover and mutation if target function is found
			if (targetFitnessReached) {
				break;
			}
			pop.doSelection();
			
			
			currentTopFitness = pop.getNextPopulation().get(0).getFitness();
			fitnesses.add(currentTopFitness);
			System.out.println("Top Fitness: " + currentTopFitness);
		
			if (currentTopFitness == previousTopFitness) {
				fitnessPlateau += 1;
			} 
			else {
				fitnessPlateau = 0;
			}
			previousTopFitness = currentTopFitness;
			
			if (fitnessPlateau > startOver) {
				fitnessPlateau = 0;
				pop.setNextPopulation(new ArrayList<EcTree>());
			} 
			else {
				
				EcTree clone = new EcTree(pop.getNextPopulation().get(0).getRoot().clone()); //Clone the most fit individual
				pop.doCrossover();
				pop.doMutation();
				
				pop.getNextPopulation().add(clone); //add the clone to the next population
			}
			
			fillUpPopulation(pop.getNextPopulation());
			pop.setCurrentPopulation(pop.getNextPopulation());
		}
		
		
		System.out.println("THIS IS THE TARGET FUNCTION");
		System.out.println(targetFunction.getRoot().toString());
		System.out.println("TARGET FITNESS = " + targetFunction.getFitness());
		
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

