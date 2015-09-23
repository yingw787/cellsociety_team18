#**SPRINT 3**

Specification:
Complete as much as you can of the following extensions to the project (with the features in each category generally given from easiest to hardest):


#Simulation
####Allow a variety of grid location shapes:
- square (with 8 neighbors max, 3 on top and bottom and 1 on each side)
>NOTE: this should be what you have already implemented

- triangular (with 8 neighbors max, 3 on top and bottom and 1 on each side)
- hexagonal (with 6 neighbors max, 1 on top and bottom and 2 on each "side")

####Allow a variety of grid edge types:
- finite (bounded by the initial size, with locations on the edges having smaller neighborhoods)
>NOTE: this should be what you have already implemented

- toroidal (bounded by the initial size, with locations on the edges having full size - neighborhoods such that the neighbors past the edge are taken from the opposite side of the grid)
>NOTE: this simulates an infinite periodic tiling because each side is connected to its opposite side

- infinite (unbounded, as cells become active on the edge the grid is expanded to include them and their neighbors)
>NOTE: this should have an initial default size for displaying and be scrollable so you can get to the extra locations rather than making the locations successively smaller

####Implement the rules for three additional simulations (all simulations should work on all kinds of grid types):
- [slime molds](http://zool33.uni-graz.at/schmickl/Self-organization/Group_behavior/Slime_mold_behavior/slime_mold_behavior.html) (any of the models described)

- [foraging ants](http://cs.gmu.edu/~eclab/projects/mason/publications/alife04ant.pdf) (rules start at the bottom of page 2)

- [sugarscape](http://www2.le.ac.uk/departments/interdisciplinary-science/research/the-sugarscape) (at least 2 of the models described)
>NOTE: these simulations require each grid location contain a "patch of ground" that can also have state and rules in addition to the standard CA cell



#Configuration
####Implement error checking for incorrect file data, such as (but not necessarily limited to):
- no simulation type given
- **default values when parameter values are not given**
- invalid cell state values given
- cell locations given that are outside the bounds of the grid's size

####Allow simulations initial configuration to be set by, such as (but not necessarily limited to):
- list of specific locations and states
>NOTE: this should be what you have already implemented

- completely randomly based on a total number of locations to occupy
- randomly based on probability/concentration distributions

###Allow simulations to be "styled", such as (but not necessarily limited to):
- kind of grid to use, both by shapes and by edges
- if scrolling is implemented, the size of each grid location (instead of it being calculated)
- whether or not grid locations should be outlined (i.e., to be able to "see" the grid or not)
- color of cell or patch states (at least support empty to represent a water world or space world, etc.)
- shape of cells or patches (i.e., circles, rectangles, or arbitrary images)
- neighbors to consider (i.e., cardinal directions, diagonal directions, or all directions) with appropriate error checking (e.g., hexagonal grids do not have cardinal directions)
>NOTE: these values can be added to the existing XML configuration file but  are more appropriate in a separate file since they can be applied to any simulation

- 

#Visualization
- Display a graph of the populations of all of the "kinds" of cells over the time of the simulation
>NOTE: an example is described in the predator-prey simulation

- Allow users to interact with the simulation dynamically to create, kill, or change a state at a grid location
>NOTE: this is mostly just listening to events directly occurring on the grid

- Allow users to interact with the simulation dynamically to change the values of its parameters
>NOTE: this requires GUI components like sliders or text fields organized in a separate panel

- Allow users to interactively set the initial states of the grid locations and save them in the same format as your XML configuration file so they can be loaded into the program (thus users do not have to edit XML files by hand)


> Written with [StackEdit](https://stackedit.io/).
