package balance;

import elsie.State;

public enum BalanceState implements State, BalanceInputs {

  BALANCED {
    @Override
    public BalanceData openParen(final BalanceData data) {
      return super.openParen(data).setState(IMBALANCED);
    }
    @Override
    public BalanceData closeParen(final BalanceData data) {
      return super.closeParen(data).setState(IMBALANCED);
    }
  },

  IMBALANCED {
    @Override
    public BalanceData openParen(final BalanceData data) {
      return checkBalance(super.openParen(data));
    }

    @Override
    public BalanceData closeParen(final BalanceData data) {
      return checkBalance(super.closeParen(data));
    }

    private BalanceData checkBalance(BalanceData data) {
      if (data.getLevel() == 0)
        return data.setState(BALANCED);
      return data;
    }
  };

  /*
   * Non-mutating state machine "inputs"
   */
  int getLevel(final BalanceData data) { return data.getLevel();}

  /*
   * Mutating state machine "inputs"
   */

  @Override
  public BalanceData openParen(final BalanceData data) {
    return data.setLevel(data.getLevel() + 1);
  }

  @Override
  public BalanceData closeParen(final BalanceData data) {
    return data.setLevel(data.getLevel() - 1);
  }

  BalanceData content(final BalanceData data) {
    // no-op
    return data;
  }


}
