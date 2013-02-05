package menu;

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
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;


public class MenuOptions implements Screen {
	
	private final int policeGrandeLargeur = CSG.LARGEUR_ECRAN / 200;
	private final int policeGrandeHauteur = CSG.HAUTEUR_ECRAN / 200;
	private final int X_XP_DISPO = CSG.DIXIEME_LARGEUR * 7;
	private final String txtXpDispo = "XP : ";
	private int ratioPolice = 2;
	// ---- champs rendu ----
	private SpriteBatch batch;
	// ---- Layout
	private Stage stage;
	private Table table;
	private BitmapFont fontXpDispo;
	
	
	public MenuOptions(final Game game) {
	    fontXpDispo = new BitmapFont();
        fontXpDispo.setColor(Color.LIGHT_GRAY);
        fontXpDispo.setScale(policeGrandeLargeur / ratioPolice, policeGrandeHauteur / ratioPolice);
   
        // bricolé pour avoir un style par défaut j'espère
		Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
		batch = new SpriteBatch();
		stage = new Stage(CSG.LARGEUR_ECRAN, CSG.HAUTEUR_ECRAN, false);
		Gdx.input.setInputProcessor(stage);
		table = new Table(skin);

		table.row();
		// SLIDER SON ARME
		Label sonArme = new Label("Weapons volume ", skin);
		table.add(sonArme).pad(5);
		final Slider sliderSonArme = new Slider(0, 1, .1f, false, skin);
		sliderSonArme.setValue(CSG.profil.volumeArme);
	    table.add(sliderSonArme).width(CSG.LARGEUR_ECRAN/2f);
		table.row();
		// SLIDER SON ARME
		Label son = new Label("Volume ", skin);
		table.add(son).pad(5);
		final Slider sliderSon = new Slider(0, 1, .1f, false, skin);
		sliderSon.setValue(CSG.profil.volumeBruitages);
	    table.add(sliderSon).width(CSG.LARGEUR_ECRAN/2f);
		table.row();
		// SLIDER MUSIQUE
		Label musique = new Label("Music volume ", skin);
		table.add(musique).pad(5);
		final Slider sliderMusique = new Slider(0, 1, .1f, false, skin);
		sliderMusique.setValue(CSG.profil.volumeMusique);
	    table.add(sliderMusique).width(CSG.LARGEUR_ECRAN/2f);
		table.row();
		// BOUTON CONTROLE
		TextButton controleButton = new TextButton( "Controle type", skin);
		table.add(controleButton).fill(true, false).pad(10);
		final Label controle = new Label(CSG.profil.getNomControle(), skin);
		table.add(controle).pad(5);
		table.row();
		// BOUTON BACK
		TextButton backButton = new TextButton( "Back", skin);
		table.add(backButton).fill(true, false).pad(10);
		
		table.setFillParent(true);
		
		stage.addActor(table);
		
		controleButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				CSG.profil.chgControle();
				CSG.profilManager.persist();
				controle.setText(CSG.profil.getNomControle());
			}
		});
		
		sliderSonArme.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				CSG.profil.volumeArme = sliderSonArme.getValue();
			}
		});
		
		sliderSon.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				CSG.profil.volumeBruitages = sliderSon.getValue();
			}
		});
		
		sliderMusique.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				CSG.profil.volumeMusique = sliderMusique.getValue();
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
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		CSG.renderBackground(delta);
		stage.act(delta);
		stage.draw();
		// -- xp dispo
		batch.begin();
		fontXpDispo.draw(batch, txtXpDispo+CSG.profil.xpDispo ,X_XP_DISPO, CSG.HAUTEUR_ECRAN - 10);
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

}
