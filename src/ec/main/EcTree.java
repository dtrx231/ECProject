
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
    	//printInOrder(this.root);
		System.out.print(this.root.toString() + " = " + this.root.calculateOutput(10));
    }
    
    
    // inorder traversal
	/*
    private void printInOrder(EcNode node) {
    	if(node == null) {
    		return;
    	}
    	if (node.getType().equals("Operator")) {
    		System.out.print( "(" );
    	}
    	printInOrder(node.leftChild);
    	System.out.print(node.data + " ");
    	printInOrder(node.rightChild);	
    	if (node.getType().equals("Operator")) {
    		System.out.print( ")" );
    	}
    	
    }
    */
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
    
	//Implement this similar to the print
    public static void createChildNodes(EcNode node, int height) {
    	if (height == 1 && node.getType().equals("Operator")) {
    		node.leftChild = createOperandNode();
			node.rightChild = createOperandNode();
    	}
    	else {
    		if (node.getType().equals("Operator")) {
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
    public static EcOperator createOperatorNode() {
		EcOperator ecRoot = new EcOperator();
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
		return ecRoot;
	}
    /**
     * 
     * @return a random operand node. Possible values of 0-9 or x
     */
    public static EcOperand createOperandNode() {
		EcOperand ecRoot = new EcOperand();
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
		
		//System.out.println(operand);
		return ecRoot;
	}
    
    
    /**
     * 
     * @return a random operand or operator node. Possible values of 0-9 or x or + - / *
     */
    public static EcNode createRandomNode() {
    	String type = "";
		Random rn = new Random();
		Integer ranInt = rn.nextInt(30);
		switch(ranInt){
			case 10: type = "Operator"; break;
			case 11: 
			case 12:
			case 13: type = "Operand"; break;
			case 14: 
			case 15:
			case 16: type = "Operator"; break;
			case 17: 
			case 18:
			case 19: type = "Operand"; break;
			case 20:
			case 21:
			case 22:
			case 23:
			case 24:
			case 25: type = "Operator"; break;
			case 26:
			case 27:
			case 28:
			case 29:
			case 30: type = "Operand"; break;
			default: type = "Operand";
		}
		
		if (type.equals("Operand")) {
			return createOperandNode();
		}
		else {
			return createOperatorNode();
		}
	}

	public double getFitness() {
		return fitness;
	}

	public void setFitness(double fitness) {
		this.fitness = fitness;
	}
}
