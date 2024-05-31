public class GenericBinaryTree<T extends Comparable<T>>
{
    // Innere Klasse für die Baumknoten
    class TreeNode
    {
        T value; // Wert des Knotens
        TreeNode left, right; // Linker und rechter Unterbaum

        // Konstruktor für einen Baumknoten mit einem bestimmten Wert
        public TreeNode(T value)
        {
            this.value = value;
        }

        // Methode zum Hinzufügen eines neuen Werts in den Baumknoten
        public void add(T value)
        {
            // Vergleich des neuen Werts mit dem aktuellen Knotenwert
            if (value.compareTo(this.value) > 0)
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

        // Methode zur Erstellung einer String-Darstellung des Baumknotens
        @Override
        public String toString()
        {
            String result = "";
            if (left != null) result = left.toString(); // String-Darstellung des linken Unterbaums
            result += " " + value; // Wert des aktuellen Knotens
            if (right != null) result += right.toString(); // String-Darstellung des rechten Unterbaums
            return result;
        }

        // Methode zur Überprüfung, ob ein bestimmter Wert im Unterbaum enthalten ist
        public boolean contains(T value)
        {
            // Überprüfen, ob der Wert im Unterbaum vorhanden ist
            if (value.equals(this.value))
            {
                return true; // Wert gefunden
            }
            else if (value.compareTo(this.value) < 0 && left != null)
            {
                return left.contains(value); // Suche im linken Unterbaum
            }
            else if (value.compareTo(this.value) > 0 && right != null)
            {
                return right.contains(value); // Suche im rechten Unterbaum
            }
            else
            {
                return false; // Wert nicht gefunden
            }
        }
    }

    TreeNode root = null; // Wurzelknoten des Baums

    // Methode zum Hinzufügen eines Werts in den Baum
    public void add(T value)
    {
        if (root == null)
            root = new TreeNode(value); // Erstelle einen neuen Wurzelknoten, wenn der Baum leer ist
        else
            root.add(value); // Füge den Wert in den bestehenden Baum ein
    }

    // Methode zum Hinzufügen mehrerer Werte in den Baum
    public void add(T[] values)
    {
        for (T value : values) add(value); // Füge jeden Wert einzeln hinzu
    }

    public boolean contains(T value)
    {
        if (root == null)
        {
            return false;
        }
        return root.contains(value);
    }

    // Methode zur Erstellung einer String-Darstellung des gesamten Baums
    @Override
    public String toString()
    {
        String result = "";
        if (root != null) result = root.toString(); // Erstelle die String-Darstellung beginnend von der Wurzel
        return result;
    }

    // Hauptmethode zum Testen der GenericBinaryTree-Klasse
    public static void main(String[] args)
    {
        // Erstellen und Testen eines Baums mit Integer-Werten
        var intTree = new GenericBinaryTree<Integer>();
        intTree.add(Integer.valueOf(5)); // Hinzufügen von Werten
        intTree.add(3); // Autoboxing von int zu Integer
        intTree.add(new Integer[]{7, 1, 6, 4, 2}); // Hinzufügen mehrerer Werte
        System.out.println(intTree); // Ausgabe des Baums
        // Erstellen und Testen eines Baums mit String-Werten
        var stringTree = new GenericBinaryTree<String>();
        stringTree.add(new String[]{"Meyer", "Mueller", "Schmidt", "Schneider", "Becker", "Hoffmann", "Koch"});
        System.out.println(stringTree); // Ausgabe des Baums
        GenericTreeVisualizer.printDot(stringTree);

        // Erstellen und Testen eines Baums mit Complex-Objekten
        var complexTree = new GenericBinaryTree<Complex>();
        Complex c1 = new Complex(5, 7);
        Complex c2 = new Complex(3, 4);
        Complex c3 = new Complex(9);
        Complex c4 = c1.add(c2);

        complexTree.add(new Complex[]{c1, c2, c3, c4});
        System.out.println(complexTree);

        System.out.println("Tree contains c2: " + complexTree.contains(c2));
        System.out.println("Tree contains new Complex(5, 7): " + complexTree.contains(new Complex(5, 7)));
        System.out.println("Tree contains new Complex(1, 1): " + complexTree.contains(new Complex(1, 1)));
    }
}
