package com.abhitech.mapreduce.maxlengthinline;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MaxLengthWordInaLineMapper extends Mapper<LongWritable, Text, Text, LongWritable> {
	@Override
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		String line = value.toString();
		String[] words = line.split(" ");
		String maxWord = "";
		for (String word : words) {
			if (word.length() >= maxWord.length()) {
				maxWord = word;
			}
		}
		context.write(new Text(maxWord), new LongWritable(maxWord.length()));
	}
}
