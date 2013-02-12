package menu;

import jeu.Endless;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;


public class DepenserXP implements Screen {
	
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
	
	
	public DepenserXP(final Game game) {
	    fontXpDispo = new BitmapFont();
        fontXpDispo.setColor(Color.LIGHT_GRAY);
        fontXpDispo.setScale(policeGrandeLargeur / ratioPolice, policeGrandeHauteur / ratioPolice);
   
        // bricolé pour avoir un style par défaut j'espère
		Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
		
		batch = new SpriteBatch();
		stage = new Stage(CSG.LARGEUR_ECRAN, CSG.HAUTEUR_ECRAN, false);
		Gdx.input.setInputProcessor(stage);
		
		// creates the table actor
		table = new Table(skin);
		table.defaults().size(CSG.LARGEUR_ECRAN/1.2f, CSG.HAUTEUR_ECRAN/10f);
		// TITRE
		table.row();
		// register the button "start game"
        TextButton weaponGameButton = new TextButton( "Weapon", skin);
		// add the start-game button sized 300x60 with a margin-bottom of 10 units
		table.add( weaponGameButton ).uniform().spaceBottom( 10 ).pad(10);
//		// move to the next row
		table.row();
		final TextButton speedBouton = new TextButton( "Speed++    (Cost : " + CSG.profil.getCoutVitesse() + ")", skin);
		// add the options button in a cell similiar to the start-game button's cell
		table.add( speedBouton ).uniform().fill().spaceBottom( 10 );
		
		table.row();
		TextButton backButton = new TextButton( "Back", skin);
		// add the options button in a cell similiar to the start-game button's cell
		table.add( backButton ).uniform().fill().spaceBottom( 10 );
		
		table.setFillParent(true);
		
		stage.addActor(table);
		
		weaponGameButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				MenuArmes armes = new MenuArmes(game);
				game.setScreen(armes);
			}
		});
		
		speedBouton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if (CSG.profil.getCoutVitesse() <= CSG.profil.xpDispo)
					CSG.profil.upVitesse();
				speedBouton.setText("Speed++    (Cost : " + CSG.profil.getCoutVitesse() + ")");
			}
		});
		
		backButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Menu menu = new Menu(game);
				game.setScreen(menu);
			}
		});
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		CSG.renderBackground();
		Endless.delta = delta;
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
