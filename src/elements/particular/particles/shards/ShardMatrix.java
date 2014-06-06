package elements.particular.particles.shards;


import jeu.Stats;
import assets.AssetMan;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;

public class ShardMatrix {
	
	public final int width, height;
	public final float pixelWidth, pixelHeight;
	public final ShardModel[] shards;
//	public static final ShardMatrix
//		ENEMY_ROTATION_1 = getShardMatrix("ennemirotation1.txt", Stats.WIDTH_QUI_TOURNE, Stats.WIDTH_QUI_TOURNE),
//		ENEMY_ROTATION_2 = getShardMatrix("ennemirotation2.txt", Stats.WIDTH_QUI_TOURNE, Stats.WIDTH_QUI_TOURNE),
//		ENEMY_ROTATION_3 = getShardMatrix("ennemirotation3.txt", Stats.WIDTH_QUI_TOURNE, Stats.WIDTH_QUI_TOURNE),
//		ENEMY_ROTATION_4 = getShardMatrix("ennemirotation4.txt", Stats.WIDTH_QUI_TOURNE, Stats.WIDTH_QUI_TOURNE),
//		ENEMY_ROTATION_5 = getShardMatrix("ennemirotation5.txt", Stats.WIDTH_QUI_TOURNE, Stats.WIDTH_QUI_TOURNE),
//		ENEMY_ROTATION_6 = getShardMatrix("ennemirotation6.txt", Stats.WIDTH_QUI_TOURNE, Stats.WIDTH_QUI_TOURNE),
//		ENEMY_ROTATION_7 = getShardMatrix("ennemirotation7.txt", Stats.WIDTH_QUI_TOURNE, Stats.WIDTH_QUI_TOURNE),
//		ENEMY_ROTATION_8 = getShardMatrix("ennemirotation8.txt", Stats.WIDTH_QUI_TOURNE, Stats.WIDTH_QUI_TOURNE);
//	public static final ShardMatrix
//		PLANE_BROKEN = getShardMatrix("avioncasse.txt", Stats.PLANE_WIDTH, Stats.PLANE_HEIGHT);
//	public static final ShardMatrix
//		BASIC_BLUE = getShardMatrix("basicenemyblue.txt", Stats.WIDTH_DE_BASE, Stats.HEIGHT_DE_BASE),
//		BASIC_GREEN = getShardMatrix("basicenemygreen.txt", Stats.WIDTH_DE_BASE, Stats.HEIGHT_DE_BASE),
//		BASIC_RED = getShardMatrix("basicenemyred.txt", Stats.WIDTH_DE_BASE, Stats.HEIGHT_DE_BASE);
//	public static final ShardMatrix
//		BOSS_QUAD = getShardMatrix("bossquad3.txt", Stats.WIDTH_BOSS_QUAD, Stats.HEIGHT_BOSS_QUAD);
//	public static final ShardMatrix
//		ADD_SAT1 = getShardMatrix("ennemiailesdeployees1.txt", Stats.WIDTH_ADD_BOSS_SAT, Stats.WIDTH_ADD_BOSS_SAT),
//		ADD_SAT2 = getShardMatrix("ennemiailesdeployees2.txt", Stats.WIDTH_ADD_BOSS_SAT, Stats.WIDTH_ADD_BOSS_SAT),
//		ADD_SAT3 = getShardMatrix("ennemiailesdeployees3.txt", Stats.WIDTH_ADD_BOSS_SAT, Stats.WIDTH_ADD_BOSS_SAT);
//	public static final ShardMatrix
//		ZIG_ZAG_BLUE1 = getShardMatrix("ennemizigzagblue1.txt", Stats.WIDTH_ZIG_ZAG, Stats.HEIGHT_ZIG_ZAG),
//		ZIG_ZAG_BLUE2 = getShardMatrix("ennemizigzagblue2.txt", Stats.WIDTH_ZIG_ZAG, Stats.HEIGHT_ZIG_ZAG),
//		ZIG_ZAG_BLUE3 = getShardMatrix("ennemizigzagblue3.txt", Stats.WIDTH_ZIG_ZAG, Stats.HEIGHT_ZIG_ZAG),
//		ZIG_ZAG_BLUE4 = getShardMatrix("ennemizigzagblue4.txt", Stats.WIDTH_ZIG_ZAG, Stats.HEIGHT_ZIG_ZAG),
//		ZIG_ZAG_BLUE5 = getShardMatrix("ennemizigzagblue5.txt", Stats.WIDTH_ZIG_ZAG, Stats.HEIGHT_ZIG_ZAG),
//		ZIG_ZAG_GREEN1 = getShardMatrix("ennemizigzaggreen1.txt", Stats.WIDTH_ZIG_ZAG, Stats.HEIGHT_ZIG_ZAG),
//		ZIG_ZAG_GREEN2 = getShardMatrix("ennemizigzaggreen2.txt", Stats.WIDTH_ZIG_ZAG, Stats.HEIGHT_ZIG_ZAG),
//		ZIG_ZAG_GREEN3 = getShardMatrix("ennemizigzaggreen3.txt", Stats.WIDTH_ZIG_ZAG, Stats.HEIGHT_ZIG_ZAG),
//		ZIG_ZAG_GREEN4 = getShardMatrix("ennemizigzaggreen4.txt", Stats.WIDTH_ZIG_ZAG, Stats.HEIGHT_ZIG_ZAG),
//		ZIG_ZAG_GREEN5 = getShardMatrix("ennemizigzaggreen5.txt", Stats.WIDTH_ZIG_ZAG, Stats.HEIGHT_ZIG_ZAG),
//		ZIG_ZAG_RED1 = getShardMatrix("ennemizigzagred1.txt", Stats.WIDTH_ZIG_ZAG, Stats.HEIGHT_ZIG_ZAG),
//		ZIG_ZAG_RED2 = getShardMatrix("ennemizigzagred2.txt", Stats.WIDTH_ZIG_ZAG, Stats.HEIGHT_ZIG_ZAG),
//		ZIG_ZAG_RED3 = getShardMatrix("ennemizigzagred3.txt", Stats.WIDTH_ZIG_ZAG, Stats.HEIGHT_ZIG_ZAG),
//		ZIG_ZAG_RED4 = getShardMatrix("ennemizigzagred4.txt", Stats.WIDTH_ZIG_ZAG, Stats.HEIGHT_ZIG_ZAG),
//		ZIG_ZAG_RED5 = getShardMatrix("ennemizigzagred5.txt", Stats.WIDTH_ZIG_ZAG, Stats.HEIGHT_ZIG_ZAG);
//	public static final ShardMatrix
//		FUSEE_BROKEN = getShardMatrix("fuseeamochee.txt", Stats.WIDTH_QUI_TIR, Stats.HEIGHT_QUI_TIR);
//	public static final ShardMatrix
//		INSECT = getShardMatrix("insectecasse.txt", Stats.WIDTH_INSECTE, Stats.WIDTH_INSECTE);
//	public static final ShardMatrix
//		KINDER1 = getShardMatrix("kinder1.txt", Stats.WIDTH_KINDER, Stats.HEIGHT_KINDER),
//		KINDER2 = getShardMatrix("kinder2.txt", Stats.WIDTH_KINDER, Stats.HEIGHT_KINDER),
//		KINDER3 = getShardMatrix("kinder3.txt", Stats.WIDTH_KINDER, Stats.HEIGHT_KINDER);
//	public static final ShardMatrix
//		PORTENEF = getShardMatrix("portenef2.txt", Stats.WIDTH_BOSS_SAT, Stats.WIDTH_BOSS_SAT);
//	public static final ShardMatrix
//		PORTERAISIN = getShardMatrix("porteraisin2.txt", Stats.WIDTH_PORTE_RAISIN, Stats.HEIGHT_PORTE_RAISIN);
	
	public ShardMatrix(int width, int height, ShardModel[] shards, float pixelWidth, float pixelHeight) {
		super();
		this.width = width;
		this.height = height;
		this.shards = shards;
		this.pixelWidth = pixelWidth;
		this.pixelHeight = pixelHeight;
	}
	
	private static ShardMatrix getShardMatrix(String string, float actualWidth, float actualHeight) {
		FileHandle fileHandle = Gdx.files.internal("shards/" + string);
		String s = fileHandle.readString();
		String[] lines = s.split(";");
		
		int spriteWidth = getInt(lines[0].split(" ")[0]);
		int spriteHeight = getInt(lines[0].split(" ")[1]);
		ShardModel[] shards = getShards(lines, actualWidth, actualHeight, spriteWidth, spriteHeight);
		return new ShardMatrix(spriteWidth, spriteHeight, shards, actualWidth / spriteWidth, actualHeight / spriteHeight);
	}

	private static ShardModel[] getShards(String[] lines, float actualWidth, float actualHeight, int spriteWidth, int spriteHeight) {
		int centerX = spriteWidth/2;
		int centerY = spriteHeight/2;
		Array<ShardModel> shards = new Array<ShardModel>();
		int cpt = 0;
		boolean alternate = false;
		for (String string : lines) {
			if (cpt++ == 0)
				continue;
//			alternate = !alternate;
//			if (alternate)
//				continue;
			String[] components = string.split(" ");
			int originalX = getInt(components[0]);
			int originalY = spriteHeight - getInt(components[1]);
			int initColor = getInt(components[2]);
			if (initColor == 0)
				continue;
			int initDirX = (int) (-(originalX - centerX) * Stats.UU);
			int initDirY = (int) ((originalY - centerY) * Stats.UU);
			float actualPosX = correspondingDimension(spriteWidth - (originalX - 1), actualWidth, spriteWidth);
			float actualPosY = correspondingDimension(originalY - 1, actualHeight, spriteHeight);
			shards.add(new ShardModel(actualPosX, actualPosY, translateColor(initColor), initDirX, initDirY));
		}
		return convert(shards);
	}

	private static float translateColor(int initColor) {
		float a = getA(initColor);
		float r = getR(initColor);
		float g = getG(initColor);
		float b = getB(initColor);
		return AssetMan.convertARGB(a, r, g, b);
	}
	
	public static float getB (int intBits) {
		return (intBits & 0xff)/255f;
	}
	
	public static float getG (int intBits) {
		return ((intBits >>> 8) & 0xff)/255f;
	}
	
	public static float  getR (int intBits) {
		return ((intBits >>> 16) & 0xff)/255f;
	}
	
	public static float getA (int intBits) {
		return ((intBits >>> 24) & 0xff)/255f;
	}

	private static ShardModel[] getShards(String s, String before, float width, float height, int spriteWidth, int spriteHeight) {
		String sub = s.substring(s.indexOf(before) + before.length(), s.length()-2);
		String[] pixels = sub.split("},");
		Array<ShardModel> shards = new Array<ShardModel>();
		for (String string : pixels) {
			shards.add(getShard(string, width, height, spriteWidth, spriteHeight));
		}
		return convert(shards);
	}

	private static ShardModel[] convert(Array<ShardModel> shards2) {
		ShardModel[] tmp = new ShardModel[shards2.size];
		int i = 0;
		for (ShardModel shardModel : shards2) {
			tmp[i++] = shardModel;
		}
		return tmp;
	}

	private static ShardModel getShard(String string, float width, float height, int spriteWidth, int spriteHeight) {
		int originalX = getInt(string, "{\"initX\":", ",\"initY");
		int originalY = getInt(string, "initY\":", ",\"initColor");
		int initColor = getInt(string, "initColor\":", ",\"initDirX");
		int initDirX = getInt(string, "initDirX\":", ",\"initDirY");
		int initDirY = getInt(string, "initDirY\":");
		float actualPosX = correspondingDimension(spriteWidth - (originalX - 1), width, spriteWidth);
		float actualPosY = correspondingDimension(originalY - 1, height, spriteHeight);
		return new ShardModel(actualPosX, actualPosY, Float.intBitsToFloat(initColor), initDirX, initDirY);
	}

	private static float correspondingDimension(int position, float actual, int initial) {
		float step = actual / initial;
		return position * step;
	}

	private static int getInt(String string, String before) {
		String subString = string.substring(string.indexOf(before) + before.length());
		return getInt(subString.replaceAll("}", ""));
	}

	private static int getInt(String string, String before, String after) {
		String subString = string.substring(string.indexOf(before) + before.length(), string.indexOf(after));
		return getInt(subString);
	}

	private static int getInt(String subString) {
		float rep = 0;
		rep = Float.valueOf(subString);
		return (int) rep;
	}
	
}
