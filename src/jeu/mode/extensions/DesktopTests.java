package jeu.mode.extensions;

import jeu.CSG;
import jeu.mode.EndlessMode;
import assets.SoundMan;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

import elements.generic.enemies.Enemy;
import elements.generic.enemies.individual.bosses.BossMine;
import elements.generic.enemies.individual.bosses.BossQuad;
import elements.generic.enemies.individual.bosses.BossSat;
import elements.generic.enemies.individual.bosses.Ombrelle;
import elements.generic.enemies.individual.lvl1.Boule;
import elements.generic.enemies.individual.lvl1.Cylon;
import elements.generic.enemies.individual.lvl1.DeBase;
import elements.generic.enemies.individual.lvl1.Insecte;
import elements.generic.enemies.individual.lvl1.Kinder;
import elements.generic.enemies.individual.lvl1.Laser;
import elements.generic.enemies.individual.lvl1.Plane;
import elements.generic.enemies.individual.lvl1.PorteRaisin;
import elements.generic.enemies.individual.lvl1.QuiTir;
import elements.generic.enemies.individual.lvl1.QuiTirTriangle;
import elements.generic.enemies.individual.lvl1.Toupie;
import elements.generic.enemies.individual.lvl1.Vicious;
import elements.generic.enemies.individual.lvl1.ZigZag;
import elements.generic.enemies.individual.lvl2.BouleTirCoteRotation;
import elements.generic.enemies.individual.lvl3.BouleNv3;
import elements.generic.enemies.individual.lvl3.CylonNv3;
import elements.generic.enemies.individual.lvl3.DeBaseNv3;
import elements.generic.enemies.individual.lvl3.Group3;
import elements.generic.enemies.individual.lvl3.InsecteNv3;
import elements.generic.enemies.individual.lvl3.KinderNv3;
import elements.generic.enemies.individual.lvl3.LaserNv3;
import elements.generic.enemies.individual.lvl3.Plane3;
import elements.generic.enemies.individual.lvl3.PorteRaisinNv3;
import elements.generic.enemies.individual.lvl3.QuiTirNv3;
import elements.generic.enemies.individual.lvl3.QuiTirTriangle3;
import elements.generic.enemies.individual.lvl3.QuiTourneNv3;
import elements.generic.enemies.individual.lvl3.ToupieNv3;
import elements.generic.enemies.individual.lvl3.ZigZagNv3;
import elements.generic.enemies.individual.lvl4.BouleNv4;
import elements.generic.enemies.individual.lvl4.CylonNv4;
import elements.generic.enemies.individual.lvl4.DeBaseNv4;
import elements.generic.enemies.individual.lvl4.InsecteNv4;
import elements.generic.enemies.individual.lvl4.KinderNv4;
import elements.generic.enemies.individual.lvl4.LaserNv4;
import elements.generic.enemies.individual.lvl4.Plane4;
import elements.generic.enemies.individual.lvl4.PorteRaisinNv4;
import elements.generic.enemies.individual.lvl4.QuiTirNv4;
import elements.generic.enemies.individual.lvl4.QuiTirTriangle4;
import elements.generic.enemies.individual.lvl4.ToupieNv4;
import elements.generic.enemies.individual.lvl4.ZigZagNv4;
import elements.particular.bonuses.Bonus;
import elements.particular.bonuses.BonusBouclier;
import elements.particular.bonuses.BonusStop;
import elements.particular.bonuses.XP;

public class DesktopTests {
	
	

	public static void debug() {
			//		if (Gdx.input.isKeyPressed(Keys.A)) {
	//		Ennemis.LISTE.add(DeBase.pool.obtain());
	//	}
	//	CSG.profil.NvArmeBalayage = 1;
	//	CSG.profil.NvArmeDeBase = 1;
	//	CSG.profil.NvArmeHantee = 1;
	//	CSG.profil.NvArmeTrois = 1;
		if (Gdx.input.isKeyPressed(Keys.PAGE_DOWN))	{
			EndlessMode.cam.translate(0, 0, 1);
			EndlessMode.cam();
			SoundMan.playBruitage(SoundMan.bonusTaken);
		}
		if (Gdx.input.isKeyPressed(Keys.END))	Group3.ref.invoquer();
		if (Gdx.input.isKeyPressed(Keys.A)) 	BossSat.ref.invoquer();
		if (Gdx.input.isKeyPressed(Keys.Z)) 	DeBase.ref.invoquer();
		if (Gdx.input.isKeyPressed(Keys.Z)) 	DeBaseNv3.ref.invoquer();
		if (Gdx.input.isKeyPressed(Keys.Z)) 	DeBaseNv4.ref.invoquer();
		if (Gdx.input.isKeyPressed(Keys.E))		ZigZag.ref.invoquer();
		if (Gdx.input.isKeyPressed(Keys.E))		ZigZagNv3.ref.invoquer();
		if (Gdx.input.isKeyPressed(Keys.E))		ZigZagNv4.ref.invoquer();
		if (Gdx.input.isKeyPressed(Keys.R))		EndlessMode.addBonusStop();
		if (Gdx.input.isKeyPressed(Keys.T))		QuiTir.ref.invoquer();
		if (Gdx.input.isKeyPressed(Keys.T))		QuiTirNv3.ref.invoquer();
		if (Gdx.input.isKeyPressed(Keys.T))		QuiTirNv4.ref.invoquer();
		if (Gdx.input.isKeyPressed(Keys.Y))		QuiTirTriangle.ref.invoquer();
		if (Gdx.input.isKeyPressed(Keys.Y))		QuiTirTriangle3.ref.invoquer();
		if (Gdx.input.isKeyPressed(Keys.Y))		QuiTirTriangle4.ref.invoquer();
		if (Gdx.input.isKeyPressed(Keys.U))		Boule.ref.invoquer();
		if (Gdx.input.isKeyPressed(Keys.U))		BouleNv3.ref.invoquer();
		if (Gdx.input.isKeyPressed(Keys.U))		BouleNv4.ref.invoquer();
		if (Gdx.input.isKeyPressed(Keys.O))		Ombrelle.ref.invoquer();
		if (Gdx.input.isKeyPressed(Keys.P))		BouleTirCoteRotation.ref.invoquer();
		if (Gdx.input.isKeyPressed(Keys.Q))		Toupie.ref.invoquer();
		if (Gdx.input.isKeyPressed(Keys.Q))		ToupieNv3.ref.invoquer();
		if (Gdx.input.isKeyPressed(Keys.Q))		ToupieNv4.ref.invoquer();
		if (Gdx.input.isKeyPressed(Keys.D))		XP.POOL.obtain().init(400, 400, 300);
		if (Gdx.input.isKeyPressed(Keys.F))		QuiTourneNv3.ref.invoquer();
		if (Gdx.input.isKeyPressed(Keys.J))		Kinder.ref.invoquer();
		if (Gdx.input.isKeyPressed(Keys.J))		KinderNv3.ref.invoquer();
		if (Gdx.input.isKeyPressed(Keys.J))		KinderNv4.ref.invoquer();
		if (Gdx.input.isKeyPressed(Keys.M))		Cylon.ref.invoquer();
		if (Gdx.input.isKeyPressed(Keys.M))		CylonNv3.ref.invoquer();
		if (Gdx.input.isKeyPressed(Keys.M))		CylonNv4.ref.invoquer();
		if (Gdx.input.isKeyPressed(Keys.X))		Plane.ref.invoquer();
		if (Gdx.input.isKeyPressed(Keys.X))		Plane3.ref.invoquer();
		if (Gdx.input.isKeyPressed(Keys.X))		Plane4.ref.invoquer();
		if (Gdx.input.isKeyPressed(Keys.V))		Laser.ref.invoquer();
		if (Gdx.input.isKeyPressed(Keys.V))		LaserNv3.ref.invoquer();
		if (Gdx.input.isKeyPressed(Keys.V))		LaserNv4.ref.invoquer();
		if (Gdx.input.isKeyPressed(Keys.N))		PorteRaisin.ref.invoquer();
		if (Gdx.input.isKeyPressed(Keys.N))		PorteRaisinNv3.ref.invoquer();
		if (Gdx.input.isKeyPressed(Keys.N))		PorteRaisinNv4.ref.invoquer();
		if (Gdx.input.isKeyPressed(Keys.B))		BonusBouclier.POOL.obtain().init(400, 200);
		
		
		if (Gdx.input.isKeyPressed(Keys.F1))	EndlessMode.invicibility = true;
		if (Gdx.input.isKeyPressed(Keys.F2))	EndlessMode.invicibility = false;
		if (Gdx.input.isKeyPressed(Keys.F3))	EndlessMode.freeze = true;
		if (Gdx.input.isKeyPressed(Keys.F4))	EndlessMode.freeze = false;
		if (Gdx.input.isKeyPressed(Keys.F5))	Enemy.bombe();
		if (Gdx.input.isKeyPressed(Keys.F6))	Insecte.ref.invoquer();
		if (Gdx.input.isKeyPressed(Keys.F6))	InsecteNv3.ref.invoquer();
		if (Gdx.input.isKeyPressed(Keys.F6))	InsecteNv4.ref.invoquer();
		if (Gdx.input.isKeyPressed(Keys.F9))	BossMine.ref.invoquer();
		if (Gdx.input.isKeyPressed(Keys.F10))	BossQuad.ref.invoquer();
		if (Gdx.input.isKeyPressed(Keys.F11))	Vicious.ref.invoquer();
		if (Gdx.input.isKeyPressed(Keys.F12))	DeBaseNv4.ref.invoquer();
		if (Gdx.input.isKeyPressed(Keys.F3))	Bonus.LIST.add(BonusStop.POOL.obtain());
		if (Gdx.input.isKeyPressed(Keys.F4)) {
			Bonus.LIST.add(XP.POOL.obtain());
			CSG.profile.bfg = true;
		}
	//	if (Gdx.input.isKeyPressed(Keys.H))
	//		try {
	//			sendInfos();
	//		} catch (Exception e) {
	//			e.printStackTrace();
	//		}
		if (Gdx.input.isKeyPressed(Keys.F5)) EndlessMode.score++;
	}

}
