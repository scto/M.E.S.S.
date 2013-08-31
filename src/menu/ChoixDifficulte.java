package menu;

import jeu.Endless;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.moribitotech.mtx.AbstractScreen;

public class ChoixDifficulte extends AbstractScreen{
	
	 public ChoixDifficulte(Game game) {
		 super(game);
		 setUpScreenElements();
	 }
	  
	 public void setUpScreenElements() {
		// ** ** ** PIECE OF CAKE ** ** **
		Bouton lvl1 = new Bouton("Piece of Cake", false, CSG.menuFont, LARGEUR_BOUTON,
				HAUTEUR_BOUTON, CSG.LARGEUR_ECRAN / PADDING, CSG.HAUTEUR_ECRAN - HAUTEUR_BOUTON * 4, this, new OnClick() {
					public void onClick() {
						getGame().setScreen(new Endless(getGame(), batch, 1));
					}
				},true);
		ajout(lvl1);
		// ** ** **  LET'S ROCK  ** ** **
		Bouton lvl2 = new Bouton("Let's Rock", false, CSG.menuFont, LARGEUR_BOUTON,
				HAUTEUR_BOUTON, CSG.LARGEUR_ECRAN / PADDING, CSG.HAUTEUR_ECRAN - HAUTEUR_BOUTON * 6, this, new OnClick() {
					public void onClick() {
						getGame().setScreen(new Endless(getGame(), batch, 2));
					}
				},true);
		ajout(lvl2);
		// ** ** **  COME GET SOME  ** ** **
		Bouton lvl3 = new Bouton("Come get some", false, CSG.menuFont, LARGEUR_BOUTON,
				HAUTEUR_BOUTON, CSG.LARGEUR_ECRAN / PADDING, CSG.HAUTEUR_ECRAN - HAUTEUR_BOUTON * 8, this, new OnClick() {
					public void onClick() {
						getGame().setScreen(new Endless(getGame(), batch, 3));
					}
				},true);
		ajout(lvl3);
		
		if (Gdx.app.getVersion() != 0) CSG.myRequestHandler.showAds(true);
	 }

	@Override
	public void keyBackPressed() {
		Menu menu = new Menu(getGame());
		getGame().setScreen(menu);
	}
}