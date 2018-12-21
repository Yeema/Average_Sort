package average_sort;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class AverageReducer extends Reducer<Text,SumCountPair,Text,DoubleWritable> {
	
    public void reduce(Text key, Iterable<SumCountPair> values, Context context) throws IOException, InterruptedException {
		// compute the average of same word
		int sum = 0;
		int count = 0;
		for (SumCountPair val: values) {
			sum += val.getSum();
			count += val.getCount();
        }
		// write the result
		context.write(key,new DoubleWritable((double)sum/(double)count));
	}
}
