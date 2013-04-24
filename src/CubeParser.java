package org.haodev.puzzlecube;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

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
    return createCubeFromFile(new File(fileName));
  }
  
  /**
   * Given a file, parse it and return the cube
   *
   * @param file The file to parse
   * @returns the cube generated from the file
   */
  public static Cube createCubeFromFile(File file)
      throws IOException, FileNotFoundException{
    BufferedReader br = new BufferedReader(new FileReader(file));
    String res = "";
    String str = br.readLine();
    while(str != null){
    
      // Remove Comments
      if(str.indexOf("#") >= 0){
        str = str.substring(0, str.indexOf("#"));
      }
    
      res += str + " ";
      str = br.readLine();
    }
    return createCubeFromArgs(res);
  }
  
  /**
   * Given a cube and the file path to write to, write the cube
   *   to the file
   *
   * @param cube the cube to write
   * @param fileName the file path to the file to write to
   */
  public static void writeCubeToFile(Cube cube, String fileName)
      throws IOException, FileNotFoundException{
    writeCubeToFile(cube, new File(fileName));
  }
  
  /**
   * Given a cube and the file to write to, write the cube 
   *   onto the file
   *
   * @param cube the cube to write
   * @param file the file to write to
   */
  public static void writeCubeToFile(Cube cube, File file)
      throws IOException, FileNotFoundException{
    file.createNewFile();
    FileOutputStream fos = new FileOutputStream(file);
    String res = "";
    ColorMapper mapper = cube.getMapper();
    int ind = 0;
    
    res += cube.getSideLength() + " ";
    res += mapper.getUpColor().toString().charAt(0) + " ";
    res += mapper.getUpColor() + " ";
    res += mapper.getFrontColor().toString().charAt(0) + " ";
    res += mapper.getFrontColor() + " ";
    res += mapper.getLeftColor().toString().charAt(0) + " ";
    res += mapper.getLeftColor() + " ";
    res += mapper.getDownColor().toString().charAt(0) + " ";
    res += mapper.getDownColor() + " ";
    res += mapper.getBackColor().toString().charAt(0) + " ";
    res += mapper.getBackColor() + " ";
    res += mapper.getRightColor().toString().charAt(0) + " ";
    res += mapper.getRightColor() + " ";
    
    for(Iterator<Piece> iterator = cube.iterator(); iterator.hasNext(); ind ++){
      res += iterator.next().getFace(Util.determineDirectionFromInd(ind, cube.getSideLength()))
          .getColor().toString().charAt(0) + " ";
    }
    
    fos.write(res.getBytes());
    fos.flush();
  }
  
  static Cube createCubeFromArgs(String args){
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