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
		Color[][] colorArray = board.flatten(); 
		boolean[][] booleanArray = new boolean[colorArray.length][colorArray.length];
		for(int i = 0; i < colorArray.length; i++){
			for(int n = 0; n < colorArray.length; n++){
				int blobScore = undiscoveredBlobSize(i, n, colorArray, booleanArray);
				if(score < blobScore){ 
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
		int value = 0; 

		if(i < 0 || i >= unitCells.length || j < 0 || j >= unitCells[0].length){
			return 0;
		}

		if(visited[i][j]){ 
			return 0;
		}

		if(!visited[i][j]){
			visited[i][j] = true;
			if(unitCells[i][j].equals(targetGoal)){
				value++;
			} else {
				return 0; 
			}

		} else {
			return 0;
		}

		return value + undiscoveredBlobSize(i + 1, j, unitCells, visited) + undiscoveredBlobSize(i , j + 1, unitCells, visited) + undiscoveredBlobSize(i - 1, j, unitCells, visited) + undiscoveredBlobSize(i, j-1, unitCells, visited);

	}


}
