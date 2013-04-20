package org.haodev.puzzlecube;

import org.haodev.puzzlecube.Util.Axis;
import org.haodev.puzzlecube.Util.Direction;
import org.haodev.puzzlecube.Util.Rotation;
import org.haodev.puzzlecube.Util.Position;

/**
 * Interface to be inherited by pieces of the cube.
 *
 * @author Yuhao Ma (yuhao93@gmail.com)
 */
public interface IPiece{
  /**
   * Rotates the piece around axis by rot
   *
   * @param rot the direction to rotate the piece
   * @param axis the axis to rotate the piece around
   */
  public void rotate(Rotation rot, Axis axis);
  
  /**
   * Gets the position of the piece
   *
   * @returns the position of the piece
   */
  public Position getPosition();
  
  /**
   * Gets the faces of the piece
   *
   * @returns the faces of the piece
   */
  public Face[] getFaces();
  
  /**
   * Gets the face specified by the direction
   * or null if it doesn't exist
   *
   * @param direction the direction of the face
   * @returns the face in that direction, or null
   */
  public Face getFace(Direction direction);
}