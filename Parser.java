public class Parser {
/*
Usage
Parser parser = new Parser(...);
parser.parse();
*/
    String[] tokenList;
    String[] valueList;
    // Constructor from strings
    public Parser(String tokenString, String valueString) {
        this.tokenList = tokenString.split(", ");
        this.valueList = valueString.split(", ");
        System.out.println("IS THIS CORRECT?");
        System.out.print("Token List: ");
        System.out.println(this.tokenList);
        System.out.print("Value List: ");
        System.out.println(this.valueList);
    }
    // Constructor from lists
    public Parser(String[] tokenList, String[] valueList) {
        this.tokenList = tokenList;
        this.valueList = valueList;
    }
    // ? TODO return type?
    // Just prints out stuff, recursively calls things based on the token value
    public void parse() {

    }


    /* 
        If we want to write a string to a file, 
        instead of (or in addition to) printing the XML tag,
        we can just edit it here
    */
    private void print(String string) {
        System.out.print(string);
    }
    
    private void println(String string) {
        System.out.println(string);
    }
}