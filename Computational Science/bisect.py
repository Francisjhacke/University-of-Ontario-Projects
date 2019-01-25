import numpy as np

def F(x,a):
    return x^x - a

def bisect(F, a, left, right, k= 10, epsx = 10**-4, epsF=10**-4):
    results = np.zeros((k, 3))
    for i in range(k):
        mid = 1.0  / 2.0 * (left + right)
        results[i] = i, right - left, abs(F(mid, a))
        if F(left, a) * F(mid, a) > 0:
            left = mid
        else:
            right = mid
        if (right - left < epsx) and (abs(F(mid, a)) < epsF):
            return mid, results[:i]
        print("Iterations did not converge")
        return mid, results