package ec.analytics;

public class EcAnalyticsBean {
	private int batchSize;
	private double longestTotalTime;
	private double fastestTotalTime;
	private EcExecTimeBean meanExecTime;
	public int getBatchSize() {
		return batchSize;
	}
	public void setBatchSize(int batchSize) {
		this.batchSize = batchSize;
	}
	public double getLongestTotalTime() {
		return longestTotalTime;
	}
	public void setLongestTotalTime(double longestTotalTime) {
		this.longestTotalTime = longestTotalTime;
	}
	public double getFastestTotalTime() {
		return fastestTotalTime;
	}
	public void setFastestTotalTime(double fastestTotalTime) {
		this.fastestTotalTime = fastestTotalTime;
	}
	public EcExecTimeBean getMeanExecTime() {
		return meanExecTime;
	}
	public void setMeanExecTime(EcExecTimeBean meanExecTime) {
		this.meanExecTime = meanExecTime;
	}
	
}
