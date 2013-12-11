//package jeu;
//
//import static org.junit.Assert.assertTrue;
//
//import java.util.Random;
//
//import org.junit.Test;
//
//import com.badlogic.gdx.math.Rectangle;
//
//public class PhysiqueTest {
//
//	// temps avec ma methode :
//	// 7732241202, 8104189714, 8110887269, 7831529097, 8022294091
//	// 7609730109, 8064820000, 7832789039, 7533050536, 7566406699
//	// temps avec overlaps rectangle
//	// 7217568191, 7083345131, 7309198632, 7139509746, 6831461180
//	// test pour point in rectangle :
//	// moi
//	// 7131692914, 6915816015, 6869181560, 7229923109, 6720369555
//	// built in
//	// 6815732431, 7126713215, 694642016, 7550891966, 7097727003
//	
//	@Test
//	public void test() {
//		final int nbTest = 1000000;
//		Random r = new Random();
//		Long debut = System.nanoTime();
//		for (int i = 0; i < nbTest; i++) {
//			Rectangle r1 = new Rectangle(r.nextFloat() * 100f, r.nextFloat() * 100f, r.nextFloat() * 100, r.nextFloat() * 100);
////			System.out.println(Physique.pointDansRectangle(r.nextFloat() * 100, r.nextFloat() * 100, r1));
//			System.out.println(r1.contains(r.nextFloat() * 100, r.nextFloat() * 100));
////			System.out.println(Physique.rectangleDansRectangle(r1, r2));
////			System.out.println(r1.overlaps(r2));
////			assertTrue(Physique.rectangleDansRectangle(r1, r2) == r1.overlaps(r2));
//		}
//		System.out.println("Temps : " + (System.nanoTime() - debut) );
//	}
//
//}
