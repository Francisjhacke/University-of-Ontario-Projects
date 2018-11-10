import heapq

def huffman_coding(frequency):
    heap = [[freq, [char, '']] for char, freq in frequency.items()]
    heapq.heapify(heap)

    while len(heap) > 1:
        left = heapq.heappop(heap)
        right = heapq.heappop(heap)
        for node in left[1:]:
            node[1] = '0' + node[1]
        for node in right[1:]:
            node[1] = '1' + node[1]
        heapq.heappush(heap, [left[0] + right[0]] + left[1:] + right[1:])

    return sorted(heapq.heappop(heap)[1:], key=lambda prefix: (len(prefix[-1]), prefix))

# Test the algorithm
string = ""
fileName = input("Enter a filename: ")
with open(fileName, 'r') as textFile:
    string = textFile.read().replace('\n', '')

# Get frequencies of each character
freq = {}
for character in string:
    if character in freq:
        freq[character] += 1
    else:
        freq[character] = 1

prefixCodes = huffman_coding(freq)
print("Char".ljust(5) + "Freq".ljust(5) + "Prefix Code")
for prefix in prefixCodes:
    print(prefix[0].ljust(5) + str(freq[prefix[0]]).ljust(5) + prefix[1])

print("Original length: ", len(string))
print("Encoded length: ", len(prefixCodes))
