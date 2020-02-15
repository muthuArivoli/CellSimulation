# Simulation Design Final
### Names

## Team Roles and Responsibilities

 * Team Member #1 - Dennis Harrsch dmh58 - Visualization, Driver, and Configuration

 * Team Member #2 - Muthu Arivoli ma381 - Simulation

 * Team Member #3 - Luke Peterson lp175 - Visualization


## Design goals

#### What Features are Easy to Add

Our group spent a lot of time designing and discussing potential implementations before we actually 
started implementing code, and so I think that we discussed and were fairly prepared for most of the
 changes that needed to be made to go from the basic to the complete implementation. In fact, very 
 few large details or implementation decisions had to be changed or reversed, but rather we found 
 that the decisions we had made were easily adaptable and expandable to the new goals. For example, 
 since the visualization component was so fully divested from the simulation, but still had a means 
 of accessing each individual component of the grid/simulation (without the potential for changing it),
  it made adding all of the new features on the Visualization very simple and required no large design
   scale changes, but simply addition of new classes and functionalities to visualization. Similarly,
    since the simulation is so self contained, adding new simulations is very simple too, although it
     does require adding some components to the configuration. However, this is essentially unavoidable: 
     if we want the configuration and simulation to be separate, then they must both have some information
      stored about the different simulations and the way that they operate, so this is necessary. But, we do
       believe that we found a way of compacting and communicating these easily and intuitively through the 
       Parameter objects. Finally, adding to the configuration was also fairly simple in that it was 
        in regards to parsing the XML files and error handling, and since all of that was contained within 
        the GridBuilder class, as long as the output and functionality matched itself to the needs of the 
        Parameter and initial configuration that are being created then there was no need to change anything
         other than configuration. One thing that was difficult to add in were newer features like Tauroid
          edges or different shapes, as it required refactoring and replacing some information in the Parameter
           and CellState objects to avoid the easier solution of just adding more if-trees to more places in the
            code. While this wasn't ideal, and did require some work to change the internal design of the configuration,
             it did not require large scale design changes.

Two specific examples of features that are easy to implement:

The first thing that comes to mind, and has been said/will be said this multiple times throughout the document,
 is the ease of adding features to the visualization due to its total separation and encapsulation. 
 Specifically, the fact that it accessed a replica of a grid as a collection, it meant that it could
  basically do whatever it needed, through any method best suitable to the task, without having any 
  added dependencies on the simulation. This meant that simply by internally adding classes, features,
   and functionalities was sufficient to add in all of the visualization additions we made. One that 
   would have been more difficult, and which we were unable to implement, would have been changing 
   the state of the simulation through the UI, but this would have been able to be implemented by 
 telling the simulation in the Driver that the user had changed the state of a specific cell, and we
  were very close to implementing it but ran out of time. Thus, the use of a Driver class was also 
  useful to achieving this separation.
  
The second way that we feel the design made adding new features easier to implement would be through
 the ease of adding new simulations via our design, as well as new neighborhoods and shapes. With
  regards to the new simulation, simply adding a new simulation, parameter, and cell object would
   be sufficient to make reading in a new XML file through a method in GridBuilder possible. There was
    necessarily, communication between the configuration and simulation (if one stored all of the information
     with regards to the rules and inputs of a simulation, then they would essentially be the same and
      they would not be separated as they logically should be) but the changes that needed to be made
       were intuitive and were insulated enough such that they did not require any reworking of the
        communication channels between the two, and require only internal changes. Then, in terms
         of adding new shapes, since the implementation of the simulation was so hidden, it did not matter
          to the configuration or visualization if it handled a grid differently as long as it was updating
           it correctly. Similarly, our group choice to use a map rather than an Arraylist made it such that
            adding in new neighborhoods was really simply, and even though that is an implementation 
            level decision, our thought process was that a map would be more flexible and thus the better choice,
             and so our group's intentional process of designing our project paid off here.

## High-level Design

At a high level, the program has four primary components - a configuration, which initializes the grid
and starting point for the simulation, the simulation itself which does all calculations, the
visualization component which translates the output from the simulation to an interpretable UI, and 
finally the driver component which acts as a means of synthesizing the different components and allowing
for another level of separation between them and their communication. 

The configuration takes in some user input, either a default selection or a file loaded, and creates a specified Parameter object and the initial grid as a collection from that input. In turn, Driver communicates this initial configuration and Parameter to the simulation and visualization, which starts calculating the next steps or displays the initial configuration, respectively. From then on, after a file has been selected in some form, Driver asks the simulation for a replica of the current state, and then this replica of the grid is handed over to the visualization for display. The visualization does no calculations, nor do any methods in visualization call a simulation function, but rather the Driver communicates their outputs to each other. Similarly, the simulation does not have JavaFX even imported, and is totally dedicated to calculation of the next stage and creating the new grid every iteration.

#### Core Classes

CellSociety
    CellState
    Driver

Configuration
    Configuration
    GridBuilder
    ConfigurationError
    Parameter
    
Simulation
    GridBehavior
    Grid
    Clonable
    Simulation
    Graph

Visualization
    Board
    Bargraph
    GuiTools
    Slider
    Visualization

## Assumptions that Affect the Design

Generally speaking, few assumptions were made outside of those internally for each of the components. 
The only assumption that is necessary to hold is that any given implementation is able to be passed 
around as a collection through Driver, and that in Driver each of the necessary pieces of information
are properly transported from place to place when necessary.

#### Features Affected by Assumptions
The only features that are affected by these assumptions was the creation of a Driver class, which 
added the insulation needed

## New Features HowTo
#### Easy to Add Features
A specific example of an easy to add feature would be a new simulation.

One of the primary design goals we had going into the project, and therefore in creating and implementing
our aforementioned design, was that our program would be easily applicable to any given cellular automata, and that adding new simulations would therefore be simple. For the most part, we were successful in this goal, but actually going to write this some of the technicalities of adding a new simulation come to mind which add just a couple more steps than the one that we had set as a goal. However, first and most obviously to implement a new simulation one would have to create a new subclass of Simulation that implements how the simulation gets the next state. Then, one would have to add a subclass to the configuration to enable taking in input from the user and setting up the initial state of the simulation so that the newly implemented class can act on it. This requires two steps, but they are fairly simple and intuitive. In the configuration, one would have to define the Parameter object subclass for the simulation, as well as a class which implements CellState which represents a given cell for the simulation. Finally, and this is the step that I think ideally would be addressed differently, one would have to add in a means in to read in the XML file that creates the new simulation Parameter object. This, however, has been done in such a way that it is simple - one must simply add in a method and call it in the makeParameter function. 

Thus, to add a new simulation you just add a way to read it in, objects for its parameters and cells, and then the simulation itself (i.e. how it updates). Though there are several steps, it is fairly simple and intuitive as to what has to be added, and to keep configuration and simulation separate then these parts will have to be created separately.  There is room for improvement probably, especially in adding in new ways to read a new simulation, but overall we did a fairly good job in making adding a new simulation simple.

Similarly, other features are added simply by adding them to the specific implementation of the component
they belong to. Indeed, adding a new simulation is the most complex feature to add, as it requires navigating
the relationship between simulation and configuration and yet it is still simple.

#### Other Features not yet Done
Other features that would be easy to add and yet are not quite finished involved speeding up and slowing
down the simulation based on user input or changing the state of the grid based on user input. These would
simply require the visualiztion letting the Driver know that the user asked for this, and then in turn
the driver would notify the simulation. Thus, this would also be simple, but was not able to be implemented
in the time frame given. However, we can see how our design is flexible and expandable.