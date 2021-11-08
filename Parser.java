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
        // System.out.print("Token List: ");
        // for (String s : this.tokenList) {
        //     System.out.println(s);
            
        // }
        // System.out.print("Value List: ");
        // for (String s : this.valueList) {
        //     System.out.println(s);
        // }
    }
    // Constructor from lists
    public Parser(String[] tokenList, String[] valueList) {
        this.tokenList = tokenList;
        this.valueList = valueList;
    }
    // ? TODO return type?
    // Just prints out stuff, recursively calls things based on the token value
    public void parse() {
        // this.println("Workingln");
        // this.print("Working");
    }


    /* 
        If we want to write a string to a file, 
        instead of (or in addition to) printing the XML tag,
        we can just edit it here
    */
    /// usage: this.print(string)
    private void print(String string) {
        System.out.print(string);
    }
    /// usage: this.println(string)
    private void println(String string) {
        System.out.println(string);
    }
}