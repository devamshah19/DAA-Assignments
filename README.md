Assignment - 4 Hands On

Problem 0
1.	Implementation
    function fib(n) {
    if (n <= 0) return [];
    if (n === 1) return [0];
    const seq = [0, 1];
    for (let i = 2; i < n; i++) {
        seq.push(seq[i - 1] + seq[i - 2]);
    }
    return seq;
}

Debugging:
The code will take to lowest value of n i.e. 1 and then start returning values
1.	fib(5) will call fib(4) + fib(3)
2.	fib(4) will call fib(3) + fib(2)
3.	fib(3) will call fib(2) + fib(1)
4.	fib(2) will call fib(1) + fib(0)
5.	fib(1) will return 1 and fib(0) will return 0
for eg, in fib(5) 
	it will call itself until it gets n=0; then start returning values according to n
	at n=0 it returns 0 
  at n=1 it returns 1
	for n=2 it returns 0+1 = 1
	then for fib(3), fib(2) + fib(1) = 2
	then, for fib(4), fib(3) + fib(2) = 3
	then, for fib(5) the loop condition will become false and final series will be: 
		0, 1, 1, 2, 3


2.	prove time complexity
for every number, there are 2 more nested calls for it, for 0 and 1 its only single call
for level 0 it will be fib(n): T(n)
then on level 1 it will be fib(n-1) + fib(n-2): T(n-1) + t(n-2)
then on level 2 it will be fib(n-1) + fib(n-2): T(n-1) + t(n-2)
then on level 3 it will be fib(n-1) + fib(n-2): T(n-1) + t(n-2)…
T(0) + T(1) + T(n)
T(0) + T(1) + (T(n-1) + T(n-2)) + …
which means, 2 nested calls for every single call.
Hence the time complexity grows by n times everytime with 2 calls resulting in time complexity of 2^n.

3.	Comment on way's you could improve your implementation
a.	Divide and conquer is a strategy in which a problem is broken down into smaller problems and made more manageable and then solved and then combined for solution of the original problem.
b.	Precomputation and caching: In this, the values are pre-calculated and cached ahead of time. Useful when range is known.
