
# PART A: Remove Duplicates
def removeDuplicatesReference(arr):
    result = []

    for i in range(len(arr)):
        found = False

        for j in range(len(result)):
            if arr[i] == result[j]:
                found = True
                break

        if not found:
            result.append(arr[i])

    return len(result)


# PART A: Remove Duplicates - Optimized
def removeDuplicatesOptimized(arr):
    n = len(arr)

    if n == 0:
        return 0

    slow = 0

    for fast in range(1, n):
        if arr[fast] != arr[slow]:
            slow += 1
            arr[slow] = arr[fast]

    return slow + 1


# PART B: Pair Sum
def pairSumBruteForce(arr, target):
    n = len(arr)

    for i in range(n):
        for j in range(i + 1, n):
            if arr[i] + arr[j] == target:
                return (i, j)

    return (-1, -1)


# PART B: Pair Sum - Optimized
def pairSumOptimized(arr, target):
    if len(arr) < 2:
        return (-1, -1)

    left = 0
    right = len(arr) - 1

    while left < right:
        total = arr[left] + arr[right]

        if total == target:
            return (left, right)

        elif total < target:
            left += 1

        else:
            right -= 1

    return (-1, -1)


# Function to Display Array
def displayArray(arr, k):
    print(arr[:k])


# MAIN FUNCTION
if __name__ == "__main__":

    # PART A: REMOVE DUPLICATES

    print("PART A: REMOVE DUPLICATES")

    duplicateTests = [
        [1, 1, 2, 2, 2, 3, 4, 4],
        [5],
        [],
        [-3, -3, -1, 0, 0]
    ]

    for i in range(len(duplicateTests)):
        arr = duplicateTests[i].copy()

        referenceK = removeDuplicatesReference(arr)
        k = removeDuplicatesOptimized(arr)

        print("\nTest Case", i + 1)
        print("Reference Unique Count:", referenceK)
        print("Optimized Unique Count:", k)
        print("Unique Prefix:", end=" ")
        displayArray(arr, k)

    # PART B: PAIR SUM

    print("\nPART B: PAIR SUM")

    pairTests = [
        [1, 2, 4, 7, 11, 15],
        [1, 3, 5, 8],
        [2, 4, 6],
        [1, 2, 2, 3]
    ]

    targets = [15, 7, 20, 4]

    for i in range(len(pairTests)):
        arr = pairTests[i]
        target = targets[i]

        brute = pairSumBruteForce(arr, target)
        optimized = pairSumOptimized(arr, target)

        print("\nTest Case", i + 1)
        print("Target:", target)
        print("Brute Force Indices:", brute)
        print("Optimized Indices:", optimized)
