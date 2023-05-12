package finalproject;
//FINAL UPDATED
public class RatingDistributionByProf extends DataAnalyzer {

	public RatingDistributionByProf(Parser p) {
		super(p);
		// Call the extractInformation() method here to initialize the hash table
	}

	@Override
	public MyHashTable<String, Integer> getDistByKeyword(String keyword) {
		String properKeyword = stringConverter(keyword);
		MyHashTable<String, Integer> finalTable = (MyHashTable<String, Integer>) super.nestedHash.get(properKeyword);
		// ADD YOUR CODE BELOW THIS
		return finalTable;
		//ADD YOUR CODE ABOVE THIS
	}

	@Override
	public void extractInformation() {
		// ADD YOUR CODE BELOW THIS
		//setup both indexes from the parser
		int profIndex = super.parser.fields.get("professor_name");
		int ratingIndex = super.parser.fields.get("student_star");
		for(int i = 0; i < super.parser.data.size(); i++){
			String profName = stringConverter(super.parser.data.get(i)[profIndex]); //convert prof to lowercase
			String rating = String.valueOf((int) Double.parseDouble(super.parser.data.get(i)[ratingIndex]));
			if(!containsHashmap(super.nestedHash, profName)) {
				//if not prof in thehashmap must add
				super.nestedHash.put(profName, new MyHashTable<>(5));
				MyHashTable<String, Integer> ratingsTable = (MyHashTable<String, Integer>) super.nestedHash.get(profName);
				for (int n = 1; n < 6; n++) {
					ratingsTable.put(String.valueOf(n), 0);
				}
			}
			//otherwise add the rating dict
			MyHashTable<String, Integer> ratingsTable = (MyHashTable<String, Integer>) super.nestedHash.get(profName);
			int tempRating  = ratingsTable.get(rating);
			ratingsTable.put(rating, tempRating + 1);

		}



		//ADD YOUR CODE ABOVE THIS
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