package org.haodev.puzzlecube;

/**
 * A Color for a face
 *
 * @author Yuhao Ma (yuhao93@gmail.com)
 */
public class Color{
  public static final Color RED = new Color("Red");
  public static final Color BLUE = new Color("Blue");
  public static final Color YELLOW = new Color("Yellow");
  public static final Color ORANGE = new Color("Orange");
  public static final Color GREEN = new Color("Green");
  public static final Color WHITE = new Color("White");

  private String color;

  /**
   * @param color the color to create
   */
  public Color(String color){
    this.color = color;
  }
  
  @Override
  public int hashCode(){
    return color.hashCode();
  }
  
  @Override
  public boolean equals(Object obj){
    if(obj instanceof Color){
      return ((Color)obj).color.equals(color);
    }
    return false;
  }
  
  @Override
  public String toString(){
    return color;
  }
}