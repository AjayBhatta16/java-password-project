/**
 * An instance class for storing password data.
 * @author Ajay Bhattacharyya
 */
public class Password {

    /**The password itself. */
    private String value;
    /**Specifies whether or not a password is a PIN. The default value is false.*/
    private boolean isPin = false;

    //********** Constructor methods *************//
    /**
     * This constructor creates a new Password object with a given value and name.
     * It is intended for use with the Generator class.
     *
     * @param value    The password itself.
     *
     * 
     */
    public Password(String value) {
        this.value = value;
    } // end of constructor

    /**
     * This constructor creates a new password object with a given value and specifies whether
     * or not it is a PIN. It is intended for use with the Analyzer class.
     *
     * @param value    The password itself.
     *
     * @param isPin    A boolean value to specify whether or not the password's value is supposed to contain 
     *                 only numbers.
     */
    public Password(String value, boolean isPin) {
        this.value = value;
        this.isPin = isPin;
    } // end of constructor

    //*********** Accessor methods ****************//
    
    //=============== GETVALUE METHOD ================
    /**
     * Gives other programs access to a given Password object's value attribute.
     *
     * @return The password.
     */
    public String getValue() {
        return this.value;
    } // end of getValue method

    //============== GETLENGTH METHOD ==================
    /**
     * Calculates the total number of characters in a given Password object's value attribute.
     *
     * @return The length of the password.
     */
    public int getLength() {
        return this.value.length();
    } // end of getLength method

    //============== ISPIN METHOD ==================
    /**
     * Gives other programs access to a given Password object's isPin attribute.
     *
     * @return True if the given Password has been specified as a pin, false otherwise.
     */
    public boolean isPin() {
        return this.isPin;
    } // end of isPin method

    //******** Chartype counter methods ***********//

    //============== TOTALCAPLETTERS METHOD ===============
    /**
     * Counts the uppercase Letters in a given password object's value attribute.
     * 
     * @return The total number of capital letters in a password.
     */
    public int totalCapLetters() {
        int result = 0;
        for(int i = 0; i < this.value.length(); i++) {
            if(Character.isUpperCase(this.value.charAt(i))) {
                result += 1;
            } // end of if statement
        } // end of for loop
        return result;
    } // end of totalCapLetters method

    //============== TOTALLOWLETTERS METHOD ===============
    /**
     * Counts the lowercase letters in a given Password object's value attribute.
     * 
     * @return The total number of lowercase letters in a password.
     */
    public int totalLowLetters() {
        int result = 0;
        for(int i = 0; i < this.value.length(); i++) {
            if(Character.isLowerCase(this.value.charAt(i))) {
                result += 1;
            } // end of if statement
        } // end of for loop
        return result;
    } // end of totalLowLetters method

    //============== TOTALNUMBERS METHOD ==================
    /**
     * Counts the numeric characters in a given Password object's value attribute.
     *
     * @return The total number of numeric characters in a password.
     */
    public int totalNumbers() {
        int result = 0;
        for(int i = 0; i < this.value.length(); i++) {
            if(Character.isDigit(this.value.charAt(i))) {
                result += 1;
            } // end of if statement
        } // end of for loop
        return result;
    } // end of totalNumbers method

    //============== TOTALSPECIALCHARS METHOD =============
    /**
     * Calculates the total number of miscallaneous characters ($,?,etc.) in a given Password's
     * value attribute. 
     *
     * @return The total number of characters in the password that aren't letters or numbers.
     */
    public int totalSpecialChars() {
        return this.value.length() - (this.totalCapLetters()+this.totalLowLetters()+this.totalNumbers());
    } // end of totalSpecialChars method

    //*********** Other methods ***************//

    //============== GETSTRENGTHSCORE METHOD ===============
    /**
     * Gives the password a strength score using methods from the Analyzer class.
     *
     * @return The strength of the password.
     */
    public double getStrengthScore() {
        return Analyzer.getScore(this);
    } // end of getStrengthScore method 

} // end of Password class
