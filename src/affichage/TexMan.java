package affichage;

import menu.CSG;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/*
 * Classe servant à centraliser la gestion des textures
 */

public class TexMan {

	private static Texture t;
	private static final boolean ENABLE_FILTER = false;
	//** The play button
	private static final float LARGEUR_PLAY = CSG.LARGEUR_ECRAN / 2;
	private static final float HT_PLAY = CSG.HAUTEUR_ECRAN / 5;
	private static final float POSX_PLAY = LARGEUR_PLAY/2;
	private static final float POSY_PLAY = (HT_PLAY/2) * 8;
	public static Sprite menuPlay;
	
	public static Texture lesVaisseaux;
	public static TextureRegion[] animationVaisseau;
	//** Background étoiles
	public static TextureRegion trFond1;
	public static TextureRegion trFond2;
	//** Balles
	public static Texture balleDeBase;
	public static Texture balleFeu;
	public static Texture bouleBleu;
	//** Ennemis
	public static Texture ennemiTourne;
	public static Texture vaisseauxAileDeployee;
	public static Texture bouleBleuRouge;
	//** Explosions ---- explosion 1
	//** XP et autre
	
	public static TextureAtlas atlas;
	public static AtlasRegion XP;

	public static void loadMenu() {
		// ** The play button
		menuPlay = configSprite(configTexture("menu/play.png",ENABLE_FILTER), LARGEUR_PLAY, HT_PLAY, POSX_PLAY, POSY_PLAY);	
	}

	public static void loadGame() {
		lesVaisseaux = new Texture(Gdx.files.internal("jeu/vaisseaux24sur28.png"));
		vaisseauxAileDeployee = new Texture(Gdx.files.internal("jeu/ennemiailes21sur21et3frames.png"));
		// ** background étoiles
		Texture tFond = new Texture(Gdx.files.internal("jeu/etoiles.png"));
		trFond1 = new TextureRegion(tFond);
		tFond = new Texture(Gdx.files.internal("jeu/etoiles2.png"));
		trFond2 = new TextureRegion(tFond);
		// ** XP
		Texture tmp = new Texture(Gdx.files.internal("jeu/items/items.png"));
//		XP = new TextureRegion(tmp, 5, 5);
//		triangleRond = new Texture(Gdx.files.internal("jeu/triangle21large22haut.png"));
		// ** Balles
		//tmp = new Texture(Gdx.files.internal("jeu/balles/balles12sur12et13frames.png"));
		balleDeBase = new Texture(Gdx.files.internal("jeu/balles/balles12sur12et13frames.png"));
		balleFeu = new Texture(Gdx.files.internal("jeu/balles/ballefeu16sur27et3frames.png"));
		bouleBleu = new Texture(Gdx.files.internal("jeu/balles/boulebleu20sur20et2frames.png"));
		// ** explosion
//		explosionNv1 = new Texture(Gdx.files.internal("jeu/explosions/explosions12sur14et13frames.png"));

		ennemiTourne = new Texture(Gdx.files.internal("jeu/ennemitourne21sur21et8frames.png"));
		bouleBleuRouge = new Texture(Gdx.files.internal("jeu/boulebleurouge18large17haut3frames.png"));
		
		
		atlas = new TextureAtlas(Gdx.files.internal("textures.atlas"));
		XP = atlas.findRegion("xp");
	}
	

	/**
	 * Classe servant à configurer les textures
	 * @param chemin
	 * @param filter
	 * @return
	 */
	private static Texture configTexture(String chemin, boolean filter) {
		t = new Texture(Gdx.files.internal(chemin));
		
		if(filter)
			t.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		else
			t.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		return t;
	}
	

	/**
	  Classe servant a créer et configurer le sprite
	  @param texture : le chemin de la texture
	  @param largeur : largeur du sprite
	  @param hauteur : hauteur du sprite
	  @param posx : sa position en x
	  @param posy : sa position en y
	  @param filter : active les filtres ou pas
	 */
	private static Sprite configSprite(Texture texture, float largeur, float hauteur, float posx, float posy) {
		Sprite sprite = new Sprite(texture);
		sprite.setSize(largeur, hauteur);
		sprite.setPosition(posx, posy);
		return sprite;
	}

	private TexMan() {
		super();
	}
}
