package ec.analytics;

public class EcExecTimeBean {
	private double generationTime1;
	private double generationTime2;
	private double calculateFitnessTime;
	private double selectionTime;
	private double cloneTime;
	private double crossOverTime;
	private double mutationTime;
	private double bestFitness;
	private double worstFitness;
	private double totalTime;
	private String targetFunction;
	private double targetFunctionFitness;
	
	public double getGenerationTime1() {
		return generationTime1;
	}
	public void setGenerationTime1(double generationTime1) {
		this.generationTime1 = generationTime1;
	}
	public double getGenerationTime2() {
		return generationTime2;
	}
	public void setGenerationTime2(double generationTime2) {
		this.generationTime2 = generationTime2;
	}
	public double getCalculateFitnessTime() {
		return calculateFitnessTime;
	}
	public void setCalculateFitnessTime(double calculateFitnessTime) {
		this.calculateFitnessTime = calculateFitnessTime;
	}
	public double getSelectionTime() {
		return selectionTime;
	}
	public void setSelectionTime(double selectionTime) {
		this.selectionTime = selectionTime;
	}
	public double getCloneTime() {
		return cloneTime;
	}
	public void setCloneTime(double cloneTime) {
		this.cloneTime = cloneTime;
	}
	public double getCrossOverTime() {
		return crossOverTime;
	}
	public void setCrossOverTime(double crossOverTime) {
		this.crossOverTime = crossOverTime;
	}
	public double getMutationTime() {
		return mutationTime;
	}
	public void setMutationTime(double mutationTime) {
		this.mutationTime = mutationTime;
	}
	public double getBestFitness() {
		return bestFitness;
	}
	public void setBestFitness(double bestFitness) {
		this.bestFitness = bestFitness;
	}
	public double getWorstFitness() {
		return worstFitness;
	}
	public void setWorstFitness(double worstFitness) {
		this.worstFitness = worstFitness;
	}
	public double getTotalTime() {
		return totalTime;
	}
	public void setTotalTime(double totalTime) {
		this.totalTime = totalTime;
	}
	public String getTargetFunction() {
		return targetFunction;
	}
	public void setTargetFunction(String targetFunction) {
		this.targetFunction = targetFunction;
	}
	public double getTargetFunctionFitness() {
		return targetFunctionFitness;
	}
	public void setTargetFunctionFitness(double targetFunctionFitness) {
		this.targetFunctionFitness = targetFunctionFitness;
	}
}
