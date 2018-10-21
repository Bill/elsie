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
  public AlarmData sample(final int newVal, final AlarmData data) {
    final AlarmData data2 = super.sample(newVal,data);
    /*
     * THIS IS HOW WE CHANGE STATE
     */
    if (newVal > RISING_THRESHOLD) {
      return data2.setState(Alarm_Red.STATE);
    }
    // else state is unmodified
    return data2;
  }
}
