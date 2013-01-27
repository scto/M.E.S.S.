package menu;

import vaisseaux.armes.Armes;
import vaisseaux.bonus.Bonus;
import vaisseaux.bonus.XP;
import vaisseaux.ennemis.Ennemis;
import vaisseaux.ennemis.Progression;
import jeu.Profil;
import jeu.ProfilManager;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

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
	public static ProfilManager profilManager;
	public static Profil profil;
	
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
		profilManager = new ProfilManager();
		profil = profilManager.retrieveProfile();
		
//		Splashscreen splash = new Splashscreen(this);
//		setScreen(splash);
//		Menu menu = new Menu(this);
//		setScreen(menu);
		Menu menu = new Menu(this);
		setScreen(menu);
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
