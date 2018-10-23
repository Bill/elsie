package balance;

import elsie.State;

public enum BalanceState implements State {

  BALANCED {
    BalanceData openParen(final BalanceData data) {
      return super.openParen(data).setState(IMBALANCED);
    }
    BalanceData closeParen(final BalanceData data) {
      return super.closeParen(data).setState(IMBALANCED);
    }
  },

  IMBALANCED {
    BalanceData openParen(final BalanceData data) {
      return checkBalance(super.openParen(data));
    }

    BalanceData closeParen(final BalanceData data) {
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

  BalanceData openParen(final BalanceData data) {
    return data.setLevel(data.getLevel() + 1);
  }

  BalanceData closeParen(final BalanceData data) {
    return data.setLevel(data.getLevel() - 1);
  }

  BalanceData content(final BalanceData data) {
    // no-op
    return data;
  }


}
