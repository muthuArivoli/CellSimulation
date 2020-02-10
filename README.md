Simulation
====

This project implements a cellular automata simulator.

Names:
Muthu Arivoli (ma381)

Dennis Harrsch (dmh58)

Luke Peterson (lp175)

### Timeline

Start Date: 1/23/20

Finish Date: 2/11/20

Hours Spent: 80

### Primary Roles

Muthu Arivoli: Simulation

Dennis Harrsch: Configuration

Luke Peterson: Visualization

### Resources Used


### Running the Program

Main class: cellsociety/Driver

Data files needed: 

ButtonNames.properties

GraphInformation.properties

Shapes.properties

DefaultFire.xml

DefaultGameofLife.xml

DefaultPercolation.xml

DefaultSegregation.xml

DefaultWaTor.xml

Features implemented:

In terms of Simulation, 5 cellular automata (Fire, Game of Life, Percolation, Segregation, Wator) were implemented. There were implemented such that various grids including a hexagonal and rectangular grid were used. Boundary conditions include a finite boundary and a torus boundary. Multiple neighborhoods are also easily implementable, with the 8 edge square being implemented.


### Notes/Assumptions

Assumptions or Simplifications: 
Simulation Speed Slider changes the speed of all simulations. All simulations run in one window. The pause and step buttons only effect the corresponding simulation.

Interesting data files:

Known Bugs: The triangle grid is not fully implemented, and thus cannot be used in its current state. The hexagon grid sometimes does not pick the correct neighbors and won't update the simulation accordingly to the rules of the simulation.

Extra credit:


### Impressions

Dennis Harrsch: Far and away, the most difficult aspects of this project involved team work, and working cohesively with others such that when everything came together it worked well. Luckily, towards the end of the project, getting into the habit of good git practices really helped in this regard. However, at other times, assumptions were made and left untested due to the fact that we were constantly waiting for others to finish, and not communicating well. It was also difficult to properly allow for enough flexibility in configuration files, while also juggling helping to complete the visualization aspects. We had to make decisions with regards to what we valued, and ended up preferring the visual aspects to allowing for more variable configuration files, though this is a choice that could be argued obviously. All in all, it was a very formative and helpful experience to work on a team like this, and learn these practices, while also practicing these good methodologies that will transfer to any project I work on in the future.

Muthu Arivoli: This project has been my first major software project with a team. Navigating through team dynamics and implementing a large software project has definetly been a challenge, and has taught me many useful skills for my future in software development.

Luke Peterson: Working in a team was new to me and presented many challenges. Navigating Git and working together cause lots of frustration but proved to be a valuable asset in the end. I have learned a lot about working on code at the same time as team members and about inheritance.
