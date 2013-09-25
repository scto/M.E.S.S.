package menu;

import jeu.Stats;
import vaisseaux.bonus.BonusBombe;
import vaisseaux.bonus.BonusStop;
import vaisseaux.bonus.BonusTemps;
import vaisseaux.bonus.XP;
import vaisseaux.ennemis.particuliers.nv1.DeBase;
import assets.AssetMan;
import assets.animation.AnimationEnnemiDeBase;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class Tuto implements Screen {
	
	private final Game game;
	private final GL20 gl = Gdx.graphics.getGL20();
	private final float ligne = (CSG.DIXIEME_HAUTEUR / 3) * 2;
	private final float colonne = CSG.DIXIEME_LARGEUR / 2;
	private final String stay = "STAY A WHILE AND LISTEN";
	private final String enemy = "That's an enemy";
	private final String xp = "You can collect points to upgrade your ship";
	private final String bomb = "Use this bomb... to kill them all";
	private final String slow = "That's a nice bonus, it will freeze the time";
	private final String stop = "That's even better... Try it !";
	private final BitmapFont font;

	public Tuto(Game game) {
		this.game = game;
		font = new BitmapFont(Gdx.files.internal("default.fnt"), false);
		font.setScale(Stats.U / 20);
		font.setColor(.82f, .82f, 0.1f, 1);
	}

	@Override
	public void render(float delta) {
		gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		CSG.batch.begin();
		CSG.renderBackground(CSG.batch);
		// SHIP
		CSG.batch.draw(AnimationEnnemiDeBase.getTexture(0), colonne, ligne, DeBase.LARGEUR, DeBase.HAUTEUR);
		font.draw(CSG.batch, enemy, CSG.LARGEUR_ECRAN - (colonne + font.getBounds(enemy).width) , ligne + DeBase.DEMI_HAUTEUR);
		// XP
		CSG.batch.draw(AssetMan.XP, colonne, ligne * 3, XP.LARGEUR, XP.LARGEUR);
		font.draw(CSG.batch, xp, CSG.LARGEUR_ECRAN - (colonne + font.getBounds(xp).width) , ligne * 3 + XP.LARGEUR  / 2);
		// BOMB
		CSG.batch.draw(AssetMan.bombe, colonne, ligne * 5, BonusBombe.LARGEUR, BonusBombe.LARGEUR);
		font.draw(CSG.batch, bomb, CSG.LARGEUR_ECRAN - (colonne + font.getBounds(bomb).width) , ligne * 5 + BonusBombe.LARGEUR  / 2);
		// STOP
		CSG.batch.draw(AssetMan.temps, colonne, ligne * 9, BonusStop.LARGEUR, BonusStop.LARGEUR);
		font.draw(CSG.batch, stop, CSG.LARGEUR_ECRAN - (colonne + font.getBounds(stop).width) , ligne * 7 + BonusStop.LARGEUR  / 2);
		// SLOW
		CSG.batch.draw(AssetMan.bonusetoile, colonne, ligne * 7, BonusTemps.LARGEUR, BonusTemps.LARGEUR);
		font.draw(CSG.batch, slow, CSG.LARGEUR_ECRAN - (colonne + font.getBounds(slow).width) , ligne * 9 + BonusTemps.LARGEUR  / 2);
		// TITRE
		font.draw(CSG.batch, stay, CSG.DEMI_LARGEUR_ECRAN - (font.getBounds(stay).width/2) , CSG.HAUTEUR_ECRAN - 5);
		CSG.batch.end();
		
		if (Gdx.input.isKeyPressed(Keys.BACK) || Gdx.input.isKeyPressed(Keys.ESCAPE)) {
			Menu menu = new Menu(game);
			game.setScreen(menu);
		}
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
