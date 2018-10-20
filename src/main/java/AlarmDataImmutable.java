import java.util.Objects;

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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    final Class<?> otherClass = o.getClass();
    if (o == null || (getClass() != otherClass && otherClass != AlarmDataMutable.class)) {
      return false;
    }
    AlarmData that = (AlarmData) o;
    return getCount() == that.getCount() &&
        getMin() == that.getMin() &&
        getMax() == that.getMax() &&
        Double.compare(that.getAvg(), getAvg()) == 0;
  }

  @Override
  public int hashCode() {
    return Objects.hash(count, min, max, avg);
  }
}
