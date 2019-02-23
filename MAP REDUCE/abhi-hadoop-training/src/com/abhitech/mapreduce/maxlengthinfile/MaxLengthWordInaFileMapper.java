package com.abhitech.mapreduce.maxlengthinfile;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer.Context;

public class MaxLengthWordInaFileMapper extends Mapper<LongWritable, Text, Text, LongWritable> {

	String maxWord;

	@Override
	protected void setup(Context context) throws IOException, InterruptedException {
		maxWord = "";
	}

	@Override
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		String line = value.toString();
		String[] words = line.split(" ");
		for (String word : words) {
			if (word.length() >= maxWord.length()) {
				maxWord = word;
			}
		}
	}

	@Override
	protected void cleanup(Context context) throws IOException, InterruptedException {
		context.write(new Text(maxWord), new LongWritable(maxWord.length()));
	}

}
