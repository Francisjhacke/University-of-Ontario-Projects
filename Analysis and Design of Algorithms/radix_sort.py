#Authors: Francis Hackenberger & Andre Dallaire

import numpy as np

# Radix sort of 6 digit numbers
def radix_sort(A):
    A = np.array(A, dtype=np.int)
    B = np.zeros_like(A, dtype=np.int)


    
    for d in range(1, 7):
        # initialize the counts
        counting_vector = np.zeros([10], dtype=np.int)

        # count the occurrences of each value
        for num in A:
            counting_vector[get_digit(num, d)] += 1
    
        # determine the cumulative frequencies
        for i in range(1, 10):
            counting_vector[i] = counting_vector[i] + counting_vector[i - 1]
    
        # order the elements by their frequency
        for num in A[::-1]:
            B[counting_vector[get_digit(num, d)] - 1] = num
            counting_vector[get_digit(num, d)] -= 1
            
        A = np.copy(B)

    return B
 
def get_digit(num, digit):
    working = num / 10**(digit - 1)
    return int(working % 10)

#test
test = [18, 200000, 2347, 934, 1, 1, 373, 372]
print(test)
print(radix_sort(test))