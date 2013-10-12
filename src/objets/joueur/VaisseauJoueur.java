package objets.joueur;

import jeu.EndlessMode;
import jeu.Stats;
import menu.CSG;
import objets.Objet;
import objets.armes.joueur.ArmeAdd;
import objets.armes.joueur.ManagerArme;
import objets.bonus.Bonus;
import assets.AssetMan;
import assets.animation.AnimationVaisseau;
import assets.particules.Particules;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Classe g�rant les vaisseaux du types de base
 * @author Julien
 *
 */
public class VaisseauJoueur extends Objet {

	// ** ** dimensions du vaisseau et autre
	public static final int LARGEUR = (int) (CSG.LARGEUR_ECRAN / 9.5f), DEMI_LARGEUR = LARGEUR/2, LARGEUR_ADD = LARGEUR/3, DEMI_LARGEUR_ADD = LARGEUR_ADD/2;
	public static final int HAUTEUR = CSG.HAUTEUR_ECRAN / 12, DEMI_HAUTEUR = HAUTEUR / 2, HAUTEUR_MAX_ADD = HAUTEUR + DEMI_HAUTEUR, DEMI_HAUTEUR_ADD = DEMI_HAUTEUR / 2;
	public static final int DECALAGE_ADD = LARGEUR + DEMI_LARGEUR - LARGEUR_ADD;
	public static final int DECALAGE_TIR_ADD_X_GAUCHE = -DEMI_LARGEUR - DEMI_LARGEUR_ADD + ArmeAdd.DEMI_LARGEUR;
	public static final int DECALAGE_TIR_ADD_X_DROITE = DECALAGE_ADD - DEMI_LARGEUR_ADD + ArmeAdd.DEMI_LARGEUR;
	// ** ** limites dans l'espace
	private static final int LIMITE_X_GAUCHE = 0 - DEMI_LARGEUR, LIMITE_X_DROITE = CSG.LARGEUR_ZONE_JEU - DEMI_LARGEUR, LIMITE_Y_GAUCHE = 0 - DEMI_HAUTEUR, LIMITE_Y_DROITE = CSG.HAUTEUR_ECRAN - DEMI_HAUTEUR;
	private static final float DEGRE_PRECISION_DEPLACEMENT = (CSG.LARGEUR_ECRAN + CSG.HAUTEUR_ECRAN) / 600;
	// ** ** parametres pouvant etre modifi�s par des bonus
	@SuppressWarnings("unused")
	private static float vitesseMax = 0;
	private static ManagerArme arme = CSG.profil.getArmeSelectionnee();
//	private static TypesArmes[] typeArmePossible = TypesArmes.LISTE_ARME_JOUEUR;
	public static float centreX = 0, centreY = 0, prochainTir = 0, dernierTirAdd = 0;
	public static Vector2 position = new Vector2();
	public static float prevX, prevY, destX, destY;
	private static float vitesseFoisdelta = 0, tmpCalculDeplacement = 0, originalAccelX = 0, originalAccelY = 0;
	// ** ** A D D S
	public static boolean addGauche = false, addDroite = false, addGauche2 = false, addDroite2 = false;
	public static float addX, addY, centreAddGauche1X, centreAdds1Y, centreAddDroite1X, centreAddGauche2X, centreAdds2Y, centreAddDroite2X;
	public static boolean alterner = true;
	public static float angleAdd = -90, angleAddDroite = -90;
	private Vector2 oldPosition = new Vector2();
	public static boolean bouclier = false;
	private float alphaBouclier = .5f;
	private boolean sensAlpha = true;
	
	/**
	 * initialise le vaisseau avec les parametres par d�faut
	 */
	public VaisseauJoueur() {
		super();
		initialiser();
	}

	/**
	 * positionne et initialise tout
	 */
	public void initialiser() {
		bouclier = false;
		reInit();
	    addDroite = false;
	    addGauche = false;
	    addDroite2 = false;
	    addGauche2 = false;
	    angleAdd = 80;
	}

	/**
	 * Positionne mais ne reset ni adds ni shield
	 */
	public void reInit() {
		prochainTir = 0;
		position = new Vector2(CSG.DEMI_LARGEUR_ZONE_JEU - DEMI_LARGEUR, HAUTEUR/2);
		vitesseMax = (Stats.V_JOUEUR);
		prevX = position.x;
		prevY = position.y;
		centreX = position.x + DEMI_LARGEUR;
		centreY = position.y + DEMI_HAUTEUR;
		if (CSG.profil.typeControle == CSG.CONTROLE_ACCELEROMETRE) {
			originalAccelX = Gdx.input.getAccelerometerX();
			originalAccelY = Gdx.input.getAccelerometerY();
		}
	}
	
	public void draw(SpriteBatch batch) {
		if (alphaBouclier > .95f) sensAlpha = false;
		if (alphaBouclier < .55f) sensAlpha = true;
		
		if (sensAlpha) alphaBouclier += EndlessMode.delta;
		else alphaBouclier -= EndlessMode.delta;
			
		Particules.ajoutFlammes(this);
		batch.draw(AnimationVaisseau.getTexture(), position.x, position.y, LARGEUR, HAUTEUR);
		if (bouclier) {
			batch.setColor(.9f, .9f, 1, alphaBouclier);
			batch.draw(AssetMan.bouclier, position.x - 2, position.y - 2, LARGEUR + 4, HAUTEUR + 4);
			batch.setColor(Color.WHITE);
		}
		// ** ** A D D S
		if (addGauche) 	batch.draw(assets.AssetMan.addvaisseau, addX - DEMI_LARGEUR, addY - DEMI_HAUTEUR, DEMI_LARGEUR_ADD, DEMI_HAUTEUR/2, LARGEUR_ADD, DEMI_HAUTEUR, 2f, 0.5f, angleAdd, false);
		if (addDroite) 	batch.draw(assets.AssetMan.addvaisseau, addX + DECALAGE_ADD, addY - DEMI_HAUTEUR, DEMI_LARGEUR_ADD, DEMI_HAUTEUR/2, LARGEUR_ADD, DEMI_HAUTEUR, 2f, 0.5f, angleAddDroite, false);
		if (addGauche2) batch.draw(assets.AssetMan.addvaisseau, addX - LARGEUR, addY - HAUTEUR, DEMI_LARGEUR_ADD, DEMI_HAUTEUR/2, LARGEUR_ADD, DEMI_HAUTEUR, 2f, 0.5f, angleAdd, false);
		if (addDroite2) batch.draw(assets.AssetMan.addvaisseau, addX + DECALAGE_ADD + DEMI_LARGEUR, addY - HAUTEUR, DEMI_LARGEUR_ADD, DEMI_HAUTEUR/2, LARGEUR_ADD, DEMI_HAUTEUR, 2f, 0.5f, angleAddDroite, false);
	}
	
	private float clicX = 0, clicY = 0, camXmoinsDemiEcran = EndlessMode.cam.position.x - CSG.DEMI_LARGEUR_ECRAN;
	/**
	 * Fait aller le vaisseau � l'endroit cliqu�.
	 * Si il peut se teleporter il y va directement -- Sinon il se d�place suivant sa vitesse max
	 */
	public void mouvements() {		
		centreX = position.x + DEMI_LARGEUR;
		centreY = position.y + DEMI_HAUTEUR;
		switch (CSG.profil.typeControle) {
		case CSG.CONTROLE_TOUCH_NON_RELATIVE: // Pour la camera : position.x = DEMI_ECRAn c'est le 0, position.x = ecran c'est le 1

			clicX = (Gdx.input.getX() - DEMI_LARGEUR);			// Le clic x va de (0 à LARGEUR_ECRAN) - DEMI_LARGEUR
			clicY = (CSG.HAUTEUR_ECRAN - Gdx.input.getY() + DEMI_HAUTEUR);
			destX =  clicX - (position.x - (camXmoinsDemiEcran) );
			destX *= 10000;
			destY =  clicY - position.y;
			destY *= 10000;
			// ****** NORMALISATION DE LA VITESSE SUIVANT LA LIMITE
			tmpCalculDeplacement = ((destX * destX) + (destY * destY)) * EndlessMode.delta * EndlessMode.delta;
			tmpCalculDeplacement = (float) Math.sqrt(tmpCalculDeplacement);
			vitesseFoisdelta = vitesseMax * EndlessMode.delta;
			// Si on va trop vite
			if (tmpCalculDeplacement > vitesseFoisdelta) {
				destX = destX * (vitesseFoisdelta / tmpCalculDeplacement);
				destY = destY * (vitesseFoisdelta / tmpCalculDeplacement);
			} 
			// CALCUL AFFICHAGE
			if (destX < -.9f) {
				AnimationVaisseau.versLaGauche();
				if (EndlessMode.cam.position.x > CSG.DEMI_LARGEUR_ECRAN) 	mvtCamSuivantDeplacement();
			} else if (destX > .9f) {
				AnimationVaisseau.versLaDroite();
				if (EndlessMode.cam.position.x < CSG.DEMI_CAMERA) 			mvtCamSuivantDeplacement();
			}
			else 					AnimationVaisseau.droit();
			
			if (position.y < clicY && (position.y + destY * EndlessMode.delta) > clicY || position.y > clicY && (position.y + destY * EndlessMode.delta) < clicY)		position.y = clicY;
			else 				position.y += destY * EndlessMode.delta;
			routineAdds();
			// On est à gauche		&		on est à droite // Si on était à gauche et qu'après on se retrouve à droite ou inversement on met directement la position à l'endroit ou on a cliqué
			if (position.x - camXmoinsDemiEcran < clicX & ( (position.x - camXmoinsDemiEcran) + destX * EndlessMode.delta) > clicX 
					|| position.x - camXmoinsDemiEcran > clicX & ((position.x - camXmoinsDemiEcran) + destX * EndlessMode.delta) < clicX){ 
				position.x = clicX + camXmoinsDemiEcran;
			} else {
				position.x += destX * EndlessMode.delta;
			}
			break;
		case CSG.CONTROLE_DPAD:
			destX = Gdx.input.getX() - prevX;
			destY = -(Gdx.input.getY() - prevY);
			destX *= 4;
			destY *= 4;
			mvtLimiteVitesse(destX, destY);
			break;
		case CSG.CONTROLE_TOUCH_RELATIVE: // recup d'autres float
			destX = Gdx.input.getX() - prevX;
			destY = -(Gdx.input.getY() - prevY);
			
			destX /= (EndlessMode.delta );
			destY /= (EndlessMode.delta );
			
			prevX = originalAccelX;
			prevY = originalAccelY;
			
			originalAccelX = Gdx.input.getX();
			originalAccelY = Gdx.input.getY();
			
			if (Gdx.input.justTouched()) {
				prevX = destX = originalAccelX;
				prevY = destY = originalAccelY;
			} 
			alterner = !alterner;
			mvtLimiteVitesse(destX, destY);
			break;
		}
		limitesEtCentre();
	}

	private void mvtCamSuivantDeplacement() {
		EndlessMode.cam.position.x += EndlessMode.delta * destX * 0.18f;
		camXmoinsDemiEcran = EndlessMode.cam.position.x - CSG.DEMI_LARGEUR_ECRAN;
		destX -= EndlessMode.delta * destX * 0.125f; // On doit d'office decrementer de ce que la camera s'est déplacée pour ne pas additionner les deux vitesses !
	}


	/**
	 * Le vaisseau se d�place vers les coordonn�es pass�es avec un cap � sa vitesse max
	 * @param x
	 * @param y
	 */
	public void mvtLimiteVitesse(float x, float y) {
		// haut gauche : +x -y

		oldPosition.x = position.x;
		oldPosition.y = position.y;			// position avant

		tmpCalculDeplacement = ((x * x) + (y * y)) * EndlessMode.delta * EndlessMode.delta;
		if(tmpCalculDeplacement < DEGRE_PRECISION_DEPLACEMENT){
			AnimationVaisseau.droit();
			return;
		}
		tmpCalculDeplacement = (float) Math.sqrt(tmpCalculDeplacement);

		vitesseFoisdelta = vitesseMax * EndlessMode.delta;
		// Si on va trop vite
		if (tmpCalculDeplacement > vitesseFoisdelta) {
			x = x * (vitesseFoisdelta / tmpCalculDeplacement);
			y = y * (vitesseFoisdelta / tmpCalculDeplacement);
		} 
		
		if (x < 0) {
			AnimationVaisseau.versLaGauche();
			if (EndlessMode.cam.position.x > CSG.DEMI_LARGEUR_ECRAN) {
				EndlessMode.cam.position.x += EndlessMode.delta * x * 0.35f;
				camXmoinsDemiEcran = EndlessMode.cam.position.x - CSG.DEMI_LARGEUR_ECRAN;
				x -= EndlessMode.delta * x * 0.35f; // On doit d'office decrementer de ce que la camera s'est déplacée pour ne pas additionner les deux vitesses !
			}
		}
		if (x > 0) {
			AnimationVaisseau.versLaDroite();
			if (EndlessMode.cam.position.x < CSG.DEMI_CAMERA) {
				EndlessMode.cam.position.x += EndlessMode.delta * x * 0.35f;
				camXmoinsDemiEcran = EndlessMode.cam.position.x - CSG.DEMI_LARGEUR_ECRAN;
				x -= EndlessMode.delta * x * 0.35f; // On doit d'office decrementer de ce que la camera s'est déplacée pour ne pas additionner les deux vitesses !
			}
		}
		
		position.x += (x * EndlessMode.delta);
		position.y += (y * EndlessMode.delta);	// Maj position

		routineAdds();
	}

	
	public void routineAdds() {
		addX = position.x;
		if (addY > position.y + HAUTEUR_MAX_ADD) {
			addY = position.y + HAUTEUR_MAX_ADD;
			angleAdd = -80;
			angleAddDroite = -100; 
		} else if (addY < position.y) {
			addY = position.y;
			angleAdd = 80;
			angleAddDroite = 100;
		} else {		// L'angle a une amplitude de 160° qui varie à gauche entre -80 et 80 et à droite entre -100 et 100 car on tourne dans l'autre sens 
			angleAdd = addY - position.y;
			angleAdd /= HAUTEUR_MAX_ADD;
			angleAdd *= 160;
			angleAddDroite = -angleAdd - 100; // Lui tourne dans l'autre sens (d'où le -) et doit décaler de 110 et pas 80 (C'est logique avec le petit dessin devant soi :) ). Ca fait 5 lignes, une multiplication, une division et une soustraction en moins par rapport à l'original
			angleAdd -= 80;
			angleAdd = -angleAdd;	// Car il fait son flip autour de 0
			angleAddDroite = -angleAddDroite; // Bon il doit y avoir moyen de se passer de ses inversions !
		}
		centreAdds2Y = (addY - HAUTEUR) + DEMI_HAUTEUR_ADD;
		centreAddDroite2X = (addX + DECALAGE_ADD + DEMI_LARGEUR) + DEMI_LARGEUR_ADD;
		
		centreAdds2Y = (addY - HAUTEUR) + DEMI_HAUTEUR_ADD;
		centreAddGauche2X = (addX - LARGEUR) + DEMI_LARGEUR_ADD;
		
		centreAddDroite1X = (addX + DECALAGE_ADD) + DEMI_LARGEUR_ADD;
		centreAdds1Y = (addY - DEMI_HAUTEUR) + DEMI_HAUTEUR_ADD;
		
		centreAddGauche1X = (addX - DEMI_LARGEUR) + DEMI_LARGEUR_ADD;
		centreAdds1Y = (addY - DEMI_HAUTEUR) + DEMI_HAUTEUR_ADD;
	}

	/**
	 * Le vaisseau va aux coordonn�es pass�es
	 * @param x
	 * @param y
	 */
	@SuppressWarnings("unused")
	private void mvtTeleport(int x, int y) {
		position.x = x;
		position.y = y;
	}
	
	/**
	 * oblige le vaisseau a rester dans les limites de l'�cran
	 */
	private void limitesEtCentre() {
		oldPosition.y = position.y;
		centreX = position.x + DEMI_LARGEUR;
		centreY = position.y + DEMI_HAUTEUR;
		
		if(position.x < LIMITE_X_GAUCHE) position.x = LIMITE_X_GAUCHE;
		if(position.x > LIMITE_X_DROITE) position.x = LIMITE_X_DROITE;
		if(position.y < LIMITE_Y_GAUCHE) position.y = LIMITE_Y_GAUCHE;
		if(position.y > LIMITE_Y_DROITE) position.y = LIMITE_Y_DROITE;
	}

	/**
	 * verifie si le vaisseau peut tirer ou pas. Tir au cas ou.
	 * Vu qu'on appele tir une frame sur deux adapter la cadence en cons�quence
	 * @param listeTir
	 */
	public void tir(){
		prochainTir = arme.init(prochainTir);
		// ** ** A D D S
		if (EndlessMode.maintenant > dernierTirAdd + ArmeAdd.CADENCETIR) {
			if (addGauche) {
				ArmeAdd b = ArmeAdd.pool.obtain();
				b.init(addX + DECALAGE_TIR_ADD_X_GAUCHE , addY - DEMI_HAUTEUR, angleAdd);
				if (CSG.profil.cadenceAdd > 3) {
					ArmeAdd c = ArmeAdd.pool.obtain();
					c.init(addX + DECALAGE_TIR_ADD_X_GAUCHE , addY - DEMI_HAUTEUR, angleAdd+10);
				}
			}
			if (addDroite){
				ArmeAdd b = ArmeAdd.pool.obtain();
				b.init(addX + DECALAGE_TIR_ADD_X_DROITE , addY - DEMI_HAUTEUR, angleAddDroite);
				if (CSG.profil.cadenceAdd > 3) {
					ArmeAdd c = ArmeAdd.pool.obtain();
					c.init(addX + DECALAGE_TIR_ADD_X_DROITE , addY - DEMI_HAUTEUR, angleAddDroite-10);
				}
			}
			if (addGauche2){
				ArmeAdd b = ArmeAdd.pool.obtain();
				b.init(addX + DECALAGE_TIR_ADD_X_GAUCHE - DEMI_LARGEUR , addY - HAUTEUR, angleAdd);
				if (CSG.profil.cadenceAdd > 6) {
					ArmeAdd c = ArmeAdd.pool.obtain();
					c.init(addX + DECALAGE_TIR_ADD_X_GAUCHE - DEMI_LARGEUR , addY - HAUTEUR, angleAdd+10);
				}
			}
			if (addDroite2){
				ArmeAdd b = ArmeAdd.pool.obtain();
				b.init(addX + DECALAGE_TIR_ADD_X_DROITE + DEMI_LARGEUR , addY - HAUTEUR, angleAddDroite);
				if (CSG.profil.cadenceAdd > 6) {
					ArmeAdd c = ArmeAdd.pool.obtain();
					c.init(addX + DECALAGE_TIR_ADD_X_DROITE + DEMI_LARGEUR , addY - HAUTEUR, angleAddDroite-10);
				}
			}
			dernierTirAdd = EndlessMode.maintenant;
		}
	}
	
	public static void rajoutAdd() {
		if (VaisseauJoueur.addGauche == false) {
			VaisseauJoueur.addGauche = true;
			Bonus.collisionEnPlusDroite = LARGEUR_ADD * 2;
		}
		else if (VaisseauJoueur.addDroite == false) {
			VaisseauJoueur.addDroite = true;
			Bonus.collisionEnPlusAGauche = LARGEUR_ADD * 2;
		}
		else if (VaisseauJoueur.addGauche2 == false) {
			VaisseauJoueur.addGauche2 = true;
			Bonus.collisionEnPlusDroite = LARGEUR_ADD * 4;
		}
		else if (VaisseauJoueur.addDroite2 == false) {
			VaisseauJoueur.addDroite2 = true;
			Bonus.collisionEnPlusAGauche = LARGEUR_ADD * 4;
		}
	}
	
	public static void changerArme(){
		arme = ManagerArme.changerArme(arme);
		CSG.profil.setArmeSelectionnee(arme.getLabel());
	}


	public static void perdu() {
		if (EndlessMode.konamiCode) return;
		if (!bouclier) EndlessMode.perdu();
		else bouclier = false;
	}

	@Override
	public int getLargeur() {		return LARGEUR;	}

	@Override
	public int getHauteur() {		return HAUTEUR;	}

	public void accelerometre() {
		mvtLimiteVitesse(-((Gdx.input.getAccelerometerX()-originalAccelX)*140), -((Gdx.input.getAccelerometerY()-originalAccelY)*130));
		limitesEtCentre();
	}

	public static void enleverAddGauche1() {	addGauche = false;	}
	public static void enleverAddDroite1() {	addDroite = false;	}
	public static void enleverAddGauche2() {	addGauche2 = false;	}
	public static void enleverAddDroite2() {	addDroite2 = false;	}

	public static void ajoutBouclier() {
		bouclier = true;
	}
}