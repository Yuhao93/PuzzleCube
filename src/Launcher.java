package org.haodev.puzzlecube;

import org.haodev.puzzlecube.Util.Axis;
import org.haodev.puzzlecube.Util.Direction;
import org.haodev.puzzlecube.Util.Rotation;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import java.util.Scanner;

public class Launcher{
  public static void main(String[] args) throws Exception{
    Cube cube = CubeParser.createCubeFromFile(args[0]);
    System.out.println(cube);
    
    Scanner scanner = new Scanner(System.in);
    String str = scanner.next().toLowerCase();
    while(!str.equals("q")){
      if(str.equals("f")){
        cube.rotate(Rotation.CLOCKWISE, Direction.FRONT, 0);
      }else if(str.equals("f'")){
        cube.rotate(Rotation.COUNTER_CLOCKWISE, Direction.FRONT, 0);
      }else if(str.equals("l")){
        cube.rotate(Rotation.CLOCKWISE, Direction.LEFT, 0);
      }else if(str.equals("l'")){
        cube.rotate(Rotation.COUNTER_CLOCKWISE, Direction.LEFT, 0);
      }else if(str.equals("u")){
        cube.rotate(Rotation.CLOCKWISE, Direction.UP, 0);
      }else if(str.equals("u'")){
        cube.rotate(Rotation.COUNTER_CLOCKWISE, Direction.UP, 0);
      }else if(str.equals("b")){
        cube.rotate(Rotation.CLOCKWISE, Direction.BACK, 0);
      }else if(str.equals("b'")){
        cube.rotate(Rotation.COUNTER_CLOCKWISE, Direction.BACK, 0);
      }else if(str.equals("r")){
        cube.rotate(Rotation.CLOCKWISE, Direction.RIGHT, 0);
      }else if(str.equals("r'")){
        cube.rotate(Rotation.COUNTER_CLOCKWISE, Direction.RIGHT, 0);
      }else if(str.equals("d")){
        cube.rotate(Rotation.CLOCKWISE, Direction.DOWN, 0);
      }else if(str.equals("d'")){
        cube.rotate(Rotation.COUNTER_CLOCKWISE, Direction.DOWN, 0);
      }
      System.out.println(cube);
      str = scanner.next().toLowerCase();
    }
    
  }
}