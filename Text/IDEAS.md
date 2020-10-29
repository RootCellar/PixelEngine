# Ideas

This file just contains ideas for the PixelEngine. These may or may not be implemented at some point in the future.

#### Physics engine

- More of an interesting concept that could make for some interesting games
  - Possibly vehicles, with horsepower. Apply work of engines properly into kinetic energy and solve for more realistic velocity (even braking force could be realistic)
  - gravity, make games with planets to curve projectiles and players
  - vehicles with mass might actually have interesting collisions (collision code would have to be improved though)

#### Data dumping

- Low priority, but could potentially be useful

#### Error Handling - IN PROGRESS

- Make a class that offers error handling features. Should be able to take errors and turn them into strings containing message, stack trace, and cause. This will make for easy logging and help with debugging.

#### UDP

- Make some basic UDP classes to support the UDP protocol. TCP will probably have better support though.

#### Monitoring Features

- Make some classes that are capable of monitoring the game engine/game. This class could be used to set a current status, keep track of some durations of how long things take, and even allow for jobs to be made.

#### GUI

- Add some classes that contain GUIs that can help monitor the engine/game as it runs. These could be extended to possibly pause the engine, and even add objects while it is running for testing.
