#poltnom
Authors: Danielle Zand Tehila Uzan
Monom 
The monom class represent monom from the form :
ax^b -> a represent the coefficient of the monom (can be double) while b represent the power of the monom (must be integer).
This class contain the following functions:
•	Add – function add monom to monom
•	Derivative – This function gives us Monom's derivative.
•	F(x) – function calculate the value of y in a given x
•	isZero – function that check if the monom is zero
•	multipy – function multiply monom 
•	is equal – check if two monoms are equals
•	equals – check if two monom is equals.
•	isMonomStringValid - check if the monom contains only valid charcter. 

polynom
polynom class represent polynom from the form:
ax^b+ax^b-ax^b -> the polynom contains from monom object.
This class contain the following functions:
•	add – two function: first add monom to polynom, second add polynom to polynom. 
•	Derivative – function derivative the Polynom by the derivative function of Monom.
•	f(x) - function calculate the value of y in a given x
•	isZero - function that check if the polynom  is zero
•	multiply - function multiply polymon in polynom
•	equals - check if two polynom are equals
•	removeSamePower – function check if there are 2 Monoms with the same power in this case she union between them.
•	substract –  function that substract polynom from polynom.
•	root – function that compute a value x between two given x. with two conditions.
•	copy – function that do a deep copy to new polynom 
•	area – This function compute Riemann's Integral over this Polynom starting from x0, till x1 using eps size steps.
•	isPolynomStringValid – check if the polynom contains only valid character. 
•	removeZZeroMonoms – function that in case thrae is zero monon is remove it from the polynom


