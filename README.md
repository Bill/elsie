_Elsie_ is an experiment, testing the hypothesis that (mostly) coding conventions can be a good way to implement state machines.

A set of opinions are tested here:

* a Java interface defines a machine
* interface methods define the inputs to the machine
* arguments to methods capture the (variant) input data
* a singleton instance of a Java class implementing the interface, defines a state

This is in contrast to the prevalent approach to state machines in Java, which involve a DSL. In those frameworks inputs (or events) are explicitly modeled as first-class entities.


* whereas the DSL approach demands that you define a new class per input/event type, in _Elsie_ they're just methods on an interface
* whereas a typical DSL-generated state machine must be wrapped in "object clothes", in _Elsie_ the state machine, ultimately, looks like a regular old Java class 

## See Also

The GoF _State_ pattern.

## TODO

* discuss mutable vs. immutable data holder class
* discuss side effects: immediate vs. monadic
* discuss concurrency control and applicability to a range of situations, through interchangeable policies
