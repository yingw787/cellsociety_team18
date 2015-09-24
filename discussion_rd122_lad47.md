#NeighborProcessor
Two subclasses had similar process() methods, with just the if condition different. We talked about
extracting the method into the superclass, but other subclasses use have different process() methods so
we didn't want confusion for that. We talked about extracting it to a different method in the super class
and have the subclass call it if needed. We then realized that the parameters would be a little different
so we created another method that would call the extracted method with different parameters.

#TorusEdges
The torusX and torusY methods were basically exactly the same so we deleted one and renamed the other to
something more general, which continued to be called with different parameters in the code.