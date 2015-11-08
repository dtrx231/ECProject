package ec.behaviors.mutation;

import ec.main.EcPopulation;
import ec.main.EcTree;
import ec.nodes.EcNode;
import ec.util.EcUtils;

public class EcPopulationDefaultMutationMode implements EcPopulationMutationMode {
	
	private EcPopulation population;
	
	public EcPopulationDefaultMutationMode(EcPopulation pop) {
		this.population = pop;
	}
	
	@Override
	public void mutate() {
		for (EcTree e : population.getNextPopulation()) {
			if (EcUtils.flipCoin()) {
				for (EcNode n : e) {
					if (EcUtils.flipCoin()) {
						n.mutate();
					}
				}
			}
		}
	}
}
