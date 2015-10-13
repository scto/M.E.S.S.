package menu.screens;

import jeu.CSG;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class Loading extends AbstractScreen {
	
	private int alterner = 0, cpt = 0;
	private String loading[] = {"Loading", "lOading", "loAding", "loaDing", "loadIng", "loadiNg", "loadinG", "loading"};

	public Loading(Game csg) {
		super(csg);
		CSG.assetMan.load();
	}

	@Override
	public void render(float delta) {
		if (!CSG.assetMan.fini()) {
			afficherLoading();
		} else { // si il a fini le loading on change le menu
			CSG.assetMan.loadPartie2();
			final Menu menu = new Menu(game);
			game.setScreen(menu);
		}
	}

	private void afficherLoading() {
		if (++alterner > 10) { // on bouge toutes les 10 frames pour le pas bouger trop vite
			alterner = 0;
			if (++cpt >= loading.length)
				cpt = 0;
		}
		Gdx.graphics.getGL20().glClear(GL20.GL_COLOR_BUFFER_BIT);
		CSG.batch.begin();
		CSG.menuFont.draw(CSG.batch, loading[cpt], CSG.halfWidth - CSG.menuFont.getBounds("Loading -").width / 2, CSG.halfHeight - CSG.menuFontSmall.getBounds("Loading -").height / 2);
		CSG.batch.end();
	}

	@Override	public void show() {	}
	@Override	public void hide() {	}
	@Override	public void pause() {	}
	@Override	public void resize(int w, int h) {		}
	@Override	public void resume() {	}
	@Override	public void dispose() {	}
}
