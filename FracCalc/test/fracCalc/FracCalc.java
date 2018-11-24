package fracCalc;

import java.util.*;

public class FracCalc {
public static boolean firstOperandIsFraction;
public static boolean secondOperandIsFraction;
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
    }
    
    // ** IMPORTANT ** DO NOT DELETE THIS FUNCTION.  This function will be used to test your code
    // This function takes a String 'input' and produces the result
    //
    // input is a fraction string that needs to be evaluated.  For your program, this will be the user input.
    //      e.g. input ==> "1/2 + 3/4"
    //        
    // The function should return the result of the fraction after it has been calculated
    //      e.g. return ==> "1_1/4"
    public static String produceAnswer(String input)
    { 
    	/* Finds first space then puts everything before the first space into the firstOperand and deletes it from input, then 
    	 * removes the space. Does this for every operand and the operator.*/
        int spaceLoc = input.indexOf(" ");
        String firstOperand = input.substring(0, spaceLoc);
        input = input.replaceFirst(firstOperand, "");
        firstOperand = firstOperand.replaceFirst(" ", "");
        
        spaceLoc = input.indexOf(" ");
        String operator = input.substring(0, spaceLoc + 2);
        input = input.replaceFirst(operator, "");
        operator = operator.replaceFirst(" ", "");
        
        spaceLoc = input.indexOf(" ");
        String secondOperand = input.substring(spaceLoc, input.length());
        input = input.replaceFirst(firstOperand, "");
        secondOperand = secondOperand.replaceFirst(" ", "");
        
        int intFirstOperand[] = separateOperand(firstOperand);
        int intSecondOperand[] = separateOperand(secondOperand);
        String answer = "";
        if (operator.contains("*")) {
        	
        	System.out.println("Whole: " + intFirstOperand[0] + intFirstOperand[2] + intFirstOperand[1]);
        	
        	int firstImproperFractionNumerator = (intFirstOperand[0] * intFirstOperand[2] /* First denominator */) + intFirstOperand[1] /* First numerator */; // This is the numerator of the first fraction as an improper fraction
        	int secondImproperFractionNumerator = (intSecondOperand[0] * intSecondOperand[2]) + intSecondOperand[1]; // This is the numerator of the second fraction as an improper fraction
        	
        	int improperMultipliedNumerator = firstImproperFractionNumerator * secondImproperFractionNumerator; // Multiples the numerator of the two improper fractions
        	
        	int improperMultipliedDenominator = intFirstOperand[2] * intSecondOperand[2]; // Multiples the two denominators
        	
        	answer = convertToMixedNumber(improperMultipliedNumerator, improperMultipliedDenominator); // Converts the improper fraction into a mixed number and simplifies it.
        		
        }
        
        return answer;
  
    }
    public static String convertToMixedNumber(int numerator, int denominator) { // converts improper fractions into mixed numbers and simplifies them
    	int simplifiedNumerator = numerator;
    	int simplifiedDenominator = denominator;

    	int whole = ((simplifiedNumerator - (simplifiedNumerator % simplifiedDenominator)) / denominator);
    	int mixedNumerator = (numerator % denominator);
    	
    	simplifiedNumerator = mixedNumerator / GCF(mixedNumerator, denominator); // Simplifies numerator
    	simplifiedDenominator = denominator / GCF(mixedNumerator, denominator);
 
    	
    	return whole + " " + simplifiedNumerator + "/" + simplifiedDenominator;
    }
   public static int GCF(int numerator, int denominator) // Finds the greatest common factor of the denominator and numerator
    {   
        if (numerator == 0) {
          return denominator;
        }
        if (denominator == 0) {
          return numerator; 
        }
        
        if (numerator == denominator) { 
            return numerator; 
        }
         
        if (numerator > denominator) { 
            return GCF(numerator - denominator, denominator); // if the numerator is greater than the denominator, loops the method and returns that.
        }
        return GCF(numerator, denominator - numerator); // Returns greatest common factor outside if statement
    } 
    
    public static int[] separateOperand(String operand) {
    	String operandHold = operand;
        String whole = "";
        String numerator = "";
        String denominator = "";
        int spaceLoc = 0;
        if (operandHold.contains("_")) {
        	spaceLoc = operandHold.indexOf("_");
        	whole = operandHold.substring(0, spaceLoc);
        	operandHold = operandHold.replaceFirst(whole, "");
    		
    		spaceLoc = operandHold.indexOf("/");
    		numerator = operandHold.substring(0, spaceLoc);
    		operandHold = operandHold.replaceFirst(numerator, "");
    		
    		
    		operandHold = operandHold.replaceFirst("/", "");
    		denominator = operandHold;
    		
        } else {
        	whole = "0";
        	if (operandHold.contains("/")) {
        		
        		spaceLoc = operandHold.indexOf("/");
        		numerator = operandHold.substring(0, spaceLoc);
        		operandHold = operandHold.replaceFirst(numerator, "");
        		
        		
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
        int intWhole = operandIntoInt(whole);
        int intNumerator = operandIntoInt(numerator);
        int intDenominator = operandIntoInt(denominator);
        int[] separatedOperand = {intWhole, intNumerator, intDenominator};
        return separatedOperand;
    }
    
    
    public static int operandIntoInt(String operand) {
    	int intOperand = Integer.parseInt(operand);
    	return intOperand;
    }
}
