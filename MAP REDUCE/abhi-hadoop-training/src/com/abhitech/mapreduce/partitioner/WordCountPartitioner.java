package com.abhitech.mapreduce.partitioner;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class WordCountPartitioner extends Partitioner<Text, LongWritable> {

	@Override
	public int getPartition(Text key, LongWritable value, int noOfReducers) {
		String word = key.toString().toLowerCase();
		char firstChar = word.charAt(0);
		int diff = Math.abs(firstChar - 'a') % noOfReducers;
		return diff;
	}

}
