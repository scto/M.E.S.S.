package elements.generic.enemies;

/**
 * Created by julien on 11/4/15.
 */
public enum EnemyGroup {

  VERY_EASY(EnemyType.BASIC);

  public EnemyType[] types;

  EnemyGroup(EnemyType... types) {
    this.types = types;
  }
}
