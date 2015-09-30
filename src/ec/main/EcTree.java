
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
    	root = createOperatorNode();
    	createChildNodes(root, height);
    }
    
    /**
     * Display tree in equation form
     * For example: our target function will look like (((x * x )- 1 )/ 2 )
     * Can be parsed by tools such as WolframAlpha to get a more readable form.
     */
    
    public void displayTree() {
    	printInOrder(this.root);
    }
    
    
    // inorder traversal
    private void printInOrder(EcNode node) {
    	if(node == null) {
    		return;
    	}
    	if (node.isOperator) {
    		System.out.print( "(" );
    	}
    	printInOrder(node.leftChild);
    	System.out.print(node.data + " ");
    	printInOrder(node.rightChild);	
    	if (node.isOperator) {
    		System.out.print( ")" );
    	}
    	
    }
    
    public int compute(int x) {
    	// TODO : to be implemented
    	return 0;
    }
    
    public static EcTree createExampleTree() {
    	EcNode ecRoot = new EcNode();
    	ecRoot.data = "/";
    	ecRoot.isOperator = true;
    			
		EcTree ecTree = new EcTree(ecRoot);
		ecRoot.leftChild = new EcNode();
		ecRoot.leftChild.data = "-";
		ecRoot.leftChild.isOperator = true;
		ecRoot.rightChild = new EcNode();
		ecRoot.rightChild.data = "2";
		ecRoot.rightChild.isOperator = false;
		
		ecRoot.leftChild.leftChild = new EcNode();
		ecRoot.leftChild.leftChild.data = "*";
		ecRoot.leftChild.leftChild.isOperator = true;
		ecRoot.leftChild.rightChild = new EcNode();
		ecRoot.leftChild.rightChild.data = "1";
		ecRoot.leftChild.rightChild.isOperator = false;
		
		
		ecRoot.leftChild.leftChild.leftChild = new EcNode();
		ecRoot.leftChild.leftChild.leftChild.data = "x";
		ecRoot.leftChild.leftChild.leftChild.isOperator = false;
		
		ecRoot.leftChild.leftChild.rightChild = new EcNode();
		ecRoot.leftChild.leftChild.rightChild.data = "x";
		ecRoot.leftChild.leftChild.rightChild.isOperator = false;
		return ecTree;
    }
    
    /**
     * 
     * a recursive function that populates an inital node with child nodes until 
     * either the specifed is reached or all the leaves are operators
     */
    
    public static void createChildNodes(EcNode node, int height) {
    	if (height == 1 && node.isOperator) {
    		node.leftChild = createOperandNode();
			node.rightChild = createOperandNode();
    	}
    	else {
    		if (node.isOperator) {
    		node.leftChild = createRandomNode();
			node.rightChild = createRandomNode();
			createChildNodes(node.leftChild, height - 1);
			createChildNodes(node.rightChild, height - 1);
    		}
    	}
		
	}
    /**
     * Useful for initializing the root node
     * @return a random operator node. Possible values are + - / *
     */
    public static EcNode createOperatorNode() {
		EcNode ecRoot = new EcNode();
		Random rn = new Random();
		String operator;
		int ranInt = rn.nextInt(4) + 1;
		switch(ranInt){
			case 1: operator = "+"; break;
			case 2: operator = "-"; break;
			case 3: operator = "/"; break;
			case 4: operator = "*"; break;
			default: operator = null;
		}
		
		ecRoot.data = operator;
		ecRoot.isOperator = true;
		
		//System.out.println(operator);
		return ecRoot;
	}
    /**
     * 
     * @return a random operand node. Possible values of 0-9 or x
     */
    public static EcNode createOperandNode() {
		EcNode ecRoot = new EcNode();
		Random rn = new Random();
		String operand;
		Integer ranInt = rn.nextInt(14);
		switch(ranInt){
			case 10: 
			case 11:
			case 12:
			case 13:
			case 14: operand = "x"; break;
			default: operand = ranInt.toString();
		}
		
		ecRoot.data = operand;
		ecRoot.isOperator = false;
		
		//System.out.println(operand);
		return ecRoot;
	}
    
    
    /**
     * 
     * @return a random operand or operator node. Possible values of 0-9 or x or + - / *
     */
    public static EcNode createRandomNode() {
    	EcNode ecNode = new EcNode();
		Random rn = new Random();
		boolean isOperator;
		String randomNode;
		Integer ranInt = rn.nextInt(30);
		switch(ranInt){
			case 10: randomNode = "+"; isOperator = true; break;
			case 11: 
			case 12:
			case 13: randomNode = "*"; isOperator = true; break;
			case 14: 
			case 15:
			case 16: randomNode = "/"; isOperator = true; break;
			case 17: 
			case 18:
			case 19: randomNode = "-"; isOperator = true; break;
			case 20:
			case 21:
			case 22:
			case 23:
			case 24:
			case 25:
			case 26:
			case 27:
			case 28:
			case 29:
			case 30: randomNode = "x"; isOperator = false; break;
			default: randomNode = ranInt.toString();  isOperator = false;
		}
		
		ecNode.data = randomNode;
		ecNode.isOperator = isOperator;
		
		return ecNode;
	}

	public double getFitness() {
		return fitness;
	}

	public void setFitness(double fitness) {
		this.fitness = fitness;
	}
}
