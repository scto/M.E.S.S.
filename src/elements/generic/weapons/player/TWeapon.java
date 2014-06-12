package elements.generic.weapons.player;

import jeu.CSG;
import jeu.Physic;
import jeu.Stats;
import jeu.mode.EndlessMode;
import assets.AssetMan;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;

import elements.particular.particles.Particles;
import elements.particular.particles.individual.PrecalculatedParticles;

public class TWeapon extends PlayerWeapon implements Poolable{
	
	public static final int width = (int) MINWIDTH, halfWidth = width/2, height = (int) (width * 1.3f), halfHeight = height / 2, VITESSE_ANGLE = 5000;
	public static final float FIRERATETIR = initCadence(.099f, 6), FIRERATETIRLVL8 = initCadence(.03f, 7);
	public static final String LABEL = "armeHantee";
	public static final Pool<TWeapon> POOL = new Pool<TWeapon>(30) {		protected TWeapon newObject() {			return new TWeapon();		}	};
	private static boolean alterner = false;
	public float angle;
//	public static final Animated ANIMATED = initAnimation(4, 12);
	public static final float[] COLORS = {
		AssetMan.convertARGB(1, 0, 84f  / 255f, 73f  / 255f),
		AssetMan.convertARGB(1, 0, 162f / 255f, 140f / 255f),
		AssetMan.convertARGB(1, 0, 141f / 255f, 239f / 255f),
		AssetMan.convertARGB(1, 0, 218f / 255f, 228f / 255f),
		AssetMan.convertARGB(1, 0, 89f  / 255f, 252f / 255f),
		
		AssetMan.convertARGB(1, 0, 94f  / 255f, 73f  / 255f),
		AssetMan.convertARGB(1, 0, 172f / 255f, 140f / 255f),
		AssetMan.convertARGB(1, 0, 151f / 255f, 239f / 255f),
		AssetMan.convertARGB(1, 0, 228f / 255f, 228f / 255f),
		AssetMan.convertARGB(1, 0, 99f  / 255f, 252f / 255f),
		
		AssetMan.convertARGB(1, 0, 84f  / 255f, 83f  / 255f),
		AssetMan.convertARGB(1, 0, 162f / 255f, 150f / 255f),
		AssetMan.convertARGB(1, 0, 141f / 255f, 249f / 255f),
		AssetMan.convertARGB(1, 0, 218f / 255f, 238f / 255f),
		AssetMan.convertARGB(1, 0, 99f  / 255f, 255f / 255f),
		
		AssetMan.convertARGB(1, 0, 94f  / 255f, 83f  / 255f),
		AssetMan.convertARGB(1, 0, 172f / 255f, 150f / 255f),
		AssetMan.convertARGB(1, 0, 151f / 255f, 249f / 255f),
		AssetMan.convertARGB(1, 0, 228f / 255f, 238f / 255f),
		AssetMan.convertARGB(1, 0, 109f / 255f, 255f / 255f),
		
		AssetMan.convertARGB(1, 0, 62f  / 255f, 254f / 255f)};
	private static final float LIMITE = (CSG.SCREEN_HEIGHT / 4) / Stats.V_ARME_HANTEE;

	public void init(float posX, float posY) {
		pos.x = posX;
		pos.y = posY;
		PLAYER_LIST.add(this);
		dir.x = 0;
		dir.y = 1;
		dir.y *= Stats.V_ARME_HANTEE;
	}

	@Override
	public void reset() {
		dir.x = 0;
		dir.y = 1;
		dir.y *= Stats.V_ARME_HANTEE;
		super.reset();
	}

	@Override
	public void draw(SpriteBatch batch) {
		if (alterner && EndlessMode.triggerStop == false)
			Particles.armeHantee(this);
		batch.draw(AssetMan.tWeapon, pos.x, pos.y, halfWidth, halfHeight, width, height, 1f, 1f, angle);
	}

	@Override
	public boolean mouvementEtVerif() {
		angle += VITESSE_ANGLE * EndlessMode.delta;
		if (now > LIMITE && dir.y != 0) {
			if (alterner)
				dir.x = -dir.y;
			else
				dir.x = dir.y;
			dir.y = 0;
			alterner = !alterner;
		}
		return Physic.mvt(dir, pos, width);
	}

	@Override	public float getWidth() {				return width;										}
	@Override	public float getHeight() {				return height;										}
	@Override	public void free() {					POOL.free(this);									}
	@Override	public float getHalfWidth() {			return halfWidth;									}
	@Override	public float getHalfHeight() {			return halfHeight;									}
	@Override	public float getColor() {				return COLORS[R.nextInt(COLORS.length)];			}
	@Override	public float[] getColors() {			return PrecalculatedParticles.colorsOverTimeBlue;	}
}
