package finalproject;
//FINAL UPDATED
import java.util.ArrayList;
import java.util.LinkedList;
public class GenderByKeyword extends DataAnalyzer {

	public GenderByKeyword(Parser p) {
		super(p);
	}

	@Override
	public MyHashTable<String, Integer> getDistByKeyword(String keyword) {
		String properKeyword = stringConverter(keyword);
		MyHashTable<String, Integer> finalTable = (MyHashTable<String, Integer>) super.nestedHash.get(properKeyword);

		return finalTable;

	}

	@Override
	public void extractInformation() {

		int genderIndex = super.parser.fields.get("gender");
		int commentsIndex = super.parser.fields.get("comments");

		for(int i = 0; i < super.parser.data.size(); i++) {

			String comment = stringConverter(super.parser.data.get(i)[commentsIndex]);
			comment = comment.replaceAll("[^a-z']+", " ");
			String gender = super.parser.data.get(i)[genderIndex];
			String[] comments = comment.trim().split("\\s+");
			ArrayList<String> tokens = removeDuplicates(comments);

			for(String token: tokens) {
				if (!containsHashmap(super.nestedHash, token)){
					super.nestedHash.put(token, new MyHashTable<>(3));
					MyHashTable<String, Integer> genderTable = (MyHashTable<String, Integer>) super.nestedHash.get(token);
					genderTable.put("M", 0);
					genderTable.put("F", 0);
					genderTable.put("X", 0);
				}
				MyHashTable<String, Integer> genderTable = (MyHashTable<String, Integer>) super.nestedHash.get(token);
				int numberofGender = genderTable.get(gender);
				genderTable.put(gender, numberofGender + 1);
			}
		}
	}
	private String stringConverter(String s){
		String correctedString = s.toLowerCase();
		correctedString = correctedString.trim();
		return correctedString;
	}

	private boolean containsHashmap(MyHashTable hashtable, String s) {
		return hashtable.get(s) != null;
	}
	private ArrayList<String> removeDuplicates(String [] strArr){
		MyHashTable<String, Integer> duplicateRemover = new MyHashTable<>();
		for(String s: strArr){
			duplicateRemover.put(s, 0);
		}
		return duplicateRemover.getKeySet();

	}

}
