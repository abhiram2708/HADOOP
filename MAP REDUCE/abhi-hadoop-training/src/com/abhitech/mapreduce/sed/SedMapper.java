package com.abhitech.mapreduce.sed;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class SedMapper extends Mapper<LongWritable, Text, Text, NullWritable> {
	@Override
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		String line = value.toString();

		Configuration conf = context.getConfiguration();
		String pattern = conf.get("sed.pattern");
		String replace = conf.get("sed.replace");
		// then replace it, otherwise print it
		if (line.contains(pattern)) {
			String newline = line.replaceAll(pattern, replace);
			context.write(new Text(newline), NullWritable.get());
		} else {
			context.write(value, NullWritable.get());
		}
	}
}
