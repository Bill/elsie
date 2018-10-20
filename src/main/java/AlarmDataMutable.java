public class AlarmDataMutable implements AlarmData {

  private long count;
  private int min;
  private int max;
  private double avg;

  AlarmDataMutable() {
    this(-1,Integer.MAX_VALUE,Integer.MIN_VALUE,0);
  }

  AlarmDataMutable(final long count, final int min, final int max, final double avg) {
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
}
