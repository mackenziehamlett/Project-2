import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;

public class ScannerClass {
    String filePath;
    String firstToken;
    String currentToken ="";
    Scanner keyboard;
    String tokenString="";
    Tokenize tokenizedToken = new Tokenize();
    String splitToken="";
    String valueString = "";
    String exprToken = "";

    // constructor
    public void scan_ScannerClass() {
        filePath = "";
        firstToken = "";
        currentToken = "";
        tokenString = "";
    }

    // determine if character is a number (or decimal point)
    public static boolean isNumber( char val ) {
        if ((val >= 48 && val <= 57) || val == 46) {
            return true;
        } else {
            return false;
        }
    }

    // determine if character is a letter
    public static boolean isLetter( char val ) {
        if ((val >= 65 && val <= 90) || (val >= 97 && val <= 122)) {
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

    // SCAN FUNCTION (will be long)
    public void scan_ScannerClass( String fPath ) {
        filePath = fPath;

        // Make sure the program can find the file in the system
        try {
            keyboard = new Scanner(new FileInputStream(filePath));
        } catch (FileNotFoundException e) {
            System.out.println(filePath+" NOT FOUND. TRY FULL PATH FOR THE FILE");
            System.exit(0);
        }        

        do {
            currentToken = "";
            splitToken = "";
            exprToken = "";
            firstToken = keyboard.next();
            valueString += firstToken+", ";
            CharacterIterator iterator = new StringCharacterIterator(firstToken);

            // if there is a comment block or comment line
            if (firstToken.equals("/*")) {
                while (!firstToken.equals("*/"))
                firstToken = keyboard.next();
            } else if (firstToken.equals("//")) {
                firstToken = keyboard.nextLine();
            } 
            if (firstToken.equals("*/")) {
                firstToken = keyboard.next();
            }

            if (isNumber(iterator.current()) && isOperator(iterator.next())) {
                iterator.previous();
                while (iterator.current() != CharacterIterator.DONE) {
                    exprToken += iterator.current();
                    iterator.next();
                }
                tokenizedToken.set_TokenizeExpr(exprToken);
            }

            

            // LONGEST POSSIBLE TOKEN RULE IMPLEMENTATION
            while (iterator.current() != CharacterIterator.DONE && (isLetter(iterator.current()) || isNumber(iterator.current()))) {
                char temp = iterator.current();
                if (isNumber(temp) && currentToken=="") {
                    while (isNumber(iterator.current())) {
                        currentToken += String.valueOf(iterator.current());
                        iterator.next();
                    }
                } else if (isLetter(temp) || currentToken != "") {
                    while (isLetter(iterator.current()) || isNumber(iterator.current())) {
                        splitToken += String.valueOf(iterator.current());
                        iterator.next();
                    }
                }
            } 

            //  scan for operators (,),-,+,:=... etc
            while (iterator.current() != CharacterIterator.DONE && (iterator.current() >= 40 && iterator.current() <= 45)) {
                char temp = iterator.current();
                currentToken += String.valueOf(temp);
                iterator.next();
            }
    

            // switch scanned in element into its repective token
            if (currentToken != "" && currentToken != firstToken) {
                tokenString += tokenizedToken.set_Tokenize(currentToken);
            } 
            if (splitToken != "" && currentToken != firstToken) {
                tokenString += tokenizedToken.set_Tokenize(splitToken);
            } else {
               tokenString += tokenizedToken.set_Tokenize(firstToken);
            }


        } while (keyboard.hasNext());

        // get rid of last comma
        String finalString = tokenString.substring(0, tokenString.length()-2);

        //System.out.print(parseString);
        //System.out.println("("+ finalString +")");
    }
}
