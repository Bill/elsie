import java.util.function.Consumer;

/**
 * A holding bin for utility functions.
 */
public interface F {
  /**
   * This method applies a function to a mutable object, and returns the mutable object.
   *
   * The benefit of this function is twofold:
   *
   * - it turns what would otherwise be a set of statements into a single expression
   *   which can aid readability by reducing syntactic clutter
   *
   * - once you know what doto does, it's easy, when reading code left-to-right, to
   *   quickly understand that the first arg is the result value of the expression,
   *   and that the purpose of the expression is to first mutate the object
   *
   * Inspired by Clojure's doto macro and Ruby's tap() (and Rails' old returning())
   */
  static <T> T doto(final T mutable, final Consumer<T> f) {
    f.accept(mutable);
    return mutable;
  }
}
