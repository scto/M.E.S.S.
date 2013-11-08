//package jeu;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertTrue;
//import menu.CSG;
//import objets.joueur.VaisseauJoueur;
//
//import org.junit.Test;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import com.badlogic.gdx.math.Rectangle;
//import com.badlogic.gdx.math.Vector2;
//
//public class PhysiqueTest {
//
//	@Test
//	public void testPointIn() {
//		// Le rectangle va de 1 � 3 dans chaque axe
//		final Rectangle r = new Rectangle(1, 1, 2, 2); 
//		
//		pointRectangle(r, new Vector2(r.x-1,r.y), false);
//		pointRectangle(r, new Vector2(r.x-1,r.y-1), false);
//		pointRectangle(r, new Vector2(r.x,r.y-1), false);
//		pointRectangle(r, new Vector2(r.x,r.y), true);
//		pointRectangle(r, new Vector2(r.x+1,r.y), true);
//		pointRectangle(r, new Vector2(r.x,r.y+1), true);
//		pointRectangle(r, new Vector2(r.x+1,r.y+1), true);
//		pointRectangle(r, new Vector2(r.x+3,r.y+1), false);
//		pointRectangle(r, new Vector2(r.x+1,r.y+3), false);
//	}
//
//	private void pointRectangle(Rectangle r, Vector2 point, boolean excepted) {
//		if (excepted) {
//			assertTrue(Physique.pointDansCarre(point.x, point.y, r.x, r.y, r.width));
//			assertTrue(Physique.pointDansRectangle(point, r));
//			assertTrue(Physique.pointDansRectangle(point.x, point.y, r));
//			assertTrue(Physique.pointDansRectangle(point.x, point.y, r.x, r.y, r.width, r.height));
//		} else {
//			assertFalse(Physique.pointDansCarre(point.x, point.y, r.x, r.y, r.width));
//			assertFalse(Physique.pointDansRectangle(point, r));
//			assertFalse(Physique.pointDansRectangle(point.x, point.y, r));
//			assertFalse(Physique.pointDansRectangle(point.x, point.y, r.x, r.y, r.width, r.height));
//		}
//	}
//	
//	@Mock
//	CSG csg;
//	@Mock
//	Profil profil;
//	@Test
//	public void testGetAngleVersJoueur() {
//		MockitoAnnotations.initMocks(this);
//		CSG.profil = profil;
//		float demiLargeur = 50;
//		float demiHauteur = 150;
//		Vector2 position = new Vector2(100 - demiLargeur, 150 - demiHauteur);
//		VaisseauJoueur.position.x = 100;
//		VaisseauJoueur.position.y = 100;
//		// Le centre de l'arme se trouve en 100 150, le vaisseau en 100 100 avec 0 de largeur donc son centre aussi est en 100 100, l'angle attendu est de 270 car le 0 est � droite
//		assertEquals(270f, Physique.getAngleVersJoueur(position, demiLargeur, demiHauteur), 1f);
//		VaisseauJoueur.position.y  = 200;
//		assertEquals(90f, Physique.getAngleVersJoueur(position, demiLargeur, demiHauteur), 1f);
//		VaisseauJoueur.position.x = 50;
//		VaisseauJoueur.position.y = 150;
//		assertEquals(180f, Physique.getAngleVersJoueur(position, demiLargeur, demiHauteur), 1f);
//		VaisseauJoueur.position.x = 151;
//		assertEquals(1f, Physique.getAngleVersJoueur(position, demiLargeur, demiHauteur), 1f);
//		VaisseauJoueur.position.x = 150;
//		VaisseauJoueur.position.y = 200;
//		assertEquals(45f, Physique.getAngleVersJoueur(position, demiLargeur, demiHauteur), 1f);
//	}
//	
//
//	/**
//	 * Le mock est fait par celui avant
//	 */
//	@Test
//	public void testCollisionAdds() {
//		VaisseauJoueur.addDroite = true;
//		VaisseauJoueur.addGauche = true;
//		VaisseauJoueur.addDroite2 = true;
//		VaisseauJoueur.addGauche2 = true;
//		
//		VaisseauJoueur.centreAddDroite1X = 10;
//		VaisseauJoueur.centreAdds1Y = 10;
//		VaisseauJoueur.centreAddGauche1X = 30;
//		VaisseauJoueur.centreAddDroite2X = 0;
//		VaisseauJoueur.centreAdds2Y = 0;
//		VaisseauJoueur.centreAddGauche2X = 20;
//		assertTrue(VaisseauJoueur.addGauche);
//		assertTrue(VaisseauJoueur.addGauche2);
//		assertTrue(VaisseauJoueur.addDroite);
//		assertTrue(VaisseauJoueur.addDroite2);
//		// droite
//		assertTrue(Physique.testCollisionAdds(new Vector2(9,9), 2));
//		assertFalse(VaisseauJoueur.addDroite);
//		VaisseauJoueur.addDroite = true;
//		assertTrue(Physique.testCollisionAdds(new Vector2(9,9), 2, 3));
//		assertTrue(VaisseauJoueur.addGauche);
//		assertTrue(VaisseauJoueur.addGauche2);
//		assertFalse(VaisseauJoueur.addDroite);
//		assertTrue(VaisseauJoueur.addDroite2);
//		// gauche
//		assertTrue(Physique.testCollisionAdds(new Vector2(27,9), 4));
//		assertFalse(VaisseauJoueur.addGauche);
//		VaisseauJoueur.addGauche = true;
//		assertTrue(Physique.testCollisionAdds(new Vector2(27,9), 4, 3));
//		assertFalse(VaisseauJoueur.addGauche);
//		assertTrue(VaisseauJoueur.addGauche2);
//		assertFalse(VaisseauJoueur.addDroite);
//		assertTrue(VaisseauJoueur.addDroite2);
//		// gauche 2
//		assertTrue(Physique.testCollisionAdds(new Vector2(18,0), 3));
//		assertFalse(VaisseauJoueur.addGauche2);
//		VaisseauJoueur.addGauche2 = true;
//		assertTrue(Physique.testCollisionAdds(new Vector2(18,0), 3, 3));
//		assertFalse(VaisseauJoueur.addGauche);
//		assertFalse(VaisseauJoueur.addGauche2);
//		assertFalse(VaisseauJoueur.addDroite);
//		assertTrue(VaisseauJoueur.addDroite2);
//		// droite 2
//		assertTrue(Physique.testCollisionAdds(new Vector2(00,0), 3));
//		assertFalse(VaisseauJoueur.addDroite2);
//		VaisseauJoueur.addDroite2 = true;
//		assertTrue(Physique.testCollisionAdds(new Vector2(00,0), 3, 3));
//		assertFalse(VaisseauJoueur.addGauche);
//		assertFalse(VaisseauJoueur.addGauche2);
//		assertFalse(VaisseauJoueur.addDroite);
//		assertFalse(VaisseauJoueur.addDroite2);
//		
//		assertFalse(Physique.testCollisionAdds(new Vector2(0,0), 3));
//		assertFalse(Physique.testCollisionAdds(new Vector2(00,0), 3, 3));
//		VaisseauJoueur.addDroite = true;
//		VaisseauJoueur.addGauche = true;
//		VaisseauJoueur.addDroite2 = true;
//		VaisseauJoueur.addGauche2 = true;
//		assertFalse(Physique.testCollisionAdds(new Vector2(55,55), 3));
//		assertFalse(Physique.testCollisionAdds(new Vector2(55,55), 3, 4));
//	}
//	
//	@Test
//	public void testRectangleCollision() {
//		assertTrue(Physique.rectangleDansRectangle(new Rectangle(10,15,5,5), new Rectangle(10,10,20,20)));
//		assertTrue(Physique.rectangleDansRectangle(new Rectangle(10,15,5,5), new Rectangle(14,19,1,1)));
//		assertTrue(Physique.rectangleDansRectangle(new Rectangle(10,15,5,5), new Rectangle(9,14,2,2)));
//		
//		assertFalse(Physique.rectangleDansRectangle(new Rectangle(10,15,5,5), new Rectangle(11,21,1,1)));
//		assertFalse(Physique.rectangleDansRectangle(new Rectangle(10,15,5,5), new Rectangle(11,1,1,1)));
//		assertFalse(Physique.rectangleDansRectangle(new Rectangle(10,15,5,5), new Rectangle(21,17,1,1)));
//		assertFalse(Physique.rectangleDansRectangle(new Rectangle(10,15,5,5), new Rectangle(1,17,1,1)));
//	}
//	
//	@Test
//	public void testTjrsAffiche() {
//		CSG.LARGEUR_ZONE_JEU = 1500;
//		CSG.HAUTEUR_ECRAN = 800;
//		tjrsAfficher(new Vector2(100, 100), true, 20, 30);
//		tjrsAfficher(new Vector2(1499, 100), true, 40, 50);
//		tjrsAfficher(new Vector2(-39, 100), true, 40, 50);
//		tjrsAfficher(new Vector2(100, -39), true, 40, 40);
//		tjrsAfficher(new Vector2(100, 839), true, 40, 40);
//		
//		tjrsAfficher(new Vector2(-41, 100), false, 40, 40);
//		tjrsAfficher(new Vector2(1501, 100), false, 40, 40);
//		tjrsAfficher(new Vector2(100, -41), false, 40, 40);
//		tjrsAfficher(new Vector2(100, 841), false, 40, 40);
//	}
//
//	private void tjrsAfficher(Vector2 pos, boolean dedans, int largeur, int hauteur) {
//		if (dedans) {
//			assertTrue(Physique.toujoursAfficher(pos, largeur));
//			assertTrue(Physique.toujoursAfficher(pos.x, pos.y, largeur));
//			assertTrue(Physique.toujoursAfficher(pos, largeur, hauteur));
//		} else {
//			assertFalse(Physique.toujoursAfficher(pos, largeur));
//			assertFalse(Physique.toujoursAfficher(pos.x, pos.y, largeur));
//			assertFalse(Physique.toujoursAfficher(pos, largeur, hauteur));
//		}
//	}
//}
