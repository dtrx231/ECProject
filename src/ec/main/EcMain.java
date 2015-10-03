package ec.main;

/**
 *  @author Duy
 *  @created 09-29-2015
 */

public class EcMain {
	public static final int MAX_DEPTH = 4;
	public static final int INITIAL_POPULATION_SIZE = 60;
	
	
	
	public static void main(String[] args) {
		System.out.println("*****TARGET FUNCTION*****");
		System.out.println();
		EcTree ecTree1 = EcTree.createExampleTree();
		ecTree1.displayTree();
		System.out.println();
		System.out.println();
		System.out.println("*************************");
		
		for (int i = 0; i < INITIAL_POPULATION_SIZE ; i++) {
			EcTree ecTree = new EcTree(3);
			ecTree.displayTree();
			System.out.println();		
		}			
	}

}
