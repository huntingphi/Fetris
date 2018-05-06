Fetris (Fake Tetris)
Assignment 1 - CSC2003S

To Run:

Requires Gradle

Go to desktop/build/libs/ and double click on the jar file.

Alternatively:

Unix based OS: ./gradlew desktop:run

Windows: gradlew.bat desktop:run

To Build:

Requires Gradle
Unix based OS: ./gradlew desktop:dist

Windows: gradlew.bat desktop:dist

The built .jar file will be in desktop/build/libs/

To run it after build, change to the above directory and run: java -jar desktop-1.0.jar

Controls:
Control - Key
Move Left - Left Arrow
Move Right - Right Arrow
Move Down - Down Arrow
Drop Down - Spacebar
Rotate - Right Shift
Move Up - Up Arrow (Bonus Feature)"
Toggle Move Up - U
Reset game - Enter
Toggle music - M

Special features:
Music playing in background
Blocks can move upwards if the upwards toggle is on

Implementation
Used a form of bitmapping to populate an array with different values corresponding to different blocks and then printing an image corresponding to the block.

Gameplay

The gameplay involves the player moving and positioning the various pieces in an attempt to complete rows and avoid the blocks stacking to the top of the play area.


The player can move the blocks left or right and the blocks can be rotated using the Right shift key.

As an interesting extra feature, blocks can also be moved upwards if the up toggle is on.

The player can drop the block from its current position. It will stop when it collides with the floor or another block.

After the blocks land, there is a time (the same as the rate at which the block falls) in which the block can be moved.

Finally, once the game is over, a popup saying "Game over!" will appear

*BUGS*
Pressing the spacebar too soon after a block is generated will generate another block on top of the current one and the game will think the game is over.

