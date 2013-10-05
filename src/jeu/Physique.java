package jeu;

import objets.armes.Armes;
import objets.armes.joueur.ArmeJoueur;
import objets.ennemis.Ennemis;
import objets.joueur.VaisseauJoueur;
import menu.CSG;
import assets.particules.Particules;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Physique {

	/**
	 * Test si c'est touch� dans le sprite
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
		position.y += (dirY * EndlessMode.delta * vitesse);
		return toujoursAfficher(position, hauteur, largeur);
	}
	
	private static void deplacementBase(Vector2 direction, Vector2 position, float v) {
		position.x += (direction.x * EndlessMode.delta * v);
		position.y += (direction.y * EndlessMode.delta * v);	
	}
	
	/**
	 * On laisse une marge au dessus
	 * @return True = toujours affiche
	 */
	public static boolean toujoursAfficher(Vector2 position, int hauteur, int largeur) {
		if (position.y + hauteur < 0 || position.x + largeur < 0 || position.x > CSG.LARGEUR_ZONE_JEU  || position.y > CSG.HAUTEUR_ECRAN + hauteur)
			return false;
		return true;
	}
	
	/**
	 * On laisse une marge au dessus
	 * @return True = toujours affiche
	 */
	public static boolean toujoursAfficher(Vector2 position, int lrg) {
		if (position.y + lrg < 0 || position.x + lrg  < 0 || position.x > CSG.LARGEUR_ZONE_JEU || position.y > CSG.HAUTEUR_ECRAN + lrg)
			return false;
		return true;
	}
	
	/**
	 * On laisse une marge au dessus
	 * @return True = toujours affiche
	 */
	public static boolean toujoursAfficher(float posX, float posY, int largeur) {
		if (posY + largeur < 0 || posX + largeur < 0 || posX > CSG.LARGEUR_ZONE_JEU || posY > CSG.HAUTEUR_ECRAN + largeur)
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
			if (sens)								dir.x -= amplitude * EndlessMode.delta;
			else									dir.x += amplitude * EndlessMode.delta;
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
		if (sens)					dir.x -= AMPLITUDE * EndlessMode.delta;
		else						dir.x += AMPLITUDE * EndlessMode.delta;
		mvtSansVerif(pos, dir);
		return sens;
	}

	/**
	 * Teste les collisions vaisseau, balles et ennemis. Invoque la methode perdu du vaisseau si c'est le cas
	 * @param vaisseau
	 * @return True si le vaisseau est touch�.
	 */
	public static void testCollisions() {
		// On parcourt la liste des ennemis
		for(Ennemis ennemi : Ennemis.LISTE){
			// Si le centre du vaisseau est dans un ennemi
			if (pointDansRectangle(VaisseauJoueur.centreX, VaisseauJoueur.centreY, ennemi.getRectangleCollision())) {
				EndlessMode.perdu();
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
	 * On lui passe le point en bas � gauche du rectangle, return true si oui
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

	// True si touch�, false sinon
    public static boolean pointDansRectangle(Vector2 p, Rectangle r) {
        return r.x <= p.x && r.x + r.width >= p.x && r.y <= p.y && r.y + r.height >= p.y;
    }
    
	public static boolean pointDansVaisseau(Vector2 position, int rectLarg, int rectHaut) {
		if (position.x <= VaisseauJoueur.centreX && position.x + rectLarg >= VaisseauJoueur.centreX && position.y <= VaisseauJoueur.centreY && position.y + rectHaut >= VaisseauJoueur.centreY) {
			VaisseauJoueur.perdu();
			return true;
		}
		return false;
	}
	
	public static boolean pointDansVaisseau(Vector2 position, int rectLarg) {
		if (position.x <= VaisseauJoueur.centreX && position.x + rectLarg >= VaisseauJoueur.centreX && position.y <= VaisseauJoueur.centreY && position.y + rectLarg >= VaisseauJoueur.centreY) {
			VaisseauJoueur.perdu();
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
		return angleRotation + (EndlessMode.delta * vitesseRotation);
	}
	
	private static Vector2 cibleTMP = new Vector2();
	private static float angleCible;
	private static float angleDirection;

	public static float mouvementTeteChercheuse(Vector2 direction, Vector2 position, float vitesseMax, int largeur, float vitesseAngulaire, int demiLargeur) {
		cibleTMP.x = (VaisseauJoueur.position.x + VaisseauJoueur.DEMI_LARGEUR) - (position.x + demiLargeur);
		cibleTMP.y = (VaisseauJoueur.position.y + VaisseauJoueur.DEMI_HAUTEUR) - (position.y + demiLargeur);
		angleCible = cibleTMP.angle();
		angleDirection = direction.angle();
		// cas particulier
		if ((angleCible > 350) && ( (angleDirection < 5) || (angleDirection > 355)) )
			direction.rotate(-vitesseAngulaire * EndlessMode.delta * 10);
		else {
			if (angleDirection < angleCible && angleCible - angleDirection < 180) {
				direction.rotate(vitesseAngulaire * EndlessMode.delta);
			} else {
				if (Math.abs(angleCible - angleDirection) < 180)	direction.rotate(-vitesseAngulaire * EndlessMode.delta);
				else												direction.rotate(vitesseAngulaire * EndlessMode.delta);
			}
		}
		
		mouvementDeBase(direction, position, vitesseMax, largeur);
		return angleDirection;
	}
	
	private static boolean collision = false;
	/**
	 * Retourne true si elle a touché un add
	 * @param position
	 * @param largeur
	 * @param hauteur
	 * @return
	 */
	public static boolean testCollisionAdds(Vector2 position, int largeur, int hauteur) {
		collision = false;
		if (VaisseauJoueur.addGauche && Physique.pointDansRectangle(VaisseauJoueur.centreAddGauche1X, VaisseauJoueur.centreAdds1Y, position.x, position.y, largeur, hauteur)) {
			VaisseauJoueur.enleverAddGauche1();
			collision = true;
		}
		if (VaisseauJoueur.addDroite && Physique.pointDansRectangle(VaisseauJoueur.centreAddDroite1X, VaisseauJoueur.centreAdds1Y, position.x, position.y, largeur, hauteur))	{
			VaisseauJoueur.enleverAddDroite1();
			collision = true;
		}
		if (VaisseauJoueur.addGauche2 && Physique.pointDansRectangle(VaisseauJoueur.centreAddGauche2X, VaisseauJoueur.centreAdds2Y, position.x, position.y, largeur, hauteur)) {
			VaisseauJoueur.enleverAddGauche2();
			collision = true;
		}
		if (VaisseauJoueur.addDroite2 && Physique.pointDansRectangle(VaisseauJoueur.centreAddDroite2X, VaisseauJoueur.centreAdds2Y, position.x, position.y, largeur, hauteur)) {
			VaisseauJoueur.enleverAddDroite2();
			collision = true;
		}
		return collision;
	}
	
	/**
	 * Test si le carr� form� par la position + la largeur est dans un des adds du vaisseau
	 * @param position
	 * @param largeur
	 * @return
	 */
	public static boolean testCollisionAdds(Vector2 position, int largeur) {
		collision = false;
		if (VaisseauJoueur.addGauche && Physique.pointDansCarre(VaisseauJoueur.centreAddGauche1X, VaisseauJoueur.centreAdds1Y, position.x, position.y, largeur)) {
			VaisseauJoueur.enleverAddGauche1();
			collision = true;
		}
		if (VaisseauJoueur.addDroite && Physique.pointDansCarre(VaisseauJoueur.centreAddDroite1X, VaisseauJoueur.centreAdds1Y, position.x, position.y, largeur))	{
			VaisseauJoueur.enleverAddDroite1();
			collision = true;
		}
		if (VaisseauJoueur.addGauche2 && Physique.pointDansCarre(VaisseauJoueur.centreAddGauche2X, VaisseauJoueur.centreAdds2Y, position.x, position.y, largeur)) {
			VaisseauJoueur.enleverAddGauche2();
			collision = true;
		}
		if (VaisseauJoueur.addDroite2 && Physique.pointDansCarre(VaisseauJoueur.centreAddDroite2X, VaisseauJoueur.centreAdds2Y, position.x, position.y, largeur)) {
			VaisseauJoueur.enleverAddDroite2();
			collision = true;
		}
		return collision;
	}

	
	public static void mvtSansVerif(Vector2 position, Vector2 direction) {
		position.x += direction.x * EndlessMode.delta;
		position.y += direction.y * EndlessMode.delta;
	}
	
	/**
	 * Le fait bouger vers le bas et ralentit � un premier pallier et s'arrete au deuxi�me
	 * @param position
	 * @param vitesse
	 * @param maintenant
	 */
	public static void mvtArretHauteur(Vector2 position, float vitesse, float maintenant) {
		if (position.y < CSG.HAUTEUR_ECRAN_PALLIER_2) {
			// On ralentit
			if (position.y > CSG.HAUTEUR_ECRAN_PALLIER_3)
				position.y += (-50 * EndlessMode.delta);
		} else {
			position.y += (vitesse * EndlessMode.delta);
		}
		if (maintenant > 10) {
			position.y -= vitesse * EndlessMode.delta;
			position.x += vitesse * EndlessMode.delta;
		}
	}
	
	/**
	 * Retourne true = toujours affich�
	 * @param direction
	 * @param position
	 * @param largeur
	 * @return true si toujours affich�
	 */
	public static boolean mouvementDeBase(Vector2 direction, Vector2 position,	int largeur) {
		position.x += direction.x * EndlessMode.delta;
		position.y += direction.y * EndlessMode.delta;
		return toujoursAfficher(position, largeur);
	}
	
	/**
	 * Le fait bouger en tenant compte du delta
	 * @param hauteur
	 * @param largeur
	 * @param direction
	 * @param position
	 * @return true si toujours affich�
	 */
	public static boolean mouvementDeBase(int hauteur, int largeur,	Vector2 direction, Vector2 position) {
		position.x += direction.x * EndlessMode.delta;
		position.y += direction.y * EndlessMode.delta;
		return toujoursAfficher(position, largeur, hauteur);
	}
	
	/**
	 * Se d�place par rapport au joueur dans l'axe X pour essayer d'�tre sur le m�me axe Y.
	 * @param e
	 * @param direction
	 * @return
	 */
	public static float mvtOmbrelle(Ennemis e, Vector2 direction) {
		direction.x = -((CSG.DEMI_LARGEUR_ZONE_JEU - VaisseauJoueur.centreX) - (CSG.DEMI_LARGEUR_ZONE_JEU - e.position.x - e.getDemiLargeur()));
		direction.y /= 1 + EndlessMode.delta;
		Physique.mvtSansVerif(e.position, direction);
		return getAngleVersJoueur(e.position, e.getDemiLargeur(), e.getDemiHauteur());
	}
	
	private static Vector2 positionCible = new Vector2();
	private static Vector2 positionBalle = new Vector2();
	/**
	 * Renvoie l'angle entre le centre de l'arme et le centre du joueur, les dimensions pass�es sont celles de l'arme
	 * @param vecteurPosition
	 * @param demiLargeur 
	 * @param demiHauteur
	 * @return
	 */
	public static float getAngleVersJoueur(Vector2 vecteurPosition,  float demiLargeur, float demiHauteur) {
		positionCible.x = (VaisseauJoueur.position.x + VaisseauJoueur.DEMI_LARGEUR);
		positionCible.y = (VaisseauJoueur.position.y + VaisseauJoueur.DEMI_HAUTEUR);
		positionBalle.x = vecteurPosition.x + demiLargeur;
		positionBalle.y = vecteurPosition.y + demiHauteur;
		return positionCible.sub(positionBalle).angle();
	}
}
