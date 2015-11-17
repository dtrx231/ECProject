package ec.behaviors.selection;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import ec.main.EcPopulation;
import ec.main.EcTree;
import ec.util.EcPropertyValues;

public class EcPopulationDefaultSelectionMode implements EcPopulationSelectionMode {
	private EcPopulation population;
	
	public EcPopulationDefaultSelectionMode(EcPopulation pop) {
		this.population = pop;
	}
	
	@Override
	public void select() {
		
		Collections.sort(population.getCurrentPopulation(), new Comparator<EcTree>() {
			@Override
			public int compare(EcTree e1, EcTree e2) {
				return Double.compare(e1.getFitness(),e2.getFitness());
			}
		});
		population.setNextPopulation(new ArrayList<EcTree>());
		for (int i = 0 ; i < EcPropertyValues.getInstance().getProbSelection(); i++) {
			population.getNextPopulation().add(population.getCurrentPopulation().get(i));
		}
	}
}
