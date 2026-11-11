# Game of Life Simulation (COMP2000)

A Java Swing version of Conway's Game of Life, made for COMP2000 Object Oriented Programming at Macquarie University. It uses only the standard Java library (JRE), no external libraries.

Student: Rumit Maharjan (48930830)

## How to run

From the `src` folder:

```
javac *.java
java Main
```

Or open the project in IntelliJ and run `Main`.

To run the speed comparison:

```
java Benchmark
```

## What it does

The board is a grid of cells that are alive or dead. Every 120 ms the grid moves forward one generation using a rule. The edges wrap around, so a glider that leaves one side comes back on the other. The window starts with a random board and a glider, and uses the HighLife rule (B36/S23). The classic rule (B3/S23) is also in the code.

Controls:
- Click or drag on the grid to bring cells to life
- Start / Pause to stop and resume the simulation
- Reset to fill the board with a new random pattern
- Clear to empty the board

The top of the window shows the current generation and how many cells are alive.

## Files

| File | What it does |
|---|---|
| `Main.java` | Builds the grid, window, buttons and timer, and registers the listeners |
| `Grid.java` | The board. Counts neighbours, steps one generation (`step()` and `stepParallel()`), notifies listeners |
| `Rule.java` | Strategy interface, decides if a cell is alive in the next generation |
| `Rules.java` | The two rules, `CLASSIC` and `HIGHLIFE`, written as lambdas |
| `GridListener.java` | Observer interface, called when the grid changes |
| `SimulationPanel.java` | Draws the grid and handles mouse clicks and drags |
| `InvalidDimensionException.java` | Custom checked exception for bad sizes or out of range cells |
| `Benchmark.java` | Times `step()` against `stepParallel()` and checks they give the same result |

## Design patterns

- **Strategy:** `Rule` is the interface, `Rules.CLASSIC` and `Rules.HIGHLIFE` are the strategies, `Grid` is the context and `Main` picks the rule.
- **Observer:** `Grid` is the subject and `GridListener` is the observer. The panel repaint and the stats label are listeners added in `Main`.

## Streams and parallelism

`step()` and `countAlive()` use `IntStream`. `stepParallel()` is the same as `step()` with `.parallel()`. Each row is built by its own thread from the old board, and the board is only replaced after the stream finishes, so no locks are needed. The benchmark compares the two over 50 generations and checks that no cells differ.

Results on my machine (32 processors):

| Board | Sequential | Parallel |
|---|---|---|
| 1000x1000, 20 steps | 468 ms | 38 ms |
| 200x200, 300 steps | 244 ms | 47 ms |
| 60x40, 2000 steps | 97 ms | 53 ms |

The window itself uses the normal `step()`, since at that size the difference isn't visible.

## Versions

The Week 7 version (Cell hierarchy and generic Grid) is under the `week7` tag. `main` has the Week 13 version.
