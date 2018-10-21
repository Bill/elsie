interface AlarmState {

  /*
   * Non-mutating methods
   */
  boolean isTriggered();

  /*
   * Mutating methods
   */
  AlarmData sample(int val, AlarmData data);

}
