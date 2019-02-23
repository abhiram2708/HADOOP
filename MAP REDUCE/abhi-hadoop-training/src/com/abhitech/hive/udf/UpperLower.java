package com.abhitech.hive.udf;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;

@Description(name = "lowerupper", value = "Accept the String input returns lower and upper letter by letter", extended = "Example: _FUNC_('abhitech') = AbHiTeCh")
public class UpperLower extends UDF {
	public Text evaluate(Text input) {
		String out = "";
		if (input != null) {
			for (int i = 0; i < input.toString().length(); i++) {
				char c;
				if ((i) % 2 == 0) {
					c = Character.toUpperCase(input.toString().charAt(i));
				} else {
					c = Character.toLowerCase(input.toString().charAt(i));
				}
				out = out + "" + c;
			}
			return new Text(out);

		} else {
			return new Text(out);
		}
	}

}
