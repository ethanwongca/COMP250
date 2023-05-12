package assignment3;
//Apr 10th Version Updated
import java.awt.Color;

public class PerimeterGoal extends Goal{

	public PerimeterGoal(Color c) {
		super(c);
	}

	@Override
	public int score(Block board) {
		Color[][] colorArray =  board.flatten();
		int score = 0; //one for-loop is optimal runs through top and bottom of one row
		for(int i = 0; i < colorArray.length; i++){ //thought of four but now one  :)
			if(colorArray[0][i].equals(targetGoal)){ //top
				score++;
			}
			if(colorArray[i][0].equals(targetGoal)){ //side left
				score++;
			}
			if(colorArray[colorArray.length - 1][i].equals(targetGoal)){ //side right
				score++;
			}
			if(colorArray[i][colorArray.length - 1].equals(targetGoal)){ //side bottom
				score++;
			}
		}
		return score;
	}

	@Override
	public String description() {
		return "Place the highest number of " + GameColors.colorToString(targetGoal)
				+ " unit cells along the outer perimeter of the board. Corner cell count twice toward the final score!";
	}

}