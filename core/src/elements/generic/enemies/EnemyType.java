package elements.generic.enemies;

import com.badlogic.gdx.utils.Pool;
import elements.generic.enemies.individual.lvl1.Ball;
import elements.generic.enemies.individual.lvl1.Basic;
import elements.generic.enemies.individual.lvl3.Ball3;

/**
 * Created by julien on 11/2/15.
 */
public enum EnemyType {

  BASIC(Basic.POOL, Ball.POOL, Ball3.POOL);

  Pool<? extends Enemy>[] enemies;
  int cost;

  EnemyType(Pool<? extends Enemy>... enemies) {
    this.enemies = enemies;
    for (Pool<? extends Enemy> enemy : enemies) {
      Enemy e = enemy.obtain();
      cost += e.getXp();
      e.free();
    }
  }
}
