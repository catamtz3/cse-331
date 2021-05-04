### Written Answers: 18/20

- The abstraction function for `IntQueue1` ought to be:
```
AF(this) = [entries[0], entries[1], ..., entries[entries.size() - 1]]
```

- The representation invariant for `IntQueue1` ought to be:
```
entries != null && !entries.contains(null)
```

- The representation invariant for `IntQueue2` ought to be:
```
entries != null && 0 <= front < entries.length && 0 <= size <= entries.length
```

### Design: 2/3

- Class names should be capitalized, like MapClass.java and Node.java.

- There should not be any static methods in your classes. These methods need to be called on
a particular instance of the graph.

### Documentation & Specification (including JavaDoc): 3/3

- There should be none of this at the class comment level:
```
* @spec.requires graph != null, nodes != null, edges !=null
* @spec.modifies this
* @spec.effects implements nodes into the graph and creates edges between them
```

### Testing (test suite quality & implementation): 1/3

- Sparse JUnit tests, but no script tests were found.

- In addition to testing standard cases for operations using something like the 0,1,many heuristic,
it's also important to test interesting edge cases that might break your implementation. Examples
of this include cyclical graphs, alphabetical ordering or nodes, etc.

### Code quality (code stubs/skeletons only, nothing else): 1/3

- You included some implementation details.  These may include private methods,
private inner classes, private fields, abstraction functions, and representation
invariants.

### Mechanics: 3/3

#### Overall Feedback

- Good work!

#### More Details

- Remove non-used imports like: `import javax.xml.namespace.QName;`
