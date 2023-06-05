package finalproject;
//FINAL UPDATED
import java.util.ArrayList;

public class RatingDistributionBySchool extends DataAnalyzer {

	public RatingDistributionBySchool(Parser p) {
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
		MyHashTable<String, MyHashTable<String, Integer>> tempMap = new MyHashTable();
		int profIndex = super.parser.fields.get("professor_name");
		int ratingIndex = super.parser.fields.get("student_star");
		int schoolIndex = super.parser.fields.get("school_name");
		for(int i = 0; i < super.parser.data.size(); i++) {
			String profName = stringConverter(super.parser.data.get(i)[profIndex]) + "," + stringConverter(super.parser.data.get(i)[schoolIndex]); //convert prof to lowercase
			double ratingValue = Double.parseDouble(super.parser.data.get(i)[ratingIndex]);
			//must merge prof and school name
			if (!containsHashmap(tempMap, profName)) {
				//if not prof in the hashmap must add
				tempMap.put(profName, new MyHashTable<>(2));
				MyHashTable<String, Integer> ratingCountMap = (MyHashTable<String, Integer>) tempMap.get(profName);
				//the two variable I need to add and exploit
				ratingCountMap.put("total_rating", 0);
				ratingCountMap.put("count", 0);
			}

			MyHashTable<String, Integer> ratingCountMap = (MyHashTable<String, Integer>) tempMap.get(profName);
			int count = ratingCountMap.get("count");
			int total_rating = ratingCountMap.get("total_rating");
			//Separate what I have
			ratingCountMap.put("count", count + 1);
			ratingCountMap.put("total_rating", total_rating + (int) (ratingValue * 10000));
		}

		//now iterate through the hashmap
		ArrayList<String> keys = tempMap.getKeySet();
		for(String professor: keys){
			MyHashTable<String, Integer> valuesMap = (MyHashTable<String, Integer>)tempMap.get(professor);
			String prof = professor.split(",")[0];
			String school = professor.split(",")[1];

			double total_rating = valuesMap.get("total_rating") / (double) 10000;
			int count = valuesMap.get("count");
			String actual_rating = String.valueOf(Math.round((total_rating / count)*100.0) / 100.0);

			if(!containsHashmap(super.nestedHash, school)) {
				//if not prof in thehashmap must add
				super.nestedHash.put(school, new MyHashTable<>());
			}
			MyHashTable<String, Integer> profs = (MyHashTable<String, Integer>) super.nestedHash.get(school);
			profs.put(prof + "\n" + actual_rating,count);

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
