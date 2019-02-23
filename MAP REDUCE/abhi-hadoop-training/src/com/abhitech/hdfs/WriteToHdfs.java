package com.abhitech.hdfs;

import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

public class WriteToHdfs {
	public static void main(String[] args) throws Exception {
		String uri = "hdfs://localhost:9000/home/chenchu/hdfs/wordcount";
		Configuration conf = new Configuration();
		FileSystem fs = FileSystem.get(URI.create(uri), conf);

		FSDataInputStream in = null;
		FSDataOutputStream out = fs.create(new Path("hdfs://localhost:9000/home/chenchu/hdfs/hdfswritefile"));

		try {
			in = fs.open(new Path(uri));
			IOUtils.copyBytes(in, out, 4096, false);
			System.out.println("File Successfully written");
		} finally {
			IOUtils.closeStream(in);
		}
	}
}
