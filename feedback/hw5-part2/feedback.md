### Written Answers: 8/10
What graph operations are O(1) time? A collection of edges has graph operations
which certainly aren't. Furthermore, an adjacencly list is effectively the same
spatial complexity as a collection of edges.

### Design: 2/3
`getChildren` and `getEdge` don't provide adequate functionality: They shouldn't
return strings that test script expects - the graph interface should be
different than the test script interface.

### Documentation & Specification (including JavaDoc): 3/3
Your `addNode` method should document that null isn't added.

### Code quality (code and internal comments including RI/AF): 1/3

### Testing (test suite quality & implementation): 1/3

### Mechanics: 3/3

#### Overall Feedback

Good work! Your design is pretty solid, but there are a few flaws. Your testing
suite has good general coverage, but you're missing special cases.
#### More Details

Some notes about design: Your use of generics here has a few flaws. It's
generally good practice not to name the your variables with the same type they
have, but more importantly you have redundant types: you shouldn't have two
different types for your nodes.

Some notes on code quality: You have representation exposure in the public
method `entrySet`. You also have a pretty expensive `checkRep` method that
doesn't short circuit when a debug flag is specified.

Some notes about testing: In addition to the notes about special cases, your
junit test suite will currently always pass, even if your graphs don't work.
This is because you never make any assertion statements, which are what junit
looks for to see if tests pass or fail.

