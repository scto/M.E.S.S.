package menu.screens;

import jeu.CSG;
import jeu.Stats;
import jeu.Strings;
import assets.AssetMan;
import assets.animation.AnimationEnnemiDeBase;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import elements.particular.bonuses.BonusBombe;
import elements.particular.bonuses.BonusStop;
import elements.particular.bonuses.XP;
import elements.particular.particles.Particles;

public class Tuto extends AbstractScreen {
	
	private final float ligne = (CSG.HEIGHT_DIV10 / 3) * 2;
	private final float colonne = CSG.DIXIEME_LARGEUR / 2;
	private final String stay = "STAY A WHILE AND LISTEN";
	private final String enemy = "That's an enemy. One hit and you die !";
	private final String xp = "You can collect points to upgrade your ship";
	private final String bomb = "Use this bomb... to kill them all";
	private final String slow = "That's a nice bonus, it will freeze the time";
//	private final String stop = "That's even better... Try it !";

	public Tuto(Game game) {
		super(game);
		CSG.google.unlockAchievementGPGS(Strings.ACH_LISTEN);
		ajout(boutonBack);
		Gdx.input.setCatchBackKey(true);
		renderBackground = false;
	}

	@Override
	public void render(float delta) {
		CSG.begin(delta);
		CSG.batch.begin();
		Particles.background(CSG.batch);
		// SHIP
		ligne(AnimationEnnemiDeBase.getTexture(0), 3, Stats.LARGEUR_DE_BASE, Stats.HAUTEUR_DE_BASE, enemy);
		// XP
		CSG.batch.setColor(0,1,1,1);
		ligne(AssetMan.star, 9, (int)(XP.WIDTH * 1.5f), (int)(XP.WIDTH * 1.5f), xp);
		CSG.batch.setColor(1,1,1,1);
		// BOMB
		ligne(AssetMan.bomb, 5, BonusBombe.WIDTH, BonusBombe.WIDTH, bomb);

//		ligne(AssetMan.temps, 7, BonusTemps.WIDTH, BonusTemps.WIDTH, stop);

		ligne(AssetMan.stopBonus, 7, BonusStop.WIDTH, BonusStop.WIDTH, slow);
		                           
		// TITRE
		CSG.menuFontSmall.draw(CSG.batch, stay, CSG.screenHalfWidth - (CSG.menuFontSmall.getBounds(stay).width/2) , CSG.SCREEN_HEIGHT - 5);
		boutonBack.draw(CSG.batch);
		CSG.end();
		if (Gdx.input.isKeyPressed(Keys.BACK) || Gdx.input.isKeyPressed(Keys.ESCAPE)) {
			Menu menu = new Menu(game);
			game.setScreen(menu);
		}
	}

	private void ligne(TextureRegion r, int l, int largeur, int hauteur, String text) {
		CSG.batch.draw(r, colonne, ligne * l, largeur, hauteur);
		CSG.menuFontSmall.draw(CSG.batch, text, CSG.screenWidth - (colonne + CSG.menuFontSmall.getBounds(text).width) , (ligne*l) + (hauteur/2));
	}

	@Override
	public void resize(int width, int height) {	}
	@Override
	public void show() {	}
	@Override
	public void hide() {	}
	@Override
	public void pause() {	}
	@Override
	public void resume() {	}
	@Override
	public void dispose() {	}

}
