import java.io.*;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.Scanner;

import java.io.*;
import java.text.ParseException;
import java.util.Scanner;

public class Main {

    static int index = -1;

    public static void main(String[] args) throws IOException, FunctionException {
        Scanner in = new Scanner(System.in);
        Tree t = new Parser().parse(in.nextLine());
        visualize(t, "graph");
    }

    private static void visualize(Tree root, String fileName) throws IOException {
        StringBuilder sb = new StringBuilder("digraph G {\n");
        dfs(root, -1, sb);
        sb.append("}\n");
        try (PrintWriter out = new PrintWriter(fileName + ".dot")) {
            out.println(sb.toString());
            Runtime.getRuntime().exec("dot -Tpng " + fileName + ".dot -o " + fileName + ".png");
        }
    }

    private static void dfs(Tree t, int pIndex, StringBuilder sb) {
        index++;
        sb.append(String.format("%d [label = \"%s\"]\n", index, t.node));
        if (pIndex != -1) {
            sb.append(String.format("%d -> %d\n", pIndex, index));
        }
        int p = index;
        for (Tree ch : t.children) {
            dfs(ch, p, sb);
        }
    }


}

