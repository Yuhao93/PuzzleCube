PuzzleCube
==========

PuzzleCube is a Java library for modeling arbitrary sized PuzzleCubes.
A PuzzleCube with a sideLength of 2 or greater can be created by a simply instantiating the `Cube` class.

```java
import org.haodev.puzzlecube.Cube;

/**
 * Example for demonstrating a cube
 */
public class Launcher{
  public static void main(String[] args){
    // Creates a 3x3x3 puzzle cube, initially solved
    Cube cube = new Cube(3);
    
    // Prints out the cube
    System.out.println(cube);
    
    // Rotates the front face clockwise
    cube.rotate(Rotation.CLOCKWISE, Direction.FRONT, 0);
    
    // Prints out the cube after rotated
    System.out.println(cube);
  }
}
```

Cube
----

A cube can be thought of as a lattice of points that lie in 3D space. Each piece of the cube can be represented as a single point. The center of this lattice always lies on the origin. As such, odd and even length cubes have different ways of denoting the position of each piece.

The range of coordinates that a piece can have in a `sideLength` sized cube will be [-sideLength / 2, sideLength / 2]. As an example, a 3x3x3 cube would have a corner at `{-1, -1, -1}`, an edge at `{0, -1, -1}` and the opposite corner at `{1, -1, -1}`. In contrast, a 4x4x4 cube would have a corner at  `{-2, -2, -2}`, two edges at `{-1, -2, -2}` and `{1, -2, -2}`, and the opposite corner at `{2, -2, -2}`. Notice that for cubes with an even sideLength, to maintain the symmetry, there are no pieces with any coordinates of 0. Therefore, for a 4x4x4 cube, the 4 possible values for any coordinate is `[-2, -1, 1, 2]`.

Solution
--------

Future updates of PuzzleCube will include ways of solving a cube. This will be implemented using the `Algorithm` interface. A sequence of algorithms will be applied to a cube, solving it.