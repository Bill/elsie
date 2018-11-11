package alarm;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Random;
import java.util.concurrent.Callable;

@RunWith(value = Parameterized.class)
public class AlarmTest {

  @Parameters(name = "{index}: {1} data")
  public static Object[][] data() {
    return new Object[][] {
        {(Callable<Alarm>)()->new AlarmContextNotThreadSafe(new AlarmDataMutable()), "mutable", "not thread-safe"},
        {(Callable<Alarm>)()->new AlarmContextNotThreadSafe(new AlarmDataImmutable()), "immmutable", "not thread-safe"},
        {(Callable<Alarm>)()->new AlarmContextThreadSafe(new AlarmDataImmutable()), "immmutable", "thread-safe"}
    };
  }

  @Parameter
  public Callable<Alarm> createAlarm;

  @Parameter(value=1)
  public String mutability;

  @Parameter(value=2)
  public String threadSafety;

  @Test
  public void hysteresis() throws Exception {
    assertThat(true).isTrue();

    final int N = (int) 1e2;

    // 80..110
    final int MIN_VAL = 80;
    final int MAX_VAL = 31;

    final Random random = new Random();

    final Alarm alarm = createAlarm.call();

    int previousSample = 0;
    boolean previousIsTriggered = false;

    for (int i = 0; i < N; i++) {
      final int sample = MIN_VAL + random.nextInt(MAX_VAL);

      previousIsTriggered = alarm.isTriggered();

      // MUTATE!
      final double avg = alarm.sample(sample);

      if (i > 0)
        if (previousIsTriggered)
          assertThat(!alarm.isTriggered()).isEqualTo(sample < AlarmState.FALLING_THRESHOLD);
        else
          assertThat(alarm.isTriggered()).isEqualTo(sample > AlarmState.RISING_THRESHOLD);

      previousSample = sample;

//      System.out.println(String.format("%2d %3d (triggered = %5s) %s (= %3.2f)", i, newVal, alarm.isTriggered(), alarm, avg));
    }
  }
}
