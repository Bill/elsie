package alarm;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Random;

public class ObservabilityTest {

  @Test
  public void allImplementationsHaveIdenticalObservableBehavior() {
    assertThat(true).isTrue();

    final int N = (int) 1e2;

    // 80..110
    final int MIN_VAL = 80;
    final int MAX_VAL = 31;

    final Random random = new Random();

    Alarm alarmMutableNonThreadSafe = new AlarmContextNotThreadSafe(new AlarmDataMutable());
    Alarm alarmImmutableNonThreadSafe = new AlarmContextNotThreadSafe(new AlarmDataImmutable());
    Alarm alarmImmutableThreadSafe = new AlarmContextThreadSafe(new AlarmDataImmutable());
    for (int i = 0; i < N; i++) {
      final int newVal = MIN_VAL + random.nextInt(MAX_VAL);
      alarmMutableNonThreadSafe.sample(newVal);
      alarmImmutableNonThreadSafe.sample(newVal);
      alarmImmutableThreadSafe.sample(newVal);
      assertThat(alarmImmutableNonThreadSafe).isEqualTo(alarmMutableNonThreadSafe);
      assertThat(alarmImmutableNonThreadSafe).isEqualTo(alarmImmutableThreadSafe);
    }
  }
}
