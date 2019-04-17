package xyz_UML;

import java.awt.Color;
import java.awt.Graphics;

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
        super.paint(g);
        g.setColor(Color.lightGray);
        g.fillOval(x, y, width, height);  // internal of the oval
        g.setColor(Color.black);
        g.drawOval(x, y, width, height);  // border of the oval
    }
}
