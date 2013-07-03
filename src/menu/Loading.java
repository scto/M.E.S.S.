package menu;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Loading implements Screen {
	
	private Game game;
	private SpriteBatch batch;
	private int orientation = 0, alterner = 0;
	private String loading = "Loading /";

	public Loading(CSG csg) {
		this.game = csg;
		CSG.assetMan.load();
		batch = CSG.batch;
	}

	@Override
	public void render(float delta) {
		if (!CSG.assetMan.fini()) {
			Gdx.graphics.getGL20().glClear(GL20.GL_COLOR_BUFFER_BIT);
			batch.begin();
			if (++alterner > 10) { // on bouge toutes les 10 frames pour le pas bouger trop vite
				alterner = 0;
				switch(orientation) {
				case 0:					loading = "Loading";					break;
				case 1:					loading = "lOading";					break;
				case 2:					loading = "loAding";					break;
				case 3:					loading = "loaDing";					break;
				case 4:					loading = "loadIng";					break;
				case 5:					loading = "loadiNg";					break;
				case 6:					loading = "loadinG";					break;
				case 7:					loading = "loading";					break;
				}
				if(++orientation > 7) orientation = 0;
			}
			CSG.menuFontPetite.draw(batch, loading, CSG.DEMI_LARGEUR_ECRAN - CSG.menuFontPetite.getBounds("Loading -").width / 2, CSG.DEMI_HAUTEUR_ECRAN - CSG.menuFontPetite.getBounds(loading).height / 2);
			batch.end();
		} else { // si il a fini le loading on change le menu
			CSG.assetMan.loadPartie2();
			Menu menu = new Menu(game);
			game.setScreen(menu);
		}
	}

	@Override
	public void resize(int width, int height) {		}

	@Override
	public void show() {	}

	@Override
	public void hide() {	}

	@Override
	public void pause() {	}

	@Override
	public void resume() {	}

	@Override
	public void dispose() {	}
}