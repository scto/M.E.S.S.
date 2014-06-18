package jeu.mode.extensions;

import jeu.CSG;
import jeu.Stats;
import jeu.mode.EndlessMode;
import assets.SoundMan;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;

import elements.generic.Player;
import elements.generic.components.enemies.Merlin;
import elements.generic.enemies.Enemy;
import elements.generic.enemies.Wave;
import elements.generic.enemies.individual.demo.Prototype;
import elements.generic.enemies.individual.lvl3.Basic3;
import elements.particular.bonuses.Bonus;
import elements.particular.bonuses.Shield;
import elements.particular.bonuses.TimeStop;
import elements.particular.bonuses.XP;

public class DesktopTests {
	
	
	private static final Vector2 tmp = new Vector2(0, CSG.SCREEN_HEIGHT);
	private static boolean wave = false;
	private static float nextActivation = 0;
	private static float stx, sty;

	public static void debug() {
	//	CSG.profil.NvArmeBalayage = 1;
	//	CSG.profil.NvArmeDeBase = 1;
	//	CSG.profil.NvArmeHantee = 1;
	//	CSG.profil.NvArmeTrois = 1;
//		if (Gdx.input.justTouched()) {
//			Merlin.DE_BASE.incantation.invoke().setPosition(new Vector2(200, 400));;
//		}
//		System.out.println("====================================");
//		for (Enemy e : Enemy.LIST) {
//			System.out.println(e + "\t" + e.getPosition());
//		}
//		System.out.println("====================================");
		
		if (EndlessMode.oneToFour != 2)
			return;
		if (Gdx.input.isKeyPressed(Keys.PAGE_DOWN))	{
			EndlessMode.cam.translate(0, 0, 1);
			EndlessMode.cam();
			SoundMan.playBruitage(SoundMan.bonusTaken);
		}
		// POC
		if (Gdx.input.isKeyPressed(Keys.A)) {
//			LeftToRightCurve d = LeftToRightCurve.POOL.obtain();
			Basic3 d = Basic3.POOL.obtain();
			CSG.tmpPos.x = Stats.U;
			CSG.tmpPos.y = CSG.halfHeight;
			d.setPosition(CSG.tmpPos);
		}
		
		if (Gdx.input.isKeyPressed(Keys.END))	Merlin.GROUP.incantation.invoke();
		if (Gdx.input.isKeyPressed(Keys.END))	Merlin.GROUP3.incantation.invoke();
		if (Gdx.input.isKeyPressed(Keys.END))	Merlin.GROUP4.incantation.invoke();
		if (Gdx.input.isKeyPressed(Keys.Z)) 		Merlin.DE_BASE.incantation.invoke();
		if (Gdx.input.isKeyPressed(Keys.Z)) 		Merlin.DE_BASE3.incantation.invoke();
		if (Gdx.input.isKeyPressed(Keys.Z)) 		Merlin.DE_BASE4.incantation.invoke();
//		if (Gdx.input.isKeyPressed(Keys.E)) {
//			Merlin.ZIGZAG.incantation.invoke().pos.x = 100;
//		}
		if (Gdx.input.isKeyPressed(Keys.E)) {
			CSG.tmpDir.x = 25*Stats.u;
			CSG.tmpDir.y = 0;
			CSG.tmpDir.rotate(CSG.R.nextFloat() * 360);
			float x = CSG.tmpDir.x;
			float y = CSG.tmpDir.y;
			addDemo(Prototype.POOL.obtain(), x, y, true, false);
			addDemo(Prototype.POOL.obtain(), x, y, false, false);
		}
		if (Gdx.input.isKeyPressed(Keys.R)) {
			addDemo(Prototype.POOL.obtain(), stx, sty, true, false);
			addDemo(Prototype.POOL.obtain(), stx, sty, false, false);
			addDemo(Prototype.POOL.obtain(), stx, sty, true, true);
			addDemo(Prototype.POOL.obtain(), stx, sty, false, true);
		}
		if (Gdx.input.isKeyPressed(Keys.T)) {
			CSG.tmpDir.x = 25*Stats.u;
			CSG.tmpDir.y = 0;
			CSG.tmpDir.rotate(CSG.R.nextFloat() * 360);
			stx = CSG.tmpDir.x;
			sty = CSG.tmpDir.y;
		}
		
//		if (Gdx.input.isKeyPressed(Keys.E))		Merlin.ZIGZAG4.incantation.invoke();
//		if (Gdx.input.isKeyPressed(Keys.R))		EndlessMode.addBonusStop();
		if (Gdx.input.isKeyPressed(Keys.I))		Merlin.QUI_TIR.incantation.invoke();
//		if (Gdx.input.isKeyPressed(Keys.I))		Merlin.QUI_TIR3.incantation.invoke();
//		if (Gdx.input.isKeyPressed(Keys.I))		Merlin.QUI_TIR4.incantation.invoke();
		if (Gdx.input.isKeyPressed(Keys.C))		Merlin.QUI_TIR_TRIANGLE.incantation.invoke();
		if (Gdx.input.isKeyPressed(Keys.C))		Merlin.QUI_TIR_TRIANGLE3.incantation.invoke();
		if (Gdx.input.isKeyPressed(Keys.C))		Merlin.QUI_TIR_TRIANGLE4.incantation.invoke();
		if (Gdx.input.isKeyPressed(Keys.U))		Merlin.BALL.incantation.invoke();
		if (Gdx.input.isKeyPressed(Keys.U))		Merlin.BALL3.incantation.invoke();
		if (Gdx.input.isKeyPressed(Keys.U))		Merlin.BALL4.incantation.invoke();
//		if (Gdx.input.isKeyPressed(Keys.O))		Ombrelle.ref.invoke();
		if (Gdx.input.isKeyPressed(Keys.P))		Merlin.BOULE_TIR_COTE_ROTATION.incantation.invoke();
		if (Gdx.input.isKeyPressed(Keys.Q))		Merlin.ROUND_N_ROUND.incantation.invoke().setPosition(new Vector2(200, 400));
		if (Gdx.input.isKeyPressed(Keys.Q))		Merlin.ROUND_N_ROUND3.incantation.invoke().setPosition(new Vector2(220, 400));
		if (Gdx.input.isKeyPressed(Keys.Q))		Merlin.ROUND_N_ROUND4.incantation.invoke().setPosition(new Vector2(240, 400));
		if (Gdx.input.isKeyPressed(Keys.S))		Merlin.QUI_TIR.incantation.invoke().setPosition(new Vector2(240, 400));
		if (Gdx.input.isKeyPressed(Keys.S))		Merlin.QUI_TIR3.incantation.invoke().setPosition(new Vector2(240, 400));
		if (Gdx.input.isKeyPressed(Keys.S))		Merlin.QUI_TIR4.incantation.invoke().setPosition(new Vector2(240, 400));
		if (Gdx.input.isKeyPressed(Keys.D))		XP.POOL.obtain().init(400, 400, 300);
		if (Gdx.input.isKeyPressed(Keys.F))		Merlin.QUI_TOURNE.incantation.invoke();
		if (Gdx.input.isKeyPressed(Keys.F))		Merlin.QUI_TOURNE3.incantation.invoke();
		if (Gdx.input.isKeyPressed(Keys.F))		Merlin.QUI_TOURNE4.incantation.invoke();
		if (Gdx.input.isKeyPressed(Keys.J))		Merlin.KINDER.incantation.invoke();
		if (Gdx.input.isKeyPressed(Keys.J))		Merlin.KINDER3.incantation.invoke();
		if (Gdx.input.isKeyPressed(Keys.J))		Merlin.KINDER4.incantation.invoke();
		if (Gdx.input.isKeyPressed(Keys.M))		Merlin.CYLON.incantation.invoke();
		if (Gdx.input.isKeyPressed(Keys.M))		Merlin.CYLON3.incantation.invoke();
		if (Gdx.input.isKeyPressed(Keys.M))		Merlin.CYLON4.incantation.invoke();
		if (Gdx.input.isKeyPressed(Keys.X))		Merlin.PLANE.incantation.invoke();
		if (Gdx.input.isKeyPressed(Keys.X))		Merlin.PLANE3.incantation.invoke();
		if (Gdx.input.isKeyPressed(Keys.X))		Merlin.PLANE4.incantation.invoke();
		if (Gdx.input.isKeyPressed(Keys.V))		Merlin.LASER.incantation.invoke();
		if (Gdx.input.isKeyPressed(Keys.V))		Merlin.LASER3.incantation.invoke();
		if (Gdx.input.isKeyPressed(Keys.V))		Merlin.LASER4.incantation.invoke();
		if (Gdx.input.isKeyPressed(Keys.N))		Merlin.CRUSADER.incantation.invoke();
		if (Gdx.input.isKeyPressed(Keys.N))		Merlin.CRUSADER3.incantation.invoke();
		if (Gdx.input.isKeyPressed(Keys.N))		Merlin.CRUSADER4.incantation.invoke();
		if (Gdx.input.isKeyPressed(Keys.B))		Shield.POOL.obtain().init(CSG.R.nextFloat() * CSG.gameZoneWidth, 200);
		
		
		if (Gdx.input.isKeyPressed(Keys.F1))	EndlessMode.invicibility = true;
		if (Gdx.input.isKeyPressed(Keys.F2))	EndlessMode.invicibility = false;
		if (Gdx.input.isKeyPressed(Keys.F3))	EndlessMode.freeze = true;
		if (Gdx.input.isKeyPressed(Keys.F4))	EndlessMode.freeze = false;
		if (Gdx.input.isKeyPressed(Keys.F5))	Player.nextShot += 150;
		if (Gdx.input.isKeyPressed(Keys.F6))	Player.nextShot -= 150;
		if (Gdx.input.isKeyPressed(Keys.F7))	Enemy.attackAllEnemies(Enemy.bomb);
		if (Gdx.input.isKeyPressed(Keys.F8)) {
			wave = true;
			nextActivation = 0;
		}
		if (Gdx.input.isKeyPressed(Keys.F9)) 	wave = false;
//		if (Gdx.input.isKeyPressed(Keys.F9))	BossMine.ref.invoke();
//		if (Gdx.input.isKeyPressed(Keys.F10))	BossQuad.ref.invoke();
		if (Gdx.input.isKeyPressed(Keys.F11))	EndlessMode.invoque = true;
		if (Gdx.input.isKeyPressed(Keys.F12))	EndlessMode.invoque = false;
		if (Gdx.input.isKeyPressed(Keys.F3))	Bonus.LIST.add(TimeStop.POOL.obtain());
		if (Gdx.input.isKeyPressed(Keys.F4)) {
			Bonus.LIST.add(XP.POOL.obtain());
			CSG.profile.bfg = true;
		}
		if (Gdx.input.isKeyPressed(Keys.F5)) Score.score++;
		if (wave)
			runWave(Wave.lvl3_3);
	}

	private static void addDemo(Enemy e, float x, float y, boolean rotation, boolean scale) {
		e.init();
		e.pos.x = CSG.gameZoneHalfWidth;
		e.pos.y = CSG.halfHeight;
		e.dir.x = x;
		e.dir.y = y;
		e.setShotDir(scale);
		e.setWay(rotation);
		Enemy.LIST.add(e);
	}

	private static void runWave(Wave wave) {
		if (wave.active)
			wave.mightSpawn();
		else if (nextActivation < EndlessMode.now) { 
			wave.activate();
			nextActivation = EndlessMode.now + 15;
		}
	}

}
