# cellsociety

Ying Wang, John Dai, Inan Tainwala

Date started: 9/11/15

Date finished: 9/29/15

Number of man-hours: 1,000,000 hrs

Major Roles: Inan-XML parsing, John-Simulation backend, Ying-GUI

Resources used: Java documentation, CS308 lectures and readings, StackOverflow

Files to start project: Main.java

Files to test project: XML files, JohnsTemporaryTester.java, InitializeSimulation.java's main method

Data/resource files: XML files, .properties files

Program info: The simulation grid appears in a separate window than the control buttons. Some of the buttons do not have functionality. Pressing the play button will continue running even when closing the simulation window and opening a new simulation. The drawing may lag sometimes due to memory/cpu usage.

Bugs/crashes/problems: WaTor World simulation is unable to properly interface with the new TorusEdges implementation. Some parameters such as gridstyle in the GUI were hardcoded due to time constraints; if we had excess time we would extract them as variables.

Extra features: Dynamic XML loading through the GUI

Impressions of assignment: Very tough and time-consuming to implement all of the extensions, and hard to try to assume as little as possible during the basic sprint. The product itself in the end was interesting to view, and cellular automata is applicable to a variety of disciplines. The project definitely emphasized the importance of class hierarchies and polymorphism, although it might have been more useful to learn about interfaces, packages, etc. earlier in the project timeline.







#To Do XML Parser

- **2 XML files????**
- **Change the Simulation.properties and XML so that rules dont have specific names**
- **Is what I am doing with the properties file cheating in a way?**
  + For Example the Cell.properties file

- Separate the Cell parsing from the Factory** + **Extend the Cell parsing to create the different cells
  + default values
  + probability distribution for cells
  + Default cell location?
  
- How do you use the getresourcestream method to get the config files if you move all the classes into packages?


#Sim Refactoring

- Remove hardcoded state ints


names of all people who worked on the project
date you started, date you finished, and an estimate of the number of hours worked on the project
each person's role in developing the project
any books, papers, online, or human resources that you used in developing the project
files used to start the project (the class(es) containing main)
files used to test the project (the class(es) containing TestSuite)
any data or resource files required by the project (including format of non-standard files)
any information about using the program (i.e., command-line/applet arguments, key inputs, interesting example data files, or easter eggs)
any known bugs, crashes, or problems with the project's functionality
any extra features included in the project
your impressions of the assignment to help improve it in the future
