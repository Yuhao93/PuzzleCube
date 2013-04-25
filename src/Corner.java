package org.haodev.puzzlecube;

import org.haodev.puzzlecube.Util.Direction;

/**
 * Represents a Corner piece on a cube.
 * Contains three faces.
 *
 * @author Yuhao Ma (yuhao93@gmail.com)
 */
public class Corner implements Piece {
  private Face[] faces;
  private Position position;
  private int faceInd = 0;
  
  /**
   * @param faces the faces to paint onto this corner
   * @throws IllegalArgumentException if faces is null or faces is the wrong length
   */
  public Corner(Position position, Face[] faces) throws IllegalArgumentException {
    if(faces == null || faces.length != 3 || position == null){
      throw new IllegalArgumentException();
    }
    
    this.position = position;
    this.faces = faces;
    this.faceInd = 3;
  }

  Corner(Position position){
    this.faces = new Face[3];
    this.position = position;
  }
  
  void addFace(Face face){
    faces[faceInd ++] = face;
  }
  
  @Override
  public void rotate(Move move){
    position.rotate(move);
    for(Face face : faces){
      face.rotate(move);
    }
  }

  @Override
  public Position getPosition(){
    return position;
  }
  
  @Override
  public String getPieceType(){
    return "Corner";
  }
  
  @Override
  public Face[] getFaces(){
    return faces;
  }
  
  @Override
  public Face getFace(Direction direction){
    for(Face face : faces){
      if(face.getDirection() == direction){
        return face;
      }
    }
    return null;
  }
  
  @Override
  public boolean setFace(Direction direction, Color color){
    for(Face face : faces){
      if(face.getDirection() == direction){
        face.setColor(color);
        return true;
      }
    }
    return false;
  }
  
  @Override
  public boolean hasFace(Direction direction){
    for(Face face : faces){
      if(face.getDirection() == direction){
        return true;
      }
    }
    return false;
  }
}