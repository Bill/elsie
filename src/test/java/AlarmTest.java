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

@RunWith(value = Parameterized.class)
public class AlarmTest {

  @Parameters(name = "{index}: {1} data")
  public static Object[][] data() {
    return new Object[][] {
        {(Callable<AlarmData>)AlarmDataMutable::new, "mutable"},
        {(Callable<AlarmData>)AlarmDataImmutable::new, "immmutable"}
    };
  }

  @Parameter
  public Callable<AlarmData> createData;

  @Parameter(value=1)
  public String mutability;

  @Test
  public void foo() throws Exception {
    assertThat(true).isTrue();

    final int N = (int) 1e2;

    // 80..110
    final int MIN_VAL = 80;
    final int MAX_VAL = 31;

    final Random random = new Random();

    Alarm alarm = new AlarmMachineNotThreadSafe(Alarm_Green.STATE, createData.call());
    for (int i = 0; i < N; i++) {
      final int newVal = MIN_VAL + random.nextInt(MAX_VAL);
      final double avg = alarm.sample(newVal);
      System.out.println(String.format("%2d %3d (triggered = %5s) %s (= %3.2f)", i, newVal, alarm.isTriggered(), alarm, avg));
    }
  }
}
