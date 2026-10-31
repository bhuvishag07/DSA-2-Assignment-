# DSA 2 Programming Assignment 1: Two-Pointer Technique

## Student and Course Details

| | |
|---|---|
| **Student Name** | Bhuvisha Gohil |
| **Roll Number** | 150096725190 |
| **Cohort** | Demis Hassabis |
| **Subject** | DSA2 |
| **Assignment** | Programming Assignment 1 |
| **Topic** | Two-Pointer Technique |


## 1. Overview

The Two-Pointer Technique uses two indices that move through a sorted array so that a problem is solved in a single pass instead of repeated scanning.

This repository solves two problems on sorted arrays. Each problem has a straightforward version and an optimized two-pointer version. The same program is written in **C++, Java and Python**.

| Part | Problem | Straightforward version | Optimized version |
|---|---|---|---|
| A | Remove duplicates in-place and return `k` | Separate result list | Slow and fast pointers |
| B | Pair sum: return indices `(i, j)` | Brute force (nested loops) | Left and right pointers from opposite ends |

## 2. Repository Files

| File | Description |
|---|---|
| `TwoPointerAssignment.cpp` | C++ source code |
| `TwoPointerAssignment.java` | Java source code |
| `TwoPointerAssignment.class` | Compiled Java class |
| `TwoPointerAssignment.py` | Python source code |
| `README.md` | This file |

Each source file contains all four functions plus a main driver that runs four test cases per part.

## 3. Problem Statements

### Part A: Remove Duplicates from a Sorted Array

Given an integer array sorted in non-decreasing order, remove the duplicates **in-place** so that each distinct value appears only once.

- The first `k` positions must hold the unique values in their original order.
- The function returns `k`.
- An empty array returns `k = 0`. A single-element array returns `k = 1`.
- The reference version only counts the unique values. The optimized version also rearranges the array.

### Part B: Pair Sum in a Sorted Array

Given a sorted integer array and a target `T`, find two different indices `i < j` such that `arr[i] + arr[j] = T`.

- Return `(i, j)`, or `(-1, -1)` if no valid pair exists.
- The same element cannot be used twice. Equal values at different positions are allowed.
- If several pairs are valid, any one is accepted. Each algorithm returns the first pair its search order reaches.

## 4. Algorithms

### Part A, Approach 1: Separate Result List (`removeDuplicatesReference`)

1. Create an empty list `result`.
2. For each element `arr[i]`, scan `result`.
3. If the value is not found, append it.
4. Return the size of `result`.

This method does not use the sorted order, so every element scans the whole result list. The input array is not changed.

### Part A, Approach 2: Slow and Fast Pointers (`removeDuplicatesOptimized`)

1. If the array is empty, return 0.
2. Set `slow = 0`, which is the position of the last unique value.
3. Move `fast` from 1 to `n - 1`.
4. If `arr[fast] != arr[slow]`, a new value was found: do `slow++` and `arr[slow] = arr[fast]`.
5. If they are equal, it is a duplicate and only `fast` moves.
6. Return `slow + 1`.

Because the array is sorted, equal values are adjacent, so comparing with the last unique value is enough. Only `n`, `slow` and `fast` are used, and the array is overwritten in-place.

### Part B, Approach 1: Brute Force (`pairSumBruteForce`)

1. For each `i`, loop `j` from `i + 1` to the end.
2. If `arr[i] + arr[j] == target`, return `(i, j)`.
3. If no pair is found, return `(-1, -1)`.

Starting `j` at `i + 1` means an element is never paired with itself.

### Part B, Approach 2: Two Pointers (`pairSumOptimized`)

1. Set `left = 0` and `right = n - 1`.
2. While `left < right`, compute `sum = arr[left] + arr[right]`.
3. If `sum == target`, return `(left, right)`.
4. If `sum < target`, do `left++`. The right value is the largest remaining, so the left value is too small to pair with anything left.
5. If `sum > target`, do `right--`. The left value is the smallest remaining, so the right value is too large.
6. If the pointers meet, return `(-1, -1)`.

## 5. Dry Runs

### Remove Duplicates on `[1, 1, 2, 2, 2, 3, 4, 4]`

| Step | fast | slow (before) | arr[fast] | arr[slow] | Action | Array after |
|---|---|---|---|---|---|---|
| 1 | 1 | 0 | 1 | 1 | Equal, skip | `[1,1,2,2,2,3,4,4]` |
| 2 | 2 | 0 | 2 | 1 | Different: slow = 1, arr[1] = 2 | `[1,2,2,2,2,3,4,4]` |
| 3 | 3 | 1 | 2 | 2 | Equal, skip | `[1,2,2,2,2,3,4,4]` |
| 4 | 4 | 1 | 2 | 2 | Equal, skip | `[1,2,2,2,2,3,4,4]` |
| 5 | 5 | 1 | 3 | 2 | Different: slow = 2, arr[2] = 3 | `[1,2,3,2,2,3,4,4]` |
| 6 | 6 | 2 | 4 | 3 | Different: slow = 3, arr[3] = 4 | `[1,2,3,4,2,3,4,4]` |
| 7 | 7 | 3 | 4 | 4 | Equal, skip | `[1,2,3,4,2,3,4,4]` |

Result: `k = slow + 1 = 4` and the unique prefix is `[1, 2, 3, 4]`.

### Pair Sum on `[1, 2, 4, 7, 11, 15]`, target 15

| Iter | left | right | arr[left] | arr[right] | sum | Comparison | Movement |
|---|---|---|---|---|---|---|---|
| 1 | 0 | 5 | 1 | 15 | 16 | 16 > 15 | right-- (right = 4) |
| 2 | 0 | 4 | 1 | 11 | 12 | 12 < 15 | left++ (left = 1) |
| 3 | 1 | 4 | 2 | 11 | 13 | 13 < 15 | left++ (left = 2) |
| 4 | 2 | 4 | 4 | 11 | 15 | 15 = 15 | Match: return `(2, 4)` |

## 6. Compilation and Run Instructions

Run these commands from the repository folder.

| Language | Compile | Run |
|---|---|---|
| C++ | `g++ -std=c++17 TwoPointerAssignment.cpp -o main` | `./main` |
| Java | `javac TwoPointerAssignment.java` | `java TwoPointerAssignment` |
| Python | not required | `python3 TwoPointerAssignment.py` |

**Requirements:** a C++17 compiler (g++), JDK for Java (`javac` and `java`), and Python 3.

## 7. Program Output

All three programs print identical output:

```
PART A: REMOVE DUPLICATES

Test Case 1
Reference Unique Count: 4
Optimized Unique Count: 4
Unique Prefix: [1, 2, 3, 4]

Test Case 2
Reference Unique Count: 1
Optimized Unique Count: 1
Unique Prefix: [5]

Test Case 3
Reference Unique Count: 0
Optimized Unique Count: 0
Unique Prefix: []

Test Case 4
Reference Unique Count: 3
Optimized Unique Count: 3
Unique Prefix: [-3, -1, 0]

PART B: PAIR SUM

Test Case 1
Target: 15
Brute Force Indices: (2, 4)
Optimized Indices: (2, 4)

Test Case 2
Target: 7
Brute Force Indices: (-1, -1)
Optimized Indices: (-1, -1)

Test Case 3
Target: 20
Brute Force Indices: (-1, -1)
Optimized Indices: (-1, -1)

Test Case 4
Target: 4
Brute Force Indices: (0, 3)
Optimized Indices: (0, 3)
```

## 8. Test Cases

### Part A: Remove Duplicates

| Test | Type | Input | Expected | Actual | Status |
|---|---|---|---|---|---|
| 1 | Duplicate-heavy | `[1,1,2,2,2,3,4,4]` | k = 4, `[1,2,3,4]` | k = 4, `[1,2,3,4]` | PASS |
| 2 | Single element | `[5]` | k = 1, `[5]` | k = 1, `[5]` | PASS |
| 3 | Empty array | `[]` | k = 0, `[]` | k = 0, `[]` | PASS |
| 4 | Negatives and duplicates | `[-3,-3,-1,0,0]` | k = 3, `[-3,-1,0]` | k = 3, `[-3,-1,0]` | PASS |

### Part B: Pair Sum

| Test | Type | Input | Target | Expected | Actual | Status |
|---|---|---|---|---|---|---|
| 1 | Pair exists | `[1,2,4,7,11,15]` | 15 | `(2, 4)` | `(2, 4)` | PASS |
| 2 | No pair | `[1,3,5,8]` | 7 | `(-1, -1)` | `(-1, -1)` | PASS |
| 3 | No pair, target too large | `[2,4,6]` | 20 | `(-1, -1)` | `(-1, -1)` | PASS |
| 4 | Equal values | `[1,2,2,3]` | 4 | `(0, 3)` or `(1, 2)` | `(0, 3)` | PASS |

**Notes**
- Test 2: no pair in `[1,3,5,8]` sums to 7. The pair sums are 4, 6, 9, 8, 11 and 13.
- Test 4: both `(0, 3)` (1 + 3) and `(1, 2)` (2 + 2) sum to 4. Both algorithms return `(0, 3)` because it is reached first.

## 9. Complexity Analysis

n is the array length. Auxiliary space is the extra memory used apart from the input array.

| Problem | Approach | Time | Auxiliary space | Reason |
|---|---|---|---|---|
| Remove duplicates | Separate result list | O(n^2) | O(n) | Each of the n elements scans `result`, which can hold up to n values, giving n(n-1)/2 comparisons. `result` may store all n values. |
| Remove duplicates | Slow and fast pointers | O(n) | O(1) | One loop of n - 1 iterations with constant work each. Only `n`, `slow` and `fast` are used. |
| Pair sum | Brute force | O(n^2) | O(1) | Nested loops test n(n-1)/2 pairs. Only `i`, `j` and the result pair are stored. |
| Pair sum | Two pointers | O(n) | O(1) | Each iteration moves one pointer inward, so `right - left` shrinks from n - 1 to 0 in at most n - 1 iterations. Only `left`, `right` and `sum` are used. |

The complexity is the same in C++, Java and Python because it depends on the algorithm and not on the language.

The optimized versions rely on the array being sorted. Duplicates are adjacent (Part A), and moving a pointer changes the sum in a known direction (Part B). This improves time from O(n^2) to O(n), and for Remove Duplicates it also improves auxiliary space from O(n) to O(1).
