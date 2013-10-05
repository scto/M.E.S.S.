package objets;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import menu.CSG;
import objets.ennemis.Ennemis;
import objets.ennemis.particuliers.nv1.DeBase;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.badlogic.gdx.math.Vector2;

public class PositionnementTest {

	@Mock
	DeBase e;
	@Test
	public void testRandomX() {
		MockitoAnnotations.initMocks(this);
		CSG.LARGEUR_ZONE_JEU = 1000;
		CSG.HAUTEUR_ECRAN = 800;
		e = new DeBase();
		for (int i = 0; i < 300; i++) {
			Positionnement.getEmplacementX(e);
			assertTrue( 0 < e.position.x);
			assertTrue( 1001 > e.position.x - e.getLargeur());
		}
	}
	
	@Test
	public void testxVersMilieu() {
		float min = 1000;
		float max = 0;
		MockitoAnnotations.initMocks(this);
		e.position = new Vector2(0,0);
		for (int i = 0; i < 300; i++) {
			Positionnement.hautMoyen(e);;
			if (e.position.x < min) min = e.position.x;
			if (e.position.x > max) max = e.position.x;
		}
		assertTrue(min > CSG.LARGEUR_ZONE_JEU / 4);
		assertTrue(max < (CSG.LARGEUR_ZONE_JEU / 4) * 3);
		assertEquals(e.position.y, CSG.HAUTEUR_ECRAN, 2);
	}

	@Test
	public void testMilieu() {
		MockitoAnnotations.initMocks(this);
		e.position = new Vector2(0,0);
		CSG.DEMI_LARGEUR_ZONE_JEU = CSG.LARGEUR_ZONE_JEU / 2;
		Positionnement.milieu(e);
		assertEquals((float) ((CSG.LARGEUR_ZONE_JEU / 2) - e.getDemiLargeur()), e.position.x, 2);
	}
	
	@Test
	public void testHautMoyen() {
		
	}
}
