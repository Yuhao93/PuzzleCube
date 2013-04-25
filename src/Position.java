package org.haodev.puzzlecube;

import org.haodev.puzzlecube.Util.Axis;
import org.haodev.puzzlecube.Util.Rotation;

/**
 * A 3D cartesian point that can be rotated around any of the three axis
 *
 * @author Yuhao Ma (yuhao93@gmail.com)
 */
public class Position {
  private int x, y, z;
  
  /**
   * @param x x coordinate
   * @param y y coordinate
   * @param z z coordinate
   */
  public Position(int x, int y, int z){
    this.x = x;
    this.y = y;
    this.z = z;
  }
   
  /**
   * @return x coodinate
   */
  public int getX(){
    return x;
  }
  
  /**
   * @return y coodinate
   */  
  public int getY(){
    return y;
  }
  
  /**
   * @return z coodinate
   */
  public int getZ(){
    return z;
  }

  /**
   * Rotates the point according to the rotation described by move
   *
   * @param move The rotation to perform on this point
   */
  public void rotate(Move move){
    rotate(move.getMatrix());
  }
  
  // Rotates the point by rotation around axis
  void rotate(Rotation rotation, Axis axis){
    rotate(new Matrix(rotation, axis));
  }
    
  // Applies the rotation matrix to this point
  void rotate(Matrix matrix){
    setPoints(matrix.rotate(this));
  }
  
  // Array of x, y, and z coordinates
  int[] points(){
    return new int[]{x, y, z};
  }
    
  // Set the points to x, y, and z
  void setPoints(int[] points){
    this.x = points[0];
    this.y = points[1];
    this.z = points[2];
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