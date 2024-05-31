public class BinaryTree
{
    // Innere Klasse, die einen Knoten im binären Baum darstellt
    class TreeNode
    {
        final int value; // Wert des Knotens
        TreeNode left, right; // Linker und rechter Unterbaum

        // Konstruktor, der einen Knoten mit einem bestimmten Wert erstellt
        public TreeNode(int value)
        {
            this.value = value;
        }

        // Methode zum Hinzufügen eines neuen Werts in den Baum
        public void add(int value)
        {
            // Überprüfen, ob der neue Wert größer oder kleiner ist als der aktuelle Knotenwert
            if (value > this.value)
            {
                // Wenn der Wert größer ist, füge ihn in den rechten Unterbaum ein
                if (right == null)
                    right = new TreeNode(value);
                else
                    right.add(value);
            }
            else
            {
                // Wenn der Wert kleiner oder gleich ist, füge ihn in den linken Unterbaum ein
                if (left == null)
                    left = new TreeNode(value);
                else
                    left.add(value);
            }
        }

        // Methode zur Überprüfung, ob ein bestimmter Wert im Baum vorhanden ist
        public boolean contains(int value)
        {
            if (value == this.value)
            {
                return true; // Wert gefunden
            }
            else if (value < this.value && left != null)
            {
                return left.contains(value); // Suche im linken Unterbaum
            }
            else if (value > this.value && right != null)
            {
                return right.contains(value); // Suche im rechten Unterbaum
            }
            else
            {
                return false; // Wert nicht gefunden
            }
        }

        // Methode zur Erstellung einer String-Darstellung des Baums
        @Override public String toString()
        {
            String result = "";
            if (left != null)
                result = left.toString(); // String-Darstellung des linken Unterbaums
            result += " " + value; // Wert des aktuellen Knotens
            if (right != null)
                result += right.toString(); // String-Darstellung des rechten Unterbaums
            return result;
        }
    }

    TreeNode root = null; // Wurzelknoten des Baums

    // Methode zum Hinzufügen eines einzelnen Werts in den Baum
    public void add(int value)
    {
        if (root == null)
            root = new TreeNode(value); // Erstelle einen neuen Wurzelknoten, wenn der Baum leer ist
        else
            root.add(value); // Füge den Wert in den bestehenden Baum ein
    }

    // Methode zum Hinzufügen mehrerer Werte in den Baum
    public void add(int... values)
    {
        for (int value : values)
            add(value); // Füge jeden Wert einzeln hinzu
    }

    // Methode zur Überprüfung, ob ein bestimmter Wert im Baum vorhanden ist
    public boolean contains(int value)
    {
        if (root == null)
        {
            return false; // Baum ist leer, Wert kann nicht vorhanden sein
        }
        else
        {
            return root.contains(value); // Überprüfe, ob der Wert im Baum vorhanden ist
        }
    }

    // Methode zur Erstellung einer String-Darstellung des gesamten Baums
    @Override public String toString()
    {
        String result = "";
        if (root != null)
            result = root.toString(); // Erstelle die String-Darstellung beginnend von der Wurzel
        return result;
    }

    // Hauptmethode zum Testen der BinaryTree-Klasse
    public static void main(String[] args)
    {
        BinaryTree tree = new BinaryTree();
        tree.add(5, 3, 7, 1, 6, 4, 2); // Hinzufügen mehrerer Werte in den Baum
        TreeVisualizer.printDot(tree);

        // Testen der contains-Methode
        System.out.println("Does the tree contain 3? " + tree.contains(3));
        System.out.println("Does the tree contain 8? " + tree.contains(8));
        System.out.println("Does the tree contain 1? " + tree.contains(1));

        // Ausgabe des Baums
        System.out.println("Binary Tree: " + tree);
    }
}
