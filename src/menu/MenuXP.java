package menu;

import vaisseaux.joueur.VaisseauType1;
import com.badlogic.gdx.Game;
import com.moribitotech.mtx.AbstractScreen;


public class MenuXP extends AbstractScreen{
	
	private Bouton boutonUpgrade;
	private Bouton boutonCadence;
	private Bouton boutonUndo;
	private final Bouton boutonXP;
	private static int prevXp = CSG.profil.xpDispo;
	private static int prevNvArmeDeBase = CSG.profil.NvArmeDeBase;
	private static int prevNvArmeBalayage = CSG.profil.NvArmeBalayage;
	private static int prevNvArmeHantee = CSG.profil.NvArmeHantee;
	private static int prevNvArmeTrois = CSG.profil.NvArmeTrois;
	private static int prevVitesse = CSG.profil.cadenceAdd;
	private JeuBackground jeu = new JeuBackground();

	public MenuXP(final Game game) {
		super(game);
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
						VaisseauType1.changerArme();
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
		VaisseauType1.position.y = CSG.HAUTEUR_ECRAN / 3;
		VaisseauType1.position.x = (CSG.LARGEUR_ECRAN / 2) - VaisseauType1.DEMI_LARGEUR; 
		VaisseauType1.rajoutAdd();
		VaisseauType1.rajoutAdd();
		VaisseauType1.rajoutAdd();
		VaisseauType1.rajoutAdd();
		setRenderBackground(false);
	}

	@Override
	public void render(float delta) {
		batch.begin();
		
		CSG.renderBackground(batch);
		jeu.render(batch, delta);
		super.render(delta);
	}
	
	private void ajoutUndo() {
		if (boutonUndo == null) {
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

	private void updateTexteUpgrade() {
		if (CSG.profil.getCoutUpArme() == 43200) boutonUpgrade.setTexte("LEVEL MAX");
		else boutonUpgrade.setTexte("WEAPON ++ (" + CSG.profil.getCoutUpArme() + ")");
	}

}