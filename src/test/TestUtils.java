package test;

import ec.main.EcTree;
import ec.nodes.EcNode;
import ec.nodes.EcOperator;
import ec.util.EcPropertyValues;

import static ec.nodes.EcNodeFactory.*;

public class TestUtils {
	public static EcTree createExampleTree() {
    	EcOperator ecRoot = createOperator(DIVIDE);
    	
    	EcTree ecTree = new EcTree(ecRoot);
		ecRoot.setLeftChild(createOperator(SUBTRACT));
		ecRoot.setRightChild(createOperand("2"));
		
		ecRoot.getLeftChild().setLeftChild(createOperator(MULTIPLY));
		
		ecRoot.getLeftChild().setRightChild(createOperand("1"));
	
		ecRoot.getLeftChild().getLeftChild().setLeftChild(createOperand("x"));
		ecRoot.getLeftChild().getLeftChild().setRightChild(createOperand("x"));
		
		return ecTree;
    }
	
	public static EcTree createExampleTreeNoX() {
    	EcOperator ecRoot = createOperator(DIVIDE);
    	
    	EcTree ecTree = new EcTree(ecRoot);
		ecRoot.setLeftChild(createOperator(SUBTRACT));
		ecRoot.setRightChild(createOperand("2"));
		
		ecRoot.getLeftChild().setLeftChild(createOperator(MULTIPLY));
		
		ecRoot.getLeftChild().setRightChild(createOperand("1"));
	
		ecRoot.getLeftChild().getLeftChild().setLeftChild(createOperand("2"));
		ecRoot.getLeftChild().getLeftChild().setRightChild(createOperand("4"));
		
		return ecTree;
    }
	
	public static void main(String args[]) {
		
		System.out.println();
		System.out.println(EcPropertyValues.getInstance().toString());
		System.out.println();
		System.out.println();
		System.out.println("*************************");
		
		for (int i = 0; i < 100 ; i++) {
			EcTree ecTree = new EcTree(4);
			if (ecTree.hasX()) {
				ecTree.displayTree();
			}
		}	
		
		
		EcTree ecTree = createExampleTree();
		ecTree.displayTree();
		
		for (EcNode ecn : ecTree) {
			System.out.println();
			System.out.println(ecn.getData());
		}
	}
}
