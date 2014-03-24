package jeu.db;

import elements.generic.enemies.Wave;


public interface DataManager {

	void setupDatabase();

	void openOrCreateDatabase();

	void closeDatabae();

	void execSQL(String sql);

	int getInt(String req, int def);
	float getFloat(String req, float def);
	Wave getWave(int pk, Wave def);

//	int getVersionId();
//
//	int getAnimationFromEnemy(int pk);

}
