package menu.screens;

import menu.JeuBackground;
import menu.tuto.OnClick;
import menu.ui.Bouton;
import jeu.CSG;
import jeu.EndlessMode;
import jeu.Profil;
import jeu.Strings;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

import elements.generic.Player;
import elements.generic.weapons.player.ArmeAdd;
import elements.particular.particles.Particles;
import elements.particular.particles.individual.weapon.GreenAddParticle;


public class MenuXP extends AbstractScreen{
	
	private Bouton boutonUpgrade, boutonCadence, boutonUndo;
	private final Bouton boutonXP;
	private final JeuBackground jeu = new JeuBackground();
	private static short prevNvArmeDeBase = CSG.profile.NvArmeDeBase;
	private static short prevNvArmeBalayage = CSG.profile.NvArmeBalayage;
	private static short prevNvArmeHantee = CSG.profile.NvArmeHantee;
	private static short prevNvArmeTrois = CSG.profile.lvlPinkWeapon;
	private static short prevNvArmeSun = CSG.profile.NvArmeSun;
	private static short prevVitesse = CSG.profile.cadenceAdd;
	private static int prevXp = CSG.profile.xpDispo;

	public MenuXP(final Game game) {
		super(game);
		Gdx.graphics.setVSync(true);
		Gdx.input.setCatchBackKey(true);
		ajout(boutonBack);
		// **       B O U T O N   X P       **
		boutonXP = new Bouton(CSG.profile.xpDispo + "xp", false, CSG.menuFont, LARGEUR_BOUTON, HAUTEUR_PETITBOUTON,
				(CSG.screenWidth / 2) - Menu.LARGEUR_BOUTON/2, - Menu.decalageY + CSG.SCREEN_HEIGHT - Menu.HAUTEUR_BOUTON * 2, this, new OnClick() {
					public void onClick() {	}}, false);
		ajout(boutonXP);
		// ** ** ** BOUTON WEAPON ** ** **
		ajout(new Bouton(OTHER_WEAP, false, CSG.menuFont, LARGEUR_BOUTON, HAUTEUR_PETITBOUTON,
				(CSG.screenWidth / 2) - Menu.LARGEUR_BOUTON/2, - Menu.decalageY + CSG.SCREEN_HEIGHT - Menu.HAUTEUR_BOUTON * 4, this, new OnClick() {
					public void onClick() {	
						Player.changerArme();
						CSG.profilManager.persist();
						updateTexteUpgrade();
					}}, false));
		// ** ** ** BOUTON UPGRADE ** **
		boutonUpgrade = new Bouton("", false, CSG.menuFontPetite, LARGEUR_PETITBOUTON, HAUTEUR_PETITBOUTON,
				CSG.screenWidth - (CSG.screenWidth / Menu.PADDING) - Menu.LARGEUR_PETITBOUTON,	Menu.HAUTEUR_BOUTON, this, new OnClick() {
					public void onClick() {	
						if(CSG.profile.getCoutUpArme() <= CSG.profile.xpDispo){
							save();
							CSG.profile.upArme();
							updateTexteUpgrade();
							CSG.profilManager.persist();
							updateTexteXp();
							ajoutUndo();
						}
					}}, false);
		ajout(boutonUpgrade);
		updateTexteUpgrade();
		// ** ** ** BOUTON CADENCE ** ** **
		boutonCadence = new Bouton("", false, CSG.menuFontPetite, Menu.LARGEUR_PETITBOUTON, Menu.HAUTEUR_PETITBOUTON,
				CSG.screenWidth - (CSG.screenWidth / Menu.PADDING) - Menu.LARGEUR_PETITBOUTON,	Menu.HAUTEUR_BOUTON * 3, this, new OnClick() {
					public void onClick() {
						if (CSG.profile.getCoutCadenceAdd() <= CSG.profile.xpDispo){
							save();
							CSG.profile.upCadenceAdd();
							CSG.profilManager.persist();
							updateTexteCadence();
							updateTexteXp();
							ajoutUndo();
						}
					}}, false);
		updateTexteCadence();
		ajout(boutonCadence);
		Player.POS.y = CSG.SCREEN_HEIGHT / 3;
		Player.POS.x = (CSG.screenWidth / 2) - Player.DEMI_LARGEUR; 
		Player.rajoutAdd();
		Player.rajoutAdd();
		Player.rajoutAdd();
		Player.rajoutAdd();
		setRenderBackground(false);
	}

	@Override
	public void render(float delta) {
		if (CSG.R.nextBoolean())
			EndlessMode.explosions = 1;
		CSG.begin(delta);
		if (Gdx.input.isKeyPressed(Keys.BACK)) {
			keyBackPressed();
		}
		CSG.batch.begin();
		Particles.background(CSG.batch);
		jeu.render(CSG.batch, delta);
		for (int i = 0; i < boutons.size; i++) {
			if (boutons.get(i) != null) boutons.get(i).draw(CSG.batch);
		}
		Particles.drawUi(CSG.batch);
		CSG.menuFontPetite.draw(CSG.batch, "Weapon level : " + CSG.profile.getArmeSelectionnee().nv(), 4, 4 + CSG.menuFontPetite.getBounds("W").height);
		CSG.end();
		EndlessMode.majDeltas();
		EndlessMode.fps = Gdx.graphics.getFramesPerSecond();
		EndlessMode.perf = EndlessMode.fps / 6;
//		Particles.background(CSG.batch);
	}
	
	@Override
	public void keyBackPressed() {
		changeMenu(new Menu(game));
		CSG.profilManager.persist();
	}
	private void ajoutUndo() {
		if (boutonUndo == null) {
			CSG.google.unlockAchievementGPGS(Strings.ACH_FAVORITE_SHOP);
			boutonUndo = new Bouton("UNDO", false, CSG.menuFontPetite, Menu.LARGEUR_PETITBOUTON, Menu.HAUTEUR_PETITBOUTON, CSG.screenWidth / Menu.PADDING, Menu.HAUTEUR_BOUTON * 3, this,
			new OnClick()  {
				public void onClick() {		undo();		}
			}, false);
			ajout(boutonUndo);
		} else {
			ajout(boutonUndo);
		}
			
	}

	private void updateTexteCadence() {
		boutonCadence.setTexte("Drones (" + CSG.profile.getCoutCadenceAdd() + ")");
	}

	private void updateTexteXp() {
		boutonXP.setTexte(CSG.profile.xpDispo + "xp");
	}

	protected void save() {
		prevXp = CSG.profile.xpDispo;
		prevVitesse = CSG.profile.cadenceAdd;
		prevNvArmeDeBase = CSG.profile.NvArmeDeBase;
		prevNvArmeBalayage = CSG.profile.NvArmeBalayage;
		prevNvArmeHantee = CSG.profile.NvArmeHantee;
		prevNvArmeTrois = CSG.profile.lvlPinkWeapon;
		prevNvArmeSun = CSG.profile.NvArmeSun;
		updateTexteXp();
		updateTexteCadence();
		updateTexteUpgrade();
	}

	protected void undo() {
		CSG.profile.NvArmeBalayage = prevNvArmeBalayage;
		CSG.profile.NvArmeDeBase = prevNvArmeDeBase;
		CSG.profile.NvArmeHantee = prevNvArmeHantee;
		CSG.profile.lvlPinkWeapon = prevNvArmeTrois;
		CSG.profile.NvArmeSun = prevNvArmeSun;
		CSG.profile.cadenceAdd = prevVitesse;
		CSG.profile.xpDispo = prevXp;
		updateTexteUpgrade();
		updateTexteCadence();
		updateTexteXp();
		ArmeAdd.determinerCadenceTir();
		GreenAddParticle.COLOR = ArmeAdd.COLORS[CSG.R.nextInt(ArmeAdd.COLORS.length)];
		boutons.removeValue(boutonUndo, true);
	}

	private void updateTexteUpgrade() {
		if (CSG.profile.getArmeSelectionnee().nv() >= Profil.NV_ARME_MAX)
			boutonUpgrade.setTexte("LEVEL MAX");
		else 
			boutonUpgrade.setTexte("Weapon (" + CSG.profile.getCoutUpArme() + ")");
	}

}