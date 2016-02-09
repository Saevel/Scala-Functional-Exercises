
In this part we will train to use lazy evaluation to bolester performance of certain methods.

A. In the package "heap", implement a lazily-evaluated heap data structure. The heap is a binary tree with fulfilling the
maxheap property: if x is a parent to y and z that x > y && x > z (here this should be understood in the sense of the
"compare" function passed as an argument). The heap should be immutable and implement the following operations:

    * ++ that returns a new heap containing the heap on which ity is invoked and the new element

    * -- that extracts an element from the top of the heap and returns it, along with a new heap without this element,
    as a tuple

    * map that takes each element of the heap and transforms it into a different element of arbitrary type returning
    the heap of elements belonging to the new type.

    Out of these, ++ and -- must lead to re-evaluation of the heap, but map should be lazily calculated.


B. Implement the LinePager class allowing paging through lines in a text file. It should work as follows:

    * If there is no such File or it is a directory, the next() method should always return Nil

    * If there are no lines in the File / no more lines in the File, next next() method should return Nil

    * If the next page is smaller than the page size, it should return only the remaining lines

    * If there is enough lines to serve the next page, a Traversable containign these lines should be returned

Use the Stream lazily-evaluated collection to enahnce the memory footprint of the solution

C. In the transfers package there is an implementation of TransferService that executes a dummy bank transfer. Since
    the included transaction may fail, in which case a token is not needed, yet is still included by the "transfer" method
    and its computation using the TokenGenerator is very costly in terms of performance -> Optimize the system using lazy
    parameters to enable the token not to be evaluated if the transaction has failed.