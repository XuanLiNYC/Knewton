Xuan Li  xuanlinyc@gmail.com

To run input fileName and threshold from command line
eg :	Artist_lists_small
	50

Output is in the file "ArtistPair.txt"


Below is the description of my program:

Get fileName and threshodl from command line.

1) read file by line and created artist pair. 

Pass fileName to method readFile. readFile will read a line from input, split them into String[] and sort the String[]*
Then pass the String[] to method getArtPair.

Time complexity mlg(m) for each line. m is the number of names in one line. Maximum 50 as predefined in the problem.

2) Save artist pair* into Hashmap and count appear times

getArtPair method find out and created all possible artist pairs with order. Artist pairs are saved into HashMap map for count.
Key in HashMap is Pair and value is time of counts.

time Complexity m*(m+1)/2 = m^2 for finding all possible artist pairs.

3) output artist pair which appears >= threshod.

Iterate the HashMap map and find out all Keys with value >= threshold. Print and save artist pairs into file ArtistPair.txt.

time complexity t. t is the number of pairs in Hashmap. t < m*n



The total time complexity is O(n*(mlg(m) + m^2) + t) = O(m*m*n) = O(n)
m is the number of names in one line. Maximum 50 as predefined in the problem.
n is the number of lines in the file.

The total space complexity is O(t) = O(n). t is the number of pairs in Hashmap. t < m*n

* artist pair is stored in a new class Pair(art A , art B).  Pair(A,B) != Pair(B,A)
* Sort the input String[] can garantee all Artist Pairs is stored in a sequence where art A < art B

