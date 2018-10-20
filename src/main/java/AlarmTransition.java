import java.util.concurrent.Callable;

public class AlarmTransition {
  final AlarmStateAbstract fromState;
  final AlarmStateAbstract toState;
  final Callable<AlarmData> sideEffect;

  AlarmTransition(final AlarmStateAbstract fromState,
      final AlarmStateAbstract toState,
      final Callable<AlarmData> sideEffect) {
    this.fromState = fromState;
    this.toState = toState;
    this.sideEffect = sideEffect;
  }
}
