package org.haodev.puzzlecube;

import org.haodev.puzzlecube.Matrix;
import org.haodev.puzzlecube.Util.Axis;
import org.haodev.puzzlecube.Util.Rotation;


public class Position {
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
    rotate(new Matrix(rotation, axis));
  }
    
  void rotate(Matrix matrix){
    setPoints(matrix.rotate(this));
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