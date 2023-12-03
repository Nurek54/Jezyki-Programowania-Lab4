import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/*Treść zadania:
Zaprojektuj interfejs Swing z polem tekstowym.
Po kliknięciu myszą w to pole, aplikacja powinna wyświetlić współrzędne kliknięcia w konsoli.
 */

public class Zadanie1 extends JFrame {

    public Zadanie1() {
        super("Zadanie1");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTextField poleTekstowe = new JTextField();
        poleTekstowe.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                System.out.println("Kliknięcie na współrzędnych: (" + x + ", " + y + ")");
            }
        });

        getContentPane().add(poleTekstowe);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Zadanie1();
        });
    }
}
