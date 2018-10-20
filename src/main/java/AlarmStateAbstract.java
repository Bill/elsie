public abstract class AlarmStateAbstract implements AlarmState {

  protected static final int RISING_THRESHOLD = 100;
  protected static final int FALLING_THRESHOLD = 90;

  /**
   * Subclasses specialize this by changing the toState in the transition
   */
  public AlarmTransition sample(final int newVal, final AlarmData data) {
    /*
     * THIS IS HOW WE MUTATE (or "MUTATE") DATA (depending on whether
     * AlarmData is mutable or immutable.
     */
    return new AlarmTransition(
        this, this,
        /*
         * There be dragons here. This class works for both mutable and immutable
         * AlarmData. Your classes will probably pick one or ther other (mutability
         * or immutability)--so you won't have to be concerned with these complications.
         *
         * But since this class is agnostic, and since the _average_ calculation depends
         * on the _count_, we must be careful to not modify the count (in the mutable case)
         * before computing the average. Also we must code the averaging logic so that it
         * assumes the count is the _previous_ count.
         */
        () -> data.setMin(newMin(newVal, data))
            .setMax(newMax(newVal, data))
            .setAvg(newAvg(newVal, data))
            .setCount(data.getCount() + 1));
  }

  // could provide default implementations (of input methdos) here for machine-wide behavior


  private int newMin(final int newVal, final AlarmData data) {
    return data.getMin() == Integer.MAX_VALUE ? newVal : Math.min(data.getMin(), newVal);
  }

  private int newMax(final int newVal, final AlarmData data) {
    return data.getMax() == Integer.MAX_VALUE ? newVal : Math.max(data.getMax(), newVal);
  }

  private double newAvg(final int newVal, final AlarmData data) {
    /*
     * data.getCount() returns _old_ count because that seemed to be the simplest way
     * to make this class agnostic as to whether the AlarmData implementation was
     * mutable or not
     */
    final long count = data.getCount() + 1;
    return (data.getAvg() * count + newVal) / (count + 1);
  }

  public String toString() {
    return String.format("state:%12s", getClass().getName());
  }
}