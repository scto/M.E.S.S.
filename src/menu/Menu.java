package menu;


import jeu.Endless;
import affichage.TexMan;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class Menu implements Screen {
	
	// ---- champs rendu ----
	private SpriteBatch batch;
	// ---- autre ----
	private Game game;
	private BitmapFont font;
	// ---- Layout
	private Stage stage;
	private Table table;

	/*
	 * Constructeur de base prenant game en parametre pour le rebalancer aux autres quand il charge. Il charge les textures dont il a besoin.
	 */
	public Menu(final Game game) {
		// bricolé pour avoir un style par défaut j'espère
		Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
		
		TexMan.loadGame();
        // -- autre
		batch = new SpriteBatch();
		this.game = game;
		//stage = new Stage();
		stage = new Stage(CSG.LARGEUR_ECRAN, CSG.HAUTEUR_ECRAN, false);
		Gdx.input.setInputProcessor(stage);

		// creates the table actor
		table = new Table(skin);
		table.defaults().size(CSG.LARGEUR_ECRAN/1.2f, CSG.HAUTEUR_ECRAN/10f);
		// add the welcome message with a margin-bottom of 50 units
		Label titre = new Label("MyShooter", skin);
		titre.setFontScale(CSG.LARGEUR_ECRAN/100);
		table.add(titre).expandX().top().uniform().pad(10).fill();
		// move to the next row
		table.row();
		// register the button "start game"
        TextButton startGameButton = new TextButton( "Start game", skin);
		// add the start-game button sized 300x60 with a margin-bottom of 10 units
		table.add( startGameButton ).uniform().spaceBottom( 10 ).pad(10);
//		// move to the next row
		table.row();
		TextButton xpBouton = new TextButton( "XP", skin);
		// add the options button in a cell similiar to the start-game button's cell
		table.add( xpBouton ).uniform().fill().spaceBottom( 10 );
		
		table.row();
		TextButton optionsButton = new TextButton( "Options", skin);
		// add the options button in a cell similiar to the start-game button's cell
		table.add( optionsButton ).uniform().fill().spaceBottom( 10 );
		// move to the next row
		table.row();
		TextButton highScoresButton = new TextButton( "Highscores", skin);
		// add the high-scores button in a cell similiar to the start-game button's cell
		table.add( highScoresButton ).uniform().fill();
		table.setFillParent(true);

		
		
		stage.addActor(table);
		
		
        startGameButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Endless endless = new Endless(game);
				game.setScreen(endless);
				super.clicked(event, x, y);
			}
        } );
        xpBouton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				DepenserXP xp = new DepenserXP(game);
				game.setScreen(xp);
				super.clicked(event, x, y);
			}
        });

	}

	@Override
	public void render(float delta) {
		// ** ** clear screen
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		stage.act(delta);
		stage.draw();
	}


	@Override
	public void resize(int width, int height) {
		Gdx.app.log("resize","");
	}

	@Override
	public void show() {
		Gdx.app.log("Show menu","");
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void hide() {
		Gdx.app.log("Hide menu","");
		Gdx.input.setInputProcessor(null);
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
	

	@Override
	public void dispose() {
		batch.dispose();
	}
}
