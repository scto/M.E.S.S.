/*******************************************************************************
 * Copyright 2012-Present, MoribitoTech
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package menu.screens;

import jeu.CSG;
import jeu.Physic;
import menu.Credits;
import menu.DeBaseMenu;
import menu.tuto.OnClick;
import menu.ui.Bouton;
import assets.AssetMan;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.Array;

import elements.particular.particles.Particles;

public abstract class AbstractScreen implements Screen {

	protected Game game;
	protected Array<Bouton> boutons = new Array<Bouton>();
	private Credits credits;
	public static final String PLAY = "Play!", SHIP = "Upgrade", OPTION = "Options", HIGHSCORE = "Highscores", EXIT = "Exit", BACK = "BACK", WEAPON_VOL = "WEAPON VOL  ", MOINS = "-", PLUS = "+", BRUITAGE_VOL = "EFFECTS VOL  ", MUSIQUE_VOL = "MUSIC VOL  ", INTENSITY = "INTENSITY : ", OTHER_WEAP = "Next weapon", TUTO = "Tutorial" , ACHIEVEMENT = "Achievements", SUPPORT_US = " Buy us a beer";
	public final static int PADDING = 11, LARGEUR_BOUTON = (CSG.screenWidth / PADDING) * 9, HAUTEUR_BOUTON = CSG.SCREEN_HEIGHT / 25;
	public final static int DEMI_LARGEUR_BOUTON = LARGEUR_BOUTON / 2, DEMI_HAUTEUR_BOUTON = HAUTEUR_BOUTON / 2;
	public final static int LARGEUR_PETITBOUTON = (CSG.screenWidth / PADDING) * 4, HAUTEUR_PETITBOUTON = CSG.SCREEN_HEIGHT / 18;
	public final static int LARGEUR_MINIBOUTON = LARGEUR_PETITBOUTON/2, HAUTEUR_MINIBOUTON = HAUTEUR_PETITBOUTON/2, decalageY = CSG.SCREEN_HEIGHT/10;
	public static OrthographicCamera cam = new OrthographicCamera(CSG.screenWidth, CSG.SCREEN_HEIGHT);
	protected Bouton boutonBack;
	public boolean renderBackground = true;
	private final Array<DeBaseMenu> enemies = new Array<DeBaseMenu>();
	private final float speed = CSG.SCREEN_HEIGHT / 6, offset = speed/15;
	private float trigger = 0;
	
	public AbstractScreen(final Game game) {
		super();
		this.game = game;
		credits = new Credits();
		
		CSG.initBloom();
		cam.position.set(CSG.screenWidth /2, CSG.SCREEN_HEIGHT/2, 0);
		
		boutonBack = new Bouton(BACK, false, CSG.menuFontPetite, LARGEUR_PETITBOUTON, HAUTEUR_PETITBOUTON, CSG.screenWidth / PADDING, HAUTEUR_BOUTON, this,
	    		new OnClick() {
					public void onClick() {
						changeMenu(new Menu(game));
						CSG.profilManager.persist();
					}
				}, true);
		Gdx.graphics.setVSync(true);
		CSG.reset();
	}

	public void setRenderBackground(boolean renderBackground) {
		this.renderBackground = renderBackground;
	}
	
	protected void ajout(Bouton bouton) {
		boutons.add(bouton);
	}
	
	
	@Override
	public void render(float delta) {
		CSG.begin(delta);
		
		if (Gdx.input.isKeyPressed(Keys.BACK)) {
			keyBackPressed();
		}
		CSG.batch.begin();
		if (renderBackground) {
			Particles.background(CSG.batch);
		}
		Particles.draw(CSG.batch);
		for (DeBaseMenu e : enemies) {
			CSG.batch.draw(e.getTexture(), e.pos.x, e.pos.y, e.getHalfWidth(), e.getHalfHeight(), e.getWidth(), e.getHeight(), 1, 1, e.dir.angle() + 90);
			Particles.smoke(e.pos.x + DeBaseMenu.DEMI_LARGEUR - e.dir.x/offset, e.pos.y + DeBaseMenu.DEMI_HAUTEUR - e.dir.y/offset, true);
			Physic.mvt(e.dir, e.pos, e.getWidth());
			if (e.pos.x + DeBaseMenu.DEMI_LARGEUR < CSG.DIXIEME_LARGEUR * 2) {
				e.desired.x = speed;
				e.desired.y = e.dir.y;
				e.steerScl = ((CSG.DIXIEME_LARGEUR * 2) - (e.pos.x + DeBaseMenu.DEMI_LARGEUR)) / (CSG.DIXIEME_LARGEUR/3);
			} else if (e.pos.x + DeBaseMenu.DEMI_LARGEUR > CSG.screenWidth - CSG.DIXIEME_LARGEUR * 2) {
				e.desired.x = -speed;
				e.desired.y = e.dir.y;
				e.steerScl = Math.abs(((e.pos.x + DeBaseMenu.DEMI_LARGEUR) - (CSG.screenWidth - CSG.DIXIEME_LARGEUR * 2))) / (CSG.DIXIEME_LARGEUR/3);
			} else if (e.pos.y + DeBaseMenu.DEMI_HAUTEUR < CSG.HEIGHT_DIV10 * 2.5f) {
				e.desired.x = e.dir.x;
				e.desired.y = speed;
				e.steerScl = ((CSG.HEIGHT_DIV10*2.5f) - (e.pos.y + DeBaseMenu.DEMI_HAUTEUR)) / (CSG.HEIGHT_DIV10/6);
			} else if (e.pos.y + DeBaseMenu.DEMI_HAUTEUR > CSG.SCREEN_HEIGHT - CSG.HEIGHT_DIV10*2.5f) {
				e.desired.x = e.dir.x;
				e.desired.y = -speed;
				e.steerScl = Math.abs(((e.pos.y + DeBaseMenu.DEMI_HAUTEUR) - (CSG.SCREEN_HEIGHT - CSG.HEIGHT_DIV10*2.5f))) / (CSG.HEIGHT_DIV10/6);
			}
			steer(e);
		}
		for (int i = 0; i < boutons.size; i++) {
			if (boutons.get(i) != null) boutons.get(i).draw(CSG.batch);
		}
		Particles.drawUi(CSG.batch);
		if (credits != null)
			credits.render(CSG.batch, delta);
		CSG.end();
		trigger += delta;
		if (trigger > 4) {
			DeBaseMenu e = DeBaseMenu.POOL.obtain();
			e.dir.x = 0;
			e.dir.y = -speed;
			e.dir.rotate(CSG.R.nextInt(360));
			enemies.add(e);
			trigger = -enemies.size;
		}
	}

	private void steer(DeBaseMenu e) {
		e.steer.x = e.desired.x - e.dir.x;
		e.steer.y = e.desired.y - e.dir.y;
		e.steer.nor();
		e.steer.scl(e.steerScl*2);
		e.dir.x += e.steer.x;
		e.dir.y += e.steer.y;
		e.dir.nor();
		e.dir.scl(speed);
	}

	public void changeMenu (Screen s) {
		DeBaseMenu.POOL.freeAll(enemies);
		enemies.clear();
		game.setScreen(s);
	}
	
	public void setBackButtonActive(boolean isBackButtonActive) {		Gdx.input.setCatchBackKey(true);	}

	public void keyBackPressed() {	}

	public void setGame(Game game) {		this.game = game;	}

	@Override
	public void resize(int width, int height) {
		cam.position.set(width /2, height/2, 0);
	}

	@Override
	public void show() {
		reset();
    }

	public void reset() {		for (Bouton b : boutons) if (b != null) b.reset();	}
	@Override
	public void hide() {
		
	}
	@Override
	public void pause() {
	}
	@Override
	public void resume() {
		CSG.assetMan.reload(true);
	}
	
	@Override
	public void dispose() {		
//		AssetMan.unload();
	}

	public void touche() {
		for (Bouton b : boutons)
			b.setFade(true);
	}
}
