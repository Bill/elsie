/**
 * Alarm data. All data which is not the discrete "state" of the state machine.
 *
 * It's an interface so we can experiement with two implementations:
 *
 * - a mutable one
 *
 * - an immutable one
 *
 * Code that uses this interface is written to work the same whether the instance
 * is mutable or immutable. That entails respecting the "mutation" protocol.
 *
 * The mutation protocol is: each mutator (method) returns an object reference.
 * An immutable implementation returns a new reference. A mutable implementation
 * typically returns the target (this).
 *
 * To make your calling code agnostic, assume this object is immutable and that every
 * mutator returns a reference to a new object.
 */
public interface AlarmData {

  AlarmState getState();
  AlarmData setState(AlarmState state);

  long getCount();
  AlarmData setCount(long count);

  int getMin();
  AlarmData setMin(int min);

  int getMax();
  AlarmData setMax(int max);

  double getAvg();
  AlarmData setAvg(double avg);
}
