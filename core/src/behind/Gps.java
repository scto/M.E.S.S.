package behind;

import jeu.CSG;

/**
 * Created by julien on 19/10/15.
 */
public class Gps {

    public int locateVertical5Portions(float x) {
        if (x < CSG.widthDiv5)      return 0;
        if (x < CSG.widthDiv5Mul2)  return 1;
        if (x < CSG.widthDiv5Mul3)  return 2;
        if (x < CSG.widthDiv5Mul4)  return 3;
        return 4;
    }
}
