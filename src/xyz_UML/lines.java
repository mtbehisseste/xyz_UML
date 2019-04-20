package xyz_UML;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Line2D;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

public class lines extends Component {
    private int chosenPortIndex;  // 0 for north, 1 for east, 2 for south, 3 for west

    public lines(JLayeredPane canvas, String btnName, int firstx, int firsty, int secondx, int secondy,
            classAndCaseBase firstComponent, classAndCaseBase secondComponent) {
        Point firstConnectingPoint = calcClosestPort(firstx, firsty, firstComponent, true);
        Point secondConnectingPoint = calcClosestPort(secondx, secondy, secondComponent, false);
        Point tmpfirstConnectingPoint = new Point(); // restore the new first point on the line type

        switch (btnName) {
            case "association line":
                break;

            case "generation line":
                int[] gene_xoffset = { -5, 5, -5, -15 };
                int[] gene_yoffset = { -15, -5, 5, -5 };
                int[][] gene_dimandx = { { 0, 10, 5 },
                    { 0, 10, 10 },
                    { 5, 10, 0 },
                    { 0, 10, 0 } };
                int[][] gene_dimandy = { { 0, 0, 10 },
                    { 5, 0, 10 },
                    { 0, 10, 10 },
                    { 0, 5, 10 } };
                JComponent gene_lineType = new JComponent() {
                    @Override
                    public void paint(Graphics g) {
                        Graphics2D g2 = (Graphics2D) g;
                        g2.setColor(Color.white);
                        g2.fillPolygon(gene_dimandx[chosenPortIndex], gene_dimandy[chosenPortIndex], 3);
                        g2.setStroke(new BasicStroke(2f));
                        g2.setColor(Color.black);
                        g2.drawPolygon(gene_dimandx[chosenPortIndex], gene_dimandy[chosenPortIndex], 3);
                    }
                };
                tmpfirstConnectingPoint.setLocation(firstConnectingPoint.x + gene_xoffset[chosenPortIndex],
                        firstConnectingPoint.y + gene_yoffset[chosenPortIndex]);
                gene_lineType.setBounds(tmpfirstConnectingPoint.x, tmpfirstConnectingPoint.y, 10, 10);
                canvas.add(gene_lineType, new Integer(10));
                break;

            case "composition line":
                int[] compos_xoffset = { -5, 5, -5, -15 };
                int[] compos_yoffset = { -15, -5, 5, -5 };
                int[] compos_dimandx = { 5, 10, 5, 0 };
                int[] compos_dimandy = { 0, 5, 10, 5 };
                JComponent compos_lineType = new JComponent() {
                    @Override
                    public void paint(Graphics g) {
                        Graphics2D g2 = (Graphics2D) g;
                        g2.setColor(Color.white);
                        g2.fillPolygon(compos_dimandx, compos_dimandy, 4);
                        g2.setStroke(new BasicStroke(2f));
                        g2.setColor(Color.black);
                        g2.drawPolygon(compos_dimandx, compos_dimandy, 4);
                    }
                };
                tmpfirstConnectingPoint.setLocation(firstConnectingPoint.x + compos_xoffset[chosenPortIndex],
                        firstConnectingPoint.y + compos_yoffset[chosenPortIndex]);
                compos_lineType.setBounds(tmpfirstConnectingPoint.x, tmpfirstConnectingPoint.y, 10, 10);
                canvas.add(compos_lineType, new Integer(10));
                break;

            default:
        }

        JLabel firstComponentPort = new JLabel();
        firstComponentPort.setBounds(firstConnectingPoint.x - 5, firstConnectingPoint.y - 5, 10, 10);
        firstComponentPort.setOpaque(true);
        firstComponentPort.setBackground(Color.black);
        canvas.add(firstComponentPort, new Integer(3));

        JLabel secondComponentPort = new JLabel();
        secondComponentPort.setBounds(secondConnectingPoint.x - 5, secondConnectingPoint.y - 5, 10, 10);
        secondComponentPort.setOpaque(true);
        secondComponentPort.setBackground(Color.black);
        canvas.add(secondComponentPort, new Integer(3));

        JComponent l = new JComponent() {
            @Override
            public void paint(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setStroke(new BasicStroke(1.5f));
                g2.setColor(Color.black);
//                g2.draw(new Line2D.Double(firstConnectingPoint.x, firstConnectingPoint.y,
//                        secondConnectingPoint.x, secondConnectingPoint.y));
                g2.draw(new Line2D.Double(tmpfirstConnectingPoint.x + 5, tmpfirstConnectingPoint.y + 5,
                        secondConnectingPoint.x, secondConnectingPoint.y));
            }
        };
        l.setBounds(0, 0, 570, 520);
        canvas.add(l);

        canvas.repaint();
    }

    private Point calcClosestPort(int x, int y, classAndCaseBase c, boolean isFirstComponent) {  // find which port to
                                                                                                 // connect with
        // order: north, east, south, west
        int[] xcoord = { c.xmin + (c.xmax - c.xmin) / 2, c.xmax, c.xmin + (c.xmax - c.xmin) / 2, c.xmin };
        int[] ycoord = { c.ymin, c.ymin + (c.ymax - c.ymin) / 2, c.ymax, c.ymin + (c.ymax - c.ymin) / 2 };
        double min = 10000;
        Point minPt = new Point();
        for (int i = 0; i < 4; i++) {
            double dist = Math.sqrt(Math.pow(x - xcoord[i], 2) + Math.pow(y - ycoord[i], 2));
            if (dist < min) {
                if (isFirstComponent)
                    chosenPortIndex = i;
                min = dist;
                minPt.setLocation(xcoord[i], ycoord[i]);
            }
        }
        return minPt;
    }
}
