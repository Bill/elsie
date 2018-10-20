/**
 * An unchecked exception type to tunnel Exceptions (checked) thrown by
 * a Callable implementing a side-effect.
 */
public class SideEffectError extends Error {
  public SideEffectError(Throwable cause) {
    super(cause);
  }
}
