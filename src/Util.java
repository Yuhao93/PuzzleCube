package org.haodev.puzzlecube;

public class Util {
  private Util() { }
  
  public static final int UP = 0;
  public static final int FRONT = 1;
  public static final int RIGHT = 2;
  public static final int DOWN = 3;
  public static final int BACK = 4;
  public static final int LEFT = 5;
  
  public static Position map(int face, int x, int y, int z){
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
  
  public static Direction rotateFaceX(Direction dir, Rotation rot){
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
  
  public static Direction rotateFaceY(Direction dir, Rotation rot){
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
  
  public static Direction rotateFaceZ(Direction dir, Rotation rot){
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
  
  public static class Position {
    private int x, y, z;
    
    public Position(int x, int y, int z){
      this.x = x;
      this.y = y;
      this.z = z;
    }
    
    public Position(Position copy){
      this(copy.getX(), copy.getY(), copy.getZ());
    }
    
    public int getX(){
      return x;
    }
    
    public int getY(){
      return y;
    }
    
    public int getZ(){
      return z;
    }
    
    public int[] points(){
      return new int[]{x, y, z};
    }
    
    public void setPoints(int[] points){
      this.x = points[0];
      this.y = points[1];
      this.z = points[2];
    }
    
    public void rotate(Rotation rotation, Axis axis){
      System.out.println("before: " + this);
      Matrix r = new Matrix(rotation, axis);
      setPoints(r.rotate(this));
      System.out.println("after : " + this);
    }
    
    @Override
    public int hashCode(){
      return x * y * z;
    }
    
    @Override
    public boolean equals(Object obj){
      if(obj instanceof Position){
        Position pos = (Position) obj;
        return (pos.x == x && pos.y == y && pos.z == z);
      }
      
      return false;
    }
    
    @Override
    public String toString(){
      return "{" + x + "," + y + "," + z + "}";
    }
  }
  
  public static class Matrix {
    int[] m = new int[9];
    
    public Matrix(Rotation rot, Axis axis) {
      int cos = 0;
      int sin = rot == Rotation.CLOCKWISE ? 1 : -1;
    
      switch(axis){
        case X_AXIS:
          m = new int[]{1, 0, 0, 0, cos, -sin, 0, sin, cos};
          break;
          
        case Y_AXIS:
          m = new int[]{cos, 0, sin, 0, 1, 0, -sin, 0, cos};
          break;
          
        case Z_AXIS:
          m = new int[]{cos, -sin, 0, sin, cos, 0, 0, 0, 1};
          break;
      }
    }
    
    public int[] rotate(Position point){
      int[] res = new int[3];
      int[] points = point.points();
      
      for(int i = 0; i < res.length; i ++){
        for(int j = 0; j < 3; j ++){
          res[i] += points[j] * m[j + (i * 3)];
        }
      }
      
      return res;
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
    GREEN,
    WHITE,
    ORANGE,
    BLUE,
    YELLOW
  }
}