### Written Answers: 17/26

### Code Quality: 2/3

### Mechanics: 3/3

### General Feedback

### Specific Feedback
- did not define tr in multiplication pseudocode
- The loop in polynomial division needs to terminate when p becomes 0.  If you
don't have this condition in the loop, then an infinite loop can occur when the
degree of the divisor is 0, since the degree of p = 0 is also 0.
- the `if` in division pseudocode looks like of wrong indentation
- in 1.2, the method specification lacks `@modifies` this, so it would violate the
specification to modify the abstract state of this.
- for 1.3, having the specs specify `RatNum` to be immutable is not enough to actually enforce immutability. `final` immutable fields cannot be modified after they are instantiated; the compiler would complain about any attempt to do so.  Therefore, we can reason
that `RatNum` and `RatTerm` cannot contain any bugs with regard to the
representation invariant as long as we ensure the coherency of the data at
initialization.  Therefore, `RatNum` and `RatTerm` are special cases that do not
- in 2.3, need to change `checkRep` other than constructor
- for 3.1,
    - Immutability is a property of the specification, and checkRep does not assume
    the specification was correctly implemented.  So, in general, regardless of
    whether or not they are immutable, ADTs need calls to `checkRep` at the
    beginning and end of all public methods.
    - `checkRep` does not assume that methods were implemented properly, regardless of
    how trivial or innocuous they seem.  So, in general, even observer methods need
    calls to `checkRep` at their beginning and end.
- for 3.2, missing explanation of the advantage and disadvantage of switching representation
- Missing call to `checkRep` at the end of the constructor in RatPoly.
- Missing calls to `checkRep` at the beginning and end of every public method in `RatPoly` and/or `RatPolyStack`.
