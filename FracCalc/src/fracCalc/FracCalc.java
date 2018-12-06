package fracCalc;

import java.util.*;

public class FracCalc {
    public static void main(String[] args) {
        
        // TODO: Read the input from the user and call produceAnswer with an equation
    	Scanner in = new Scanner (System.in); 
    	String input = "";   //initializes input so I can change it in the while loop
    	
        //reads input from user and prints out product of produceAnswer
    	while ( !(input.equalsIgnoreCase("quit")) ) {
    		System.out.print("Input expression or enter quit to exit program: ");
    		input = in.nextLine();
    		if ( !(input.equalsIgnoreCase("quit")) ) {
    			System.out.println(produceAnswer(input));
    		}
    	}
    	in.close();
    }
    
    
    // ** IMPORTANT ** DO NOT DELETE THIS FUNCTION.  This function will be used to test your code
    // This function takes a String 'input' and produces the result
    //
    // input is a fraction string that needs to be evaluated.  For your program, this will be the user input.
    //      e.g. input ==> "1/2 + 3/4"
    //        
    // The function should return the result of the fraction after it has been calculated
    //      e.g. return ==> "1_1/4"
    public static String produceAnswer(String input) { 
    	
    	/* Finds first space then puts everything before the first space into the firstOperand and deletes it from input, then 
    	 * removes the space. Does this for every operand and the operator.*/
        //first operand
    	int spaceLoc = input.indexOf(" ");
        String firstOperand = input.substring(0, spaceLoc);
        input = input.replaceFirst(firstOperand, "");
        firstOperand = firstOperand.replaceFirst(" ", "");
        
        //operator
        spaceLoc = input.indexOf(" ");
        String operator = input.substring(0, spaceLoc + 2);
        input = input.replaceFirst(operator, "");
        operator = operator.replaceFirst(" ", "");
        
        //second operand
        spaceLoc = input.indexOf(" ");
        String secondOperand = input.substring(spaceLoc, input.length());
        input = input.replaceFirst(firstOperand, "");
        secondOperand = secondOperand.replaceFirst(" ", "");
        
        
        //separates the operands into whole, numerator, denominator and turns into integers
        int intFirstOperand[] = separateOperand(firstOperand);
        int intSecondOperand[] = separateOperand(secondOperand);
        
        String answer = ""; //initializes variable
        
        if (operator.contains("*")) { //if operator is multiplication, multiply operands
        	answer = multiplication(intFirstOperand[0], intSecondOperand[0], intFirstOperand[1], intSecondOperand[1], intFirstOperand[2], intSecondOperand[2]);
        } else if (operator.contains("+")) { // Function to add two fractions together
        	answer = addition(intFirstOperand[0], intSecondOperand[0], intFirstOperand[1], intSecondOperand[1], intFirstOperand[2], intSecondOperand[2]);
        } else if (operator.contains("-")) {
        	answer = subtraction(intFirstOperand[0], intSecondOperand[0], intFirstOperand[1], intSecondOperand[1], intFirstOperand[2], intSecondOperand[2]);
        } else if (operator.contains("/")) {
        	if (intFirstOperand[0] == 0 || intSecondOperand[0] == 0) {
        		System.out.println("Error: Cannot divide by zero. Answer is undefined.");
        	}
        	else {
        		answer = division(intFirstOperand[0], intSecondOperand[0], intFirstOperand[1], intSecondOperand[1], intFirstOperand[2], intSecondOperand[2]);
        	}
        } else {
        	answer = "Please enter a valid expression."; //error catch
        }
        return answer;
  
    }
    
    
    /* Subtracts the second operand from the first operand */
    public static String subtraction(int whole1, int whole2, int numerator1, int numerator2, int denominator1, int denominator2) {
    	int improperNumeratorOne = improperNumerator(whole1, denominator1, numerator1);
    	int improperNumeratorTwo = improperNumerator(whole2, denominator2, numerator2);
    	
    	//initializes variables
    	int commonDenominator = 0;
    	int convertedNumeratorOne = 0;
    	int convertedNumeratorTwo = 0;
    	int subtractedNumerator = 0;
    	
    	//if the operands already have common denominators, certain steps should be omitted
    	if (denominator1 == denominator2) {
    		commonDenominator = denominator1;
    		subtractedNumerator = improperNumeratorOne - improperNumeratorTwo;
    	} else {
    		commonDenominator = denominator1 * denominator2;
    		
    		//when one side of an equation is multiplied by a value, the other side must be multiplied by the same value
    		convertedNumeratorOne = improperNumeratorOne * denominator2; 
    		convertedNumeratorTwo = improperNumeratorTwo * denominator1;
    	
    		subtractedNumerator = convertedNumeratorOne - convertedNumeratorTwo;
    	}
    	
    	return convertToMixedNumber(subtractedNumerator, commonDenominator);
    	
    }
    
    
    /* Adds the first and second operands together */
    public static String addition(int whole1, int whole2, int numerator1, int numerator2, int denominator1, int denominator2) {
    	int improperNumeratorOne = improperNumerator(whole1, denominator1, numerator1);
    	int improperNumeratorTwo = improperNumerator(whole2, denominator2, numerator2);
    	
    	//initializes variables
    	int commonDenominator = 0;
    	int convertedNumeratorOne = 0;
    	int convertedNumeratorTwo = 0;
    	int addedNumerator = 0;
    	
    	//if the operands already have common denominators, certain steps should be omitted
    	if (denominator1 == denominator2) {
    		commonDenominator = denominator1;
    		addedNumerator = improperNumeratorOne + improperNumeratorTwo;
    	} else {
    		commonDenominator = denominator1 * denominator2;
    		
    		//when one side of an equation is multiplied by a value, the other side must be multiplied by the same value
    		convertedNumeratorOne = improperNumeratorOne * denominator2; 
    		convertedNumeratorTwo = improperNumeratorTwo * denominator1;
    	
    		addedNumerator = convertedNumeratorOne + convertedNumeratorTwo;
    	}
    	
    	return convertToMixedNumber(addedNumerator, commonDenominator);
    }
    
    
    /* converts improper fractions into mixed numbers and simplifies them */
    public static String convertToMixedNumber(int numerator, int denominator) {  
    	int simplifiedNumerator = numerator;
    	int simplifiedDenominator = denominator;

    	int whole = ((simplifiedNumerator - (simplifiedNumerator % simplifiedDenominator)) / denominator);
    	int mixedNumerator = (numerator % denominator);
    	
    	simplifiedNumerator = mixedNumerator / GCF(mixedNumerator, denominator); // Simplifies numerator
    	simplifiedDenominator = denominator / GCF(mixedNumerator, denominator);
 
    	//if simplified fraction is a whole number, return only the whole number
    	if ((simplifiedNumerator == 0) & (simplifiedDenominator == 1)) { 
    		return whole + "";
    	} else {
    		if (whole < 0) { // if the whole number is negative, remove any other negative signs within the fraction
    			if (simplifiedNumerator < 0) {
    				simplifiedNumerator *= -1;
    			}
    			if (simplifiedDenominator < 0) {
    				simplifiedDenominator *= -1;
    			}
    		}
    		return whole + "_" + simplifiedNumerator + "/" + simplifiedDenominator;
    	}
    }
    
    /* finds the greatest common factor of the denominator and numerator */
   public static int GCF(int numerator, int denominator) {   
       if (numerator == 0) {
          return denominator;
          
        } else if (denominator == 0) {
          return numerator; 
          
        } else if (numerator == denominator) { 
            return numerator; 
          
        } else if (numerator > denominator) {
        	//these always need to be positive
            if (numerator < 0) {
            	numerator *= -1;
            }
            if (denominator < 0) {
            	denominator *= -1;
            }
            
	        while (numerator != denominator) {
	        	if (numerator > denominator) {
	        		numerator -= denominator;
	        	} else {
	        		denominator -= numerator;
	        	}
	        }
	        return numerator;

        } else if (numerator < denominator) {
        	//these always need to be positive
            if (numerator < 0) {
            	numerator *= -1;
            }
            if (denominator < 0) {
            	denominator *= -1;
            }
            
        	while (denominator != numerator) {
        		if (denominator > numerator) {
        			denominator -= numerator;
        		} else {
        			numerator -= denominator;
        		}
        	}
        	return denominator;
        } else {
        	return 1;
        }

   }
   
   
    /* separates the operand into whole number, numerator, and denominator and returns these values inside an array */
    public static int[] separateOperand(String operand) {
    	//initializes the variables
    	String operandHold = operand;
        String whole = "";
        String numerator = "";
        String denominator = "";
        int spaceLoc = 0;
        
        //determines if there is a whole number- if there is not, it is set to 0
        if (operandHold.contains("_")) {
        	//separates the whole number
        	spaceLoc = operandHold.indexOf("_");
        	whole = operandHold.substring(0, spaceLoc);
        	operandHold = operandHold.replaceFirst(whole, "");
    		
        	//separates the numerator
    		spaceLoc = operandHold.indexOf("/");
    		numerator = operandHold.substring(0, spaceLoc);
    		operandHold = operandHold.replaceFirst(numerator, "");
    		
    		//separates the denominator
    		operandHold = operandHold.replaceFirst("/", "");
    		denominator = operandHold;
    		
        } else {
        	whole = "0";
        	if (operandHold.contains("/")) { //if the value is a mixed number
        		
        		//separates the numerator
        		spaceLoc = operandHold.indexOf("/");
        		numerator = operandHold.substring(0, spaceLoc);
        		operandHold = operandHold.replaceFirst(numerator, "");
        		
        		//separates the denominator
        		operandHold = operandHold.replaceFirst("/", "");
        		denominator = operandHold;

        	} else {
        		
        		numerator = "0";
        		denominator = "1";
        		whole = operand;
        	}
        	
        }
        
        numerator = numerator.replaceFirst("/", "");
        numerator = numerator.replaceFirst("_", "");
        
        //changes the variables into integers that can be worked with
        int intWhole = operandIntoInt(whole);
        int intNumerator = operandIntoInt(numerator);
        int intDenominator = operandIntoInt(denominator);
        int[] separatedOperand = {intWhole, intNumerator, intDenominator}; /* sets the separated operand 
        into designated indexes of an array so as to return the whole operand with a single method */
        return separatedOperand;
    }
    
    
    /* changes the operand into an integer to do calculations with
     * --implemented in method separateOperator, lines 188-190-- */
    public static int operandIntoInt(String operand) { 
    	int intOperand = Integer.parseInt(operand);
    	return intOperand;
    }
    
    
    /* multiplies the first and second operands */
    public static String multiplication(int whole1, int whole2, int numerator1, int numerator2, int denominator1, int denominator2) {
    	//turns mixed fractions into improper fractions to work with
    	int firstImproperFractionNumerator = improperNumerator(whole1, denominator1, numerator1); 
        int secondImproperFractionNumerator = improperNumerator(whole2, denominator2, numerator2);
        	
        int improperMultipliedNumerator = firstImproperFractionNumerator * secondImproperFractionNumerator; // Multiples the numerator of the two improper fractions
        	
        int improperMultipliedDenominator = denominator1 * denominator2; // Multiples the two denominators
        	
        return convertToMixedNumber(improperMultipliedNumerator, improperMultipliedDenominator); // Converts the improper fraction into a mixed number and simplifies it.
    }
    
    
    /* divides first operand by the second using a strategy that switches the second operand's
     * numerator and denominator, turning the division into multiplication. This allows the use of the 
     * multiplication method */
    public static String division(int whole1, int whole2, int numerator1, int numerator2, int denominator1, int denominator2) {
    	//turns second operand into an improper fraction to be worked with--must be improper before switching denom. and numer. 
    	int improperNumerator1 = improperNumerator(whole2, denominator2, numerator2);
    	int newWhole2 = 0; //operand is now an improper fraction, so whole must be changed to 0
        
    	//switches the second operator's numerator and denominator
    	int newNumerator2 = denominator2;
    	int newDenominator2 = improperNumerator1;
    	//finishes the calculation with the multiplication method
    	return multiplication(whole1, newWhole2, numerator1, newNumerator2, denominator1, newDenominator2);
    }
    
    /* turns mixed fractions into improper fractions to work with */
    public static int improperNumerator(int whole, int denominator, int numerator) {
    	if (whole == 0) {
    		return numerator;
    		
    	} else if ((whole < 0) || (denominator < 0) || (numerator < 0)) {
    		return (whole * denominator) - numerator;
    		
    	} else {
    		return (whole * denominator) + numerator;
    		
    	}
    }
    
    
}   
