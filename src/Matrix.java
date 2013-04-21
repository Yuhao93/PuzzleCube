package org.haodev.puzzlecube; 

import org.haodev.puzzlecube.Util.Axis;
import org.haodev.puzzlecube.Util.Rotation;

class Matrix {
  int[] m = new int[9];
  
  Matrix(Rotation rot, Axis axis) {
    int cos = 0;
    int sin = rot == Rotation.CLOCKWISE ? 1 : -1;
  
    switch(axis){
      case X_AXIS:
        m = new int[]{1, 0, 0, 0, cos, -sin, 0, sin, cos};
        break;
        
      case Y_AXIS:
        m = new int[]{cos, 0, sin, 0, 1, 0, -sin, 0, cos};
        break;
        
      case Z_AXIS:
        m = new int[]{cos, -sin, 0, sin, cos, 0, 0, 0, 1};
        break;
    }
  }
    
  int[] rotate(Position point){
    int[] res = new int[3];
    int[] points = point.points();
    
    for(int i = 0; i < res.length; i ++){
      for(int j = 0; j < 3; j ++){
        res[i] += points[j] * m[j + (i * 3)];
      }
    }
    
    return res;
  }
}