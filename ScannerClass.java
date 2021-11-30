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
    Tokenize tokenizedToken;
    String splitToken="";
    String valueString = "";
    String exprToken = "";

    // constructor
    public void scan_ScannerClass(IDHashMap ids) {
        filePath = "";
        firstToken = "";
        currentToken = "";
        tokenString = "";
        this.tokenizedToken = new Tokenize(ids);
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
    public String scan_ScannerClass( String fPath, IDHashMap ids ) {
        filePath = fPath;
        this.tokenizedToken = new Tokenize(ids);

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

            // if there is a comment block or comment line
            if (firstToken.startsWith("/*")) {
                while (!firstToken.endsWith("*/"))
                    firstToken = keyboard.next();
            } else if (firstToken.startsWith("//")) {
                if(keyboard.hasNext()) {
                    keyboard.nextLine();
                    firstToken = keyboard.next();
                }else{break;}

            }
            if (firstToken.endsWith("*/")) {
                if(keyboard.hasNext()) {
                    keyboard.nextLine();
                    firstToken = keyboard.next();
                }else{break;}
            }
            CharacterIterator iterator = new StringCharacterIterator(firstToken);
            if (isNumber(iterator.current()) && isOperator(iterator.next())) {
                iterator.previous();
                while (iterator.current() != CharacterIterator.DONE) {
                    exprToken += iterator.current();
                    iterator.next();
                }
                tokenString += "expr"+tokenizedToken.set_TokenizeExpr(exprToken)+", ";
                if (keyboard.hasNext()) {
                    firstToken = keyboard.next();
                } else {
                    break;
                }
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

        // System.out.print(finalString);
        return finalString;
        //System.out.println("("+ finalString +")");
    }
}
