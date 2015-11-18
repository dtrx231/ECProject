package ec.main;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONObject;

import ec.util.EcPropertyValues;


/**
 *  @author Duy
 *  @created 09-29-2015
 */

public class EcMain {
	
	public static void main(String[] args) throws IOException {
		
		long startTime = System.currentTimeMillis();
		JSONObject obj = new JSONObject();
		
		EcPopulation pop = new EcPopulation();
		EcTree targetFunction = new EcTree();
		double targetFitness = 0.01;
		boolean targetFitnessReached = false;
		final Double INPUT[] = {-3.0,-2.0,-1.0,0.0,1.0,2.0,3.0};
		final Double OUTPUT[] = {4.0,1.5,0.0,-0.5,0.0,1.5,4.0} ;
		FileWriter file = new FileWriter("/login.txt",true);
		List <Double> fitness= new ArrayList<Double>();
		
		long startSelection=0,selectionTime=0,startCrossOver=0,crossOverTime=0,startMutation=0,mutationtime = 0;
		long startGeneration=0,generationTime=0,startCalculatingFitness=0,calculateFitnessTime=0,startCloneTime=0,cloneTime=0;
		//initialize the population
		
		startGeneration = System.nanoTime();
		fillUpPopulation(pop.getCurrentPopulation());
		generationTime = System.nanoTime() - startGeneration;
		obj.put("generationTime",generationTime/1000000000.0);
		
		while (!targetFitnessReached) {
			startCalculatingFitness = System.nanoTime();
			// calculate fitness
			for (EcTree tree : pop.getCurrentPopulation()) {
				if( tree.calculateFitness(INPUT,OUTPUT) <= targetFitness) {
					targetFitnessReached = true;
					targetFunction = tree;
					break;
				}
			}
			calculateFitnessTime= System.nanoTime() - startGeneration;
			obj.put("calculateFitnessTime",calculateFitnessTime/1000000000.0);
			// no need to do crossover and mutation if target function is found
			if (targetFitnessReached) {
				break;
			}
			startSelection = System.nanoTime();
			pop.doSelection();
			selectionTime += System.nanoTime() - startSelection;
			obj.put("selectionTime",selectionTime/1000000000.0);
			
			System.out.println("Top Fitness Value: " + pop.getNextPopulation().get(0).getFitness());
			fitness.add(pop.getNextPopulation().get(0).getFitness());
			EcTree clone = new EcTree(pop.getNextPopulation().get(0).getRoot().clone()); //Clone the most fit individual
			
			startCrossOver = System.nanoTime();
			pop.doCrossover();
			crossOverTime += System.nanoTime() - startCrossOver;
			obj.put("crossOverTime",crossOverTime/1000000000.0 );
			
			startMutation = System.nanoTime();
			pop.doMutation();
			mutationtime += System.nanoTime() - startMutation;
			obj.put("mutationTime",mutationtime/1000000000.0);
			
			startMutation = System.nanoTime();
			pop.getNextPopulation().add(clone); //add the clone to the next population
			fillUpPopulation(pop.getNextPopulation());
			pop.setCurrentPopulation(pop.getNextPopulation());
			
		}
		
		long stopTime = System.currentTimeMillis();
		System.out.println("THIS IS THE TARGET FUNCTION");
		targetFunction.displayTree();
		obj.put("WorstFitness",fitness.get(0));
		obj.put("BestFitness",fitness.get(fitness.size()-1));
		
		System.out.println(" Total elapsed time: " +  (stopTime - startTime ) / 1000 + " seconds" );
		obj.put("Total elapsed time", (stopTime - startTime ) / 1000);
		file.write(obj.toJSONString()+",");
		file.close();
		
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
