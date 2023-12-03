import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/*Treść zadania:
Stwórz interfejs z obszarem rysowania (np. JPanel) i narysuj kilka prostych figur.
Dodaj obsługę zdarzeń myszy (MouseListener), tak aby po kliknięciu myszą na figurę można było ją przesunąć.
Po kliknięciu na figurę, przesuwaj ją w prawo lub w dół,w zależności od miejsca kliknięcia,
zmieniając jej położenie za pomocą timer'a (np. javax.swing.Timer).
 */

public class Zadanie4 extends JPanel {

    private static final int SQUARE_SIZE = 80;
    private static final int CIRCLE_SIZE = 80;
    private static final int TRIANGLE_SIZE = 80;

    private int squareX = 100;
    private int squareY = 100;
    private boolean squareMoving = false;
    private boolean squareMoveRight = true;
    private boolean squareMoveDown = true;

    private int circleX = 300;
    private int circleY = 100;
    private boolean circleMoving = false;
    private boolean circleMoveRight = true;
    private boolean circleMoveDown = true;

    private int triangleX = 500;
    private int triangleY = 100;
    private boolean triangleMoving = false;
    private boolean triangleMoveRight = true;
    private boolean triangleMoveDown = true;

    private Timer timer;

    private boolean squareSelected = false;
    private boolean circleSelected = false;
    private boolean triangleSelected = false;

    private int destinationX;
    private int destinationY;

    public Zadanie4() {
        setPreferredSize(new Dimension(1920, 1080));
        setBackground(Color.WHITE);
        addMouseListener(new MyMouseListener());

        timer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveShapes();
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.RED);
        g.fillRect(squareX, squareY, SQUARE_SIZE, SQUARE_SIZE);

        g.setColor(Color.ORANGE);
        g.fillOval(circleX, circleY, CIRCLE_SIZE, CIRCLE_SIZE);

        int[] xPoints = {triangleX, triangleX + TRIANGLE_SIZE, triangleX + TRIANGLE_SIZE / 2};
        int[] yPoints = {triangleY, triangleY, triangleY - TRIANGLE_SIZE};
        g.setColor(Color.YELLOW);
        g.fillPolygon(xPoints, yPoints, 3);
    }

    private void moveShapes() {
        if (squareMoving) {
            if (squareMoveRight) {
                squareX += 5;
                if (squareX > getWidth()) {
                    squareX = -SQUARE_SIZE;
                }
            } else {
                squareY += 5;
                if (squareY > getHeight()) {
                    squareY = -SQUARE_SIZE;
                }
            }
        }

        if (circleMoving) {
            if (circleMoveDown) {
                circleY += 5;
                if (circleY > getHeight()) {
                    circleY = -CIRCLE_SIZE;
                }
            } else {
                circleX += 5;
                if (circleX > getWidth()) {
                    circleX = -CIRCLE_SIZE;
                }
            }
        }

        if (triangleMoving) {
            if (triangleMoveRight) {
                triangleX += 5;
                if (triangleX > getWidth()) {
                    triangleX = -TRIANGLE_SIZE;
                }
            } else {
                triangleY += 5;
                if (triangleY > getHeight()) {
                    triangleY = -TRIANGLE_SIZE;
                }
            }
        }
    }

    private class MyMouseListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (!squareSelected && e.getX() >= squareX && e.getX() <= squareX + SQUARE_SIZE &&
                    e.getY() >= squareY && e.getY() <= squareY + SQUARE_SIZE) {
                if (squareMoving) {
                    squareMoving = false;
                } else {
                    squareSelected = true;
                    circleSelected = false;
                    triangleSelected = false;
                }
            } else if (!circleSelected && e.getX() >= circleX && e.getX() <= circleX + CIRCLE_SIZE &&
                    e.getY() >= circleY && e.getY() <= circleY + CIRCLE_SIZE) {
                if (circleMoving) {
                    circleMoving = false;
                } else {
                    squareSelected = false;
                    circleSelected = true;
                    triangleSelected = false;
                }
            } else if (!triangleSelected && e.getX() >= triangleX && e.getX() <= triangleX + TRIANGLE_SIZE &&
                    e.getY() >= triangleY - TRIANGLE_SIZE && e.getY() <= triangleY) {
                if (triangleMoving) {
                    triangleMoving = false;
                } else {
                    squareSelected = false;
                    circleSelected = false;
                    triangleSelected = true;
                }
            } else {
                if (squareSelected) {
                    squareSelected = false;
                    squareMoving = true;
                    squareMoveRight = destinationX < e.getX();
                    squareMoveDown = destinationY < e.getY();
                } else if (circleSelected) {
                    circleSelected = false;
                    circleMoving = true;
                    circleMoveRight = destinationX < e.getX();
                    circleMoveDown = destinationY < e.getY();
                } else if (triangleSelected) {
                    triangleSelected = false;
                    triangleMoving = true;
                    triangleMoveRight = destinationX < e.getX();
                    triangleMoveDown = destinationY < e.getY();
                }
            }

            destinationX = e.getX();
            destinationY = e.getY();
        }
    }

    public void run() {
        JFrame frame = new JFrame("Moving Shapes");
        frame.add(this);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        timer.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Zadanie4().run();
        });
    }
}
