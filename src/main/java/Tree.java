import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Tree {
    public String node;
    StringBuilder sb;
    public List<Tree> children;

    public Tree(String node, Tree... trees) {
        this.node = node;
        this.children = new ArrayList<>(Arrays.asList(trees));
        this.sb = new StringBuilder();
    }

    @Override
    public String toString() {
        sb.append(node);
        for (int i = 0; i < children.size(); i++) {
            sb.append(children.get(i).toString());
        }
        return sb.toString();
    }
}
