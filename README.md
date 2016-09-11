The aim is to build a simple tautology verifier.

Steps to do it:
1. Parse the given boolean expression
2. Evaluate the expression for all possible values of boolean variables.

If all possible values result in “true” values, then the expression is a tautology


To parse the boolean expression atleast three methods are possible [3]. Either through Infix-Postfix method, or by constructing Abstract Syntax tree or writing a recursive descent parser for the expression.

Here the infix-postfix method is used as it is simpler than other methods. Specifically Shunting Yard Algorithm is used. [1]If other parsing methods are to be used the class structure of the present solution can be extended.



COMPLEXITY:
The complexity of the Shunting Yard algorithm is O(length of BooleanExpression)
However the tautology verifier is exponential on the number of variables.




COMPILE/RUN STEPS:

$ mvn clean package //compile

$ mvn test //can be used for running Junit tests

$ java -jar target/IndixProj-1.0-SNAPSHOT.jar IndixProb.TautologyTester

output:
!a & !b
false
(!a | (a & a))
true
(!a | (b & !a))
false
(!a | a)
true
((a & (!b | b)) | (!a & (!b | b)))
true



REFERENCES:
[1] https://en.wikipedia.org/wiki/Shunting-yard_algorithm
[2] http://www.geeksforgeeks.org/expression-evaluation/
[3] http://www.sunshine2k.de/coding/java/SimpleParser/SimpleParser.html