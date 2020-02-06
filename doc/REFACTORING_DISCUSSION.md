### REFACTORING LAB
#### Simulation Team 07

#### Discussion

To start, each of us felt that in the rush to meet the deadline for the basic implementation, we had each produced code
that was messy and needed to be cleaned up and refactored. Since the website was down to start the class, we each went to
our own branch and worked on those issues that we self identified within our own branches, some of which included
changing some public instance variables to protected (a silly mistake), breaking down larger methods, utilizing some
helper functions/making the ones that existed more generalizable, as well as decreasing dependencies between classes.

At some point during this process, the website become live, and we ran it on our master branch, which had not merged with
any of the changes we had made during the lab. As such, we were none too surprised to see that some of the biggest issues
were those which we had felt were issues in our struggle to meet the deadline. For example, in the Grid Builder class, 
there was a function doing at least three things and was 50 lines long, which was able to be broken up into three functions
each around 20 lines long. Other issues we found were smaller, but also good ideas to hold onto moving forward. For example, 
making sure that all variable declarations are general/super classes, and that we removed all magic values and unused imports.
However, we were quite happy to see that our intuition matched the judgement of the software, and we feel as if we made
progress towards fixing these issues. Finally, we felt that most of the issues found by the software were easy fixes,
which in general made us confident moving forward that we should be able to utilize this code as a basis easily.