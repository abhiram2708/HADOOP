package com.abhitech.mapreduce.distributedcache;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class DistributedCacheMapper extends Mapper<LongWritable, Text, Text, NullWritable> {

	private FSDataInputStream stream;
	BufferedReader br;

	@Override
	protected void setup(Context context) throws java.io.IOException, InterruptedException {
		Configuration conf = context.getConfiguration();
		URI[] uris = context.getCacheFiles();
		FileSystem fs = FileSystem.get(uris[0], conf);
		stream = fs.open(new Path(uris[0]));
		br = new BufferedReader(new InputStreamReader(stream));
	};

	@Override
	protected void map(LongWritable key, Text value, Context context) throws java.io.IOException, InterruptedException {
		stream.seek(0);
		String inputLine = value.toString();
		int index = inputLine.indexOf(",");
		String leftJoinKey = inputLine.substring(0, index);
		String leftRow = inputLine.substring(index + 1);
		String dcLine = br.readLine();
		while (dcLine != null) {
			int dcindex = dcLine.indexOf(",");
			String rightJoinKey = dcLine.substring(0, dcindex);
			String rightRow = dcLine.substring(dcindex + 1);
			if (leftJoinKey.equals(rightJoinKey)) {
				String row = leftJoinKey + "," + leftRow + "," + rightRow;
				context.write(new Text(row), NullWritable.get());
				break;
			}
		}
	};

	@Override
	protected void cleanup(Context context) throws java.io.IOException, InterruptedException {
		stream.close();
	};
}