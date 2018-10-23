package balance;

public interface Balance {

  BalanceState openParen();
  BalanceState closeParen();
  BalanceState content();

  int getLevel();
}
