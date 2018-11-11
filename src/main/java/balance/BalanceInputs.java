package balance;

/**
 * Each method of this interface defines an "input" to the state machine
 */
public interface BalanceInputs {
  BalanceData openParen(final BalanceData data);
  BalanceData closeParen(final BalanceData data);
}
