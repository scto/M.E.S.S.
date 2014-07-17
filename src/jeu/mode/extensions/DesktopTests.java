package jeu.mode.extensions;

import jeu.CSG;
import jeu.level.Wave;
import jeu.mode.EndlessMode;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

import elements.generic.components.enemies.Merlin;
import elements.generic.enemies.Enemy;
import elements.particular.Player;
import elements.particular.bonuses.Bonus;
import elements.particular.bonuses.Shield;
import elements.particular.bonuses.TimeStop;
import elements.particular.bonuses.XP;

public class DesktopTests {
	
	
	private static boolean wave = false;
	private static float nextActivation = 0;

	public static void debug() {
		if (EndlessMode.oneToFour != 2)
			return;
		
		if (Gdx.input.isKeyPressed(Keys.END))	Merlin.GROUP.incantation.invoke();
		if (Gdx.input.isKeyPressed(Keys.END))	Merlin.GROUP3.incantation.invoke();
		if (Gdx.input.isKeyPressed(Keys.END))	Merlin.GROUP4.incantation.invoke();
		if (Gdx.input.isKeyPressed(Keys.Z)) 	Merlin.DE_BASE.incantation.invoke();
		if (Gdx.input.isKeyPressed(Keys.Z)) 	Merlin.DE_BASE3.incantation.invoke();
		if (Gdx.input.isKeyPressed(Keys.Z)) 	Merlin.DE_BASE4.incantation.invoke();
		if (Gdx.input.isKeyPressed(Keys.E))		Merlin.INSECT.incantation.invoke();
		if (Gdx.input.isKeyPressed(Keys.E))		Merlin.INSECT3.incantation.invoke();
		if (Gdx.input.isKeyPressed(Keys.E))		Merlin.INSECT4.incantation.invoke();
//		if (Gdx.input.isKeyPressed(Keys.R))		EndlessMode.addBonusStop();
		if (Gdx.input.isKeyPressed(Keys.R))		Merlin.VICIOUS.incantation.invoke();
		if (Gdx.input.isKeyPressed(Keys.I))		Merlin.SHOOTER.incantation.invoke();
//		if (Gdx.input.isKeyPressed(Keys.I))		Merlin.QUI_TIR3.incantation.invoke();
//		if (Gdx.input.isKeyPressed(Keys.I))		Merlin.QUI_TIR4.incantation.invoke();
		if (Gdx.input.isKeyPressed(Keys.O))		Merlin.ZIGZAG.incantation.invoke();
		if (Gdx.input.isKeyPressed(Keys.O))		Merlin.ZIGZAG3.incantation.invoke();
		if (Gdx.input.isKeyPressed(Keys.O))		Merlin.ZIGZAG4.incantation.invoke();
		if (Gdx.input.isKeyPressed(Keys.O))		Merlin.BOSS_SAT.incantation.invoke();
		if (Gdx.input.isKeyPressed(Keys.C))		Merlin.SHOOTER_FRAG.incantation.invoke();
//		if (Gdx.input.isKeyPressed(Keys.C))		Merlin.SHOOTER_FRAG3.incantation.invoke();
//		if (Gdx.input.isKeyPressed(Keys.C))		Merlin.SHOOTER_FRAG4.incantation.invoke();
		if (Gdx.input.isKeyPressed(Keys.U))		Merlin.BALL.incantation.invoke();
		if (Gdx.input.isKeyPressed(Keys.U))		Merlin.BALL3.incantation.invoke();
		if (Gdx.input.isKeyPressed(Keys.U))		Merlin.BALL4.incantation.invoke();
//		if (Gdx.input.isKeyPressed(Keys.O))		Ombrelle.ref.invoke();
		if (Gdx.input.isKeyPressed(Keys.P))		Merlin.BOULE_TIR_COTE_ROTATION.incantation.invoke();
//		if (Gdx.input.isKeyPressed(Keys.Q))		Merlin.ROUND_N_ROUND.incantation.invoke();
//		if (Gdx.input.isKeyPressed(Keys.Q))		Merlin.ROUND_N_ROUND3.incantation.invoke();
		if (Gdx.input.isKeyPressed(Keys.Q))		Merlin.ROUND_N_ROUND4.incantation.invoke();
//		if (Gdx.input.isKeyPressed(Keys.S))		Merlin.SHOOTER.incantation.invoke();
//		if (Gdx.input.isKeyPressed(Keys.S))		Merlin.SHOOTER3.incantation.invoke();
		if (Gdx.input.isKeyPressed(Keys.S))		Merlin.SHOOTER4.incantation.invoke();
		if (Gdx.input.isKeyPressed(Keys.D))		XP.POOL.obtain().init(400, 400, 300);
//		if (Gdx.input.isKeyPressed(Keys.F))		Merlin.DIABOLO.incantation.invoke();
//		if (Gdx.input.isKeyPressed(Keys.F))		Merlin.DIABOLO3.incantation.invoke();
		if (Gdx.input.isKeyPressed(Keys.F))		Merlin.DIABOLO4.incantation.invoke();
//		if (Gdx.input.isKeyPressed(Keys.J))		Merlin.KINDER.incantation.invoke();
//		if (Gdx.input.isKeyPressed(Keys.J))		Merlin.KINDER3.incantation.invoke();
		if (Gdx.input.isKeyPressed(Keys.J))		Merlin.KINDER4.incantation.invoke();
//		if (Gdx.input.isKeyPressed(Keys.M))		Merlin.CYLON.incantation.invoke();
		if (Gdx.input.isKeyPressed(Keys.M))		Merlin.CYLON3.incantation.invoke();
		if (Gdx.input.isKeyPressed(Keys.M))		Merlin.CYLON4.incantation.invoke();
//		if (Gdx.input.isKeyPressed(Keys.X))		Merlin.PLANE.incantation.invoke();
//		if (Gdx.input.isKeyPressed(Keys.X))		Merlin.PLANE3.incantation.invoke();
		if (Gdx.input.isKeyPressed(Keys.X))		Merlin.PLANE4.incantation.invoke();
//		if (Gdx.input.isKeyPressed(Keys.V))		Merlin.LASER.incantation.invoke();
//		if (Gdx.input.isKeyPressed(Keys.V))		Merlin.LASER3.incantation.invoke();
		if (Gdx.input.isKeyPressed(Keys.V))		Merlin.LASER4.incantation.invoke();
		if (Gdx.input.isKeyPressed(Keys.B))		Merlin.BOSS_SAT.incantation.invoke();
//		if (Gdx.input.isKeyPressed(Keys.N))		Merlin.CRUSADER.incantation.invoke();
//		if (Gdx.input.isKeyPressed(Keys.N))		Merlin.CRUSADER3.incantation.invoke();
		if (Gdx.input.isKeyPressed(Keys.N))		Merlin.CRUSADER4.incantation.invoke();
		if (Gdx.input.isKeyPressed(Keys.B))		Shield.POOL.obtain().init(CSG.R.nextFloat() * CSG.width, 200);
		
		
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
//		if (Gdx.input.isKeyPressed(Keys.F9)) 	wave = false;
		if (Gdx.input.isKeyPressed(Keys.F9))	Merlin.BOSS_MINE.incantation.invoke();
		if (Gdx.input.isKeyPressed(Keys.F10))	Merlin.BOSS_QUAD.incantation.invoke();
		if (Gdx.input.isKeyPressed(Keys.F11))	EndlessMode.invoque = true;
		if (Gdx.input.isKeyPressed(Keys.F12))	EndlessMode.invoque = false;
		if (Gdx.input.isKeyPressed(Keys.F3))	Bonus.LIST.add(TimeStop.POOL.obtain());
		if (Gdx.input.isKeyPressed(Keys.F4)) {
			Bonus.LIST.add(XP.POOL.obtain());
			CSG.profile.bfg = true;
		}
		if (Gdx.input.isKeyPressed(Keys.F5)) Score.score++;
		if (wave)
			runWave(Wave.LVL3_3);
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
