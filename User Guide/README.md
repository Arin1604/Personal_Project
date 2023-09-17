# Personal_Project

[CHECK USER GUIDE FOR DETAILED DESCRIPTION OF GAME]

C(s15)ONTRA README

Overview:
    1) My app class has the start method that sets up the scene and gets the stage to show up. It is here that I
    instantiate the pane organizer.
    
    2) Pane Organizer is my top-level graphics class. I instantiate my border pane, my Hbox, and other layout elements.
    I also added a rectangle that I use to animate the lava in the pane organizer. I use helper methods to achieve my
    goal of making the lava animated. It is here that I instantiate my game class.
    
    3) My game class is the top-level logic class. It contains the most crucial interactions between the bullets,
    mobs, players, and other central classes. It is here that I instantiate the Player and the Player's Gun. This class
    also keeps track of the score and player health using two labels. It also checks if the game is over.
    Terrain Generator, which is central to building the infinite terrain, is also instantiated here.
    
    4) I delegated the function of creating the Terrain Blocks and managing them in an ArrayList to
    Terrain Generator. Additionally, it also handles the important interactions between the player and the terrain
    blocks. It also creates an instance of mob generator and uses it to create mobs and space them out.
    
    5)I delegate the function of generating Mobs to MobGenerator. It also handles the mob timeline. It is here that
    the mobs are generated and added to the mobArrayList. It is here that the mob guns are generated.



Design Choices:

Data Structures:
I made use of array lists to access and call methods on the mobs and bullets that were created. The Bullet
and Mob ArrayLists made it possible to carry out the complex logical operations in the Game class. The same can be
said for arraylist of all the terrain blocks. Using ArrayLists in these instances allowed me to deal with a
large collection of objects that had varying sizes.

I also used arrays to hold the bullets made by my triple gun as it was a fixed size.

I use a Queue to wrap the functionality of a linked list to best implement the FIFO mechanism. The bridge in my game
(which is a queue of bridge blocks) was supposed to collapse in such a way that the first piece was the first to fall.
This seemed like a perfect place to implement a queue as the dequeue method would not only remove the first
bridge block but it would also return it, allowing me to call methods to make the bridge fall.

Algorithms:
Of the algorithms I use in C(s15)ONTRA, The auto-aiming algorithm that I implemented for my mobs has got to be my favorite.
 Getting to create a target zone and then working out the maths behind calculating the player's exact position and
 then setting the bullet's velocity so that it would hit the player was all immensely satisfying!

Polymorphism:
Over the course, I have come to know that Polymorphism is a powerful tool for. C(s15)ONTRA showed me just how powerful it
can be. Inheritance is essential to my project. Its most profound impact on my project is in the implementation of
Triple Gun. In addition, the different terrains, mobs, and guns all add more variety to my program.



Known Bugs: none

Debugging Collaborators: none

Hours Spent: 112
