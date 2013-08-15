package menu;

import assets.SoundMan;
import bloom.Bloom;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.moribitotech.mtx.AbstractScreen;

public class MenuOptions extends AbstractScreen{

	private Bouton boutonSonArmeMoins;
	private Bouton boutonSonArmePlus;
	private Bouton boutonBruitagePlus;
	private Bouton boutonBack;
	private Bouton boutonBruitageMoins;
	private Bouton affichageBruitage;
	private Bouton affichageSonArme;
	private Bouton boutonMusiquePlus;
	private Bouton boutonMusiqueMoins;
	private Bouton affichageMusique;
	private Bouton boutonBloom;
	private Bouton boutonBloomIntensite;
//	private Bouton boutonParticules;
	private Bouton boutonControles;
	private SpriteBatch batch;
	private Bloom bloom;
	private int ligne1 = 2;
	private int ligne2 = 3;
	private int ligne3 = 4;
	private int ligne4 = 7;
	private int ligne5 = 8;
	private int ligne6 = 9;
	
	public MenuOptions(final Game game, SpriteBatch batch, JeuBackground jeuBackground, Bloom bloom) {
		super(game, "");
	    this.batch = batch;
	    this.bloom = bloom;
	    CSG.resetLists();
		// ********************************
		// ** ** ** BOUTON BACK ** ** **
		// ********************************
		boutonBack = new Bouton(CSG.menuFontPetite, false);
		boutonBack.setSize(Menu.LARGEUR_PETITBOUTON, Menu.HAUTEUR_PETITBOUTON);
		boutonBack.setPosition(CSG.LARGEUR_ECRAN / Menu.PADDING, Menu.HAUTEUR_BOUTON);
		boutonBack.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				clic(boutonBack);
				CSG.profilManager.persist();
				super.clicked(event, x, y);
			}
		});
		boutonBack.setTexte("BACK");
		getStage().addActor(boutonBack);
	    // *****************************
		// ** ** ** SON ARME ** ** **
		// *****************************
		affichageSonArme = new Bouton(CSG.menuFontPetite, true);
		affichageSonArme.setSize(Menu.LARGEUR_PETITBOUTON, Menu.HAUTEUR_PETITBOUTON);
		affichageSonArme.setPosition((CSG.LARGEUR_ECRAN / 2) - Menu.LARGEUR_PETITBOUTON/2,
				-Menu.decalageY +getStage().getHeight() - Menu.HAUTEUR_BOUTON * ligne1);
		updateTextSonArme();
		getStage().addActor(affichageSonArme);
		// *****************************
		// *****************************
		boutonSonArmeMoins = new Bouton(CSG.menuFont, false);
		boutonSonArmeMoins.setSize(Menu.LARGEUR_MINIBOUTON, Menu.HAUTEUR_MINIBOUTON);
		boutonSonArmeMoins.setPosition(CSG.LARGEUR_ECRAN / Menu.PADDING,
				-Menu.decalageY +getStage().getHeight() - Menu.HAUTEUR_BOUTON * ligne1 + Menu.HAUTEUR_MINIBOUTON / 2);
		boutonSonArmeMoins.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				CSG.profil.diminuerVolumeArme();
				updateTextSonArme();
			}
		});
		boutonSonArmeMoins.setTexte("-");
		getStage().addActor(boutonSonArmeMoins);
		// *****************************
		boutonSonArmePlus = new Bouton(CSG.menuFont, false);
		boutonSonArmePlus.setSize(Menu.LARGEUR_MINIBOUTON, Menu.HAUTEUR_MINIBOUTON);
		boutonSonArmePlus.setPosition(CSG.LARGEUR_ECRAN - (CSG.LARGEUR_ECRAN / Menu.PADDING) - Menu.LARGEUR_MINIBOUTON,
				-Menu.decalageY +getStage().getHeight() - Menu.HAUTEUR_BOUTON * ligne1 + Menu.HAUTEUR_MINIBOUTON / 2);
		boutonSonArmePlus.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				CSG.profil.augmenterVolumeArme();
				updateTextSonArme();
			}
		});
		boutonSonArmePlus.setTexte("+");
		getStage().addActor(boutonSonArmePlus);
		// *******************************************************************************************************
		// ************************ B R U I T A G E S ************************************************************
		// *******************************************************************************************************
		affichageBruitage = new Bouton(CSG.menuFontPetite, true);
		affichageBruitage.setSize(Menu.LARGEUR_PETITBOUTON, Menu.HAUTEUR_PETITBOUTON);
		affichageBruitage.setPosition((CSG.LARGEUR_ECRAN / 2) - Menu.LARGEUR_PETITBOUTON/2,
				-Menu.decalageY +getStage().getHeight() - (Menu.HAUTEUR_MINIBOUTON + Menu.HAUTEUR_BOUTON * ligne2) );
		updateTextBruitage();
		getStage().addActor(affichageBruitage);
		// *******************************************************************************************************
		boutonBruitageMoins = new Bouton(CSG.menuFont, false);
		boutonBruitageMoins.setSize(Menu.LARGEUR_MINIBOUTON, Menu.HAUTEUR_MINIBOUTON);
		boutonBruitageMoins.setPosition(CSG.LARGEUR_ECRAN / Menu.PADDING,
				-Menu.decalageY +- Menu.HAUTEUR_MINIBOUTON +getStage().getHeight() - Menu.HAUTEUR_BOUTON * ligne2 + Menu.HAUTEUR_MINIBOUTON / 2);
		boutonBruitageMoins.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				CSG.profil.diminuerVolumeBruitage();
				updateTextBruitage();
			}
		});
		boutonBruitageMoins.setTexte("-");
		getStage().addActor(boutonBruitageMoins);
		// ********************************************************************************************************
		boutonBruitagePlus = new Bouton(CSG.menuFont, false);
		boutonBruitagePlus.setSize(Menu.LARGEUR_MINIBOUTON, Menu.HAUTEUR_MINIBOUTON);
		boutonBruitagePlus.setPosition(CSG.LARGEUR_ECRAN - (CSG.LARGEUR_ECRAN / Menu.PADDING) - Menu.LARGEUR_MINIBOUTON,
				-Menu.decalageY +-Menu.HAUTEUR_MINIBOUTON +getStage().getHeight() - Menu.HAUTEUR_BOUTON * ligne2 + Menu.HAUTEUR_MINIBOUTON / 2);
		boutonBruitagePlus.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				CSG.profil.augmenterVolumeBruitage();
				updateTextBruitage();
			}
		});
		boutonBruitagePlus.setTexte("+");
		getStage().addActor(boutonBruitagePlus);
		// *******************************************************************************************************
		// ************************  M U S I Q U E S  ************************************************************
		// *******************************************************************************************************
		affichageMusique = new Bouton(CSG.menuFontPetite, true);
		affichageMusique.setSize(Menu.LARGEUR_PETITBOUTON, Menu.HAUTEUR_PETITBOUTON);
		affichageMusique.setPosition((CSG.LARGEUR_ECRAN / 2) - Menu.LARGEUR_PETITBOUTON/2,
				-Menu.decalageY +-Menu.HAUTEUR_MINIBOUTON*2 +getStage().getHeight() - Menu.HAUTEUR_BOUTON * ligne3);
		updateTextMusique();
		getStage().addActor(affichageMusique);
		// *******************************************************************************************************
		boutonMusiqueMoins = new Bouton(CSG.menuFont, false);
		boutonMusiqueMoins.setSize(Menu.LARGEUR_MINIBOUTON, Menu.HAUTEUR_MINIBOUTON);
		boutonMusiqueMoins.setPosition(CSG.LARGEUR_ECRAN / Menu.PADDING,
				-Menu.decalageY +-Menu.HAUTEUR_MINIBOUTON*2 +getStage().getHeight() - Menu.HAUTEUR_BOUTON * ligne3 + Menu.HAUTEUR_MINIBOUTON / 2);
		boutonMusiqueMoins.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				CSG.profil.diminuerVolumeMusique();
				updateTextMusique();
				SoundMan.checkMusique();
			}
		});
		boutonMusiqueMoins.setTexte("-");
		getStage().addActor(boutonMusiqueMoins);
		// ********************************************************************************************************
		boutonMusiquePlus = new Bouton(CSG.menuFont, false);
		boutonMusiquePlus.setSize(Menu.LARGEUR_MINIBOUTON, Menu.HAUTEUR_MINIBOUTON);
		boutonMusiquePlus.setPosition(CSG.LARGEUR_ECRAN - (CSG.LARGEUR_ECRAN / Menu.PADDING) - Menu.LARGEUR_MINIBOUTON,
				-Menu.decalageY +-Menu.HAUTEUR_MINIBOUTON*2 +getStage().getHeight() - Menu.HAUTEUR_BOUTON * ligne3 + Menu.HAUTEUR_MINIBOUTON / 2);
		boutonMusiquePlus.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				CSG.profil.augmenterVolumeMusique();
				updateTextMusique();
				SoundMan.checkMusique();
			}
		});
		boutonMusiquePlus.setTexte("+");
		getStage().addActor(boutonMusiquePlus);
		
		// *******************************************************************************************************
		// ******************************  B L O O M  ************************************************************
		// *******************************************************************************************************
		boutonBloom = new Bouton(CSG.menuFontPetite, false);
		boutonBloom.setSize(Menu.LARGEUR_PETITBOUTON, Menu.HAUTEUR_BOUTON);
		boutonBloom.setPosition(CSG.LARGEUR_ECRAN /2 - Menu.LARGEUR_BOUTON/2,
				-Menu.decalageY +getStage().getHeight() - Menu.HAUTEUR_BOUTON * ligne4 );
		boutonBloom.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if(Gdx.graphics.isGL20Available()){
					CSG.profil.bloom = !CSG.profil.bloom;
					updateBoutonBloomOnOff();
				}
			}
		});
		updateBoutonBloomOnOff();
		getStage().addActor(boutonBloom);
		// *******************************************************************************************************
		boutonBloomIntensite = new Bouton(CSG.menuFontPetite, false);
		boutonBloomIntensite.setSize(Menu.LARGEUR_PETITBOUTON, Menu.HAUTEUR_BOUTON);
		boutonBloomIntensite.setPosition(CSG.LARGEUR_ECRAN /2 + (Menu.LARGEUR_BOUTON/2 - Menu.LARGEUR_MINIBOUTON*2),
				-Menu.decalageY +getStage().getHeight() - Menu.HAUTEUR_BOUTON * ligne4 );
		boutonBloomIntensite.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				updateBoutonBloomIntensite();
			}
		});
		updateTexteBoutonBloomIntensite();
		getStage().addActor(boutonBloomIntensite);

		// *******************************************************************************************************
		// ************************   C O N T R O L E S   ********************************************************
		// *******************************************************************************************************
		boutonControles = new Bouton(CSG.menuFont, false);
		boutonControles.setSize(Menu.LARGEUR_BOUTON, Menu.HAUTEUR_BOUTON);
		boutonControles.setPosition(CSG.LARGEUR_ECRAN /2 - Menu.LARGEUR_BOUTON/2,
				-Menu.decalageY +-2*Menu.HAUTEUR_MINIBOUTON+getStage().getHeight() - Menu.HAUTEUR_BOUTON * ligne6 );
		boutonControles.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				CSG.profil.chgControle();
				updateTexteControle();
			}
		});
		updateTexteControle();
		getStage().addActor(boutonControles);
	}

	private void updateBoutonBloomIntensite() {
		if(Gdx.graphics.isGL20Available()){
			CSG.profil.intensiteBloom += 0.3;
			if(CSG.profil.intensiteBloom > 3.1f) CSG.profil.intensiteBloom = 0.3f;
			updateTexteBoutonBloomIntensite();
			bloom.setBloomIntesity(CSG.profil.intensiteBloom);
			CSG.profilManager.persist();
		}
	}

	private void updateTexteBoutonBloomIntensite() {
		if(CSG.profil.bloom) boutonBloomIntensite.setTexte("INTENSITY " + (int)(CSG.profil.intensiteBloom * 10));
		else boutonBloomIntensite.setTexte("---");
	}

	private void updateBoutonBloomOnOff() {
		if(CSG.profil.bloom) boutonBloom.setTexte("BLOOM ON");
		else boutonBloom.setTexte("BLOOM OFF");
		CSG.profilManager.persist();
	}

	protected void boutonBloom() {
		
	}

	protected void updateTexteControle() {
		boutonControles.setTexte(CSG.profil.getNomControle());
	}

	protected void updateTextMusique() {
		affichageMusique.setTexte("MUSIC  " + (int)(CSG.profil.volumeMusique*10));
		if (SoundMan.outsideNorm != null) {
			if(SoundMan.outsideNorm.isPlaying()) SoundMan.outsideNorm.setVolume(CSG.profil.volumeMusique);
			else SoundMan.playMusic();
		}
	}

	private void updateTextBruitage() {
		affichageBruitage.setTexte("VOLUME  " + (int)(CSG.profil.volumeBruitages*10));
	}

	private void updateTextSonArme() {
		affichageSonArme.setTexte("WEAPON VOL  " + (int)(CSG.profil.volumeArme*10));
	}
	
	protected void clic(Bouton bouton) {
		if(bouton != boutonSonArmeMoins) 		boutonSonArmeMoins.setRapetisser(true);
		if(bouton != boutonBruitagePlus) 		boutonBruitagePlus.setRapetisser(true);
		if(bouton != boutonBack) 				boutonBack.setRapetisser(true);
		if(bouton != boutonSonArmePlus) 		boutonSonArmePlus.setRapetisser(true);
		if(bouton != affichageSonArme) 			affichageSonArme.setRapetisser(true);
		if(bouton != boutonBruitageMoins) 		boutonBruitageMoins.setRapetisser(true);
		if(bouton != affichageBruitage) 		affichageBruitage.setRapetisser(true);
		if(bouton != affichageMusique) 			affichageMusique.setRapetisser(true);
		if(bouton != boutonMusiqueMoins) 		boutonMusiqueMoins.setRapetisser(true);
		if(bouton != boutonMusiquePlus) 		boutonMusiquePlus.setRapetisser(true);
		if(bouton != boutonBloom) 				boutonBloom.setRapetisser(true);
		if(bouton != boutonBloomIntensite)		boutonBloomIntensite.setRapetisser(true);
//		if(bouton != boutonParticules)			boutonParticules.setRapetisser(true);
		if(bouton != boutonControles)			boutonControles.setRapetisser(true);
		bouton.setVersDroite(true);
		Gdx.input.setInputProcessor(null);
	}

	@Override
	public void render(float delta) {
		if(CSG.profil.bloom)	bloom.capture();
		else					Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		CSG.renderBackground(batch);
//		Menu.jeuBackground.immortel();
		Menu.jeuBackground.render(batch, delta);
		batch.end();
		super.render(delta);
		if(CSG.profil.bloom)		bloom.render();
		boutonBack.setTexte("BACK");
		// ************************************
		if (boutonBack.aFini()) {
			Menu menu = new Menu(getGame());
			getGame().setScreen(menu);
		}
		test();
		if (Gdx.input.isKeyPressed(Keys.BACK)) {
			Menu menu = new Menu(getGame());
			getGame().setScreen(menu);
		}
	}

	private float intensite = 1;
	
	private void test() {
		if(Gdx.input.isKeyPressed(Keys.F)){
			intensite -= 0.1f;
			if(intensite < 0 ) intensite = 0;
			bloom.setBloomIntesity(intensite);
		}
		if(Gdx.input.isKeyPressed(Keys.G)){
			intensite += 0.1f;
			bloom.setBloomIntesity(intensite);
		}
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(getStage());
	}

	@Override
	public void hide() {
		Gdx.input.setInputProcessor(null);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}

