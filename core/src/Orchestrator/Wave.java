package Orchestrator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by julien on 10/25/15.
 */
public class Wave {

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

  public static final Wave[] WAVES = getWaves();
  public static final Wave[] DEFAULT_WAVES = {new Wave(D0), new Wave(D2), new Wave(D4), new Wave(D6), new Wave(D8), new Wave(D10), new Wave(D12), new Wave(D14), new Wave(D16), new Wave(D18), new Wave(D20)};

  public final int cost;
  public final Nest[][] nest;

  public Wave(Nest[][] nest) {
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

  public static Wave[] getWaves() {
    List<Wave> list = new ArrayList<Wave>();
    list.add(new Wave(A));
    return (Wave[]) list.toArray();
  }

}
