package com.abhitech.mapreduce.counters;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


public class CounterMapper extends Mapper<LongWritable, Text, Text, NullWritable> {

	@Override
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		String line = value.toString();

		for (String word : line.split(" ")) {
			if (word.equals("Bangalore")) {
				context.getCounter(Cities.Bangalore).increment(1);
			} else if (word.equals("Hyderabad")) {
				context.getCounter(Cities.Hyderabad).increment(1);
			} else if (word.equals("Chennai")) {
				context.getCounter(Cities.Chennai).increment(1);
			}
		}
		context.write(value, NullWritable.get());
	}
}
