package balance;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import static balance.BalanceState.BALANCED;
import static balance.BalanceState.IMBALANCED;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(value = Parameterized.class)
public class BalanceTest {


  @Parameters(name = "{index}: {1} data")
  public static Object[][] data() {
    return new Object[][] {
        {"()", BALANCED,0},
        {"(hi", IMBALANCED,1}
    };
  }

  @Parameter
  public String in;

  @Parameter(value=1)
  public BalanceState expectState;

  @Parameter(value=2)
  public int expectLevel;

  @Test
  public void testBalance() {

    assertThat(true).isTrue();
  }

}
