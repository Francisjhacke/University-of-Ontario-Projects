def fractional_knapsack(capacity, values, weights):
    """Given a number of items with weight and value, 
    select a percentage (0.0-1.0) for each item(s) to 
    include in your knapsack such that the weight capacity 
    (W) of the knapsack is not exceeded, and the total 
    value (V, the sum of the value of all included items) 
    is maximal.
    """

    index = list(range(len(values)))
    ratio = [v/w for v, w in zip(values, weights)]   # get value to weight ratios
    index.sort(key=lambda i: ratio[i], reverse=True) # sort value to weight ratio in decreasing order
 
    total = 0
    percentages = [0] * len(values)
    
    for i in index:
        if weights[i] <= capacity:
            total += values[i]
            capacity -= weights[i]
            percentages[i] = 1
        else:
            total += values[i] * capacity/weights[i]
            percentages[i] = capacity/weights[i]
            break
    return total, percentages
 
# Sample inputs taken from lecture slides (Lecture 06, slide 28)
capacity = 11
values = [14, 9, 7.5, 1]
weights = [7, 6, 5, 1]
 
print("Capacity:", capacity)
print("Values:", values)
print("Weights:", weights)

total, percentages = fractional_knapsack(capacity, values, weights)

print("Maximal total value:", total)
print("Percentages for each item:", percentages)