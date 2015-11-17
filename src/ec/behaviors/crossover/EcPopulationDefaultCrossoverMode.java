package ec.behaviors.crossover;

import java.util.Iterator;
import java.util.Random;

import ec.main.EcPopulation;
import ec.main.EcTree;
import ec.nodes.EcNode;
import ec.nodes.EcOperator;
import ec.util.EcUtils;


public class EcPopulationDefaultCrossoverMode implements EcPopulationCrossoverMode{
	
	private EcPopulation population;
	
	public EcPopulationDefaultCrossoverMode(EcPopulation pop) {
		this.population = pop;
	}
	@Override
	public void crossover() {
		// TODO Auto-generated method stub
		Random rn = new Random();
		
		for (int i = 0 ; i < population.getNextPopulation().size(); i++) {
			EcTree e1 = population.getNextPopulation().get(i);
			EcTree e2 = population.getNextPopulation().get(rn.nextInt(population.getNextPopulation().size()));
			crossover(e1,e2);
		}
		
	}
	
	private void crossover(EcTree e1, EcTree e2) {
		boolean targetFound = false;
		
		Iterator<EcNode> iter = e1.iterator();
		while (iter.hasNext() && !targetFound ) {
			EcNode source = iter.next();
			if (source instanceof EcOperator && EcUtils.flipCoin() ) {
				for (EcNode target : e2) {
					if (target instanceof EcOperator && EcUtils.flipCoin()) {
						// since target is an operator, we are guaranteed to have a left child and a right child
						EcNode leftNodeTmp = target.getLeftChild();
						EcNode rightNodeTemp = target.getRightChild();
						target.setLeftChild(source.getLeftChild());
						target.setRightChild(source.getRightChild());
						source.setLeftChild(leftNodeTmp);
						source.setRightChild(rightNodeTemp);
						targetFound = true;
						break;
					}
				}
			}
		}
	}
	
	

}
