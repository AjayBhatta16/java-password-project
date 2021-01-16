import java.util.Random;
/** 
 * Generator class creates a new Password object with user specifications.
 * 
 * @author Ajay Bhattacharyya
 */
public class Generator {

    /** Contains all of the capital letter characters */
    static final String CAP_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    /** Contains all of the lowercase letter characters */
    static final String LOW_LETTERS = "abcdefghijklmnopqrstuvwxyz";
    /** Contains all of the numerical characters */
    static final String NUMBERS = "0123456789";
    /** Contains all of the miscalleneous characters (@,$,etc.) */
    static final String SPECIAL_CHARS = "~!@#$%^&*()_+-=[]|;\'\"\\:,.<>/?";
    static Random rand = new Random();
    
    //========== CREATECAPLETTERS METHOD ============
    /** 
     * Creates a string of random capital letters.
     * 
     * @param size     The length of the generated string.
     *
     * @return A string of random capital letters with the specified length.
     */
    public static String createCapLetters(int size) {

        String output="";
        int index;

        for(int i = 0; i < size; i++){
            index = rand.nextInt(CAP_LETTERS.length());
            output = output + CAP_LETTERS.charAt(index);
        } // end of for loop

        return output;

    } // end of createCapLetters method

    //========== CREATELOWLETTERS METHOD ===========
    /** 
     * Creates a string of random lowercase letters.
     * 
     * @param size     The length of the generated string.
     *
     * @return A string of random lowercase letters with the specified length.
     */
    public static String createLowLetters(int size) {

        String output = "";
        int index;

        for(int i = 0; i < size; i++){
            index = rand.nextInt(LOW_LETTERS.length());
            output = output + LOW_LETTERS.charAt(index);
        } // end of for loop

        return output;

    } // end of createLowLetters method

    //=========== CREATENUMBERS METHOD ============
    /** 
     * Creates a string of random numbers.
     * 
     * @param size     The length of the generated string.
     *
     * @return A string of random numbers with the specified length.
     */
    public static String createNumbers(int size) {

        String output = "";
        int index;

        for(int i = 0; i < size; i++){
            index = rand.nextInt(NUMBERS.length());
            output = output + NUMBERS.charAt(index);
        } // end of for loop

        return output;

    } // end of createNumbers method

    //=========== CREATESPECIALCHARS METHOD ============
    /** 
     * Creates a string of random special characters (@,$,etc.).
     * 
     * @param size     The length of the generated string.
     *
     * @return A string of random special characters with the specified length.
     */
    public static String createSpecialChars(int size){

        String output = "";
        int index;

        for(int i = 0; i < size; i++){
            index = rand.nextInt(SPECIAL_CHARS.length());
            output = output + SPECIAL_CHARS.charAt(index);
        } // end of for loop

        return output;

    } // end of createSpecialChars method

    //============= CREATELEFTOVERS METHOD ================
    /** 
     * Creates a string of random miscalleneous characters.
     * 
     * @param size     The length of the generated string.
     *
     * @return A string of random miscalleneous characters with the specified length.
     */
    public static String createLeftovers(int size){

        String output = "";
        String charset = CAP_LETTERS + LOW_LETTERS + NUMBERS + SPECIAL_CHARS;
        int index;
        
        for(int i = 0; i < size; i++){
            index = rand.nextInt(charset.length());
            output = output + charset.charAt(index);
        } // end of for loop
        
        return output;

    } // end of createLeftovers method

    //============= SHUFFLE METHOD ================
    /** 
     * Creates a shuffled version of an input string.
     *
     * @param txt    The string to be shuffled.
     *
     * @return a string with the same characters as the input parameter but in a different order.
     */
    public static String shuffle(String txt) {
         String[] charset = new String[txt.length()];
         
         for(int i=0; i<txt.length();i++){
            charset[i]=txt.substring(i,i+1);        
         }//end of for loop
         
         String[] shuffChars = new String[charset.length];
         int r;
      
         for(int i=0; i<charset.length; i++){
            r = rand.nextInt(charset.length);
            
            while(charset[r]==null){
               r = rand.nextInt(charset.length);
               //System.out.println(rand);           
            }
            
            shuffChars[i]=charset[r];
            charset[r]=null;
            
         }//end of for loop
         
         String output = "";
      
         for(int i=0;i<shuffChars.length;i++){
            output=output+shuffChars[i];
         } //end of for loop
         
         return output;
         
    } // end of shuffle method

} // end of Generator class
