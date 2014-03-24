package assets;

import jeu.CSG;
import assets.animation.AnimBall;
import assets.animation.AnimPlayer;
import assets.animation.AnimTWeapon;
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
import assets.animation.AnimationFragWeapon;
import assets.animation.AnimationInsecte;
import assets.animation.AnimationKinder;
import assets.animation.AnimationMeteorite;
import assets.animation.AnimationMine;
import assets.animation.AnimationOmbrelle;
import assets.animation.AnimationPorteNef;
import assets.animation.AnimationQuiTir;
import assets.animation.AnimationRouli;
//import assets.animation.AnimationTirBleu;
import assets.animation.AnimationTirFeu;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.NumberUtils;

import elements.generic.weapons.player.BlueSweepWeapon;
import elements.generic.weapons.player.Fireball;
import elements.generic.weapons.player.PinkWeapon;
import elements.generic.weapons.player.SunWeapon;
import elements.generic.weapons.player.TWeapon;

public final class AssetMan implements AssetErrorListener {

	public static TextureRegion arrow, add, addShip, bomb, bombGrey, xp, xp2, blueDestroyer, blueDestroyerBroken, blueBullet, shield, stopBonus, stopBonusGrey, dust, debris, background, player, backgroundButton;
	public final static AssetManager MAN = new AssetManager();
	private TextureAtlas atlas;
	public final static float WHITE = convertARGB(1, 1, 1, 1);
	public final static float ALPHA40 = convertARGB(.4f, 1, 1, 1);
	private static final String ATLAS = "textures.atlas";
	
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
		
		MAN.load("sons/bonus.wav", Sound.class); // bruit quand prend bonus
	}

	private static void loadAtlas() {
		MAN.load("textures.atlas", TextureAtlas.class);
	}

	public boolean fini() {
		System.out.println(AssetMan.MAN.getProgress());
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
		TWeapon.updateDimensions();
		Fireball.updateDimensions();
		BlueSweepWeapon.updateDimensions();
		SunWeapon.updateDimensions();
	}

	private void loadAnims() {
		AnimBall.initAnimation();
		AnimTWeapon.initAnimation();
		AnimationMine.initAnimation();
		AnimationAvion.initAnimation();
		AnimationRouli.initAnimation();
		AnimationCylon.initAnimation();
		AnimationQuiTir.initAnimation();
		AnimationKinder.initAnimation();
		AnimationTirFeu.initAnimation();
		AnimationInsecte.initAnimation();
		AnimationOmbrelle.initAnimation();
		AnimationBossMine.initAnimation();
		AnimPlayer.initAnimation();
		AnimationBossQuad.initAnimation();
		AnimationPorteNef.initAnimation();
		AnimationMeteorite.initAnimation();
		AnimationBouleBleu.initAnimation();
		AnimationFragWeapon.initAnimation();
		AnimationCylonCasse.initAnimation();
		AnimationEnnemiToupie.initAnimation();
		AnimationEnnemiTourne.initAnimation();
		AnimationEnnemiDeBase.initAnimation();
		AnimationEnnemiAileDeployee.initAnimation();
	}

	private static void loadTextureRegions() {
		System.out.println("AssetMan.loadTextureRegions()");
		System.out.println("xp av : " + xp);
		xp = new TextureRegion(CSG.assetMan.getAtlas().findRegion("xp"));
		System.out.println("xp ap : " + xp);
		xp2 = CSG.assetMan.getAtlas().findRegion("xp2");
		add = CSG.assetMan.getAtlas().findRegion("add");
		bomb = CSG.assetMan.getAtlas().findRegion("bombe");
		arrow = CSG.assetMan.getAtlas().findRegion("fleche");
		debris = CSG.assetMan.getAtlas().findRegion("debris");
		dust = CSG.assetMan.getAtlas().findRegion("rondblanc");
		shield = CSG.assetMan.getAtlas().findRegion("bouclier");
		player = CSG.assetMan.getAtlas().findRegion("player");
		bombGrey = CSG.assetMan.getAtlas().findRegion("bombegris");
		addShip = CSG.assetMan.getAtlas().findRegion("addvaisseau");
		stopBonus = CSG.assetMan.getAtlas().findRegion("bonusetoile");
		background = CSG.assetMan.getAtlas().findRegion("spacefield");
		blueBullet = CSG.assetMan.getAtlas().findRegion("boulenergiebleu");
		blueDestroyer = CSG.assetMan.getAtlas().findRegion("porteraisin1");
		stopBonusGrey = CSG.assetMan.getAtlas().findRegion("bonusetoilegris");
		blueDestroyerBroken = CSG.assetMan.getAtlas().findRegion("porteraisin2");
		backgroundButton = CSG.assetMan.getAtlas().findRegion("backgroundbutton");
	}

	public TextureAtlas getAtlas() {
		if (atlas == null) {
			atlas = MAN.get("textures.atlas", TextureAtlas.class);
		}
		return atlas;
	}

	public void reload(boolean dimension) {
		MAN.dispose();
		if (CSG.profile.bloom)
			CSG.bloom.resume();
		atlas = null;
		load();
		while (!fini()){
			CSG.log("reload");
		}
		loadPartie2(dimension);
	}
	
	private static final short MAX = 255, A = 24, R = 16, G = 8;
	public static float convertARGB(float a, float r, float g, float b) {
		return NumberUtils.intToFloatColor(((int)(MAX * a) << A) | ((int)(MAX * b) << R) | ((int)(MAX * g) << G) | ((int)(MAX * r)));
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
			CSG.menuFontPetite.dispose();
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
		
		SoundMan.outsideNorm = MAN.get("sons/OutsideNorm.ogg", Music.class);
	}

	public static void resume() {
		loadAtlas();
		loadTextureRegions();
		loadSounds();
		loadMusics();
		System.out.println("RESUME");
		while (!CSG.assetMan.fini() ) {
			System.out.println("Loading");
		}
	}

	@Override
	public void error(AssetDescriptor asset, Throwable throwable) {
		System.out.println("Probleme pour asset " + asset + " -------- " + throwable.getMessage());
	}
}

