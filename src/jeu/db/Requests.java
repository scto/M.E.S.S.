package jeu.db;

import java.util.ArrayList;
import java.util.List;

import jeu.CSG;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import elements.generic.Invocable;
import elements.generic.components.enemies.Merlin;
import elements.generic.enemies.SpawnEnemyPosition;
import elements.generic.enemies.Wave;
import elements.generic.enemies.individual.bosses.AddBossStat;
import elements.generic.enemies.individual.lvl1.Ball;
import elements.generic.enemies.individual.lvl1.Cylon;
import elements.generic.enemies.individual.lvl1.DeBase;
import elements.generic.enemies.individual.lvl1.Group;
import elements.generic.enemies.individual.lvl1.Insect;
import elements.generic.enemies.individual.lvl1.Kinder;
import elements.generic.enemies.individual.lvl1.Laser;
import elements.generic.enemies.individual.lvl1.Plane;
import elements.generic.enemies.individual.lvl1.Crusader;
import elements.generic.enemies.individual.lvl1.QuiTir;
import elements.generic.enemies.individual.lvl1.QuiTirTriangle;
import elements.generic.enemies.individual.lvl1.QuiTourne;
import elements.generic.enemies.individual.lvl1.RoundAndRound;
import elements.generic.enemies.individual.lvl1.Vicious;
import elements.generic.enemies.individual.lvl1.ZigZag;

public class Requests {
	
	private static final String WEAPON_FIRERATE = "Select firerate from weapons where pk = "; 
	private static final String ENEMY_SPEED = "Select speed from enemy where pk = "; 
	private static final String ENEMY_WEAPON = "Select fk_weapons from enemy where pk = "; 
	private static final String ENEMY_WEAPON_SPEED = "Select speed from weapons where pk = "; 
	private static final String ENEMY_FIRERATE = "Select firerate from enemy where pk = "; 
	private static final String ENEMY_WEAPON_ANIMATION = "Select fk_animation from weapons where pk = "; 
	private static final String ENEMY_BEHAVIOR = "Select fk_behavior from enemy where pk = "; 
	private static final String ENEMY_NEXTSHOT = "Select nextShot from enemy where pk = "; 
	private static final String ENEMY_POSITIONNEMENT = "Select fk_positionning from enemy where pk = "; 
	private static final String ENEMY_HP = "Select pv from enemy where pk = "; 
	private static final String ENEMY_XP = "Select xp from enemy where pk = "; 
	private static final String ENEMY_EXPLOSION = "Select explosion from enemy where pk = "; 
	public static final String WAVE = "select scoremin, interval, boss, freq, ordered, scoremax, order_spawn, fk_enemies, fk_position from wave " + 
									"inner join wave_spawn on wave.pk = ? and wave_spawn.fk_wave = wave.pk " +
									"inner join spawn on spawn.pk = wave_spawn.fk_spawn " +
									"inner join spawn_enemies_position on spawn_enemies_position.fk_spawn = spawn.pk "+
									"inner join enemy_position on enemy_position.pk = spawn_enemies_position.fk_enemies_position"
									+ " order by order_spawn asc"; 
	
	public static float getFireRate(int pk, float def) {
		return CSG.dbManager.getFloat(WEAPON_FIRERATE + pk, def);
	}
	
	public static float getNextShot(int pk, float def) {
		return CSG.dbManager.getFloat(ENEMY_NEXTSHOT + pk, def);
	}
	
	public static float getSpeed(int pk, float def) {
		return CSG.dbManager.getFloat(ENEMY_SPEED + pk, def);
	}
	
	public static float getFireRateEnemy(int pk, float def) {
		return CSG.dbManager.getFloat(ENEMY_FIRERATE + pk, def);
	}
	
	public static int getPvEnemy(int pk, int def) {
		return CSG.dbManager.getInt(ENEMY_HP + pk, def);
	}
	
	public static int getXpEnemy(int pk, int def) {
		return CSG.dbManager.getInt(ENEMY_XP + pk, def);
	}

	public static int getExplosionEnemy(int pk, int def) {
		return CSG.dbManager.getInt(ENEMY_EXPLOSION + pk, def);
	}

	public static int getAnimation(int pk, int def) {
		return CSG.dbManager.getInt(ENEMY_WEAPON_ANIMATION + pk, def);
	}
	
	public static int getPositionnement(int pk, int def) {
		return CSG.dbManager.getInt(ENEMY_POSITIONNEMENT + pk, def);
	}
	
	public static int getBehavior(int pk, int def) {
		return CSG.dbManager.getInt(ENEMY_BEHAVIOR + pk, def);
	}

	public static float getSpeedEnemyWeapon(int pk, float def) {
		return CSG.dbManager.getFloat(ENEMY_WEAPON_SPEED + pk, def);
	}

	public static int getEnemyWeapon(int pk, int def) {
		return CSG.dbManager.getInt(ENEMY_WEAPON + pk, def);
	}
    
	public static Wave getWave(int pk, Wave def) {
		final Wave wave = CSG.dbManager.getWave(pk, def);
		if (wave != null) {
			printWave(pk, wave);
			return wave;
		}
		System.err.println("wave null");
		return def;
	}

	private static void printWave(int pk, final Wave wave) {
		System.out.println("------------------------------------------------- " + pk + " score min : " + wave.scoreMin + " / max " + wave.maxScore);
		SpawnEnemyPosition[] spawns = wave.lignes;
		for (SpawnEnemyPosition spawnEnemyPosition : spawns) {
			if (spawnEnemyPosition.merlin.length == 0)
				System.out.println("pause");
			int i = 1;
			for (Vector2 pos : spawnEnemyPosition.positions) {
				System.out.println(i + " pos : " + pos.toString());
				i++;
			}
			int j = 1;
			for (Merlin pos : spawnEnemyPosition.merlin) {
				System.out.println(j + " inv : " + pos.incantation.invoke().getXp());
				j++;
			}
		}
		System.out.println(pk + " -------------------------------------------------");
	}

	public static void waveDb(Wave def) {
		final int PK = 7;
		System.out.println("-------------------------------------------------------------");
		System.out.println("insert into wave (pk, scoremin, interval, boss, freq, ordered, scoremax, fk_lvl) values (" + PK + "," + def.scoreMin + "," + def.espaceActivation + "," + def.boss + "," + def.freqSpawn + "," + def.ordered + "," + def.maxScore + ",1);");
		int i = 1;
		for (SpawnEnemyPosition ligne : def.lignes) {
			List<Integer> fkEnemies = new ArrayList<Integer>();
			List<Integer> fkPos = new ArrayList<Integer>();
			List<Integer> enemyPos = new ArrayList<Integer>();
			populatePkEnemies(fkEnemies, ligne.merlin);
			populatePkPositions(fkPos, ligne.positions);
			getEnemyPos(fkEnemies, fkPos, enemyPos);
			for (Integer integer : enemyPos) {
				System.out.println("insert into spawn_enemies_position (order_spawn, fk_enemies_position, fk_spawn) values (" + i + "," + integer + "," + PK + ");" );
			}
			i++;
		}
	}

	private static void getEnemyPos(List<Integer> fkEnemies, List<Integer> fkPos, List<Integer> enemyPos) {
		for (int i = 0; i < fkEnemies.size(); i++) {
			enemyPos.add(getEnemyPos(fkEnemies.get(i), fkPos.get(i)));
		}
	}

	private static Integer getEnemyPos(Integer enemy, Integer pos) {
//		System.out.println("Enemy : " + enemy + "  pos : " + pos);
		switch (enemy) {
		case 1:
			switch (pos) {
				case 1:		return 5;
				case 3:		return 42;
				case 4:		return 46;
				case 5:		return 6;
				case 6:		return 7;
				case 9:		return 9;
				case 10:	return 47;
				case 11:	return 8;
			}
		case 2:
			if (pos == 1)		return 45;
		case 3:
			if (pos == 1)		return 10;
			if (pos == 3)		return 11;
			if (pos == 4)		return 12;
			if (pos == 5)		return 13;
			if (pos == 6)		return 14;
			if (pos == 8)		return 15;
			if (pos == 9)		return 16;
			if (pos == 10)		return 17;
			if (pos == 11)		return 18;
		case 8:
			if (pos == 1)		return 50;
		case 10:
			if (pos == 0)		return 19;
			if (pos == 1)		return 20;
			if (pos == 2)		return 49;
			if (pos == 3)		return 21;
			if (pos == 4)		return 22;
			if (pos == 5)		return 23;
			if (pos == 6)		return 24;
			if (pos == 8)		return 25;
			if (pos == 9)		return 26;
			if (pos == 10)		return 27;
			if (pos == 11)		return 28;
			if (pos == 12)		return 49;
		case 13:
			if (pos == 0)		return 29;
			if (pos == 1)		return 43;
			if (pos == 9)		return 30;
			if (pos == 10)		return 44;
		case 15:
			if (pos == 1)		return 31;
			if (pos == 2)		return 32;
			if (pos == 3)		return 33;
			if (pos == 4)		return 34;
			if (pos == 5)		return 35;
			if (pos == 6)		return 36;
			if (pos == 8)		return 37;
			if (pos == 9)		return 38;
			if (pos == 10)		return 39;
			if (pos == 11)		return 40;
			if (pos == 12)		return 41;
		}
		
		return -1;
	}

	private static void populatePkPositions(List<Integer> fkPos, Vector2[] positions) {
		for (Vector2 pos : positions) {
			fkPos.add(getPkPosition(pos));
		}
	}

	private static Integer getPkPosition(Vector2 pos) {
		if (pos == SpawnEnemyPosition.middle) 	return 1;
		if (pos == SpawnEnemyPosition._05sur10) return 2;
		if (pos == SpawnEnemyPosition._1sur10) 	return 3;
		if (pos == SpawnEnemyPosition._2sur10) 	return 4;
		if (pos == SpawnEnemyPosition._3sur10) 	return 5;
		if (pos == SpawnEnemyPosition._4sur10) 	return 6;
		if (pos == SpawnEnemyPosition._6sur10) 	return 8;
		if (pos == SpawnEnemyPosition._7sur10) 	return 9;
		if (pos == SpawnEnemyPosition._8sur10) 	return 10;
		if (pos == SpawnEnemyPosition._9sur10) 	return 11;
		if (pos == SpawnEnemyPosition._95sur10) return 12;
		return 1;
	}

	private static void populatePkEnemies(List<Integer> fkEnemies, Invocable[] enemies) {
		for (Invocable invocable : enemies) {
			fkEnemies.add(getPkEnemy(invocable));
		}
	}

	private static Integer getPkEnemy(Invocable invocable) {
		if (invocable == Ball.REF) return Ball.PK;
		if (invocable == Cylon.ref) return Cylon.PK;
		if (invocable == DeBase.REF) return DeBase.PK;
		if (invocable == Group.ref) return Group.PK;
		if (invocable == Insect.ref) return Insect.PK;
		if (invocable == Kinder.REF) return Kinder.PK;
		if (invocable == Laser.REF) return Laser.PK;
		if (invocable == Plane.REF) return Plane.PK;
		if (invocable == Crusader.REF) return Crusader.PK;
		if (invocable == QuiTir.ref) return QuiTir.PK;
		if (invocable == QuiTirTriangle.REF) return QuiTirTriangle.PK;
		if (invocable == QuiTourne.ref) return QuiTourne.PK;
		if (invocable == Vicious.ref) return Vicious.PK;
		if (invocable == RoundAndRound.ref) return RoundAndRound.PK;
		if (invocable == ZigZag.ref) return ZigZag.PK;
		if (invocable == AddBossStat.ref) return  AddBossStat.PK;
		return DeBase.PK;
	}

	/**
	 * @param enemies
	 * @param positions
	 * @param spawns
	 */
	public static void createSpawn(Array<Invocable> enemies, Array<Vector2> positions, Array<SpawnEnemyPosition> spawns) {
		Invocable[] inv = CSG.convert(enemies);
		Vector2[] pos = CSG.convert(positions);
		enemies.clear();
		positions.clear();
		spawns.add(new SpawnEnemyPosition(inv, pos));
	}

	public static Vector2 getPosition(int positionPk) {
		switch (positionPk) {
		case 1 :	return SpawnEnemyPosition.middle;
		case 2 :	return SpawnEnemyPosition._05sur10;
		case 3 :	return SpawnEnemyPosition._1sur10;
		case 4 :	return SpawnEnemyPosition._2sur10;
		case 5 :	return SpawnEnemyPosition._3sur10;
		case 6 :	return SpawnEnemyPosition._4sur10;
		case 8 :	return SpawnEnemyPosition._6sur10;
		case 9 :	return SpawnEnemyPosition._7sur10;
		case 10 :	return SpawnEnemyPosition._8sur10;
		case 11 :	return SpawnEnemyPosition._9sur10;
		case 12 :	return SpawnEnemyPosition._95sur10;
		}
		return SpawnEnemyPosition.middle;
	}

	public static Invocable getInvocable(int enemyPk) {
		switch (enemyPk) {
		case Ball.PK :				return Ball.REF;
		case Cylon.PK :				return Cylon.ref;
		case DeBase.PK :			return DeBase.REF;
		case Group.PK :				return Group.ref;
		case Insect.PK :			return Insect.ref;
		case Kinder.PK :			return Kinder.REF;
		case Laser.PK :				return Laser.REF;
		case Plane.PK :				return Plane.REF;
		case Crusader.PK :		return Crusader.REF;
		case QuiTir.PK :			return QuiTir.ref;
		case QuiTirTriangle.PK :	return QuiTirTriangle.REF;
		case QuiTourne.PK :			return QuiTourne.ref;
		case RoundAndRound.PK :			return RoundAndRound.ref;
		case Vicious.PK :			return Vicious.ref;
		case ZigZag.PK :			return ZigZag.ref;
		case AddBossStat.PK :		return AddBossStat.ref;
		}
		return DeBase.REF;
	}

}
