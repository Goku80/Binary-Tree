public class TreeVisualizer {
    // Methode zum Starten der Graphviz DOT-Ausgabe für den Baum
    public static void printDot(BinaryTree tree) {
        System.out.println("digraph G {"); // Beginn der DOT-Syntax
        if (tree.root != null) {
            printSubTree(tree.root, 0); // Rekursiver Aufruf zum Ausdrucken des Baumes
        }
        System.out.println("}");
    }

    // Rekursive Methode zum Drucken eines Teilbaumes und zur Verwaltung leerer Knoten
    private static int printSubTree(BinaryTree.TreeNode node, int empties) {
        // Wenn der Knoten ein Blatt ist (keine Kinder), drucken wir ihn als Rechteck
        if (node.left == null && node.right == null) {
            System.out.println("    \"" + node.value + "\" [shape=rectangle];");
            return empties;
        }

        // Falls linker Kindknoten existiert, drucken wir die Verbindung und rufen rekursiv die Methode für den linken Teilbaum auf
        if (node.left != null) {
            printNode(node.value, node.left.value);
            empties = printSubTree(node.left, empties);
        } else if (node.right != null) {
            // Falls kein linker Kindknoten existiert, aber ein rechter, drucken wir einen unsichtbaren linken Knoten
            empties = printTerminatingNode(node.value, empties);
        }

        // Falls rechter Kindknoten existiert, drucken wir die Verbindung und rufen rekursiv die Methode für den rechten Teilbaum auf
        if (node.right != null) {
            printNode(node.value, node.right.value);
            empties = printSubTree(node.right, empties);
        } else if (node.left != null) {
            // Falls kein rechter Kindknoten existiert, aber ein linker, drucken wir einen unsichtbaren rechten Knoten
            empties = printTerminatingNode(node.value, empties);
        }
        return empties;
    }

    // Methode zum Drucken der Verbindung zwischen zwei Knoten
    private static void printNode(int rootValue, int childValue) {
        System.out.println("    \"" + rootValue + "\" -> \"" + childValue + "\"");
    }

    // Methode zum Drucken eines unsichtbaren Knoten und Erhöhung des Zählers
    private static int printTerminatingNode(int value, int empties) {
        System.out.println("    empty" + empties + " [label=\"\", style=invis];");
        System.out.println("    \"" + value + "\" -> empty" + empties);
        return empties + 1;
    }
}
