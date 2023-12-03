import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/*Treść zadania:
Stwórz prosty interfejs z polem tekstowym. Dodaj obsługę zdarzeń klawiatury (KeyListener), aby:

Po naciśnięciu klawisza "A" tekst zmieniał kolor na czerwony.
Po naciśnięciu klawisza "B" tekst zmieniał kolor na niebieski.
Po naciśnięciu klawisza "C" wyczyść pole tekstowe.

Haczyk: pole tekstowe jest polem edycyjnym, więc jeśli jesteśmy w nim to litery mają swoje normalne znaczenie.
 */

public class Zadanie3 extends JFrame {
    private JTextField textField;

    public Zadanie3() {
        setTitle("Zadanie3");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Tworzymy pole tekstowe
        textField = new JTextField();
        textField.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                char keyChar = e.getKeyChar();

                if (keyChar == 'A' || keyChar == 'a') {
                    textField.setForeground(Color.RED);
                } else if (keyChar == 'B' || keyChar == 'b') {
                    textField.setForeground(Color.BLUE);
                } else if (keyChar == 'C' || keyChar == 'c') {
                    textField.setText("");
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {
                // Ignorujemy keyTyped
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // Ignorujemy keyReleased
            }
        });

        textField.setText("Hello World!!!");

        // Ustawiamy kolor, czcionkę i wielkość tekstu
        textField.setForeground(Color.BLACK);
        textField.setFont(new Font("Monotype Corsiva", Font.PLAIN, 24));

        add(textField);

        // Ustawiamy lokalizację ramki na środku ekranu
        setLocationRelativeTo(null);

        // Ustawiamy ramkę jako widoczną
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Zadanie3::new);
    }
}
