package menu.splash;

import java.util.ArrayList;
import java.util.List;

import menu.CSG;
import vaisseaux.ennemis.particuliers.EnnemiDeBase;
import vaisseaux.joueur.VaisseauType1;
import affichage.TexMan;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Splashscreen implements Screen {
	
	private Game game;
	private SpriteBatch batch;
	private static List<EnnemiSplash> liste = new ArrayList<EnnemiSplash>();
	private float tps;

	public Splashscreen(Game game) {
		new EnnemiSplash();
		this.game = game;
		batch = new SpriteBatch();
		liste.add(new EnnemiSplash());
		Gdx.graphics.setVSync(false);
	}

	@Override
	public void render(float delta) {
		tps += delta;
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		
		for(int e = 0; e < liste.size(); e++)
			if(!liste.get(e).afficher(batch, delta))
				liste.remove(e);
		
		batch.end();
		//if(tps > .1){
			liste.add(new EnnemiSplash());
			tps = 0;
		//}
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
