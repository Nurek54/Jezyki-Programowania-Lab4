import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

/*Treść zadania:
Stwórz interfejs z obszarem rysowania (np. JPanel) i narysuj kilka prostych figur.
Dodaj obsługę zdarzeń myszy (MouseListener),
tak aby po kliknięciu myszą na figurę można było ją przesunąć.
Po kliknięciu na figurę, przesuwaj ją w prawo lub w dół, w zależności od miejsca kliknięcia,
zmieniając jej położenie za pomocą timer'a (np. javax.swing.Timer).
 */

//    NIE DZIAŁA POPRAWNIE !!!

/*interface DrawingArea {
    void draw(Graphics g);
}

class Figure implements DrawingArea {
    public Shape shape;
    private Color color;

    public Figure(Shape shape, Color color) {
        this.shape = shape;
        this.color = color;
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);
        g2d.draw(shape);
        g2d.fill(shape);
    }

    public void move(int targetX, int targetY) {
        if (shape instanceof Rectangle2D) {
            Rectangle2D rectangle = (Rectangle2D) shape;
            double dx = targetX - rectangle.getX();
            double dy = targetY - rectangle.getY();
            rectangle.setRect(targetX, targetY, rectangle.getWidth(), rectangle.getHeight());
        }
    }

    public boolean containsPoint(int mouseX, int mouseY) {
        return shape.contains(mouseX, mouseY);
    }
}

class DrawingPanel extends JPanel implements MouseListener, ActionListener {
    private List<Figure> figures;
    private Figure currentFigure;
    private Timer timer;

    public DrawingPanel() {
        figures = new ArrayList<>();
        timer = new Timer(50, this);
        addMouseListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Figure figure : figures) {
            figure.draw(g);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (currentFigure == null) {
            // Kliknięcie myszą bez zaznaczonej figury, tworzymy nową
            currentFigure = new Figure(new Rectangle2D.Double(e.getX(), e.getY(), 50, 50), Color.BLUE);
            repaint();
        } else {
            // Kliknięcie myszą z zaznaczoną figurą, ustawiamy jej nową pozycję
            currentFigure.move(e.getX(), e.getY());
            figures.add(currentFigure);
            currentFigure = null;
            timer.start();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Przesuwanie figury w dół co 50 ms
        for (Figure figure : figures) {
            figure.move((int) figure.shape.getBounds2D().getX(), (int) figure.shape.getBounds2D().getY() + 5);
        }
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
*/