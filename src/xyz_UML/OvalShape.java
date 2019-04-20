package xyz_UML;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

public class OvalShape extends JComponent {
	private int x, y, width, height;

	public OvalShape(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	@Override
    public void paint(Graphics g) {
//        super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.lightGray);
        g2.fillOval(x, y, width, height);  // internal of the oval
        g2.setStroke(new BasicStroke(1.5f));
        g2.setColor(Color.black);
        g2.drawOval(x, y, width, height);  // border of the oval
    }
}
