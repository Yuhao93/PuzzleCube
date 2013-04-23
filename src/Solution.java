package org.haodev.puzzlecube;

import java.util.ArrayList;
import java.util.List;

/**
 * A solution, that brings a cube from a mixed state to a solved state
 * 
 * @author Yuhao Ma (yuhao93@gmail.com)
 */
public abstract class Solution {
  private Algorithm[] steps;
  
  /**
   * Get the list of moves to perform on the cube to solve it
   *
   * @param cube The cube to solve
   * @returns List of moves
   */
  public List<Move> solve(Cube cube){
    List<Move> moves = new ArrayList<Move>();
    for(Algorithm step : steps){
      moves.addAll(step.run(cube));
    }
    
    return moves;
  }
}