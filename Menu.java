import java.util.*;

class Menu {
     private static Scanner keyboard = new Scanner(System.in);

     //============ MAIN METHOD ==============
     /**
      * Loops through the menu of choices and allows the user to generate and analyze as many
      * passwords as they want. Also includes an option to exit the program.
      *
      * @param args    stores arguments passed by the command line
      */
     public static void main(String [] args){

         System.out.println("Welcome to our password program. ");
         System.out.println();
         
         int choice = 0;
         boolean valid = false;
         
         while(choice!=3) {
            System.out.println("1. Generate a password ");
            System.out.println("2. Analyze a password ");
            System.out.println("3. Exit");
            while(!valid) {
               try {
                  System.out.print("Select an option: ");
                  choice = keyboard.nextInt();
                  valid = true;
               } catch(InputMismatchException ex) {
                  System.out.println("Invalid input. Please enter a 1, 2, or 3.");
                  keyboard.next();
               }
            } // end of while loop
            switch(choice) {
               case 1:
                  valid = false;
                  generate();
                  break;
               case 2:
                  valid = false;
                  analyze();
                  break;
               case 3:
                  break;
               default:
                  System.out.println("Invalid input. Please enter a 1, 2, or 3.");
                  valid = false;
            } // end of switch statement
            System.out.println();
         } // end of while loop     

         System.out.println("Thank you for using our program.");

     } // end of main method
     
     //=========== GENERATE METHOD ===============
     /**
      * Uses methods from the Generator class to create a new password for the user.
      */
     public static void generate() {
         
         String purpose,value;
         
         System.out.println();
         System.out.print("What is the purpose for this password? ");
         purpose = keyboard.nextLine();
         purpose = keyboard.nextLine();
         
         value = setParams();
         value = Generator.shuffle(value);
         
         Password pw = new Password(value);
         System.out.println();
         System.out.println("Your new password for "+purpose+" is "+pw.getValue());
         
     } // end of generate method

     //============ ANALAYE METHOD =============
     /**
      * Uses methods from the Analyzer class to evaluate a password inputted by the user.
      */
     public static void analyze() {
         
         String value,checkPin;
         boolean isPin=false, valid=false;
         
         System.out.println();
         System.out.print("Enter your password: ");
         value = keyboard.next();
         
         while(!valid) {
            System.out.print("Is your password a PIN? (y/n): ");
            checkPin = keyboard.next();
            if(checkPin.toLowerCase().equals("y")){
               isPin = true;
               valid = true;
            } else if(checkPin.toLowerCase().equals("n")){
               isPin = false;
               valid = true;
            } else {
               System.out.println("Invalid input. Please enter a y for yes or n for no.");
            }
         } // end of while loop
         
         Password pw = new Password(value,isPin);
         System.out.println();
         try{
            System.out.println("Your score: "+pw.getStrengthScore());
         } catch(NumberFormatException ex) {
            System.out.println("PINs can only contain numerical characters.");
            analyze();
         } catch(ArrayIndexOutOfBoundsException ex) {
            System.out.println("PINs can only be up to 4 digits in length.");
            analyze();
         }
         
     } // end of analyze method

     //============ SETPARAMS METHOD =================
     /**
      * Takes user input to customize the password being generated.
      *
      * @return The characters to be used in the password.
      */
     public static String setParams() {
          
         int passLength=0, minCaps=0, minLows=0, minNums=0, minSpecs=0, leftoverLength=0;
         String output;
         boolean valid = false;
          
         System.out.println();
          
         while(!valid){
            try {
               System.out.print("Enter a total length for your password: ");
               passLength = keyboard.nextInt();
               if(!(passLength > 0)) {
                  throw new Exception("Invalid input: please enter a number greater than 0.");
               }
               valid = true;
            } catch(InputMismatchException ex) {
               System.out.println("Invalid input: please enter an integer.");
               keyboard.next();
            } catch(Exception ex) {
               System.out.println(ex.getMessage());
            }
         } // end of while loop
         valid = false;
         
         while(!valid){
            try {
               System.out.print("Enter a minimum number of capital letters: ");
               minCaps = keyboard.nextInt();
               if(!(minCaps >= 0)) {
                  throw new Exception("Invalid input: please enter a number greater than or equal to 0.");
               }
               valid = true;
            } catch(InputMismatchException ex) {
               System.out.println("Invalid input: please enter an integer.");
               keyboard.next();
            } catch(Exception ex) {
               System.out.println(ex.getMessage());
            }
         } // end of while loop
         valid = false;
         
         while(!valid){
            try {
               System.out.print("Enter a minimum number of lowercase letters: ");
               minLows = keyboard.nextInt();
               if(!(minLows >= 0)) {
                  throw new Exception("Invalid input: please enter a number greater than or equal to 0.");
               }
               valid = true;
            } catch(InputMismatchException ex) {
               System.out.println("Invalid input: please enter an integer.");
               keyboard.next();
            } catch(Exception ex) {
               System.out.println(ex.getMessage());
            }
         } // end of while loop
         valid = false;
         
         while(!valid){
            try {
               System.out.print("Enter a minimum number of numerical characters: ");
               minNums = keyboard.nextInt();
               if(!(minNums >= 0)) {
                  throw new Exception("Invalid input: please enter a number greater than or equal to 0.");
               }
               valid = true;
            } catch(InputMismatchException ex) {
               System.out.println("Invalid input: please enter an integer.");
               keyboard.next();
            } catch(Exception ex) {
               System.out.println(ex.getMessage());
            }
         } // end of while loop
         valid = false;

         while(!valid){
            try {
               System.out.print("Enter a minimum number of special characters: ");
               minLows = keyboard.nextInt();
               if(!(minLows >= 0)) {
                  throw new Exception("Invalid input: please enter a number greater than or equal to 0.");
               }
               valid = true;
            } catch(InputMismatchException ex) {
               System.out.println("Invalid input: please enter an integer.");
               keyboard.next();
            } catch(Exception ex) {
               System.out.println(ex.getMessage());
            }
         } // end of while loop
         valid = false;
 
         leftoverLength = passLength - (minCaps + minLows + minNums + minSpecs);
         if(leftoverLength < 0) {
            System.out.println("ERROR: Your minimum specifications exceed the total length of your password.");
            output = setParams();
         } else {
            output = Generator.createCapLetters(minCaps) + Generator.createLowLetters(minLows) + Generator.createNumbers(minNums) + Generator.createSpecialChars(minSpecs) + Generator.createLeftovers(leftoverLength);
         } // end of if statement
         
         return output;
         
     } // end of setParams method
    
} // end of Menu class

