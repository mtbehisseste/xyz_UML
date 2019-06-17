package shape;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import javax.swing.JComponent;

import editor.Canvas;

public class CompositionLine extends BasicLine {
    private int[] compos_dimandx = { 5, 10, 5, 0 };  // x coordinates of composition line shape
    private int[] compos_dimandy = { 0, 5, 10, 5 };  // y coordinates of composition line shape
    private int[] xOffsetOfShape = { -5, 5, -5, -15 };
    private int[] yOffsetOfShape = { -15, -5, 5, -5 };
    private JComponent lineInstance, composLineTypeShape;

    public CompositionLine(Canvas canvas, int firstx, int firsty, int secondx, int secondy,
            BasicObj firstComponent, BasicObj secondComponent) {
        super(canvas, firstx, firsty, secondx, secondy, firstComponent, secondComponent);

        lineInstance = new JComponent() {  // draw the line only
            @Override
            public void paint(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setStroke(new BasicStroke(1.5f));
                g2.setColor(Color.black);
                // adjusting the connecting point of generation lines and composition lines
                g2.draw(new Line2D.Double(adjustedFirstConnectingPoint.x + 5, adjustedFirstConnectingPoint.y + 5,
                        secondConnectingPoint.x, secondConnectingPoint.y));
            }
        };
        canvas.add(lineInstance, new Integer(0));

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
        canvas.add(composLineTypeShape, new Integer(1));
        paintMyComponents(canvas);
    }

    @Override
    public void paintMyComponents(Canvas canvas) {
        super.paintMyComponents(canvas);
        adjustedFirstConnectingPoint.setLocation(firstConnectingPoint.x + xOffsetOfShape[firstChosenPortIndex],
                firstConnectingPoint.y + yOffsetOfShape[firstChosenPortIndex]);
        composLineTypeShape.setBounds(adjustedFirstConnectingPoint.x, adjustedFirstConnectingPoint.y, 10, 10);
        lineInstance.setBounds(0, 0, canvasWidth, canvasHeight);

        canvas.repaint();
    }

}
