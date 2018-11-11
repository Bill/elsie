package alarm;

public abstract class AbstractAlarmContext implements Alarm {

  @Override
  public long getCount() {
    return getData().getCount();
  }

  @Override
  public int getMin() {
    return getData().getMin();
  }

  @Override
  public int getMax() {
    return getData().getMax();
  }

  @Override
  public double getAvg() {
    return getData().getAvg();
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
  public int hashCode() { return getData().hashCode();}

  @Override
  public String toString() {
    return getData().toString();
  }

  abstract AlarmData getData();
}
