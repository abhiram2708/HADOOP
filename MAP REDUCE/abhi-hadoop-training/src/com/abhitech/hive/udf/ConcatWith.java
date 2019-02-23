package com.abhitech.hive.udf;

import java.util.StringJoiner;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;

@Description(name = "concatwith", value = "takes seperator and comma seperated columns returned concatnated string ", extended = "Example: _FUNC_('abhi','tech','-') = abhi-tech")
public class ConcatWith extends UDF {
	
	public Text evaluate(Text seperator, Text... input) {
		if (seperator != null) {
			StringJoiner sj = new StringJoiner(seperator.toString());
			for (Text t : input) {
				if (t == null) {
					return null;
				} else {
					sj.add(t.toString());
				}
			}
			return new Text(sj.toString());

		} else {
			throw new RuntimeException("pass valid seperator....");
		}

	}

}
