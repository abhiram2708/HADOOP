package com.abhitech.mapreduce.maxlengthinfile;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class MaxLengthWordInaFileReducer extends
		Reducer<Text, LongWritable, Text, LongWritable> {
	String maxWord;
	@Override
	protected void setup(Context context)
			throws IOException, InterruptedException {
		maxWord = "";
	}
	@Override
	protected void reduce(Text key, Iterable<LongWritable> values,
			Context context) throws IOException, InterruptedException {
		String word = key.toString();
		if (word.length() >= maxWord.length()) {
			maxWord = word;
		}
	}
	@Override
	protected void cleanup(Context context)
			throws IOException, InterruptedException {
		context.write(new Text(maxWord), new LongWritable(maxWord.length()));
	}
}


















