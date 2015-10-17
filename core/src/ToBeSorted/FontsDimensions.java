package ToBeSorted;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import jeu.CharSeq;

/**
 * Created by julien on 15/10/15.
 */
public class FontsDimensions {

    private final GlyphLayout layout;

    public FontsDimensions() {
        layout = new GlyphLayout();
    }

    public float getWidth(BitmapFont menuFont, CharSequence strScore) {
        layout.setText(menuFont, strScore);
        return layout.width;
    }
    public float getHeight(BitmapFont menuFont, CharSequence strScore) {
        layout.setText(menuFont, strScore);
        return layout.height;
    }
}
