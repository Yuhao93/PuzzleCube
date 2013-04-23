package org.haodev.puzzlecube;

import java.util.HashSet;
import java.util.Set;

public class Util {
  private Util() { }
  
  static final int UP = 0;
  static final int FRONT = 1;
  static final int LEFT = 2;
  static final int DOWN = 3;
  static final int BACK = 4;
  static final int RIGHT = 5;
  
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
  
  static Direction rotateFaceX(Move move, Direction dir){
    Rotation rot = move.getRotation();
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
  
  static Direction rotateFaceY(Move move, Direction dir){
    Rotation rot = move.getRotation();
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
  
  static Direction rotateFaceZ(Move move, Direction dir){
    Rotation rot = move.getRotation();
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
  
  public static boolean isSamePiece(Piece piece1, Piece piece2){
    Set<Face> f1 = new HashSet<Face>();
    Set<Face> f2 = new HashSet<Face>();
    
    for(Face face : piece1.getFaces()){
      f1.add(face);
    }
    
    for(Face face : piece2.getFaces()){
      f2.add(face);
    }

    if(f1.size() != f2.size()){
      return false;
    }
    
    for(Face f : f1){
      if(!f2.contains(f)){
        return false;
      }
    }
    
    return true;
  }
  
  static Direction determineDirectionFromInd(int ind, int sideLength){
    int linesComplete = ind / sideLength;
  
    if(linesComplete < sideLength){
      return Direction.UP;
    }else if(linesComplete >= sideLength && (linesComplete < sideLength * 5)){
      int faceCount = ((linesComplete - sideLength) % 4);
      
      switch(faceCount){
        case 0:
          return Direction.LEFT;
        
        case 1:
          return Direction.FRONT;
          
        case 2:
          return Direction.RIGHT;
        
        case 3:
          return Direction.BACK;
        
        default:
          return null;
      }
      
    }else if(linesComplete >= sideLength * 5){
      return Direction.DOWN;
    }
    return null;
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
}