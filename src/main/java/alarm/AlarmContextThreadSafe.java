package alarm;

import java.util.concurrent.atomic.AtomicReference;

/**
 * A (mutable), thread-safe, OO façade over the state machine
 */
public class AlarmContextThreadSafe implements Alarm {
  private final AtomicReference<AlarmDataImmutable> data;

  public AlarmContextThreadSafe(final AlarmDataImmutable data) {
    this.data = new AtomicReference<>(data);
  }

  /**
   * This is what a mutating method looks like. Since data and state can change,
   * we go through the state machine protocol.
   */
  @Override
  public double sample(int val) {
    return data.updateAndGet((data)->(AlarmDataImmutable)data.getState().sample(val, data)).getAvg();
  }

  /**
   * This non-mutating method doesn't have to go through the state machine protocol,
   * though it could.
   */
  @Override
  public boolean isTriggered() {
    return data.get().getState().isTriggered();
  }

  @Override
  public long getCount() {
    return data.get().count;
  }

  @Override
  public int getMin() {
    return data.get().min;
  }

  @Override
  public int getMax() {
    return data.get().max;
  }

  @Override
  public double getAvg() {
    return data.get().avg;
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
  public int hashCode() { return data.get().hashCode();}

  @Override
  public String toString() {
    return data.get().toString();
  }
}