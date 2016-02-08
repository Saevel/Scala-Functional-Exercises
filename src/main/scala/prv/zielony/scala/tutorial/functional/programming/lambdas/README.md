This exercise will test your usage of Lamba Expressions (and related concepts) in Scala.

The aim of this exercise is to finish the implementation of Counting Sort, a linear-time sorting algorithm fit for the
situations when a collection of integers that fall in a given range.
The way counting sort works is as follows:

1. Knowing the range of the numbers fall into, we map all of them to the [0, M] integer range, where 0 corresponds to
the lower boundary of the original range and N corresponds to the upper boundary of the original range. Now we are
operating on [0, M] - range numbers

2. We initialize a B array of the same size (N) as the input collection, that we will refer to as A and fill it with
zeros.

3. We iterate through the A collection and for each number a we encounter in it, we set :

    B[a] = B[a] + 1

Therefore, after this iteration the i-th entry in the B array will contain a total number of occurences of i in the
A collection.

4. We iterate through the B array and set:

    B[i] = B[i] + B[i-1]

Therefore after this iteration the i-th entry in the B array will contain a total number of occurences of i and all the
number lesser than it in the A collection.

5. We iterate over the B array and calculate the new array, C , which will be the final result. For each b = B[i] and
b1 = B[i+1] we fill the entries in C from C[b] to C[b1] with the number i+1.

6. Before returning the result, we apply the mapping inverse to the one in point 1.

The array is now correctly sorted.
Should you have problems understaning the algorithm, read more in here: https://en.wikipedia.org/wiki/Counting_sort

EXERCISES:

1. Use a lambda expression to define the countOccurences method for the CountingSort trait according to the 3rd step
in the counting sort algorithm description

2. Use the "map" method in the Array class to implement the fillFinalArray method in the CountingSort trait accoring to
the 5th step in the counting sort algorithm description

3. Use an eta expansion to extract the "translate" and "reverseTranslate" from the CollectionMapper object and attach
them to the "translateCollection" and "reverseTranslate" fields in the CountingSort trait respectively.

4. Run "gradle test" to test the full implementation of the counting sort algorithm. Check the tests report in the
HTML form to find the errors that appeared.
