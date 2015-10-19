package behind;

import behind.graphics.Palette;

/**
 * Created by julien on 19/10/15.
 */
public class Gm {

    private final Palette palette = new Palette();
    private final Gps gps = new Gps();

    public Palette palette() {
        return palette;
    }

    public Gps gps() {
        return gps;
    }
}
