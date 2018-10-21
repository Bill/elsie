import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Random;

public class ImmutableMatchesMutableTest {

  @Test
  public void immutableMatchesMutable() throws Exception {
    assertThat(true).isTrue();

    final int N = (int) 1e2;

    // 80..110
    final int MIN_VAL = 80;
    final int MAX_VAL = 31;

    final Random random = new Random();

    Alarm alarmImmutable = new AlarmMachineNotThreadSafe(new AlarmDataImmutable());
    Alarm alarmMutable = new AlarmMachineNotThreadSafe(new AlarmDataMutable());
    for (int i = 0; i < N; i++) {
      final int newVal = MIN_VAL + random.nextInt(MAX_VAL);
      alarmImmutable.sample(newVal);
      alarmMutable.sample(newVal);
      assertThat(alarmImmutable).isEqualTo(alarmMutable);
    }
  }
}
