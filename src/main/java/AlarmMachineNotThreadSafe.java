public class AlarmMachineNotThreadSafe implements Alarm {
  private AlarmState state;
  private AlarmData data;

  public AlarmMachineNotThreadSafe(final AlarmState state, final AlarmData data) {
    this.state = state;
    this.data = data;
  }

  @Override
  public double sample(int val) {
    final AlarmTransition transition = state.sample(val, data);
    try {
      data = transition.sideEffect.call();
      state = transition.toState;
      // TODO: it's ugly that this method has to know about which datum to return
      return data.getAvg();
    } catch (final Exception e) {
      throw new SideEffectError(e);
    }
  }

  @Override
  public boolean isTriggered() {
    return state.isTriggered();
  }

  public String toString() {
    return String.format("%s %s", state.toString(), data.toString());
  }
}