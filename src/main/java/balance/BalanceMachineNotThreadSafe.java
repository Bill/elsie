package balance;

public class BalanceMachineNotThreadSafe implements Balance {
  BalanceData data;

  public BalanceMachineNotThreadSafe(final BalanceData data) {
    this.data = data;
  }

  @Override
  public BalanceState openParen() {
    return data.getState().openParen(data).getState();
  }

  @Override
  public BalanceState closeParen() {
    return data.getState().closeParen(data).getState();
  }

  @Override
  public BalanceState content() {
    return data.getState().content(data).getState();
  }

  @Override
  public int getLevel() {
    return data.getState().getLevel(data);
  }
}
