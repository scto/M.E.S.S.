package vaisseaux.joueur;

import menu.CSG;
import vaisseaux.TypesArmes;
import vaisseaux.Vaisseaux;
import vaisseaux.armes.ArmesBalayage;
import vaisseaux.armes.ArmesDeBase;
import vaisseaux.armes.ManagerArmeBalayage;
import vaisseaux.armes.ManagerArmeDeBase;
import affichage.animation.AnimationVaisseau;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Classe gérant les vaisseaux du types de base
 * @author Julien
 *
 */
public class VaisseauType1 extends Vaisseaux {

	// ** ** dimensions du vaisseau et autre
	public static final int LARGEUR = CSG.LARGEUR_ECRAN / 9;
	public static final int DEMI_LARGEUR = LARGEUR/2;
	public static final int HAUTEUR = CSG.HAUTEUR_ECRAN / 12;
	public static final int DEMI_HAUTEUR = HAUTEUR / 2;
	private final AnimationVaisseau animation;
	// ** ** limites dans l'espace
	private static final int LIMITE_X_GAUCHE = 0 - DEMI_LARGEUR;
	private static final int LIMITE_X_DROITE = CSG.LARGEUR_ECRAN - DEMI_LARGEUR;
	private static final int LIMITE_Y_GAUCHE = 0 - DEMI_HAUTEUR;
	private static final int LIMITE_Y_DROITE = CSG.HAUTEUR_ECRAN - DEMI_HAUTEUR;
	private static final int DEGRE_PRECISION_DEPLACEMENT = 4;
	// ** ** parametres pouvant etre modifiés par des bonus
	private static boolean peutSeTeleporter = false;
	private static int vitesseMax = 1000;
	private static long modifCadenceTir = 0;
	private static TypesArmes typeArme = CSG.profil.getArmeSelectionnee();
	private static TypesArmes[] typeArmePossible = TypesArmes.LISTE_ARME_JOUEUR;
	// ** ** variable utilitaires
	private static float dernierTir = 0;
	private static float maintenant = 0;
	public static float oldPosition = 0;
	public static Vector2 position = new Vector2();
	public static float prevX;
	public static float prevY;
	public static float destX;
	public static float destY;
	private static float vitesseFoisDelta = 0;
	private static float tmpCalculDeplacement = 0;

	/**
	 * initialise le vaisseau avec les parametres par défaut
	 */
	public VaisseauType1() {
		super();
		animation = new AnimationVaisseau(this, 3);
		initialiser();
	}

	/**
	 * positionne au centre et en bas
	 */
	private void initialiser() {
		position = new Vector2(CSG.DEMI_LARGEUR_ECRAN - DEMI_LARGEUR, HAUTEUR/2);
		vitesseMax += CSG.profil.bonusVitesse;
		prevX = position.x;
		prevY = position.y;
	}
	/**
	 * affiche le vaisseau à l'endroit prévu avec la taille standardt
	 * @param batch
	 * @param delta 
	 */
	public void draw(SpriteBatch batch, float delta) {
		animation.afficher(batch, delta);
		// obligé de faire l'update ici car le mouvement n'est updaté que quand on clique.
		oldPosition = position.x;
	}
	/**
	 * Fait aller le vaisseau à l'endroit cliqué.
	 * Si il peut se teleporter il y va directement -- Sinon il se déplace suivant sa vitesse max
	 */
	public void mouvements(float delta) {
		switch (CSG.profil.typeControle) {
		case CSG.CONTROLE_TOUCH_NON_RELATIVE:
			destX = (Gdx.input.getX() - DEMI_LARGEUR) - position.x;
			if (destX < DEGRE_PRECISION_DEPLACEMENT & destX > -DEGRE_PRECISION_DEPLACEMENT)
				destX = 0;
			else
				destX *= 10;
			destY = (CSG.HAUTEUR_ECRAN - (Gdx.input.getY() + DEMI_HAUTEUR))	- position.y;
			if (destY < DEGRE_PRECISION_DEPLACEMENT	& destY > -DEGRE_PRECISION_DEPLACEMENT)
				destY = 0;
			else
				destY *= 10;
			break;
		case CSG.CONTROLE_TOUCH_RELATIVE:
			if (Gdx.input.justTouched()) {
				prevX = Gdx.input.getX();
				prevY = Gdx.input.getY();
			}
			destX = Gdx.input.getX() - prevX;
			destY = -(Gdx.input.getY() - prevY);
			break;
		}
		mvtLimiteVitesse(destX, destY, delta);
		limites();
	}

	/**
	 * Le vaisseau se déplace vers les coordonnées passées avec un cap à sa vitesse max
	 * @param x
	 * @param y
	 */
	private void mvtLimiteVitesse(float x, float y, float delta) {
		// haut gauche : +x -y
		tmpCalculDeplacement = ((x * x) + (y * y)) * delta;
		if(tmpCalculDeplacement < DEGRE_PRECISION_DEPLACEMENT) return;
		tmpCalculDeplacement = (float) Math.sqrt(tmpCalculDeplacement);
		vitesseFoisDelta = vitesseMax * delta;
		// Si on va trop vite
		if (tmpCalculDeplacement > vitesseFoisDelta) {
			x = x * (vitesseFoisDelta / tmpCalculDeplacement);
			y = y * (vitesseFoisDelta / tmpCalculDeplacement);
		} 
		position.x += (x * delta);
		position.y += (y * delta);
	}

	/**
	 * Le vaisseau va aux coordonnées passées
	 * @param x
	 * @param y
	 */
	private void mvtTeleport(int x, int y) {
		position.x = x;
		position.y = y;
	}
	
	/**
	 * oblige le vaisseau a rester dans les limites de l'écran
	 */
	private void limites() {
		if(position.x < LIMITE_X_GAUCHE) position.x = LIMITE_X_GAUCHE;
		if(position.x > LIMITE_X_DROITE) position.x = LIMITE_X_DROITE;
		if(position.y < LIMITE_Y_GAUCHE) position.y = LIMITE_Y_GAUCHE;
		if(position.y > LIMITE_Y_DROITE) position.y = LIMITE_Y_DROITE;
	}

	/**
	 * vérifie si le vaisseau peut tirer ou pas. Tir au cas ou.
	 * Vu qu'on appele tir une frame sur deux adapter la cadence en conséquence
	 * @param listeTir
	 */
	public void tir(float delta){
		maintenant += delta;
		// current time millis prend apparement 5 à 6 cycles contre parfois 100 pour nanotime mais c'est moins précis. JE N'AI PAS VERIFIE
		// -- -- Bon c'est naze la il doit y avoir un meilleur moyen de faire
		switch (typeArme) {
			case ArmeDeBase:
				if (maintenant > dernierTir	+ ArmesDeBase.CADENCETIR + modifCadenceTir) {
					ManagerArmeDeBase.init(position.x + DEMI_LARGEUR - ArmesDeBase.DEMI_LARGEUR, position.y + HAUTEUR, false);
					dernierTir = maintenant;
				}
				break;
			case ArmeBalayage:
				if (maintenant > dernierTir	+ ArmesBalayage.CADENCETIR + modifCadenceTir) {
					ManagerArmeBalayage.init(position.x + DEMI_LARGEUR - ArmesBalayage.DEMI_LARGEUR, position.y + HAUTEUR, 0, 1, false);
					dernierTir = maintenant;
				}
				break;
		}
	}
	
	public void changerArme(){
		typeArme = TypesArmes.changerArme(typeArmePossible, typeArme);
		CSG.profil.setArmeSelectionnee(typeArme);
	}


	public void perdu() {
		
	}

	@Override
	public int getLargeur() {
		return LARGEUR;
	}

	@Override
	public int getHauteur() {
		return HAUTEUR;
	}
	
	
}
