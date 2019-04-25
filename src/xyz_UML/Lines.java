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

public class Lines extends XYZComponent { 
    private int firstChosenPortIndex;  // 0 for north, 1 for east, 2 for south, 3 for west
    private int secondChosenPortIndex;
    private String lineType = "";
    private ClassAndCaseBase firstComponent, secondComponent;
    private Point firstConnectingPoint = new Point();
    private Point secondConnectingPoint = new Point();
    private Point adjustedFirstConnectingPoint = new Point(); // store the adjusted first point onto the upper-left of line type shape
    private int[] xOffsetOfShape = { -5, 5, -5, -15 };
    private int[] yOffsetOfShape = { -15, -5, 5, -5 };
    private int[][] gene_trianglex = { { 0, 10, 5 },  // x coordinates of composition line shape
        { 0, 10, 10 },
        { 5, 10, 0 },
        { 0, 10, 0 } };
    private int[][] gene_triangley = { { 0, 0, 10 },  // y coordinates of composition line shape
        { 5, 0, 10 },
        { 0, 10, 10 },
        { 0, 5, 10 } };
    private int[] compos_dimandx = { 5, 10, 5, 0 };  // x coordinates of composition line shape
    private int[] compos_dimandy = { 0, 5, 10, 5 };  // y coordinates of composition line shape
    private JLabel drawFirstComponentPort, drawSecondComponentPort;
    private JComponent geneLineTypeShape, composLineTypeShape, lineInstance;

    public Lines(JLayeredPane canvas, String btnName, int firstx, int firsty, int secondx, int secondy,
            ClassAndCaseBase firstComponent, ClassAndCaseBase secondComponent) {
        firstChosenPortIndex = calcClosestPort(firstx, firsty, firstComponent);  // the point will be on the edge of components
        secondChosenPortIndex = calcClosestPort(secondx, secondy, secondComponent);
        lineType = btnName;
        this.firstComponent = firstComponent;
        this.secondComponent = secondComponent;

        switch (lineType) {
            case "association line":
                break;

            case "generation line":
                geneLineTypeShape = new JComponent() {
                    @Override
                    public void paint(Graphics g) {
                        Graphics2D g2 = (Graphics2D) g;
                        g2.setColor(Color.white);
                        g2.fillPolygon(gene_trianglex[firstChosenPortIndex], gene_triangley[firstChosenPortIndex], 3);
                        g2.setStroke(new BasicStroke(2f));
                        g2.setColor(Color.black);
                        g2.drawPolygon(gene_trianglex[firstChosenPortIndex], gene_triangley[firstChosenPortIndex], 3);
                    }
                };
                canvas.add(geneLineTypeShape, new Integer(2));
                break;

            case "composition line":
                composLineTypeShape = new JComponent() {
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
                canvas.add(composLineTypeShape, new Integer(2));
                break;

            default:
        }

        drawFirstComponentPort = new JLabel();
        drawFirstComponentPort.setOpaque(true);
        drawFirstComponentPort.setBackground(Color.black);
        canvas.add(drawFirstComponentPort, new Integer(0));

        drawSecondComponentPort = new JLabel();
        drawSecondComponentPort.setOpaque(true);
        drawSecondComponentPort.setBackground(Color.black);
        canvas.add(drawSecondComponentPort, new Integer(0));

        lineInstance = new JComponent() {  // draw line itself
            @Override
            public void paint(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setStroke(new BasicStroke(1.5f));
                g2.setColor(Color.black);
                if (lineType == "generation line" || lineType == "composition line") {
                    // adjusting the connecting point of generation lines and composition lines
                    g2.draw(new Line2D.Double(adjustedFirstConnectingPoint.x + 5, adjustedFirstConnectingPoint.y + 5,
                            secondConnectingPoint.x, secondConnectingPoint.y));
                } else {
                    g2.draw(new Line2D.Double(firstConnectingPoint.x, firstConnectingPoint.y,
                            secondConnectingPoint.x, secondConnectingPoint.y));
                }
            }
        };
        canvas.add(lineInstance, new Integer(0));

        paintMyComponents(canvas);
    }

    private int calcClosestPort(int x, int y, ClassAndCaseBase c) {  // find which port to connect with
        // order: north, east, south, west
        int[] xcoord = { c.xmin + (c.xmax - c.xmin) / 2, c.xmax, c.xmin + (c.xmax - c.xmin) / 2, c.xmin };
        int[] ycoord = { c.ymin, c.ymin + (c.ymax - c.ymin) / 2, c.ymax, c.ymin + (c.ymax - c.ymin) / 2 };
        int minIndex = 0;
        double min = 10000;
        for (int i = 0; i < 4; i++) {
            double dist = Math.sqrt(Math.pow(x - xcoord[i], 2) + Math.pow(y - ycoord[i], 2));
            if (dist < min) {
                min = dist;
                minIndex = i;
            }
        }
        return minIndex;
    }

    private Point getPortCoord(ClassAndCaseBase c, int portIndex) {
        int[] xcoord = { c.xmin + (c.xmax - c.xmin) / 2, c.xmax, c.xmin + (c.xmax - c.xmin) / 2, c.xmin };
        int[] ycoord = { c.ymin, c.ymin + (c.ymax - c.ymin) / 2, c.ymax, c.ymin + (c.ymax - c.ymin) / 2 };
        return new Point(xcoord[portIndex], ycoord[portIndex]);
    }

    @Override
    protected void paintMyComponents(JLayeredPane canvas) {
        firstConnectingPoint = getPortCoord(firstComponent, firstChosenPortIndex);
        secondConnectingPoint = getPortCoord(secondComponent, secondChosenPortIndex);
        if (lineType == "generation line" || lineType == "composition line") {
            adjustedFirstConnectingPoint.setLocation(firstConnectingPoint.x + xOffsetOfShape[firstChosenPortIndex],
                    firstConnectingPoint.y + yOffsetOfShape[firstChosenPortIndex]);
        } else {
            adjustedFirstConnectingPoint.setLocation(firstConnectingPoint.x, firstConnectingPoint.y);
        }

        drawFirstComponentPort.setBounds(firstConnectingPoint.x - 5, firstConnectingPoint.y - 5, 10, 10);
        canvas.moveToFront(drawFirstComponentPort);
        drawSecondComponentPort.setBounds(secondConnectingPoint.x - 5, secondConnectingPoint.y - 5, 10, 10);
        canvas.moveToFront(drawSecondComponentPort);
        if (lineType == "generation line")
            geneLineTypeShape.setBounds(adjustedFirstConnectingPoint.x, adjustedFirstConnectingPoint.y, 10, 10);
        else if (lineType == "composition line")
            composLineTypeShape.setBounds(adjustedFirstConnectingPoint.x, adjustedFirstConnectingPoint.y, 10, 10);
        lineInstance.setBounds(0, 0, 570, 520);
        canvas.moveToFront(lineInstance);

        canvas.repaint();
    }
}
