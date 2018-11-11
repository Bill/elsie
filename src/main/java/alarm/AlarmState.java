package alarm;

import elsie.State;

/**
 * Alarm state machine definition. Enum instances represent states. States control
 * behavior.
 *
 * The set of all inputs recognized by the state machine is encoded in the
 * AlarmInputs interface. Global behavior is defined on the base class, AlarmState.
 * States (enum instances) can override the defaults.
 */
enum AlarmState implements State, AlarmInputs {

  GREEN {
    @Override
    public boolean isTriggered() {
      return false;
    }

    @Override
    public AlarmData sample(final int newVal, final AlarmData data) {
      final AlarmData data2 = super.sample(newVal,data);
      /*
       * THIS IS HOW WE CHANGE STATE
       */
      if (newVal > RISING_THRESHOLD) {
        return data2.setState(RED);
      }
      // else state is unmodified
      return data2;
    }
  },

  RED {
    @Override
    public boolean isTriggered() {
      return true;
    }

    @Override
    public AlarmData sample(final int newVal, final AlarmData data) {
      final AlarmData data2 = super.sample(newVal,data);
      /*
       * THIS IS HOW WE CHANGE STATE
       */
      if (newVal < FALLING_THRESHOLD) {
        return data2.setState(GREEN);
      }
      // else state is unmodified
      return data2;
    }
  };

  public static final int RISING_THRESHOLD = 100;
  public static final int FALLING_THRESHOLD = 90;

  // this method does not represent an input to the state machine
  public String toString() {
    return String.format("state:%5s", name());
  }

  /*
   * Non-mutating state machine "inputs"
   */

  @Override
  public boolean isTriggered() {
    return illegalInput("isTriggered()");
  }

  /*
   * Mutating state machine "inputs"
   *
   * These methods differ from their counterparts in Alarm in that these take an additional
   * final argument of type AlarmData, and return an AlarmData.
   *
   * Alarm objects providing a traditional "OO-looking" interface to the state machine
   * either return void or return a datum extracted from the AlarmData.
   */


  /**
   * Subclasses specialize this by changing the data.state sometimes
   */
  @Override
  public AlarmData sample(final int newVal, final AlarmData data) {
    /*
     * THIS IS HOW WE MUTATE (or "MUTATE") DATA (depending on whether
     * AlarmData is mutable or immutable.
     *
     * There be dragons here. This class works for both mutable and immutable
     * AlarmData. Your classes will probably pick one or ther other (mutability
     * or immutability)--so you won't have to be concerned with these complications.
     *
     * But since this class is agnostic, and since the _average_ calculation depends
     * on the _count_, we must be careful to not modify the count (in the mutable case)
     * before computing the average. Also we must code the averaging logic so that it
     * assumes the count is the _previous_ count.
     */
    return data.setMin(newMin(newVal, data))
        .setMax(newMax(newVal, data))
        .setAvg(newAvg(newVal, data))
        .setCount(data.getCount() + 1);
  }

  /*
   * Default implementations of other "inputs" can go here for machine-wide behavior
   */


  /*
   * Guts
   */

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

}
