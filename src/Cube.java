package org.haodev.puzzlecube;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.haodev.puzzlecube.Util.Axis;
import org.haodev.puzzlecube.Util.Direction;
import org.haodev.puzzlecube.Util.Rotation;

/**
 * Represents a Puzzle Cube
 *
 *
 * Puzzle Cube sits with its center on the origin with 
 *   Front, Up, and Left being on the negative axis
 *   and Back, Down, Right being on the positive axis
 *
 * @author Yuhao Ma (yuhao93@gmail.com)
 */
public class Cube implements Iterable<Piece>{
  private int sideLength = 3;
  
  private List<Corner> corners = new ArrayList<Corner>();
  private List<Edge> edges = new ArrayList<Edge>();
  private List<Center> centers = new ArrayList<Center>();
  
  private List<Piece> pieces = new ArrayList<Piece>();
  private Map<Position, Piece> piecesMap = new HashMap<Position, Piece>();
  
  private ColorMapper colorMapper = new ColorMapper();
  
  /**
   * @param sideLength How many pieces make up one edge of the cube
   */
  public Cube(int sideLength){
    this(sideLength, true);
  }
  
  Cube(int sideLength, boolean withFaces){
    this.sideLength = sideLength;
    init(withFaces);
  }
  
  /**
   * Rotate the cube slice in the direction given
   *
   * @param rotation the rotation of the cube
   * @param direction the face to be targeted
   * @param depth the slice number (0 <= depth < sideLength)
   */
  public void rotate(Rotation rotation, Direction direction, int depth){
    // Store all the new pieces in a new temp Map
    Map<Position, Piece> newMap = new HashMap<Position, Piece>();
    
    // Move to make
    Move move = new Move(rotation, direction, depth);
    
    // Slice index, changed to cube coordinates
    int s = depth - (sideLength / 2);
    if(sideLength % 2 == 0 && s >= 0){
      s ++;
    }

    // Which direction we are coming from
    // BACK, RIGHT, and DOWN are just inverses of FRONT, LEFT, and UP
    for(Map.Entry<Position, Piece> entry : piecesMap.entrySet()){
      Piece p = entry.getValue();
      boolean test = false;
      
      switch(direction){
        case FRONT:
          test = (entry.getKey().getZ() == s);
          break;
        
        case LEFT:
          test = (entry.getKey().getX() == s);
          break;
        
        case UP:
          test = (entry.getKey().getY() == s);
          break;
          
        case BACK:
          test = (entry.getKey().getZ() == -s);
          break;
        
        case RIGHT:
          test = (entry.getKey().getX() == -s);
          break;
        
        case DOWN:
          test = (entry.getKey().getY() == -s);
          break;
      }
      
      if(test){
        p.rotate(move);
      }
      newMap.put(p.getPosition(), p);
    }
    
    piecesMap = newMap;
  }
  
  /**
   * Gets the piece of the cube at the x, y, z coordinate
   *
   * @param x x cube coordinate
   * @param y y cube coordinate
   * @param z z cube coordinate
   *
   * @returns the cube piece at the position, or null if doesn't exist
   */
  public Piece getPiece(int x, int y, int z){
    return getPiece(new Position(x, y, z));
  }
  
  /**
   * Gets the piece of the cube at the position
   *
   * @param position position
   *
   * @returns the cube piece at the position, or null if doesn't exist
   */
  public Piece getPiece(Position position){
    if(piecesMap.containsKey(position)){
      return piecesMap.get(position);
    }else{
      return null;
    }
  }
  
  /**
   * Iterate through the cube in the 2D map format
   *
   * @returns Cube Iterator
   */
  public Iterator<Piece> iterator(){
    return new CubeIterator(this);
  }
  
  /**
   * @returns Length of side of cube
   */
  public int getSideLength(){
    return sideLength;
  }
  
  // Set the color mapper
  void setMapper(ColorMapper mapper){
    colorMapper = mapper;
  }
  
  // Initialize a cube with initially correct colors
  private void init(boolean withFaces){
    initCenters(withFaces);
    initCorners(withFaces);
    initEdges(withFaces);
  }
  
  // Initialize the centers
  private void initCenters(boolean withFaces){
    // All the faces we are using
    Face[] faces = new Face[]{
      colorMapper.getUp(),
      colorMapper.getFront(),
      colorMapper.getLeft(),
      colorMapper.getDown(),
      colorMapper.getBack(),
      colorMapper.getRight()
    };
  
    // Center is a square whose side length is 2 less than the cube
    int centerWidth = sideLength - 2;
    
    // Iterate through each piece in each center and then rotate it to the correct location
    for(int i = 0; i < 6; i ++){
      for(int j = 0; j < centerWidth; j ++){
        for(int k = 0; k < centerWidth; k ++){
        
          // Initially have it be in the front center
          int x = j - (centerWidth / 2);
          int y = k - (centerWidth / 2);
          int z = -sideLength / 2;
          
          // Even sideLength means that there is no coordinate 0
          if(sideLength % 2 == 0){
            if(j >= centerWidth / 2){
              x ++;
            }
            if(k >= centerWidth / 2){
              y ++;
            }
          }
          
          // Rotate the piece to the correct direction
          Center center = null;
          if(withFaces){
            center = new Center(Util.map(i, x, y, z), faces[i]);
          }else{
            center = new Center(Util.map(i, x, y, z));
          }

          // Add to all lists and maps
          pieces.add(center);
          centers.add(center);
          piecesMap.put(center.getPosition(), center);
        } 
      }
    }
  }
  
  // Initialize the Corners
  private void initCorners(boolean withFaces){
    // The corner is always sideLength / 2 away from Origin
    int cornerDist = sideLength / 2;
    
    // Iterate through all 8 corners
    for(int i = 0; i < 2; i ++){
      for(int j = 0; j < 2; j ++){
        for(int k = 0; k < 2; k ++){
        
          // Build the position of all 8 corners
          int x = cornerDist + (-2 * cornerDist * (1 - i));
          int y = cornerDist + (-2 * cornerDist * (1 - j));
          int z = cornerDist + (-2 * cornerDist * (1 - k));
          Position position = new Position(x, y, z);
          
          // Determine the faces for each corner
          Face[] f = new Face[3];
          f[0] = (i == 0 
              ? colorMapper.getLeft()
              : colorMapper.getRight());
              
          f[1] = (j == 0 
              ? colorMapper.getUp()
              : colorMapper.getDown());
              
          f[2] = (k == 0 
              ? colorMapper.getFront()
              : colorMapper.getBack());
          
          // Add to all lists and maps
          Corner corner = null;
          if(withFaces){
            corner = new Corner(position, f);
          }else{
            corner = new Corner(position);
          }
          pieces.add(corner);
          corners.add(corner);
          
          piecesMap.put(position, corner);
        }
      }
    }
  }
  
  // Initialize Edges
  private void initEdges(boolean withFaces){
    // An edge is 1 x (sideLength - 2)
    int edgeLength = sideLength - 2;
    
    // Distance from origin to edge
    int cubeLength = sideLength / 2;
    
    // Distance from origin to farthest edge
    int edgeCount = edgeLength / 2;
  
    // Iterate through each layer, top, middle, and bottom
    for(int i = 0; i < 3; i ++){
    
      // Each layer has four edges
      for(int j = 0; j < 4; j ++){
      
        // Each edge is composed of edgeLength pieces
        for(int k = 0; k < edgeLength; k ++){
        
          // The offset from the center to the current piece
          int offset = k - edgeCount;
          if(sideLength % 2 == 0 && k >= edgeCount){
            offset ++;
          }
          
          int x = 0;
          int y = 0;
          int z = 0;
          Face[] f = new Face[2];
          
          if(i == 0 || i == 2){
            x = (j % 2 == 0 ? offset : (j == 1 ? cubeLength : -cubeLength));
            y = cubeLength * (i == 0 ? -1 : 1);
            z = (j % 2 == 0 ? (j == 0 ? -cubeLength : cubeLength): offset);
            f[0] = (i == 0 
                ? colorMapper.getUp()
                : colorMapper.getDown());
            if(j == 0){
              f[1] = colorMapper.getFront();
            }else if(j == 1){
              f[1] = colorMapper.getRight();
            }else if(j == 2){
              f[1] = colorMapper.getBack();
            }else if(j == 3){
              f[1] = colorMapper.getLeft();
            }
          }else{
            x = (j == 0 || j == 3 ? -cubeLength : cubeLength);
            y = offset;
            z = (j < 2 ? -cubeLength : cubeLength);
            f[0] = (j < 2 
                ? colorMapper.getFront()
                : colorMapper.getBack());
                
            f[1] = (j == 0 || j == 3 
                ? colorMapper.getLeft()
                : colorMapper.getRight());
            
          }
          
          Edge edge = null;
          if(withFaces){
            edge = new Edge(new Position(x, y, z), f);
          }else{
            edge = new Edge(new Position(x, y, z));
          }
           
          // Add pieces to list and map
          pieces.add(edge);
          edges.add(edge);
          piecesMap.put(edge.getPosition(), edge);

        }
      }
    }
  }
  
  /**
   * Returns a String that can be printed to the console to display the cube's current state
   * For example, a solved cube with sideLength 3 will print out
   *
   *    YYY
   *    YYY
   *    YYY
   * BBBRRRGGGOOO
   * BBBRRRGGGOOO
   * BBBRRRGGGOOO
   *    WWW
   *    WWW
   *    WWW
   * 
   *
   * where:
   *   Y => Top
   *   B => Left
   *   R => Front
   *   G => Right
   *   O => Back
   *   W => Down
   *
   * The orientation of each face is what it would look like if the cube were flattened into
   *   a 2D pattern
   *
   * @returns A string representation of the cube suitable for console line printing
   */
  @Override
  public String toString(){
    String res = "";
    Iterator<Piece> iterator = iterator();

    for(int i = 0; i < sideLength; i ++){
      for(int k = 0; k < sideLength; k ++){
        res += ' ';
      }
      for(int j = 0; j < sideLength; j ++){
        res += iterator.next().getFace(Direction.UP).getColor().toString().charAt(0);
      }
      res += '\n';
    }
    
    for(int i = 0; i < sideLength; i ++){

      for(int j = 0; j < sideLength; j ++){
        res += iterator.next().getFace(Direction.LEFT).getColor().toString().charAt(0);
      }

      for(int j = 0; j < sideLength; j ++){
        res += iterator.next().getFace(Direction.FRONT).getColor().toString().charAt(0);
      }

      for(int j = 0; j < sideLength; j ++){
        res += iterator.next().getFace(Direction.RIGHT).getColor().toString().charAt(0);
      }

      for(int j = 0; j < sideLength; j ++){
        res += iterator.next().getFace(Direction.BACK).getColor().toString().charAt(0);
      }
      res += '\n';
    }
    
    for(int i = 0; i < sideLength; i ++){
      for(int k = 0; k < sideLength; k ++){
        res += ' ';
      }
      
      for(int j = 0; j < sideLength; j ++){
        res += iterator.next().getFace(Direction.DOWN).getColor().toString().charAt(0);
      }
      res += '\n';
    }
    return res;
  }
}