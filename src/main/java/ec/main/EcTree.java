
package ec.main;
import java.util.ArrayList;
import java.util.Iterator;
import ec.nodes.EcNode;
import ec.nodes.EcNodeFactory;
import ec.util.EcTreeIterator;


/**
 *  @author Duy
 *  @created 09-29-2015
 */
public class EcTree implements Iterable<EcNode> {
	
    private EcNode root;
	private int height;
    private double fitness;
    
    public EcTree(){};
  
    public EcTree(EcNode root){
    	this.root = root;
    }
    
    public EcTree(int height) {
    	this.root = EcNodeFactory.createRandomNode(EcNodeFactory.OPERATOR);
    	this.setHeight(height);
    	this.root.spawnRandomNode(height);
    }
	
	public EcNode getRoot() {
		return root;
	}

	public void setRoot(EcNode root) {
		this.root = root;
	}

	public double getFitness() {
		return fitness;
	}

	public void setFitness(double fitness) {
		this.fitness = fitness;
	}
    
    /**
     * Display tree in equation form
     * For example: our target function will look like (((x * x )- 1 )/ 2 )
     * Can be parsed by tools such as WolframAlpha to get a more readable form.
     */
    
    public void displayTree() {
		System.out.println(this.root.toString() + " = " + this.getFitness());
    }
    
    public double calculateFitness(Double[] input, Double[] output) {
    	
    	ArrayList<Double> out = new ArrayList<Double>();
		for (int i = 0; i < input.length ; i++) {
			out.add(this.root.calculateOutput(input[i]));
		}
		
		double fit = 0;
		for (int i = 0; i < output.length ; i++) {
			fit += Math.abs(out.get(i) - output[i]);
		}
		this.fitness=fit;
    	return fit;
      }
   
   public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	@Override
	public Iterator<EcNode> iterator() {
		return new EcTreeIterator(root);
	}

	public boolean hasX() {
		boolean hasX = false;
		for (EcNode node : this) {
			if (node.checkData("x")) {
				hasX = true;
				break;
			}
		}
		return hasX;
	}
}
