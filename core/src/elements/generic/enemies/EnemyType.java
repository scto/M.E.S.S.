package elements.generic.enemies;

import com.badlogic.gdx.utils.Pool;
import elements.generic.enemies.Enemy;
import elements.generic.enemies.individual.lvl1.Ball;
import elements.generic.enemies.individual.lvl1.Basic;
import elements.generic.enemies.individual.lvl3.Ball3;
import elements.generic.enemies.individual.lvl3.Basic3;
import elements.generic.enemies.individual.lvl4.Basic4;

/**
 * Created by julien on 11/2/15.
 */
public enum EnemyType {

  BASIC(Basic.POOL, Basic3.POOL, Basic4.POOL);

  public Pool<? extends Enemy>[] enemies;
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
