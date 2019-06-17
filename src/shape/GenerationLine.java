package shape;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import javax.swing.JComponent;

import editor.Canvas;

public class GenerationLine extends BasicLine {
    private int[][] gene_trianglex = { { 0, 10, 5 },  // x coordinates of composition line shape
        { 0, 10, 10 },
        { 5, 10, 0 },
        { 0, 10, 0 } };
    private int[][] gene_triangley = { { 0, 0, 10 },  // y coordinates of composition line shape
        { 5, 0, 10 },
        { 0, 10, 10 },
        { 0, 5, 10 } };
    private int[] xOffsetOfShape = { -5, 5, -5, -15 };
    private int[] yOffsetOfShape = { -15, -5, 5, -5 };
    private JComponent lineInstance, geneLineTypeShape;

    public GenerationLine(Canvas canvas, int firstx, int firsty, int secondx, int secondy,
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
        canvas.add(geneLineTypeShape, new Integer(1));
        paintMyComponents(canvas);
    }

    @Override
    public void paintMyComponents(Canvas canvas) {
        super.paintMyComponents(canvas);
        adjustedFirstConnectingPoint.setLocation(firstConnectingPoint.x + xOffsetOfShape[firstChosenPortIndex],
                firstConnectingPoint.y + yOffsetOfShape[firstChosenPortIndex]);
        geneLineTypeShape.setBounds(adjustedFirstConnectingPoint.x, adjustedFirstConnectingPoint.y, 10, 10);
        lineInstance.setBounds(0, 0, canvasWidth, canvasHeight);

        canvas.repaint();
    }

}
