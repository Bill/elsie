/**
 * The "off"/"un-triggered" state of the alarm. This is the initial state.
 */
public class Alarm_Green extends AlarmStateAbstract {

  public static final AlarmStateAbstract STATE = new Alarm_Green();

  @Override
  public boolean isTriggered() {
    return false;
  }

  @Override
  public AlarmTransition sample(final int newVal, final AlarmData data) {
    final AlarmTransition transition = super.sample(newVal,data);
    /*
     * THIS IS HOW WE CHANGE STATE
     */
    if (newVal > RISING_THRESHOLD) {
      return new AlarmTransition(transition.fromState, Alarm_Red.STATE, transition.sideEffect);
    }
    // else state is unmodified
    return transition;
  }
}
