import java.util.Objects;

/**
 * A (mutable) OO façade over the state machine
 */
public class AlarmMachineNotThreadSafe implements Alarm {
  private AlarmData data;

  public AlarmMachineNotThreadSafe(final AlarmData data) {
    this.data = data;
  }

  /**
   * This is what a mutating method looks like. Since data and state can change,
   * we go through the state machine protocol.
   */
  @Override
  public double sample(int val) {
    data = data.getState().sample(val, data);
    // TODO: it's ugly that this method has to know about which datum to return
    return data.getAvg();
  }

  /**
   * This non-mutating method doesn't have to go through the state machine protocol,
   * though it could.
   */
  @Override
  public boolean isTriggered() {
    return data.getState().isTriggered();
  }

  public String toString() {
    return data.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || !(o instanceof AlarmMachineNotThreadSafe)) {
      return false;
    }
    final AlarmMachineNotThreadSafe that = (AlarmMachineNotThreadSafe) o;

    return data.equals(that.data);
  }

  @Override
  public int hashCode() {
    return data.hashCode();
  }
}