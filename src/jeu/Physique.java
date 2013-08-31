package jeu;

import menu.CSG;
import vaisseaux.armes.Armes;
import vaisseaux.armes.joueur.ArmeJoueur;
import vaisseaux.ennemis.Ennemis;
import vaisseaux.joueur.VaisseauType1;
import assets.particules.Particules;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Physique {

	public static boolean pointIn(Sprite s) { //, int x, int y) {
		int x = Gdx.input.getX();
		int y = CSG.HAUTEUR_ECRAN - Gdx.input.getY();
        return s.getX() <= x && s.getX() + s.getWidth() >= x && s.getY() <= y && s.getY() + s.getHeight() >= y;
	}
	/**
	 * Test si le point est dans la police. 
	 */
	public static boolean pointIn(TextBounds bounds, int boundsX, int boundsY, int x, int y) {
		y += bounds.height;
		return boundsX <= x && boundsX + bounds.width >= x && boundsY <= y && boundsY + bounds.height >= y;
	}     
	
	/**
	 * Fait aller l'objet dans la direction passee
	 * @return True si encore a l'ecran
	 */
	public static boolean mouvementDeBase(Vector2 direction, Vector2 position, final int VITESSE, int hauteur, int largeur) { 
		deplacementBase(direction, position, VITESSE);
		return toujoursAfficher(position, hauteur, largeur);
	}
	
	/**
	 * Fait aller l'objet dans la direction passee
	 * @return True si encore a l'ecran
	 */
	public static boolean mouvementDeBase(Vector2 direction, Vector2 position, float VITESSE, int largeur) {
		deplacementBase(direction, position, VITESSE);
		return toujoursAfficher(position, largeur);
	}
	
	/**
	 * Fait aller l'objet dans la direction passee
	 * @return True si encore a l'ecran
	 */
	public static boolean mouvementDeBaseDirY(float dirY, Vector2 position,	int vitesse, int hauteur, int largeur) {
		deplacementBaseDirY(dirY, position, vitesse);
		return toujoursAfficher(position, hauteur, largeur);
	}
	
	/**
	 * Fait aller l'objet dans la direction passee
	 * @return True si encore a l'ecran
	 */
	public static boolean mouvementDeBaseDirYDirX(float dirX, float dirY, Vector2 position, int vitesse, int hauteur, int largeur) {
		deplacementBaseDirYDirX(dirY, dirX, position, vitesse);
		return toujoursAfficher(position, hauteur, largeur);
	}
	
	/**
	 * Fait aller l'objet dans la direction passee
	 * @return True si encore a l'ecran
	 */
	public static boolean mouvementDeBaseDirYDirX(float dirX, float dirY, Vector2 position, int VITESSE, int largeur) {
		deplacementBaseDirYDirX(dirY, dirX, position, VITESSE);
		return toujoursAfficher(position, largeur);
	}
	
	private static void deplacementBaseDirYDirX(float dirY, float dirX, Vector2 position, final int VITESSE) {
		position.y += (dirY * Endless.delta * VITESSE);
		position.x += (dirX * Endless.delta * VITESSE);	
	}

	private static void deplacementBaseDirY(float dirY, Vector2 position, final int VITESSE) {
		position.y += (dirY * Endless.delta * VITESSE);	
	}
	
	/**
	 * ATTENTION voir si il vaut mieux creer un vecteur ou alors faire les calculs sur x et y sans en creer un
	 */
	private static void deplacementBase(Vector2 direction, Vector2 position, final float VITESSE) {
		position.x += (direction.x * Endless.delta * VITESSE);
		position.y += (direction.y * Endless.delta * VITESSE);	
	}
	
	/**
	 * @return True = toujours affiche
	 */
	public static boolean toujoursAfficher(Vector2 position, int hauteur, int largeur) {
		if (position.y + hauteur < 0 || position.x + largeur + largeur < 0 || position.x > CSG.LARGEUR_ZONE_JEU + largeur || position.y > CSG.HAUTEUR_ECRAN + hauteur)
			return false;
		return true;
	}
	
	public static boolean toujoursAfficher(Vector2 position, int lrg) {
		if (position.y + lrg < 0 || position.x + lrg + lrg < 0 || position.x > CSG.LARGEUR_ZONE_JEU + lrg || position.y > CSG.HAUTEUR_ECRAN + lrg)
			return false;
		return true;
	}
	/**
	 * @return True = toujours affiche
	 */
	public static boolean toujoursAfficher(float posX, float posY, int hauteur,	int largeur) {
		if (posY + hauteur < 0 || posX + largeur + largeur < 0 || posX > CSG.LARGEUR_ZONE_JEU + largeur || posY > CSG.HAUTEUR_ECRAN + hauteur)
			return false;
		return true;
	}
	
	/**
	 * @return True = toujours affiche
	 */
	public static boolean toujoursAfficher(float posX, float posY, int lARGEUR) {
		if (posY + lARGEUR < 0 || posX + lARGEUR + lARGEUR < 0 || posX > CSG.LARGEUR_ZONE_JEU + lARGEUR || posY > CSG.HAUTEUR_ECRAN + lARGEUR)
			return false;
		return true;
	}
	
	/**
	 * Fait aller l'ennemi en zigzag. Il doit utiliser le boolean pour modifier son sens
	 * @return le sens
	 */
	public static boolean goToZigZag(Vector2 pos, Vector2 dir, int demiLargeur, boolean sens, final float AMPLITUDE, final int VITESSE, final int hauteur, final int largeur, boolean mort, float axeDeBase){
		if (!mort) {
			if (pos.x + demiLargeur < axeDeBase)	sens = false;
			else									sens = true;
			if (sens)								dir.x -= AMPLITUDE * Endless.delta;
			else									dir.x += AMPLITUDE * Endless.delta;
		}
		deplacementBase(dir, pos, VITESSE);
		return sens;
	}
	
	/**
	 * Fait aller l'ennemi en zigzag. Il doit utiliser le boolean pour modifier son sens
	 * @return le sens
	 */
	public static boolean goToZigZagCentre(Vector2 pos, Vector2 dir,int demiLargeur, boolean sens, final float AMPLITUDE, final int hauteur, final int largeur){
		if (pos.x + demiLargeur < CSG.DEMI_LARGEUR_ZONE_JEU) 	sens = false;
		else 													sens = true;
		if (sens)					dir.x -= AMPLITUDE * Endless.delta;
		else						dir.x += AMPLITUDE * Endless.delta;
		mvtSansVerif(pos, dir);
		return sens;
	}

	/**
	 * Teste les collisions vaisseau, balles et ennemis. Invoque la methode perdu du vaisseau si c'est le cas
	 * @param vaisseau
	 * @return True si le vaisseau est touchï¿½.
	 */
	public static void testCollisions() {
		// On parcourt la liste des ennemis
		for(Ennemis ennemi : Ennemis.liste){
			// Si le centre du vaisseau est dans un ennemi
			if( !ennemi.mort &&	pointDansRectangle(VaisseauType1.centreX, VaisseauType1.centreY, ennemi.getRectangleCollision())){
				Endless.perdu();
			}
			for (ArmeJoueur a : Armes.liste) {
				// Le tir touche l'ennemie
				if (!ennemi.mort && rectangleDansRectangle(a.position.x, a.position.y, a.getLargeur(), a.getHauteur(), ennemi.getRectangleCollision())) {
					// on decompte suivant la puissance de l'arme et en plus ca fait un free
					Particules.ajoutDebris(a); // Ajout des particules dans la direction voulue
					if (ennemi.touche(a.getForce())){
						a.free();
						Armes.liste.removeValue(a, true);
					}
				}
			}
		}
	}
	
	private static boolean rectangleDansRectangle(float x, float y,	int largeur, int hauteur, Rectangle r2) {
		  if(x < r2.x + r2.width && x + largeur > r2.x && y < r2.y + r2.height && y + hauteur > r2.y)	            return true;
	      else	            return false;
	}
	/**
	 * On lui passe le point en bas ï¿½ gauche du rectangle, return true si oui
	 * @param x
	 * @param y
	 * @param rectX
	 * @param rectY
	 * @param rectLarg
	 * @param rectHaut
	 * @return
	 */
	public static boolean pointDansRectangle(float x, float y, float rectX, float rectY, float rectLarg, float rectHaut) {
		 return rectX <= x && rectX + rectLarg >= x && rectY <= y && rectY + rectHaut >= y;
	}
	// 1.94 - 4
	public static boolean pointDansCarre(float x, float y, float rectX, float rectY, float rectLarg) {
		 return rectX <= x && rectX + rectLarg >= x && rectY <= y && rectY + rectLarg >= y;
	}
	
	 public static boolean pointDansRectangle(float x, float y, Rectangle r) {
		 return r.x <= x && r.x + r.width >= x && r.y <= y && r.y + r.height >= y;
	}

	// True si touchï¿½, false sinon
    public static boolean pointDansRectangle(Vector2 p, Rectangle r) {
        return r.x <= p.x && r.x + r.width >= p.x && r.y <= p.y && r.y + r.height >= p.y;
    }
    
	public static float nbTests = 0;
	public static float nbPass = 0;
	public static int nbTestParPass = 0;
	
	public static boolean pointDansVaisseau(Vector2 position, int rectLarg, int rectHaut) {
		if (position.x <= VaisseauType1.centreX && position.x + rectLarg >= VaisseauType1.centreX && position.y <= VaisseauType1.centreY && position.y + rectHaut >= VaisseauType1.centreY) {
			VaisseauType1.perdu();
			return true;
		}
		return false;
	}
	
	public static boolean pointDansVaisseau(Vector2 position, int rectLarg) {
		if (position.x <= VaisseauType1.centreX && position.x + rectLarg >= VaisseauType1.centreX && position.y <= VaisseauType1.centreY && position.y + rectLarg >= VaisseauType1.centreY) {
			VaisseauType1.perdu();
			return true;
		}
		return false;
	}
    
    /**
     * Si les rectangles se touchent. True si oui, false si non
     * @param r1
     * @param r2
     * @return
     */
    public static boolean rectangleDansRectangle(Rectangle r1, Rectangle r2) {
        if(r1.x < r2.x + r2.width && r1.x + r1.width > r2.x && r1.y < r2.y + r2.height && r1.y + r1.height > r2.y)     return true;
        else            return false;
    }
	public static float rotation(float angleRotation, float vitesseRotation) {
		return angleRotation + (Endless.delta * vitesseRotation);
	}
	
	// ATTENTION 
	private static Vector2 cibleTMP = new Vector2();
	private static float angleCible;
	private static float angleDirection;

	// Pas compris en relisant ? Normal, dï¿½jï¿½ pas compris en ï¿½crivant, enfin si mais en tout cas c'est surement pas top
	public static float mouvementTeteChercheuse(Vector2 direction, Vector2 position, float vitesseMax, int largeur, float vitesseAngulaire, int demiLargeur) {
		// Init variable
		cibleTMP.x = (VaisseauType1.position.x + VaisseauType1.DEMI_LARGEUR) - (position.x + demiLargeur);
		cibleTMP.y = (VaisseauType1.position.y + VaisseauType1.DEMI_HAUTEUR) - (position.y + demiLargeur);
		angleCible = cibleTMP.angle();
		angleDirection = direction.angle();
		// cas particulier
		if ((angleCible > 350) & ( (angleDirection < 5) | (angleDirection > 355)) )
			direction.rotate(-vitesseAngulaire * Endless.delta * 10);
		else {
			if (angleDirection < angleCible & angleCible - angleDirection < 180) {
				direction.rotate(vitesseAngulaire * Endless.delta);
			} else {
				if (Math.abs(angleCible - angleDirection) < 180)	direction.rotate(-vitesseAngulaire * Endless.delta);
				else												direction.rotate(vitesseAngulaire * Endless.delta);
			}
		}
		
		mouvementDeBase(direction, position, vitesseMax, largeur);
		return angleDirection;
	}
	
	/** @return the angle in degrees of this vector (point) relative to the x-axis. Angles are counter-clockwise and between 0 and
	 *         360. */
	static float angle;
	public static float angle (float x, float y) {
		angle = (float)Math.atan2(y, x) * MathUtils.radiansToDegrees;
		return angle;
	}
	

	
	private static boolean collision = false;
	
	/**
	 * Retourne true si elle a touchÃ© un add
	 * @param position
	 * @param LARGEUR
	 * @param HAUTEUR
	 * @return
	 */
	public static boolean testCollisionAdds(Vector2 position, int LARGEUR, int HAUTEUR) {
		collision = false;
		if (VaisseauType1.addGauche && Physique.pointDansRectangle(VaisseauType1.centreAddGauche1X, VaisseauType1.centreAdds1Y, position.x, position.y, LARGEUR, HAUTEUR)) {
			VaisseauType1.enleverAddGauche1();
			collision = true;
		}
		if (VaisseauType1.addDroite && Physique.pointDansRectangle(VaisseauType1.centreAddDroite1X, VaisseauType1.centreAdds1Y, position.x, position.y, LARGEUR, HAUTEUR))	{
			VaisseauType1.enleverAddDroite1();
			collision = true;
		}
		if (VaisseauType1.addGauche2 && Physique.pointDansRectangle(VaisseauType1.centreAddGauche2X, VaisseauType1.centreAdds2Y, position.x, position.y, LARGEUR, HAUTEUR)) {
			VaisseauType1.enleverAddGauche2();
			collision = true;
		}
		if (VaisseauType1.addDroite2 && Physique.pointDansRectangle(VaisseauType1.centreAddDroite2X, VaisseauType1.centreAdds2Y, position.x, position.y, LARGEUR, HAUTEUR)) {
			VaisseauType1.enleverAddDroite2();
			collision = true;
		}
		return collision;
	}
	
	public static boolean testCollisionAdds(Vector2 position, int LARGEUR) {
		collision = false;
		if (VaisseauType1.addGauche && Physique.pointDansCarre(VaisseauType1.centreAddGauche1X, VaisseauType1.centreAdds1Y, position.x, position.y, LARGEUR)) {
			VaisseauType1.enleverAddGauche1();
			collision = true;
		}
		if (VaisseauType1.addDroite && Physique.pointDansCarre(VaisseauType1.centreAddDroite1X, VaisseauType1.centreAdds1Y, position.x, position.y, LARGEUR))	{
			VaisseauType1.enleverAddDroite1();
			collision = true;
		}
		if (VaisseauType1.addGauche2 && Physique.pointDansCarre(VaisseauType1.centreAddGauche2X, VaisseauType1.centreAdds2Y, position.x, position.y, LARGEUR)) {
			VaisseauType1.enleverAddGauche2();
			collision = true;
		}
		if (VaisseauType1.addDroite2 && Physique.pointDansCarre(VaisseauType1.centreAddDroite2X, VaisseauType1.centreAdds2Y, position.x, position.y, LARGEUR)) {
			VaisseauType1.enleverAddDroite2();
			collision = true;
		}
		return collision;
	}
	
	private static float baseBleu = 0;
	private static float baseVert = 0;
	private static boolean sensBleu = false;
	private static boolean sensVert = false;
	
	public void routineCouleur(){
		if (baseBleu > 1) {
			sensBleu = !sensBleu;
			baseBleu = 1;
		} else {
			if (baseBleu < 0) {
				sensBleu = !sensBleu;
				baseBleu = 0;
			}
		}
		if (sensBleu) 	baseBleu += .06f;
		else 			baseBleu -= .06f;
		
		if (baseVert > 1) {
			sensVert = !sensVert;
			baseVert = 1;
		} else {
			if (baseVert < 0) {
				sensVert = !sensVert;
				baseVert = 0;
			}
		}
		if (sensVert) 	baseVert += .08f;
		else 			baseVert -= .08f;
	}
	
	public static void mvtSansVerif(Vector2 position, Vector2 direction) {
		position.x += direction.x * Endless.delta;
		position.y += direction.y * Endless.delta;
	}
	
	public static void mvtArretHauteur(Vector2 position, float vitesse, float maintenant) {
		if (position.y < CSG.HAUTEUR_ECRAN_PALLIER_2) {
			// On ralentit
			if (position.y > CSG.HAUTEUR_ECRAN_PALLIER_3)
				position.y += (-50 * Endless.delta);
		} else {
			position.y += (vitesse * Endless.delta);
		}
		if (maintenant > 10) {
			position.y -= vitesse * Endless.delta;
			position.x += vitesse * Endless.delta;
		}
	}
	
	/**
	 * Retourne true = toujours affiché
	 * @param direction
	 * @param position
	 * @param LARGEUR
	 * @return
	 */
	public static boolean mouvementDeBase(Vector2 direction, Vector2 position,	int LARGEUR) {
		position.x += direction.x * Endless.delta;
		position.y += direction.y * Endless.delta;
		return toujoursAfficher(position, LARGEUR);
	}
	public static boolean mouvementDeBase(int HAUTEUR, int LARGEUR,	Vector2 direction, Vector2 position) {
		position.x += direction.x * Endless.delta;
		position.y += direction.y * Endless.delta;
		return toujoursAfficher(position, LARGEUR, HAUTEUR);
	}
}
