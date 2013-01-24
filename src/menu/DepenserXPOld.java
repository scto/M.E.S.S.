package menu;

import physique.Physique;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class DepenserXPOld implements Screen {
	
	private Game game;
	private BitmapFont font;
	private BitmapFont fontXpDispo;
	// -- -- coordonnées
	private final int X_GENERAL = CSG.DIXIEME_LARGEUR;
	private final int X_XP_DISPO = CSG.DIXIEME_LARGEUR * 7;
	private final int Y_ARME = (int) (CSG.DIXIEME_HAUTEUR * 8.5);
	private final int Y_VITESSE = (int) (CSG.DIXIEME_HAUTEUR * 9.5);
	private final int Y_RETOUR = CSG.DIXIEME_HAUTEUR * 1;
	private final int DECALAGE_Y = CSG.DIXIEME_HAUTEUR  / 2;
	// -- -- Texte
	private final String txtVitesse = "Speed Up";
	private final String txtArme = "+Weapon";
	private final String txtRetour = "-Back";
	private final String txtXpDispo = "XP : ";
	// -- -- autre
	private SpriteBatch batch;
	private int ratioPolice = 2;
	private final int policeGrandeLargeur = CSG.LARGEUR_ECRAN / 200;
	private final int policeGrandeHauteur = CSG.HAUTEUR_ECRAN / 200;

	public DepenserXPOld(Game game) {
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
	}

	@Override
	public void render(float delta) {
		// ** ** clear screen
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		// ** ** batch
		batch.begin();
		// -- partie armes
		font.draw(batch, txtArme, X_GENERAL, Y_ARME);
		// -- partie vitesse
		font.draw(batch, txtVitesse, X_GENERAL, Y_VITESSE );
		fontXpDispo.draw(batch, "Cost : " + CSG.profil.getCoutVitesse(),
				CSG.LARGEUR_ECRAN - fontXpDispo.getBounds("Cost : " + CSG.profil.getCoutVitesse()).width - X_GENERAL, Y_VITESSE - (font.getBounds(txtVitesse).height / 2) );
		// -- partie retour
		font.draw(batch, txtRetour, X_GENERAL, Y_RETOUR );
		// -- xp dispo
		fontXpDispo.draw(batch, txtXpDispo+CSG.profil.xpDispo ,X_XP_DISPO, CSG.HAUTEUR_ECRAN);
		batch.end();
		// ** ** update
		update();
	}

	/*
	 * Sert à tester quel menu a été choisi et lancé l'écran correspondant si jamais. Appelée à chaque frame
	 */
	private void update() {
		if (Gdx.input.justTouched()) {
			// ** ** prend les coordonnées
			int touchX = Gdx.input.getX();
			int touchY = CSG.HAUTEUR_ECRAN - Gdx.input.getY();
			// ** ** test les menus
			if(Physique.pointIn(font.getBounds(txtArme), X_GENERAL, Y_ARME, touchX, touchY)){
				ArmesCustom armes = new ArmesCustom(game);
				game.setScreen(armes);
			}
			if(Physique.pointIn(font.getBounds(txtVitesse), X_GENERAL, Y_VITESSE, touchX, touchY)){
				if(CSG.profil.getCoutVitesse() <= CSG.profil.xpDispo)
					CSG.profil.upVitesse();
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
		// TODO Auto-generated method stub
		
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
