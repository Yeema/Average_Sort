package average_sort;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.io.NullWritable;

public class SortMapper extends Mapper<Text, Text, SortPair, NullWritable> {
	
	
	
	public void map(Text key, Text value, Context context) throws IOException, InterruptedException {
		SortPair sp = new SortPair(key,Double.parseDouble(value.toString()));
		context.write(sp, NullWritable.get());
	}
	
}
