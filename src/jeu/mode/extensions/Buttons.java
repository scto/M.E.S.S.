package jeu.mode.extensions;

import jeu.CSG;
import jeu.Physic;
import jeu.Profil;
import jeu.Strings;
import jeu.mode.EndlessMode;
import menu.screens.Menu;
import menu.tuto.OnClick;
import menu.ui.Button;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import elements.generic.enemies.Enemy;
import elements.particular.Player;

public class Buttons {

	private static final Vector2 TMP_POS = new Vector2(), TMP_DIR = new Vector2();
	private static Button boutonBack = null, boutonUpgrade = null, boutonTwitter = null, boutonRestart = null;
	
	public static void init() {
		boutonUpgrade = null;
		boutonTwitter = null;
		boutonRestart = null;
		boutonBack = null;
	}
	
	public static void backButton(SpriteBatch batch, Game game) {
		if (boutonBack == null) {
			boutonBack = new Button(Strings.BACK, CSG.menuFont, 
					(int) CSG.menuFont.getBounds(Strings.BACK).width, 
					(int) CSG.menuFont.getBounds(Strings.BACK).height, 
					(int) ((EndlessMode.cam.position.x) - CSG.menuFont.getBounds(Strings.BACK).width / 2),
					Menu.BUTTON_HEIGHT * 3,
					new OnClick() {
					public void onClick() {
					}
			});
		}
		if (Gdx.input.justTouched()) {
			if (boutonBack != null && 
					Physic.isPointInRect(Gdx.input.getX() + EndlessMode.cam.position.x / 2, CSG.SCREEN_HEIGHT - Gdx.input.getY(),
							0, Menu.BUTTON_HEIGHT * 1.1f, CSG.gameZoneWidth, Menu.BUTTON_HEIGHT * 3)) {
				goToMenu(game);
				boutonBack = null;
			}
		}
		if (boutonBack != null)
			boutonBack.draw(batch);
	}
	
	public static void goToMenu(Game game) {
		CSG.profilManager.persist();
		game.setScreen(new Menu(game));
		Enemy.clear();
		if (CSG.profile.xpDispo >= 30000) {
			CSG.talkToTheWorld.unlockAchievementGPGS(Strings.ACH_30k_XP);
		}
	}

	public static void initAndDrawButtons(SpriteBatch batch) {
		if (boutonRestart == null) {
			boutonRestart = new Button(Strings.RESTART_BUTTON, CSG.menuFont, 
					(int) CSG.menuFont.getBounds(Strings.RESTART_BUTTON).width, 
					(int) CSG.menuFont.getBounds(Strings.RESTART_BUTTON).height, 
					(int) ((EndlessMode.cam.position.x) - CSG.menuFont.getBounds(Strings.RESTART_BUTTON).width / 2),
					Menu.BUTTON_HEIGHT,
					new OnClick() {
					public void onClick() {
						EndlessMode.init();
					}
			});
		} else {
			boutonRestart.draw(batch);
		}
		if (Player.weapon.nv() < Profil.NV_ARME_MAX) {
			if (boutonUpgrade == null) {
				if (CSG.profile.getCoutUpArme() <= CSG.profile.xpDispo) {
					boutonUpgrade = new Button(Strings.UPGRADE_BUTTON, CSG.menuFont, 
							(int) CSG.menuFont.getBounds(Strings.UPGRADE_BUTTON).width, 
							(int) CSG.menuFont.getBounds(Strings.UPGRADE_BUTTON).height, 
							(int) ((EndlessMode.cam.position.x) - CSG.menuFont.getBounds(Strings.UPGRADE_BUTTON).width / 2),
							(int) (Menu.BUTTON_HEIGHT * 3f),
							new OnClick() {
						public void onClick() {
							if(CSG.profile.getCoutUpArme() <= CSG.profile.xpDispo) {
								CSG.profile.upArme();
								CSG.profilManager.persist();
								EndlessMode.init();
							} else {
								CSG.talkToTheWorld.buyXp();
							}
						}
					});
				} else {
					boutonUpgrade = new Button(Strings.BUY_XP_BUTTON, CSG.menuFont, 
							(int) CSG.menuFont.getBounds(Strings.UPGRADE_BUTTON).width, 
							(int) CSG.menuFont.getBounds(Strings.UPGRADE_BUTTON).height, 
							(int) ((EndlessMode.cam.position.x) - CSG.menuFont.getBounds(Strings.UPGRADE_BUTTON).width / 2),
							(int) (Menu.BUTTON_HEIGHT * 3f),
							new OnClick() {
						public void onClick() {
							if(CSG.profile.getCoutUpArme() <= CSG.profile.xpDispo) {
								CSG.profile.upArme();
								CSG.profilManager.persist();
								init();
							} else {
								CSG.talkToTheWorld.buyXp();
							}
						}
					});
				}
			} else {
				boutonUpgrade.draw(batch);
			}
		} 
		if (Score.score > 50000) {
			if (boutonTwitter == null) {
				boutonTwitter = new Button(Strings.BRAG_TWITTER, CSG.menuFont, 
						(int) CSG.menuFont.getBounds(Strings.BRAG_TWITTER).width, 
						(int) CSG.menuFont.getBounds(Strings.BRAG_TWITTER).height, 
						(int) ((EndlessMode.cam.position.x) - CSG.menuFont.getBounds(Strings.BRAG_TWITTER).width / 2),
						Menu.BUTTON_HEIGHT * 5,
						new OnClick() {
						public void onClick() {
							CSG.talkToTheWorld.bragTwitter("I made " + (int)Score.score + " on #MESS" + EndlessMode.difficulty + " #androidgames");
						}
				});
			} else {
				boutonTwitter.draw(batch);
			}
		}
	}

	public static void drawUpgradeAndTwitter(SpriteBatch batch) {
		if (boutonUpgrade != null) {
			boutonUpgrade.draw(batch);
			if (boutonTwitter != null)
				boutonTwitter.draw(batch);
		}
	}

	public static void testClick() {
		if (Gdx.input.justTouched()) {
			Button.testClick(boutonUpgrade, EndlessMode.cam.position.x / 2);
			Button.testClick(boutonRestart, EndlessMode.cam.position.x / 2);
			Button.testClick(boutonTwitter, EndlessMode.cam.position.x / 2);
		}
	}

	public static void removeBack() {
		boutonBack = null;
	}

	
}
