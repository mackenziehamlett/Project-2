import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.HashMap;

public class Tokenize {
    IDHashMap IDhash;
    int IDsize = -1;
    int counter = 0;
    String token;
    String tokenizedToken;
    String finalString ="";
    String returnVAL = "";
    String returnVals = "";

    // constructor
    public Tokenize(IDHashMap ids) {
        token = "";
        tokenizedToken = "";
        finalString = "";
        this.IDhash = ids;
    }

    public IDHashMap returnHashMap() {
        return IDhash;
    }

    // determine if character is a number (or decimal point)
    public static boolean isNumber( char val ) {
        if ((val >= 48 && val <= 57) || val == 46) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isOperator( char val ) {
        if ((val >= 42 && val <= 47) && val != 46) {
            return true;
        } else {
            return false;
        }
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

    public String set_TokenizeExpr( String expr ) {
        String exprs = expr+", ";
        CharacterIterator iterator = new StringCharacterIterator(expr);
        while (iterator.current() != CharacterIterator.DONE) {
            if (iterator.current() == 43) {
                exprs += "plus, ";
                returnVals += "plus, ";
            } else if (iterator.current() == 45) {
                exprs += "minus, ";
                returnVals += "minus, ";
            } else if (iterator.current() == 42) {
                exprs += "times, ";
                returnVals += "times, ";
            } else if (iterator.current() == 47) {
                exprs += "div, ";
                returnVals += "div, ";
            }
            iterator.next();
        }

        IDhash.setToken("expr-"+IDhash.size, exprs);
        IDsize++;
        String returnS = returnVals.substring(0, returnVals.length()-2);
        return "-"+Integer.toString(IDsize)+", "+returnS;
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
            
            // TODO add id-xxx to a hashmap so we can access it in the parser
            IDhash.setToken("id-"+IDhash.size, token);
            IDsize++;
            returnVAL = "id-"+Integer.toString(IDsize)+", ";
        } 
        
        // invalid token
        if (badToken(token)){
            System.out.print("error");
            System.exit(0);
        }

        return returnVAL;
    }
}

