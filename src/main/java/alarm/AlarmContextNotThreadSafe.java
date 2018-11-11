package alarm;

/**
 * A (mutable) OO façade over the state machine
 */
public class AlarmContextNotThreadSafe implements Alarm {
  private AlarmData data;

  public AlarmContextNotThreadSafe(final AlarmData data) {
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

  @Override
  public long getCount() {
    return data.getCount();
  }

  @Override
  public int getMin() {
    return data.getMin();
  }

  @Override
  public int getMax() {
    return data.getMax();
  }

  @Override
  public double getAvg() {
    return data.getAvg();
  }

  @Override
  public boolean equals(final Object other_) {
    if (this == other_) {
      return true;
    }
    if (other_ == null || !(other_ instanceof Alarm)) {
      return false;
    }
    final Alarm other = (Alarm)other_;
    return isTriggered() == other.isTriggered() &&
        other.getCount() == other.getCount() &&
        other.getMin() == other.getMin() &&
        other.getMax() == other.getMax() &&
        Double.compare(other.getAvg(), other.getAvg()) == 0;
  }

  @Override
  public int hashCode() {
    return data.hashCode();
  }

  @Override
  public String toString() { return data.toString(); }
}