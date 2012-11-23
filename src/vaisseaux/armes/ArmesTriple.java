package vaisseaux.armes;
//package armes;
//
//import physique.Physique;
//import main.CSG;
//
//import affichage.TexMan;
//
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.badlogic.gdx.math.Rectangle;
//import com.badlogic.gdx.math.Vector2;
//
//public class ArmesTriple extends Armes {
//	
//	// ** ** caracteristiques générales
//	public static final int LARGEUR= CSG.LARGEUR_ECRAN / 60;
//	public static final int DEMI_LARGEUR = LARGEUR/2;
//	private static final int HAUTEUR = LARGEUR;
//	private static final int DEMI_HAUTEUR = HAUTEUR / 2; 
//	private static final int VITESSE_MAX = 200;
//	public static final long CADENCETIR = 1200;
//	// ** ** variable utilitaire
//	private static int placeBalle = 0;
//
//	private ArmesTriple(float posX, float posY, Vector2 direction) {
//		super(posX, posY,direction);
//	}
//	
//	public ArmesTriple(float posX, float posY, Vector2 directionMilieu, int nbBalle){
//		super(posX, posY, directionMilieu);
//		switch(nbBalle){
//		case 2:
//			new ArmesDeBase(posX, posY, directionMilieu.rotate(10));
//			break;
//		case 3:
//			Vector2 vecteurModifie = new Vector2(directionMilieu);
//			new ArmesTriple(posX, posY, vecteurModifie.rotate(5));
//			new ArmesTriple(posX, posY,  vecteurModifie.rotate(-10));
//			break;
//		}
//	}
//
//	@Override
//	public void afficher(SpriteBatch batch) {
//		batch.draw(TexMan.balleDeBase , position.x, position.y, LARGEUR, HAUTEUR);
//	}
//
//	@Override
//	public boolean mouvementEtVerif() {
//		return Physique.mouvementDeBase(direction, position, VITESSE_MAX, HAUTEUR, LARGEUR);
//	}
//	@Override
//	public Rectangle getRectangleCollision() {
//		collision.set(position.x, position.y, LARGEUR, HAUTEUR);
//		return collision;
//	}
//}
