/**
 * Immutable
 */
public class AlarmDataImmutable implements AlarmData {

  final long count;
  final int min;
  final int max;
  final double avg;

  AlarmDataImmutable() {
    this(-1,Integer.MAX_VALUE,Integer.MIN_VALUE,0);
  }

  AlarmDataImmutable(final long count, final int min, final int max, final double avg) {
    this.count = count;
    this.min = min;
    this.max = max;
    this.avg = avg;
  }

  public String toString() {
    return String.format("count:%10d min:%3d max:%3d avg:%3.2f",
        count, min, max, avg);
  }

  @Override
  public long getCount() {
    return count;
  }

  @Override
  public AlarmData setCount(long count) {
    return new AlarmDataImmutable(count, min, max, avg);
  }

  @Override
  public int getMin() {
    return min;
  }

  @Override
  public AlarmData setMin(int min) {
    return new AlarmDataImmutable(count, min, max, avg);
  }

  @Override
  public int getMax() {
    return max;
  }

  @Override
  public AlarmData setMax(int max) {
    return new AlarmDataImmutable(count, min, max, avg);
  }

  @Override
  public double getAvg() {
    return avg;
  }

  @Override
  public AlarmData setAvg(double avg) {
    return new AlarmDataImmutable(count, min, max, avg);
  }
}
