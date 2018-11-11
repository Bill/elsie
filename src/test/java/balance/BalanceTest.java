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


  @Parameters(name = "{index}: {0} {1} {2}")
  public static Object[][] data() {
    return new Object[][] {
        {"", BALANCED,0},
        {"(", IMBALANCED,1},
        {")", IMBALANCED,-1},
        {"(hi", IMBALANCED,1},
        {"()", BALANCED,0},
        {"(())", BALANCED,0},
    };
  }

  @Parameter
  public String input;

  @Parameter(value=1)
  public BalanceState expectState;

  @Parameter(value=2)
  public int expectLevel;

  @Test
  public void testBalance() {
    final BalanceContextNotThreadSafe
        balancer =
        new BalanceContextNotThreadSafe(new BalanceDataMutable());
    for( int i = 0; i < input.length(); i++) {
      switch(input.charAt(i)) {
        case '(':
          balancer.openParen();
          break;
        case ')':
          balancer.closeParen();
          break;
        default:
          balancer.content();
      }
    }
    assertThat(balancer.data.getState()).isEqualTo(expectState);
    assertThat(balancer.data.getLevel()).isEqualTo(expectLevel);
  }

}
