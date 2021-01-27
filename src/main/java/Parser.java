public class Parser {
    private static LexicalAnalyzer lex;

    static Tree parse(String input) throws FunctionException {
        lex = new LexicalAnalyzer(input);
        lex.nextToken();
        return S();
    }

    private static Tree S() throws FunctionException {
        switch (lex.curToken()) {
            case NAME:
                //name
                Tree name = new Tree(lex.getName());
                lex.nextToken();
                //stars
                Tree stars = Stars();
                //brackets
                Tree brackets = Brackets();
                return new Tree("S", name, stars, brackets);
            case END:
                return new Tree("S", new Tree("$"));
            default:
                throw new FunctionException("Invalid function");
        }
    }

    private static Tree Stars() throws FunctionException {
        if (lex.curToken() == Token.STAR) {
            //*
            lex.nextToken();
            Tree stars = Stars();
            return new Tree("*", stars);
        }
        return Ampersand();
    }

    private static Tree Ampersand() throws FunctionException {
        switch (lex.curToken()) {
            case AMPERSAND:
                //&
                lex.nextToken();
                //name
                String name = lex.getName();
                lex.nextToken();
                return new Tree("&", new Tree(name));
            case NAME:
                //name
                name = lex.getName();
                lex.nextToken();
                return new Tree(name);
            default:
                throw new FunctionException("Invalid function");
        }
    }

    private static Tree Brackets() throws FunctionException {
        if (lex.curToken() == Token.LPAREN) {
            lex.nextToken();
            Tree vars = Vars();
            return new Tree("(", vars);
        }
        throw new FunctionException("Invalid function");
    }

    private static Tree Vars() throws FunctionException {
        switch (lex.curToken()) {
            case RPAREN:
                lex.nextToken();
                return new Tree(")");
            case COMMA:
                lex.nextToken();
                //name
                String name = lex.getName();
                lex.nextToken();
                //stars
                Tree stars = Stars();
                //vars
                Tree vars = Vars();
                return new Tree("," + name, stars, vars);
            case NAME:
                //name
                name = lex.getName();
                lex.nextToken();
                //stars
                stars = Stars();
                //vars
                vars = Vars();
                return new Tree(name, stars, vars);
            default:
                throw new FunctionException("Invalid function");
        }
    }
}