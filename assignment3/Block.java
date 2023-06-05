package assignment3;
//Apr 10th Version Updated
import java.util.ArrayList;
import java.util.Random;
import java.awt.Color;

public class Block {
 private int xCoord;
 private int yCoord;
 private int size; // height/width of the square
 private int level; // the root (outer most block) is at level 0
 private int maxDepth;
 private Color color;

 private Block[] children; // {UR, UL, LL, LR}

 public static Random gen = new Random();


 public Block() {
 }

 public Block(int x, int y, int size, int lvl, int maxD, Color c, Block[] subBlocks) {
  this.xCoord = x;
  this.yCoord = y;
  this.size = size;
  this.level = lvl;
  this.maxDepth = maxD;
  this.color = c;
  this.children = subBlocks;
 }


 /*
  * Creates a random block given its level and a max depth.
  *
  * xCoord, yCoord, size, and highlighted should not be initialized
  * (i.e. they will all be initialized by default)
  */
 public Block(int lvl, int maxDepth) {
  if(lvl > maxDepth){
   throw new IllegalArgumentException("Wrong");
  }
  this.level = lvl;
  this.maxDepth = maxDepth;
  this.children = new Block[0];
  this.color = null; 

  if (this.level < this.maxDepth) { //
   double randomNum = gen.nextDouble();
   if (randomNum < Math.exp(-0.25 * this.level)) {
    int newLevel = this.level + 1;
    this.children = new Block[4];
    this.children[0] = new Block(newLevel, maxDepth);
    this.children[1] = new Block(newLevel, maxDepth);
    this.children[2] = new Block(newLevel, maxDepth);
    this.children[3] = new Block(newLevel, maxDepth);
   } else {
    int randomColor = gen.nextInt(4);
    this.color = GameColors.BLOCK_COLORS[randomColor];
   }
  } else { 
   int randomColor = gen.nextInt(4);
   this.color = GameColors.BLOCK_COLORS[randomColor];
  }
 } 


 /*
  * Updates size and position for the block and all of its sub-blocks, while
  * ensuring consistency between the attributes and the relationship of the
  * blocks.
  *
  *  The size is the height and width of the block. (xCoord, yCoord) are the
  *  coordinates of the top left corner of the block.
  */
 public void updateSizeAndPosition(int size, int xCoord, int yCoord) { 
  if (size <= 0 || (size % 2 != 0 && this.level < this.maxDepth)) {
   throw new IllegalArgumentException("Size is not big enough");
  } 
  this.size = size;
  this.xCoord = xCoord;
  this.yCoord = yCoord;

  if (this.level < this.maxDepth) { //base case
   if (this.children.length > 0) {
    int halfSize = this.size / 2;
    int addedX = this.xCoord + halfSize;
    int addedY = this.yCoord + halfSize;
    if(size % 2 != 0){
     throw new IllegalArgumentException("Not evenly divisible");
    }

    this.children[0].updateSizeAndPosition(halfSize, addedX, this.yCoord);
    this.children[1].updateSizeAndPosition(halfSize, this.xCoord, this.yCoord);
    this.children[2].updateSizeAndPosition(halfSize, this.xCoord, addedY);
    this.children[3].updateSizeAndPosition(halfSize, addedX, addedY);
   }
  }


 }


 /*
  * Returns a List of blocks to be drawn to get a graphical representation of this block.
  *
  * This includes, for each undivided Block:
  * - one BlockToDraw in the color of the block
  * - another one in the FRAME_COLOR and stroke thickness 3
  *
  * Note that a stroke thickness equal to 0 indicates that the block should be filled with its color.
  *
  * The order in which the blocks to draw appear in the list does NOT matter.
  */
 public ArrayList<BlockToDraw> getBlocksToDraw() { 
  ArrayList<BlockToDraw> drawingBlocks = new ArrayList<BlockToDraw>();

  if (this.children.length == 0) { //base case for the recursive call
   drawingBlocks.add(new BlockToDraw(this.color, this.xCoord, this.yCoord, this.size, 0)); //Colour
   drawingBlocks.add(new BlockToDraw(GameColors.FRAME_COLOR, this.xCoord, this.yCoord, this.size, 3)); //frame
  } else { 
   for (BlockToDraw drawingBlock : this.children[0].getBlocksToDraw()) {
    drawingBlocks.add(drawingBlock); 
   }
   for (BlockToDraw drawingBlock : this.children[1].getBlocksToDraw()) {
    drawingBlocks.add(drawingBlock);
   }
   for (BlockToDraw drawingBlock : this.children[2].getBlocksToDraw()) {
    drawingBlocks.add(drawingBlock);
   }
   for (BlockToDraw drawingBlock : this.children[3].getBlocksToDraw()) {
    drawingBlocks.add(drawingBlock);
   }
  }

  return drawingBlocks;
 }


 /*
  * This method is provided and you should NOT modify it.
  */
 public BlockToDraw getHighlightedFrame() {
  return new BlockToDraw(GameColors.HIGHLIGHT_COLOR, this.xCoord, this.yCoord, this.size, 5);
 }



 /*
  * Return the Block within this Block that includes the given location
  * and is at the given level. If the level specified is lower than
  * the lowest block at the specified location, then return the block
  * at the location with the closest level value.
  *
  * The location is specified by its (x, y) coordinates. The lvl indicates
  * the level of the desired Block. Note that if a Block includes the location
  * (x, y), and that Block is subdivided, then one of its sub-Blocks will
  * contain the location (x, y) too. This is why we need lvl to identify
  * which Block should be returned.
  *
  * Input validation:
  * - this.level <= lvl <= maxDepth (if not throw exception)
  * - if (x,y) is not within this Block, return null.
  */
 public Block getSelectedBlock(int x, int y, int lvl) {
  Block selectedBlock = this;

  if(lvl < this.level){
   throw new IllegalArgumentException("Level is wrong");
  }
  if(lvl > this.maxDepth){
   throw new IllegalArgumentException("Max Depth");
  }
  if(!(contains(x, y))){
   return null;  
  }

  if(this.level == lvl && contains(x, y)){ 
   return this;
  } 

  for(Block children: this.children){
   selectedBlock = children.getSelectedBlock(x, y, lvl);
   if(children.contains(x, y)) {
    return selectedBlock;
   }
  }

  return selectedBlock; 
 }
 private boolean contains(int x, int y){
  if((x >= this.xCoord && x < this.xCoord + this.size && y >= this.yCoord && y < this.yCoord + this.size)){
   return true;
  } else {
   return false;
  }
 }




 /*
  * Swaps the child Blocks of this Block.
  * If input is 1, swap vertically. If 0, swap horizontally.
  * If this Block has no children, do nothing. The swap
  * should be propagate, effectively implementing a reflection
  * over the x-axis or over the y-axis.
  *
  */
 public void reflect(int direction) {
  //takes - over x and input 1 if it is y
  // T && F => F
  //like mirroring a tree so swap the children nodes properly
  if(direction != 0 && direction != 1){
   throw new IllegalArgumentException("Improper direction");
  }
  if(this.children.length == 0){
   return;
  }
  if(direction == 0){ //x-axis
   this.reflectX(this.size, this.xCoord, this.yCoord);
   this.updateSizeAndPosition(this.size, this.xCoord, this.yCoord);
  }
  else if (direction == 1){ //y-axis
   this.reflectY(this.size, this.xCoord, this.yCoord);
   this.updateSizeAndPosition(this.size, this.xCoord, this.yCoord);
   //need to swap on x in terms of position
  } else {
   throw new IllegalArgumentException("Wrong");
  }

 }
 //naive way of doing it but it works, still iterates the O(2^j)
 private void reflectX(int size, int xCoord, int yCoord) { //need to do for the other parts fo the method

  this.size = size;
  this.xCoord = xCoord;
  this.yCoord = yCoord;

  if (this.level < this.maxDepth) { //base case
   if (this.children.length > 0) {
    int halfSize = this.size / 2;
    int addedX = this.xCoord + halfSize;
    int addedY = this.yCoord + halfSize;
    //have to swap arrays, to make sure the code matches up with the size

    Block temp = this.children[0];
    this.children[0] = this.children[3];
    this.children[3] = temp;

    temp = this.children[1];
    this.children[1] = this.children[2];
    this.children[2] = temp;

    // reflect the children nodes recursively
    this.children[0].reflectX(halfSize, addedX, this.yCoord);
    this.children[1].reflectX(halfSize, this.xCoord, this.yCoord);
    this.children[2].reflectX(halfSize, this.xCoord, addedY);
    this.children[3].reflectX(halfSize, addedX, addedY);
   }
  }
 }
 private void reflectY(int size, int xCoord, int yCoord) {

  this.size = size;
  this.xCoord = xCoord;
  this.yCoord = yCoord;

  if (this.level < this.maxDepth) { //base case
   if (this.children.length > 0) {
    int halfSize = this.size / 2;
    int addedX = this.xCoord + halfSize;
    int addedY = this.yCoord + halfSize;


    Block temp = this.children[0];
    this.children[0] = this.children[1];
    this.children[1] = temp;


    temp = this.children[3];
    this.children[3] = this.children[2];
    this.children[2] = temp;

    // reflect the children nodes recursively
    this.children[0].reflectY(halfSize, addedX, this.yCoord);
    this.children[1].reflectY(halfSize, this.xCoord, this.yCoord);
    this.children[2].reflectY(halfSize, this.xCoord, addedY);
    this.children[3].reflectY(halfSize, addedX, addedY);
   }
  }
 }

 /*
  * Rotate this Block and all its descendants.
  * If the input is 1, rotate clockwise. If 0, rotate
  * counterclockwise. If this Block has no children, do nothing.
  */
 public void rotate(int direction) { //same application just do it naivve
  if(direction != 0 && direction != 1){
   throw new IllegalArgumentException("Direction is wrong");
  }
  if(this.children.length == 0){
   return;
  }
  if(direction == 0){
   this.rotateCCW(this.size, this.xCoord, this.yCoord);
   this.updateSizeAndPosition(this.size, this.xCoord, this.yCoord);
  }
  if(direction == 1){
   this.rotateCW(this.size, this.xCoord, this.yCoord);
   this.updateSizeAndPosition(this.size, this.xCoord, this.yCoord);
  }
 }
 private void rotateCCW(int size, int xCoord, int yCoord) { 

  this.size = size;
  this.xCoord = xCoord;
  this.yCoord = yCoord;

  if (this.level < this.maxDepth) { //base case
   if (this.children.length > 0) {

    int halfSize = this.size / 2;
    int addedX = this.xCoord + halfSize;
    int addedY = this.yCoord + halfSize;

    Block temp = this.children[0];
    this.children[0] = this.children[3];
    this.children[3] = this.children[2];
    this.children[2] = this.children[1];
    this.children[1] = temp;
    //have to swap arrays, to make sure the code matches up with the size
    this.children[0].rotateCCW(halfSize, addedX, this.yCoord);
    this.children[1].rotateCCW(halfSize, this.xCoord, this.yCoord);
    this.children[2].rotateCCW(halfSize, this.xCoord, addedY);
    this.children[3].rotateCCW(halfSize, addedX, addedY);
   }
  }
 }
 private void rotateCW(int size, int xCoord, int yCoord) { 

  this.size = size;
  this.xCoord = xCoord;
  this.yCoord = yCoord;

  if (this.level < this.maxDepth) { //base case
   if (this.children.length > 0) {
    int halfSize = this.size / 2;
    int addedX = this.xCoord + halfSize;
    int addedY = this.yCoord + halfSize;

    Block temp = this.children[3];
    this.children[3] = this.children[0];
    this.children[0] = this.children[1];
    this.children[1] = this.children[2];
    this.children[2] = temp;
    //have to swap arrays, to make sure the code matches up with the size
    this.children[0].rotateCW(halfSize, addedX, this.yCoord);
    this.children[1].rotateCW(halfSize, this.xCoord, this.yCoord);
    this.children[2].rotateCW(halfSize, this.xCoord, addedY);
    this.children[3].rotateCW(halfSize, addedX, addedY);
   }
  }
 }


 /*
  * Smash this Block.
  *
  * If this Block can be smashed,
  * randomly generate four new children Blocks for it.
  * (If it already had children Blocks, discard them.)
  * Ensure that the invariants of the Blocks remain satisfied.
  *
  * A Block can be smashed iff it is not the top-level Block
  * and it is not already at the level of the maximum depth.
  *
  * Return True if this Block was smashed and False otherwise.
  *
  */
 public boolean smash() {
  if(this.level == this.maxDepth || this.level == 0) {
   return false;
  } else {
   this.children = new Block[4];
   this.children[0] = new Block(this.level + 1, this.maxDepth);
   this.children[1] = new Block(this.level + 1, this.maxDepth);
   this.children[2] = new Block(this.level + 1, this.maxDepth);
   this.children[3] = new Block(this.level + 1, this.maxDepth);
   this.updateSizeAndPosition(this.size, this.xCoord, this.yCoord);
   return true;
  }
 }


 /*
  * Return a two-dimensional array representing this Block as rows and columns of unit cells.
  *
  * Return and array arr where, arr[i] represents the unit cells in row i,
  * arr[i][j] is the color of unit cell in row i and column j.
  *
  * arr[0][0] is the color of the unit cell in the upper left corner of this Block.
  */
 public Color[][] flatten() {
  int updatedSize = this.size / (int) Math.pow(2, this.maxDepth);
  //I think this is it, 4^maxDepth =  2^MaxDepth * 2^MaxDepth
  Color[][] flattenArray = new Color[(int) Math.pow(2, this.maxDepth)][(int) Math.pow(2, this.maxDepth)];

  flattenArray = this.flattenRecursion(flattenArray, updatedSize);
  //Flatten recursion

  return flattenArray;
 }

 private Color[][] flattenRecursion(Color[][] array, int updatedSize){

  int sizeUpdated =  this.size / updatedSize;
  int xUpdate = this.xCoord / updatedSize;
  int yUpdate = this.yCoord / updatedSize;
  //recursive call related to the children length
  if(this.children.length == 0){
   //size updated is what we go to which is the 2^ value
   for (int i = 0; i < sizeUpdated; i++) {
    for (int n = 0; n < sizeUpdated; n++) {
     array[i + yUpdate][n + xUpdate] = this.color;
    }
   }

  } else{
   for(Block children: this.children){
    children.flattenRecursion(array, updatedSize);
   }
  }
  return array;
 }



 // These two get methods have been provided. Do NOT modify them.
 public int getMaxDepth() {
  return this.maxDepth;
 }

 public int getLevel() {
  return this.level;
 }


 /*
  * The next 5 methods are needed to get a text representation of a block.
  * You can use them for debugging. You can modify these methods if you wish.
  */
 public String toString() {
  return String.format("pos=(%d,%d), size=%d, level=%d"
          , this.xCoord, this.yCoord, this.size, this.level);
 }

 public void printBlock() {
  this.printBlockIndented(0);
 }

 private void printBlockIndented(int indentation) {
  String indent = "";
  for (int i=0; i<indentation; i++) {
   indent += "\t";
  }

  if (this.children.length == 0) {
   // it's a leaf. Print the color!
   String colorInfo = GameColors.colorToString(this.color) + ", ";
   System.out.println(indent + colorInfo + this);
  } else {
   System.out.println(indent + this);
   for (Block b : this.children)
    b.printBlockIndented(indentation + 1);
  }
 }

 private static void coloredPrint(String message, Color color) {
  System.out.print(GameColors.colorToANSIColor(color));
  System.out.print(message);
  System.out.print(GameColors.colorToANSIColor(Color.WHITE));
 }

 public void printColoredBlock(){
  Color[][] colorArray = this.flatten();
  for (Color[] colors : colorArray) {
   for (Color value : colors) {
    String colorName = GameColors.colorToString(value).toUpperCase();
    if(colorName.length() == 0){
     colorName = "\u2588";
    }else{
     colorName = colorName.substring(0, 1);
    }
    coloredPrint(colorName, value);
   }
   System.out.println();
  }
 }

}
