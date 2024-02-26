# PACMAN

2 The Objects
Each object is an image consisting of a set of picture elements, pels, or pels. Each pel is defined by 3 values
x, y, and c; (x, y) are the coordinates of the pel and c is its color. We will think that each object f is enclosed
in a rectangle rf (so all the pels are inside this rectangle and no smaller rectangle contains all the pels; see
Figure 1 below). The width and height of rectangle rf are the width and height of the object. To determine
the position where an object f would be displayed, we need to give the coordinates (ux, uy) of the upper-left
corner of its enclosing rectangle rf ; (ux, uy) is called the locus of the object.
For specifying coordinates, we assume that the upper-left corner of the window ω where the objects are
displayed has coordinates (0, 0). The coordinates of the lower-right corner of ω are (W, H), where W is the
width and H is the height of ω.
Each object will have a unique integer identifier used to distinguish an object from another, as two objects
might be identical (but they cannot be in the same position).
The pels of an object f will be stored in a binary search tree that you are to implement. Each node in
the tree stores a data item of the form (position,color) representing one pel, where position = (x, y)
contains the coordinates of the pel relative to the upper-left corner of the rectangle rf enclosing the object.
For example, the coordinates of the black dot in Figure 1 below are (20, 10), so this black dot corresponds
to the pel ((20, 10),black). As shown in Figure 1, the locus of object f1 is (40, 25), so when rendering f1
inside the window ω the actual position of the black dot is (20 + 40, 10 + 25) = (60, 35).
Note that by storing the pels in the binary search tree with coordinates relative to the object’s enclosing
rectangle, the data stored in the tree does not need to change when the object moves: The only thing that
needs to change is the locus of the object
