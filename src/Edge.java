package org.haodev.puzzlecube;

import org.haodev.puzzlecube.Util.Direction;

/**
 * Represents an Edge piece on a cube.
 * Contains two faces.
 *
 * @author Yuhao Ma (yuhao93@gmail.com)
 */
class Edge implements Piece {
  private Face[] faces;
  private Position position;
  private int faceInd = 0;

  Edge(Position position, Face[] faces){
    this.faces = faces;
    this.position = position;
    this.faceInd = 2;
  }
  
  Edge(Position position){
    this.faces = new Face[2];
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
    return "Edge";
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