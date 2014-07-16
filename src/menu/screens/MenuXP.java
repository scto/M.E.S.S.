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
	private static int prevNvArmeDeBase = CSG.profile.lvlFireball, prevNvArmeBalayage = CSG.profile.lvlSweepWeapon, prevNvArmeHantee = CSG.profile.lvlTWeapon, prevNvArmeTrois = CSG.profile.lvlPinkWeapon,
			prevNvArmeSun = CSG.profile.lvlSunWeapon, prevVitesse = CSG.profile.dronesFirerate, prevXp = CSG.profile.xp;

	public MenuXP(final Game game) {
		super(game);
		Gdx.input.setCatchBackKey(true);
		ajout(buttonBack);
		// **       B O U T O N   X P       **
		boutonXP = new Button(CSG.profile.xp + "xp", false, CSG.menuFont, BUTTON_WIDTH, SMALL_BUTTON_HEIGHT,
				(CSG.width / 2) - Menu.BUTTON_WIDTH/2, - Menu.yOffset + CSG.height - Menu.BUTTON_HEIGHT * 2, new OnClick() {
					public void onClick() {	}});
		ajout(boutonXP);
		// ** ** ** BOUTON WEAPON ** ** **
		ajout(new Button(OTHER_WEAP, false, CSG.menuFont, BUTTON_WIDTH, SMALL_BUTTON_HEIGHT,
				(CSG.width / 2) - Menu.BUTTON_WIDTH/2, - Menu.yOffset + CSG.height - Menu.BUTTON_HEIGHT * 4, new OnClick() {
					public void onClick() {	
						Player.chqngeWeapon();
						CSG.profilManager.persist();
						updateTexteUpgrade();
					}}));
		// ** ** ** BOUTON UPGRADE ** **
		boutonUpgrade = new Button("", false, CSG.menuFontSmall, SMALL_BUTTON_WIDTH, SMALL_BUTTON_HEIGHT,
				CSG.width - (CSG.width / Menu.PADDING) - Menu.SMALL_BUTTON_WIDTH,	Menu.BUTTON_HEIGHT, new OnClick() {
					public void onClick() {	
						if(CSG.profile.getCoutUpArme() <= CSG.profile.xp) {
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
					}});
		ajout(boutonUpgrade);
		updateTexteUpgrade();
		// ** ** ** BOUTON FIRERATE ** ** **
		boutonCadence = new Button("", false, CSG.menuFontSmall, Menu.SMALL_BUTTON_WIDTH, Menu.SMALL_BUTTON_HEIGHT,
				CSG.width - (CSG.width / Menu.PADDING) - Menu.SMALL_BUTTON_WIDTH,	Menu.BUTTON_HEIGHT * 3, new OnClick() {
					public void onClick() {
						if (CSG.profile.getCoutCadenceAdd() <= CSG.profile.xp){
							save();
							CSG.profile.upCadenceAdd();
							CSG.profilManager.persist();
							updateTexteCadence();
							updateTexteXp();
							ajoutUndo();
						} else {
							CSG.talkToTheWorld.buyXp();
						}
					}});
		updateTexteCadence();
		ajout(boutonCadence);
		Player.POS.set((CSG.width / 2) - Player.HALF_WIDTH, CSG.height / 3); 
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
		if (Gdx.input.isKeyPressed(Keys.BACK))
			keyBackPressed();
		CSG.batch.begin();
		Particles.background(CSG.batch);
		jeu.render(CSG.batch, delta);
		for (int i = 0; i < buttons.size; i++) 
			if (buttons.get(i) != null)
				buttons.get(i).draw(CSG.batch);
		CSG.menuFontSmall.draw(CSG.batch, "Weapon level : " + CSG.profile.getArmeSelectionnee().nv(), 4, 4 + CSG.menuFontSmall.getBounds("W").height);
		CSG.end();
		EndlessMode.majDeltas(true);
		EndlessMode.alternate = !EndlessMode.alternate;
		EndlessMode.fps = Gdx.graphics.getFramesPerSecond();
		EndlessMode.perf = EndlessMode.fps / 6;
	}
	
	@Override
	public void keyBackPressed() {
		changeMenu(new Menu(game));
		CSG.profilManager.persist();
	}
	private void ajoutUndo() {
		if (boutonUndo == null) {
			CSG.talkToTheWorld.unlockAchievementGPGS(Strings.ACH_FAVORITE_SHOP);
			boutonUndo = new Button("UNDO", false, CSG.menuFontSmall, Menu.SMALL_BUTTON_WIDTH, Menu.SMALL_BUTTON_HEIGHT, CSG.width / Menu.PADDING, Menu.BUTTON_HEIGHT * 3, new OnClick()  {
				public void onClick() {		undo();		}
			});
			ajout(boutonUndo);
		} else 
			ajout(boutonUndo);
	}

	private void updateTexteCadence() {
		boutonCadence.setTexte("Drones (" + CSG.profile.getCoutCadenceAdd() + ")");
	}

	private void updateTexteXp() {
		boutonXP.setTexte(CSG.profile.xp + "xp");
	}

	protected void save() {
		prevXp = CSG.profile.xp;
		prevVitesse = CSG.profile.dronesFirerate;
		prevNvArmeDeBase = CSG.profile.lvlFireball;
		prevNvArmeBalayage = CSG.profile.lvlSweepWeapon;
		prevNvArmeHantee = CSG.profile.lvlTWeapon;
		prevNvArmeTrois = CSG.profile.lvlPinkWeapon;
		prevNvArmeSun = CSG.profile.lvlSunWeapon;
		updateTexteXp();
		updateTexteCadence();
		updateTexteUpgrade();
	}

	protected void undo() {
		CSG.profile.lvlSweepWeapon = prevNvArmeBalayage;
		CSG.profile.lvlFireball = prevNvArmeDeBase;
		CSG.profile.lvlTWeapon = prevNvArmeHantee;
		CSG.profile.lvlPinkWeapon = prevNvArmeTrois;
		CSG.profile.lvlSunWeapon = prevNvArmeSun;
		CSG.profile.dronesFirerate = prevVitesse;
		CSG.profile.xp = prevXp;
		updateTexteUpgrade();
		updateTexteCadence();
		updateTexteXp();
		ArmeAdd.determinerCadenceTir();
		GreenAddParticle.COLOR = ArmeAdd.COLORS[CSG.R.nextInt(ArmeAdd.COLORS.length)];
		buttons.removeValue(boutonUndo, true);
	}

	private void updateTexteUpgrade() {
		if (CSG.profile.getArmeSelectionnee().nv() >= Profil.LVL_MAX)
			boutonUpgrade.setTexte("LEVEL MAX");
		else 
			boutonUpgrade.setTexte("Weapon (" + CSG.profile.getCoutUpArme() + ")");
	}

}