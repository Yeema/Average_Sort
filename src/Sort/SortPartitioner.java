package average_sort;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;
import org.apache.hadoop.io.NullWritable;

public class SortPartitioner extends Partitioner<SortPair, NullWritable> {
	
	private double maxValue = 11.05;
	private double minValue = 9.0;

	@Override
	public int getPartition(SortPair key, NullWritable value, int numReduceTasks) {
		
		int num = 0;
		double interval = (maxValue-minValue)/numReduceTasks;
		// customize which <K ,V> will go to which reducer
		// Based on defined min/max value and numReduceTasks
		num = ((int)((key.getAverage()-minValue)/interval))%numReduceTasks;
		num = numReduceTasks - num - 1;
		return num;
	}
}
