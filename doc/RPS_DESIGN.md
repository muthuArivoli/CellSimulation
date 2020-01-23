#RPS Design 
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