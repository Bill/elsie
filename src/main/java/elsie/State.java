package elsie;

public interface State {

  /**
   * For use in base-class input methods to implement "default deny" for inputs which
   * must be overridden in derived classes (in order to make an input legal in a
   * particular state)
   */
  default <T> T illegalInput(final String input) {
    throw new IllegalStateException(String.format("Illegal input (%s) in state (%s)",
        input,this));
  }

}
