import java.util.HashMap;
import java.util.PriorityQueue;

public class HuffmanEncoding {

    // Erstellt ein Histogramm (Häufigkeitsverteilung) der Zeichen im Text
    public static HashMap<Character, Integer> stringHistogram(String text) {
        var map = new HashMap<Character, Integer>();
        // Iteriert über jedes Zeichen im Text
        for (char c : text.toCharArray()) {
            Integer value = map.get(c); // Holt die aktuelle Häufigkeit des Zeichens
            if (value == null) // Wenn das Zeichen noch nicht im Map ist
                map.put(c, 1); // Füge es mit einer Häufigkeit von 1 hinzu
            else
                map.put(c, value + 1); // Erhöhe die Häufigkeit um 1
        }
        return map; // Gib das Histogramm zurück
    }

    // Klasse zur Speicherung eines Paares von Werten (z.B. Zeichen und deren Häufigkeit)
    static class Pair<T1, T2 extends Comparable<T2>> implements Comparable<Pair<T1, T2>> {
        T1 first; // Erster Wert des Paares (z.B. Zeichen)
        T2 second; // Zweiter Wert des Paares (z.B. Häufigkeit)

        public Pair(T1 first, T2 second) {
            this.first = first;
            this.second = second;
        }

        // Vergleicht zwei Paare basierend auf dem zweiten Wert (z.B. Häufigkeit)
        @Override
        public int compareTo(Pair<T1, T2> other) {
            return this.second.compareTo(other.second);
        }

        // Gibt eine lesbare Darstellung des Paares zurück
        @Override
        public String toString() {
            return first + " : " + second;
        }
    }

    // Klasse für einen vergleichbaren Binärbaum
    static class ComparableBinaryTree<T extends Comparable<T>> extends GenericBinaryTree<T> implements Comparable<ComparableBinaryTree<T>> {
        // Vergleicht zwei Bäume basierend auf dem Wert des Wurzelknotens
        @Override
        public int compareTo(ComparableBinaryTree<T> other) {
            return this.root.value.compareTo(other.root.value);
        }
    }

    private static void generateHuffmanCode(GenericBinaryTree<Pair<String, Integer>>.TreeNode node, String code, HashMap map)
    {
        if (node.left == null && node.right == null)
        {
            map.put(node.value.first.charAt(0), code);
        }
        else
        {
            generateHuffmanCode(node.left, code + '0', map);
            generateHuffmanCode(node.right, code + '1' , map) ;
        }
    }

    public static HashMap<Character , String> generateHuffmanCode( GenericBinaryTree tree )
    {
        var map = new HashMap<Character, String>();
        generateHuffmanCode(tree.root, "" , map);
        return map;
    }

    public static void main(String[] args) {
        // Erzeugt das Histogramm der Zeichenhäufigkeit aus dem Eingabetext
        var histogram = stringHistogram("a fast runner need never be afraid of the dark");

        // Erstellt eine Prioritätswarteschlange für die Bäume
        var queue = new PriorityQueue<ComparableBinaryTree<Pair<String, Integer>>>();

        // Fügt jedes Zeichen als Einzelbaum zur Warteschlange hinzu
        for (char c : histogram.keySet()) {
            var tree = new ComparableBinaryTree<Pair<String, Integer>>();
            tree.add(new Pair<String, Integer>(String.valueOf(c), histogram.get(c)));
            queue.add(tree);
        }

        // Kombiniert die Bäume nach dem Huffman-Algorithmus
        while (queue.size() > 1) {
            var tree1 = queue.remove(); // Entfernt den Baum mit der niedrigsten Häufigkeit
            var tree2 = queue.remove(); // Entfernt den Baum mit der zweitniedrigsten Häufigkeit
            var newTree = new ComparableBinaryTree<Pair<String, Integer>>();
            // Erstellt einen neuen Baum, dessen Wurzel die Summe der Häufigkeiten der beiden Bäume enthält
            newTree.add(new Pair<String, Integer>(
                    tree1.root.value.first + tree2.root.value.first,
                    tree1.root.value.second + tree2.root.value.second));
            newTree.root.left = tree1.root; // Setzt den ersten Baum als linken Teilbaum
            newTree.root.right = tree2.root; // Setzt den zweiten Baum als rechten Teilbaum
            queue.add(newTree); // Fügt den neuen Baum zur Warteschlange hinzu
        }

        // Holt den finalen Baum aus der Warteschlange
        var tree = queue.peek();

        // Visualisiert den Baum als DOT-Graph
        GenericTreeVisualizer.printDot(tree);

        var tree3 = queue.peek();
        var map = generateHuffmanCode(tree3);
        for (var c : map.keySet())
        {
            System.out.println(c + " : " + map.get(c));
        }
    }
}
