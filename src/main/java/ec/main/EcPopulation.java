package ec.main;

import java.util.ArrayList;
import java.util.List;

import ec.behaviors.crossover.EcPopulationCrossoverMode;
import ec.behaviors.crossover.EcPopulationDefaultCrossoverMode;
import ec.behaviors.mutation.EcPopulationDefaultMutationMode;
import ec.behaviors.mutation.EcPopulationMutationMode;
import ec.behaviors.selection.EcPopulationDefaultSelectionMode;
import ec.behaviors.selection.EcPopulationSelectionMode;
import ec.nodes.EcNode;
import ec.nodes.EcOperand;

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
		setCrossoverMode(new EcPopulationDefaultCrossoverMode(this));
		setSelectionMode(new EcPopulationDefaultSelectionMode(this));
		setMutationMode(new EcPopulationDefaultMutationMode(this));
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
		System.out.println("Doing clone");
	}
	
	/*
	*	Remove trees from population that are less than or equal to the specified height. This will ensure a minimum height is maintained in the 
	*	population
	*/
	
	public void pruneTrees(int height) {
		for(int i=0 ; i < this.nextPopulation.size() ; i++) {
			if(this.nextPopulation.get(i).getRoot().getDepth() <= height) {
				this.nextPopulation.remove(i);
			}
		}
	}
	public void forceX() {
		for (EcTree e : this.nextPopulation) {
			if (!e.hasX()) {
				for (EcNode n : e) {
					if (n instanceof EcOperand) {
						n.setData("x");
						break;
					}
				}
			}
		}
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
	public void displayCurrentPopulation() {
		for (EcTree tree: currentPopulation) {
			tree.displayTree();
		}
	}
	public void displayNextPopulation() {
		for (EcTree tree: nextPopulation) {
			tree.displayTree();
		}
	}
}
