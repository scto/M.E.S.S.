package behind.levelbuilder;

import elements.generic.enemies.EnemyGroup;
import jeu.CSG;

/**
 * Created by julien on 11/2/15.
 */
public class Orchestrator {

  private EnemyPattern enemyPattern;
  private int index;
  private EnemyGroup enemyGroup;

  public void invoke(float score) {
    if (enemyPattern == null)
      init(score);

  }

  private void init(float score) {
    enemyPattern = startNextWave(score);
    enemyGroup = getEnemyType(score);
    enemyGroup.types[0].enemies[0].obtain().init();
  }

  private EnemyGroup getEnemyType(float score) {
    return EnemyGroup.VERY_EASY;
  }

  private EnemyPattern startNextWave(float score) {
    index = 0;
    for (int i = 0; i < EnemyPattern.ENEMY_PATTERNs.length; i++)
      if (score > EnemyPattern.ENEMY_PATTERNs[i].cost)
        return enemyPattern;
    return EnemyPattern.DEFAULT_ENEMY_PATTERNs[CSG.gm.rand.nextInt(EnemyPattern.DEFAULT_ENEMY_PATTERNs.length)];
  }

}