package physique;

import vaisseaux.XP;
import vaisseaux.armes.Armes;
import vaisseaux.ennemis.Ennemis;
import vaisseaux.joueur.VaisseauType1;
import menu.CSG;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;


public class Physique {

	/**
	 * test si le point est dans le sprite ou pas
	 */
	public static boolean pointIn(Sprite s, int x, int y) {
        return s.getX() <= x && s.getX() + s.getWidth() >= x && s.getY() <= y && s.getY() + s.getHeight() >= y;
	}
	/**
	 * Test si le point est dans la police. 
	 * @param bounds
	 * @param x 
	 * @param y 
	 * @param touchX
	 * @param touchY
	 * @return
	 */
	public static boolean pointIn(TextBounds bounds, int boundsX, int boundsY, int x, int y) {
		y += bounds.height;
		return boundsX <= x & boundsX + bounds.width >= x & boundsY <= y & boundsY + bounds.height >= y;
	}     
	
	public static void mouvement(){
		
	}
	/**
	 * Fait aller l'objet dans la direction passée.
	 * @param direction
	 * @param position
	 * @param VITESSE
	 * @param largeur 
	 * @param hauteur 
	 * @return True si encore a l'ecran
	 */
	public static boolean mouvementDeBase(Vector2 direction, Vector2 position, final int VITESSE, int hauteur, int largeur) { 
		deplacementBase(direction, position, VITESSE);
		return toujoursAfficher(position, hauteur, largeur);
	}
	/**
	 * ATTENTION voir si il vaut mieux créer un vecteur ou alors faire les calculs sur x et y sans en créer un
	 * @param direction
	 * @param position
	 * @param VITESSE_MAX
	 */
	private static void deplacementBase(Vector2 direction, Vector2 position, final int VITESSE) {
//		Vector2 directionCourante = new Vector2(direction);
//		directionCourante.mul((Gdx.graphics.getDeltaTime() * VITESSE));
//		position.add(directionCourante);
		
		position.x += direction.x * Gdx.graphics.getDeltaTime() * VITESSE;
		position.y += direction.y * Gdx.graphics.getDeltaTime() * VITESSE;	
	}
	
	/**
	 * Test si l'ennemi est toujours affiché
	 * @param position
	 * @param hauteur
	 * @param largeur
	 * @return True = toujours affiché
	 */
	public static boolean toujoursAfficher(Vector2 position, int hauteur, int largeur) {
		if (position.y + hauteur < -5 | position.x + largeur + largeur < 5 |
				position.x > CSG.LARGEUR_ECRAN + largeur + 0 | position.y > CSG.HAUTEUR_ECRAN + hauteur)
			return false;
		else return true;
	}
	
	/**
	 * Fait aller l'ennemi en zigzag. Il doit utiliser le boolean pour modifier son sens
	 * @param position
	 * @param direction
	 * @param demiLargeur
	 * @param sens
	 * @param AMPLITUDE
	 * @param VITESSE 
	 * @param mort 
	 * @param axeDeBase 
	 * @return
	 */
	public static boolean goToZigZag(Vector2 position, Vector2 direction,int demiLargeur, boolean sens, final float AMPLITUDE, final int VITESSE, final int hauteur, final int largeur, boolean mort, float axeDeBase){
		// Seulement si il n'est pas en train d'exploser
		if (!mort) {
			if (position.x + demiLargeur < axeDeBase) {
				sens = false;
			} else {
				sens = true;
			}
			if (sens)
				direction.x -= AMPLITUDE * Gdx.graphics.getDeltaTime();
			else
				direction.x += AMPLITUDE * Gdx.graphics.getDeltaTime();
		}
		
		deplacementBase(direction, position, VITESSE);

		return sens;
	}
	
	/**
	 * Fait aller l'ennemi en zigzag. Il doit utiliser le boolean pour modifier son sens
	 * @param position
	 * @param direction
	 * @param demiLargeur
	 * @param sens
	 * @param AMPLITUDE
	 * @param VITESSE 
	 * @param mort 
	 * @return
	 */
	public static boolean goToZigZagCentre(Vector2 position, Vector2 direction,int demiLargeur, boolean sens, final float AMPLITUDE, final int VITESSE, final int hauteur, final int largeur, boolean mort){
		// Seulement si il n'est pas en train d'exploser
		if (!mort) {
			if (position.x + demiLargeur < CSG.LARGEUR_ECRAN / 2) {
				sens = false;
			} else {
				sens = true;
			}
			if (sens)
				direction.x -= AMPLITUDE * Gdx.graphics.getDeltaTime();
			else
				direction.x += AMPLITUDE * Gdx.graphics.getDeltaTime();
		}
		
		deplacementBase(direction, position, VITESSE);

		return sens;
	}

	/**
	 * Teste les collisions vaisseau, balles et ennemis. Invoque la methode perdu du vaisseau si c'est le cas
	 * @param vaisseau
	 * @return True si le vaisseau est touché.
	 */
	public static boolean testCollisions(VaisseauType1 vaisseau) {
		// ** On regarde si on prend de l'xp
		for(int xp = 0; xp < XP.liste.size(); xp++){
			if(pointDansRectangle(vaisseau.position.x + VaisseauType1.DEMI_LARGEUR, vaisseau.position.y + VaisseauType1.DEMI_HAUTEUR, XP.liste.get(xp).posX - XP.DEMI_LARGEUR_COLLISION, XP.liste.get(xp).posY - XP.DEMI_HAUTEUR_COLLISION, XP.LARGEUR_COLLISION, XP.HAUTEUR_COLLISION)){
				CSG.profil.addXp(XP.liste.get(xp).valeur);
				XP.liste.remove(xp);
			}
		}
		// ** On regarde si le joueur se fait toucher
		for(int a = 0; a < Armes.listeTirsDesEnnemis.size(); a++){
			if(pointDansRectangle(vaisseau.position.x + VaisseauType1.DEMI_LARGEUR, vaisseau.position.y + VaisseauType1.DEMI_HAUTEUR,
					Armes.listeTirsDesEnnemis.get(a).position.x, Armes.listeTirsDesEnnemis.get(a).position.y,
					Armes.listeTirsDesEnnemis.get(a).getLargeur(), Armes.listeTirsDesEnnemis.get(a).getHauteur())){
//				vaisseau.perdu();
//				return true;
			}
		}
		// On parcourt la liste des ennemis
		for(Ennemis ennemi : Ennemis.liste){
			// Si le centre du vaisseau est dans un ennemi
			if(!ennemi.mort &
					pointDansRectangle(vaisseau.position.x + VaisseauType1.DEMI_LARGEUR, vaisseau.position.y + VaisseauType1.DEMI_HAUTEUR, ennemi.getRectangleCollision())){
//				vaisseau.perdu();
//				return true;
			}
			for (Armes a : Armes.liste) {
				if (rectangleDansRectangle(a.getRectangleCollision(),	ennemi.getRectangleCollision())) {
					if (ennemi.touche(a.getForce())) {
						a.free();
						Armes.liste.removeValue(a, true);
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * On lui passe le point en bas à gauche du rectangle, return true si oui
	 * @param x
	 * @param y
	 * @param rectX
	 * @param rectY
	 * @param rectLarg
	 * @param rectHaut
	 * @return
	 */
	private static boolean pointDansRectangle(float x, float y, float rectX, float rectY, float rectLarg, float rectHaut) {
		 return rectX <= x & rectX + rectLarg >= x & rectY <= y & rectY + rectHaut >= y;
	}
	
	 private static boolean pointDansRectangle(float x, float y, Rectangle r) {
		 return r.x <= x & r.x + r.width >= x & r.y <= y & r.y + r.height >= y;
	}

	// True si touché, false sinon
    public static boolean pointDansRectangle(Vector2 p, Rectangle r) {
        return r.x <= p.x & r.x + r.width >= p.x & r.y <= p.y & r.y + r.height >= p.y;
    }
    
    /**
     * Si les rectangles se touchent. True si oui, false si non
     * @param r1
     * @param r2
     * @return
     */
    public static boolean rectangleDansRectangle(Rectangle r1, Rectangle r2) {
        if(r1.x < r2.x + r2.width & r1.x + r1.width > r2.x & r1.y < r2.y + r2.height & r1.y + r1.height > r2.y)
            return true;
        else
            return false;
    }
	public static float rotation(float angleRotation, float vitesseRotation) {
		return angleRotation + (Gdx.graphics.getDeltaTime() * vitesseRotation);
	}

}
