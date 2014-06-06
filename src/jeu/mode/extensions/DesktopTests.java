package jeu.mode.extensions;

import jeu.CSG;
import jeu.mode.EndlessMode;
import assets.SoundMan;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;

import elements.generic.components.enemies.Merlin;
import elements.generic.enemies.Enemy;
import elements.particular.bonuses.Bonus;
import elements.particular.bonuses.BonusBouclier;
import elements.particular.bonuses.BonusStop;
import elements.particular.bonuses.XP;

public class DesktopTests {
	
	
	private static final Vector2 tmp = new Vector2(0, CSG.SCREEN_HEIGHT);

	public static void debug() {
	//	CSG.profil.NvArmeBalayage = 1;
	//	CSG.profil.NvArmeDeBase = 1;
	//	CSG.profil.NvArmeHantee = 1;
	//	CSG.profil.NvArmeTrois = 1;
		if (Gdx.input.justTouched()) {
//			Merlin.BOSS_QUAD.incantation.invoke();
		}
			
		
		if (EndlessMode.oneToFour != 2)
			return;
		if (Gdx.input.isKeyPressed(Keys.PAGE_DOWN))	{
			EndlessMode.cam.translate(0, 0, 1);
			EndlessMode.cam();
			SoundMan.playBruitage(SoundMan.bonusTaken);
		}
		
		if (Gdx.input.isKeyPressed(Keys.END))	Merlin.GROUP.incantation.invoke();
		if (Gdx.input.isKeyPressed(Keys.END))	Merlin.GROUP3.incantation.invoke();
		if (Gdx.input.isKeyPressed(Keys.END))	Merlin.GROUP4.incantation.invoke();
//		if (Gdx.input.isKeyPressed(Keys.A)) 	BossSat.ref.invoke();
		if (Gdx.input.isKeyPressed(Keys.Z)) 	Merlin.DE_BASE.incantation.invoke();
		if (Gdx.input.isKeyPressed(Keys.Z)) 	Merlin.DE_BASE3.incantation.invoke();
		if (Gdx.input.isKeyPressed(Keys.Z)) 	Merlin.DE_BASE4.incantation.invoke();
//		if (Gdx.input.isKeyPressed(Keys.E))		Merlin.ZIGZAG.incantation.invoke();
//		if (Gdx.input.isKeyPressed(Keys.E))		Merlin.ZIGZAG3.incantation.invoke();
		if (Gdx.input.isKeyPressed(Keys.E))		Merlin.ZIGZAG4.incantation.invoke();
		if (Gdx.input.isKeyPressed(Keys.R))		EndlessMode.addBonusStop();
		if (Gdx.input.isKeyPressed(Keys.T))		Merlin.QUI_TIR.incantation.invoke();
		if (Gdx.input.isKeyPressed(Keys.T))		Merlin.QUI_TIR3.incantation.invoke();
		if (Gdx.input.isKeyPressed(Keys.T))		Merlin.QUI_TIR4.incantation.invoke();
		if (Gdx.input.isKeyPressed(Keys.Y))		Merlin.QUI_TIR_TRIANGLE.incantation.invoke();
		if (Gdx.input.isKeyPressed(Keys.Y))		Merlin.QUI_TIR_TRIANGLE3.incantation.invoke();
		if (Gdx.input.isKeyPressed(Keys.Y))		Merlin.QUI_TIR_TRIANGLE4.incantation.invoke();
		if (Gdx.input.isKeyPressed(Keys.U))		Merlin.BALL.incantation.invoke();
		if (Gdx.input.isKeyPressed(Keys.U))		Merlin.BALL3.incantation.invoke();
		if (Gdx.input.isKeyPressed(Keys.U))		Merlin.BALL4.incantation.invoke();
//		if (Gdx.input.isKeyPressed(Keys.O))		Ombrelle.ref.invoke();
		if (Gdx.input.isKeyPressed(Keys.P))		Merlin.BOULE_TIR_COTE_ROTATION.incantation.invoke();
		if (Gdx.input.isKeyPressed(Keys.Q))		Merlin.ROUND_N_ROUND.incantation.invoke();
		if (Gdx.input.isKeyPressed(Keys.Q))		Merlin.ROUND_N_ROUND3.incantation.invoke();
		if (Gdx.input.isKeyPressed(Keys.Q))		Merlin.ROUND_N_ROUND4.incantation.invoke();
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
		if (Gdx.input.isKeyPressed(Keys.B))		BonusBouclier.POOL.obtain().init(CSG.R.nextFloat() * CSG.gameZoneWidth, 200);
		
		
		if (Gdx.input.isKeyPressed(Keys.F1))	EndlessMode.invicibility = true;
		if (Gdx.input.isKeyPressed(Keys.F2))	EndlessMode.invicibility = false;
		if (Gdx.input.isKeyPressed(Keys.F3))	EndlessMode.freeze = true;
		if (Gdx.input.isKeyPressed(Keys.F4))	EndlessMode.freeze = false;
		if (Gdx.input.isKeyPressed(Keys.F5))	EndlessMode.frameByFrame = true;
		if (Gdx.input.isKeyPressed(Keys.F6))	EndlessMode.frameByFrame = false;
		if (Gdx.input.isKeyPressed(Keys.F7))	Enemy.attackAllEnemies(Enemy.bomb);
		if (Gdx.input.isKeyPressed(Keys.F8))	Merlin.INSECT.incantation.invoke();
		if (Gdx.input.isKeyPressed(Keys.F8))	Merlin.INSECT3.incantation.invoke();
		if (Gdx.input.isKeyPressed(Keys.F8))	Merlin.INSECT4.incantation.invoke();
//		if (Gdx.input.isKeyPressed(Keys.F9))	BossMine.ref.invoke();
//		if (Gdx.input.isKeyPressed(Keys.F10))	BossQuad.ref.invoke();
		if (Gdx.input.isKeyPressed(Keys.F11))	Merlin.VICIOUS.incantation.invoke();
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
		if (Gdx.input.isKeyPressed(Keys.F5)) Score.score++;
	}

}
