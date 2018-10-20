public class AlarmMachineNotThreadSafe implements Alarm {
  private AlarmState state;
  private AlarmData data;

  public AlarmMachineNotThreadSafe(final AlarmState state, final AlarmData data) {
    this.state = state;
    this.data = data;
  }

  /**
   * This is what a mutating method looks like. Since data and state can change,
   * we go through the state machine protocol.
   */
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

  /**
   * This non-mutating method doesn't have to go through the state machine protocol,
   * though it could.
   */
  @Override
  public boolean isTriggered() {
    return state.isTriggered();
  }

  public String toString() {
    return String.format("%s %s", state.toString(), data.toString());
  }
}