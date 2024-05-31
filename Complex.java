public class Complex implements Comparable<Complex>
{
    // Die realen und imaginären Teile der komplexen Zahl sind als Konstanten definiert.
    final double real;
    final double imaginary;

    // Konstruktor, der eine komplexe Zahl mit angegebenen realen und imaginären Teilen erstellt.
    public Complex(double real, double imaginary)
    {
        this.real = real;
        this.imaginary = imaginary;
    }

    // Konstruktor, der eine komplexe Zahl mit einem angegebenen realen Teil und einem imaginären Teil von 0 erstellt.
    public Complex(double real)
    {
        this(real, 0);
    }

    // Konstruktor, der eine komplexe Zahl mit realem und imaginärem Teil von 0 erstellt.
    public Complex()
    {
        this(0, 0);
    }

    // Methode zur Berechnung des Absolutbetrags (der Länge) der komplexen Zahl.
    public double abs()
    {
        // Die Formel für den Absolutbetrag einer komplexen Zahl ist sqrt(real^2 + imaginary^2).
        return Math.sqrt(real * real + imaginary * imaginary);
    }

    // Methode zur Addition zweier komplexer Zahlen.
    public Complex add(Complex rhs)
    {
        // Eine neue komplexe Zahl wird erstellt, indem die entsprechenden Teile der beiden Zahlen addiert werden.
        return new Complex(real + rhs.real, imaginary + rhs.imaginary);
    }

    // Methode zur Multiplikation zweier komplexer Zahlen.
    public Complex multiply(Complex rhs)
    {
        // Eine neue komplexe Zahl wird erstellt, indem die reellen und imaginären Teile gemäß der Regel für komplexe Multiplikation berechnet werden.
        return new Complex(real * rhs.real - imaginary * rhs.imaginary, real * rhs.imaginary + imaginary * rhs.real);
    }

    // Methode zur Darstellung der komplexen Zahl als String.
    @Override
    public String toString()
    {
        // Gibt die komplexe Zahl im Format "real + i*imaginary" zurück.
        return real + " + i*" + imaginary;
    }

    // Implementierung compareTo-Methoden zur Vergleichung der komplexen Zahlen nach ihrem Absolutbetrag.
    @Override
    public int compareTo(Complex other)
    {
        return Double.compare(this.abs(), other.abs());
    }

    // Hauptmethode zum Testen der Complex-Klasse.
    public static void main(String[] args)
    {
        // Erstellen von komplexen Zahlen mit unterschiedlichen Konstruktoren.
        Complex c1 = new Complex(5, 7);
        Complex c2 = new Complex(3, 4);
        Complex c3 = new Complex(9);

        // Ausgabe der komplexen Zahlen.
        System.out.println(c1);
        System.out.println("Absolutbetrag c1: " + c1.abs());
        System.out.println(c2);
        System.out.println(c3);

        // Addition zweier komplexer Zahlen und Ausgabe des Ergebnisses.
        Complex c4 = c1.add(c2);    // Zwischenergebnis in c4 speichern
        System.out.println(c4);
        System.out.println(c1.add(c3)); // Direkte Ausgabe des Ergebnisses der Addition

        // Multiplikation zweier komplexer Zahlen und Ausgabe des Ergebnisses.
        System.out.println(c1.multiply(c2));
        System.out.println(c1.multiply(c3));

        // Verkettung von Methoden: Multiplikation und anschließend Berechnung des Absolutbetrags.
        System.out.println(c1.multiply(c3).abs());

        // Testen des generischen Baums mit Complex-Objekten
        var complexTree = new GenericBinaryTree<Complex>();
        complexTree.add(new Complex[]{c1, c2, c3, c4});
        System.out.println(complexTree);

        System.out.println("Tree contains c2: " + complexTree.contains(c2));
        System.out.println("Tree contains new Complex(5, 7): " + complexTree.contains(new Complex(5, 7)));
        System.out.println("Tree contains new Complex(1, 1): " + complexTree.contains(new Complex(1, 1)));
    }
}

