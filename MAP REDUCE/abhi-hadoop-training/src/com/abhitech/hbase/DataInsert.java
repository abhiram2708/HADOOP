package com.abhitech.hbase;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

public class DataInsert {

	public static void main(String[] args) throws IOException {

		Configuration c = HBaseConfiguration.create(new Configuration()); // Instantiate Configuration class

		Connection connection = ConnectionFactory.createConnection(c);
		Table table = connection.getTable(TableName.valueOf("table1"));
		// use table as needed, the table returned is lightweight

		Put P1 = new Put(Bytes.toBytes("row1")); // Instantiate put Class

		// accepts column family name, row name and its value

		P1.addColumn(Bytes.toBytes("CF1"), Bytes.toBytes("id"), Bytes.toBytes("20"));

		P1.addColumn(Bytes.toBytes("CF1"), Bytes.toBytes("name"), Bytes.toBytes("rishi"));

		table.put(P1);

		System.out.println("Data is inserted"); // Save the put Instance to the HTable.

		table.close(); // close HTable

	}

}