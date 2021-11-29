import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parser {
    /*
     * Usage Parser parser = new Parser(...); parser.parse();
     */
    ArrayList<Object> tokenList;
    IDHashMap idHashMap;
    // String[] valueList;

    // Constructor from strings
    // public Parser(String tokenString, String valueString) {
    // this.tokenList = tokenString.split(", "); // "a, b, c" => [a, b, c]

    // // this.valueList = valueString.split(", ");

    // }

    // Constructor
    public Parser(ArrayList<Object> tokenList, IDHashMap ids) {
        this.tokenList = tokenList;
        this.idHashMap = ids;
    }

    // ? TODO return type?
    // Just prints out stuff, recursively calls things based on the token value
    public void parse() {
        // TODO print <program>
        this.parseHelper(0, this.tokenList, this.idHashMap);
    }

    // We have no counter variable, we are resizing tokenList and valueList to
    // remove the first element from the next call
    private void parseHelper(int depth, ArrayList<Object> tokenList, IDHashMap idHashMap) {
        // // int tokensUsed = 0; // Incremement each time you use a token
        // // int valuesUsed = 0; // Increment each time you use a value

        this.indent(depth);
        this.println("<stmt_list>");

        this.indent(depth + 1);
        this.println("<stmt>");

        // This is the current <stmt> object basically
        // TODO switch case logic here
        // TODO .....

        ArrayList<Object> tokens = (ArrayList<Object>) tokenList.get(0);
        // // String valueToken = valueList[0];
        // <stmt> → id assign <expr> | read id | write <expr>
        switch ((String) tokens.get(0)) {

        case "id": // id assign <expr>
            if (tokenList.get(1) == "assign") {
                // ? grab the value, valueToken or go down the <expr> tree ?
            } else {
                System.out.println("id not followed by assign operator");
                System.exit(0);
            }
            break;
        case "read": // read id
            if (tokenList.get(1) == "id") {
                // TODO get the value for this id
                // TODO use the ID HASH MAP lookup to get the actual value
            }
            break;

        case "write": // write <expr>
            // TODO check for an expression
            break;

        default:
            System.out.println("Error has occured");
            break;
        }

        // Now that we got the switch/case thing done, we go to the next call
        // Call other parseHelper functions to do specific things, that need their own
        // depth

        // This is the next <stmt_list> object basically
        if (tokenList.size() != 0) {
            // * Now we are down into <stmt>'s <stmt_list>
            this.parseHelper(depth + 1, new ArrayList<Object>(tokenList.subList(0, tokenList.size())),
                    this.idHashMap.getCopy());
        } else {
            // TODO we are done printing, all of the parseHelper functions will begin to
            // indent back and print end tags
            // ? Does something belong here?
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