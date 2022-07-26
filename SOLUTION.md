Zooplus Coding Exercise - Anagrams
==================================

How to run
------------
```
mvn clean package
java -cp target/recruiting-javier-pinon-1.0.0.jar com.jpinon.challenge.recruiting_javier_pinon.infrastructure.runner.Main <pathToDirectoryWithTextFiles>
```

Execution example
------------
```
matchFor>
matchFor> Astronomer
Longest anagram is... ‘Moon starer’
matchFor> Statue of Liberty
Longest anagram is...’Built to stay free’
matchFor> We
Longest anagram is... not found here
```

Assumptions
------------
* Shortest anagram solution is 3
* Dictionary file words can't contain whitespaces
* Dictionary file words will have only a word each line



Known issues
-------------
* From 12 characters upwards, execution time can be extensive if an anagram is not found on early iterations 
