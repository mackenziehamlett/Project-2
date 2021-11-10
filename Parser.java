import java.util.Arrays;

public class Parser {
    /*
     * Usage Parser parser = new Parser(...); parser.parse();
     */
    String[] tokenList;
    String[] valueList;

    // Constructor from strings
    public Parser(String tokenString, String valueString) {
        this.tokenList = tokenString.split(", "); // "a, b, c" => [a, b, c]
        this.valueList = valueString.split(", ");

    }

    // Constructor from lists
    public Parser(String[] tokenList, String[] valueList) {
        this.tokenList = tokenList;
        this.valueList = valueList;
    }

    // ? TODO return type?
    // Just prints out stuff, recursively calls things based on the token value
    public void parse() {
        // TODO print <program>
        this.parseHelper(0, this.tokenList, this.valueList);
    }

    // We have no counter variable, we are resizing tokenList and valueList to
    // remove the first element from the next call
    private void parseHelper(int depth, String[] tokenList, String[] valueList) {
        this.indent(depth);
        this.println("<stmt_list>");

        this.indent(depth + 1);
        this.println("<stmt>");

        // This is the current <stmt> object basically
        // TODO switch case logic here
        // TODO .....
        /*
         * ... ...
         */

        // Now that we got the switch/case thing done, we go to the next call
        // Call other parseHelper functions to do specific things, that need their own
        // depth

        // This is the next <stmt_list> object basically
        if (tokenList.length != 0 && valueList.length != 0) {

            this.parseHelper(depth + 1, 
                    Arrays.copyOfRange(tokenList, 1, tokenList.length),
                    Arrays.copyOfRange(valueList, 1, valueList.length));
        } else {
            // TODO we are done printing, all of the parseHelper functions will begin to
            // indent back and print end tags
            if (tokenList.length != valueList.length) {
                //* This is debugging stuff, you can ignore it. I'm trying to see if some weird edge cases happen, 
                //* eventually and I'd rather catch them instantly then debug
                System.out.println("TokenList and valueList are not the same size during parseHelperCalls");
                System.out.println("Is this okay/allowed????");
                System.out.println("Depth: " + depth);
                for (String s : this.tokenList) {
                    System.out.print(s);
                }
                for (String s : this.valueList) {
                    System.out.print(s);
                }
                System.exit(0);
            }
        }

        this.indent(depth + 1);
        this.println("</stmt>");
        this.indent(depth);
        this.println("</stmt_list>");

    }

    /*
     * If we want to write a string to a file, instead of (or in addition to)
     * printing the XML tag, we can just edit it here
     */
    /// usage: this.print(string)
    private void indent(int depth) {
        for (int i = 0; i < depth; i++) {
            this.print("\t");
        }
    }

    private void print(String string) {
        System.out.print(string);
    }

    /// usage: this.println(string)
    private void println(String string) {
        System.out.println(string);
    }
}