package org.haodev.puzzlecube;

import org.haodev.puzzlecube.Util.Axis;
import org.haodev.puzzlecube.Util.Direction;
import org.haodev.puzzlecube.Util.Position;
import org.haodev.puzzlecube.Util.Rotation;

/**
 * Represents an Edge piece on a cube.
 * Contains two faces.
 *
 * @author Yuhao Ma (yuhao93@gmail.com)
 */
public class Edge implements IPiece {
  private Face[] faces;
  private Position position;
  
  /**
   * @param faces the faces to paint onto this edge
   * @throws IllegalArgumentException if faces is null or faces is the wrong length
   */
  public Edge(Position position, Face[] faces) throws IllegalArgumentException{
    if(faces == null || faces.length != 2 || position == null){
      throw new IllegalArgumentException();
    }
  
    this.faces = faces;
    this.position = position;
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