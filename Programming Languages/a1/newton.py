# Assignment 1 - Part 1 - Francis Hackenberger
a = 1.0
b = 0.0
c = -1.0
d = 1.0

def f(x):
    return a*x**3 + b*x**2 + c*x + d

def df(x):
    return 3*a*x**2 + 2*b*x + c

def newton_solver(x0, iter_n):
    xn = x0
    i = 0
    while i < iter_n:
        xn = xn - (f(xn) / df(xn))
        i += 1
    return xn
	
#test the implementation
x = newton_solver(x0=0, iter_n=20)
y = a*x*x*x + b*x*x + c*x + d
print("x = %.8f, y = %.8f", x, y)