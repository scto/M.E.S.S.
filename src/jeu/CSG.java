package jeu;

import java.util.Random;

import jeu.mode.EndlessMode;
import menu.screens.Loading;
import shaders.Bloom;
import assets.AssetMan;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import elements.generic.components.enemies.Merlin;
import elements.generic.enemies.Enemy;
import elements.generic.enemies.SpawnEnemyPosition;
import elements.generic.weapons.Weapon;
import elements.particular.bonuses.Bonus;
import elements.particular.particles.Particles;

public class CSG extends Game implements ApplicationListener {

	// ---- champs globaux ---- Je ne trouve pas comment mettre final car Gdx n'est pas encore initialise
	public static int screenHalfWidth = 0, screenTierWidth, gameZoneHalfWidth, halfHeight, screenWidth, gameZoneWidth, borderWidth, gameZoneWidthDiv20, gameZoneWidthDiv100, screenWidth2Thirds;
	public static int WIDTH_ZONE_MOINS_WIDTH_BORD, WIDTH_ZONE_MOINS_WIDTH_BORD_MUL2, SCREEN_HEIGHT, DIXIEME_WIDTH, HEIGHT_DIV10, CENTIEME_HEIGHT, HEIGHT_DIV50, HEIGHT_DIV20, HEIGHT_PLUS_4, HEIGHT_DIV8;
	public static int CINQUIEME_ECRAN, DEUX_CINQUIEME_ECRAN, TROIS_CINQUIEME_ECRAN, QUATRE_CINQUIEME_ECRAN, QUATR_HEIGHT;
	public static int CINQUIEME_ZONE, DEUX_CINQUIEME_ZONE, TROIS_CINQUIEME_ZONE, QUATRE_CINQUIEME_ZONE;
	public static float RATIO;
	public static int HEIGHT_9_10, HEIGHT_8_10, HEIGHT_7_10, HEIGHT_ECRAN_PALLIER_3 = 0, HEIGHT_ECRAN_PALLIER_7;
	public static final int CONTROLE_TOUCH_NON_RELATIVE = 0, CONTROLE_DPAD = 1, CONTROLE_ACCELEROMETRE = 2, CONTROLE_TOUCH_RELATIVE = 3, CONTROLE_MAX = 3;
	// ********  A U T R E S  *********
	public static ProfilManager profilManager;
	public static Profil profile;
	public static BitmapFont menuFont, menuFontSmall, menuFontBlack, scoreFont;//, outlineScoreFont;
	public static AssetMan assetMan;
	public static SpriteBatch batch;
	public static TalkToTheWorld talkToTheWorld;
	public static final Random R = new Random();
	public static final float mulLvl1 = 1, mulLvl2 = 1.15f, mulLvl3 = 2.2f, mulLvl4 = 4f, mulSCORE = 1.1f;
	public static final int dbVersion = 2;
	public static final int NO_CHEAT = 0, BEGIN_70K = 1;
	public static float originalScoreFontScale;
	public static final Vector2 vecteurPosition = new Vector2(), tmpPos = new Vector2(), tmpDir = new Vector2(), tmp2 = new Vector2();
	public static Vector2 vecteurCible = new Vector2();
	public static int tmpInt;
//	public static RayHandler rayHandler;
//	public static World world;
	
	public CSG(TalkToTheWorld google) {
		CSG.talkToTheWorld = google;
	}

	@Override
	public void create() {
//		world = new World(Vector2.Zero, true);
//		rayHandler = new RayHandler(world);
//		CSG.rayHandler.setAmbientLight(0,0,0,0.8f);
		
		log("Create");
		batch = new SpriteBatch(5460);
		assetMan = new AssetMan();
		// **************  V A R I A B L E S   C O N S T A N T E S  :) ********************
		if (Gdx.app.getVersion() != 0)
			CSG.talkToTheWorld.showAds(true);
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
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("PolenticalNeonRegular.ttf"));
		FreeTypeFontParameter param = new FreeTypeFontParameter();
		
		float dimension = CSG.SCREEN_HEIGHT + CSG.screenWidth;
		dimension = dimension / 900;
		if (dimension < 1f)
			dimension = 1f;
		
		menuFontBlack = setFont((int) (25 * dimension),			dimension, generator, param, AssetMan.BLACK);
		originalScoreFontScale = dimension * 0.75f;
		scoreFont = setFont((int) (13 * dimension), 			dimension, generator, param, AssetMan.convertARGB(1, 0, 0, 1));
		scoreFont.setScale(0.5f);
		
		menuFontSmall = setFont((int) (12 * dimension), 		dimension, generator, param, AssetMan.convertARGB(1, .32f, .52f, 0.99f));
		menuFont = setFont((int) (23 * dimension), 		dimension, generator, param, AssetMan.convertARGB(1, .32f, .52f, 0.99f));
		
		generator.dispose(); // don't forget to dispose to avoid memory leaks!
	}

	private static BitmapFont setFont(int size, float dimension, FreeTypeFontGenerator generator, FreeTypeFontParameter param, float color) {
		BitmapFont font = new BitmapFont();
		param.size = size;
		font = generator.generateFont(param);
		font.setColor(color);
		font.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		return font;
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
		WIDTH_ZONE_MOINS_WIDTH_BORD = gameZoneWidth - borderWidth;
		WIDTH_ZONE_MOINS_WIDTH_BORD_MUL2 = gameZoneWidth - (borderWidth*2);
		gameZoneHalfWidth = gameZoneWidth / 2;
//		DEMI_CAMERA = (gameZoneWidth - screenWidth) * 2;
		DIXIEME_WIDTH = screenWidth / 10;
		HEIGHT_DIV10 = SCREEN_HEIGHT / 10;
		CENTIEME_HEIGHT = SCREEN_HEIGHT / 100;
		HEIGHT_DIV50 = SCREEN_HEIGHT / 50;
		HEIGHT_DIV8 = SCREEN_HEIGHT / 8;
		HEIGHT_DIV20 = SCREEN_HEIGHT / 20;
		CINQUIEME_ECRAN = DIXIEME_WIDTH * 2;
		DEUX_CINQUIEME_ECRAN = CINQUIEME_ECRAN * 2;
		TROIS_CINQUIEME_ECRAN = CINQUIEME_ECRAN * 3;
		QUATRE_CINQUIEME_ECRAN = CINQUIEME_ECRAN * 4;
		QUATR_HEIGHT = SCREEN_HEIGHT / 4;
		
		CINQUIEME_ZONE = gameZoneWidth / 5;
		DEUX_CINQUIEME_ZONE = CINQUIEME_ZONE * 2;
		TROIS_CINQUIEME_ZONE = CINQUIEME_ZONE * 3;
		QUATRE_CINQUIEME_ZONE = CINQUIEME_ZONE * 4;
		HEIGHT_PLUS_4 = SCREEN_HEIGHT + 4;
		HEIGHT_9_10 = SCREEN_HEIGHT - HEIGHT_DIV10;
		HEIGHT_8_10 = SCREEN_HEIGHT - (HEIGHT_DIV10 * 2);
		HEIGHT_7_10 = SCREEN_HEIGHT - (HEIGHT_DIV10 * 3);
		HEIGHT_ECRAN_PALLIER_3 = SCREEN_HEIGHT - (HEIGHT_DIV10 * 3);
		HEIGHT_ECRAN_PALLIER_7 = SCREEN_HEIGHT - (HEIGHT_DIV10 * 7);
		
		screenWidth2Thirds = (screenWidth / 2) * 3;
	}
	
	public static void reset(){
		Enemy.clear();
		Weapon.clear();
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
		EndlessMode.majDeltas(true);
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
	
	public static float[] getDifferences(float[] array) {
		final Array<Float> tmp = new Array<Float>();
		tmp.add(0f);
		for (int i = 1; i < array.length; i++)
			tmp.add( (array[i]-array[i-1])/2);
		return CSG.convert(tmp);
	}

	public static Merlin[] convert(Array<Merlin> enemies) {
		Merlin[] array2 = new Merlin[enemies.size];
	    int i=0;
	    for (Merlin f : enemies) {
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
			tmp.add(array[i]*2);
		return CSG.convert(tmp);
	}
}
