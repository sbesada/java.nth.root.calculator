# java nth root calculator

BigInteger &amp; BigDecimal nth Root Calculator

see in the test how to use it!

This algorithm was tested with numbers up to 20000 digits but it can work with bigger numbers.

The algorithm result, can be a BigInteger if the result is an exact integer or a BigDecimal.

Example:
integer: 12345678901234567890 ^ 1000

root result : 12345678901234567890

nth_root:1000

number of the digits:19091
 
calculation time:284 ms on my i7 6500U laptop.

Algorithm:

 1. I make a fast root approximation using logarithms: root = antilog(log(number)/n)
 
 2. I use the approximation in numerical methods to figure out the real root:
 
    -Example numerical methods: Nth root order 3 --> Xk+1=Xk*[((n+1)*r + (n-1)Xk^n)/(n-1)*r + (n+1)*xk^n]
    
Enjoy it!!

If you think that my work deserves a donation, you can do it: https://sbesada.github.io/


