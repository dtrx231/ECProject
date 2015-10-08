package ec.main;
import java.io.IOException;

/**
 *  @author Duy
 *  @created 09-29-2015
 */

public class EcMain {
	
	public static void main(String[] args) throws IOException {
		/*EcPropertyValues properties = new EcPropertyValues();
		properties.getPropValues();*/
		System.out.println();
		System.out.println(EcPropertyValues.getInstance().toString());
		System.out.println();
	/*	EcTree ecTree1 = EcTree.createExampleTree();
		ecTree1.displayTree();*/
		System.out.println();
		System.out.println("*************************");
		
		for (int i = 0; i < EcPropertyValues.getInstance().populationSize ; i++) {
			EcTree ecTree = new EcTree(EcPropertyValues.getInstance().maxHeight);
			ecTree.displayTree();
			System.out.println();		
		}			
	}

}
