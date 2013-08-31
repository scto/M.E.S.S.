package menu;

import vaisseaux.armes.joueur.ArmeAdd;
import jeu.Endless;
import assets.SoundMan;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.moribitotech.mtx.AbstractScreen;
import com.swarmconnect.Swarm;

public class Menu extends AbstractScreen{
	
	private float temps = 0;
	private int etapeCode = 0;
	
	 public Menu(Game game) {
		super(game);
		setUpScreenElements();
	 }
	  
	 public void setUpScreenElements() {
		ArmeAdd.determinerCadenceTir();
		temps = 0;
		Gdx.input.setCatchBackKey(true);
				
		ajout(new Bouton(PLAY, false, CSG.menuFont, LARGEUR_BOUTON, HAUTEUR_BOUTON, CSG.LARGEUR_ECRAN / PADDING, CSG.HAUTEUR_ECRAN - (HAUTEUR_BOUTON * 4), this,
				new OnClick() {
					public void onClick() {
						ChoixDifficulte choix = new ChoixDifficulte(getGame());
						getGame().setScreen(choix);
					}
				}, true));
		ajout(new Bouton(SHIP, false, CSG.menuFont, LARGEUR_BOUTON, HAUTEUR_BOUTON, CSG.LARGEUR_ECRAN / PADDING, CSG.HAUTEUR_ECRAN - (HAUTEUR_BOUTON * 6), this,
				new OnClick() {
					public void onClick() {
						MenuXP choix = new MenuXP(getGame());
						getGame().setScreen(choix);
					}
				}, true));
		ajout(new Bouton(OPTION, false, CSG.menuFont, LARGEUR_BOUTON, HAUTEUR_BOUTON, CSG.LARGEUR_ECRAN / PADDING, CSG.HAUTEUR_ECRAN - (HAUTEUR_BOUTON * 8), this,
				new OnClick() {
					public void onClick() {
						MenuOptions choix = new MenuOptions(getGame());
						getGame().setScreen(choix);
					}
				}, true));
		ajout(new Bouton(SWARM, false, CSG.menuFont, LARGEUR_BOUTON, HAUTEUR_BOUTON, CSG.LARGEUR_ECRAN / PADDING, CSG.HAUTEUR_ECRAN - (HAUTEUR_BOUTON * 10), this,
				new OnClick() {
					public void onClick() {
						Swarm.showDashboard(); 						
					}
				}, true));
		ajout(new Bouton(EXIT, false, CSG.menuFont, LARGEUR_BOUTON, HAUTEUR_BOUTON, CSG.LARGEUR_ECRAN / PADDING, CSG.HAUTEUR_ECRAN - (HAUTEUR_BOUTON * 15), this,
				new OnClick() {
					public void onClick() {
						Gdx.app.exit();
					}
				}, true));
		
		if(Gdx.app.getVersion() != 0)		CSG.myRequestHandler.showAds(true);
	 }

	@Override
	public void keyBackPressed() {
		super.keyBackPressed();
		getGame().setScreen(new Endless(getGame(), batch, 1));
	}

	@Override
	public void render(float delta) {
		temps += delta;
		detectiopnKonamiCode();
		super.render(delta);
		if (Gdx.input.isKeyPressed(Keys.BACK) && temps > 2) Gdx.app.exit();
	}

	/**
	 * Il a besoin du batch pour afficher le jeu background en cas de code valide
	 * @param batch
	 */
	 private void detectiopnKonamiCode() {
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
			SoundMan.playBruitage(SoundMan.explosionGrosse);
			break;
		}
	}
}

