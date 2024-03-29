package behind.levelbuilder;

/**
 * Created by julien on 10/25/15.
 */
public class EnemyPattern {

  /*
  X------------------X
  --X--------------X--
  ----X----------X----
  */
  private static final Nest[][] A = {{Nest.T0, Nest.T19}, {Nest.T2, Nest.T17}, {Nest.T4, Nest.T15}};

  private static final Nest[][] D0 = {{Nest.T0}};
  private static final Nest[][] D2 = {{Nest.T2}};
  private static final Nest[][] D4 = {{Nest.T4}};
  private static final Nest[][] D6 = {{Nest.T6}};
  private static final Nest[][] D8 = {{Nest.T8}};
  private static final Nest[][] D10 = {{Nest.T10}};
  private static final Nest[][] D12 = {{Nest.T12}};
  private static final Nest[][] D14 = {{Nest.T14}};
  private static final Nest[][] D16 = {{Nest.T16}};
  private static final Nest[][] D18 = {{Nest.T18}};
  private static final Nest[][] D20 = {{Nest.T20}};

  public static final EnemyPattern[] ENEMY_PATTERNs = {new EnemyPattern(A)};
  public static final EnemyPattern[] DEFAULT_ENEMY_PATTERNs = {new EnemyPattern(D0), new EnemyPattern(D2), new EnemyPattern(D4), new EnemyPattern(D6), new EnemyPattern(D8), new EnemyPattern(D10), new EnemyPattern(D12), new EnemyPattern(D14), new EnemyPattern(D16), new EnemyPattern(D18), new EnemyPattern(D20)};

  public final int cost;
  public final Nest[][] nest;

  public EnemyPattern(Nest[][] nest) {
    this.cost = getCost(nest);
    this.nest = nest;
  }

  private static int getCost(Nest[][] nests) {
    int cost = 0;
    for (int i = 0; i < nests.length; i++) {
      for (int i1 = 0; i1 < nests.length; i1++) {
        cost += i1;
      }
    }
    return cost;
  }

}
