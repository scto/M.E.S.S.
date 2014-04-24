package jeu;

import java.util.Random;

import shaders.Bloom;
import jeu.db.DataManager;
import menu.screens.Loading;
import assets.AssetMan;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import elements.generic.Invocable;
import elements.generic.enemies.Enemy;
import elements.generic.enemies.SpawnEnemyPosition;
import elements.generic.weapons.Weapons;
import elements.particular.bonuses.Bonus;
import elements.particular.particles.Particles;

public class CSG extends Game implements ApplicationListener {

	public static Adds myRequestHandler;
	// ---- champs globaux ---- Je ne trouve pas comment mettre final car Gdx n'est pas encore initialise
	public static int screenHalfWidth = 0, screenTierWidth, gameZoneHalfWidth, halfHeight, screenWidth, gameZoneWidth, borderWidth, gameZoneWidthDiv20, gameZoneWidthDiv100;
	public static int LARGEUR_ZONE_MOINS_LARGEUR_BORD, LARGEUR_ZONE_MOINS_LARGEUR_BORD_MUL2, SCREEN_HEIGHT, DIXIEME_LARGEUR, HEIGHT_DIV10, CENTIEME_HAUTEUR, HEIGHT_DIV50, HEIGHT_DIV20, HEIGHT_PLUS_4, HEIGHT_DIV8;
	public static int CINQUIEME_ECRAN, DEUX_CINQUIEME_ECRAN, TROIS_CINQUIEME_ECRAN, QUATRE_CINQUIEME_ECRAN, QUATR_HAUTEUR;
	public static int CINQUIEME_ZONE, DEUX_CINQUIEME_ZONE, TROIS_CINQUIEME_ZONE, QUATRE_CINQUIEME_ZONE;
	public static float RATIO;
	public static int HAUTEUR_ECRAN_PALLIER_1, HAUTEUR_ECRAN_PALLIER_2, HAUTEUR_ECRAN_PALLIER_3 = 0, HAUTEUR_ECRAN_PALLIER_7;
	public static final int CONTROLE_TOUCH_NON_RELATIVE = 0, CONTROLE_DPAD = 1, CONTROLE_ACCELEROMETRE = 2, CONTROLE_TOUCH_RELATIVE = 3, CONTROLE_MAX = 3;
	// ********  A U T R E S  *********
	public static ProfilManager profilManager;
	public static Profil profile;
	public static BitmapFont menuFont, menuFontPetite, scoreFont, outlineScoreFont;
	public static AssetMan assetMan;
	public static SpriteBatch batch;
	public static TalkToTheWorld google;
	public static final Random R = new Random();
	public static final float mulLvl1 = 1, mulLvl2 = 1.15f, mulLvl3 = 2.2f, mulLvl4 = 4f, mulSCORE = 1.1f;
	public static DataManager dbManager;
	public static final int dbVersion = 2;
	public static boolean updateNeeded = false;
	public static float originalScoreFontScale;
	
	public CSG(Adds handler, TalkToTheWorld google) {
		updateNeeded = false;
		myRequestHandler = handler;
		CSG.google = google;
	}

	public CSG(TalkToTheWorld google, DataManager dbManager) { // Constructeur desktop
		CSG.google = google;
		CSG.dbManager = dbManager;
//		dbManager.setupDatabase();
//		CSG.dbManager.openOrCreateDatabase();
//		if (dbManager.getInt("select id from version", dbVersion) == dbVersion)
			updateNeeded = false;
//		else
//			updateNeeded = true;
		
		System.out.println("Update needed : " + updateNeeded);
	}

	@Override
	public void create() {
		log("Create");
		batch = new SpriteBatch(5460);
		assetMan = new AssetMan();
		// **************  V A R I A B L E S   C O N S T A N T E S  :) ********************
		if (Gdx.app.getVersion() != 0)
			CSG.myRequestHandler.showAds(true);
		dimensions();
		// ***********************  P R O F I L  ****************************
		profilManager = new ProfilManager();
		profile = profilManager.retrieveProfile();
		initFonts();
		// ***** Une fois que toutes les variables globales sont chargees on lance le loading pour charger les assets
		final Loading loading = new Loading(this);
		setScreen(loading);
	}

	public static void log(String s ) {
		Gdx.app.log("ESGLOG", s);
	}

	public static void initFonts() {
		menuFont = new BitmapFont();//Gdx.files.internal("default.fnt"), false);
		float dimension = CSG.SCREEN_HEIGHT + CSG.screenWidth;
		dimension = dimension / 700;
		if (dimension < 1f)
			dimension = 1f;
		menuFont.setScale(dimension);
		menuFont.setColor(.32f, .52f, 0.99f, 1);
		menuFont.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		menuFontPetite = new BitmapFont();
		dimension /= 2.2f;
		if (dimension < 1f)
			dimension = 1f;
		menuFontPetite = new BitmapFont();//Gdx.files.internal("default.fnt"), false);
		menuFontPetite.setScale(dimension);
		menuFontPetite.setColor(.32f, .52f, 0.99f, 1);
		
		originalScoreFontScale = dimension * 1.2f;
		scoreFont = new BitmapFont();//Gdx.files.internal("default.fnt"), false);
		scoreFont.setScale(originalScoreFontScale);
		scoreFont.setColor(0, 0, 1, 1);
		scoreFont.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		outlineScoreFont = new BitmapFont();//Gdx.files.internal("default.fnt"), false);
		outlineScoreFont.setScale(originalScoreFontScale * 1.2f);
		outlineScoreFont.setColor(0, 0, 1, 0.65f);
		outlineScoreFont.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
	}

	public static void dimensions() {
		screenHalfWidth = Gdx.graphics.getWidth() / 2;
		halfHeight = Gdx.graphics.getHeight() / 2;
		screenWidth = Gdx.graphics.getWidth();
		SCREEN_HEIGHT = Gdx.graphics.getHeight();
		RATIO = screenWidth / SCREEN_HEIGHT;
		gameZoneWidth = (int) (screenWidth * 1.0f);
		gameZoneWidthDiv20 = gameZoneWidth / 20;
		gameZoneWidthDiv100 = gameZoneWidth / 100;
		borderWidth = screenWidth / 3;
		screenTierWidth = screenWidth / 3;
		LARGEUR_ZONE_MOINS_LARGEUR_BORD = gameZoneWidth - borderWidth;
		LARGEUR_ZONE_MOINS_LARGEUR_BORD_MUL2 = gameZoneWidth - (borderWidth*2);
		gameZoneHalfWidth = gameZoneWidth / 2;
//		DEMI_CAMERA = (gameZoneWidth - screenWidth) * 2;
		DIXIEME_LARGEUR = screenWidth / 10;
		HEIGHT_DIV10 = SCREEN_HEIGHT / 10;
		CENTIEME_HAUTEUR = SCREEN_HEIGHT / 100;
		HEIGHT_DIV50 = SCREEN_HEIGHT / 50;
		HEIGHT_DIV8 = SCREEN_HEIGHT / 8;
		HEIGHT_DIV20 = SCREEN_HEIGHT / 20;
		CINQUIEME_ECRAN = DIXIEME_LARGEUR * 2;
		DEUX_CINQUIEME_ECRAN = CINQUIEME_ECRAN * 2;
		TROIS_CINQUIEME_ECRAN = CINQUIEME_ECRAN * 3;
		QUATRE_CINQUIEME_ECRAN = CINQUIEME_ECRAN * 4;
		QUATR_HAUTEUR = SCREEN_HEIGHT / 4;
		
		CINQUIEME_ZONE = gameZoneWidth / 5;
		DEUX_CINQUIEME_ZONE = CINQUIEME_ZONE * 2;
		TROIS_CINQUIEME_ZONE = CINQUIEME_ZONE * 3;
		QUATRE_CINQUIEME_ZONE = CINQUIEME_ZONE * 4;
		HEIGHT_PLUS_4 = SCREEN_HEIGHT + 4;
		HAUTEUR_ECRAN_PALLIER_1 = SCREEN_HEIGHT - HEIGHT_DIV10;
		HAUTEUR_ECRAN_PALLIER_2 = SCREEN_HEIGHT - (HEIGHT_DIV10 * 2);
		HAUTEUR_ECRAN_PALLIER_3 = SCREEN_HEIGHT - (HEIGHT_DIV10 * 3);
		HAUTEUR_ECRAN_PALLIER_7 = SCREEN_HEIGHT - (HEIGHT_DIV10 * 7);
	}
	
	public static void reset(){
		Enemy.clear();
		Weapons.clear();
		// Progression.reset(); 
        Bonus.resetAll();
        Particles.clear();
        EndlessMode.reset();
	}

	public static AssetMan getAssetMan() {
		return assetMan;
	}

	public static Bloom bloom;
	public static boolean alternateGraphics = false;
	
	public static void initBloom() {
		if (CSG.profile.bloom) {
			try {
				bloom = new Bloom();
				bloom.setBloomIntesity(CSG.profile.intensiteBloom);
			} catch (Exception e) {
				e.printStackTrace();
				CSG.profile.bloom = false;
			}
		} 
	}

	public static void begin(float delta) {
		EndlessMode.delta = delta;
		EndlessMode.majDeltas();
		EndlessMode.now += delta;
		if (CSG.profile.bloom)
			bloom.capture();
		else
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}

	public static void end() {
		batch.end();
		if (CSG.profile.bloom)
			bloom.render();
	}
	
	public static float[] convert(Array<Float> tmp) {
		float[] array2 = new float[tmp.size];
	    int i=0;
	    for (Float f : tmp) {
	        array2[i] = f.floatValue();
	        i++;
	    }
		return array2;
	}
	
	public static float[] getHalf(float[] array) {
		final Array<Float> tmp = new Array<Float>();
		tmp.add(0f);
		for (int i = 1; i < array.length; i++)
			tmp.add( (array[i]-array[i-1])/2);
		return CSG.convert(tmp);
	}

	public static Invocable[] convert(Array<Invocable> enemies) {
		Invocable[] array2 = new Invocable[enemies.size];
	    int i=0;
	    for (Invocable f : enemies) {
	        array2[i] = f;
	        i++;
	    }
		return array2;
	}

	public static Vector2[] convert(Array<Vector2> positions) {
		Vector2[] array2 = new Vector2[positions.size];
	    int i=0;
	    for (Vector2 f : positions) {
	        array2[i] = f;
	        i++;
	    }
		return array2;
	}

	public static SpawnEnemyPosition[] convert(Array<SpawnEnemyPosition> spawns) {
		SpawnEnemyPosition[] array2 = new SpawnEnemyPosition[spawns.size];
	    int i=0;
	    for (SpawnEnemyPosition f : spawns) {
	        array2[i] = f;
	        i++;
	    }
		return array2;
	}

	public static float[] getDouble(float[] array) {
		final Array<Float> tmp = new Array<Float>();
		tmp.add(0f);
		for (int i = 1; i < array.length; i++)
			tmp.add( (array[i]-array[i-1])*2);
		return CSG.convert(tmp);
	}
}
