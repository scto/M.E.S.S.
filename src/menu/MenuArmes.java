package menu;

import vaisseaux.armes.Armes;
import vaisseaux.joueur.VaisseauType1;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;


public class MenuArmes implements Screen {
	
	private final int policeGrandeLargeur = CSG.LARGEUR_ECRAN / 200;
	private final int policeGrandeHauteur = CSG.HAUTEUR_ECRAN / 200;
	private int ratioPolice = 2;
	private BitmapFont font;
	// -- -- Texte
	private final String txtXpDispo = "XP : ";
	// -- -- coordonnées
	private final int X_XP_DISPO = CSG.DIXIEME_LARGEUR * 7;
	// ---- champs rendu ----
	private SpriteBatch batch;
	// ---- Layout
	private Stage stage;
	private Table table;
	private BitmapFont fontXpDispo;
	// --- VAISSEAU
	private VaisseauType1 vaisseau = new VaisseauType1();
	private boolean alterner = true;
	
	
	public MenuArmes(final Game game) {
		CSG.resetLists();
		// -- font
		font = new BitmapFont();
        font.setColor(Color.GREEN);
        font.setScale(policeGrandeLargeur, policeGrandeHauteur);
        
        fontXpDispo = new BitmapFont();
        fontXpDispo.setColor(Color.LIGHT_GRAY);
        fontXpDispo.setScale(policeGrandeLargeur / ratioPolice, policeGrandeHauteur / ratioPolice);
   
        // bricolé pour avoir un style par défaut j'espère
		Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
		batch = new SpriteBatch();
		stage = new Stage(CSG.LARGEUR_ECRAN, CSG.HAUTEUR_ECRAN, false);
		Gdx.input.setInputProcessor(stage);
		table = new Table(skin);
		// BOUTON PREV ET NEXT
		Label vide = new Label(" ", skin);
		TextButton prevButton = new TextButton( "     <=     ", skin);
		table.add(prevButton).pad(130);
		TextButton nextButton = new TextButton( "     =>     ", skin);
		table.add(nextButton).pad(130);
		table.row();
		// Bon c'est con mais du moment que ça marche
		vide(vide);

		// BOUTON BACK
		TextButton backButton = new TextButton( "    Back    ", skin);
		table.add(backButton);

		// BOUTON UPGRADE
		final TextButton upgradeButton = new TextButton( " Upgrade (" + CSG.profil.getCoutUpArme() + ")", skin);
		table.add(upgradeButton);
		
		table.setFillParent(true);
		
		stage.addActor(table);
		
		nextButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				vaisseau.changerArme();
				CSG.profilManager.persist();
				upgradeButton.setText(" Upgrade (" + CSG.profil.getCoutUpArme() + ")");
			}
		});
		prevButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				vaisseau.changerArme();
				CSG.profilManager.persist();
				upgradeButton.setText(" Upgrade (" + CSG.profil.getCoutUpArme() + ")");
			}
		});
		backButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				CSG.profilManager.persist();
				Menu menu = new Menu(game);
				game.setScreen(menu);
			}
		});
		upgradeButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if(CSG.profil.getCoutUpArme() < CSG.profil.xpDispo){
					CSG.profil.xpDispo -= CSG.profil.getCoutUpArme();
					CSG.profil.upArme();
					upgradeButton.setText(" Upgrade (" + CSG.profil.getCoutUpArme() + ")");
					CSG.profilManager.persist();
				}
			}
		});
	}

	@Override
	public void render(float delta) {
		alterner = !alterner;
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		CSG.renderBackground(delta);
		stage.act(delta);
		stage.draw();
		
		
		batch.begin();
		// -- PREVIEW ARME
		vaisseau.draw(batch, delta);
		if (alterner) {
			vaisseau.tir(delta);
		}
		Armes.affichageEtMouvement(batch, delta);
		// -- xp dispo
		fontXpDispo.draw(batch, txtXpDispo + CSG.profil.xpDispo, X_XP_DISPO, CSG.HAUTEUR_ECRAN);
		
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		Gdx.app.log("Show xp","");
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void hide() {
		Gdx.app.log("Hide xp","");
		Gdx.input.setInputProcessor(null);
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
	
	private void vide(Label vide) {
		table.add(vide);
		table.row();
		table.add(vide);
		table.row();
		table.add(vide);
		table.row();
		table.add(vide);
		table.row();
		table.add(vide);
		table.row();
		table.add(vide);
		table.row();
		table.add(vide);
		table.row();
		table.add(vide);
		table.row();
		table.add(vide);
		table.row();
		table.add(vide);
		table.row();
		table.add(vide);
		table.row();
		table.add(vide);
		table.row();
		table.add(vide);
		table.row();
		table.add(vide);
		table.row();
	}


}
