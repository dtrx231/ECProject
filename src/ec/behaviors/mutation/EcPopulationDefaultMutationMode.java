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
			for (EcNode n : e) {
				// look for a non-empty node
				if (n.getLeftChild() != null) {
					if (EcUtils.byChance(10)) { // decide whether to mutate
						// randomly pick between left and right child
						if (EcUtils.flipCoin()) {
							EcUtils.mutateLeftChild(n);
						} 
						else {
							EcUtils.mutateRightChild(n);
						}

					}

				}

			}
		}
	}

	

}
