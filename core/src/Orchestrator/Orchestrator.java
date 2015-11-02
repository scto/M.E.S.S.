package Orchestrator;

import elements.generic.enemies.EnemyType;
import jeu.CSG;

/**
 * Created by julien on 11/2/15.
 */
public class Orchestrator {

  private Wave wave;
  private int index;
  private EnemyType enemyType;

  public void invoke(int score) {
    if (wave == null)
      init(score);
  }

  private void init(int score) {
    wave = startNextWave(score);
    enemyType = getEnemyType(score);
  }

  private EnemyType getEnemyType(int score) {
    return null;
  }

  private Wave startNextWave(int score) {
    index = 0;
    for (int i = 0; i < Wave.WAVES.length; i++)
      if (score > Wave.WAVES[i].cost)
        return wave;
    return Wave.DEFAULT_WAVES[CSG.gm.rand.nextInt(Wave.DEFAULT_WAVES.length)];
  }

}
