package ec.main;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import ec.util.EcPropertyValues;



/**
 *  @author Duy
 *  @created 09-29-2015
 */

public class EcMain {
	
	public static void main(String[] args) {
		List<String> ecArgs = new ArrayList<>();
		ecArgs.addAll(Arrays.asList(args));
		long startTime = System.currentTimeMillis();
		JSONObject obj = new JSONObject();
		
		EcPopulation pop = new EcPopulation();
		EcTree targetFunction = new EcTree();
		double targetFitness = 0.01;
		boolean targetFitnessReached = false;
		final Double INPUT[] = {-3.0,-2.0,-1.0,0.0,1.0,2.0,3.0};
		final Double OUTPUT[] = {4.0,1.5,0.0,-0.5,0.0,1.5,4.0} ;
		
		
		List <Double> fitness= new ArrayList<Double>();
		
		long startSelection=0,selectionTime=0,startCrossOver=0,crossOverTime=0,startMutation=0,mutationtime = 0;
		long startGeneration1=0,startGeneration2=0,generationTime1=0,generationTime2=0,startCalculatingFitness=0,calculateFitnessTime=0,startCloneTime=0,cloneTime=0;
		//initialize the population
		startGeneration1 = System.nanoTime();
		fillUpPopulation(pop.getCurrentPopulation());
		generationTime1 = System.nanoTime() - startGeneration1;
		
		
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
			calculateFitnessTime += System.nanoTime() - startCalculatingFitness;
			
			// no need to do crossover and mutation if target function is found
			if (targetFitnessReached) {
				break;
			}
			startSelection = System.nanoTime();
			pop.doSelection();
			selectionTime += System.nanoTime() - startSelection;
			
			
			startCloneTime = System.nanoTime();
			System.out.println("Top Fitness Value: " + pop.getNextPopulation().get(0).getFitness());
			fitness.add(pop.getNextPopulation().get(0).getFitness());
			EcTree clone = new EcTree(pop.getNextPopulation().get(0).getRoot().clone()); //Clone the most fit individual
			cloneTime += System.nanoTime() - startCloneTime;
			
			
			startCrossOver = System.nanoTime();
			pop.doCrossover();
			crossOverTime += System.nanoTime() - startCrossOver;
			
			
			startMutation = System.nanoTime();
			pop.doMutation();
			mutationtime += System.nanoTime() - startMutation;
			
			//Prune trees less than or equal to height of 1
			if (ecArgs.contains("-p")) {
				pop.pruneTrees(1); 
			}
			//replace with the first operand with x if an individual doesn't have x
			if (ecArgs.contains("-f")) {
				pop.forceX();
			}
			
			pop.getNextPopulation().add(clone); //add the clone to the next population
			startGeneration2 = System.nanoTime();
			fillUpPopulation(pop.getNextPopulation());
			generationTime2 += System.nanoTime() - startGeneration2;
			pop.setCurrentPopulation(pop.getNextPopulation());
			
		}
		
		long stopTime = System.currentTimeMillis();
		System.out.println("THIS IS THE TARGET FUNCTION");
		targetFunction.displayTree();
		System.out.println(" Total elapsed time: " +  (stopTime - startTime ) / 1000 + " seconds" );
		
		try {
			obj.put("generationTime1",generationTime1/1000000000.0);
			obj.put("generationTime2",generationTime2/1000000000.0);
			obj.put("totalgenerationTime", (generationTime1+generationTime2)/1000000000.0);
			obj.put("calculateFitnessTime",calculateFitnessTime/1000000000.0);
			obj.put("selectionTime",selectionTime/1000000000.0);
			obj.put("cloneTime", cloneTime/1000000000.0);
			obj.put("crossOverTime",crossOverTime/1000000000.0 );
			obj.put("mutationTime",mutationtime/1000000000.0);
			Collections.sort(fitness);
			obj.put("bestFitness",fitness.get(0));
			obj.put("worstFitness",fitness.get(fitness.size()-1));
			obj.put("Total elapsed time", (stopTime - startTime ) / 1000);
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
		
		try {
			File file = new File("/EcLog.txt");
			System.out.println("Output file was saved at " + file.getAbsolutePath());
			FileWriter fw = new FileWriter(file, true);
			fw.write(obj.toString()+",");
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
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

