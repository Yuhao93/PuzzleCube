package org.haodev.puzzlecube;

import org.haodev.puzzlecube.Util.Direction;

/**
 * Interface to be inherited by pieces of the cube.
 *
 * @author Yuhao Ma (yuhao93@gmail.com)
 */
public interface Piece{
  /**
   * Rotates the piece around axis by rot
   *
   * @param move the move to do
   */
  public void rotate(Move move);
  
  /**
   * Gets the position of the piece
   *
   * @return the position of the piece
   */
  public Position getPosition();
  
  /**
   * Gets the string name of the piece type
   *
   * @return the string name of the piece type
   */
  public String getPieceType();
  
  /**
   * Gets the faces of the piece
   *
   * @return the faces of the piece
   */
  public Face[] getFaces();
  
  /**
   * Gets the face specified by the direction
   * or null if it doesn't exist
   *
   * @param direction the direction of the face
   * @return the face in that direction, or null
   */
  public Face getFace(Direction direction);
  
  /**
   * Try to paint a face of the piece.
   * 
   * @param direction the direction of the face to paint
   * @param color the color to paint
   * @return true if successful, false if the face doesn't exist
   */
  public boolean setFace(Direction direction, Color color);
  
  /**
   * Check to see if the piece has a face pointing in
   * a given direction.
   *
   * @param direction the direction of the face
   * @return true if the piece has a face pointing in 
   *          the given direction
   */
  public boolean hasFace(Direction direction);
}