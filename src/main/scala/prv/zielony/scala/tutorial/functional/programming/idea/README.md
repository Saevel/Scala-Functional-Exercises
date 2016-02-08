In this exercise we are going to put the abstract notions of Functional Programming into practice.

The idea is to read a list of Ints (from 2 upwards) from a text file, parse them and use the Erastotenes Shieve to
calculate all primes among them.

The Erastotenes shieve finds primes in a range [2, N] in such a way:

1. Take each k from 2 to [sqrt(N)]

2. For each k take each n from [2, N]

3. If n is divisible by k, but not equal to it, cross it out

4. After all k's have been checked, the numbers that were not crossed out are the requested primes


The tricky parts is, our computations will be done in parallel threads, with each thread operating on all the n's, but
only a subset of k's. Therefore, we need to make the code as functional as possible to exclude all side effects that
may distort the results.

A. The FileParser.parse method will be executed on the driver, but it isn't safe either. The Java APIs used for file
manipulation can throw some exceptions, which is a side effect. Remove it by catching the exceptions and using the
Either class.

B. The ErastotenesShieveNonFunctional.shieve method is not very functional (:D) as it generates side effect by mutating
the collection of crossed-out elements "under the cover" rewrite that method in a functional style in the Erastotenes
Shieve Functional object so that it doesn't cause this problem.