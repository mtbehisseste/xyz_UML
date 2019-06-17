package shape;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import javax.swing.JComponent;

import editor.Canvas;

public class AssociationLine extends BasicLine {
    private JComponent lineInstance;

    public AssociationLine(Canvas canvas, int firstx, int firsty, int secondx, int secondy,
            BasicObj firstComponent, BasicObj secondComponent) {
        super(canvas, firstx, firsty, secondx, secondy, firstComponent, secondComponent);

        lineInstance = new JComponent() {  // draw the line only
            @Override
            public void paint(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setStroke(new BasicStroke(1.5f));
                g2.setColor(Color.black);
                g2.draw(new Line2D.Double(firstConnectingPoint.x, firstConnectingPoint.y,
                        secondConnectingPoint.x, secondConnectingPoint.y));
            }
        };
        canvas.add(lineInstance, new Integer(1));

        paintMyComponents(canvas);
    }

    @Override
    public void paintMyComponents(Canvas canvas) {
        super.paintMyComponents(canvas);
//        adjustedFirstConnectingPoint.setLocation(firstConnectingPoint.x, firstConnectingPoint.y);  // TODO check if bug happens here
        lineInstance.setBounds(0, 0, canvasWidth, canvasHeight);

        canvas.repaint();
    }
}
