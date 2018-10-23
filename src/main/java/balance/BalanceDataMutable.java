package balance;

import java.util.Objects;

import static balance.BalanceState.BALANCED;
import static f.F.doto;

public class BalanceDataMutable implements BalanceData {

  private BalanceState state;
  private int level;

  BalanceDataMutable() {
    this(BALANCED,0);
  }

  BalanceDataMutable(final BalanceState state, final int level) {
    this.state = state;
    this.level = level;
  }

  public String toString() {
    return String.format("state:%12s level:%10d",
        state, level);
  }

  @Override
  public BalanceState getState() {
    return state;
  }

  @Override
  public BalanceData setState(BalanceState state) {
    return doto(this, (d) -> d.state = state);
  }

  @Override
  public int getLevel() {
    return level;
  }

  @Override
  public BalanceData setLevel(final int level) {
    return doto(this, (d) -> d.level = level);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || !(o instanceof BalanceData)) {
      return false;
    }
    final BalanceData that = (BalanceData) o;
    return getState() == that.getState() &&
        getLevel() == that.getLevel();
  }

  @Override
  public int hashCode() {
    return Objects.hash(state, level);
  }

}
