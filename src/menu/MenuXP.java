package menu;

import vaisseaux.armes.joueur.ArmeHantee;
import vaisseaux.armes.joueur.ArmesBalayage;
import vaisseaux.armes.joueur.ArmesDeBase;
import vaisseaux.armes.joueur.ArmesTrois;
import vaisseaux.joueur.VaisseauType1;
import bloom.Bloom;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.moribitotech.mtx.AbstractScreen;


public class MenuXP extends AbstractScreen{
	
//	private Bouton boutonWeaponPrev;
	private Bouton boutonWeaponNext;
	private Bouton boutonUpgrade;
	private Bouton boutonBack;
	private Bouton boutonCadence;
	private Bouton boutonUndo;
	private Bouton boutonXP;
	private SpriteBatch batch;
	private Bloom bloom;
	private static int prevXp = CSG.profil.xpDispo;
	private static int prevNvArmeDeBase = CSG.profil.NvArmeDeBase;
	private static int prevNvArmeBalayage = CSG.profil.NvArmeBalayage;
	private static int prevNvArmeHantee = CSG.profil.NvArmeHantee;
	private static int prevNvArmeTrois = CSG.profil.NvArmeTrois;
	private static int prevVitesse = CSG.profil.cadenceAdd;

	public MenuXP(final Game game, SpriteBatch batch, JeuBackground jeuBackground, Bloom bloom) {
		super(game, "");
	    this.batch = batch;
	    this.bloom = bloom;
	    this.bloom.setBloomIntesity(CSG.profil.intensiteBloom);
	    // ***********************************
		// **       B O U T O N   X P       **
		// ***********************************
		boutonXP = new Bouton(CSG.menuFont, false);
		boutonXP.setSize(Menu.LARGEUR_BOUTON, Menu.HAUTEUR_PETITBOUTON);
		boutonXP.setPosition((CSG.LARGEUR_ECRAN / 2) - Menu.LARGEUR_BOUTON/2, - Menu.decalageY + getStage().getHeight() - Menu.HAUTEUR_BOUTON * 2);
		updateTexteXp();
		getStage().addActor(boutonXP);
		// *****************************
		// ** ** ** BOUTON WEAPON ** ** **
		// *****************************
//		boutonWeaponPrev = new Bouton(CSG.menuFontPetite, false);
//		boutonWeaponPrev.setSize(Menu.LARGEUR_MINIBOUTON, Menu.HAUTEUR_MINIBOUTON);
//		boutonWeaponPrev.setPosition(CSG.LARGEUR_ECRAN / Menu.PADDING,
//				- Menu.decalageY + getStage().getHeight() - Menu.HAUTEUR_BOUTON * 2 + Menu.HAUTEUR_MINIBOUTON / 2);
//		boutonWeaponPrev.addListener(new ClickListener() {
//			@Override
//			public void clicked(InputEvent event, float x, float y) {
//				Menu.jeuBackground.getVaisseau().changerArme();
//				CSG.profilManager.persist();
//				updateTexteUpgrade();
//			}
//		});
//		boutonWeaponPrev.setTexte("PREV");
//		getStage().addActor(boutonWeaponPrev);
		// *****************************
		boutonWeaponNext = new Bouton(CSG.menuFont, false);
		boutonWeaponNext.setSize(Menu.LARGEUR_BOUTON, Menu.HAUTEUR_PETITBOUTON);
		boutonWeaponNext.setPosition((CSG.LARGEUR_ECRAN / 2) - Menu.LARGEUR_BOUTON/2, - Menu.decalageY + getStage().getHeight() - Menu.HAUTEUR_BOUTON * 4);
		boutonWeaponNext.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Menu.jeuBackground.getVaisseau().changerArme();
				CSG.profilManager.persist();
				updateTexteUpgrade();
			}
		});
		boutonWeaponNext.setTexte("Other weapon");
		getStage().addActor(boutonWeaponNext);
		// *****************************
		// ** ** ** BOUTON UPGRADE ** **
		// *****************************
		boutonUpgrade = new Bouton(CSG.menuFontPetite, false);
		boutonUpgrade.setSize(Menu.LARGEUR_PETITBOUTON, Menu.HAUTEUR_PETITBOUTON);
		boutonUpgrade.setPosition( CSG.LARGEUR_ECRAN - (CSG.LARGEUR_ECRAN / Menu.PADDING) - Menu.LARGEUR_PETITBOUTON,
				Menu.HAUTEUR_BOUTON);
		boutonUpgrade.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if(CSG.profil.getCoutUpArme() < CSG.profil.xpDispo){
					save();
					CSG.profil.upArme();
					updateTexteUpgrade();
					CSG.profilManager.persist();
					updateTexteXp();
					ajoutUndo();
				}
			}
		});
		updateTexteUpgrade();
		getStage().addActor(boutonUpgrade);
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
		boutonBack.setTexte("DONE");
		getStage().addActor(boutonBack);
		// ********************************
		// ** ** ** BOUTON CADENCE ** ** **
		// ********************************
		boutonCadence = new Bouton(CSG.menuFontPetite, false);
		boutonCadence.setSize(Menu.LARGEUR_PETITBOUTON, Menu.HAUTEUR_PETITBOUTON);
		boutonCadence.setPosition(CSG.LARGEUR_ECRAN - (CSG.LARGEUR_ECRAN / Menu.PADDING) - Menu.LARGEUR_PETITBOUTON,	Menu.HAUTEUR_BOUTON * 3);
		boutonCadence.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if (CSG.profil.getCoutCadenceAdd() < CSG.profil.xpDispo){
					save();
					CSG.profil.upCadenceAdd();
					CSG.profilManager.persist();
					updateTexteCadence();
					updateTexteXp();
					ajoutUndo();
				}
			}
		});
		updateTexteCadence();
		getStage().addActor(boutonCadence);
		VaisseauType1.position.y = CSG.HAUTEUR_ECRAN;
	}

	private void ajoutUndo() {
		if (boutonUndo == null) {
			boutonUndo = new Bouton(CSG.menuFontPetite, false);
			boutonUndo.setSize(Menu.LARGEUR_PETITBOUTON, Menu.HAUTEUR_PETITBOUTON);
			boutonUndo.setPosition(CSG.LARGEUR_ECRAN / Menu.PADDING, Menu.HAUTEUR_BOUTON * 3);
			boutonUndo.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {		undo();		}
			});
			boutonUndo.setTexte("UNDO");
			getStage().addActor(boutonUndo);
		}
	}

	private void updateTexteCadence() {
		boutonCadence.setTexte("ADD   ++ (" + CSG.profil.getCoutCadenceAdd() + ")");
	}

	private void updateTexteXp() {
		boutonXP.setTexte(CSG.profil.xpDispo + "xp");
	}

	protected void clic(Bouton bouton) {
//		if(bouton != boutonWeaponPrev) 	boutonWeaponPrev.setRapetisser(true);
		if (bouton != boutonUpgrade) 	boutonUpgrade.setRapetisser(true);
		if (bouton != boutonBack) 		boutonBack.setRapetisser(true);
		if (bouton != boutonWeaponNext) boutonWeaponNext.setRapetisser(true);
		if (bouton != boutonXP) 		boutonXP.setRapetisser(true);
		if (bouton != boutonCadence) 	boutonCadence.setRapetisser(true);
		if (boutonUndo != null && bouton != boutonUndo) 		boutonUndo.setRapetisser(true);
		bouton.setVersDroite(true);
		Gdx.input.setInputProcessor(null);
	}
	
	protected void save() {
		prevXp = CSG.profil.xpDispo;
		prevNvArmeDeBase = CSG.profil.NvArmeDeBase;
		prevNvArmeBalayage = CSG.profil.NvArmeBalayage;
		prevVitesse = CSG.profil.cadenceAdd;
		prevNvArmeHantee = CSG.profil.NvArmeHantee;
		prevNvArmeTrois = CSG.profil.NvArmeTrois;
		updateTexteXp();
		updateTexteCadence();
		updateTexteUpgrade();
	}

	protected void undo() {
		CSG.profil.NvArmeDeBase = prevNvArmeDeBase;
		CSG.profil.NvArmeBalayage = prevNvArmeBalayage;
		CSG.profil.xpDispo = prevXp;
		CSG.profil.cadenceAdd = prevVitesse;
		CSG.profil.NvArmeHantee = prevNvArmeHantee;
		CSG.profil.NvArmeTrois = prevNvArmeTrois;
		updateTexteUpgrade();
		updateTexteCadence();
		updateTexteXp();
	}
	
	@Override
	public void render(float delta) {
		if (CSG.profil.bloom)	bloom.capture();
		else					Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		CSG.renderBackground(batch);
		Menu.jeuBackground.render(batch, delta);
		batch.end();
		super.render(delta);
		if (CSG.profil.bloom) bloom.render();
		boutonBack.setTexte("DONE");
		// ************************************
		if (boutonBack.aFini()) {
			Menu menu = new Menu(getGame());
			getGame().setScreen(menu);
		}
		if (Gdx.input.isKeyPressed(Keys.BACK)) {
			Menu menu = new Menu(getGame());
			getGame().setScreen(menu);
		}
		
		// DEBUG
		if (Gdx.input.isKeyPressed(Keys.NUM_1)) CSG.profil.NvArmeTrois = 1;
		if (Gdx.input.isKeyPressed(Keys.NUM_2)) CSG.profil.NvArmeTrois = 2;
		if (Gdx.input.isKeyPressed(Keys.NUM_3)) CSG.profil.NvArmeTrois = 3;
		if (Gdx.input.isKeyPressed(Keys.NUM_4)) CSG.profil.NvArmeTrois = 4;
		if (Gdx.input.isKeyPressed(Keys.NUM_5)) CSG.profil.NvArmeTrois = 5;
		if (Gdx.input.isKeyPressed(Keys.NUM_6)) CSG.profil.NvArmeTrois = 6;
		ArmesTrois.updateDimensions();
	}
	
	 @Override
	public void reset() {
		boutonBack.reset();
		boutonBack.setTexte("DONE");
		boutonUpgrade.reset();
		updateTexteUpgrade();
		boutonWeaponNext.reset();
//		boutonWeaponPrev.setTexte(">");
//		boutonWeaponPrev.reset();
//		boutonWeaponPrev.setTexte("<");
		Gdx.input.setInputProcessor(getStage());
	}

	@Override
	public void resize(int width, int height) {
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
	public void pause() {	}

	@Override
	public void resume() {	}

	@Override
	public void dispose() {	}

	private void updateTexteUpgrade() {
		if (CSG.profil.getCoutUpArme() == 43200) boutonUpgrade.setTexte("LEVEL MAX");
		else boutonUpgrade.setTexte("WEAPON ++ (" + CSG.profil.getCoutUpArme() + ")");
	}

}
