package ec.main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
*  @author Dane
*  @created 10-8-2015
*/

public class EcPropertyValues {
	
	private InputStream inputStream;
	int maxHeight;
	private String targetFunction;
	private String inputData;
	int populationSize;
	private String[] operands;
	private String[] operators;
	public String[] getOperands() {
		return operands;
	}

	public void setOperands(String[] operands) {
		this.operands = operands;
	}

	public String[] getOperators() {
		return operators;
	}

	public void setOperators(String[] operators) {
		this.operators = operators;
	}

	int probSelection;
	int probCopy;
	int probMutate;
	int probCrossover;
	
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
		
		try {
			Properties prop = new Properties();
			String propFileName = "resources/config.properties";
			
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
			this.probCopy = Integer.parseInt(prop.getProperty("probCopy"));
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
			"; Copy Probability: " + this.probCopy + "; Mutate Probability: " + this.probMutate + "; Crossover Probability: " +
			this.probCrossover + ";";
	}
	
}