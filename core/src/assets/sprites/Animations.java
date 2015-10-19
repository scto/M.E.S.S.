package assets.sprites;

import behind.Gps;
import jeu.CSG;
import assets.AssetMan;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public enum Animations {

	ZIG_ZAG_RED(new Animated() {
		public TextureRegion getTexture(AnimUser u) {
            return zigZagRed[CSG.gm.gps().locateVertical5Portions(u.getPosition().x)];
		}
	}),
    ZIG_ZAG_BLUE(new Animated() {
		public TextureRegion getTexture(AnimUser u) {
            return zigZagBlue[CSG.gm.gps().locateVertical5Portions(u.getPosition().x)];
		}
	}),
    ZIG_ZAG_GREEN(new Animated() {
		public TextureRegion getTexture(AnimUser u) {
            return zigZagGreen[CSG.gm.gps().locateVertical5Portions(u.getPosition().x)];
		}
	}), PLANE(new Animated() {
		public TextureRegion getTexture(AnimUser u) {
			if (u.isInGoodShape())
				return planeGood;
			return planeBad;
		}
	}), ROUND_N_ROUND(new Animated() {
		public TextureRegion getTexture(AnimUser u) {
			return roundAndRound.getKeyFrame(u.getNow(), true);
		}
	}), SHOOTER(new Animated() {
		public TextureRegion getTexture(AnimUser u) {
			if (u.isInGoodShape())
				return quiTirGood;
			return quiTirBad;
		}
	}), T_WEAPON(new Animated() {
		@Override
		public TextureRegion getTexture(AnimUser a) {
			return AssetMan.tWeapon;
		}
	}), SAT(new Animated() {
		public TextureRegion getTexture(AnimUser u) {
			if (u.isInGoodShape())
				return satGood;
			return satBad;
		}
	}), KINDER(new Animated() {
		public TextureRegion getTexture(AnimUser u) {
			return kinder.getKeyFrame(u.getNow(), false);
		}
	}), INSECT(new Animated() {
		public TextureRegion getTexture(AnimUser u) {
			if (u.isInGoodShape())
				return insectGood;
			return insectBad;
		}
	}), DIABOLO(new Animated() {
		public TextureRegion getTexture(AnimUser u) {
			return enemiTourne.getKeyFrame(u.getNow(), true);
		}
	}), BASIC_ENEMY_RED(new Animated() {
		public TextureRegion getTexture(AnimUser u) {
			return basicEnemyRed;
		}
	}), BASIC_ENEMY_GREEN(new Animated() {
		public TextureRegion getTexture(AnimUser u) {
			return basicEnemyGreen;
		}
	}), BASIC_ENEMY_BLUE(new Animated() {
		public TextureRegion getTexture(AnimUser u) {
			return basicEnemyBlue;
		}
	}), AILE_DEPLOYEES(new Animated() {
		public TextureRegion getTexture(AnimUser u) {
			return ailesDeployees.getKeyFrame(u.getNow(), false);
		}
	}), CYLON_RED(new Animated() {
		public TextureRegion getTexture(AnimUser u) {
			return cylonRed[u.getAnimIndex()];
		}
	}), CYLON_GREEN(new Animated() {
		public TextureRegion getTexture(AnimUser u) {
			return cylonGreen[u.getAnimIndex()];
		}
	}), CYLON_BLUE(new Animated() {
		public TextureRegion getTexture(AnimUser u) {
			return cylonBlue[u.getAnimIndex()];
		}
	}), QUAD(new Animated() {
		public TextureRegion getTexture(AnimUser u) {
			return bossQuad[u.getAnimIndex()];
		}
	}), BOSS_MINE(new Animated() {
		public TextureRegion getTexture(AnimUser u) {
			if (u.isInGoodShape())
				return bossMineGood;
			return bossMineBad;
		}
	}), BLUE_BALL(new Animated() {
		public TextureRegion getTexture(AnimUser u) {
			return blueBall.getKeyFrame(u.getNow(), true);
		}
	}), METEORITE(new Animated() {
		public TextureRegion getTexture(AnimUser u) {
			return meteorite.getKeyFrame(u.getNow(), false);
		}
	}), FRAG_METEORITE(new Animated() {
		public TextureRegion getTexture(AnimUser u) {
			return meteoriteSolo;
		}
	}), MINE(new Animated() {
		public TextureRegion getTexture(AnimUser u) {
			return mine.getKeyFrame(u.getNow(), true);
		}
	}), BALL(new Animated() {
		public TextureRegion getTexture(AnimUser u) {
			return ball.getKeyFrame(u.getNow(), true);
		}
	}), FIREBALL(new Animated() {
		public TextureRegion getTexture(AnimUser u) {
			return fireball;
		}
	}), BLUE_CRUSADER(new Animated() {
		public TextureRegion getTexture(AnimUser u) {
			if (u.isInGoodShape())
				return blueCrusaderGood;
			return blueCrusaderBroken;
		}
	});

	private static TextureRegion planeGood, planeBad, quiTirGood, quiTirBad, satGood, satBad, insectGood, insectBad, basicEnemyRed, basicEnemyBlue, basicEnemyGreen, bossMineGood,
			bossMineBad, blueCrusaderGood, blueCrusaderBroken, meteoriteSolo, fireball;
	private static TextureRegion[] zigZagRed, zigZagGreen, zigZagBlue, cylonRed, cylonBlue, cylonGreen, bossQuad;
	public static Animation roundAndRound, kinder, enemiTourne, ball, ailesDeployees, blueBall, meteorite, mine;
	public static final float ROUND_N_ROUND_TIME = .04f, KINDER_TIME = 1.5f, KINDER_TIME_OPEN = KINDER_TIME * 2, ENEMI_TOURNE_TIME = 0.05f, BALL_TIME = 0.03f, AILE_DEPL_TIME = .3f, BLUE_BALL_TIME = 0.03f, METEORITE_TIME = .8f, METEORITE_TOTAL_TIME = METEORITE_TIME * 4, MINE_TIME = .1f;
	public Animated anim;

	public static void initAnimations() {
		planeGood = AssetMan.getTextureRegion("avion");
		planeBad = AssetMan.getTextureRegion("avioncasse");

		zigZagRed = getTrArray("ennemizigzagred1", "ennemizigzagred2", "ennemizigzagred3", "ennemizigzagred4", "ennemizigzagred5");
		zigZagGreen = getTrArray("ennemizigzaggreen1", "ennemizigzaggreen2", "ennemizigzaggreen3", "ennemizigzaggreen4", "ennemizigzaggreen5");
		zigZagBlue = getTrArray("ennemizigzagblue1", "ennemizigzagblue2", "ennemizigzagblue3", "ennemizigzagblue4", "ennemizigzagblue5");
		

		roundAndRound = getAnimation(ROUND_N_ROUND_TIME, getTrArray("ennemirotation1", "ennemirotation2", "ennemirotation3", "ennemirotation4", "ennemirotation5", "ennemirotation6", "ennemirotation7", "ennemirotation8"), Animation.PlayMode.NORMAL);

		quiTirGood = AssetMan.getTextureRegion("fusee");
		quiTirBad = AssetMan.getTextureRegion("fuseeamochee");

		satGood = AssetMan.getTextureRegion("portenef1");
		satBad = AssetMan.getTextureRegion("portenef2");

		kinder = getAnimation(KINDER_TIME, getTrArray("kinder3", "kinder2", "kinder1"), Animation.PlayMode.NORMAL);

		insectGood = AssetMan.getTextureRegion("insecte");
		insectBad = AssetMan.getTextureRegion("insectecasse");

		enemiTourne = getAnimation(ENEMI_TOURNE_TIME, getTrArray("ennemitourne1", "ennemitourne2", "ennemitourne3", "ennemitourne4", "ennemitourne5", "ennemitourne6", "ennemitourne7", "ennemitourne8"), Animation.PlayMode.NORMAL);

		basicEnemyRed = AssetMan.getTextureRegion("basicenemyred");
		basicEnemyBlue = AssetMan.getTextureRegion("basicenemyblue");
		basicEnemyGreen = AssetMan.getTextureRegion("basicenemygreen");
		ball = getAnimation(BALL_TIME, getTrArray("ennemiboulebleubanderouge1", "ennemiboulebleubanderouge2", "ennemiboulebleubanderouge3"), Animation.PlayMode.LOOP_PINGPONG);

		ailesDeployees = getAnimation(AILE_DEPL_TIME, getTrArray("ennemiailesdeployees3", "ennemiailesdeployees2", "ennemiailesdeployees1"), Animation.PlayMode.NORMAL);

		cylonRed = getTrArray("cylon1", "cylon2", "cylon3");
		cylonBlue = getTrArray("cylon1blue", "cylon2blue", "cylon3blue");
		cylonGreen = getTrArray("cylon1green", "cylon2green", "cylon3green");

		bossQuad = getTrArray("bossquad1", "bossquad2","bossquad3");

		bossMineGood = AssetMan.getTextureRegion("bossmine");
		bossMineBad = AssetMan.getTextureRegion("bossminecasse");
		
		blueBall = getAnimation(BLUE_BALL_TIME, getTrArray("boulebleu1", "boulebleu2"), Animation.PlayMode.LOOP_PINGPONG);

		meteorite = getAnimation(METEORITE_TIME, getTrArray("meteorite1", "meteorite2", "meteorite3", "meteorite4"), Animation.PlayMode.LOOP_PINGPONG);
		meteoriteSolo = AssetMan.getTextureRegion("meteorite1");

		mine = getAnimation(MINE_TIME, getTrArray("mine1", "mine2", "mine3"), Animation.PlayMode.LOOP_PINGPONG);

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
		for (String string : images)
			tr[i++] = AssetMan.getTextureRegion(string);
		return tr;
	}

	private Animations(Animated anim) {
		this.anim = anim;
	}

}
