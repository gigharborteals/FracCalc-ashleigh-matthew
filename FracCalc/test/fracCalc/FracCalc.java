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
        String Operator = input.substring(0, spaceLoc + 2);
        input = input.replaceFirst(Operator, "");
        Operator = Operator.replaceFirst(" ", "");
        
        spaceLoc = input.indexOf(" ");
        String secondOperand = input.substring(spaceLoc, input.length());
        input = input.replaceFirst(firstOperand, "");
        secondOperand = secondOperand.replaceFirst(" ", "");

         
        String secondOperandHold = secondOperand;
        String whole2 = "";
        String numerator2 = "";
        String denominator2 = "";
        if (secondOperandHold.contains("_")) {
        	spaceLoc = secondOperandHold.indexOf("_");
        	whole2 = secondOperandHold.substring(0, spaceLoc);
        	secondOperandHold = secondOperandHold.replaceFirst(whole2, "");
    		
    		spaceLoc = secondOperandHold.indexOf("/");
    		numerator2 = secondOperandHold.substring(0, spaceLoc);
    		secondOperandHold = secondOperandHold.replaceFirst(numerator2, "");
    		
    		secondOperandHold = secondOperandHold.replaceFirst("/", "");
    		denominator2 = secondOperandHold;
    		
        }
        else {
        	whole2 = "0";
        	if (secondOperandHold.contains("/")) {
        		
        		spaceLoc = secondOperandHold.indexOf("/");
        		numerator2 = secondOperandHold.substring(0, spaceLoc);
        		secondOperandHold = secondOperandHold.replaceFirst(numerator2, "");
        		
        		
        		secondOperandHold = secondOperandHold.replaceFirst("/", "");
        		denominator2 = secondOperandHold;

        	}
        	else {
        		
        		numerator2 = "0";
        		denominator2 = "1";
        		whole2 = secondOperand;
        	}
        	
        	
        }
        String firstOperandHold = firstOperand;
        String whole1 = "";
        String numerator1 = "";
        String denominator1 = "";
        if (firstOperandHold.contains("_")) {
        	spaceLoc = firstOperandHold.indexOf("_");
        	whole1 = firstOperandHold.substring(0, spaceLoc);
        	firstOperandHold = firstOperandHold.replaceFirst(whole1, "");
    		
    		spaceLoc = firstOperandHold.indexOf("/");
    		numerator1 = firstOperandHold.substring(0, spaceLoc);
    		firstOperandHold = firstOperandHold.replaceFirst(numerator1, "");
    		
    		firstOperandHold = firstOperandHold.replaceFirst("/", "");
    		denominator1 = firstOperandHold;
    		
        }
        else {
        	whole1 = "0";
        	if (firstOperandHold.contains("/")) {
        		
        		spaceLoc = firstOperandHold.indexOf("/");
        		numerator1 = firstOperandHold.substring(0, spaceLoc);
        		firstOperandHold = firstOperandHold.replaceFirst(numerator1, "");
        		
        		
        		firstOperandHold = firstOperandHold.replaceFirst("/", "");
        		denominator1 = firstOperandHold;

        	}
        	else {
        		
        		numerator1 = "0";
        		denominator1 = "1";
        		whole1 = secondOperand;
        	}
        	
        	
        }
        
        
        
        
       // if (secondOperandHold.contains("/"))
        numerator2 = numerator2.replaceFirst("/", "");
        numerator2 = numerator2.replaceFirst("_", "");
        numerator1 = numerator1.replaceFirst("/", "");
        numerator1 = numerator1.replaceFirst("_", "");
        
        
        
        
        
        
        
        String EndResult = ("whole:" + whole1 + " numerator:" + numerator1 + " denominator:" + denominator1);
        return EndResult;
        
        
    }

    // TODO: Fill in the space below with any helper methods that you think you will need
    
}
