package meta;

public class Greeter {
	public String greetings(String ... args) {
		for(int i = 0; i < args.length; i++) {
			System.out.print(args[i] + " ");
		}
		return "done";
	}
}
