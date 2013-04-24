package org.haodev.puzzlecube;

import org.haodev.puzzlecube.Util.Direction;

/**
 * Maps sides with its respecive colors
 *
 * @author Yuhao Ma (yuhao93@gmail.com)
 */
public class ColorMapper {
  private Color[] colors;

  /**
   * @param colors list of colors to map to each face
   *               colors[0] = Up face color
   *               colors[1] = Front face color
   *               colors[2] = Left face color
   *               colors[5] = Down face color
   *               colors[3] = Back face color
   *               colors[4] = Right face color   
   */
  public ColorMapper(Color[] colors){
    this.colors = colors;
  }
  
  public ColorMapper(){
    this.colors = new Color[]{
      Color.YELLOW,
      Color.RED,
      Color.BLUE,
      Color.WHITE,
      Color.ORANGE,
      Color.GREEN
    };
  }
  
  /**
   * @returns the color of the up face
   */
  public Color getUpColor(){
    return colors[0];
  }
  
  /**
   * @returns the color of the front face
   */
  public Color getFrontColor(){
    return colors[1];
  }
  
  /**
   * @returns the color of the left face
   */
  public Color getLeftColor(){
    return colors[2];
  }
  
  /**
   * @returns the color of the down face
   */
  public Color getDownColor(){
    return colors[3];
  }
  
  /**
   * @returns the color of the back face
   */
  public Color getBackColor(){
    return colors[4];
  }
  
  /**
   * @returns the color of the right face
   */
  public Color getRightColor(){
    return colors[5];
  }
  
  /**
   * @returns an up face with the up color
   */
  public Face getUp(){
    return new Face(Direction.UP, getUpColor());
  }
  
  /**
   * @returns a front face with the front color
   */
  public Face getFront(){
    return new Face(Direction.FRONT, getFrontColor());
  }
  
  /**
   * @returns a left face with the left color
   */
  public Face getLeft(){
    return new Face(Direction.LEFT, getLeftColor());
  }
  
  /**
   * @returns a down face with the down color
   */
  public Face getDown(){
    return new Face(Direction.DOWN, getDownColor());
  }
  
  /**
   * @returns a back face with the back color
   */
  public Face getBack(){
    return new Face(Direction.BACK, getBackColor());
  }
  
  /**
   * @returns a right face with the right color
   */
  public Face getRight(){
    return new Face(Direction.RIGHT, getRightColor());
  }
}