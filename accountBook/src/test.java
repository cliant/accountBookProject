import java.io.Console;

public class test {
	public static void main(String[] args) {
		Console a = System.console();
		String sample = a.readLine();
		System.out.println(sample);
	}
}
