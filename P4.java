import java.io.*;

public class P4 {

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		if (args.length != 3) {
			System.out.println("Usage: java P4 <haystack_file> <searchstr_file> <method>");
			System.exit(0);
		}
		
		CharacterList haystack, searchstr;
		int method;
		try {
			haystack = new CharacterList(new File(args[0]));
			searchstr = new CharacterList(new File(args[1]));
			method = Integer.parseInt(args[2]);

			if (haystack.checkIfSubstring(searchstr, method))
				System.out.println("true");
			else System.out.println("false");
			long endTime = System.currentTimeMillis();
			System.out.println("Running time is: " + ((endTime - startTime)/1000.000)  + " seconds" );
		}
		catch (IllegalArgumentException e) {
			System.out.println("An illegal argument was passed in.");			
		}
		catch (IOException e) {
			System.out.println("An IOException occurred.");
		}
	}
}
