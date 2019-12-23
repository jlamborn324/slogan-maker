# slogan-maker
My favorite class project adapted to be much more. Using data files of social media posts, I have created a program that provides a semi-realistic slogan from a given acronym. An external tokenizer class is being used to seperate each tweet into a collection of tokens. These tokens are used to create a list of [bigrams](https://en.wikipedia.org/wiki/Bigram), which are in turn used to construct slogans based on a given acronym that somewhat resemble real speech. 

## Next Steps

While this code runs, there are improvements that I envision. 

### 1. Efficiency

As I continue to learn more about sorting algorithms, data structures, and time complexities, I will use this project as an opportunity to apply my knowledge and constantly improve the efficiency of this software.

### 2. More Realistic Slogans

At its current stage, the slogans that are produced don't always make the most sense. Sometimes, things slip in that aren't words, like links and such. Adding some way for the slogan-maker to check words against an english dictionary would make slogans more realistic. 

### 3. Scale Up

Currently, the slogan generator takes a long-long time to parse the 30,000 tweets its using. I hope to make this more efficient, and I am researching the best way how. 

### 4. GUI Problems

The GUI freezes up when the code is doing heavy tasks, like the parsing. Implementing multi-threading would keep the GUI and heavy tasks seperate, and provide a smoother user experience. 

