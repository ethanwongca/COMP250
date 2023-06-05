package finalproject;
//FINAL UPDATED
import java.util.ArrayList;

public class RatingByGender extends DataAnalyzer{

	public RatingByGender(Parser p) {
		super(p);
	}

	@Override
	public MyHashTable<String, Integer> getDistByKeyword(String keyword) {
		//HashMap = rating HashMapTwo = difficulty
		String properKeyword = stringConverter(keyword);
		String[] updatedKeyword = properKeyword.trim().split(",");
		if(!updatedKeyword[0].equalsIgnoreCase("x")) {
			if ((updatedKeyword[1].equals("quality") || updatedKeyword[1].equals(" quality"))) {
				MyHashTable<String, Integer> finalTable = (MyHashTable<String, Integer>) super.nestedHash.get(updatedKeyword[0]);
				return finalTable;
			} else if (updatedKeyword[1].equals("difficulty") || updatedKeyword[1].equals(" difficulty")) {
				MyHashTable<String, Integer> finalTable = (MyHashTable<String, Integer>) super.nestedHashTwo.get(updatedKeyword[0]);
				return finalTable;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	@Override
	public void extractInformation() {

		int genderIndex = super.parser.fields.get("gender");
		int difficultyIndex = super.parser.fields.get("student_difficult");
		int ratingIndex = super.parser.fields.get("student_star");
		for(int i = 0; i < super.parser.data.size(); i++){
			String gender = stringConverter(super.parser.data.get(i)[genderIndex]);
			String rating = String.valueOf((int) Double.parseDouble(super.parser.data.get(i)[ratingIndex]));
			String difficulty = String.valueOf((int) Double.parseDouble(super.parser.data.get(i)[difficultyIndex]));
			if(!containsHashmap(super.nestedHash, gender)){
				//nestedHash is rating, nestedHashTwo is difficulty
				super.nestedHash.put(gender, new MyHashTable<>(5));
				super.nestedHashTwo.put(gender, new MyHashTable<>(5));
				MyHashTable<String, Integer> ratingsTable = (MyHashTable<String, Integer>) super.nestedHash.get(gender);
				MyHashTable<String, Integer> difficultyTable = (MyHashTable<String, Integer>) super.nestedHashTwo.get(gender);
				for (int n = 1; n < 6; n++) {
					ratingsTable.put(String.valueOf(n), 0);
					difficultyTable.put(String.valueOf(n), 0);
				}
			}
			//Just double up everything
			MyHashTable<String, Integer> ratingsTable = (MyHashTable<String, Integer>) super.nestedHash.get(gender);
			int tempRating  = ratingsTable.get(rating);
			ratingsTable.put(rating, tempRating + 1);
			MyHashTable<String, Integer> difficultyTable = (MyHashTable<String, Integer>) super.nestedHashTwo.get(gender);
			int tempDifficulty  = difficultyTable.get(difficulty);
			difficultyTable.put(difficulty, tempDifficulty + 1);
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


}
