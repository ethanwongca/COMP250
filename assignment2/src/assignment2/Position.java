package assignment2;
//March 20 Ver
public class Position {
    private int xCoord;
    private int yCoord;
    public Position(int xCoord, int yCoord){
        this.xCoord = xCoord;
        this.yCoord = yCoord;
    }
    //copy constructor!! like room
    public Position(Position p){
        this.xCoord = p.xCoord;
        this.yCoord = p.yCoord;
    }
    public void reset(int x , int y){
        this.xCoord = x;
        this.yCoord = y;
    }
    public void reset(Position p){
        this.xCoord = p.xCoord;
        this.yCoord = p.yCoord;
    }

    public static int getDistance(Position p1, Position p2){
        return Math.abs(p1.xCoord - p2.xCoord) + Math.abs(p1.yCoord - p2.yCoord);
    }
    public int getX(){
        return this.xCoord;
    }
    public int getY(){
        return this.yCoord;
    }
    //A moveWest method that decrements the x-coordinate by 1
    public void moveWest(){
        this.xCoord--;
    }
    // A moveEast method that increments the x-coordinate by 1.
    public void moveEast(){
        this.xCoord++;
    }
    //A moveNorth method that decrements the y-coordinate by 1.
    public void moveNorth(){
        this.yCoord--;
    }
    //A moveSouth method that increments the y-coordinate by 1.
    public void moveSouth(){
        this.yCoord++;
    }
    public boolean equals(Object obj) {
        if(obj == null){ //always check null first
            return false;
        }
        if(!(obj instanceof Position)){
            return false;
        }
        return (this.xCoord == ((Position) obj).xCoord) && (this.yCoord == ((Position) obj).yCoord);
    }
}
