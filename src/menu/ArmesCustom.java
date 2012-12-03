package menu;

import physique.Physique;
import vaisseaux.armes.Armes;
import vaisseaux.joueur.VaisseauType1;
import affichage.TexMan;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class ArmesCustom implements Screen {
	
	private Game game;
	private BitmapFont font;
	private BitmapFont fontXpDispo;
	// -- -- coordonnées
	private final int X_GENERAL = CSG.DIXIEME_LARGEUR;
	private final int X_XP_DISPO = CSG.DIXIEME_LARGEUR * 7;
	private final int Y_ARME_NOUV = (int) (CSG.DIXIEME_HAUTEUR * 9);
	private final int Y_ARME_UP = (int) (CSG.DIXIEME_HAUTEUR * 2);
	private final int Y_RETOUR = CSG.DIXIEME_HAUTEUR * 1;
	// -- -- Texte
	private final String txtArmeLvlUp = "Better weapon";
	private final String txtArmeNouv = "Other";
	private final String txtRetour = "-Back";
	private final String txtXpDispo = "XP : ";
	// -- -- autre
	private SpriteBatch batch;
	private int ratioPolice = 2;
	private final int policeGrandeLargeur = CSG.LARGEUR_ECRAN / 200;
	private final int policeGrandeHauteur = CSG.HAUTEUR_ECRAN / 200;
	private VaisseauType1 vaisseau = new VaisseauType1();

	public ArmesCustom(Game game) {
		this.game = game;
		// -- font
		font = new BitmapFont();
        font.setColor(Color.GREEN);
        font.setScale(policeGrandeLargeur, policeGrandeHauteur);
        
        fontXpDispo = new BitmapFont();
        fontXpDispo.setColor(Color.LIGHT_GRAY);
        fontXpDispo.setScale(policeGrandeLargeur / ratioPolice, policeGrandeHauteur / ratioPolice);
        // -- autre
		batch = new SpriteBatch();
		TexMan.loadGame();
		vaisseau.position.y += CSG.DIXIEME_HAUTEUR * 2;
	}

	@Override
	public void render(float delta) {
		// ** ** clear screen
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		// ** ** batch
		batch.begin();
		// -- PREVIEW ARME
		vaisseau.draw(batch, delta);
		vaisseau.tir(delta);
		Armes.affichageEtMouvement(batch, delta);
		// -- partie armes
		font.draw(batch, txtArmeNouv, X_GENERAL, Y_ARME_NOUV);
		// -- partie vitesse
		font.draw(batch, txtArmeLvlUp, X_GENERAL, Y_ARME_UP);
		fontXpDispo.draw(batch, "Cost : " + CSG.profil.getCoutUpArme(), CSG.LARGEUR_ECRAN - fontXpDispo.getBounds("Cost : " + CSG.profil.getCoutVitesse()).width
				- X_GENERAL, Y_ARME_UP - (font.getBounds(txtArmeLvlUp).height / 2));
		// -- partie retour
		font.draw(batch, txtRetour, X_GENERAL, Y_RETOUR);
		// -- xp dispo
		fontXpDispo.draw(batch, txtXpDispo + CSG.profil.xpDispo, X_XP_DISPO,
				CSG.HAUTEUR_ECRAN);
		
		batch.end();
		// ** ** update
		update();
	}

	private void update() {
		if (Gdx.input.justTouched()) {
			// ** ** prend les coordonnées
			int touchX = Gdx.input.getX();
			int touchY = CSG.HAUTEUR_ECRAN - Gdx.input.getY();
			// ** ** test les menus
			if(Physique.pointIn(font.getBounds(txtArmeNouv), X_GENERAL, Y_ARME_NOUV, touchX, touchY)){
				vaisseau.changerArme();
			}
			if(Physique.pointIn(font.getBounds(txtArmeLvlUp), X_GENERAL, Y_ARME_UP, touchX, touchY)){
				if(CSG.profil.getCoutUpArme() <= CSG.profil.xpDispo)
					CSG.profil.upArme();
			}
			if(Physique.pointIn(font.getBounds(txtRetour), X_GENERAL, Y_RETOUR, touchX, touchY)){
				Menu menu = new Menu(game);
				game.setScreen(menu);
			}
		}
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		CSG.profilManager.persist();
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
