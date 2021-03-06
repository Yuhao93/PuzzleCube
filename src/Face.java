package org.haodev.puzzlecube;

import org.haodev.puzzlecube.Util.Axis;
import org.haodev.puzzlecube.Util.Direction;
import org.haodev.puzzlecube.Util.Rotation;

/**
 * A single face of one cube piece.
 * Has a color and direction that it is facing.
 *
 * @author Yuhao Ma (yuhao93@gmail.com)
 */
public class Face {
  static final Face UP = new Face(Direction.UP, Color.YELLOW);
  static final Face LEFT = new Face(Direction.LEFT, Color.BLUE);
  static final Face FRONT = new Face(Direction.FRONT, Color.RED);
  static final Face DOWN = new Face(Direction.DOWN, Color.WHITE);
  static final Face RIGHT = new Face(Direction.RIGHT, Color.GREEN);
  static final Face BACK = new Face(Direction.BACK, Color.ORANGE);

  private Direction direction;
  private Color color;

  /**
   * @param direction the direction that the face is facing
   * @param color the color that the face is
   */
  public Face(Direction direction, Color color){
    this.direction = direction;
    this.color = color;
  }
  
  /**
   * @return the direction that the face is pointing
   */
  public Direction getDirection(){
    return direction;
  }
  
  /**
   * @return the color of the face
   */
  public Color getColor(){
    return color;
  }
  
  /**
   * @return shallow copy of this Face
   */
  public Face copy(){
    return new Face(this.direction, this.color);
  }
  
  /**
   * Rotate the face around axis by rot
   * 
   * @param move the transformation to apply to the cube
   */
  public void rotate(Move move){
    switch(move.getAxis()){
      case X_AXIS:
        direction = Util.rotateFaceX(move, direction);
        break;
        
      case Y_AXIS:
        direction = Util.rotateFaceY(move, direction);
        break;
        
      case Z_AXIS:
        direction = Util.rotateFaceZ(move, direction);
        break;
    }

  }
  
  void setColor(Color color){
    this.color = color;
  }
}