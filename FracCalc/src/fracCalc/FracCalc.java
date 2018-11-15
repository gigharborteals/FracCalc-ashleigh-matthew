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
    		System.out.println(produceAnswer(input));
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
        int SpaceLoc = input.indexOf(" ");
        String firstOperand = input.substring(0, SpaceLoc);
        input = input.replaceFirst(firstOperand, "");
        firstOperand = firstOperand.replaceFirst(" ", "");
        
        SpaceLoc = input.indexOf(" ");
        String Operator = input.substring(0, SpaceLoc + 2);
        input = input.replaceFirst(Operator, "");
        Operator = Operator.replaceFirst(" ", "");
        
        SpaceLoc = input.indexOf(" ");
        String secondOperand = input.substring(SpaceLoc, input.length());
        input = input.replaceFirst(firstOperand, "");
        secondOperand = secondOperand.replaceFirst(" ", "");

        
        return secondOperand;
        
    }

    // TODO: Fill in the space below with any helper methods that you think you will need
    
}
