import java.util.StringJoiner;

public class Test {

	public static void main(String[] args) {

		StringJoiner sj = new StringJoiner("-");
		sj.add("avc");
		sj.add("ccc");
		sj.add("ddd");
		System.out.println(sj.toString());
	}

}
