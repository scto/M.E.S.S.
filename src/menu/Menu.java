package menu;

import vaisseaux.armes.joueur.ArmeAdd;
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
import com.swarmconnect.Swarm;

public class Menu extends AbstractScreen{
	
	public final static int PADDING = 10, LARGEUR_BOUTON = (CSG.LARGEUR_ECRAN / PADDING) * 8, HAUTEUR_BOUTON = CSG.HAUTEUR_ECRAN / 18;
	public final static int DEMI_LARGEUR_BOUTON = LARGEUR_BOUTON / 2, DEMI_HAUTEUR_BOUTON = HAUTEUR_BOUTON / 2;
	public final static int LARGEUR_PETITBOUTON = (CSG.LARGEUR_ECRAN / PADDING) * 3, HAUTEUR_PETITBOUTON = CSG.HAUTEUR_ECRAN / 18;
	public final static int LARGEUR_MINIBOUTON = LARGEUR_PETITBOUTON/2, HAUTEUR_MINIBOUTON = HAUTEUR_PETITBOUTON/2, decalageY = CSG.HAUTEUR_ECRAN/10;
	private Bouton boutonPlay, boutonXP, boutonOptions, boutonSwarm, boutonExit;
	static JeuBackground jeuBackground = new JeuBackground();
	private Bloom bloom = new Bloom();
	private SpriteBatch batch;
	private float temps = 0;
	private Credits credits;
	private int etapeCode = 0;
	
	 public Menu(Game game) {
		super(game,"");
		setUpScreenElements();
	 }
	  
	 public void setUpScreenElements() {
		batch = CSG.batch;
		ArmeAdd.determinerCadenceTir();
		temps = 0;
		Gdx.input.setCatchBackKey(true);
		credits = new Credits();
		
		// *****************************
		// ** ** ** BOUTON PLAY ** ** **
		// *****************************
		boutonPlay = new Bouton(CSG.menuFont, false);
		boutonPlay.setSize(LARGEUR_BOUTON, HAUTEUR_BOUTON);
		boutonPlay.setPosition(CSG.LARGEUR_ECRAN / PADDING, getStage().getHeight() - HAUTEUR_BOUTON * 4);
		boutonPlay.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {				clic(boutonPlay);			}
		});
		boutonPlay.setTexte("Play");
		getStage().addActor(boutonPlay);
		// *********************************
		// ** ** ** BOUTON VAISSEAU ** ** **
		// *********************************
		boutonXP = new Bouton(CSG.menuFont, false);
		boutonXP.setSize(LARGEUR_BOUTON, HAUTEUR_BOUTON);
		boutonXP.setPosition(CSG.LARGEUR_ECRAN / PADDING, getStage().getHeight() - HAUTEUR_BOUTON * 6);
		boutonXP.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {				clic(boutonXP);			}
		});
		boutonXP.setTexte("Ship");
		getStage().addActor(boutonXP);
		// ********************************
		// ** ** ** BOUTON OPTIONS ** ** **
		// ********************************
		boutonOptions = new Bouton(CSG.menuFont, false);
		boutonOptions.setSize(LARGEUR_BOUTON, HAUTEUR_BOUTON);
		boutonOptions.setPosition(CSG.LARGEUR_ECRAN / PADDING, getStage().getHeight() - HAUTEUR_BOUTON * 8);
		boutonOptions.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				clic(boutonOptions);
				super.clicked(event, x, y);
			}
		});
		boutonOptions.setTexte("Options");
		getStage().addActor(boutonOptions);
		// ********************************
		// ** ** **  BOUTON SWARM  ** ** **
		// ********************************
		boutonSwarm = new Bouton(CSG.menuFont, false);
		boutonSwarm.setSize(LARGEUR_BOUTON, HAUTEUR_BOUTON);
		boutonSwarm.setPosition(CSG.LARGEUR_ECRAN / PADDING, getStage().getHeight() - HAUTEUR_BOUTON * 10);
		boutonSwarm.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {				affichageSwarm();			}
		});
		boutonSwarm.setTexte("Highscores");
		getStage().addActor(boutonSwarm);
		
		// ********************************
		// ** ** **  BOUTON EXIT  ** ** **
		// ********************************
		boutonExit = new Bouton(CSG.menuFont, false);
		boutonExit.setSize(LARGEUR_BOUTON, HAUTEUR_BOUTON);
		boutonExit.setPosition(CSG.LARGEUR_ECRAN / PADDING, getStage().getHeight() - HAUTEUR_BOUTON * 15);
		boutonExit.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {				Gdx.app.exit();			}
		});
		boutonExit.setTexte("Exit");
		getStage().addActor(boutonExit);
		
		if(Gdx.app.getVersion() != 0)		CSG.myRequestHandler.showAds(true);
		reset();
		bloom.setBloomIntesity(CSG.profil.intensiteBloom);
		Endless.delta = 0.015f;
		// ** On met le vaisseau au milieu pour les autres menus
		VaisseauType1.position.y = VaisseauType1.HAUTEUR;
		VaisseauType1.position.x = CSG.DEMI_LARGEUR_ECRAN - VaisseauType1.DEMI_LARGEUR;
		VaisseauType1.centreX = VaisseauType1.position.x + VaisseauType1.DEMI_LARGEUR;
		VaisseauType1.centreY = VaisseauType1.position.y + VaisseauType1.DEMI_HAUTEUR;
		VaisseauType1.addDroite = true;
		VaisseauType1.addGauche = true;
		VaisseauType1.addDroite2 = true;
		VaisseauType1.addGauche2 = true;
		jeuBackground.add();
	 }

	protected void clic(Bouton bouton) {
		if (bouton != boutonOptions) boutonOptions.setFade(true);
		if (bouton != boutonSwarm) boutonSwarm.setFade(true);
		if (bouton != boutonPlay) boutonPlay.setFade(true);
		if (bouton != boutonXP) boutonXP.setFade(true);
		boutonExit.setFade(true);
		bouton.setVersDroite(true);
		Gdx.input.setInputProcessor(null);
	}

	private void affichageSwarm() {
		Swarm.showDashboard(); 
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
		Endless.maintenant += delta;
		credits.render(batch, delta);
		detectiopnKonamiCode(batch);
		batch.end();
		super.render(delta);

		if (CSG.profil.bloom) bloom.render();
		if (boutonPlay.aFini()) {
			ChoixDifficulte choix = new ChoixDifficulte(getGame(), credits);
			getGame().setScreen(choix);
		}
		if (boutonXP.aFini()) {
			MenuXP xp = new MenuXP(getGame(), batch, jeuBackground, bloom);
			getGame().setScreen(xp);
		}
		if (boutonOptions.aFini()) {
			MenuOptions options = new MenuOptions(getGame(), batch, jeuBackground, bloom);
			getGame().setScreen(options);
		}
		if (Gdx.input.isKeyPressed(Keys.BACK) && temps > 3) Gdx.app.exit();
	}

	/**
	 * Il a besoin du batch pour afficher le jeu background en cas de code valide
	 * @param batch
	 */
	 private void detectiopnKonamiCode(SpriteBatch batch) {
		switch(etapeCode){
		case 0:
		case 1:
			if (Gdx.input.justTouched() && Gdx.input.getY() < CSG.DIXIEME_HAUTEUR*2) 	etapeCode++;
			break;
		case 2:
		case 3:
			if (Gdx.input.justTouched() && Gdx.input.getY() > CSG.HAUTEUR_ECRAN - CSG.DIXIEME_HAUTEUR) 	etapeCode++;
			break;
		case 4:
		case 6:
			if (Gdx.input.justTouched() && Gdx.input.getX() < CSG.DEMI_LARGEUR_ECRAN) 	etapeCode++;
			break;
		case 5:
		case 7:
			if (Gdx.input.justTouched() && Gdx.input.getX() > CSG.DEMI_LARGEUR_ECRAN) 	etapeCode++;
			break;
		case 8:
			Endless.konamiCode = true;
			jeuBackground.render(batch, Endless.delta);
			break;
		}
	}

	@Override
	public void reset() {
		boutonOptions.reset();
		boutonPlay.reset();
		boutonXP.reset();
		boutonExit.reset();
		boutonSwarm.reset();
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

