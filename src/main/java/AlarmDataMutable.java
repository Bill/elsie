import java.util.Objects;

public class AlarmDataMutable implements AlarmData {

  private AlarmState state;
  private long count;
  private int min;
  private int max;
  private double avg;

  AlarmDataMutable() {
    this(AlarmState.GREEN, -1,Integer.MAX_VALUE,Integer.MIN_VALUE,0);
  }

  AlarmDataMutable(AlarmState state, final long count, final int min, final int max,
                   final double avg) {
    this.state = state;
    this.count = count;
    this.min = min;
    this.max = max;
    this.avg = avg;
  }

  public String toString() {
    return String.format("state:%12s count:%10d min:%3d max:%3d avg:%3.2f",
        state, count, min, max, avg);
  }

  @Override
  public AlarmState getState() {
    return state;
  }

  @Override
  public AlarmData setState(AlarmState state) {
    return F.doto(this, (d) -> d.state = state);
  }

  @Override
  public long getCount() {
    return count;
  }

  @Override
  public AlarmData setCount(long count) {
    return F.doto(this, (d) -> d.count = count);
  }

  @Override
  public int getMin() {
    return min;
  }

  @Override
  public AlarmData setMin(int min) {
    return F.doto(this, (d) -> d.min = min);
  }

  @Override
  public int getMax() {
    return max;
  }

  @Override
  public AlarmData setMax(int max) {
    return F.doto(this, (d) -> d.max = max);
  }

  @Override
  public double getAvg() {
    return avg;
  }

  @Override
  public AlarmData setAvg(double avg) {
    return F.doto(this, (d) -> d.avg = avg);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || !(o instanceof AlarmData)) {
      return false;
    }
    final AlarmData that = (AlarmData) o;
    return getState() == that.getState() &&
        getCount() == that.getCount() &&
        getMin() == that.getMin() &&
        getMax() == that.getMax() &&
        Double.compare(that.getAvg(), getAvg()) == 0;
  }

  @Override
  public int hashCode() {
    return Objects.hash(state, count, min, max, avg);
  }

}
