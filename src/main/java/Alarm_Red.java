/**
 * The "on"/"triggered" state of the alarm.
 */
public class Alarm_Red extends AlarmStateAbstract {

  public static final AlarmStateAbstract STATE = new Alarm_Red();

  @Override
  public boolean isTriggered() {
    return true;
  }

  @Override
  public AlarmTransition sample(final int newVal, final AlarmData data) {
    final AlarmTransition transition = super.sample(newVal,data);
    /*
     * THIS IS HOW WE CHANGE STATE
     */
    if (newVal < FALLING_THRESHOLD) {
      return new AlarmTransition(transition.fromState, Alarm_Green.STATE, transition.sideEffect);
    }
    // else state is unmodified
    return transition;
  }
}
