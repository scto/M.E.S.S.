//package jeu;
//
//import com.badlogic.gdx.Gdx;
//
//import physique.Physique;
//import menu.CSG;
//import vaisseaux.XP;
//import vaisseaux.armes.Armes;
//import vaisseaux.ennemis.Ennemis;
//import vaisseaux.joueur.VaisseauType1;
//
//public class CollisionTester implements Runnable{
//	private VaisseauType1 vaisseau;
//	private boolean finir = false;
//	private Thread deroulement;
//
//	public CollisionTester(VaisseauType1 vaisseau) {
//		this.vaisseau = vaisseau;
//	}
//	
//	public void demarrer() {
//		deroulement = new Thread(this);
//		deroulement.start();
//	}
//
//	@Override
//	public void run() {
//		while(true) {
//				Gdx.app.log("test","");
//			// ** On regarde si on prend de l'xp
//			for(int xp = 0; xp < XP.liste.size(); xp++){
//				if(Physique.pointDansRectangle(vaisseau.position.x + VaisseauType1.DEMI_LARGEUR, vaisseau.position.y + VaisseauType1.DEMI_HAUTEUR, XP.liste.get(xp).posX - XP.DEMI_LARGEUR_COLLISION, XP.liste.get(xp).posY - XP.DEMI_HAUTEUR_COLLISION, XP.LARGEUR_COLLISION, XP.HAUTEUR_COLLISION)){
//					CSG.profil.addXp(XP.liste.get(xp).valeur);
//					XP.liste.remove(xp);
//				}
//			}
//			// ** On regarde si le joueur se fait toucher
//			for(Armes a : Armes.listeTirsDesEnnemis){
//				if(Physique.pointDansRectangle(vaisseau.position.x + VaisseauType1.DEMI_LARGEUR, vaisseau.position.y + VaisseauType1.DEMI_HAUTEUR,
//						a.position.x, a.position.y,	a.getLargeur(), a.getHauteur())){
//	//				vaisseau.perdu();
//	//				return true;
//				}
//			}
//			// On parcourt la liste des ennemis
//			for(Ennemis ennemi : Ennemis.liste){
//				// Si le centre du vaisseau est dans un ennemi
//				if(!ennemi.mort &
//						Physique.pointDansRectangle(vaisseau.position.x + VaisseauType1.DEMI_LARGEUR, vaisseau.position.y + VaisseauType1.DEMI_HAUTEUR, ennemi.getRectangleCollision())){
//	//				vaisseau.perdu();
//	//				return true;
//				}
//				for (Armes a : Armes.liste) {
//					if (Physique.rectangleDansRectangle(a.getRectangleCollision(), ennemi.getRectangleCollision())) {
//						if (ennemi.touche(a.getForce())) {
//							a.free();
//							//Armes.liste.removeValue(a, true);
//						}
//					}
//				}
//			}
//			try {
//				Thread.sleep(200);
//			}
//			catch(InterruptedException e){
//				e.printStackTrace();
//			}
//		}
//	}
//
//	
//
//}
