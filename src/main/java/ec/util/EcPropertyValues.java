package ec.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
*  @author Dane
*  @created 10-8-2015
*/

public class EcPropertyValues {
	
	private int maxHeight;
	private String targetFunction;
	private String inputData;
	private int populationSize;
	private int probSelection;
	private int probClone;
	private int probMutate;
	private int probCrossover;
	private String[] operands;
	private String[] operators;
	
	public int getMaxHeight() {
		return maxHeight;
	}

	public String getTargetFunction() {
		return targetFunction;
	}

	public String getInputData() {
		return inputData;
	}

	public int getPopulationSize() {
		return populationSize;
	}

	public int getProbSelection() {
		return probSelection;
	}

	public int getProbClone() {
		return probClone;
	}

	public int getProbMutate() {
		return probMutate;
	}

	public int getProbCrossover() {
		return probCrossover;
	}

	public String[] getOperands() {
		return operands;
	}

	public String[] getOperators() {
		return operators;
	}

	private static volatile EcPropertyValues instance = null;
	
	private EcPropertyValues() {this.getPropValues();}
	
	public static synchronized EcPropertyValues getInstance() {
		if (instance == null) {
			instance = new EcPropertyValues();
		}
		return instance;
	}
	
	/*
	* Open a connection to properties.config and read settings
	*/
	
	private void getPropValues() {
		InputStream inputStream =  null;
		
		try {
			Properties prop = new Properties();
			String propFileName = "config.properties";
			
			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
			
			if (inputStream != null) {
				prop.load(inputStream);
			} 
			else {
				throw new FileNotFoundException("Property config file not found.");
			}
			this.maxHeight = Integer.parseInt(prop.getProperty("maxHeight"));
			this.targetFunction = prop.getProperty("targetFunction");
			this.inputData = prop.getProperty("inputData");
			this.populationSize = Integer.parseInt(prop.getProperty("populationSize"));
			this.operands = prop.getProperty("operands").split(",");
			this.operators = prop.getProperty("operators").split(",");
			this.probSelection = Integer.parseInt(prop.getProperty("probSelection"));
			this.probClone = Integer.parseInt(prop.getProperty("probClone"));
			this.probMutate = Integer.parseInt(prop.getProperty("probMutate"));
			this.probCrossover = Integer.parseInt(prop.getProperty("probCrossover"));
			
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}	
	}
	
	public String toString() {
		return "CONFIG SETTINGS: Max Height: " + this.maxHeight + "; Target Function: " + this.targetFunction +
			"; Input Data: " + this.inputData + "; Population Size: " + this.populationSize + "; Operands: " +
			this.operands + "; Operators: " + this.operators + "; Selection Probability: " + this.probSelection +
			"; Copy Probability: " + this.probClone + "; Mutate Probability: " + this.probMutate + "; Crossover Probability: " +
			this.probCrossover + ";";
	}
	
}