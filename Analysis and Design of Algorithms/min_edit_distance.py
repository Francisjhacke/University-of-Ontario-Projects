def min_edit_distance(first_string, second_string):
    m = len(first_string)
    n = len(second_string)
    D = [[0 for i in range(n + 1)] 
            for i in range(m + 1)]

    for i in range(m + 1):
        for j in range(n + 1):
            if i == 0:
                D[i][j] = j
            elif j == 0:
                D[i][j] = i
            elif first_string[i - 1] == second_string[j - 1]:
                D[i][j] = D[i - 1][j - 1]
            else:
                D[i][j] = 1 + min(D[i][j - 1],      # Insert
                                  D[i - 1][j],      # Remove
                                  D[i - 1][j - 1])  # Replace
    return D[m][n]

# Sample inputs taken from Stanford NLP group on Minimum Edit Distance (MED)
first_string = "intention"
second_string = "execution"

print(min_edit_distance(first_string, second_string))