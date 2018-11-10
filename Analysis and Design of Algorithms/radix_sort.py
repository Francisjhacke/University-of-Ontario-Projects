import numpy as np

# Counting sort implementation from Tutorial #5
def countingSort(A):
    A = np.array(A, dtype=np.int)
    B = np.zeros_like(A, dtype=np.int)

    # initialize the counts
    k = A.max()
    counting_vector = np.zeros([k + 1], dtype=np.int)

    # count the occurrences of each value
    for num in A:
        counting_vector[num] += 1

    # determine the cumulative frequencies
    for i in range(1, k + 1):
        counting_vector[i] = counting_vector[i] + counting_vector[i - 1]

    # order the elements by their frequency
    for num in A[::-1]:
        B[counting_vector[num] - 1] = num
        counting_vector[num] -= 1

    return B
 
def get_digit(num, digit):
    working = num / 10**(digit - 1)
    return working % 10