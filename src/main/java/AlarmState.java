interface AlarmState {

  /*
   * Non-mutating methods
   */
  boolean isTriggered();

  /*
   * Mutating methods
   */
  AlarmTransition sample(int val, AlarmData data);

}
