package org.haodev.puzzlecube;

import org.haodev.puzzlecube.Position;

public class Util {
  private Util() { }
  
  static final int UP = 0;
  static final int FRONT = 1;
  static final int RIGHT = 2;
  static final int DOWN = 3;
  static final int BACK = 4;
  static final int LEFT = 5;
  
  static Position map(int face, int x, int y, int z){
    Position pos = new Position(x, y, z);
  
    switch(face){
      case UP:
        pos.rotate(Rotation.COUNTER_CLOCKWISE, Axis.X_AXIS);
        break;
        
      case RIGHT:
        pos.rotate(Rotation.COUNTER_CLOCKWISE, Axis.Y_AXIS);
        break;
        
      case DOWN:
        pos.rotate(Rotation.CLOCKWISE, Axis.X_AXIS);
        break;
        
      case BACK:
        pos.rotate(Rotation.CLOCKWISE, Axis.X_AXIS);
        pos.rotate(Rotation.CLOCKWISE, Axis.X_AXIS);
        break;
        
      case LEFT:
        pos.rotate(Rotation.CLOCKWISE, Axis.Y_AXIS);
        break;
    }
    
    return pos;
  }
  
  static Direction rotateFaceX(Direction dir, Rotation rot){
    switch(dir){
      case UP:
          return rot == Rotation.CLOCKWISE
            ? Direction.FRONT
            : Direction.BACK;
        
      case DOWN:
        return rot == Rotation.CLOCKWISE
            ? Direction.BACK
            : Direction.FRONT;
        
      case LEFT:
      case RIGHT:
        return dir;
        
      case FRONT:
        return rot == Rotation.CLOCKWISE
            ? Direction.DOWN
            : Direction.UP;
        
      case BACK:
        return rot == Rotation.CLOCKWISE
            ? Direction.UP
            : Direction.DOWN;
    }
    return null;
  }
  
  static Direction rotateFaceY(Direction dir, Rotation rot){
    switch(dir){
      case UP:
      case DOWN:
        return dir;
        
      case LEFT:
        return rot == Rotation.CLOCKWISE
            ? Direction.BACK
            : Direction.FRONT;
          
      
      case RIGHT:
        return rot == Rotation.CLOCKWISE
            ? Direction.FRONT
            : Direction.BACK;
        
      case FRONT:
        return rot == Rotation.CLOCKWISE
            ? Direction.LEFT
            : Direction.RIGHT;
        
      case BACK:
        return rot == Rotation.CLOCKWISE
            ? Direction.RIGHT
            : Direction.LEFT;
    }
    return null;
  }
  
  static Direction rotateFaceZ(Direction dir, Rotation rot){
    switch(dir){
      case UP:
          return rot == Rotation.CLOCKWISE
            ? Direction.RIGHT
            : Direction.LEFT;
        
      case DOWN:
        return rot == Rotation.CLOCKWISE
            ? Direction.LEFT
            : Direction.RIGHT;
        
      case LEFT:
        return rot == Rotation.CLOCKWISE
            ? Direction.UP
            : Direction.DOWN;
      
      case RIGHT:
        return rot == Rotation.CLOCKWISE
            ? Direction.DOWN
            : Direction.UP;
        
      case FRONT:
      case BACK:
        return dir;
    }
    return null;
  }

  public static String colorToString(Color color){
    return color + "";
  }
  
  public static Rotation reverseRotation(Rotation rotation){
    if(rotation == Rotation.CLOCKWISE){
      return Rotation.COUNTER_CLOCKWISE;
    }else{
      return Rotation.CLOCKWISE;
    }
  }
  
  public static enum Rotation {
    CLOCKWISE,
    COUNTER_CLOCKWISE
  }
  
  public static enum Axis {
    X_AXIS,
    Y_AXIS,
    Z_AXIS
  }
  
  public static enum Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT,
    FRONT,
    BACK
  }
  
  public static enum Color {
    RED,
    BLUE,
    WHITE,
    ORANGE,
    GREEN,
    YELLOW
  }
}