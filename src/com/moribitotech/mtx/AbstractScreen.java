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

import jeu.EndlessMode;
import menu.Bouton;
import menu.CSG;
import menu.Credits;
import menu.Menu;
import menu.OnClick;
import assets.particules.Particules;
import bloom.Bloom;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.swarmconnect.Swarm;

public abstract class AbstractScreen implements Screen {

	private Game game;
	private Array<Bouton> boutons = new Array<Bouton>();
	private Credits credits;
	protected final SpriteBatch batch;
	public static final String PLAY = "Play", SHIP = "Upgrade", OPTION = "Options", SWARM = "Highscores", EXIT = "Exit", BACK = "BACK", WEAPON_VOL = "WEAPON VOL  ", MOINS = "-", PLUS = "+", BRUITAGE_VOL = "EFFECTS VOL  ", MUSIQUE_VOL = "MUSIC VOL  ", INTENSITY = "INTENSITY : ", OTHER_WEAP = "Change weapon", TUTO = "Tutorial" ;
	protected Bloom bloom = new Bloom();
	public final static int PADDING = 10, LARGEUR_BOUTON = (CSG.LARGEUR_ECRAN / PADDING) * 8, HAUTEUR_BOUTON = CSG.HAUTEUR_ECRAN / 18;
	public final static int DEMI_LARGEUR_BOUTON = LARGEUR_BOUTON / 2, DEMI_HAUTEUR_BOUTON = HAUTEUR_BOUTON / 2;
	public final static int LARGEUR_PETITBOUTON = (CSG.LARGEUR_ECRAN / PADDING) * 3, HAUTEUR_PETITBOUTON = CSG.HAUTEUR_ECRAN / 18;
	public final static int LARGEUR_MINIBOUTON = LARGEUR_PETITBOUTON/2, HAUTEUR_MINIBOUTON = HAUTEUR_PETITBOUTON/2, decalageY = CSG.HAUTEUR_ECRAN/10;
	public static OrthographicCamera cam = new OrthographicCamera(CSG.LARGEUR_ECRAN, CSG.HAUTEUR_ECRAN);
	protected final Bouton boutonBack;
	public boolean renderBackground = true;
	
	public AbstractScreen(final Game game) {
		super();
		CSG.reset();
		this.game = game;
		credits = new Credits();
		this.batch = CSG.batch;
		bloom.setBloomIntesity(CSG.profil.intensiteBloom);
		cam.position.set(CSG.LARGEUR_ECRAN / 2, CSG.HAUTEUR_ECRAN / 2, 0);
		cam.update();
		batch.setProjectionMatrix(cam.combined);
		
		boutonBack = new Bouton(BACK, false, CSG.menuFontPetite, LARGEUR_PETITBOUTON, HAUTEUR_PETITBOUTON, CSG.LARGEUR_ECRAN / PADDING, HAUTEUR_BOUTON, this,
	    		new OnClick() {
					public void onClick() {
						Menu choix = new Menu(game);
						getGame().setScreen(choix);
						CSG.profilManager.persist();
					}
				}, true);
	}
	
	public void setRenderBackground(boolean renderBackground) {
		this.renderBackground = renderBackground;
	}
	
	protected void ajout(Bouton bouton) {
		boutons.add(bouton);
	}
	
	@Override
	public void render(float delta) {
		if (CSG.profil.bloom)	bloom.capture();
		else Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		EndlessMode.delta = delta;
		EndlessMode.delta15 = delta * 15;
		EndlessMode.maintenant += delta;
		if (Gdx.input.isKeyPressed(Keys.BACK)) {
			keyBackPressed();
		}
		
		if (renderBackground) {
			batch.begin();
			Particules.background(batch);
		}
		for (Bouton b : boutons) {
			if (b != null) b.draw(batch);
		}
		credits.render(batch, delta);
		batch.end();
		

		if (CSG.profil.bloom)	bloom.render();
	}

	public void setBackButtonActive(boolean isBackButtonActive) {		Gdx.input.setCatchBackKey(true);	}

	public void keyBackPressed() {	}

	public Game getGame() {		return game;	}

	public void setGame(Game game) {		this.game = game;	}

	@Override
	public void resize(int width, int height) {	}

	@Override
	public void show() {		reset();	}

	public void reset() {		for (Bouton b : boutons) if (b != null) b.reset();	}
	@Override
	public void hide() {	}
	@Override
	public void pause() {	}
	@Override
	public void resume() {			CSG.assetMan.reload();	}
	@Override
	public void dispose() {			}

	public void touche() {
		for (Bouton b : boutons)
			b.setFade(true);
	}
}
