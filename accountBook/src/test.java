import java.io.Console;

public class test {
	public static void main(String[] args) {
		System.out.println();
		Console a = System.console();
		String sample = a.readLine();
		System.out.println(sample);
	}
}
