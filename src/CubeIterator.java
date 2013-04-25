package org.haodev.puzzlecube;

import java.util.Iterator;

import org.haodev.puzzlecube.Util.Direction;

/**
 * Iterates through a cube in a 2D mapped order
 * <br>
 * Given a cube map where:
 * <br>
 * <ul>
 * <li>T is the top face</li>
 * <li>L is the left face</li>
 * <li>F is the front face</li>
 * <li>D is the bottom face</li>
 * <li>R is the right face</li>
 * <li>B is the back face</li>
 * </ul>
 * <br>
 * <pre>
 *       T T T<br>
 *       T T T<br>
 *       T T T <br>
 * L L L F F F R R R B B B<br>
 * L L L F F F R R R B B B<br>
 * L L L F F F R R R B B B<br>
 *       D D D<br>
 *       D D D<br>
 *       D D D<br>
 * </pre>
 * <br>
 * The order returned is:<br>
 * <br>
 * <pre>
 *           1  2  3<br>
 *           4  5  6<br>
 *           7  8  9<br>
 * 10 11 12 13 14 15 16 17 18 19 20 21<br>
 * 22 23 24 25 26 27 28 29 30 31 32 33<br>
 * 34 35 36 37 38 39 40 41 42 43 44 45<br>
 *          46 47 48<br>
 *          49 50 51<br>
 *          52 53 54<br>
 * </pre>
 * @author Yuhao Ma (yuhao93@gmail.com)
 */
public class CubeIterator implements Iterator<Piece> {
  // cube to iterate through
  private Cube cube;
  
  // how many pieces we've iterated through
  private int ind;
  
  // how many pieces there are to iterate through
  private int count;
  
  // number of pieces on a face
  private int faceSize;
  
  // side Length
  private int sideLength;
  
  // The row in the 2D map that we're currently on
  private int i;
  
  // The column in the 2D map that we're currently on
  private int j;
  
  // How many sets of sideLengths we've finished
  private int linesComplete = 0;
  
  /**
   * @param cube Cube to iterate through
   */
  public CubeIterator(Cube cube){
    this.cube = cube;
    this.sideLength = cube.getSideLength();
    this.faceSize = sideLength * sideLength;
    this.count = faceSize * 6;
  }

  @Override
  public boolean hasNext(){
    return ind < count;
  }
  
  @Override
  public Piece next(){
    Piece piece = null;
    Direction direction = Util.determineDirectionFromInd(ind, sideLength);
    int x;
    int y;
    int z;

    switch(direction){
    
      // Up Face
      case UP:
        x = j - (sideLength / 2);
        y = -sideLength / 2;
        z = i - (sideLength / 2);
        
        if(x >= 0 && sideLength % 2 == 0){
          x ++;
        }
        
        if(z >= 0 && sideLength % 2 == 0){
          z ++;
        }
        piece = cube.getPiece(x, y, -z);
        break;
      
      // Left Face
      case LEFT:
        x = -sideLength / 2;
        y = i - (sideLength / 2);
        z = j - (sideLength / 2);
        
        if(y >= 0 && sideLength % 2 == 0){
          y ++;
        }
        
        if(z >= 0 && sideLength % 2 == 0){
          z ++;
        }
 
        piece = cube.getPiece(new Position(x, y, -z));
        break;
      
      // Front Face
      case FRONT:
        x = j - (sideLength / 2);
        y = i - (sideLength / 2);
        z = -sideLength / 2;
        
        if(x >= 0 && sideLength % 2 == 0) {
          x ++;
        }
        
        if(y >= 0 && sideLength % 2 == 0) {
          y ++;
        }
        
        piece = cube.getPiece(new Position(x, y, z));
        break;
      
      // Right Face
      case RIGHT:
        x = sideLength / 2;
        y = i - (sideLength / 2);
        z = j - (sideLength / 2);
        
        if(y >= 0 && sideLength % 2 == 0){
          y ++;
        }
        
        if(z >= 0 && sideLength % 2 == 0){
          z ++;
        }
        
        piece = cube.getPiece(new Position(x, y, z));
        break;
      
      // Back Face
      case BACK:
        x = j - (sideLength / 2);
        y = i - (sideLength / 2);
        z = sideLength / 2;
        
        if(x >= 0 && sideLength % 2 == 0) {
          x ++;
        }
        
        if(y >= 0 && sideLength % 2 == 0) {
          y ++;
        }
        
        piece = cube.getPiece(new Position(-x, y, z));
        break;
      
      // Down Face
      case DOWN:
        x = j - (sideLength / 2);
        y = sideLength / 2;
        z = i - (sideLength / 2);
        
        if(x >= 0 && sideLength % 2 == 0){
          x ++;
        }
        
        if(z >= 0 && sideLength % 2 == 0){
          z ++;
        }
        
        piece = cube.getPiece(new Position(x, y, z));
        break;
      
    }
    
    j ++;
    ind ++;
    
    // If we've completed a sideLength number of sides
    if(j == sideLength){
      // restart
      j = 0;
      
      // increment number of lines completed
      linesComplete ++;
      
      // If we are on the up face
      if(linesComplete < sideLength 
      
      // or we are on the middle four faces and we've
          || (linesComplete >= sideLength 
              && (linesComplete - sideLength) % 4 == 0 
              && linesComplete < sideLength * 5) 
              
      // or we are on the bottom face
          || (linesComplete >= sideLength * 5)){
          
        // increment vertically
        i ++;
        if(i == sideLength){
          i = 0;
        }
      }
    }

    return piece;
  }
  
  @Override
  public void remove() throws UnsupportedOperationException{
    throw new UnsupportedOperationException("remove not supported for CubeIterator");
  }
}