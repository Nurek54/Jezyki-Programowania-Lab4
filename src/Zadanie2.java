import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

/*Treść zadania:
Stwórz prosty interfejs z obszarem rysowania (np. JPanel).
Narysuj kilka prostych figur (np. kwadrat, koło). Dodaj obsługę zdarzeń myszy (MouseListener),
tak aby po kliknięciu myszą na figurę można było ją przesunąć.
 */

class FigureManipulation extends JPanel implements MouseListener, MouseMotionListener {
    private Shape selectedShape;
    private int offsetX, offsetY;

    private final List<Shape> shapes;

    public FigureManipulation() {
        shapes = new ArrayList<>();
        shapes.add(new Rectangle2D.Double(50, 50, 100, 100));
        shapes.add(new Ellipse2D.Double(200, 50, 150, 150));

        addMouseListener(this);
        addMouseMotionListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        for (Shape shape : shapes) {
            setShapeColor(g2d, shape);
            g2d.draw(shape);
            g2d.fill(shape);
        }
    }

    private void setShapeColor(Graphics2D g2d, Shape shape) {
        if (shape instanceof Rectangle2D) {
            g2d.setColor(Color.RED);
        } else if (shape instanceof Ellipse2D) {
            g2d.setColor(Color.BLUE);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Point clickPoint = e.getPoint();

        for (Shape shape : shapes) {
            if (shape.contains(clickPoint)) {
                selectedShape = shape;
                offsetX = clickPoint.x - (int) shape.getBounds2D().getX();
                offsetY = clickPoint.y - (int) shape.getBounds2D().getY();
                break;
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        selectedShape = null;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        moveSelectedShape(e.getPoint());
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    private void moveSelectedShape(Point point) {
        if (selectedShape != null) {
            int x = point.x - offsetX;
            int y = point.y - offsetY;

            if (selectedShape instanceof Rectangle2D) {
                ((Rectangle2D) selectedShape).setRect(x, y, selectedShape.getBounds2D().getWidth(), selectedShape.getBounds2D().getHeight());
            } else if (selectedShape instanceof Ellipse2D) {
                ((Ellipse2D) selectedShape).setFrame(x, y, selectedShape.getBounds2D().getWidth(), selectedShape.getBounds2D().getHeight());
            }

            repaint();
        }
    }
}

public class Zadanie2 {
    private void run() {
        JFrame frame = new JFrame("Zadanie2");
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        FigureManipulation figureManipulationPanel = new FigureManipulation();
        frame.add(figureManipulationPanel);

        // Ustawienie okna na środku ekranu
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - frame.getWidth()) / 2;
        int y = (screenSize.height - frame.getHeight()) / 2;
        frame.setLocation(x, y);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Zadanie2 zadanie = new Zadanie2();
            zadanie.run();
        });
    }
}
