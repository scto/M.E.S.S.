package menu;

import jeu.Profil;
import jeu.ProfilManager;
import vaisseaux.armes.Armes;
import vaisseaux.bonus.Bonus;
import vaisseaux.ennemis.Ennemis;
import vaisseaux.ennemis.Progression;
import assets.AssetMan;
import assets.background.ParallaxBackground;
import assets.particules.Particules;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class CSG extends Game implements ApplicationListener {

	public static IActivityRequestHandler myRequestHandler;
	// ---- champs globaux ---- Je ne trouve pas comment mettre final car Gdx n'est pas encore initialise
	public static int DEMI_LARGEUR_ECRAN = 0, TIER_LARGEUR_ECRAN, DEMI_LARGEUR_ZONE_JEU, DEMI_HAUTEUR_ECRAN, LARGEUR_ECRAN, LARGEUR_ZONE_JEU, LARGEUR_BORD;
	public static int DEMI_CAMERA, LARGEUR_ZONE_MOINS_LARGEUR_BORD, LARGEUR_ZONE_MOINS_LARGEUR_BORD_MUL2, HAUTEUR_ECRAN, DIXIEME_LARGEUR, DIXIEME_HAUTEUR;
	public static int CINQUIEME_ECRAN, DEUX_CINQUIEME_ECRAN, TROIS_CINQUIEME_ECRAN, QUATRE_CINQUIEME_ECRAN;
	public static int CINQUIEME_ZONE, DEUX_CINQUIEME_ZONE, TROIS_CINQUIEME_ZONE, QUATRE_CINQUIEME_ZONE;
	// ********  P A L I E R S  P O U R   E N N E M I S  ***********
	public static int HAUTEUR_ECRAN_PALLIER_1, HAUTEUR_ECRAN_PALLIER_2, HAUTEUR_ECRAN_PALLIER_3 = 0, HAUTEUR_ECRAN_PALLIER_7;
	// ********  C O N T R O L E S  ********
	public static final int CONTROLE_TOUCH_NON_RELATIVE = 0, CONTROLE_DPAD = 1, CONTROLE_ACCELEROMETRE = 2, CONTROLE_TOUCH_RELATIVE = 3, CONTROLE_MAX = 3;
	// ********  A U T R E S  *********
	public static ProfilManager profilManager;
	public static Profil profil;
	public static ParallaxBackground rbg;
	public static BitmapFont menuFont, menuFontPetite;
	public static AssetMan assetMan;
	public static SpriteBatch batch;
	
	public CSG(IActivityRequestHandler handler) {
		myRequestHandler = handler;
	}

	public CSG() { // Constructeur desktop
	}

	@Override
	public void create() {
		batch = new SpriteBatch();
		assetMan = new AssetMan();
		// **************  V A R I A B L E S   C O N S T A N T E S  :) ********************
		if (Gdx.app.getVersion() != 0)		CSG.myRequestHandler.showAds(true);
		dimensions();
		// ***********************  P R O F I L  ****************************
		profilManager = new ProfilManager();
		profil = profilManager.retrieveProfile();
		menuFont = new BitmapFont();
		// ************************ P O L I C E S ****************************
		menuFont = new BitmapFont(Gdx.files.internal("default.fnt"), false);
		float x = LARGEUR_ECRAN / 250;
		float y = HAUTEUR_ECRAN / 500;
		if (x < 1)	x = 1.0f;
		if (y < 1)	y = 1.0f;
		menuFont.setScale(x, y);
		menuFont.setColor(.59f, .55f, 0f, 1);
		menuFontPetite = new BitmapFont();
		x = LARGEUR_ECRAN / 440;
		y = HAUTEUR_ECRAN / 480;
		if (x < 1)	x = 1.0f;
		if (y < 1)	y = 1.0f;
		menuFontPetite = new BitmapFont(Gdx.files.internal("petite.fnt"), false);
		menuFontPetite.setScale(x, y);
		menuFontPetite.setColor(.59f, .55f, 0f, 1);
		// ***** Une fois que toutes les variables globales sont chargees on lance le loading pour charger les assets
		Loading loading = new Loading(this);
		setScreen(loading);
	}

	private void dimensions() {
		DEMI_LARGEUR_ECRAN = Gdx.graphics.getWidth() / 2;
		DEMI_HAUTEUR_ECRAN = Gdx.graphics.getHeight() / 2;
		LARGEUR_ECRAN = Gdx.graphics.getWidth();
		HAUTEUR_ECRAN = Gdx.graphics.getHeight();
		LARGEUR_ZONE_JEU = (int) (LARGEUR_ECRAN * 1.5f);
		LARGEUR_BORD = LARGEUR_ECRAN / 3;
		TIER_LARGEUR_ECRAN = LARGEUR_ECRAN / 3;
		LARGEUR_ZONE_MOINS_LARGEUR_BORD = LARGEUR_ZONE_JEU - LARGEUR_BORD;
		LARGEUR_ZONE_MOINS_LARGEUR_BORD_MUL2 = LARGEUR_ZONE_JEU - (LARGEUR_BORD*2);
		DEMI_LARGEUR_ZONE_JEU = LARGEUR_ZONE_JEU / 2;
		DEMI_CAMERA = (CSG.LARGEUR_ZONE_JEU - CSG.LARGEUR_ECRAN) * 2;
		DIXIEME_LARGEUR = LARGEUR_ECRAN / 10;
		DIXIEME_HAUTEUR = HAUTEUR_ECRAN / 10;
		CINQUIEME_ECRAN = DIXIEME_LARGEUR * 2;
		DEUX_CINQUIEME_ECRAN = CINQUIEME_ECRAN * 2;
		TROIS_CINQUIEME_ECRAN = CINQUIEME_ECRAN * 3;
		QUATRE_CINQUIEME_ECRAN = CINQUIEME_ECRAN * 4;
		
		CINQUIEME_ZONE = LARGEUR_ZONE_JEU / 5;
		DEUX_CINQUIEME_ZONE = CINQUIEME_ZONE * 2;
		TROIS_CINQUIEME_ZONE = CINQUIEME_ZONE * 3;
		QUATRE_CINQUIEME_ZONE = CINQUIEME_ZONE * 4;
		
		HAUTEUR_ECRAN_PALLIER_1 = HAUTEUR_ECRAN - DIXIEME_HAUTEUR;
		HAUTEUR_ECRAN_PALLIER_2 = HAUTEUR_ECRAN - (DIXIEME_HAUTEUR * 2);
		HAUTEUR_ECRAN_PALLIER_3 = HAUTEUR_ECRAN - (DIXIEME_HAUTEUR * 3);
		HAUTEUR_ECRAN_PALLIER_7 = HAUTEUR_ECRAN - (DIXIEME_HAUTEUR * 7);
	}
	
	public static void renderBackground(SpriteBatch batch){
		rbg.render(batch);
	}

	public static void resetLists(){
		ParallaxBackground.resetEtoiles();
		Ennemis.clear();
		Armes.clear();
        Progression.reset();
        Bonus.resetTout();
        Particules.clear();
	}

	public static AssetMan getAssetMan() {
		return assetMan;
	}
}
