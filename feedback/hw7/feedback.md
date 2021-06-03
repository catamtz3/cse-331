### Design: 2/3
- Allowing two different node types doesn't make all that much sense for a `Graph`; you typically only deal with `Graph`s that have a single node type and a single edge type.
- The generic parameter names you've given are pretty poor --- they don't have a clear meaning.
- The graph building routine should be factored out into its own method.
Otherwise, you cannot test the graph building on arbitrary data files or some
mock parser.

### Documentation & Specification (including JavaDoc): -/3
- No new code (generics, dijkstras, `CampusMap` constructor) documented.

### Code quality (code and internal comments including RI/AF when appropriate): 2/3
- You are missing a `checkRep` in `CampusMap`.
- You should be providing internal comments for complicated code like Dijkstra's or the `CampusMap` constructor.

### Testing (test suite quality & implementation): 2/3
- Your test suite is lacking in coverage. Here's a few ideas for interesting test
cases:

- Cyclic graphs with path finding.
- Paths where the difference between BFS and Dijkstra's algorithm is apparent.
- Graphs with multiple same-direction edges of varying costs between the same two nodes.

### Mechanics: 3/3

#### Overall Feedback

Good work overall! Remember to document all new code you introduce.

#### More Details

None.
