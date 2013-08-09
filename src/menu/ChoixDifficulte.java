package menu;

import vaisseaux.joueur.VaisseauType1;
import jeu.Endless;
import bloom.Bloom;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.moribitotech.mtx.AbstractScreen;

public class ChoixDifficulte extends AbstractScreen{
	
	public final static int PADDING = 10, decalageY = CSG.HAUTEUR_ECRAN/10;
	public final static int LARGEUR_BOUTON = (CSG.LARGEUR_ECRAN / PADDING) * 8, HAUTEUR_BOUTON = CSG.HAUTEUR_ECRAN / 18;
	public final static int DEMI_LARGEUR_BOUTON = LARGEUR_BOUTON / 2, DEMI_HAUTEUR_BOUTON = HAUTEUR_BOUTON / 2;
	public final static int LARGEUR_PETITBOUTON = (CSG.LARGEUR_ECRAN / PADDING) * 3, HAUTEUR_PETITBOUTON = CSG.HAUTEUR_ECRAN / 18;
	public final static int LARGEUR_MINIBOUTON = LARGEUR_PETITBOUTON/2, HAUTEUR_MINIBOUTON = HAUTEUR_PETITBOUTON/2;
	private Bouton bouton1, bouton2, bouton3;

	static JeuBackground jeuBackground = new JeuBackground();
	private Bloom bloom = new Bloom();
	private SpriteBatch batch = new SpriteBatch();
	private float temps = 0;
	private Credits credits;
	
	 public ChoixDifficulte(Game game, Credits credits) {
		 super(game,"");
		 setUpScreenElements();
		 this.credits = credits;
	 }
	  
	 public void setUpScreenElements() {
		temps = 0;
		Gdx.input.setCatchBackKey(true);
		// *******************************
		// ** ** ** PIECE OF CAKE ** ** **
		// *******************************
		bouton1 = new Bouton(CSG.menuFont, false);
		bouton1.setSize(LARGEUR_BOUTON, HAUTEUR_BOUTON);
		bouton1.setPosition(CSG.LARGEUR_ECRAN / PADDING, getStage().getHeight() - HAUTEUR_BOUTON * 4);
		bouton1.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {				clic(bouton1);			}
		});
		bouton1.setTexte("Piece of Cake");
		getStage().addActor(bouton1);
		// ******************************
		// ** ** **  LET'S ROCK  ** ** **
		// ******************************
		bouton2 = new Bouton(CSG.menuFont, false);
		bouton2.setSize(LARGEUR_BOUTON, HAUTEUR_BOUTON);
		bouton2.setPosition(CSG.LARGEUR_ECRAN / PADDING, getStage().getHeight() - HAUTEUR_BOUTON * 6);
		bouton2.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {				clic(bouton2);			}
		});
		bouton2.setTexte("Let's Rock");
		getStage().addActor(bouton2);
		// *********************************
		// ** ** **  COME GET SOME  ** ** **
		// *********************************
//		if (Endless.konamiCode) {
			bouton3 = new Bouton(CSG.menuFont, false);
			bouton3.setSize(LARGEUR_BOUTON, HAUTEUR_BOUTON);
			bouton3.setPosition(CSG.LARGEUR_ECRAN / PADDING, getStage().getHeight() - HAUTEUR_BOUTON * 8);
			bouton3.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {				clic(bouton3);			}
			});
			bouton3.setTexte("Come get some");
			getStage().addActor(bouton3);
//		}
		if (Gdx.app.getVersion() != 0) CSG.myRequestHandler.showAds(true);
		reset();
		bloom.setBloomIntesity(CSG.profil.intensiteBloom);
		Endless.delta = 0.015f; // Pour avoir le background qui defile
		// ** On met le vaisseau au milieu pour les autres menus
		VaisseauType1.position.y = VaisseauType1.HAUTEUR;
		VaisseauType1.position.x = CSG.DEMI_LARGEUR_ECRAN - VaisseauType1.DEMI_LARGEUR;
		VaisseauType1.centreX = VaisseauType1.position.x + VaisseauType1.DEMI_LARGEUR;
		VaisseauType1.centreY = VaisseauType1.position.y + VaisseauType1.DEMI_HAUTEUR;
	 }


	protected void clic(Bouton bouton) {
		if (bouton != bouton1)			bouton1.setFade(true);
		if (bouton != bouton2)			bouton2.setFade(true);
//		if (Endless.konamiCode)
			if (bouton != bouton3)			bouton3.setFade(true);
		bouton.setVersDroite(true);
		Gdx.input.setInputProcessor(null);
	}

	@Override
	public void keyBackPressed() {
		super.keyBackPressed();
		getGame().setScreen(new Endless(getGame(), batch, 1));
	}

	@Override
	public void render(float delta) {
		if (Gdx.input.isKeyPressed(Keys.R)) CSG.assetMan.reload();
		temps += delta;
		if (CSG.profil.bloom)	bloom.capture();
		else					Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		CSG.renderBackground(batch);
		credits.render(batch, delta);
		batch.end();
		super.render(delta);
		
		if (CSG.profil.bloom) bloom.render();
		if (bouton1.aFini()) 	lancerJeu(1);
		if (bouton2.aFini()) 	lancerJeu(2);
//		if (Endless.konamiCode)
			if (bouton3.aFini()) 	lancerJeu(3);

		if (Gdx.input.isKeyPressed(Keys.BACK) && temps > 3) Gdx.app.exit();
	}

	private void lancerJeu(int i) {
		Endless endless = new Endless(getGame(), batch, i);
		getGame().setScreen(endless);
	}

	 @Override
	public void reset() {
		bouton1.reset();
		bouton2.reset();
//		if (Endless.konamiCode)
		bouton3.reset();
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	@Override
	public void resize(int width, int height) {
		Gdx.input.setInputProcessor(getStage());
		super.resize(width, height);
	}

	@Override
	public void hide() {
		super.hide();
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		CSG.assetMan.reload();
		Gdx.input.setInputProcessor(getStage());
		super.resume();
	}
}