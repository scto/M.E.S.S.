package menu;

import jeu.CSG;
import menu.ui.Bouton;
import assets.SoundMan;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.moribitotech.mtx.AbstractScreen;

public class MenuOptions extends AbstractScreen {

	private int ligne1 = 2;
	private int ligne2 = 4;
	private int ligne3 = 6;
	private int ligne4 = 8;
	private int ligne5 = 10;
	private int ligne6 = 12;

	public MenuOptions(final Game game) {
		super(game);
		Gdx.input.setCatchBackKey(true);
		ajout(boutonBack);
		// ************************ A R M E S ************************************************************
		final Bouton vol = new Bouton(WEAPON_VOL + (int) (CSG.profil.volumeArme * 10), true, CSG.menuFontPetite, LARGEUR_PETITBOUTON, HAUTEUR_PETITBOUTON, (CSG.LARGEUR_ECRAN / 2) - Menu.LARGEUR_PETITBOUTON / 2, -Menu.decalageY + CSG.HAUTEUR_ECRAN - Menu.HAUTEUR_BOUTON * ligne1, this, new OnClick() {
			public void onClick() {
			}
		}, false);
		ajout(vol);
		ajout(new Bouton(MOINS, false, CSG.menuFont, LARGEUR_MINIBOUTON, HAUTEUR_MINIBOUTON, CSG.LARGEUR_ECRAN / Menu.PADDING, -Menu.decalageY + CSG.HAUTEUR_ECRAN - Menu.HAUTEUR_BOUTON * ligne1 + Menu.HAUTEUR_MINIBOUTON / 2, this, new OnClick() {
			public void onClick() {
				CSG.profil.diminuerVolumeArme();
				vol.setTexte(WEAPON_VOL + (int) (CSG.profil.volumeArme * 10));
				SoundMan.playTir(SoundMan.tirEnergie);
			}
		}, false));
		ajout(new Bouton(PLUS, false, CSG.menuFont, LARGEUR_MINIBOUTON, HAUTEUR_MINIBOUTON, CSG.LARGEUR_ECRAN - (CSG.LARGEUR_ECRAN / Menu.PADDING) - Menu.LARGEUR_MINIBOUTON, -Menu.decalageY + CSG.HAUTEUR_ECRAN - Menu.HAUTEUR_BOUTON * ligne1 + Menu.HAUTEUR_MINIBOUTON / 2, this, new OnClick() {
			public void onClick() {
				CSG.profil.augmenterVolumeArme();
				vol.setTexte(WEAPON_VOL + (int) (CSG.profil.volumeArme * 10));
				SoundMan.playTir(SoundMan.tirEnergie);
			}
		}, false));
		// ************************ B R U I T A G E S ************************************************************
		final Bouton bruit = new Bouton(BRUITAGE_VOL + (int) (CSG.profil.volumeBruitages * 10), true, CSG.menuFontPetite, LARGEUR_PETITBOUTON, HAUTEUR_PETITBOUTON, (CSG.LARGEUR_ECRAN / 2) - Menu.LARGEUR_PETITBOUTON / 2, -Menu.decalageY + CSG.HAUTEUR_ECRAN - Menu.HAUTEUR_BOUTON * ligne2, this, new OnClick() {
			public void onClick() {
			}
		}, false);
		ajout(bruit);
		ajout(new Bouton(MOINS, false, CSG.menuFont, LARGEUR_MINIBOUTON, HAUTEUR_MINIBOUTON, CSG.LARGEUR_ECRAN / Menu.PADDING, -Menu.decalageY + CSG.HAUTEUR_ECRAN - Menu.HAUTEUR_BOUTON * ligne2 + Menu.HAUTEUR_MINIBOUTON / 2, this, new OnClick() {
			public void onClick() {
				CSG.profil.diminuerVolumeBruitage();
				bruit.setTexte(BRUITAGE_VOL + (int) (CSG.profil.volumeBruitages * 10));
				SoundMan.playBruitage(SoundMan.tirRocket);
			}
		}, false));
		ajout(new Bouton(PLUS, false, CSG.menuFont, LARGEUR_MINIBOUTON, HAUTEUR_MINIBOUTON, CSG.LARGEUR_ECRAN - (CSG.LARGEUR_ECRAN / Menu.PADDING) - Menu.LARGEUR_MINIBOUTON, -Menu.decalageY + CSG.HAUTEUR_ECRAN - Menu.HAUTEUR_BOUTON * ligne2 + Menu.HAUTEUR_MINIBOUTON / 2, this, new OnClick() {
			public void onClick() {
				CSG.profil.augmenterVolumeBruitage();
				bruit.setTexte(BRUITAGE_VOL + (int) (CSG.profil.volumeBruitages * 10));
				SoundMan.playBruitage(SoundMan.tirRocket);
			}
		}, false));
		// ************************ M U S I Q U E S ************************************************************
		final Bouton musique = new Bouton(MUSIQUE_VOL + (int) (CSG.profil.volumeMusique * 10), true, CSG.menuFontPetite, LARGEUR_PETITBOUTON, HAUTEUR_PETITBOUTON, (CSG.LARGEUR_ECRAN / 2) - Menu.LARGEUR_PETITBOUTON / 2, -Menu.decalageY + CSG.HAUTEUR_ECRAN - Menu.HAUTEUR_BOUTON * ligne3, this, new OnClick() {
			public void onClick() {
			}
		}, false);
		ajout(musique);
		ajout(new Bouton(MOINS, false, CSG.menuFont, LARGEUR_MINIBOUTON, HAUTEUR_MINIBOUTON, CSG.LARGEUR_ECRAN / Menu.PADDING, -Menu.decalageY + CSG.HAUTEUR_ECRAN - Menu.HAUTEUR_BOUTON * ligne3 + Menu.HAUTEUR_MINIBOUTON / 2, this, new OnClick() {
			public void onClick() {
				CSG.profil.diminuerVolumeMusique();
				musique.setTexte(MUSIQUE_VOL + (int) (CSG.profil.volumeMusique * 10));
				SoundMan.playMusic();
			}
		}, false));
		ajout(new Bouton(PLUS, false, CSG.menuFont, LARGEUR_MINIBOUTON, HAUTEUR_MINIBOUTON, CSG.LARGEUR_ECRAN - (CSG.LARGEUR_ECRAN / Menu.PADDING) - Menu.LARGEUR_MINIBOUTON, -Menu.decalageY + CSG.HAUTEUR_ECRAN - Menu.HAUTEUR_BOUTON * ligne3 + Menu.HAUTEUR_MINIBOUTON / 2, this, new OnClick() {
			public void onClick() {
				CSG.profil.augmenterVolumeMusique();
				musique.setTexte(MUSIQUE_VOL + (int) (CSG.profil.volumeMusique * 10));
				SoundMan.playMusic();
			}
		}, false));

		// ****************************** B L O O M ************************************************************
		String bloomTxt = "BLOOM OFF";
		if (CSG.profil.bloom)
			bloomTxt = "BLOOM ON";
		final Bouton bloom = new Bouton(bloomTxt, true, CSG.menuFontPetite, LARGEUR_PETITBOUTON, HAUTEUR_PETITBOUTON, (CSG.LARGEUR_ECRAN / 2) - Menu.LARGEUR_PETITBOUTON / 2, -Menu.decalageY + CSG.HAUTEUR_ECRAN - Menu.HAUTEUR_BOUTON * ligne4, this);
		ajout(bloom);

		ajout(new Bouton(MOINS, false, CSG.menuFont, LARGEUR_MINIBOUTON, HAUTEUR_MINIBOUTON, CSG.LARGEUR_ECRAN / Menu.PADDING, -Menu.decalageY + CSG.HAUTEUR_ECRAN - Menu.HAUTEUR_BOUTON * ligne4 + Menu.HAUTEUR_MINIBOUTON / 2, this, new OnClick() {
			public void onClick() {
				majBloom();
				CSG.profil.intensiteBloom -= 0.5f;
				bloom.setTexte(INTENSITY + CSG.profil.intensiteBloom);
				if (CSG.profil.intensiteBloom < 0.5f) {
					CSG.profil.intensiteBloom = 0.5f;
					// CSG.profil.bloom = false;
					bloom.setTexte("BLOOM OFF");
				}
			}
		}, false));
		ajout(new Bouton(PLUS, false, CSG.menuFont, LARGEUR_MINIBOUTON, HAUTEUR_MINIBOUTON, CSG.LARGEUR_ECRAN - (CSG.LARGEUR_ECRAN / Menu.PADDING) - Menu.LARGEUR_MINIBOUTON, -Menu.decalageY + CSG.HAUTEUR_ECRAN - Menu.HAUTEUR_BOUTON * ligne4 + Menu.HAUTEUR_MINIBOUTON / 2, this, new OnClick() {
			public void onClick() {
				CSG.profil.bloom = true;
				CSG.initBloom();
				majBloom();
				if (CSG.profil.intensiteBloom < 4f)
					CSG.profil.intensiteBloom += 0.5f;
				bloom.setTexte(INTENSITY + CSG.profil.intensiteBloom);
			}
		}, false));
		// ****************************** B L O O M ************************************************************
		String bonusTxt = "Automatic bonus";
		if (CSG.profil.manualBonus)
			bonusTxt = "Manual bonus";
		final Bouton bonus = new Bouton(bonusTxt, false, CSG.menuFont, LARGEUR_BOUTON, HAUTEUR_BOUTON, (CSG.LARGEUR_ECRAN / 2) - Menu.LARGEUR_BOUTON / 2, -Menu.decalageY + CSG.HAUTEUR_ECRAN - Menu.HAUTEUR_BOUTON * ligne5, this);
		bonus.setClick(new OnClick() {
			@Override
			public void onClick() {
				CSG.profil.manualBonus = !CSG.profil.manualBonus;
				if (CSG.profil.manualBonus)
					bonus.setTexte("Manual bonus");
				else 
					bonus.setTexte("Automatic bonus");
			}
		});
		ajout(bonus);
		// ************************ C O N T R O L E S ********************************************************
		final Bouton control = new Bouton(CSG.profil.getNomControle(), false, CSG.menuFont, LARGEUR_BOUTON, HAUTEUR_BOUTON, CSG.LARGEUR_ECRAN / 2 - Menu.LARGEUR_BOUTON / 2, -Menu.decalageY + -2 * Menu.HAUTEUR_MINIBOUTON + CSG.HAUTEUR_ECRAN - Menu.HAUTEUR_BOUTON * ligne6, this);
		control.setClick(new OnClick() {
			public void onClick() {
				CSG.profil.chgControle();
				control.setTexte(CSG.profil.getNomControle());
			}
		});
		ajout(control);
	}

	private void majBloom() {
		if (CSG.profil.bloom) {
			CSG.bloom.setBloomIntesity(CSG.profil.intensiteBloom);
			CSG.profilManager.persist();
		}
	}
}
