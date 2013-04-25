package org.haodev.puzzlecube;

import java.util.List;

/**
 * An algorithm that takes in a cube and applys an algorithm to it
 *
 * @author Yuhao Ma (yuhao93@gmail.com)
 */
public interface Algorithm {
  /**
   * Runs the algorithm on the cube
   *
   * @param cube the puzzle cube to apply algorithm to
   * @return Array of moves applied to the cube
   */
  public List<Move> run(Cube cube);
}