# Graham Scan

Code written by [tado-mi](github.com/tado-mi) for [CSC 284: advanced algorithms](https://cs.rochester.edu/~stefanko/Teaching/18CS484/) class at the University of Rochester. It is made public to demonstrate a code sample by the author, and for learning. We trust it will not be used to violate academic honesty policies.

The included code implements the [Graham Scan](http://www.wikipedia.org/wiki/Graham_scan) to solve the [Convex Hull](https://en.wikipedia.org/wiki/Convex_hull) problem. It outputs the steps of the algorithm on the input in a text format, and will soon have an implementation of step-by-step execution of the algorithm on a GUI.

Is a **work in progress**.

# Files:

## Point.java

Simple class representing a point in R^2. Of interest are the comparator methods, `angleComparator` in particular, which allows to sort points for the Graham Scan.

## GrahamScan.java

The most involved class both implementing the algorithm (`computeHull` method), maintaining the execution steps (through `log` method), and dealing with graphics.

- [ ] finish `log` method
- [ ] decide whether GrahamScan should be involved with graphics

## GUI.java

Snipped from a code originally written by Michael Scott (see [here](https://www.cs.rochester.edu/courses/254/fall2019/assignments/java.shtml) for details, if interested). Included here as a source of inspiration.  

- [ ] improve graphics: scaling & translating the points
- [ ] highlight the 'focal' points and lines
- [ ] enhance interactivity on the GUI: allow the user to { insert the filename / choose to see a random example } in the GUI; { set pace } in the GUI
- [ ] display text explanation as steps are being executed

## Demo.java

Where the `main` resides.

## Compiling:

There is an included makefile for convenience. To run the code on a random input set, hit on the terminal:

    make
    make random

or, to run the code on the provided `test/small.txt` test case, hit:

	make test

or, to run the code on a custom test case, run:

	java main path/to/file

to free your computer from the bytes occupied by the .class:

    make clean
