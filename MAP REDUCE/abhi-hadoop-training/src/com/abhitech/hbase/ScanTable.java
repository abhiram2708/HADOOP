package com.abhitech.hbase;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;

public class ScanTable {

	public static void main(String args[]) throws IOException {

		Configuration conf = HBaseConfiguration.create(new Configuration());
		Connection connection = ConnectionFactory.createConnection(conf);

		Table table = connection.getTable(TableName.valueOf("table1"));

		// instantiate the Scan class
		Scan scan = new Scan();

		// scan the columns
		scan.addColumn(Bytes.toBytes("CF1"), Bytes.toBytes("id"));
		scan.addColumn(Bytes.toBytes("CF1"), Bytes.toBytes("name"));

		// get the ResultScanner
		ResultScanner scanner = table.getScanner(scan);
		for (Result result = scanner.next(); result != null; result = scanner.next()) {
			String id = Bytes.toString(result.getValue(Bytes.toBytes("CF1"), Bytes.toBytes("id")));
			String name = Bytes.toString(result.getValue(Bytes.toBytes("CF1"), Bytes.toBytes("name")));
			System.out.println(id + "   " + name);
		}

		scanner.close();
	}
}
