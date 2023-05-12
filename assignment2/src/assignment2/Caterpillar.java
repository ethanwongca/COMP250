package assignment2;
public class Caterpillar extends MyDoublyLinkedList<Position>{
    //March 20 Ver
    public Caterpillar(){
        this.add(new Position(7,7 ));
    }
    //according to ed, the first element is the head
    public Position getHead(){
        return peekFirst();
    }
    //It should say "orthogonally adjacent" for both methods! from eD
    public void eat(Position p){
        if(p.getX() < this.getHead().getX() - 1 || p.getX() > this.getHead().getX() + 1){
            throw new IllegalArgumentException("Not adjacent");
        }
        if(p.getY() < this.getHead().getY() - 1 || p.getY() > this.getHead().getY() + 1){
            throw new IllegalArgumentException("Not adjacent");
        }
        if((p.getY() > this.getHead().getY() || p.getY() < this.getHead().getY()) && (!(p.getX() == this.getHead().getX()))){
            throw new IllegalArgumentException("Moving diagonal");
        }
        if((p.getX() > this.getHead().getX() || p.getX() < this.getHead().getX()) && (!(p.getY() == this.getHead().getY()))){
            throw new IllegalArgumentException("Moving diagonal");
        }
        if(p.equals(this.getHead())){
            throw new IllegalArgumentException("NOT MOVING");
        }

        this.addFirst(p);
    }
    public void move(Position p){
        if(p.getX() < this.getHead().getX() - 1 || p.getX() > this.getHead().getX() + 1){
            throw new IllegalArgumentException("Not adjacent");
        }
        if(p.getY() < this.getHead().getY() - 1 || p.getY() > this.getHead().getY() + 1){
            throw new IllegalArgumentException("Not adjacent");
        }
        if((p.getY() > this.getHead().getY() || p.getY() < this.getHead().getY()) && (!(p.getX() == this.getHead().getX()))){
            throw new IllegalArgumentException("Moving diagonal");
        }
        if((p.getX() > this.getHead().getX() || p.getX() < this.getHead().getX()) && (!(p.getY() == this.getHead().getY()))){
            throw new IllegalArgumentException("Moving diagonal");
        }
        if(p.equals(this.getHead())){
            throw new IllegalArgumentException("NOT MOVING");
        }
        this.addFirst(p);
        this.removeLast();
    }
    public boolean selfCollision(Position p){
        for(Position body: this){
            if(body.equals(p)){
                return true;
            }
        }
        return false;
    }

}
