package org.haodev.puzzlecube;

import org.haodev.puzzlecube.Cube;

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
   * @returns true if algorithm ran successfully, false otherwise
   */
  public boolean run(Cube cube);
}