package assets;

import objets.armes.joueur.ArmeHantee;
import objets.armes.joueur.ArmesBalayage;
import objets.armes.joueur.ArmesDeBase;
import objets.armes.joueur.ArmesTrois;
import assets.animation.AnimationArmeFusee;
import assets.animation.AnimationAvion;
import assets.animation.AnimationBossMine;
import assets.animation.AnimationBossQuad;
import assets.animation.AnimationBouleBleu;
import assets.animation.AnimationBouleBleuRouge;
import assets.animation.AnimationBouleVerte;
import assets.animation.AnimationCylon;
import assets.animation.AnimationCylonCasse;
import assets.animation.AnimationEnnemiAileDeployee;
import assets.animation.AnimationEnnemiDeBase;
import assets.animation.AnimationEnnemiToupie;
import assets.animation.AnimationEnnemiTourne;
import assets.animation.AnimationExplosion1;
import assets.animation.AnimationInsecte;
import assets.animation.AnimationKinder;
import assets.animation.AnimationMeteorite;
import assets.animation.AnimationMine;
import assets.animation.AnimationOmbrelle;
import assets.animation.AnimationPorteNef;
import assets.animation.AnimationQuiTir;
import assets.animation.AnimationRouli;
import assets.animation.AnimationTirBleu;
import assets.animation.AnimationTirFeu;
import assets.animation.AnimationTirTrois;
import assets.animation.AnimationVaisseau;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.NumberUtils;

public class AssetMan {

	public static TextureRegion fleche, laserVert, add, tirAdd, addvaisseau, bombe, bombeGris, temps, tempsGris, XP, XP2, porteraisin, porteraisinamoche, boulenergiebleu, bouclier, bonusetoile, bonusetoileGris, laser, rocher, poussiere, debris;
	public static ParticleEffect explosionGros, explosionPorteNef;
	private static AssetManager man = new AssetManager();
	public static TextureRegion[] animationVaisseau;
	public TextureRegion panneau, bouton;
	public TextureAtlas atlas;
	public static float WHITE = convertARGB(1, 1, 1, 1);
	
	public void load() {
		man.clear();
		
		man.load("textures.atlas", TextureAtlas.class);
//		man.setLoader(ParticleEffect.class, new ParticleEffectManager(new InternalFileHandleResolver()));
//		man.load("explosiongros.p", ParticleEffect.class);
//		man.load("explosionportenef.p", ParticleEffect.class);
		
		man.load("sons/162792__timgormly__8-bit-explosion1.wav", Sound.class);
		man.load("sons/explosionpetittetechercheuse.wav", Sound.class);
		man.load("sons/80500__ggctuk__exp-obj-large03.wav", Sound.class);
		man.load("sons/explosionboule.wav", Sound.class);
		man.load("sons/explosioncylon.wav", Sound.class);
		
		man.load("sons/explosionennemibasequitir.wav", Sound.class);
		man.load("sons/explosionkinder.wav", Sound.class);
		man.load("sons/47252__nthompson__rocketexpl.wav", Sound.class);
		man.load("sons/laser2.wav", Sound.class);
		man.load("sons/146733__fins__energy.wav", Sound.class);
		
		man.load("sons/tiravion.wav", Sound.class);
		man.load("sons/OutsideNorm.ogg", Music.class);
		Texture.setAssetManager(man);
	}

	public boolean fini() {
		return man.update();
	}

	public void loadPartie2() {
		SoundMan.explosionpetittetechercheuse = man.get("sons/explosionpetittetechercheuse.wav", Sound.class);
		SoundMan.explosionPetite = man.get("sons/162792__timgormly__8-bit-explosion1.wav", Sound.class);
		SoundMan.explosionGrosse = man.get("sons/80500__ggctuk__exp-obj-large03.wav", Sound.class);
		SoundMan.explosionboule = man.get("sons/explosionboule.wav", Sound.class);
		SoundMan.explosioncylon = man.get("sons/explosioncylon.wav", Sound.class);
		
		SoundMan.explosionennemidebasequitir = man.get("sons/explosionennemibasequitir.wav", Sound.class);
		SoundMan.tirRocket = man.get("sons/47252__nthompson__rocketexpl.wav", Sound.class);
		SoundMan.explosionkinder = man.get("sons/explosionkinder.wav", Sound.class);
		SoundMan.explosiontoupie = man.get("sons/explosionkinder.wav", Sound.class); //!\
		SoundMan.tirEnergie = man.get("sons/146733__fins__energy.wav", Sound.class);
		SoundMan.tirAvion = man.get("sons/tiravion.wav", Sound.class);
		SoundMan.tirLaser2 = man.get("sons/laser2.wav", Sound.class);
		
		SoundMan.outsideNorm = man.get("sons/OutsideNorm.ogg", Music.class);
		// **************** T E X T U R E   R E G I O N  ******************
		XP = getAtlas().findRegion("xp");
		XP2 = getAtlas().findRegion("xp2");
		add = getAtlas().findRegion("add");
		bombe = getAtlas().findRegion("bombe");
		temps = getAtlas().findRegion("temps");
		rocher = getAtlas().findRegion("rock");
		laser = getAtlas().findRegion("laser");
		fleche = getAtlas().findRegion("fleche");
		debris = getAtlas().findRegion("debris");
		tirAdd = getAtlas().findRegion("tiradd");
		panneau = getAtlas().findRegion("panneau");
		bouclier = getAtlas().findRegion("bouclier");
		bouton = getAtlas().findRegion("boutonlarge");
		tempsGris = getAtlas().findRegion("tempsgris");
		laserVert = getAtlas().findRegion("laservert");
		poussiere = getAtlas().findRegion("rondblanc");
		bombeGris = getAtlas().findRegion("bombegris");
		addvaisseau = getAtlas().findRegion("addvaisseau");
		bonusetoile = getAtlas().findRegion("bonusetoile");
		porteraisin = getAtlas().findRegion("porteraisin1");
		porteraisinamoche = getAtlas().findRegion("porteraisin2");
		bonusetoileGris = getAtlas().findRegion("bonusetoilegris");
		boulenergiebleu = getAtlas().findRegion("boulenergiebleu");
		// *********************** P A R T I C U L E S ******************************
//		explosionGros = man.get("explosiongros.p", ParticleEffect.class);
//		explosionPorteNef = man.get("explosionportenef.p", ParticleEffect.class);

		AnimationMine.initAnimation();
		AnimationInsecte.initAnimation();
		AnimationAvion.initAnimation();
		AnimationRouli.initAnimation();
		AnimationCylon.initAnimation();
		AnimationKinder.initAnimation();
		AnimationTirFeu.initAnimation();
		AnimationTirBleu.initAnimation();
		AnimationTirTrois.initAnimation();
		AnimationOmbrelle.initAnimation();
		AnimationBossMine.initAnimation();
		AnimationVaisseau.initAnimation();
		AnimationBossQuad.initAnimation();
		AnimationPorteNef.initAnimation();
		AnimationMeteorite.initAnimation();
		AnimationArmeFusee.initAnimation();
		AnimationBouleBleu.initAnimation();
		AnimationQuiTir.initAnimation();
		AnimationBouleVerte.initAnimation();
		AnimationCylonCasse.initAnimation();
		AnimationExplosion1.initAnimation();
		AnimationEnnemiToupie.initAnimation();
		AnimationEnnemiTourne.initAnimation();
		AnimationEnnemiDeBase.initAnimation();
		AnimationBouleBleuRouge.initAnimation();
		AnimationEnnemiAileDeployee.initAnimation();
		
		ArmesBalayage.updateDimensions();
		ArmeHantee.updateDimensions();
		ArmesTrois.updateDimensions();
		ArmesDeBase.updateDimensions();
	}

	public TextureAtlas getAtlas() {
		if (atlas == null) atlas = man.get("textures.atlas", TextureAtlas.class);
		return atlas;
	}

	public void reload() {
		man.unload("textures.atlas");
		atlas = null;
		load();
		while (!fini()){
			System.out.println("loading...");
		}
		loadPartie2();
	}
	
	public static float convertARGB(float a, float r, float g, float b) {
		int color = ((int)(255 * a) << 24) | ((int)(255 * b) << 16) | ((int)(255 * g) << 8) | ((int)(255 * r));
		return NumberUtils.intToFloatColor(color);
	}
}

