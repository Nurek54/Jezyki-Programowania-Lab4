import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/*Treść zadania:
Stwórz interfejs z obszarem rysowania (np. JPanel). Dodaj obsługę zdarzeń myszy tak, aby:

Po najechaniu kursorem na obszar rysowania, wyświetlano informację w panelu bocznym.
Po opuszczeniu obszaru rysowania kursorem, wyświetlano inną informację w panelu bocznym.
Po naciśnięciu i zwolnieniu przycisku myszy, wyświetlano komunikat w panelu bocznym.
Po użyciu kółka myszy, zmieniano rozmiar wybranej figury na obszarze rysowania.
 */

public class Zadanie5 extends JFrame {

    private DrawingPanel drawingPanel;
    private InfoPanel infoPanel;

    public Zadanie5() {
        setTitle("Zadanie5");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        drawingPanel = new DrawingPanel();
        infoPanel = new InfoPanel();

        add(drawingPanel.getPanel(), BorderLayout.CENTER);
        add(infoPanel.getPanel(), BorderLayout.EAST);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private class DrawingPanel {
        private JPanel panel;
        private Color currentColor = Color.BLACK;
        private int squareSize = 100;

        public DrawingPanel() {
            panel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.setColor(currentColor);
                    g.fillRect(50, 50, squareSize, squareSize);
                }
            };
            panel.setPreferredSize(new Dimension(400, 400));
            panel.setBackground(Color.WHITE);
            panel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    updateInfo("Najechano kursorem na obszar rysowania");
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    updateInfo("Opuszczono obszar rysowania");
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    updateInfo("Naciśnięto przycisk myszy");
                    if (e.getButton() == MouseEvent.BUTTON1) { // Lewy przycisk myszy
                        currentColor = Color.RED;
                    } else if (e.getButton() == MouseEvent.BUTTON3) { // Prawy przycisk myszy
                        currentColor = Color.BLUE;
                    }
                    panel.repaint();
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    updateInfo("Zwolniono przycisk myszy");
                }
            });
            panel.addMouseWheelListener(e -> {
                squareSize = Math.max(10, squareSize + e.getWheelRotation() * 5);
                panel.repaint();
            });
        }

        public JPanel getPanel() {
            return panel;
        }

        private void updateInfo(String message) {
            infoPanel.setText(message);
        }
    }

    private class InfoPanel {
        private JLabel label;
        private JPanel panel;

        public InfoPanel() {
            label = new JLabel("Informacje");
            panel = new JPanel(new GridBagLayout());
            panel.setPreferredSize(new Dimension(200, 400));

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weighty = 1;
            panel.add(label, gbc);
        }

        public JPanel getPanel() {
            return panel;
        }

        public void setText(String message) {
            label.setText(message);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Zadanie5());
    }
}
