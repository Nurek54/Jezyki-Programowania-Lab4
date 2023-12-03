import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/*Treść zadania:
Stwórz interfejs z obszarem rysowania (np. JPanel) i narysuj kilka prostych figur.
Dodaj obsługę zdarzeń myszy (MouseListener),
tak aby po kliknięciu myszą na figurę można było ją przesunąć.
Po kliknięciu na figurę, przesuwaj ją w prawo lub w dół, w zależności od miejsca kliknięcia,
zmieniając jej położenie za pomocą timer'a (np. javax.swing.Timer).
 */

interface DrawingArea {
    void draw(Graphics g);
}

class Square implements DrawingArea {
    private int x, y, size;

    public Square(int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(x, y, size, size);
    }

    public void moveRight() {
        x += 10;
        if (x > 300) {
            // Przesunięcie za granicę, zaczynamy od nowa
            x = 0;
        }
    }

    public void moveDown() {
        y += 10;
        if (y > 300) {
            // Przesunięcie za granicę, zaczynamy od nowa
            y = 0;
        }
    }

    public boolean containsPoint(int mouseX, int mouseY) {
        return mouseX >= x && mouseX <= x + size && mouseY >= y && mouseY <= y + size;
    }
}

class DrawingPanel extends JPanel implements MouseListener, ActionListener {
    private Square square;
    private Timer timer;

    public DrawingPanel() {
        square = new Square(50, 50, 50);
        timer = new Timer(50, this);
        addMouseListener(this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        square.draw(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (square.containsPoint(e.getX(), e.getY())) {
            // Kliknięcie na kwadrat, zaczynamy przesuwanie
            timer.restart();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        square.moveRight();
        square.moveDown();
        repaint();
    }

    // Pozostałe metody interfejsu MouseListener
    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}

public class Zadanie4 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Drawing Program");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(300, 300);
            frame.add(new DrawingPanel());
            frame.setVisible(true);
        });
    }
}
