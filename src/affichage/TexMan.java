package affichage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

/*
 * Classe servant à centraliser la gestion des textures
 */

public class TexMan {

public static Sprite menuPlay;

	public static TextureAtlas atlas;
	public static AtlasRegion XP;

	public static void loadGame() {
		atlas = new TextureAtlas(Gdx.files.internal("textures.atlas"));
		XP = atlas.findRegion("xp");
	}
	
	private TexMan() {
		super();
	}
}
