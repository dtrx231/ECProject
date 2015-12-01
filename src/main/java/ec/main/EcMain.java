package ec.main;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.google.gson.Gson;

import ec.analytics.EcExecTimeBean;
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
		EcExecTimeBean execTimeBean = new EcExecTimeBean();
		
		EcPopulation pop = new EcPopulation();
		EcTree targetFunction = new EcTree();
		final double targetFitness = 0.01;
		boolean targetFitnessReached = false;
		// TODO: move to config file
		final Double INPUT[] = {-3.0,-2.0,-1.0,0.0,1.0,2.0,3.0};
		final Double OUTPUT[] = {4.0,1.5,0.0,-0.5,0.0,1.5,4.0};
		
		int fitnessPlateau = 0;
		double currentTopFitness = 0;
		double previousTopFitness = 0;
		final int startOver = 5000;
		
		List <Double> fitnesses = new ArrayList<Double>();
		// TODO: refactor with aop
		long startSelection=0,selectionTime=0,startCrossOver=0,crossOverTime=0,startMutation=0,mutationtime = 0;
		long startGeneration1=0,startGeneration2=0,generationTime1=0,generationTime2=0,startCalculatingFitness=0,calculateFitnessTime=0,startCloneTime=0,cloneTime=0;
		//initialize the population
		startGeneration1 = System.nanoTime();
		fillUpPopulation(pop.getCurrentPopulation());
		generationTime1 = System.nanoTime() - startGeneration1;
		
		ExecutorService ecExecutor = Executors.newFixedThreadPool(4);
		while (!targetFitnessReached) {
			startCalculatingFitness = System.nanoTime();
			// calculate fitness
			
			Set<Callable<Integer>> cfTasks = new HashSet<>();
			for (EcTree tree : pop.getCurrentPopulation()) {
				
				cfTasks.add((new Callable<Integer>() {
					
					@Override
					public Integer call() throws Exception {
						// TODO Auto-generated method stub
						tree.calculateFitness(INPUT,OUTPUT);
						return 0;
					}
				}));
			}
			try {
				ecExecutor.invokeAll(cfTasks);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			
			for (EcTree tree : pop.getCurrentPopulation()) {
				if (tree.getFitness() <= targetFitness)
					targetFitnessReached = true;
					targetFunction = tree;
					break;
			}
			calculateFitnessTime += System.nanoTime() - startCalculatingFitness;
			
			// no need to do crossover and mutation if target function is found
			if (targetFitnessReached) {
				break;
			}
			startSelection = System.nanoTime();
			pop.doSelection();
			selectionTime += System.nanoTime() - startSelection;
			
			currentTopFitness = pop.getNextPopulation().get(0).getFitness();
			fitnesses.add(currentTopFitness);
			
			if (ecArgs.contains("-k")) {
				if (currentTopFitness == previousTopFitness) {
					fitnessPlateau += 1;
				} 
				else {
					fitnessPlateau = 0;
				}
				previousTopFitness = currentTopFitness;
			}
			if (ecArgs.contains("-k") && fitnessPlateau > startOver) {
				fitnessPlateau = 0;
				pop.setNextPopulation(new ArrayList<EcTree>());
			} 
			else {
				startCloneTime = System.nanoTime();
				System.out.println("Top Fitness Value: " + currentTopFitness);
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
			}
			startGeneration2 = System.nanoTime();
			fillUpPopulation(pop.getNextPopulation());
			generationTime2 += System.nanoTime() - startGeneration2;
			pop.setCurrentPopulation(pop.getNextPopulation());
		}
		
		long stopTime = System.nanoTime();
		System.out.println("THIS IS THE TARGET FUNCTION");
		System.out.println(targetFunction.getRoot().toString());
		System.out.println(" Total elapsed time: " +  (stopTime - startTime ) / 1000000000.0 + " seconds" );
		ecExecutor.shutdown();
		try {
			ecExecutor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		// package 
		execTimeBean.setGenerationTime1(generationTime1/1000000000.0);
		execTimeBean.setGenerationTime2(generationTime2/1000000000.0);
		execTimeBean.setCalculateFitnessTime(calculateFitnessTime/1000000000.0);
		execTimeBean.setSelectionTime(selectionTime/1000000000.0);
		execTimeBean.setCloneTime(cloneTime/1000000000.0);
		execTimeBean.setCrossOverTime(crossOverTime/1000000000.0 );
		execTimeBean.setMutationTime(mutationtime/1000000000.0);
		Collections.sort(fitnesses);
		execTimeBean.setBestFitness(fitnesses.get(0));
		execTimeBean.setWorstFitness(fitnesses.get(fitnesses.size()-1));
		execTimeBean.setTotalTime((stopTime - startTime ) / 1000000000.0);
		execTimeBean.setTargetFunction(targetFunction.getRoot().toString());
		execTimeBean.setTargetFunctionFitness(targetFunction.getFitness());
		// save
		Gson gson = new Gson();
		File file = new File("ecLog.json");
		System.out.println("Output file was saved at " + file.getAbsolutePath());
		FileWriter fw;
		try {
			fw = new FileWriter(file, true);
			fw.write(gson.toJson(execTimeBean)+",");
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

