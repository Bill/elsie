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
  public AlarmData sample(final int newVal, final AlarmData data) {
    final AlarmData data2 = super.sample(newVal,data);
    /*
     * THIS IS HOW WE CHANGE STATE
     */
    if (newVal < FALLING_THRESHOLD) {
      return data2.setState(Alarm_Green.STATE);
    }
    // else state is unmodified
    return data2;
  }
}
