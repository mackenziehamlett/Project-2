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
        //* <stmt> → id assign <expr> | read id | write <expr>
        switch (tokens.get(0).toString()) {
            
        case "id": //* id assign <expr>
            if (tokenList.get(1) == "assign") {
                // ? grab the value, valueToken or go down the <expr> tree ?
                // todo expr
                this.println(this.idHashMap.getToken(tokens.get(2).toString()).toString());
            } else {
                System.out.println("id not followed by assign operator");
                System.exit(0);
            }
            break;
        case "read": //* read id
            this.indent(depth + 1);
            this.println("<"+tokens.get(0).toString()+">");
            String _next = tokenList.get(1).toString();
            if (_next.contains("id")) {
                Object idQueryResult = this.idHashMap.getToken(_next);
                this.indent(depth + 2);
                this.println("<read>");
                this.indent(depth + 3);
                // this.println(tokens.get(0).toString());
                this.println(idQueryResult.toString());
                this.indent(depth + 2);
                this.println("</read>");
                break;
            }
            this.indent(depth + 1);
            this.println("</"+tokens.get(0).toString()+">");


        case "write": //* write <expr>
            // TODO check for an expression
            this.indent(depth);
            this.println("<write>");
            // TODO do expression parsing here EXPR
            this.println(this.idHashMap.getToken(tokens.get(1).toString()).toString());
            this.indent(depth);
            this.println("</write>");
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
    /// usage: this.ident(n) => prints n number of tabs on 1 line, no newline
    private void indent(int depth) {
        for (int i = 0; i < depth; i++) {
            this.print("\t");
        }
    }
    
    /// usage: this.print(string)
    private void print(String string) {
        System.out.print(string);
    }

    /// usage: this.println(string)
    private void println(String string) {
        System.out.println(string);
    }
}




