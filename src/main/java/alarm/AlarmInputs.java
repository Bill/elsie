package alarm;

/**
 * Each method of this interface defines an "input" to the state machine.
 */
public interface AlarmInputs {

  /*
   * these modify state, so they take an AlarmData and return (a potentially modified) one
   */

  AlarmData sample(final int newVal, final AlarmData data);

  /*
   * these do not modify state, but they depend on state
   */

  boolean isTriggered();

  /*
   * We don't override these since they neither modify nor depend opon state.
   *
   * long getCount();
   * int getMin();
   * int getMax();
   * double getAvg();
   */
}
