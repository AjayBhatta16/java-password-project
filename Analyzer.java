import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
/**
 * Analyzer class evaluates the strength of a Password object.
 * Depends on Password
 * @author Kael Guimento-Hopenwasser
 */
public class Analyzer {

    //============ GETSCORE METHOD ================
    /**
     * Static method, Only interface method for the Analyzer class.
     * Only Pins of 4 digits is supported
     * @param pass A Password object
     * @return int from 0 - 100 representing percentage of strength.
     */
    public static int getScore(Password pass){
        if (pass.isPin()) {
            if (pass.getValue().length() > 4) {
                System.out.println("Pins greater than 4 digits is not supported");
            }
            return scorePin(pass);
        } else {
            if (isInTopList(pass)) {
                return 0;
            }
            return evalPass(pass);
        } // end of if statement
    } // end of getScore method

    //=========== EVALPASS METHOD ================
    /**
     * Analyzes the strength of an alphanumeric password by 
     * placing very heavy emphisis on using diffrent types of characters,
     * placing heavy emphisis on length, and taking in consideration
     * the placement of characters next to each other in a linear equation.
     * @param pass with isPin == false
     * @return int from 0 - 100 representing percentage of strength.
     */
    private static int evalPass(Password pass) {
        // init vars
        String value = pass.getValue();
        int length = pass.getLength();
        int caps = pass.totalCapLetters();
        int lows = pass.totalLowLetters();
        int nums = pass.totalNumbers();
        int syms = pass.totalSpecialChars();
        int variance = 0;
        int distribution = 1;
        int bias = 0;

        // calc distribution / is whats next to char the same type?
        for (int i = 0; i < length-1; i++) {
            char a = value.charAt(i);
            char b = value.charAt(i+1);
            boolean isBothUppercase = Character.isUpperCase(a)&&Character.isUpperCase(b);
            boolean isBothLowercase = Character.isLowerCase(a)&&Character.isLowerCase(b);
            boolean isBothNumber = Character.isDigit(a)&&Character.isDigit(b);
            boolean isNotNextCharSame = !(isBothUppercase || isBothNumber || isBothLowercase);
            if (isNotNextCharSame) {
                distribution++;
            }
        } // end of for loop

        // calc variance / how many types of char is there
        if (caps>0) {
            variance++;
        }
        if (lows>0) {
            variance++;
        }
        if (nums>0) {
            variance++;
        }
        if (syms>0) {
            variance++;
        }
        
        // mx+b line / m = heavy variance / x = length / b = distribution scaled by variance + a bias
        int score = 2*variance * length + variance * distribution + bias;
        // max at 100%
        if (score>100) {
            return 100;
        }
        return score;
    } // end of evalPass method

    //============== ISINTOPLIST METHOD =============
    /**
     * Checks if the provided Password is in a file with the top most used passwords.
     * @param pass Password object
     * @return boolean
     */
    private static boolean isInTopList(Password pass) {
        Path filePath = new File("10-million-password-list-top-1000000.txt").toPath(); 
        String[] stringArray = fileLinesToArray(filePath);

        // compare provided Password with line
        for (String string : stringArray) {
            if (pass.getValue().equals(string)) {
                return true;
            }
        } // end of for loop
        return false;    
    } // end of isInTopList method

    //============ SCOREPIN METHOD ================
    /**
     * Scores a pin type password object by using a freqency disribution list
     * of all 4-digit pins created by Daniel Miessler. 
     * Repo: https://github.com/danielmiessler/SecLists/tree/master/Passwords/Common-Credentials
     * @param pass with isPin == true
     * @return int from 0 - 100 representing percentage of strength.
     */
    private static int scorePin(Password pass){
        int pin = Integer.parseInt(pass.getValue());
        int freqency = 0;
        Path filePath = new File("four-digit-pin-codes-justcount.csv").toPath();
        String[] stringArray = fileLinesToArray(filePath);
        freqency = Integer.parseInt(stringArray[pin]);

        // return an int between 0-100, and shift value up
        int score = (int) (120*(1 - freqency/255.0));
        if (score>100) {
            return 100;
        }
        return score;
    } // end of scorePin method

    //============ FILELINESTOARRAY METHOD ==============
    /**
     * Reads a file provided by a Path object and creates and
     * array split on each line
     * @param filePath Path object
     * @return String Array with each line is index or faliure returns an array of length 1
     */
    private static String[] fileLinesToArray(Path filePath) {
        try {
            List<String> stringList = Files.readAllLines(filePath);
            return stringList.toArray(new String[]{});
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Error Reading in the file.");
        }
        return new String[1];
    } // end of fileLinesToArray method
} // end of Analyzer class