package org.haodev.puzzlecube;

import org.haodev.puzzlecube.Util.Axis;
import org.haodev.puzzlecube.Util.Direction;
import org.haodev.puzzlecube.Util.Rotation;

/**
 * A move that describes a particular rotation to apply to the cube
 *
 * @author Yuhao Ma (yuhao93@gmail.com)
 */
public class Move {
  // The "depth" of the rotation, which slice of the cube to rotate
  // a slice of 0 with direction being Front means that the front face
  // is rotated while a slice of sideLength - 1 with direction being Front
  // means the back face is rotated
  private int slice;
  
  // Amount to rotate by
  private Rotation rotation;
  
  // Which face to rotate
  private Direction direction;
  
  /**
   * @param move The String representation of the move. First is an optional
   *             number representing the slice. No number means a slice of 0.
   *             Next is a case-insensitive letter (F, L, U, B, R, D) corresponding
   *             to a direction. Last is the optional character "'". If the single
   *             quotation mark exists, then the rotation is counter-clockwise, if
   *             it isn't there, the rotation is clockwise.
   */
  public Move(String move){
    rotation = Rotation.CLOCKWISE;
    slice = 0;
    
    String last = move.substring(move.length() - 1).toLowerCase();
    int strLength = 1;
    
    if(last.equals("'")){
      rotation = Rotation.COUNTER_CLOCKWISE;
      last = move.substring(move.length() - 2).toLowerCase();
      strLength ++;
    }
    
    if(last.equals("f")){
      direction = Direction.FRONT;
    }else if(last.equals("l")){
      direction = Direction.LEFT;
    }else if(last.equals("u")){
      direction = Direction.UP;
    }else if(last.equals("b")){
      direction = Direction.BACK;
    }else if(last.equals("r")){
      direction = Direction.RIGHT;
    }else if(last.equals("d")){
      direction = Direction.DOWN;
    }
    
    if(move.length() > strLength){
      try{
        slice = Integer.parseInt(move.substring(0, move.length() - strLength));
      }catch(NumberFormatException e){ }
    }
  }
  
  /**
   * Build a move from rotation, direction, and slice directly
   *
   * @param rotation The rotation to rotate by
   * @param direction The face to rotate
   * @param slice the depth or slice number
   */
  public Move(Rotation rotation, Direction direction, int slice){
    this.rotation = rotation;
    this.direction = direction;
    this.slice = slice;
  }
  
  /**
   * @return the rotation described by this move
   */
  public Rotation getRotation(){
    return rotation;
  }
  
  /**
   * @return the direction described by this move
   */
  public Direction getDirection(){
    return direction;
  }
  
  /**
   * @return the slice affected by this move
   */
  public int getSlice(){
    return slice;
  }
  
  /**
   * The axis that this move will rotate around
   */
  public Axis getAxis(){
    switch(direction){
      case FRONT:
      case BACK:
        return Axis.Z_AXIS;
        
      case UP:
      case DOWN:
        return Axis.Y_AXIS;
        
      case LEFT:
      case RIGHT:
        return Axis.X_AXIS;
    }
    return null;
  }
  
  /**
   * Returns a String representation of the move
   * A counter clockwise rotation of the front face would be F'
   * A clockwise rotation of slice 2 (slice = 1) of the 
   * front face would be 2F'
   *
   * @return string representation of the mvoe
   */
  @Override
  public String toString(){
    String res = "";
    if(slice != 0){
      res += (slice + 1);
    }
    res += direction.toString().charAt(0);
    if(rotation == Rotation.COUNTER_CLOCKWISE){
      res += "'";
    }
    return res;
  }
  
  // Matrix to be applied to perform rotation
  Matrix getMatrix(){
    return new Matrix(rotation, getAxis());
  }
}