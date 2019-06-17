package shape;

import java.awt.Color;
import java.awt.Point;

import javax.swing.JLabel;

import editor.Canvas;
import editor.EditorFrame;

public class BasicLine extends Shape {
    protected int firstChosenPortIndex;  // 0 for north, 1 for east, 2 for south, 3 for west
    protected int secondChosenPortIndex;
    protected BasicObj firstComponent, secondComponent;
    protected Point firstConnectingPoint = new Point();
    protected Point secondConnectingPoint = new Point();
    protected Point adjustedFirstConnectingPoint = new Point(); // store the adjusted first point onto the upper-left of line type shape
    protected int canvasWidth, canvasHeight;
    private JLabel firstConnectingPort, secondConnectingPort;

    public BasicLine(Canvas canvas, int firstx, int firsty, int secondx, int secondy,
            BasicObj firstComponent, BasicObj secondComponent) {
        firstChosenPortIndex = calcClosestPort(firstx, firsty, firstComponent);  // the point will be on the edge of components
        secondChosenPortIndex = calcClosestPort(secondx, secondy, secondComponent);
        this.firstComponent = firstComponent;
        this.secondComponent = secondComponent;

        firstConnectingPort = new JLabel();
        firstConnectingPort.setOpaque(true);
        firstConnectingPort.setBackground(Color.black);
        canvas.add(firstConnectingPort, new Integer(1));

        secondConnectingPort = new JLabel();
        secondConnectingPort.setOpaque(true);
        secondConnectingPort.setBackground(Color.black);
        canvas.add(secondConnectingPort, new Integer(1));
    }

    private int calcClosestPort(int x, int y, BasicObj c) {  // find which port to connect with
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

    private Point getPortCoord(BasicObj c, int portIndex) {
        int[] xcoord = { c.xmin + (c.xmax - c.xmin) / 2, c.xmax, c.xmin + (c.xmax - c.xmin) / 2, c.xmin };
        int[] ycoord = { c.ymin, c.ymin + (c.ymax - c.ymin) / 2, c.ymax, c.ymin + (c.ymax - c.ymin) / 2 };
        return new Point(xcoord[portIndex], ycoord[portIndex]);
    }

    @Override
    public void paintMyComponents(Canvas canvas) {
        firstConnectingPoint = getPortCoord(firstComponent, firstChosenPortIndex);
        secondConnectingPoint = getPortCoord(secondComponent, secondChosenPortIndex);
        firstConnectingPort.setBounds(firstConnectingPoint.x - 5, firstConnectingPoint.y - 5, 10, 10);
        secondConnectingPort.setBounds(secondConnectingPoint.x - 5, secondConnectingPoint.y - 5, 10, 10);

        canvasWidth = 200 + 20 + (70 + 20) * EditorFrame.getSideButtonNumber();
        canvasHeight = 20 + (70 + 20) * EditorFrame.getSideButtonNumber();
    }
}
