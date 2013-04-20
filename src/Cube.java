package org.haodev.puzzlecube;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.haodev.puzzlecube.Util;
import org.haodev.puzzlecube.Util.Axis;
import org.haodev.puzzlecube.Util.Color;
import org.haodev.puzzlecube.Util.Direction;
import org.haodev.puzzlecube.Util.Position;
import org.haodev.puzzlecube.Util.Rotation;

/**
 * Represents a Puzzle Cube
 *
 *
 * Puzzle Cube sits on origin with Front, Up, and Left being on the negative axis
 *   and Back, Down, Right being on the positive axis
 *
 * @author Yuhao Ma (yuhao93@gmail.com)
 */
public class Cube {
  private int sideLength = 3;
  
  private List<Corner> corners = new ArrayList<Corner>();
  private List<Edge> edges = new ArrayList<Edge>();
  private List<Center> centers = new ArrayList<Center>();
  
  private List<IPiece> pieces = new ArrayList<IPiece>();
  private Map<Position, IPiece> piecesMap = new HashMap<Position, IPiece>();
  
  /**
   * @param sideLength How many pieces make up one edge of the cube
   */
  public Cube(int sideLength){
    this.sideLength = sideLength;
    init();
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
    Map<Position, IPiece> newMap = new HashMap<Position, IPiece>();
    
    // Slice index, changed to cube coordinates
    int s = depth - (sideLength / 2);
    if(sideLength % 2 == 0 && s >= 0){
      s ++;
    }
    
    // Which direction we are coming from
    // BACK, RIGHT, and DOWN are just inverses of FRONT, LEFT, and UP
    switch(direction){
      case FRONT:
        for(Map.Entry<Position, IPiece> entry : piecesMap.entrySet()){
          IPiece p = entry.getValue();
          if(entry.getKey().getZ() == s){
            p.rotate(rotation, Axis.Z_AXIS);
          }
          newMap.put(p.getPosition(), p);
        }
        break;
        
      case LEFT:
        for(Map.Entry<Position, IPiece> entry : piecesMap.entrySet()){
          IPiece p = entry.getValue();
          if(entry.getKey().getX() == s){
            p.rotate(rotation, Axis.X_AXIS);
          }
          newMap.put(p.getPosition(), p);
        }
        break;
        
      case UP:
        for(Map.Entry<Position, IPiece> entry : piecesMap.entrySet()){
          IPiece p = entry.getValue();
          if(entry.getKey().getY() == s){
            p.rotate(rotation, Axis.Y_AXIS);
          }
          newMap.put(p.getPosition(), p);
        }
        break;
        
      case BACK:
        for(Map.Entry<Position, IPiece> entry : piecesMap.entrySet()){
          IPiece p = entry.getValue();
          if(entry.getKey().getZ() == -s){
            p.rotate(Util.reverseRotation(rotation), Axis.Z_AXIS);
          }
          newMap.put(p.getPosition(), p);
        }
        break;
        
      case RIGHT:
        for(Map.Entry<Position, IPiece> entry : piecesMap.entrySet()){
          IPiece p = entry.getValue();
          if(entry.getKey().getX() == -s){
            p.rotate(Util.reverseRotation(rotation), Axis.X_AXIS);
          }
          newMap.put(p.getPosition(), p);
        }
        break;
        
      case DOWN:
        for(Map.Entry<Position, IPiece> entry : piecesMap.entrySet()){
          IPiece p = entry.getValue();
          if(entry.getKey().getY() == -s){
            p.rotate(Util.reverseRotation(rotation), Axis.Y_AXIS);
          }
          newMap.put(p.getPosition(), p);
        }
        break;
    }
    
    piecesMap = newMap;
  }
  
  // Initialize a cube with initially correct colors
  private void init(){
    initCenters();
    initCorners();
    initEdges();
  }
  
  // Initialize the centers
  private void initCenters(){
    // All the faces we are using
    Face[] faces = new Face[]{
      Face.UP.copy(),
      Face.FRONT.copy(),
      Face.RIGHT.copy(),
      Face.DOWN.copy(),
      Face.BACK.copy(),
      Face.LEFT.copy()
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
          Center center = new Center(Util.map(i, x, y, z), faces[i]);

          // Add to all lists and maps
          pieces.add(center);
          centers.add(center);
          piecesMap.put(center.getPosition(), center);
        } 
      }
    }
  }
  
  // Initialize the Corners
  private void initCorners(){
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
              ? Face.LEFT.copy()
              : Face.RIGHT.copy());
              
          f[1] = (j == 0 
              ? Face.UP.copy()
              : Face.DOWN.copy());
              
          f[2] = (k == 0 
              ? Face.FRONT.copy()
              : Face.BACK.copy());
          
          // Add to all lists and maps
          Corner corner = new Corner(position, f);
          pieces.add(corner);
          corners.add(corner);
          
          piecesMap.put(position, corner);
        }
      }
    }
  }
  
  // Initialize Edges
  private void initEdges(){
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
                ? Face.UP.copy()
                : Face.DOWN.copy());
            if(j == 0){
              f[1] = Face.FRONT.copy();
            }else if(j == 1){
              f[1] = Face.RIGHT.copy();
            }else if(j == 2){
              f[1] = Face.BACK.copy();
            }else if(j == 3){
              f[1] = Face.LEFT.copy();
            }
          }else{
            x = (j == 0 || j == 3 ? -cubeLength : cubeLength);
            y = offset;
            z = (j < 2 ? -cubeLength : cubeLength);
            f[0] = (j < 2 
                ? Face.FRONT.copy()
                : Face.BACK.copy());
                
            f[1] = (j == 0 || j == 3 
                ? Face.LEFT.copy()
                : Face.RIGHT.copy());
            
          }
          
          Edge edge = new Edge(new Position(x, y, z), f);

          // Add pieces to list and map
          pieces.add(edge);
          edges.add(edge);
          piecesMap.put(edge.getPosition(), edge);

        }
      }
    }
  }
  
  /**
   * @returns A string representation of the cube suitable for console line printing
   */
  @Override
  public String toString(){
    String res = "";
    
    // Up face
    // j => x
    // y is constant
    // i => z
    for(int i = 0; i < sideLength; i ++){
      // Padding
      for(int k = 0; k < sideLength; k ++){
        res += " ";
      }
      
      for(int j = 0; j < sideLength; j ++){
        int x = j - (sideLength / 2);
        int y = -sideLength / 2;
        int z = i - (sideLength / 2);
        
        if(x >= 0 && sideLength % 2 == 0){
          x ++;
        }
        
        if(z >= 0 && sideLength % 2 == 0){
          z ++;
        }

        IPiece piece = piecesMap.get(new Position(x, y, -z));
        
        res += Util.colorToString(piece.getFace(Direction.UP).getColor()).charAt(0);
      }
      res += '\n';
    }
    
    // Four faces
    for(int i = 0; i < sideLength; i ++){

      // Left face
      // x is constant
      // i => y
      // l => z
      for(int l = 0; l < sideLength; l ++){
        int x = -sideLength / 2;
        int y = i - (sideLength / 2);
        int z = l - (sideLength / 2);
        
        if(y >= 0 && sideLength % 2 == 0){
          y ++;
        }
        
        if(z >= 0 && sideLength % 2 == 0){
          z ++;
        }
 
        IPiece piece = piecesMap.get(new Position(x, y, -z));
        res += Util.colorToString(piece.getFace(Direction.LEFT).getColor()).charAt(0);
      }
      
      // Front face
      // f => x
      // i => y
      // z is constant
      for(int f = 0; f < sideLength; f ++){
        int x = f - (sideLength / 2);
        int y = i - (sideLength / 2);
        int z = -sideLength / 2;
        
        if(x >= 0 && sideLength % 2 == 0) {
          x ++;
        }
        
        if(y >= 0 && sideLength % 2 == 0) {
          y ++;
        }
        
        IPiece piece = piecesMap.get(new Position(x, y, z));
        res += Util.colorToString(piece.getFace(Direction.FRONT).getColor()).charAt(0);
      }
      
      // Right face
      // x is constant
      // i => y
      // r => z
      for(int r = 0; r < sideLength; r ++){
        int x = sideLength / 2;
        int y = i - (sideLength / 2);
        int z = r - (sideLength / 2);
        
        if(y >= 0 && sideLength % 2 == 0){
          y ++;
        }
        
        if(z >= 0 && sideLength % 2 == 0){
          z ++;
        }
        
        IPiece piece = piecesMap.get(new Position(x, y, z));
        res += Util.colorToString(piece.getFace(Direction.RIGHT).getColor()).charAt(0);
      }
      
      // Back face
      // b => x
      // i => y
      // z is constant
      for(int b = 0; b < sideLength; b ++){
        int x = b - (sideLength / 2);
        int y = i - (sideLength / 2);
        int z = sideLength / 2;
        
        if(x >= 0 && sideLength % 2 == 0) {
          x ++;
        }
        
        if(y >= 0 && sideLength % 2 == 0) {
          y ++;
        }
        
        IPiece piece = piecesMap.get(new Position(-x, y, z));
        res += Util.colorToString(piece.getFace(Direction.BACK).getColor()).charAt(0);
      }
      res += '\n';
    }
    
    // Bottom face
    // j => x
    // y is constant
    // i => z
    for(int i = 0; i < sideLength; i ++){
      // Padding
      for(int k = 0; k < sideLength; k ++){
        res += " ";
      }
      
      for(int j = 0; j < sideLength; j ++){
        int x = j - (sideLength / 2);
        int y = sideLength / 2;
        int z = i - (sideLength / 2);
        
        if(x >= 0 && sideLength % 2 == 0){
          x ++;
        }
        
        if(z >= 0 && sideLength % 2 == 0){
          z ++;
        }
        
        IPiece piece = piecesMap.get(new Position(x, y, z));
        res += Util.colorToString(piece.getFace(Direction.DOWN).getColor()).charAt(0);
      }
      res += '\n';
    }

    return res;
  }
}