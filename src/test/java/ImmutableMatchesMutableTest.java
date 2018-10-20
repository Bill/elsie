import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Random;
import java.util.concurrent.Callable;

public class ImmutableMatchesMutableTest {

  @Test
  public void immutableMatchesMutable() throws Exception {
    assertThat(true).isTrue();

    final int N = (int) 1e2;

    // 80..110
    final int MIN_VAL = 80;
    final int MAX_VAL = 31;

    final Random random = new Random();

    Alarm alarmImmutable = new AlarmMachineNotThreadSafe(Alarm_Green.STATE, new AlarmDataImmutable());
    Alarm alarmMutable = new AlarmMachineNotThreadSafe(Alarm_Green.STATE, new AlarmDataMutable());
    for (int i = 0; i < N; i++) {
      final int newVal = MIN_VAL + random.nextInt(MAX_VAL);
      alarmImmutable.sample(newVal);
      alarmMutable.sample(newVal);
      assertThat(alarmImmutable).isEqualTo(alarmMutable);
    }
  }
}
