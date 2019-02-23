package com.abhitech.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.Table;

public class DropTable {
	public static void main(String[] args) {
		try {

			Configuration conf = HBaseConfiguration.create(new Configuration());
			Connection connection = ConnectionFactory.createConnection(conf);

			Admin admin =  connection.getAdmin();

			// disabling table named emp
			admin.disableTable(TableName.valueOf("table1"));

			// Deleting emp
			admin.deleteTable(TableName.valueOf("table1"));
			System.out.println("Table deleted");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
