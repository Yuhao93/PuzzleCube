package org.haodev.puzzlecube;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import org.haodev.puzzlecube.Util.Direction;

/**
 * Builds a cube from various inputs
 *
 * @author Yuhao Ma (yuhao93@gmail.com)
 */
public class CubeParser {
  private CubeParser() { }
  
  /**
   * Given a file, parse it and return the cube
   *
   * @param fileName The file path leading to the file to parse
   * @returns the cube generated from the file
   */
  public static Cube createCubeFromFile(String fileName)
      throws IOException, FileNotFoundException{
    BufferedReader br = new BufferedReader(new FileReader(fileName));
    String res = "";
    String str = br.readLine();
    while(str != null){
    
      // Remove Comments
      if(str.indexOf("#") >= 0){
        str = str.substring(0, str.indexOf("#"));
      }
    
      res += str + ' ';
      str = br.readLine();
    }
    return createCubeFromArgs(res);
  }
  
  /**
   * Given a String, parse it and return the cube
   *
   * @param args the string to parse
   * @returns the cube generated from the file
   */
  public static Cube createCubeFromArgs(String args){
    Scanner scanner = new Scanner(args);
    int sideLength = scanner.nextInt();
    
    Map<String, String> colorRelation = new HashMap<String, String>();
    Color[] mapColors = new Color[6];
    
    for(int i = 0; i < 6; i ++){
      String abbr = scanner.next();
      String name = scanner.next();
      colorRelation.put(abbr, name);
      mapColors[i] = new Color(name);
    }
    
    Color[] colors = new Color[6 * sideLength * sideLength];
    for(int i = 0; i < colors.length; i ++){
      colors[i] = new Color(colorRelation.get(scanner.next()));
    }
  
    Cube cube = new Cube(sideLength, false);
    cube.setMapper(new ColorMapper(mapColors));
    
    Iterator<Piece> iterator = cube.iterator();
    for(int i = 0; i < colors.length; i ++){
      Color color = colors[i];
      Direction direction = Util.determineDirectionFromInd(i, sideLength);
      Piece piece = iterator.next();
      String type = piece.getPieceType();
      if(type.equals("Edge")){
        Edge edge = (Edge) piece;
        edge.addFace(new Face(direction, color));
        
      }else if(type.equals("Corner")){
        Corner corner = (Corner) piece;
        corner.addFace(new Face(direction, color));
        
      }else if(type.equals("Center")){
        Center center = (Center) piece;
        center.addFace(new Face(direction, color));
      }
    }
    
    return cube;
  }
}