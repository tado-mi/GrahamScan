# variable for java compiler
JC = javac
J = java

# damage control
.SUFFIXES: .java .class

# target for creating .class from .java in format:
#	.original_extention.target_extention:
#		rule
.java.class:
	$(JC) $*.java
	
# macro for each java source file
CLASSES = \
	Point.java \
	GrahamScan.java \
	GrahamScanGUI.java \
	main.java
	
# default target definition
default: classes

classes: $(CLASSES:.java=.class)

# note: can replace 20 with the number of desired points
random:
	$(J) main random 20

# note: can just replace with your filename of the format
# number of points
# x1 y1 x2 y2 ... xn yn
test:
	$(J) main test.txt


clean:
	$(RM) *.class
	
