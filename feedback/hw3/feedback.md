### Written Answers: 6/6
- Great written answers!
### Code Quality: 3/3

### Mechanics: 3/3

### General Feedback
Good coding quality overall. Most of these were very minor things. The one that I would caution about is always to make sure your fields are private.
### Specific Feedback
- Your BallContainer add/remove methods are more complicated than they need to be.
Look at the documentation for `Set.add` and `Set.remove` and see whether you
need to explicitly handle cases the cases of adding something that already
exists in the set and removing something that doesn't exist in the set.
- you should comment any fields that you add (like `maxVolume`)
- `maxVolume` field should be private. You do not want others to be able to modify this value.
- class names should be capitalized (including `compareBall`). You should also comment any additional classes
