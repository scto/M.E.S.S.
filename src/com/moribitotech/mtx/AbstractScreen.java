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

package com.moribitotech.mtx;

import jeu.CSG;
import menu.Credits;
import menu.Menu;
import menu.OnClick;
import menu.ui.Bouton;
import assets.particules.Particules;
import bloom.Bloom;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public abstract class AbstractScreen implements Screen {

	protected Game game;
	private Array<Bouton> boutons = new Array<Bouton>();
	private Credits credits;
	protected SpriteBatch batch;
	public static final String PLAY = "Play!", SHIP = "Upgrade", OPTION = "Options", HIGHSCORE = "Highscores", EXIT = "Exit", BACK = "BACK", WEAPON_VOL = "WEAPON VOL  ", MOINS = "-", PLUS = "+", BRUITAGE_VOL = "EFFECTS VOL  ", MUSIQUE_VOL = "MUSIC VOL  ", INTENSITY = "INTENSITY : ", OTHER_WEAP = "Change weapon", TUTO = "Tutorial" , ACHIEVEMENT = "Achievements";
	protected Bloom bloom;
	public final static int PADDING = 10, LARGEUR_BOUTON = (CSG.LARGEUR_ECRAN / PADDING) * 8, HAUTEUR_BOUTON = CSG.HAUTEUR_ECRAN / 18;
	public final static int DEMI_LARGEUR_BOUTON = LARGEUR_BOUTON / 2, DEMI_HAUTEUR_BOUTON = HAUTEUR_BOUTON / 2;
	public final static int LARGEUR_PETITBOUTON = (CSG.LARGEUR_ECRAN / PADDING) * 3, HAUTEUR_PETITBOUTON = CSG.HAUTEUR_ECRAN / 18;
	public final static int LARGEUR_MINIBOUTON = LARGEUR_PETITBOUTON/2, HAUTEUR_MINIBOUTON = HAUTEUR_PETITBOUTON/2, decalageY = CSG.HAUTEUR_ECRAN/10;
	public static OrthographicCamera cam = new OrthographicCamera(CSG.LARGEUR_ECRAN, CSG.HAUTEUR_ECRAN);
	protected Bouton boutonBack;
	public boolean renderBackground = true;
	
	public AbstractScreen() {
		bloom = new Bloom();
	}

	public AbstractScreen(final Game game) {
		super();
		CSG.log("On entre dans AbstractScreen");
		CSG.reset();
		this.game = game;
		credits = new Credits();
		this.batch = CSG.batch;
		
		CSG.initBloom();
		cam.position.set(CSG.LARGEUR_ECRAN /2, CSG.HAUTEUR_ECRAN/2, 0);
		
		boutonBack = new Bouton(BACK, false, CSG.menuFontPetite, LARGEUR_PETITBOUTON, HAUTEUR_PETITBOUTON, CSG.LARGEUR_ECRAN / PADDING, HAUTEUR_BOUTON, this,
	    		new OnClick() {
					public void onClick() {
						Menu choix = new Menu(game);
						getGame().setScreen(choix);
						CSG.profilManager.persist();
					}
				}, true);
		Gdx.graphics.setVSync(true);
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
		if (renderBackground) {
			batch.begin();
			Particules.background(batch);
		}
		for (int i = 0; i < boutons.size; i++) {
			if (boutons.get(i) != null) boutons.get(i).draw(batch);
		}
		if (credits != null)
			credits.render(batch, delta);
		CSG.end();
	}

	public void setBackButtonActive(boolean isBackButtonActive) {		Gdx.input.setCatchBackKey(true);	}

	public void keyBackPressed() {	}

	public Game getGame() {		return game;	}

	public void setGame(Game game) {		this.game = game;	}

	@Override
	public void resize(int width, int height) {
		cam.position.set(width /2, height/2, 0);
	}

	@Override
	public void show() {		
		reset();
//		CSG.assetMan.reload(true);
    }

	public void reset() {		for (Bouton b : boutons) if (b != null) b.reset();	}
	@Override
	public void hide() {	}
	@Override
	public void pause() {	}
	@Override
	public void resume() {			CSG.assetMan.reload(true);	}
	@Override
	public void dispose() {			}

	public void touche() {
		for (Bouton b : boutons)
			b.setFade(true);
	}
}
