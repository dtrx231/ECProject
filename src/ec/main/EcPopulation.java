package ec.main;

import java.util.ArrayList;
import java.util.List;

import ec.behaviors.crossover.EcPopulationCrossoverMode;
import ec.behaviors.crossover.EcPopulationDefaultCrossoverMode;
import ec.behaviors.mutation.EcPopulationDefaultMutationMode;
import ec.behaviors.mutation.EcPopulationMutationMode;
import ec.behaviors.selection.EcPopulationDefaultSelectionMode;
import ec.behaviors.selection.EcPopulationSelectionMode;

/**
 *  @author Duy
 *  @created 10-12-2015
 */

public class EcPopulation {
	private List<EcTree> currentPopulation;
	private List<EcTree> nextPopulation;
	private EcPopulationCrossoverMode crossoverMode;
	private EcPopulationMutationMode mutationMode;
	private EcPopulationSelectionMode selectionMode;
	
	public EcPopulation() {
		currentPopulation = new ArrayList<EcTree>();
		nextPopulation = new ArrayList<EcTree>();
		setCrossoverMode(new EcPopulationDefaultCrossoverMode());
		setSelectionMode(new EcPopulationDefaultSelectionMode());
		setMutationMode(new EcPopulationDefaultMutationMode());
	}
	
	public void doCrossover() {
		crossoverMode.crossover();
	}
	public void doSelection() {
		selectionMode.select();
	}
	public void doMutation() {
		mutationMode.mutate();
	}
	public void doClone() {
		//TODO: to be implemented
	}
	
	public List<EcTree> getCurrentPopulation() {
		return currentPopulation;
	}
	public void setCurrentPopulation(List<EcTree> currentPopulation) {
		this.currentPopulation = currentPopulation;
	}
	public List<EcTree> getNextPopulation() {
		return nextPopulation;
	}
	public void setNextPopulation(List<EcTree> nextPopulation) {
		this.nextPopulation = nextPopulation;
	}
	
	public EcPopulationCrossoverMode getCrossoverMode() {
		return crossoverMode;
	}
	public void setCrossoverMode(EcPopulationCrossoverMode crossoverMode) {
		this.crossoverMode = crossoverMode;
	}
	public EcPopulationMutationMode getMutationMode() {
		return mutationMode;
	}
	public void setMutationMode(EcPopulationMutationMode mutationMode) {
		this.mutationMode = mutationMode;
	}
	public EcPopulationSelectionMode getSelectionMode() {
		return selectionMode;
	}
	public void setSelectionMode(EcPopulationSelectionMode selectionMode) {
		this.selectionMode = selectionMode;
	}
	
}
