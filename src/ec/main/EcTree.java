
package ec.main;
import java.util.Random;


/**
 *  @author Duy
 *  @created 09-29-2015
 */
public class EcTree {
	
    private EcNode root;
    private double fitness;
    
  
    public EcTree(EcNode root){
    	this.root = root;
    }
    
    public EcTree(int height) {
    	root = new EcOperator();
    	createChildNodes(root, height, false);
    }
    
    /**
     * Display tree in equation form
     * For example: our target function will look like (((x * x )- 1 )/ 2 )
     * Can be parsed by tools such as WolframAlpha to get a more readable form.
     */
    
    public void displayTree() {
		System.out.print(this.root.toString() + " = " + this.root.calculateOutput(10));
    }
    
    public int compute(int x) {
    	// TODO : to be implemented
    	return 0;
    }
    
    public static EcTree createExampleTree() {
    	EcOperator ecRoot = new EcOperator();
    	ecRoot.data = "/";
    			
		EcTree ecTree = new EcTree(ecRoot);
		ecRoot.leftChild = new EcOperator();
		ecRoot.leftChild.data = "-";
		ecRoot.rightChild = new EcOperand();
		ecRoot.rightChild.data = "2";
		
		ecRoot.leftChild.leftChild = new EcOperator();
		ecRoot.leftChild.leftChild.data = "*";
		ecRoot.leftChild.rightChild = new EcOperand();
		ecRoot.leftChild.rightChild.data = "1";
	
		ecRoot.leftChild.leftChild.leftChild = new EcOperand();
		ecRoot.leftChild.leftChild.leftChild.data = "x";
		
		ecRoot.leftChild.leftChild.rightChild = new EcOperand();
		ecRoot.leftChild.leftChild.rightChild.data = "x";
		return ecTree;
    }
    
    /**
     * 
     * a recursive function that populates an inital node with child nodes until 
     * either the specifed is reached or all the leaves are operators
     */
    
	public static void createChildNodes(EcNode node, int height, boolean hasX) {
		
		boolean xval = hasX;
		
		if (height == 1) {
			if (xval == true) {
				node.leftChild = new EcOperand();
				node.rightChild = new EcOperand();
			}
			else {
				Random rn = new Random();
				Integer ranInt = rn.nextInt(50)+1;
				if (ranInt <= 25) {
					node.leftChild = new EcOperand("x");
					node.rightChild = new EcOperand();
				}
				else {
					node.leftChild = new EcOperand();
					node.rightChild = new EcOperand("x");
				}
			}
		}
		else {
			node.leftChild = createRandomNode();
			node.rightChild = createRandomNode();
			if ((node.leftChild.data.equals("x") || node.rightChild.data.equals("x")))  {
				xval = true;
			}
			if (node.leftChild instanceof EcOperator) {
				createChildNodes(node.leftChild, height - 1, xval);
			}
			if (node.rightChild instanceof EcOperator) {
				createChildNodes(node.rightChild, height - 1, xval);
			}
			if (node.leftChild instanceof EcOperand && node.rightChild instanceof EcOperand && !xval) {
				Random rn = new Random();
				Integer ranInt = rn.nextInt(50)+1;
				if (ranInt <= 25) {
					node.leftChild.data = "x";
				}
				else {
					node.rightChild.data = "x";
				}
			}
		}
	}
    
    /**
     * 
     * @return a random operand or operator node. Possible values of 0-9 or x or + - / *
     */
    public static EcNode createRandomNode() {
		Random rn = new Random();
		Integer ranInt = rn.nextInt(50)+1;
		if (ranInt <= 25) {
			return new EcOperator();
		}
		else {
			return new EcOperand();
		}

	}

	public double getFitness() {
		return fitness;
	}

	public void setFitness(double fitness) {
		this.fitness = fitness;
	}
}
