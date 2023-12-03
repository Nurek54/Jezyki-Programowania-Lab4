import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/*Treść zadania:
Opracuj interfejs z obszarem rysowania (np. JPanel). Dodaj obsługę zdarzeń klawiatury tak, aby:

Po naciśnięciu klawisza, zmieniał się kształt lub kolor figury na obszarze rysowania.
Po zwolnieniu klawisza, powracał do poprzedniego kształtu lub koloru.
Po użyciu klawiszy specjalnych, np. Shift lub Ctrl, zmieniały się inne właściwości wybranej figury.
 */

public class Zadanie6 extends JFrame {

    private Color currentColor = Color.BLACK;
    private int shapeType = 0; // 0 - rectangle, 1 - oval, 2 - triangle
    private int size = 100;

    public Zadanie6() {
        setTitle("Zadanie6");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setPreferredSize(new Dimension(400, 400));

        DrawingPanel drawingPanel = new DrawingPanel();
        add(drawingPanel);

        pack();
        setLocationRelativeTo(null); // Ustaw panel na środku ekranu

        addMouseListener(new MyMouseListener());
        addKeyListener(new MyKeyListener());
        setFocusable(true);
        setVisible(true);
    }

    private class DrawingPanel extends JPanel {

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(currentColor);

            int width = getWidth() / 2;
            int height = getHeight() / 2;

            if (shapeType == 0) {
                g.fillRect(width - size / 2, height - size / 2, size, size);
            } else if (shapeType == 1) {
                g.fillOval(width - size / 2, height - size / 2, size, size);
            } else if (shapeType == 2) {
                drawTriangle(g, width, height, size);
            }
        }

        private void drawTriangle(Graphics g, int x, int y, int side) {
            int[] xPoints = {x - side / 2, x, x + side / 2};
            int[] yPoints = {y + side / 2, y - side / 2, y + side / 2};
            Polygon triangle = new Polygon(xPoints, yPoints, 3);
            g.fillPolygon(triangle);
        }
    }

    public void changeShape() {
        shapeType = (shapeType + 1) % 3;
        size = 100; // Przywróć domyślny rozmiar
        repaint();
    }

    public void changeColor() {
        currentColor = new Color((int) (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random() * 256));
        repaint();
    }

    public void handleSpecialKeys(KeyEvent e) {
        if (e.isControlDown()) {
            shapeType = 1; // Koło
            currentColor = Color.YELLOW;
        } else if (e.isShiftDown()) {
            shapeType = 2; // Trójkąt
            currentColor = Color.GREEN;
        }
        size = 100; // Przywróć domyślny rozmiar
        repaint();
    }

    public void revertChanges() {
        currentColor = Color.BLACK;
        shapeType = 0;
        size = 100;
        repaint();
    }

    private class MyMouseListener extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent e) {
            changeColor();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            revertChanges();
        }
    }

    private class MyKeyListener implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {
            // Not used in this example
        }

        @Override
        public void keyPressed(KeyEvent e) {
            handleSpecialKeys(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            revertChanges();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Zadanie6();
        });
    }
}

