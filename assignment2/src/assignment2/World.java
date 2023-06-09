package assignment2;
//March 20 Ver
public class World {
    //all private classes done
    private Caterpillar caterpillar; //caterpillar
    private Position position; //food position
    private Region region; //map region
    private ActionQueue actionQueue; //caterpillar action
    private TargetQueue targetQueue; //food position
    private GameState gameState; //current game state

    public World(TargetQueue targetQueue, ActionQueue actionQueue){
        //region 0,15 0,15 inclusive
        this.targetQueue = targetQueue;
        this.actionQueue = actionQueue;
        this.region = new Region(0, 0, 15, 15);
        this.caterpillar = new Caterpillar();
        this.position = this.targetQueue.dequeue(); 
        this.gameState = gameState.MOVE; 
    }
    public void step(){ 
        Direction temp;
        if(this.actionQueue.isEmpty()){
            this.gameState = gameState.NO_MORE_ACTION; 
        }
        if(this.gameState != gameState.EAT && this.gameState != gameState.MOVE){
            return;
        }
        temp = this.actionQueue.dequeue();
        Position nextPosition = null; 
        if(temp == Direction.NORTH){
            nextPosition = new Position( this.caterpillar.getHead().getX(), this.caterpillar.getHead().getY() - 1);
        } else if (temp == Direction.SOUTH){
            nextPosition = new Position( this.caterpillar.getHead().getX(), this.caterpillar.getHead().getY() + 1);
        } else if (temp == Direction.EAST){
            nextPosition = new Position( this.caterpillar.getHead().getX() + 1, this.caterpillar.getHead().getY());
        } else if (temp == Direction.WEST){
            nextPosition = new Position( this.caterpillar.getHead().getX() - 1, this.caterpillar.getHead().getY());
        }
        if (!(this.region.contains(nextPosition))){
            this.gameState = gameState.WALL_COLLISION;
        }
        else if (this.caterpillar.selfCollision(nextPosition)){
            this.gameState = gameState.SELF_COLLISION;
        }
        else if (nextPosition.equals(this.position)){
            this.caterpillar.eat(nextPosition);
            if(targetQueue.isEmpty()){
                this.gameState = gameState.DONE;
            } else {
                this.position = this.targetQueue.dequeue();
                this.gameState = gameState.EAT; 
            }
        } else {
            caterpillar.move(nextPosition);
            this.gameState = gameState.MOVE;
        }

    }

    public GameState getState(){
        return this.gameState;
    }
    public Caterpillar getCaterpillar(){
        return this.caterpillar;
    }
    public Position getFood(){
        return this.position;
    }
    public boolean isRunning(){
        return ((this.gameState == gameState.MOVE) || (this.gameState == gameState.EAT));
    }

}
