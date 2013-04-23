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
      Color.RED,
      Color.BLUE,
      Color.YELLOW,
      Color.ORANGE,
      Color.GREEN,
      Color.WHITE
    };
  }
  
  public Color getFrontColor(){
    return colors[0];
  }
  
  public Color getLeftColor(){
    return colors[1];
  }
  
  public Color getUpColor(){
    return colors[2];
  }
  
  public Color getBackColor(){
    return colors[3];
  }
  
  public Color getRightColor(){
    return colors[4];
  }
  
  public Color getDownColor(){
    return colors[5];
  }
  
  public Face getFront(){
    return new Face(Direction.FRONT, getFrontColor());
  }
  
  public Face getLeft(){
    return new Face(Direction.LEFT, getLeftColor());
  }
  
  public Face getUp(){
    return new Face(Direction.UP, getUpColor());
  }
  
  public Face getBack(){
    return new Face(Direction.BACK, getBackColor());
  }
  
  public Face getRight(){
    return new Face(Direction.RIGHT, getRightColor());
  }
  
  public Face getDown(){
    return new Face(Direction.DOWN, getDownColor());
  }
}