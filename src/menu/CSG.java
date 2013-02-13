package menu;

import vaisseaux.armes.Armes;
import vaisseaux.bonus.Bonus;
import vaisseaux.bonus.XP;
import vaisseaux.ennemis.Ennemis;
import vaisseaux.ennemis.Progression;
import jeu.Profil;
import jeu.ProfilManager;
import affichage.ParallaxBackground;
import affichage.ParallaxLayer;
import affichage.TexMan;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class CSG extends Game implements ApplicationListener {

	//public static final String STOCKAGE_XP = "xp.lvl";
	// ---- champs globaux ---- Je ne trouve pas comment mettre final car Gdx n'est pas encore initialisé
	public static int DEMI_LARGEUR_ECRAN = 0;
	public static int DEMI_HAUTEUR_ECRAN;
	public static int LARGEUR_ECRAN;
	public static int HAUTEUR_ECRAN;
	public static float RATIO;
	public static int DIXIEME_LARGEUR;
	public static int DIXIEME_HAUTEUR;
	public static int CINQUIEME_ECRAN;
	public static int DEUX_CINQUIEME_ECRAN;
	public static int TROIS_CINQUIEME_ECRAN;
	public static int QUATRE_CINQUIEME_ECRAN;
	public static int HAUTEUR_ECRAN_PALLIER_1;
	public static int HAUTEUR_ECRAN_PALLIER_2;
	public static int HAUTEUR_ECRAN_PALLIER_3;
	public static final int CONTROLE_TOUCH_NON_RELATIVE = 0;
	public static final int CONTROLE_TOUCH_RELATIVE = 1;
	public static final int CONTROLE_MAX = 1;
	public static ProfilManager profilManager;
	public static Profil profil;
	private static ParallaxBackground rbg;
	
	@Override
	public void create() {
		DEMI_LARGEUR_ECRAN = Gdx.graphics.getWidth() /2;
		DEMI_HAUTEUR_ECRAN = Gdx.graphics.getHeight()/2;
		LARGEUR_ECRAN = Gdx.graphics.getWidth();
		HAUTEUR_ECRAN = Gdx.graphics.getHeight();
		RATIO = HAUTEUR_ECRAN/LARGEUR_ECRAN;
		DIXIEME_LARGEUR = LARGEUR_ECRAN / 10;
		DIXIEME_HAUTEUR = HAUTEUR_ECRAN / 10;
		CINQUIEME_ECRAN = DIXIEME_LARGEUR * 2;
		DEUX_CINQUIEME_ECRAN = CINQUIEME_ECRAN * 2;
		TROIS_CINQUIEME_ECRAN = CINQUIEME_ECRAN * 3;
		QUATRE_CINQUIEME_ECRAN = CINQUIEME_ECRAN * 4;
		HAUTEUR_ECRAN_PALLIER_1 = HAUTEUR_ECRAN - DIXIEME_HAUTEUR;
		HAUTEUR_ECRAN_PALLIER_2 = HAUTEUR_ECRAN - (DIXIEME_HAUTEUR * 2);
		HAUTEUR_ECRAN_PALLIER_3 = HAUTEUR_ECRAN - (DIXIEME_HAUTEUR * 3);
		profilManager = new ProfilManager();
		profil = profilManager.retrieveProfile();
		
//		Splashscreen splash = new Splashscreen(this);
//		setScreen(splash);
//		Menu menu = new Menu(this);
//		setScreen(menu);
		TexMan.loadGame();
		rbg = new ParallaxBackground(new ParallaxLayer[]{new ParallaxLayer(TexMan.atlas.findRegion("etoilesnew1"),new Vector2(),new Vector2(0, 0)),
				new ParallaxLayer(TexMan.atlas.findRegion("etoilesnew2"),new Vector2(0.1f,0.1f),new Vector2(0,0)),
	      }, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(),new Vector2(0,150));
		
		Menu menu = new Menu(this);
		setScreen(menu);
		
		
	}
	
	public static void renderBackground(SpriteBatch batch){
		rbg.render(batch);
	}
	
	public static ParallaxBackground getBackground(){
		return rbg;
	}
	
	public static void resetLists(){
		Armes.liste.clear();
		Armes.listeTirsDesEnnemis.clear();
		Ennemis.liste.clear();
        XP.liste.clear();
        Progression.reset();
        Bonus.liste.clear();
	}
}
