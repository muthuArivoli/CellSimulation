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
Visualization allows for graph displaying cells, can be hid, can be paused and stepped through. One time bug occurred where these switched functionalities with quick clicking - but unlikely to occur again. Can change shapes to match the grid implemented. 
Configuration handles errors by passing in default values when the file cannot be understood.
Visualization can run multiple simulations at once, pop up in different windows.
Configuration allows for totally random generated grids.

### Notes/Assumptions

Assumptions or Simplifications: 
Assume that people will look at configuration files to match tags before making new files - i.e. prefer to load default if tags do not match, rather than try to guess what the user meant. Sends error message if this is the case.

Interesting data files: Default files include files that we believe are representative of our simulations, but other files can be added which follow suit.

Known Bugs: The triangle grid is not fully implemented, and thus cannot be used in its current state. The hexagon grid sometimes does not pick the correct neighbors and won't update the simulation accordingly to the rules of the simulation. Configuration might not load if given a blank file or file that is of a different type. Bars on graph do not match color with those in the simulation. Some small blips during simulations in terms of switching states. Clicking on cells does not change color. Slider does not change speed - would have been easy to implement but simply ran out of time.
Visualiztion will run for a second before popping up meaning that the first screen is not displayed for a few rounds.

Extra credit:
Visualization has several new features added - see above, similarly with Simulation and Configuration.

### Impressions

Dennis Harrsch: Far and away, the most difficult aspects of this project involved team work, and working cohesively with others such that when everything came together it worked well. Luckily, towards the end of the project, getting into the habit of good git practices really helped in this regard. However, at other times, assumptions were made and left untested due to the fact that we were constantly waiting for others to finish, and not communicating well. It was also difficult to properly allow for enough flexibility in configuration files, while also juggling helping to complete the visualization aspects. We had to make decisions with regards to what we valued, and ended up preferring the visual aspects to allowing for more variable configuration files, though this is a choice that could be argued obviously. All in all, it was a very formative and helpful experience to work on a team like this, and learn these practices, while also practicing these good methodologies that will transfer to any project I work on in the future.

Muthu Arivoli: This project has been my first major software project with a team. Navigating through team dynamics and implementing a large software project has definetly been a challenge, and has taught me many useful skills for my future in software development.

Luke Peterson: Working in a team was new to me and presented many challenges. Navigating Git and working together cause lots of frustration but proved to be a valuable asset in the end. I have learned a lot about working on code at the same time as team members and about inheritance.
