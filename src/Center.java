package org.haodev.puzzlecube;

import org.haodev.puzzlecube.Util.Direction;

/**
 * Represents a Center piece on a cube.
 * Contains one face.
 *
 * @author Yuhao Ma (yuhao93@gmail.com)
 */
class Center implements Piece {
  private Face face;
  private Position position;
  
  Center(Position position, Face face) {
    this.position = position;
    this.face = face;
  }
  
  Center(Position position){
    this.position = position;
  }
  
  void addFace(Face face){
    if(this.face != null){
      throw new IndexOutOfBoundsException();
    }
    this.face = face;
  }

  @Override
  public void rotate(Move move){
    position.rotate(move);
    face.rotate(move);
  }

  @Override
  public Position getPosition(){
    return position;
  }
  
  @Override
  public String getPieceType(){
    return "Center";
  }
  
  @Override
  public Face[] getFaces(){
    return new Face[]{face};
  }
  
  @Override
  public Face getFace(Direction direction){
    return direction == face.getDirection() ? face : null;
  }
  
  @Override
  public boolean setFace(Direction direction, Color color){
    if(face.getDirection() == direction){
        face.setColor(color);
        return true;
    }
    return false;
  }
  
  @Override
  public boolean hasFace(Direction direction){
    return face.getDirection() == direction;
  }
}