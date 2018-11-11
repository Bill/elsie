package alarm;

import java.util.Objects;

import static f.F.doto;

public class AlarmDataMutable implements AlarmData {

  private AlarmState state;
  private long count;
  private int min;
  private int max;
  private double avg;

  AlarmDataMutable() {
    this(AlarmState.GREEN, -1,Integer.MAX_VALUE,Integer.MIN_VALUE,0);
  }

  AlarmDataMutable(final AlarmState state, final long count, final int min, final int max,
                   final double avg) {
    this.state = state;
    this.count = count;
    this.min = min;
    this.max = max;
    this.avg = avg;
  }

  @Override
  public AlarmState getState() {
    return state;
  }

  @Override
  public AlarmData setState(final AlarmState state) {
    return doto(this, (d) -> d.state = state);
  }

  @Override
  public long getCount() {
    return count;
  }

  @Override
  public AlarmData setCount(final long count) {
    return doto(this, (d) -> d.count = count);
  }

  @Override
  public int getMin() {
    return min;
  }

  @Override
  public AlarmData setMin(final int min) {
    return doto(this, (d) -> d.min = min);
  }

  @Override
  public int getMax() {
    return max;
  }

  @Override
  public AlarmData setMax(final int max) {
    return doto(this, (d) -> d.max = max);
  }

  @Override
  public double getAvg() {
    return avg;
  }

  @Override
  public AlarmData setAvg(final double avg) {
    return doto(this, (d) -> d.avg = avg);
  }

  @Override
  public String toString() {
    return String.format("state:%12s count:%10d min:%3d max:%3d avg:%3.2f",
        state, count, min, max, avg);
  }

}
