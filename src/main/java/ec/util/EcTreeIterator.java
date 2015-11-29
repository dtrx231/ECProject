package ec.util;

import java.util.Iterator;
import java.util.Stack;

import ec.nodes.EcNode;

public class EcTreeIterator implements Iterator<EcNode> {
	
	private Stack<EcNode> stack;
	
	public EcTreeIterator(EcNode root) {
		stack = new Stack<EcNode>();
		stack.push(root);
	}
	
	@Override
	public boolean hasNext() {
		return !stack.isEmpty();
	}

	@Override
	public EcNode next() {
		EcNode cur = stack.peek();
        if(cur.getLeftChild() != null) {
        	stack.push(cur.getLeftChild());
        }
        else {
        	EcNode tmp = stack.pop();
        	while( tmp.getRightChild() == null ) {
        		if(stack.isEmpty()) return cur;
        		tmp = stack.pop();
        	}
        	stack.push(tmp.getRightChild());
        }
        return cur;
	}
	
	public Stack<EcNode> getStack() {
		return stack;
	}

}
