package com.abhitech.hbase;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

public class DeleteData {

	public static void main(String[] args) throws IOException {

		Configuration conf = HBaseConfiguration.create(new Configuration());
		Connection connection = ConnectionFactory.createConnection(conf);

		Table table = connection.getTable(TableName.valueOf("table1"));

		// instantiate Delete class
		Delete delete = new Delete(Bytes.toBytes("row1"));
		 // delete row data
		 delete.addColumn(Bytes.toBytes("CF1"), Bytes.toBytes("id"));
		 //delete column family
		delete.addFamily(Bytes.toBytes("CF1"));

		// delete the data
		table.delete(delete);

		table.close();
		System.out.println("data deleted successfully.....");
	}
}