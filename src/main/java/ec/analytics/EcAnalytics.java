package ec.analytics;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import ec.analytics.EcExecTimeBean;

public class EcAnalytics {
	public static void main(String arg[]) {
		Gson gson = new Gson();
		EcAnalyticsBean ecAnalyticsBean = new EcAnalyticsBean();
		EcBatchBean ecBatchBean = null;
		
		try {
			ecBatchBean = gson.fromJson(new FileReader("ecLog.json"), EcBatchBean.class);
		} catch (JsonSyntaxException | JsonIOException | FileNotFoundException e) {
			e.printStackTrace();
		}
		int batchSize = ecBatchBean.getEcBatch().size();
		double totalGenerationTime1 = 0;
		double totalGenerationTime2 = 0;
		double totalCalculateFitnessTime = 0;
		double totalSelectionTime = 0;
		double totalCloneTime = 0;
		double totalCrossOverTime = 0;
		double totalMutationTime = 0;
		double totalBestFitness = 0;
		double totalWorstFitness = 0;
		double totalTotalTime = 0;
		double fastestTotalTime = 0;
		double longestTotalTime = 0;
		for (EcExecTimeBean bean : ecBatchBean.getEcBatch()) {
			totalGenerationTime1 += bean.getGenerationTime1();
			totalGenerationTime2 += bean.getGenerationTime2();
			totalCalculateFitnessTime += bean.getCalculateFitnessTime();
			totalSelectionTime += bean.getSelectionTime();
			totalCloneTime += bean.getCloneTime();
			totalCrossOverTime += bean.getCrossOverTime();
			totalMutationTime += bean.getMutationTime();
			totalBestFitness += bean.getBestFitness();
			totalWorstFitness += bean.getWorstFitness();
			totalTotalTime += bean.getTotalTime();
			fastestTotalTime = fastestTotalTime <= bean.getTotalTime() ? fastestTotalTime : bean.getTotalTime();
			longestTotalTime = longestTotalTime >= bean.getTotalTime() ? longestTotalTime : bean.getTotalTime();
		}
		EcExecTimeBean meanExecTime = new EcExecTimeBean();
		meanExecTime.setGenerationTime1(totalGenerationTime1 / batchSize);
		meanExecTime.setGenerationTime2(totalGenerationTime2 / batchSize);
		meanExecTime.setCalculateFitnessTime(totalCalculateFitnessTime / batchSize);
		meanExecTime.setSelectionTime(totalSelectionTime / batchSize);
		meanExecTime.setCloneTime(totalCloneTime / batchSize);
		meanExecTime.setCrossOverTime(totalCrossOverTime / batchSize);
		meanExecTime.setMutationTime(totalMutationTime / batchSize);
		meanExecTime.setBestFitness(totalBestFitness / batchSize);
		meanExecTime.setWorstFitness(totalWorstFitness / batchSize);
		meanExecTime.setTotalTime(totalTotalTime / batchSize);
		
		ecAnalyticsBean.setBatchSize(batchSize);
		ecAnalyticsBean.setFastestTotalTime(fastestTotalTime);
		ecAnalyticsBean.setLongestTotalTime(longestTotalTime);
		ecAnalyticsBean.setMeanExecTime(meanExecTime);
		
	
		File file = new File("ecAnalytics.json");
		System.out.println("Analytics file was saved at " + file.getAbsolutePath());
		FileWriter fw;
		try {
			fw = new FileWriter(file);
			fw.write(gson.toJson(ecAnalyticsBean));
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
