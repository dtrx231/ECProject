import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import ec.util.EcPropertyValues;

public class EcPropertyValuesTest {

	@Test
	public void test() {
		assertTrue(EcPropertyValues.getInstance().getMaxHeight()==4);
		assertTrue(EcPropertyValues.getInstance().getTargetFunction().equals("(((2*(x*x))-2)/4)"));
		String input = "-3.0,-2.0,-1.0,0.0,1.0,2.0,3.0";
		assertTrue(EcPropertyValues.getInstance().getInputData().equals(input));
		assertTrue(EcPropertyValues.getInstance().getPopulationSize()==60);
		String[] operands = {"0","1","2","3","4","5","6","7","8","9","x"};
		assertTrue(Arrays.equals(EcPropertyValues.getInstance().getOperands(),operands));
		String[] operators = {"+","-","*","/"};
		assertTrue(Arrays.equals(EcPropertyValues.getInstance().getOperators(),operators));
		assertTrue(EcPropertyValues.getInstance().getProbSelection()==45);
		
	}

}
