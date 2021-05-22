### Command line application: 3/3

- Your CLI has some issues. Doesn't allow for quitting so some sections of your 
code are unreachable. Also doesn't always print correctly for the most recently inputted characters.

#### Non-User-Friendly Error
For user interfaces in general, it is important to make sure all error cases are
handled in a graceful, user-friendly manner.  You cannot expect a user to read
an exception name or a stack trace and understand what's happening. (Your program throws 
an illegal argument exception to the user if they input an invalid file name).

- Your main method should also not be throwing an IOException. You should be catching that 
and handling the failure gracefully so the user won't ever see the IOException.

### Design: 3/3

### Documentation & Specification (including JavaDoc): 2/3

## Should Explicitly say that MarvelPaths isn't an ADT
For next time, if you implement a class that isn't an ADT, you should add an internal comment that explicitly
says so where the ADT/RI would normally go.

- The intent of `parseData` was that you'd change the return type to return whatever you wanted. However,
since you decided to pass 2 data structures and load them, you should definitely comment those @params 
in the javadoc.

- Pasting the entire BFS pseudocode into javadoc is unnecessary and bad for readability. Instead, you 
should just state that the method uses BFS. You should also document in the javadoc that the method 
returns null if no path was found since that is behavior that is important for the client to know. 

### Code quality (code and internal comments including RI/AF when appropriate): 2/3

## Missing Internal Documentation for BFS
Your BFS method is complex, and should hence have some internal comments/documentation to improve its readability and understandability.

- Instead of wrapping your entire BFS method in an if statement, consider just checking those conditions first 
and failing/returning null immediately, or assert that those conditions are true. That would remove a level 
of indentation and make your code much more readable.

- The indentation of your code should be consistent throughout and adhere to any standard java style guide.

### Testing (test suite quality & implementation): 1/3

- Since you have methods that throw exceptions, you should be testing those with JUnit.

- Many of your script tests are not correct -- see the spec for the expected output format.

#### Special Cases
Your test suite is lacking in coverage.  Here's a few ideas for interesting test
cases:
- Empty data file.
- Loading two graphs.
- Operations that mix graph loading and building.
- Cyclic graphs with path finding.
- Lexicographically least paths.

### Mechanics: 3/3

### Command line application extra credit:  -/3

#### Overall Feedback

Please see my feedback above. Good luck on hw7!

#### More Details

None.
