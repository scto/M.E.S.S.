package menu.screens;

import menu.JeuBackground;
import menu.tuto.OnClick;
import menu.ui.Button;
import jeu.CSG;
import jeu.Profil;
import jeu.Strings;
import jeu.mode.EndlessMode;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

import elements.generic.weapons.player.ArmeAdd;
import elements.particular.Player;
import elements.particular.particles.Particles;
import elements.particular.particles.individual.weapon.GreenAddParticle;


public class MenuXP extends AbstractScreen{
	
	private Button boutonUpgrade, boutonCadence, boutonUndo;
	private final Button boutonXP;
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
		Gdx.input.setCatchBackKey(true);
		ajout(buttonBack);
		// **       B O U T O N   X P       **
		boutonXP = new Button(CSG.profile.xpDispo + "xp", false, CSG.menuFont, BUTTON_WIDTH, SMALL_BUTTON_HEIGHT,
				(CSG.screenWidth / 2) - Menu.BUTTON_WIDTH/2, - Menu.yOffset + CSG.SCREEN_HEIGHT - Menu.BUTTON_HEIGHT * 2, this, new OnClick() {
					public void onClick() {	}}, false);
		ajout(boutonXP);
		// ** ** ** BOUTON WEAPON ** ** **
		ajout(new Button(OTHER_WEAP, false, CSG.menuFont, BUTTON_WIDTH, SMALL_BUTTON_HEIGHT,
				(CSG.screenWidth / 2) - Menu.BUTTON_WIDTH/2, - Menu.yOffset + CSG.SCREEN_HEIGHT - Menu.BUTTON_HEIGHT * 4, this, new OnClick() {
					public void onClick() {	
						Player.chqngeWeapon();
						CSG.profilManager.persist();
						updateTexteUpgrade();
					}}, false));
		// ** ** ** BOUTON UPGRADE ** **
		boutonUpgrade = new Button("", false, CSG.menuFontSmall, SMALL_BUTTON_WIDTH, SMALL_BUTTON_HEIGHT,
				CSG.screenWidth - (CSG.screenWidth / Menu.PADDING) - Menu.SMALL_BUTTON_WIDTH,	Menu.BUTTON_HEIGHT, this, new OnClick() {
					public void onClick() {	
						if(CSG.profile.getCoutUpArme() <= CSG.profile.xpDispo) {
							save();
							CSG.profile.upArme();
							updateTexteUpgrade();
							CSG.profilManager.persist();
							updateTexteXp();
							ajoutUndo();
						} else {
							CSG.talkToTheWorld.buyXp();
							System.out.println("MenuXP.MenuXP(...).new OnClick() {...}.onClick()");
						}
					}}, false);
		ajout(boutonUpgrade);
		updateTexteUpgrade();
		// ** ** ** BOUTON FIRERATE ** ** **
		boutonCadence = new Button("", false, CSG.menuFontSmall, Menu.SMALL_BUTTON_WIDTH, Menu.SMALL_BUTTON_HEIGHT,
				CSG.screenWidth - (CSG.screenWidth / Menu.PADDING) - Menu.SMALL_BUTTON_WIDTH,	Menu.BUTTON_HEIGHT * 3, this, new OnClick() {
					public void onClick() {
						if (CSG.profile.getCoutCadenceAdd() <= CSG.profile.xpDispo){
							save();
							CSG.profile.upCadenceAdd();
							CSG.profilManager.persist();
							updateTexteCadence();
							updateTexteXp();
							ajoutUndo();
						} else {
							CSG.talkToTheWorld.buyXp();
						}
					}}, false);
		updateTexteCadence();
		ajout(boutonCadence);
		Player.POS.y = CSG.SCREEN_HEIGHT / 3;
		Player.POS.x = (CSG.screenWidth / 2) - Player.HALF_WIDTH; 
		Player.addDrone();
		Player.addDrone();
		Player.addDrone();
		Player.addDrone();
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
		for (int i = 0; i < buttons.size; i++) 
			if (buttons.get(i) != null)
				buttons.get(i).draw(CSG.batch);
		Particles.drawUi(CSG.batch);
		CSG.menuFontSmall.draw(CSG.batch, "Weapon level : " + CSG.profile.getArmeSelectionnee().nv(), 4, 4 + CSG.menuFontSmall.getBounds("W").height);
		CSG.end();
		EndlessMode.majDeltas();
		EndlessMode.alternate = !EndlessMode.alternate;
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
			CSG.talkToTheWorld.unlockAchievementGPGS(Strings.ACH_FAVORITE_SHOP);
			boutonUndo = new Button("UNDO", false, CSG.menuFontSmall, Menu.SMALL_BUTTON_WIDTH, Menu.SMALL_BUTTON_HEIGHT, CSG.screenWidth / Menu.PADDING, Menu.BUTTON_HEIGHT * 3, this,
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
		buttons.removeValue(boutonUndo, true);
	}

	private void updateTexteUpgrade() {
		if (CSG.profile.getArmeSelectionnee().nv() >= Profil.NV_ARME_MAX)
			boutonUpgrade.setTexte("LEVEL MAX");
		else 
			boutonUpgrade.setTexte("Weapon (" + CSG.profile.getCoutUpArme() + ")");
	}

}