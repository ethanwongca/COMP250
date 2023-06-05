package finalproject;
//FINAL UPDATED
import java.util.ArrayList;

public class RatingByKeyword extends DataAnalyzer {

	public RatingByKeyword(Parser p) {
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
		int ratingIndex = super.parser.fields.get("student_star");
		int commentsIndex = super.parser.fields.get("comments");

		for(int i = 0; i < super.parser.data.size(); i++) {
			String comment = stringConverter(super.parser.data.get(i)[commentsIndex]);
			comment = comment.replaceAll("[^a-z']+", " ");
			String[] comments = comment.trim().split("\\s+");
			ArrayList<String> tokens = removeDuplicates(comments);
			String rating = String.valueOf((int) Double.parseDouble(super.parser.data.get(i)[ratingIndex]));
			for(String token: tokens) {
				if(!containsHashmap(super.nestedHash, token)) {
					super.nestedHash.put(token, new MyHashTable<>(5));
					MyHashTable<String, Integer> ratingsTable = (MyHashTable<String, Integer>) super.nestedHash.get(token);
					for (int n = 1; n < 6; n++) {
						ratingsTable.put(String.valueOf(n), 0);
					}
				}
				MyHashTable<String, Integer> ratingsTable = (MyHashTable<String, Integer>) super.nestedHash.get(token);
				int tempRating  = ratingsTable.get(rating);
				ratingsTable.put(rating, tempRating + 1);
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
