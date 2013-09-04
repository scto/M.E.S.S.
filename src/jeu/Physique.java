package jeu;

import menu.CSG;
import vaisseaux.armes.Armes;
import vaisseaux.armes.joueur.ArmeJoueur;
import vaisseaux.ennemis.Ennemis;
import vaisseaux.joueur.VaisseauType1;
import assets.particules.Particules;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Physique {

	/**
	 * Test si c'est touché dans le sprite
	 * @param s
	 * @return
	 */
	public static boolean pointIn(Sprite s) {
		int x = Gdx.input.getX();
		int y = CSG.HAUTEUR_ECRAN - Gdx.input.getY();
        return s.getX() <= x && s.getX() + s.getWidth() >= x && s.getY() <= y && s.getY() + s.getHeight() >= y;
	}
	
	/**
	 * Fait aller l'objet dans la direction passee
	 * @return True si encore a l'ecran
	 */
	public static boolean mouvementDeBase(Vector2 direction, Vector2 position, int vitesse, int hauteur, int largeur) { 
		deplacementBase(direction, position, vitesse);
		return toujoursAfficher(position, hauteur, largeur);
	}
	
	/**
	 * Fait aller l'objet dans la direction passee
	 * @return True si encore a l'ecran
	 */
	public static boolean mouvementDeBase(Vector2 direction, Vector2 position, float vitesse, int largeur) {
		deplacementBase(direction, position, vitesse);
		return toujoursAfficher(position, largeur);
	}
	
	/**
	 * Fait aller l'objet dans la direction passee
	 * @return True si encore a l'ecran
	 */
	public static boolean mouvementDeBaseDirY(float dirY, Vector2 position,	int vitesse, int hauteur, int largeur) {
		position.y += (dirY * Endless.delta * vitesse);
		return toujoursAfficher(position, hauteur, largeur);
	}
	
	private static void deplacementBase(Vector2 direction, Vector2 position, float v) {
		position.x += (direction.x * Endless.delta * v);
		position.y += (direction.y * Endless.delta * v);	
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
	public static boolean toujoursAfficher(float posX, float posY, int lARGEUR) {
		if (posY + lARGEUR < 0 || posX + lARGEUR + lARGEUR < 0 || posX > CSG.LARGEUR_ZONE_JEU + lARGEUR || posY > CSG.HAUTEUR_ECRAN + lARGEUR)
			return false;
		return true;
	}
	
	/**
	 * Fait aller l'ennemi en zigzag. Il doit utiliser le boolean pour modifier son sens
	 * @return le sens
	 */
	public static boolean goToZigZag(Vector2 pos, Vector2 dir, int demiLargeur, boolean sens, float amplitude, int VITESSE, int hauteur, int largeur, boolean mort, float axeDeBase){
		if (!mort) {
			if (pos.x + demiLargeur < axeDeBase)	sens = false;
			else									sens = true;
			if (sens)								dir.x -= amplitude * Endless.delta;
			else									dir.x += amplitude * Endless.delta;
		}
		deplacementBase(dir, pos, VITESSE);
		return sens;
	}
	
	/**
	 * Fait aller l'ennemi en zigzag. Il doit utiliser le boolean pour modifier son sens
	 * @return le sens
	 */
	public static boolean goToZigZagCentre(Vector2 pos, Vector2 dir, int demiLargeur, boolean sens, final float AMPLITUDE, final int hauteur, final int largeur){
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
		for(Ennemis ennemi : Ennemis.LISTE){
			// Si le centre du vaisseau est dans un ennemi
			if(pointDansRectangle(VaisseauType1.centreX, VaisseauType1.centreY, ennemi.getRectangleCollision())){
				Endless.perdu();
			}
			for (ArmeJoueur a : Armes.liste) {
				// Le tir touche l'ennemie
				if (ennemi.checkBullet(a)) { //rectangleDansRectangle(a.position.x, a.position.y, a.getLargeur(), a.getHauteur(), ennemi.getRectangleCollision())) {
					// on decompte suivant la puissance de l'arme et en plus ca fait un free
					Particules.ajoutDebris(a); 
					if (ennemi.touche(a.getForce())){
						a.free();
						Armes.liste.removeValue(a, true);
					}
				}
			}
		}
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
    
	private static float nbTests = 0;
	private static float nbPass = 0;
	private static int nbTestParPass = 0;
	
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
	
	private static Vector2 cibleTMP = new Vector2();
	private static float angleCible;
	private static float angleDirection;

	public static float mouvementTeteChercheuse(Vector2 direction, Vector2 position, float vitesseMax, int largeur, float vitesseAngulaire, int demiLargeur) {
		cibleTMP.x = (VaisseauType1.position.x + VaisseauType1.DEMI_LARGEUR) - (position.x + demiLargeur);
		cibleTMP.y = (VaisseauType1.position.y + VaisseauType1.DEMI_HAUTEUR) - (position.y + demiLargeur);
		angleCible = cibleTMP.angle();
		angleDirection = direction.angle();
		// cas particulier
		if ((angleCible > 350) && ( (angleDirection < 5) || (angleDirection > 355)) )
			direction.rotate(-vitesseAngulaire * Endless.delta * 10);
		else {
			if (angleDirection < angleCible && angleCible - angleDirection < 180) {
				direction.rotate(vitesseAngulaire * Endless.delta);
			} else {
				if (Math.abs(angleCible - angleDirection) < 180)	direction.rotate(-vitesseAngulaire * Endless.delta);
				else												direction.rotate(vitesseAngulaire * Endless.delta);
			}
		}
		
		mouvementDeBase(direction, position, vitesseMax, largeur);
		return angleDirection;
	}
	
	private static boolean collision = false;
	/**
	 * Retourne true si elle a touchÃ© un add
	 * @param position
	 * @param largeur
	 * @param hauteur
	 * @return
	 */
	public static boolean testCollisionAdds(Vector2 position, int largeur, int hauteur) {
		collision = false;
		if (VaisseauType1.addGauche && Physique.pointDansRectangle(VaisseauType1.centreAddGauche1X, VaisseauType1.centreAdds1Y, position.x, position.y, largeur, hauteur)) {
			VaisseauType1.enleverAddGauche1();
			collision = true;
		}
		if (VaisseauType1.addDroite && Physique.pointDansRectangle(VaisseauType1.centreAddDroite1X, VaisseauType1.centreAdds1Y, position.x, position.y, largeur, hauteur))	{
			VaisseauType1.enleverAddDroite1();
			collision = true;
		}
		if (VaisseauType1.addGauche2 && Physique.pointDansRectangle(VaisseauType1.centreAddGauche2X, VaisseauType1.centreAdds2Y, position.x, position.y, largeur, hauteur)) {
			VaisseauType1.enleverAddGauche2();
			collision = true;
		}
		if (VaisseauType1.addDroite2 && Physique.pointDansRectangle(VaisseauType1.centreAddDroite2X, VaisseauType1.centreAdds2Y, position.x, position.y, largeur, hauteur)) {
			VaisseauType1.enleverAddDroite2();
			collision = true;
		}
		return collision;
	}
	
	/**
	 * Test si le carré formé par la position + la largeur est dans un des adds du vaisseau
	 * @param position
	 * @param largeur
	 * @return
	 */
	public static boolean testCollisionAdds(Vector2 position, int largeur) {
		collision = false;
		if (VaisseauType1.addGauche && Physique.pointDansCarre(VaisseauType1.centreAddGauche1X, VaisseauType1.centreAdds1Y, position.x, position.y, largeur)) {
			VaisseauType1.enleverAddGauche1();
			collision = true;
		}
		if (VaisseauType1.addDroite && Physique.pointDansCarre(VaisseauType1.centreAddDroite1X, VaisseauType1.centreAdds1Y, position.x, position.y, largeur))	{
			VaisseauType1.enleverAddDroite1();
			collision = true;
		}
		if (VaisseauType1.addGauche2 && Physique.pointDansCarre(VaisseauType1.centreAddGauche2X, VaisseauType1.centreAdds2Y, position.x, position.y, largeur)) {
			VaisseauType1.enleverAddGauche2();
			collision = true;
		}
		if (VaisseauType1.addDroite2 && Physique.pointDansCarre(VaisseauType1.centreAddDroite2X, VaisseauType1.centreAdds2Y, position.x, position.y, largeur)) {
			VaisseauType1.enleverAddDroite2();
			collision = true;
		}
		return collision;
	}

	
	public static void mvtSansVerif(Vector2 position, Vector2 direction) {
		position.x += direction.x * Endless.delta;
		position.y += direction.y * Endless.delta;
	}
	
	/**
	 * Le fait bouger vers le bas et ralentit à un premier pallier et s'arrete au deuxième
	 * @param position
	 * @param vitesse
	 * @param maintenant
	 */
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
	 * @param largeur
	 * @return true si toujours affiché
	 */
	public static boolean mouvementDeBase(Vector2 direction, Vector2 position,	int largeur) {
		position.x += direction.x * Endless.delta;
		position.y += direction.y * Endless.delta;
		return toujoursAfficher(position, largeur);
	}
	
	/**
	 * Le fait bouger en tenant compte du delta
	 * @param hauteur
	 * @param largeur
	 * @param direction
	 * @param position
	 * @return true si toujours affiché
	 */
	public static boolean mouvementDeBase(int hauteur, int largeur,	Vector2 direction, Vector2 position) {
		position.x += direction.x * Endless.delta;
		position.y += direction.y * Endless.delta;
		return toujoursAfficher(position, largeur, hauteur);
	}
	
	/**
	 * Se déplace par rapport au joueur dans l'axe X pour essayer d'être sur le même axe Y.
	 * @param e
	 * @param direction
	 * @return
	 */
	public static float mvtOmbrelle(Ennemis e, Vector2 direction) {
		direction.x = -((CSG.DEMI_LARGEUR_ZONE_JEU - VaisseauType1.centreX) - (CSG.DEMI_LARGEUR_ZONE_JEU - e.position.x - e.getDemiLargeur()));
		direction.y /= 1 + Endless.delta;
		Physique.mvtSansVerif(e.position, direction);
		return getAngleVersJoueur(e.position, e.getDemiLargeur(), e.getDemiHauteur());
	}
	
	private static Vector2 positionCible = new Vector2();
	private static Vector2 positionBalle = new Vector2();
	/**
	 * Renvoie l'angle entre le centre de l'arme et le centre du joueur, les dimensions passées sont celles de l'arme
	 * @param vecteurPosition
	 * @param demiLargeur 
	 * @param demiHauteur
	 * @return
	 */
	public static float getAngleVersJoueur(Vector2 vecteurPosition,  float demiLargeur, float demiHauteur) {
		positionCible.x = (VaisseauType1.position.x + VaisseauType1.DEMI_LARGEUR);
		positionCible.y = (VaisseauType1.position.y + VaisseauType1.DEMI_HAUTEUR);
		positionBalle.x = vecteurPosition.x + demiLargeur;
		positionBalle.y = vecteurPosition.y + demiHauteur;
		return positionCible.sub(positionBalle).angle();
	}
}
