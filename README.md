# PixelEngine
---

Making it easier to create games with simple, pixelated graphics by creating an engine.

Development Started: 10/3/18

## Use
---

Essentially, there are two options.

Option 1:

- Put the src/PixelEngine folder in the base directory of your project (Or find/make a jar file)
- Compile the engine source files, and that should be it.

Option 2, building your game in this repository folder (easier if you're on linux) by cloning this repository:

- you'll be working in the src folder. 
- `cd Scripts` and `./fastbuild.sh` to build.
- 'cd Scripts' and './jargame.sh <name>' will package your game in a jar. <name> must match the file name that contains the main method to start your game. Ex. `./jargame BasicGame` will take BasicGame.class and specify that as the main class.

The src folder contains an example of a basic game.

Examples of how the engine can be used will be placed in this repository also.

## Development
---

Note: Development of this engine is nicer on Linux! You get to use scripts to do all the little jobs for you.

On linux, the process is built to be pretty smooth. Just make your edits, use `./fastbuild.sh' in `Scripts` folder, and test things out before committing.

Building everything as a jar is as easy as `cd Scripts` and `./jar.sh`.

Reach out to RootCellar9877@GMail.com if you wish to assist with development. Otherwise, happy modding/game building!

## Examples
---

These example games are small examples, in that they can be made in less than a day.
Games like these are really what the engine was originally built for; arcade-like games that can be made very rapidly. Which is why the engine really isn't that large.
In the future, the plan is to expand into more medium sized games that could support things like multiplayer and even basic modding.
(While the engine does support multiplayer, it is much more difficult than making a singleplayer game. Which is not the intent. Some parts of the process are also somewhat counter-intuitive and can be hard to keep track of.)

### Invaders - 2/1/2019

A small game based on the idea of Space Invaders. Contains stages and enemies that can level up,
to provide a greater challenge. The game makes use of projectiles, mobs, and polygon rendering,
and serves as a good example of how they are used.


### Arena - 2/10/2019

A very simple game using polygon rendering where the player is a boss that mobs try to take down.
This game uses the MouseHelper to show how the mouse can be used to allow the player to rotate, following
the mouse position.
Overall, the game uses projectiles, polygon rendering, mobs, and rotations.


### Dodge - 2/13/2019

A game made in just one day, where the player simply must dodge projectiles fired by the enemy mobs.
This game is actually very difficult, because the player does not regenerate, and can only take 10 hits.
Other than that, it's just shows other ways of using rotations, projectiles, and polygons.

Most of the one day was spent coming up with an idea, the game was actually programmed in less than 2 hours.
