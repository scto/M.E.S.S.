package assets.sprites;

import jeu.CSG;
import assets.AssetMan;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;

public enum Animations {
	
	ZIG_ZAG_RED(new Animated() {
		public TextureRegion getTexture(float x) {
			if (x < CSG.CINQUIEME_ZONE)
				return zigZagRed[0];
			if (x < CSG.DEUX_CINQUIEME_ZONE)
				return zigZagRed[1];
			if (x < CSG.TROIS_CINQUIEME_ZONE)
				return zigZagRed[2];
			if (x < CSG.QUATRE_CINQUIEME_ZONE)
				return zigZagRed[3];
			return zigZagRed[4];
		}
	}),
	ZIG_ZAG_BLUE(new Animated() {
		public TextureRegion getTexture(float x) {
			if (x < CSG.CINQUIEME_ZONE)
				return zigZagBlue[0];
			if (x < CSG.DEUX_CINQUIEME_ZONE)
				return zigZagBlue[1];
			if (x < CSG.TROIS_CINQUIEME_ZONE)
				return zigZagBlue[2];
			if (x < CSG.QUATRE_CINQUIEME_ZONE)
				return zigZagBlue[3];
			return zigZagBlue[4];
		}
	}),
	ZIG_ZAG_GREEN(new Animated() {
		public TextureRegion getTexture(float x) {
			if (x < CSG.CINQUIEME_ZONE)
				return zigZagGreen[0];
			if (x < CSG.DEUX_CINQUIEME_ZONE)
				return zigZagGreen[1];
			if (x < CSG.TROIS_CINQUIEME_ZONE)
				return zigZagGreen[2];
			if (x < CSG.QUATRE_CINQUIEME_ZONE)
				return zigZagGreen[3];
			return zigZagGreen[4];
		}
	}),
	PLANE_GOOD(new Animated() {				public TextureRegion getTexture(float tps) {			return planeGood;											}	}),
	PLANE_BAD(new Animated() {				public TextureRegion getTexture(float tps) {			return planeBad;											}	}),
	
	ROUND_N_ROUND(new Animated() {			public TextureRegion getTexture(float tps) {			return roundAndRound.getKeyFrame(tps, true);				}	}),
	
	SHOOTER_GOOD(new Animated() {			public TextureRegion getTexture(float tps) {			return quiTirGood;											}	}),
	SHOOTER_BAD(new Animated() {			public TextureRegion getTexture(float tps) {			return quiTirBad;											}	}),
	
	SAT_GOOD(new Animated() {				public TextureRegion getTexture(float tps) {			return satGood;												}	}),
	SAT_BAD(new Animated() {				public TextureRegion getTexture(float tps) {			return satBad;												}	}),
	
	KINDER_OPENING(new Animated() {			public TextureRegion getTexture(float tps) {			return kinderOpening.getKeyFrame(tps, false);				}	}),
	KINDER_OPEN(new Animated() {			public TextureRegion getTexture(float tps) {			return kinderOpen;											}	}),
	
	INSECT_GOOD(new Animated() {			public TextureRegion getTexture(float tps) {			return insectGood;											}	}),
	INSECT_BAD(new Animated() {				public TextureRegion getTexture(float tps) {			return insectBad;											}	}),
	
	DIABOLO(new Animated() {			public TextureRegion getTexture(float tps) {			return enemiTourne.getKeyFrame(tps, true);					}	}),
	
	BASIC_ENEMY_RED(6, new Animated() {		public TextureRegion getTexture(float tps) {			return basicEnemyRed;										}	}),
	BASIC_ENEMY_GREEN(7, new Animated() {	public TextureRegion getTexture(float tps) {			return basicEnemyGreen;										}	}),
	BASIC_ENEMY_BLUE(8, new Animated() {	public TextureRegion getTexture(float tps) {			return basicEnemyBlue;										}	}),
	
	AILE_DEPLOYEES(new Animated() {			public TextureRegion getTexture(float tps) {			return ailesDeployees.getKeyFrame(tps, false);				}	}),
	
	CYLON_RED_GOOD(new Animated() {			public TextureRegion getTexture(float tps) {			return cylonRedGood;										}	}),
	CYLON_RED_BAD(new Animated() {			public TextureRegion getTexture(float tps) {			return cylonRedBad;											}	}),
	CYLON_RED_WORST(new Animated() {		public TextureRegion getTexture(float tps) {			return cylonRedWorst;										}	}),
	CYLON_GREEN_GOOD(new Animated() {		public TextureRegion getTexture(float tps) {			return cylonGreenGood;										}	}),
	CYLON_GREEN_BAD(new Animated() {		public TextureRegion getTexture(float tps) {			return cylonGreenBad;										}	}),
	CYLON_GREEN_WORST(new Animated() {		public TextureRegion getTexture(float tps) {			return cylonGreenWorst;										}	}),
	CYLON_BLUE_GOOD(new Animated() {		public TextureRegion getTexture(float tps) {			return cylonBlueGood;										}	}),
	CYLON_BLUE_BAD(new Animated() {			public TextureRegion getTexture(float tps) {			return cylonBlueBad;										}	}),
	CYLON_BLUE_WORST(new Animated() {		public TextureRegion getTexture(float tps) {			return cylonBlueWorst;										}	}),
	
	QUAD_GOOD(new Animated() {			public TextureRegion getTexture(float tps) {			return bossQuadGood;										}	}),
	QUAD_BAD(new Animated() {			public TextureRegion getTexture(float tps) {			return bossQuadBad;											}	}),
	QUAD_WORST(new Animated() {		public TextureRegion getTexture(float tps) {			return bossQuadWorst;										}	}),
	
	BOSS_MINE_GOOD(new Animated() {			public TextureRegion getTexture(float tps) {			return bossMineGood;										}	}),
	BOSS_MINE_BAD(new Animated() {			public TextureRegion getTexture(float tps) {			return bossMineBad;											}	}),
	
	BLUE_BALL(new Animated() {				public TextureRegion getTexture(float tps) {			return blueBall.getKeyFrame(tps, true);						}	}),
	
	METEORITE(new Animated() {				public TextureRegion getTexture(float tps) {			return meteorite.getKeyFrame(tps, false);					}	}),
	METEORITE_SOLO(new Animated() {			public TextureRegion getTexture(float tps) {			return meteoriteSolo;										}	}),
	
	MINE(new Animated() {					public TextureRegion getTexture(float tps) {			return mine.getKeyFrame(tps, true);							}	}),
	
	BALL(9, new Animated() {					public TextureRegion getTexture(float tps) {			return ball.getKeyFrame(tps, true);							}	}),
	
	FIREBALL(new Animated() {				public TextureRegion getTexture(float tps) {			return fireball;							}	}),
	
	BLUE_CRUSADER_GOOD(new Animated() {		public TextureRegion getTexture(float tps) {			return blueCrusaderGood;									}	}),
	BLUE_CRUSADER_BAD(new Animated() {		public TextureRegion getTexture(float tps) {			return blueCrusaderBroken;									}	});
	
	private static TextureRegion planeGood, planeBad, quiTirGood, quiTirBad, satGood, satBad, kinderOpen, insectGood, insectBad, basicEnemyRed, basicEnemyBlue, basicEnemyGreen,
		cylonRedGood, cylonRedBad, cylonRedWorst, cylonBlueGood, cylonBlueBad, cylonBlueWorst, cylonGreenGood, cylonGreenBad, cylonGreenWorst, bossQuadGood, bossQuadBad, bossQuadWorst,
		bossMineGood, bossMineBad, blueCrusaderGood, blueCrusaderBroken, meteoriteSolo, fireball;
	private static TextureRegion[] zigZagRed, zigZagGreen, zigZagBlue;
	public static Animation roundAndRound, kinderOpening, enemiTourne, ball, ailesDeployees, blueBall, meteorite, mine;
	public static final float ROUND_N_ROUND_TIME = .04f, KINDER_TIME = 1.5f, KINDER_TIME_OPEN = KINDER_TIME * 2, ENEMI_TOURNE_TIME = 0.05f, BALL_TIME = 0.03f, AILE_DEPL_TIME = .3f, BLUE_BALL_TIME = 0.03f,
			METEORITE_TIME = .8f, METEORITE_TOTAL_TIME = METEORITE_TIME * 4, MINE_TIME = .1f;
	public Animated anim;
	public int pk;
	
	public static void initAnimations() {
		planeGood = AssetMan.getTextureRegion("avion");
		planeBad = AssetMan.getTextureRegion("avioncasse");
		
		zigZagRed = getTrArray("ennemizigzagred1","ennemizigzagred2","ennemizigzagred3","ennemizigzagred4","ennemizigzagred5");
		zigZagGreen = getTrArray("ennemizigzaggreen1","ennemizigzaggreen2","ennemizigzaggreen3","ennemizigzaggreen4","ennemizigzaggreen5");
		zigZagBlue = getTrArray("ennemizigzagblue1","ennemizigzagblue2","ennemizigzagblue3","ennemizigzagblue4","ennemizigzagblue5");
		
		roundAndRound = getAnimation(ROUND_N_ROUND_TIME, getTrArray("ennemirotation1","ennemirotation2","ennemirotation3","ennemirotation4","ennemirotation5","ennemirotation6","ennemirotation7","ennemirotation8"), Animation.PlayMode.NORMAL); 
		
		quiTirGood = AssetMan.getTextureRegion("fusee");
		quiTirBad = AssetMan.getTextureRegion("fuseeamochee");
		
		satGood = AssetMan.getTextureRegion("portenef1");
		satBad = AssetMan.getTextureRegion("portenef2");
		
		kinderOpening = getAnimation(KINDER_TIME, getTrArray("kinder3","kinder2"), Animation.PlayMode.NORMAL);
		kinderOpen = AssetMan.getTextureRegion("kinder1");
		
		insectGood = AssetMan.getTextureRegion("insecte");
		insectBad = AssetMan.getTextureRegion("insectecasse");
		
		
		enemiTourne = getAnimation(ENEMI_TOURNE_TIME, getTrArray("ennemitourne1","ennemitourne2","ennemitourne3","ennemitourne4","ennemitourne5","ennemitourne6","ennemitourne7","ennemitourne8"), Animation.PlayMode.NORMAL); 
		
		basicEnemyRed = AssetMan.getTextureRegion("basicenemyred");
		basicEnemyBlue = AssetMan.getTextureRegion("basicenemyblue");
		basicEnemyGreen = AssetMan.getTextureRegion("basicenemygreen");
		ball = getAnimation(BALL_TIME, getTrArray("ennemiboulebleubanderouge1","ennemiboulebleubanderouge2","ennemiboulebleubanderouge3"), Animation.PlayMode.LOOP_PINGPONG);
		
		ailesDeployees = getAnimation(AILE_DEPL_TIME, getTrArray("ennemiailesdeployees3","ennemiailesdeployees2","ennemiailesdeployees1"), Animation.PlayMode.NORMAL);
		
		cylonRedGood = AssetMan.getTextureRegion("cylon1");
		cylonRedBad = AssetMan.getTextureRegion("cylon2");
		cylonRedWorst = AssetMan.getTextureRegion("cylon3");
		cylonBlueGood = AssetMan.getTextureRegion("cylon1blue");
		cylonBlueBad = AssetMan.getTextureRegion("cylon2blue");
		cylonBlueWorst = AssetMan.getTextureRegion("cylon3blue");
		cylonGreenGood = AssetMan.getTextureRegion("cylon1green");
		cylonGreenBad = AssetMan.getTextureRegion("cylon2green");
		cylonGreenWorst = AssetMan.getTextureRegion("cylon3green");
		
		bossQuadGood = AssetMan.getTextureRegion("bossquad1");
		bossQuadBad = AssetMan.getTextureRegion("bossquad2");
		bossQuadWorst = AssetMan.getTextureRegion("bossquad3");
		
		bossMineGood =	AssetMan.getTextureRegion("bossmine");
		bossMineBad = AssetMan.getTextureRegion("bossminecasse");
		
		blueBall = getAnimation(BLUE_BALL_TIME, getTrArray("boulebleu1","boulebleu2"), Animation.PlayMode.LOOP_PINGPONG);
		
		meteorite = getAnimation(METEORITE_TIME, getTrArray("meteorite1","meteorite2","meteorite3","meteorite4"), Animation.PlayMode.LOOP_PINGPONG);
		meteoriteSolo = AssetMan.getTextureRegion("meteorite1");
		
		mine = getAnimation(MINE_TIME, getTrArray("mine1","mine2","mine3"), Animation.PlayMode.LOOP_PINGPONG);
		
		blueCrusaderGood = AssetMan.getTextureRegion("porteraisin1");
		blueCrusaderBroken = AssetMan.getTextureRegion("porteraisin2");
		
		fireball = AssetMan.getTextureRegion("fireball");
	}

	private static Animation getAnimation(float time, TextureRegion[] tr, PlayMode mode) {
		Animation a = new Animation(time, tr);
		a.setPlayMode(mode);
		return a;
	}

	private static TextureRegion[] getTrArray(String... images) {
		int i = 0;
		TextureRegion[] tr = new TextureRegion[images.length];
		for (String string : images) {
			tr[i++] = AssetMan.getTextureRegion(string);
		}
		return tr;
	}

	private Animations(Animated anim) {
		this.anim = anim;
	}
	
	private Animations(int pk, Animated anim) {
		this(anim);
		this.pk = pk;
	}
	
}
