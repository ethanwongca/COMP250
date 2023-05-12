package assignment2;
//March 20 Ver
public class Region {
    private int minX;
    private int minY;
    private int maxX;
    private int maxY;
    //minimum x position, minimum y
    //maximum x position, and maximum y position
    public Region(int minX, int minY, int maxX, int maxY){
        this.minX = minX;
        this.minY = minY;
        this.maxX = maxX;
        this.maxY = maxY;
    }
    public boolean contains(Position p){
        //inclusive on both bounds
        if((minX <= p.getX() && p.getX() <= maxX)&&(minY <= p.getY() && p.getY() <= maxY)){
            return true;
        }
        return false;
    }

}