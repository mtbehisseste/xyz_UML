package xyz_UML;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Line2D;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class lines extends Component {
    public lines(JLayeredPane canvas, String btnName, int firstx, int firsty, int secondx, int secondy,
            classAndCaseBase firstComponent, classAndCaseBase secondComponent) {
        Point firstConnectingPoint = calcClosestPort(firstx, firsty, firstComponent);
        Point secondConnectingPoint = calcClosestPort(secondx, secondy, secondComponent);
        JPanel l = new JPanel() {
            @Override
            public void paint(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setStroke(new BasicStroke(1.5f));
                g2.setColor(Color.black);
                g2.draw(new Line2D.Double(firstConnectingPoint.x, firstConnectingPoint.y,
                        secondConnectingPoint.x, secondConnectingPoint.y));
            }
        };
        l.setBounds(0, 0, 570, 520);
        canvas.add(l);
        canvas.repaint();

        switch (btnName) {
            case "association line":

            case "generation line":

            case "composition line":

            default:
        }

    }

    private Point calcClosestPort(int x, int y, classAndCaseBase c) {  // find which port to connect with
        // order: north, east, south, west
        int[] xcoord = { c.xmin + (c.xmax - c.xmin) / 2, c.xmax, c.xmin + (c.xmax - c.xmin) / 2, c.xmin };
        int[] ycoord = { c.ymin, c.ymin + (c.ymax - c.ymin) / 2, c.ymax, c.ymin + (c.ymax - c.ymin) / 2 };
        double min = 10000;
        Point minPt = new Point();
        for (int i = 0; i < 4; i++) {
            double dist = Math.sqrt(Math.pow(x - xcoord[i], 2) + Math.pow(y - ycoord[i], 2));
            if (dist < min) {
                min = dist;
                minPt.setLocation(xcoord[i], ycoord[i]);
            }
        }
        return minPt;
    }
}
