package assets;

import menu.CSG;
import vaisseaux.armes.joueur.ArmeHantee;
import vaisseaux.armes.joueur.ArmesBalayage;
import vaisseaux.armes.joueur.ArmesDeBase;
import vaisseaux.armes.joueur.ArmesTrois;
import vaisseaux.ennemis.particuliers.nv1.Insecte;
import vaisseaux.ennemis.particuliers.nv1.QuiTir;
import vaisseaux.ennemis.particuliers.nv1.QuiTir2;
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
import assets.animation.AnimationKinder;
import assets.animation.AnimationMeteorite;
import assets.animation.AnimationMine;
import assets.animation.AnimationPorteNef;
import assets.animation.AnimationRouli;
import assets.animation.AnimationTirBleu;
import assets.animation.AnimationTirFeu;
import assets.animation.AnimationTirTrois;
import assets.animation.AnimationVaisseau;
import assets.background.ParallaxBackground;
import assets.background.ParallaxLayer;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetMan {

	public static TextureRegion fleche, laserVert, add, addvaisseau, bombe, bombeGris, temps, tempsGris, XP, XP2, porteraisin, porteraisinamoche, boulenergiebleu, bouclier, bonusetoile, bonusetoileGris, laser, rocher, poussiere, debris;
	public static ParticleEffect explosionGros, explosionPorteNef;
	private static AssetManager man = new AssetManager();
	public static TextureRegion[] animationVaisseau;
	public TextureRegion panneau, bouton;
	public TextureAtlas atlas;
	
	public void load() {
		man.clear();
		
		man.load("textures.atlas", TextureAtlas.class);
		man.setLoader(ParticleEffect.class, new ParticleEffectManager(new InternalFileHandleResolver()));
		man.load("explosiongros.p", ParticleEffect.class);
		man.load("explosionportenef.p", ParticleEffect.class);
		
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
		explosionGros = man.get("explosiongros.p", ParticleEffect.class);
		explosionPorteNef = man.get("explosionportenef.p", ParticleEffect.class);

		AnimationMine.initAnimation();
		Insecte.initAnimation();
		AnimationAvion.initAnimation();
		AnimationRouli.initAnimation();
		AnimationCylon.initAnimation();
		AnimationKinder.initAnimation();
		AnimationTirFeu.initAnimation();
		AnimationTirBleu.initAnimation();
		AnimationTirTrois.initAnimation();
		AnimationBossMine.initAnimation();
		AnimationVaisseau.initAnimation();
		AnimationBossQuad.initAnimation();
		AnimationPorteNef.initAnimation();
		AnimationMeteorite.initAnimation();
		AnimationArmeFusee.initAnimation();
		AnimationBouleBleu.initAnimation();
		QuiTir.initAnimation();
		QuiTir2.initAnimation();
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
		
		CSG.rbg = new ParallaxBackground(new ParallaxLayer[]{new ParallaxLayer(getAtlas().findRegion("etoilesnew1"), 0), new ParallaxLayer(getAtlas().findRegion("etoilesnew2"), 1f)});
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
}

