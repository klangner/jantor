# Cantor

Cantor (after [Georg Cantor](http://pl.wikipedia.org/wiki/Georg_Cantor)) goal is to check modularity 
of the source code. Currently works only with Java projects.

## Analyzing project

To analyze project:

    cantor.jar [[command]] src

Where command:
* list_modules - list all modules found in the project
* (TODO) validate - Check if only visible packages are imported
* (TODO) find_cycles - Find cycles in modules dependecies.


## Redistributing
Cantor source code is distributed under the Apache 2.0 License. 
Cantor may be freely redistributed, subject to the provisions of this license.
