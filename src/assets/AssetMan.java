package assets;

import jeu.CSG;
import assets.animation.AnimBall;
import assets.animation.AnimPlayer;
import assets.animation.AnimationAvion;
import assets.animation.AnimationBossMine;
import assets.animation.AnimationBossQuad;
import assets.animation.AnimationBouleBleu;
import assets.animation.AnimationCylon;
import assets.animation.AnimationCylonCasse;
import assets.animation.AnimationEnnemiAileDeployee;
import assets.animation.AnimationEnnemiDeBase;
import assets.animation.AnimationEnnemiToupie;
import assets.animation.AnimationEnnemiTourne;
import assets.animation.AnimationInsecte;
import assets.animation.AnimationKinder;
import assets.animation.AnimationMeteorite;
import assets.animation.AnimationMine;
import assets.animation.AnimationOmbrelle;
import assets.animation.AnimationPorteNef;
import assets.animation.AnimationQuiTir;
import assets.animation.AnimationZigZag;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.NumberUtils;

import elements.generic.weapons.player.BlueSweepWeapon;
import elements.generic.weapons.player.Fireball;
import elements.generic.weapons.player.SunWeapon;

public final class AssetMan implements AssetErrorListener {

	public static TextureRegion add, addShip, bomb, bombGrey, blueDestroyer, blueDestroyerBroken, shield, stopBonus, stopBonusGrey, dust, debris, background, player, backgroundButton, shootingStar;
	public static TextureRegion iconDefaultW, iconTW, iconFireballW, iconSpreadW, iconSunW, iconSpaceInvW, tWeapon, fireball, star, effect, addBullet, addShipShot;
	public static TextureRegion basicenemyred, basicenemygreen, basicenemyblue;
	public final static AssetManager MAN = new AssetManager();
	private TextureAtlas atlas;
	public final static float WHITE = convertARGB(1, 1, 1, 1), ALPHA40 = convertARGB(.4f, 1, 1, 1), BLACK = convertARGB(1, 0, 0, 0), ALPHA70 = convertARGB(.70f, 1, 1, 1), RED = convertARGB(1, 1, 0, 0),
			CYAN00 = convertARGB(1, 0, 0.0f, 1),
			CYAN10 = convertARGB(1, 0, 0.1f, 1),
			CYAN20 = convertARGB(1, 0, 0.2f, 1),
			CYAN30 = convertARGB(1, 0, 0.3f, 1),
			CYAN40 = convertARGB(1, 0, 0.4f, 1),
			CYAN50 = convertARGB(1, 0, 0.5f, 1),
			CYAN60 = convertARGB(1, 0, 0.6f, 1),
			CYAN70 = convertARGB(1, 0, 0.7f, 1),
			CYAN80 = convertARGB(1, 0, 0.8f, 1),
			CYAN90 = convertARGB(1, 0, 0.9f, 1),
			GREEN = convertARGB(1, 0, 1, 0);
	private static final String ATLAS = "atlas/textures.atlas";
	
	public AssetMan() {
		MAN.setErrorListener(this);
	}
	
	public void load() {
		loadAtlas();
		loadSounds();
		loadMusics();
		Texture.setAssetManager(MAN);
	}

	private static void loadMusics() {
		MAN.load("sons/OutsideNorm.ogg", Music.class);
	}

	private static void loadSounds() {
		MAN.load("sons/162792__timgormly__8-bit-explosion1.wav", Sound.class);
		MAN.load("sons/explosionpetittetechercheuse.wav", Sound.class);
		MAN.load("sons/80500__ggctuk__exp-obj-large03.wav", Sound.class);
		MAN.load("sons/explosionboule.wav", Sound.class);
		MAN.load("sons/explosioncylon.wav", Sound.class);
		
		MAN.load("sons/explosionennemibasequitir.wav", Sound.class);
		MAN.load("sons/explosionkinder.wav", Sound.class);
		MAN.load("sons/47252__nthompson__rocketexpl.wav", Sound.class);
		MAN.load("sons/sun weapon.wav", Sound.class);
		MAN.load("sons/xp.wav", Sound.class);
		
		MAN.load("sons/bonus.wav", Sound.class); // bruit quand prend bonus
	}

	private static void loadAtlas() {
//		MAN.load("textures.atlas", TextureAtlas.class);
	}

	public boolean fini() {
		return MAN.update();
	}

	public void loadPartie2(boolean dimension) {
		setSounds();
		loadTextureRegions();
		loadAnims();
		
		if (dimension) {
			updateDimensions();
		}
	}

	private void updateDimensions() {
//		TWeapon.updateDimensions();
		Fireball.updateDimensions();
		BlueSweepWeapon.updateDimensions();
		SunWeapon.updateDimensions();
	}

	private void loadAnims() {
		AnimBall.initAnimation();
//		AnimTWeapon.initAnimation();
		AnimationMine.initAnimation();
		AnimationAvion.initAnimation();
		AnimationZigZag.initAnimation();
		AnimationCylon.initAnimation();
		AnimationQuiTir.initAnimation();
		AnimationKinder.initAnimation();
//		AnimationTirFeu.initAnimation();
		AnimationInsecte.initAnimation();
		AnimationOmbrelle.initAnimation();
		AnimationBossMine.initAnimation();
		AnimPlayer.initAnimation();
		AnimationBossQuad.initAnimation();
		AnimationPorteNef.initAnimation();
		AnimationMeteorite.initAnimation();
		AnimationBouleBleu.initAnimation();
		AnimationCylonCasse.initAnimation();
		AnimationEnnemiToupie.initAnimation();
		AnimationEnnemiTourne.initAnimation();
		AnimationEnnemiDeBase.initAnimation();
		AnimationEnnemiAileDeployee.initAnimation();
	}

	private static void loadTextureRegions() {
		add = getTextureRegion("add");
		star = getTextureRegion("star");
		bomb = getTextureRegion("bombe");
		player = getTextureRegion("player");
		debris = getTextureRegion("debris");
		effect = getTextureRegion("effect");
		dust = getTextureRegion("rondblanc");
		shield = getTextureRegion("bouclier");
		fireball = getTextureRegion("fireball");
		tWeapon = getTextureRegion("bluebullet");
		bombGrey = getTextureRegion("bombegris");
		addShip = getTextureRegion("addvaisseau");
		addBullet = getTextureRegion("addbullet");
		stopBonus = getTextureRegion("bonusetoile");
		background = getTextureRegion("spacefield");
		blueDestroyer = getTextureRegion("porteraisin1");
		shootingStar = getTextureRegion("etoilefilante");
		addShipShot = getTextureRegion("addvaisseaushot");
		stopBonusGrey = getTextureRegion("bonusetoilegris");
		blueDestroyerBroken = getTextureRegion("porteraisin2");
		backgroundButton = getTextureRegion("backgroundbutton");
		basicenemyblue = getTextureRegion("basicenemyblue");
		basicenemygreen = getTextureRegion("basicenemygreen");
		basicenemyred = getTextureRegion("basicenemyred");
		
		iconTW = getTextureRegion("tweapon80");
		iconSpreadW = getTextureRegion("spread80");
		iconSunW = getTextureRegion("secretsun80");
		iconDefaultW = getTextureRegion("default80");
		iconSpaceInvW = getTextureRegion("spaceinv80");
		iconFireballW = getTextureRegion("fireball80");
	}

	public static TextureRegion getTextureRegion(String string) {
		if (Gdx.app.getVersion() > 0) 
			return CSG.assetMan.getAtlas().findRegion(string);
		return new TextureRegion(new Texture(Gdx.files.local("c:\\mess\\" + string + ".png")));
	}

	private TextureAtlas getAtlas() {
		if (atlas == null) {
//			atlas = MAN.get("textures.atlas", TextureAtlas.class);
			atlas = new TextureAtlas(Gdx.files.internal(ATLAS));
		}
		return atlas;
	}

	public void reload(boolean dimension) {
		MAN.clear();
		if (CSG.profile.bloom)
			CSG.bloom.resume();
		atlas = null;
		load();
		while (!fini()){
		}
		loadPartie2(dimension);
	}
	
	private static final short MAX = 255, A = 24, R = 16, G = 8;
	public static float convertARGB(float a, float r, float g, float b) {
		return NumberUtils.intToFloatColor(((int)(MAX * a) << A) | ((int)(MAX * b) << R) | ((int)(MAX * g) << G) | ((int)(MAX * r)));
	}
	
	public static int tmpInt;
	public static float setAlpha(float color, float alpha) {
		tmpInt = NumberUtils.floatToIntColor(color);
		return convertARGB(alpha, (tmpInt & 0xff) / 255f, ((tmpInt >>> 8) & 0xff) / 255f, ((tmpInt >>> 16) & 0xff) / 255f);
	}

	public static void unload() {
		try {
			MAN.dispose();
			if (SoundMan.bigExplosion != null)			SoundMan.bigExplosion.dispose();
			if (SoundMan.explosion1 != null)			SoundMan.explosion1.dispose();
			if (SoundMan.explosion2 != null)			SoundMan.explosion2.dispose();
			if (SoundMan.explosion3 != null)			SoundMan.explosion3.dispose();
			if (SoundMan.explosion4 != null)			SoundMan.explosion4.dispose();
			if (SoundMan.explosion5 != null)			SoundMan.explosion5.dispose();
			if (SoundMan.explosion6 != null)			SoundMan.explosion6.dispose();
			if (SoundMan.tirRocket != null)				SoundMan.tirRocket.dispose();
			if (SoundMan.bonusTaken != null)			SoundMan.bonusTaken.dispose();
			if (SoundMan.sunWeapon != null)				SoundMan.sunWeapon.dispose();
			if (SoundMan.outsideNorm != null)			SoundMan.outsideNorm.dispose();
			CSG.menuFont.dispose();
			CSG.menuFontSmall.dispose();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void setSounds() {
		SoundMan.explosion2 = MAN.get("sons/explosionpetittetechercheuse.wav", Sound.class);
		SoundMan.explosion1 = MAN.get("sons/162792__timgormly__8-bit-explosion1.wav", Sound.class);
		SoundMan.bigExplosion = MAN.get("sons/80500__ggctuk__exp-obj-large03.wav", Sound.class);
		SoundMan.explosion3 = MAN.get("sons/explosionboule.wav", Sound.class);
		SoundMan.explosion4 = MAN.get("sons/explosioncylon.wav", Sound.class);
		SoundMan.sunWeapon = MAN.get("sons/sun weapon.wav", Sound.class);
		SoundMan.bonusTaken = MAN.get("sons/bonus.wav", Sound.class);
		SoundMan.explosion5 = MAN.get("sons/explosionennemibasequitir.wav", Sound.class);
		SoundMan.tirRocket = MAN.get("sons/47252__nthompson__rocketexpl.wav", Sound.class);
		SoundMan.explosion6 = MAN.get("sons/explosionkinder.wav", Sound.class);
		SoundMan.xp = MAN.get("sons/xp.wav", Sound.class);
		
		SoundMan.outsideNorm = MAN.get("sons/OutsideNorm.ogg", Music.class);
	}

	public static void resume() {
		loadAtlas();
		loadTextureRegions();
		loadSounds();
		loadMusics();
		while (!CSG.assetMan.fini() ) {
		}
	}

	@Override
	public void error(AssetDescriptor asset, Throwable throwable) {
		System.out.println("Probleme pour asset " + asset + " -------- " + throwable.getMessage());
	}

}

