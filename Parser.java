import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Collections;

public class Parser {
    /*
     * Usage Parser parser = new Parser(...); parser.parse();
     */
    ArrayList<Object> tokenList = new ArrayList<Object>();
    IDHashMap idHashMap;

    // Constructor
    public Parser(ArrayList<Object> tokenList, IDHashMap ids) {
        this.tokenList = tokenList;
        this.idHashMap = ids;
    }

    public Parser(String tokenString, IDHashMap ids) {
        Collections.addAll(tokenList, tokenString.split(", "));
        this.idHashMap = ids;
    }

    
    // Just prints out stuff, recursively calls things based on the token value
    public void parse() {
        this.println("<Program>");
        this.parseHelper(1, this.tokenList, this.idHashMap);
        this.println("</Program>");
    }

    private String getElemAndPop() {
        return this.tokenList.remove(0).toString();
    }

    private String peakElem(int ind) {
        return this.tokenList.get(ind).toString();
    }

    private static void debug(String s) {
        if (false) {
            System.out.println("DEBUG: " + s);
        }
    }

    private void parse_expr(ArrayList<Object> tokenList, IDHashMap idHashMap, int depth) {
        String expr_with_bs = this.idHashMap.getToken(this.getElemAndPop()).toString();

        ArrayList<String> expList = new ArrayList<String>();

        Collections.addAll(expList, expr_with_bs.split(", "));
        // Remove the beginning string
        String expr = expList.remove(0);

        ArrayList<String> numberList = new ArrayList<String>();
        String _iterStr = "";
        for (int i = 0; i < expr.length(); i++) {
            if (Character.isDigit(expr.charAt(i))) {
                _iterStr += expr.charAt(i);
            } else {
                numberList.add(_iterStr);
                _iterStr = "";
            }
        }
        numberList.add(_iterStr);
        // Remove last empty element
        expList.remove(expList.size() - 1);

        // Now start parsing

        parse_expr_help(expr, tokenList, idHashMap, depth, numberList);
        return;
    }

    // * <expr> →<term> <term tail>
    private void parse_expr_help(String expr, ArrayList<Object> tokenList, IDHashMap idHashMap, int depth,
            ArrayList<String> numberList) {
        this.indent(depth + 1);
        this.println("<expr>");
        this.parse_term(depth, false, numberList);
        if (numberList.size() != 0) {
            this.indent(depth + 2);
            this.println("<number>");
            this.indent(depth + 2);
            this.println(numberList.get(0));
            this.indent(depth + 2);
            this.println("</number>");
        }
        this.indent(depth + 1);
        this.println("</expr>");

    }

    // Term and term tail
    // * <term> →<factor> <fact tail>
    // * <term tail> →<add op> <term> <term tail> | MT
    private void parse_term(int depth, boolean isTail, ArrayList<String> numberList) {
        try {
            this.peakElem(0);
        } catch (IndexOutOfBoundsException e) {
            return;
        }
        if (!isTail) {
            this.parse_factor(depth, false, numberList);
            this.parse_factor(depth, true, numberList);

        } else {
            // <add op> <term> <term tail> | MT
            if (this.peakElem(0).equals("plus") || this.peakElem(0).equals("minus")) {
                this.parse_term(depth, false, numberList);
                this.parse_term(depth, true, numberList);
            }
        }
    }

    // *<factor> → lparen <expr> rparen | id | number
    // * <fact tail> → <mult op> <factor> <fact tail> | MT
    // Always jsut one thing and a factor tail
    private void parse_factor(int depth, boolean isTail, ArrayList<String> numberList) {
        try {
            this.peakElem(0);
        } catch (IndexOutOfBoundsException e) {
            return;
        }
        if (!isTail) {
            if (this.peakElem(0).equals("lparen")) {
                this.getElemAndPop();
                // Call expr
                this.parse_expr(tokenList, idHashMap, depth);
                if (this.peakElem(0).equals("rparen")) {
                    this.getElemAndPop();
                } else {
                    System.out.println("No matching rparen for left");
                }
            } else if (this.peakElem(0).contains("id")) {
                this.indent(depth + 1);
                this.println("<id>");
                this.indent(depth + 2);
                this.print(this.idHashMap.getToken(this.getElemAndPop()).toString());
                this.indent(depth + 1);
                this.println("</id>");

            } else {
                // TODO print number
                // int i = 0;
                // for (; i< expr.length(); i++) {
                // if (expr.charAt(i) == '/' || expr.charAt(i) == '*' || expr.charAt(i) == '+'
                // || expr.charAt(i) == '-') {
                // break;
                // }
                // System.out.print(expr.charAt(i));
                // }
                if (numberList.size() != 0) {
                    this.indent(depth + 2);
                    this.println("<number>");
                    this.indent(depth + 3);
                    this.println(numberList.remove(0));
                    this.indent(depth + 2);
                    this.println("</number>");
                }

            }
        } else {
            if (this.peakElem(0).equals("div") || this.peakElem(0).equals("times")) {
                this.indent(depth + 1);
                String mult_op = this.getElemAndPop();
                this.print("<" + mult_op + ">");
                this.println("</" + mult_op + ">");
                // <factor>
                this.parse_factor(depth, false, numberList);
                // <fact_tail>
                this.parse_factor(depth, true, numberList);
            }
        }

    }

    // We have no counter variable, we are resizing tokenList and valueList to
    // remove the first element from the next call
    private void parseHelper(int depth, ArrayList<Object> tokenList, IDHashMap idHashMap) {
        // if (tokenList.size() == 0) {
        // return;
        // }
        // // int tokensUsed = 0; // Incremement each time you use a token
        // // int valuesUsed = 0; // Increment each time you use a value

        this.indent(depth);
        this.println("<stmt_list>");

        this.indent(depth + 1);
        this.println("<stmt>");

        // This is the current <stmt> object basically

        // ArrayList<Object> tokens = (ArrayList<Object>) tokenList.get(0);

        // // String valueToken = valueList[0];
        // * <stmt> → id assign <expr> | read id | write <expr>
        String _switchCase = this.getElemAndPop();
        debug(_switchCase);
        String _switchCase1;
        if (_switchCase.contains("id")) {
            _switchCase1 = "id";
            this.indent(depth + 1);
            this.println("<id>");
            this.indent(depth + 2);
            this.println(this.idHashMap.getToken(_switchCase).toString());
            this.indent(depth + 1);
            this.println("</id>");
            _switchCase = "id";
            // If there is an assign next, we need to show the expr and assign tags

            if (this.peakElem(0).equals("assign")) {
                this.indent(depth + 1);
                this.println("<assign></assign>");
                this.getElemAndPop();
                if (this.peakElem(0).contains("expr"))
                    // Print expr
                    // this.indent(depth + 1);
                this.parse_expr(tokenList, idHashMap, depth);
            }
        } else {
            _switchCase1 = _switchCase;
        }
        switch (_switchCase) {
            case "id":
                // We already solved this above, we will just skip this
                break;
            // TODO "read" is not correct
            case "read": // * read id
                this.indent(depth + 1);
                // String _biggerTag = this.getElemAndPop();
                this.println("<" + "read" + ">");
                String _next = this.getElemAndPop();
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
                this.println("</" + "read" + ">");
                break;
            case "write": // * write <expr>
                // TODO check for an expression
                this.indent(depth + 2);
                this.println("<write>");
                // this.indent(depth + 2Tes); // * <expr_print_prob
                // TODO do expression parsing here EXPR
                this.parse_expr(tokenList, idHashMap, depth + 1);
                this.indent(depth + 1);
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
            // this.parseHelper(depth + 1, new ArrayList<Object>(tokenList.subList(1,
            // tokenList.size())),
            // this.idHashMap);
            this.parseHelper(depth + 1, tokenList, this.idHashMap);
        } else {
            return;
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
