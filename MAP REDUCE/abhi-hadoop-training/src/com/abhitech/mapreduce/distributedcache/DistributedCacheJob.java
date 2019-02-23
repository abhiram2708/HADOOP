package com.abhitech.mapreduce.distributedcache;

import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.filecache.DistributedCache;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class DistributedCacheJob implements Tool {
	private Configuration conf;
	@Override
	public Configuration getConf() {
		return conf; // Getting the configuration
	}
	@Override
	public void setConf(Configuration conf) {
		this.conf = conf; // Setting the configuration
	}

	@Override
	public int run(String[] args) throws Exception {
		Job job =  Job.getInstance(getConf());
		job.setJobName("Abhitech  Distributed Cache Job");
		job.setJarByClass(this.getClass());
		job.setMapperClass(DistributedCacheMapper.class);
		job.setNumReduceTasks(0);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(NullWritable.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(NullWritable.class);
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		Path input = new Path(args[0]);
		Path dcfile = new Path(args[1]);
		Path output = new Path(args[2]);
		URI[] uris = { dcfile.toUri() };
		//DistributedCache.setCacheFiles(uris, job.getConfiguration());
		job.setCacheFiles(uris);
		FileInputFormat.addInputPath(job, input);
		FileOutputFormat.setOutputPath(job, output);
		
		output.getFileSystem(conf).delete(output, true);
		return job.waitForCompletion(true) ? 0 : -1;
	}

	public static void main(String[] args) throws Exception {
		// if `status == 0` then `Job Success`
		// if `status == -1` then `Job Failure`
		int status = ToolRunner.run(new Configuration(), new DistributedCacheJob(), args);
		System.out.println("Job Status: " + status);
	}

}
