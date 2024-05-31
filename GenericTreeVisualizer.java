public class GenericTreeVisualizer {
    // Methode zum Starten der Graphviz DOT-Ausgabe für den Baum
    public static void printDot(GenericBinaryTree<?> tree) {
        System.out.println("digraph G {"); // Beginn der DOT-Syntax
        if (tree.root != null) {
            printSubTree(tree.root, 0); // Rekursiver Aufruf zum Ausdrucken des Baumes
        }
        System.out.println("}");
    }

    // Rekursive Methode zum Drucken eines Teilbaumes und zur Verwaltung leerer Knoten
    private static int printSubTree(GenericBinaryTree.TreeNode node, int empties) {
        if (node.left == null && node.right == null) {
            // Wenn der Knoten ein Blatt ist (keine Kinder), drucken wir ihn als Rechteck
            System.out.println("    \"" + node.value + "\" [shape=rectangle];");
            return empties;
        }
        if (node.left != null) {
            // Falls linker Kindknoten existiert, drucken wir die Verbindung und rufen rekursiv die Methode für den linken Teilbaum auf
            printNode(node.value, node.left.value);
            empties = printSubTree(node.left, empties);
        } else if (node.right != null) {
            // Falls kein linker Kindknoten existiert, aber ein rechter, drucken wir einen unsichtbaren linken Knoten
            empties = printTerminatingNode(node.value, empties);
        }
        if (node.right != null) {
            // Falls rechter Kindknoten existiert, drucken wir die Verbindung und rufen rekursiv die Methode für den rechten Teilbaum auf
            printNode(node.value, node.right.value);
            empties = printSubTree(node.right, empties);
        } else if (node.left != null) {
            // Falls kein rechter Kindknoten existiert, aber ein linker, drucken wir einen unsichtbaren rechten Knoten
            empties = printTerminatingNode(node.value, empties);
        }
        return empties;
    }

    // Methode zum Drucken der Verbindung zwischen zwei Knoten
    private static void printNode(Object rootValue, Object childValue) {
        System.out.println("    \"" + rootValue + "\" -> \"" + childValue + "\"");
    }

    // Methode zum Drucken eines unsichtbaren Knoten und Erhöhung des Zählers
    private static int printTerminatingNode(Object value, int empties) {
        System.out.println("    empty" + empties + " [label=\"\", style=invis];");
        System.out.println("    \"" + value + "\" -> empty" + empties);
        return empties + 1;
    }

    // Hauptmethode zum Testen der GenericTreeVisualizer-Klasse
    public static void main(String[] args) {
        // Erstellen und Testen eines Baums mit Integer-Werten
        GenericBinaryTree<Integer> intTree = new GenericBinaryTree<>();
        intTree.add(new Integer[]{5, 3, 7, 1, 6, 4, 2});
        printDot(intTree);

        // Erstellen und Testen eines Baums mit String-Werten
        GenericBinaryTree<String> stringTree = new GenericBinaryTree<>();
        stringTree.add(new String[]{"Meyer", "Mueller", "Schmidt", "Schneider", "Becker", "Hoffmann", "Koch"});
        printDot(stringTree);
    }
}
