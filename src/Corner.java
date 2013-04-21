package org.haodev.puzzlecube;

import org.haodev.puzzlecube.Position;
import org.haodev.puzzlecube.Util.Axis;
import org.haodev.puzzlecube.Util.Direction;
import org.haodev.puzzlecube.Util.Rotation;

/**
 * Represents a Corner piece on a cube.
 * Contains three faces.
 *
 * @author Yuhao Ma (yuhao93@gmail.com)
 */
public class Corner implements IPiece {
  private Face[] faces;
  private Position position;
  
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
  }

  @Override
  public void rotate(Rotation rot, Axis axis){
    position.rotate(rot, axis);
    for(Face face : faces){
      face.rotate(rot, axis);
    }
  }

  @Override
  public Position getPosition(){
    return position;
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
}