package menu;

import jeu.Strings;
import objets.joueur.VaisseauJoueur;
import assets.particules.Particules;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.moribitotech.mtx.AbstractScreen;


public class MenuXP extends AbstractScreen{
	
	private Bouton boutonUpgrade;
	private Bouton boutonCadence;
	private Bouton boutonUndo;
	private final Bouton boutonXP;
	private JeuBackground jeu = new JeuBackground();
	private static int prevNvArmeDeBase = CSG.profil.NvArmeDeBase;
	private static int prevNvArmeBalayage = CSG.profil.NvArmeBalayage;
	private static int prevNvArmeHantee = CSG.profil.NvArmeHantee;
	private static int prevNvArmeTrois = CSG.profil.NvArmeTrois;
	private static int prevVitesse = CSG.profil.cadenceAdd;
	private static int prevXp = CSG.profil.xpDispo;

	public MenuXP(final Game game) {
		super(game);
		Gdx.graphics.setVSync(true);
		Gdx.input.setCatchBackKey(true);
		ajout(boutonBack);
		// **       B O U T O N   X P       **
		boutonXP = new Bouton(String.valueOf(CSG.profil.xpDispo) + "xp", false, CSG.menuFont, LARGEUR_BOUTON, HAUTEUR_PETITBOUTON,
				(CSG.LARGEUR_ECRAN / 2) - Menu.LARGEUR_BOUTON/2, - Menu.decalageY + CSG.HAUTEUR_ECRAN - Menu.HAUTEUR_BOUTON * 2, this, new OnClick() {
					public void onClick() {	}}, false);
		ajout(boutonXP);
		// ** ** ** BOUTON WEAPON ** ** **
		ajout(new Bouton(OTHER_WEAP, false, CSG.menuFont, LARGEUR_BOUTON, HAUTEUR_PETITBOUTON,
				(CSG.LARGEUR_ECRAN / 2) - Menu.LARGEUR_BOUTON/2, - Menu.decalageY + CSG.HAUTEUR_ECRAN - Menu.HAUTEUR_BOUTON * 4, this, new OnClick() {
					public void onClick() {	
						VaisseauJoueur.changerArme();
						CSG.profilManager.persist();
						updateTexteUpgrade();
					}}, false));
		// ** ** ** BOUTON UPGRADE ** **
		boutonUpgrade = new Bouton("", false, CSG.menuFontPetite, LARGEUR_PETITBOUTON, HAUTEUR_PETITBOUTON,
				CSG.LARGEUR_ECRAN - (CSG.LARGEUR_ECRAN / Menu.PADDING) - Menu.LARGEUR_PETITBOUTON,	Menu.HAUTEUR_BOUTON, this, new OnClick() {
					public void onClick() {	
						if(CSG.profil.getCoutUpArme() < CSG.profil.xpDispo){
							save();
							CSG.profil.upArme();
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
				CSG.LARGEUR_ECRAN - (CSG.LARGEUR_ECRAN / Menu.PADDING) - Menu.LARGEUR_PETITBOUTON,	Menu.HAUTEUR_BOUTON * 3, this, new OnClick() {
					public void onClick() {
						if (CSG.profil.getCoutCadenceAdd() < CSG.profil.xpDispo){
							save();
							CSG.profil.upCadenceAdd();
							CSG.profilManager.persist();
							updateTexteCadence();
							updateTexteXp();
							ajoutUndo();
						}
					}}, false);
		updateTexteCadence();
		ajout(boutonCadence);
		VaisseauJoueur.position.y = CSG.HAUTEUR_ECRAN / 3;
		VaisseauJoueur.position.x = (CSG.LARGEUR_ECRAN / 2) - VaisseauJoueur.DEMI_LARGEUR; 
		VaisseauJoueur.rajoutAdd();
		VaisseauJoueur.rajoutAdd();
		VaisseauJoueur.rajoutAdd();
		VaisseauJoueur.rajoutAdd();
		setRenderBackground(false);
	}

	@Override
	public void render(float delta) {
		batch.begin();
		Particules.background(batch);
		jeu.render(batch, delta);
		super.render(delta);
	}
	
	private void ajoutUndo() {
		if (boutonUndo == null) {
			CSG.google.unlockAchievementGPGS(Strings.ACH_FAVORITE_SHOP);
			boutonUndo = new Bouton("UNDO", false, CSG.menuFontPetite, Menu.LARGEUR_PETITBOUTON, Menu.HAUTEUR_PETITBOUTON, CSG.LARGEUR_ECRAN / Menu.PADDING, Menu.HAUTEUR_BOUTON * 3, this,
			new OnClick()  {
				public void onClick() {		undo();		}
			}, false);
			ajout(boutonUndo);
		}
	}

	private void updateTexteCadence() {
		boutonCadence.setTexte("ADD   ++ (" + CSG.profil.getCoutCadenceAdd() + ")");
	}

	private void updateTexteXp() {
		boutonXP.setTexte(CSG.profil.xpDispo + "xp");
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
		CSG.profil.NvArmeBalayage = prevNvArmeBalayage;
		CSG.profil.NvArmeDeBase = prevNvArmeDeBase;
		CSG.profil.NvArmeHantee = prevNvArmeHantee;
		CSG.profil.NvArmeTrois = prevNvArmeTrois;
		CSG.profil.cadenceAdd = prevVitesse;
		CSG.profil.xpDispo = prevXp;
		updateTexteUpgrade();
		updateTexteCadence();
		updateTexteXp();
	}

	private void updateTexteUpgrade() {
		if (CSG.profil.getCoutUpArme() == 43200) {
			boutonUpgrade.setTexte("LEVEL MAX");
			CSG.google.unlockAchievementGPGS(Strings.ACH_LVL6);
		}
		else 
			boutonUpgrade.setTexte("WEAPON ++ (" + CSG.profil.getCoutUpArme() + ")");
	}

}