package alarm;

import java.util.concurrent.atomic.AtomicReference;

/**
 * A (mutable), thread-safe, OO façade over the state machine
 */
public class AlarmContextThreadSafe extends AbstractAlarmContext {
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
    return getData().getState().isTriggered();
  }

  @Override
  public AlarmData getData() {
    return data.get();
  }

}