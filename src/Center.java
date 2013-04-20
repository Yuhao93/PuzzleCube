package org.haodev.puzzlecube;

import org.haodev.puzzlecube.Util.Axis;
import org.haodev.puzzlecube.Util.Direction;
import org.haodev.puzzlecube.Util.Position;
import org.haodev.puzzlecube.Util.Rotation;

/**
 * Represents a Center piece on a cube.
 * Contains one face.
 *
 * @author Yuhao Ma (yuhao93@gmail.com)
 */
public class Center implements IPiece {
  private Face face;
  private Position position;
  
  /**
   * @param face the face to paint onto this center
   * @throws IllegalArgumentException if face is null
   */
  public Center(Position position, Face face) throws IllegalArgumentException{
    if(face == null || position == null){
      throw new IllegalArgumentException();
    }
  
    this.position = position;
    this.face = face;
  }

  @Override
  public void rotate(Rotation rot, Axis axis){
    position.rotate(rot, axis);
    face.rotate(rot, axis);
  }

  @Override
  public Position getPosition(){
    return position;
  }
  
  @Override
  public Face[] getFaces(){
    return new Face[]{face};
  }
  
  @Override
  public Face getFace(Direction direction){
    return direction == face.getDirection() ? face : null;
  }
}