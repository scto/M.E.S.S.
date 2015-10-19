package ToBeSorted;

import com.badlogic.gdx.utils.NumberUtils;

/**
 * Created by julien on 19/10/15.
 */
public class Palette {

    private final short max = 255, alpha = 24, r = 16, g = 8;
    public final float white = convertARGB(1, 1, 1, 1), black = convertARGB(1, 0, 0, 0), alpha70 = convertARGB(.70f, 1, 1, 1), red = convertARGB(1, 1, 0, 0),
            green = convertARGB(1, 0, 1, 0);
    private int tmpInt;

    public float convertARGB(float a, float r, float g, float b) {
        return NumberUtils.intToFloatColor(((int) (max * a) << alpha) | ((int) (max * b) << this.r) | ((int) (max * g) << this.g) | ((int) (max * r)));
    }

    public Float convertARGB(float a, float all) {
        return NumberUtils.intToFloatColor(((int)(max * a) << alpha) | ((int)(max * all) << r) | ((int)(max * all) << g) | ((int)(max * all)));
    }
    public float setAlpha(float color, float alpha) {
        tmpInt = NumberUtils.floatToIntColor(color);
        return convertARGB(alpha, (tmpInt & 0xff) / 255f, ((tmpInt >>> 8) & 0xff) / 255f, ((tmpInt >>> 16) & 0xff) / 255f);
    }
    public float setBlue(float color, float blue) {
        tmpInt = NumberUtils.floatToIntColor(color);
        return convertARGB((tmpInt & 0xff) / 255f, (tmpInt & 0xff) / 255f, ((tmpInt >>> 8) & 0xff) / 255f, blue);
    }
}
