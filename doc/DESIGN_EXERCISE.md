# Design Exercise -- Lab 1/23

### RPS Design 
 1) The first design idea that comes to mind is to design an overarching "Signal" class, which would be the basis for 
 each of the potential signs which could be played in the game. These sign classes would then extend this basic signal
 class, with perhaps a method that can return either who/what signals it beats, or whether or not it wins given a 
 signal to compare to. There would then be a mediator class that would make these comparisons and determine the winner
 of an interaction. Then there would of course be a Game class to perform the functions of the game, and a Player class
 which would handle scoring and winner of the game as a whole.
 2) CRC cards were done by hand but are described fairly well above and below.
 3) Use Case solutions for our implementation:
 Handling a new game would be simple here, as we would simply have to initialize a new Game, in which new player
 objects could be created with a default score of zero.
 A player choosing his "weapon" would be handled with a method in the player class, called from the Game class/main method.
 In the game class, after the players choose their weapons, the Mediator class would compare the two weapons from each of
 the players. This would create a dependency between the two, but it seems unavoidable or at the least not obtrusive. The
 players would then have methods for handling a win vs handling a loss which would increment score and reset their weapon
 choice.
 In the case of adding a new signal or weapon, one could create a new Signal subclass with its own compareTo method, or 
 its own instance variables of who it defeats and who it loses to.
 In adding a new game, we decided that it would be possible to, rather than just having a default constructor for the signal
 classes, one could have constructors that take in those it beats and those it loses to. This way, the new game could change
 these components that are passed in, and thus change the way that the game works.
 
### Using Inheritance in Game
We found a slew of potential places where we could have (and wished we had) implemented inheritance in our Game designs.
Popular places for all three of us were in designing Brick and Powerup subclasses, rather than handling every possible 
scenario in those classes. It would have been cleaner, and easier to add more options, rather than making each of those
classes longer and more confusing as time went on. It would also make the code easier to read and understand, and imagining
a scenario where a lot of bricks are made, or a lot of powerups/levels, it would be much better than all of the necessary
if statements one could imagine having in our current implementation.

### High Level Design of Simulation
In terms of design, we all agreed that the need for encapsulation between the different components was key, especially in
terms of making the simulation generalizable and applicable to each of the different methods. We started by discussing the
different components of the configuration, which included some common elements such as the threshold for action or change
between cells, the original layout of the cells themselves, and the specific components within the Cell class. Then we 
moved on to discussing how we might implement the grid itself. We agreed that, to us, a graph was the most intuitive format,
as it is build for iterating through and between nodes of information, while still being quick and size efficient. We felt
confident that we would be able to implement such a method. In terms of visualization, we were unsure at the moment how
we might make that generalizable, but did recognize some similar components, such as a grid with various states represented
in some manner. 
After this discussion, we moved onto discussing how we might go about distributing tasks and hiding implementation. We 
were thinking about how there were possibilities to either alter some Cell class and its components in the configuration, 
or we could have some large class Implementation with subclasses of specific implementations in it. We thought that generating
the original state would be best to occur in the simulation component, as that would allow for hiding of implementation, and
would only require some basic parameters to shape this from the configuration. We then discussed how to implement
the actual logic of the simulation, with some timer and while loop iterating based on the inputs from the configuration. 
This would allow for the simulation to run while remaining shy. Then, for the visualization, we tried to think about how
we might pass in just a Collection, such that it does not matter how the actual simulation is implemented. At the moment,
we were thinking that we could pass in the output collection from the simulation, as well as some shape that is carried
over from the configuration, and from that we would be able to reconstruct the actual layout of the visualization. 

1) Cells know about their neighbors via some method called in them main simulation class which passes in their neighbors
information. Using a graph makes it easy to determine what cells are neighboring a given cell. Additionally, we discussed
a rough implementation which incorporates implementing a new Graph each iteration, which would be easy to determine if a 
Cell has been updated, and solves the issue of a changing cell affecting its neighbors. We recognize that this is not 
memory or speed efficient, but it makes the problem simpler to discuss and solve. We also tossed around the idea of
some step counter which would ensure that a cell could not be updated with a cell that is older or newer than it.
2) As mentioned above, there will be a Simulation class, which the Cell class will interact with to determine its threshold
and states. In turn, this will determine the functionality of a cell, but the cell itself will handle its functions and 
changing. Thus, the simulation class sets the rules that the cells play by.
3) The grid is a graph, which contains Cells and their neighbors. The grid itself, as we are currently describing it, is 
merely a representation of geometric relationships, but the actual behaviours would be handled by the cells and the
configuration of the simulation, which would be beneficial as it would help separate the implementation of the simulation
and the configuration. Only the simulation needs to know about the specifics of the grid, but the result should be passed
in some encapsulated form such that the implementation is hidden but there is enough information for the visualization to
be handled properly.
4) The simulation needs to know the thresholds for change, the states of the cells, and the way those two interact. This
will determine parameters and instance variables that define the Simulation class and Cell classes that will be used.
5) Something will have to be passed as output from the simulation to the visualization. We mentioned earlier that in an
attempt to hide the implementation, we were thinking that a Collection could be passed in, and the shape of the 
grid could also be passed in, such that the grid can be reconfigured from any collection
