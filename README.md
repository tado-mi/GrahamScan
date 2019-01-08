# showing off:

**author:** tado-mi

**date:**   6 Jan 2018

**use:**    free. credit when due is encouraged and appreciated

# general:

included is a package of Java source code files for the [Graham Scan](http://www.wikipedia.org/wiki/Graham_scan) - (eventually) visually demonstrated as it is executed on an arbitrary data.

Graham Scan is an efficient algorithm for computing the convex hull around set of Points.

# included files:

## Point.java

description coming soon

## GrahamScan.java

description coming soon

## GrahamScanGUI.java

description coming soon
 
## main.java

Responds to standard input, and either generates random points, or reads from a file, which should be in the specified presentation: number of Points in the set, followed by the Points in simple x_1 y_1 ... x_n y_n format.

## makefile:

Some magic for your convinience. For more information, see [here](https://www.cs.swarthmore.edu/~newhall/unixhelp/javamakefiles.html).

## compiling:

hit on the terminal:
    
    make
    make random

or:

	make test

if you have the data in a "test.txt" file. or:

	java main path/to/file

to free your computer from the bytes occupied by the .class:

    make clean

# to do:

* clear up bumps with scaling / translating / graphing the points set
* make lines thicker
* pick pretty colors!
* highlight the Point being examined
* demonstrate lines as they are being considered
* allow for user to { insert the filename / choose to see a random example } in the GUI
* allow for user to set pace in the GUI
* add explanations as visual progressess
* implement Chan's algorithm

# other

shall such desire be felt, feel free to report bugs/suggestions/questions to tadouchiha@gmail.com
