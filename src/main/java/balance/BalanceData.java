package balance;

public interface BalanceData {
  BalanceState getState();
  BalanceData setState(BalanceState state);

  int getLevel();
  BalanceData setLevel(int level);
}
