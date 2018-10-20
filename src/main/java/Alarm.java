public interface Alarm {

  /**
   * Add a sample.
   *
   * Mutates the receiver.
   *
   * @param val is the sample
   * @return the new average
   */
  double sample(int val);

  /**
   *  It is untriggered at creation-time. It becomes triggered when a sample exceeds
   *  the upper threshold. After that, the alarm stays triggered until a sample
   *  below the lower threshold is seen.
   *
   *  This (varying output not only on input, but also on previous output) is often
   *  referred to as "hysteresis" in control systems.
   *
   *  Does not mutate the receiver.
   *
   * @return
   */
  boolean isTriggered();
}
