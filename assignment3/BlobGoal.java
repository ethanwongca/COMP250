package assignment3;
//Apr 10th Version Updated
import java.awt.Color;

public class BlobGoal extends Goal{

	public BlobGoal(Color c) {
		super(c);
	}

	@Override
	public int score(Block board) {
		int score = 0;
		Color[][] colorArray = board.flatten(); //boolean should be the same size emulated the board
		boolean[][] booleanArray = new boolean[colorArray.length][colorArray.length];
		for(int i = 0; i < colorArray.length; i++){
			for(int n = 0; n < colorArray.length; n++){
				int blobScore = undiscoveredBlobSize(i, n, colorArray, booleanArray);
				if(score < blobScore){ //max val typical cs
					score = blobScore;
				}
			}
		}
		return score;
	}

	@Override
	public String description() {
		return "Create the largest connected blob of " + GameColors.colorToString(targetGoal)
				+ " blocks, anywhere within the block";
	}


	public int undiscoveredBlobSize(int i, int j, Color[][] unitCells, boolean[][] visited) {
		int value = 0; //can't assume it is square, according to the tester data.

		if(i < 0 || i >= unitCells.length || j < 0 || j >= unitCells[0].length){ //remove index out of bounds error right away
			return 0;
		}

		if(visited[i][j]){ //so we visit
			return 0;
		}

		if(!visited[i][j]){
			visited[i][j] = true;
			if(unitCells[i][j].equals(targetGoal)){
				value++;
			} else {
				return 0; //get's rid of the value being one, not sure why though just did it because was getting 6 and it seemed viable
			}

		} else {
			return 0;
		}

		return value + undiscoveredBlobSize(i + 1, j, unitCells, visited) + undiscoveredBlobSize(i , j + 1, unitCells, visited) + undiscoveredBlobSize(i - 1, j, unitCells, visited) + undiscoveredBlobSize(i, j-1, unitCells, visited);

	}


}