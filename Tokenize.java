import java.text.CharacterIterator;
import java.text.StringCharacterIterator;

public class Tokenize {
    String token;
    String tokenizedToken;
    String finalString ="";
    String returnVAL = "";

    // constructor
    public void Tokenize() {
        token = "";
        tokenizedToken = "";
        finalString = "";
    }

    // return if the element is a number
    public static boolean isNumber( String token ) {
        try {
            int intVal = Integer.parseInt(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // return if the first element is a letter
    public static boolean firstLetter( String token ) {
        CharacterIterator iterator = new StringCharacterIterator(token);
        if (((iterator.current() >= 65) && (iterator.current() <= 90)) || ((iterator.current() >= 97) && (iterator.current() <= 122))) {
            return true;
        } else {
            return false;
        }
    }

    // function to define an invalid token
    public static boolean badToken( String token ) {
        CharacterIterator iterator = new StringCharacterIterator(token);
        if ((iterator.current() >= 48 && iterator.current() <= 57) || (iterator.current() >= 65 && iterator.current() <= 90) || (iterator.current() >= 97 && iterator.current() <= 122) || (iterator.current() >= 40 && iterator.current() <= 46)) {
            return false;
        } else {
            return true;
        }
    }

    // Find which token the current element is
    public String set_Tokenize( String inputToken ) {
        token = inputToken;
        returnVAL = "";

        // read or write
        if (token.equalsIgnoreCase("read") && !finalString.contains("read")) {
            finalString += " read";
            returnVAL = "read, ";
        } else if (token.equalsIgnoreCase("write") && !finalString.contains("write")) {
            finalString += " write";
            returnVAL = "write, ";
        }

        // number
        if (isNumber(token) && !finalString.contains("number")) {
            finalString += " number";
            returnVAL = "number, ";
        }

        // operators
        if (token.contains("(") && !finalString.contains("lparen")) {
            finalString += " lparen";
            returnVAL = "lparen, ";
        } else if (token.contains(")") && !finalString.contains("rparen")) {
            finalString += " rparen";
            returnVAL = "rparen, ";
        } else if (token.contains("+") && !finalString.contains("plus")) {
            finalString += " plus";
            returnVAL = "plus, ";
        } else if (token.contains("-") && !finalString.contains("minus")) {
            finalString += " minus";
            returnVAL = "minus, ";
        } else if (token.contains("*") && !finalString.contains("times")) {
            finalString += " times";
            returnVAL = "times, ";
        } else if (token.contains(":=") && !finalString.contains("assign")) {
            finalString += " assign";
            returnVAL = "assign, ";
        }

        // id
        if (firstLetter(token) && !finalString.contains("id") && (!token.contains("read") && !token.contains("write"))) {
            finalString += " id";
            returnVAL = "id, ";
        } 
        
        // invalid token
        if (badToken(token)){
            System.out.print("error");
            System.exit(0);
        }

        return returnVAL;
    }
}

