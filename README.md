# DAA-Assignments
Assignments of DAA - 5311

![BubbleSort](https://github.com/user-attachments/assets/755e2529-7355-4375-b399-213607ef4120)
![InsertionSort](https://github.com/user-attachments/assets/adb8fab1-78b1-4e48-b35f-39450a5d2ca1)
![SelectionSort](https://github.com/user-attachments/assets/6ef2ea4c-4813-4cbc-be7d-e0a750d82f8b)

Working of selection sort:
1. Finding minimum value
   - first element is considered smallest and is used to compare other elements and is stored.
   - then it searches for smaller value than stored value.
2. Swapping values
   - when smallest element is found, swapping is performed
  
This process is performed until all elements are kept on their places.

Arguing the correctness

Base Case: at first the sorted part is empty and is traversed and sorted.
at start of each loop, array upto index is in sorted order.

Algorithm completes at n−1 iterations,
where n=number of elements in an array. 
Each iteration places one more element to its proper position, at last making whole array sorted.

The upper bound and lower bound of selection sort is O(n^2)

Selection sort is considered correct as it sorts and correctly positions the elements to its places with every loop.
