PuzzleCube
==========

PuzzleCube is a Java library for modeling arbitrary sized PuzzleCubes.
A PuzzleCube with a sideLength of 2 or greater can be created by a simply instantiating the `Cube` class.

```java
// Creates a 3x3x3 puzzle cube, initially solved
Cube cube = new Cube(3);
    
// Prints out the cube
System.out.println(cube);
    
// Rotates the front face clockwise
cube.rotate(Rotation.CLOCKWISE, Direction.FRONT, 0);
    
// Prints out the cube after rotated
System.out.println(cube);
```

Cube
----

A cube can be thought of as a lattice of points that lie in 3D space. 
Each piece of the cube can be represented as a single point. 
Each piece contains one or several faces. 
Each face represents a single sticker on the cube and has a direction facing and color component. 
The center of this lattice always lies on the origin. 
As such, odd and even length cubes have different ways of denoting the position of each piece.

The range of coordinates that a piece can have in a `sideLength` sized cube will be 
`[-sideLength / 2, sideLength / 2]`. As an example, a 3x3x3 cube would have a corner at `{-1, -1, -1}`, 
an edge at `{0, -1, -1}` and the mirror corner at `{1, -1, -1}`. In contrast, a 4x4x4 cube would have a corner at 
`{-2, -2, -2}`, two edges at `{-1, -2, -2}` and `{1, -2, -2}`, and the mirror corner at `{2, -2, -2}`. 
Notice that for cubes with an even sideLength, to maintain the symmetry, there are no pieces with any coordinates of 0. 
Therefore, for a 4x4x4 cube, the 4 possible values for any coordinate is `[-2, -1, 1, 2]` while the 3 possible values 
for a 3x3x3 cube is `[-1, 0, 1]`.

A cube can be printed out to the console easily by calling:
```Java
System.out.println(cube);
```

The cube layout will look like an unfolded 2D version of the cube. 
For a cube where `T` corresponds to the Top face, `F` corresponds to the Front face, 
`L` corresponds to the Left face, `D` corresponds to the Down face, `B` corresponds to the Back face, and 
`R` corresponds to the Right face, the map of a 3x3x3 cube would follow along the lines of:
```
      T T T
      T T T
      T T T
L L L F F F R R R B B B
L L L F F F R R R B B B
L L L F F F R R R B B B
      D D D
      D D D
      D D D
```



Rotation
--------

A puzzle cube wouldn't be a puzzle cube if you can't rotate it! When you rotate a cube, 
you are rotating a "slice" of the cube. A slice could be one of the outer faces, 
but it could also be one of the interior layers as well. Slices are numbered with the 
outermost layer being `slice 0` and the opposite face being `slice sideLength - 1`. 

The front slices for a 3x3x3 puzzle cube will appear like:
```
      2 2 2
      1 1 1
      0 0 0 
2 1 0 0 0 0 0 1 2 2 2 2
2 1 0 0 0 0 0 1 2 2 2 2
2 1 0 0 0 0 0 1 2 2 2 2
      0 0 0
      1 1 1
      2 2 2
```
Although a slice 2 of the front face refers to the same pieces as slice 0 of the back face, 
directions will be reversed as you will be viewing the slice from two opposite directions.
      
The direction is simply from which perspective you are viewing the cube when describing the rotation.
The rotation describes which way, clockwise or counter clockwise, to turn the slice.

All of this can be put into a single line of code to rotate a single slice of the cube:
```Java
// Rotating the outermost front slice clockwise
cube.rotate(Rotation.CLOCKWISE, Direction.FRONT, 0);
```  

Coloring
--------

You can manually color a cube by painting its individual pieces. The `Cube` class doesn't 
perform any kind of validation to ensure that a cube is in a valid and solvable state, so 
painting parts of the cube must be done with caution.

You can paint a face of a piece of the cube using coordinates:
```Java
// Painting the front face of the upper-left-front corner green
//using coordinates
cube.paintPiece(new Position(-1, -1, -1), Direction.FRONT, Color.GREEN);
```

Or you can perform the same function using the piece itself as a convenience:
```Java
// Painting the front face of the upper-left-front corner green 
// using the piece itself
cube.paintPiece(corner, Direction.FRONT, Color.GREEN);
```



Reading From a File
-------------------

A cube can be loaded in from a configuration outlined from a file using the `CubeParser` class.
The formatting of the configuration file must be correct in order for the cube to be correctly 
constructed. The configuration file contains tokens separated by at least one white space. 
Tabs, newlines, and extra whitespaces will be ignored. A `#` character denotes the start of a 
comment line, as such, any characters after the `#` character will be ignored.

The tokens come in a specific order.
  1. sideLength
  2. 1 character abbreviation of the top face color
  3. Color of the top face
  4. 1 character abbreviation of the front face color
  5. Color of the front face
  6. 1 character abbreviation of the left face color
  7. Color of the left face
  8. 1 character abbreviation of the bottom face color
  9. Color of the bottom face
  10. 1 character abbreviation of the front back color
  11. Color of the back face
  12. 1 character abbreviation of the right face color
  13. Color of the right face
  14. From here onwards, 1 character abbreviation of the color to be painted onto the face corresponding 
to the order the faces are iterated through
  
The order which the faces are iterated through would be:
```
           1  2  3
           4  5  6
           7  8  9
 10 11 12 13 14 15 16 17 18 19 20 21
 22 23 24 25 26 27 28 29 30 31 32 33
 34 35 36 37 38 39 40 41 42 43 44 45
          46 47 48
          49 50 51
          52 53 54
```

A configuration file named `checkers.txt` would look like:
```
# Everything on a line after the '#' character is a comment
# Cube Side Length
3

# Each color is a one character abbreviation followed by 
# the name of the color

# Up Color
Y Yellow

# Front Color
R Red

# Left Color
B Blue

# Down Color
W White

# Back Color
O Orange

# Right Color
G Green


# Cube Map ( Checkerboard pattern )
# Abbreviations of the colors, each separated by at least one space
      Y W Y
      W Y W
      Y W Y
B G B R O R G B G O R O
G B G O R O B G B R O R
B G B R O R G B G O R O
      W Y W
      Y W Y
      W Y W
```

A cube can be generated from the configuration file with:
```Java
Cube cube = CubeParser.createCubeFromFile("checkers.txt");
```
  
Writing to a File
-----------------

Writing a cube to a file is very similar to how you would read a cube from a file. 
The file that is written can be read using the reading functionality. 
An example of writing a cube to a configurtion file, creating it if it doesn't exit:
```Java
CubeParser.writeCubeToFile(cube, "checkers.txt");
```
  
  
Solution
--------

Future updates of PuzzleCube will include ways of solving a cube. 
This will be implemented using the `Algorithm` interface. 
A sequence of algorithms will be applied to a cube, solving it.