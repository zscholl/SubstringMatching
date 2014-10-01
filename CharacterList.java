/* This class stores a string of characters in an ArrayList.*/
import java.io.*;
import java.util.*;

public class CharacterList {
	private List<Character> list; // for actual storage of characters

	/* Constructors */
	
	public CharacterList() {
		list = new ArrayList<Character>();
	}
	public CharacterList(List<Character> cList){
		list = cList;
	}
	/* This constructor makes a CharacterList out of a traditional Java string */
	public CharacterList(String str) {
		list = new ArrayList<Character>();
		boolean LastCharWhiteSpace = false;
		for (int i = 0; i < str.length(); i++) {
			if (Character.isWhitespace(str.charAt(i))) {
				if (!LastCharWhiteSpace) list.add(' ');
				LastCharWhiteSpace = true;
			}
			else {
				list.add(str.charAt(i));
				LastCharWhiteSpace = false;
			}
		}
	}
	
	/* This constructor makes a CharacterList out of the contents of a text file */
	public CharacterList(File file) throws IOException {
		list = new ArrayList<Character>();
		BufferedReader reader = new BufferedReader( new InputStreamReader(new FileInputStream(file)));
		int character;
		boolean LastCharWhiteSpace = false;
		while((character = reader.read()) != -1) {
			if (Character.isWhitespace(character)) {
				if (!LastCharWhiteSpace) list.add(' ');
				LastCharWhiteSpace = true;
			}
			else {
				list.add(new Character((char) character));
				LastCharWhiteSpace = false;
			}
		}
		if (list.get(list.size() - 1).charValue() == 32) list.remove(list.size() - 1);
		reader.close();
	}

	
	/* Member methods */
	
	/* This method returns the character at the given position in the CharacterList.
       It should look up the appropriate character in the list used for storage */	
	public Character getChar(int position) {
		//TODO: Fill in code here.
		return list.get(position);
	}

	/* This method returns the length of the string (i.e. the size of the list) */
	public int size() {
		//TODO: Fill in code here.
		return list.size();
	}
	@Override
	public int hashCode() {
		return (int) list.get(0) * (int)list.get(list.size() - 1) * (int)list.get(list.size() / 4) * (int)list.get((list.size()*3) / 4); 
	}
	
	public boolean checkIfSubstring(CharacterList searchstr, int method) throws IllegalArgumentException {
		if (method < 0 || method > 1) throw new IllegalArgumentException();
		
		int n = list.size(); // Length of the haystack string
		int m = searchstr.size(); // Length of the search string

		if (method == 0) {

			//TODO: Complete the code for the brute-force method in this branch.
			
			// We will use a variable called "start" to keep track of the
			// beginning position of the current portion of haystack
			// we are comparing.
			
			for (int start = 0; start < n - m + 1; start++) {
				int numMatches = 0;
				// If "searchstr" and the portion of haystack beginning
				// at "start" and continuing for m characters are identical
				// then return true.
				for (int i = 0; i < m; i++){
					if (searchstr.getChar(i).equals(this.getChar(start + i))){
						numMatches++;
					}
					if (numMatches == m ){
						return true;
					}
				}

			}
			return false; // If we get to this point, we've searched all substrings
			              // of the haystack and haven't found a match, so return false.
		}

		else {

			//TODO: Fill in code for the hashing method in this branch.
			
			// Compute the hash of the search string once.
			int strHash = searchstr.hashCode();
			
			// Compute the starting hash of the haystack (i.e. of its first m characters)
		
			
			for( int start = 0; start < n - m + 1; start++){
				CharacterList sublist = new CharacterList(list.subList(start, m + start));
				
				if (sublist.hashCode() == strHash){
					int numMatches = 0;
					for (int i = 0; i < m; i++){
						if (searchstr.getChar(i).equals(this.getChar(start + i))){
							numMatches++;
						}
						if (numMatches == m){
							return true;
						}
					}

				}
				
			}
			
			// If the hashes match, compare the two strings character by character.
			// If they match char by char, return true otherwise continue further.
			
			// Using a loop similar to the first branch of the if statement,
			// advance the portion of the haystack currently being examined
			// by one character and recompute/update its hash.
			// Then compare the hashes. If they match, compare the strings
			// character by character. If they match, return true, otherwise
			// keep going.
			
			return false; // If after the loop finishes and no match has been found
			              // return false because that substring doesn't exist
			              // in the haystack.
		}
	}

	/* This method prints the contents of the CharacterList. Use it for debugging. */
	public void print() {
		for (int i = 0; i < list.size(); i++)
			System.out.print(list.get(i));
		System.out.println();
	}
}
