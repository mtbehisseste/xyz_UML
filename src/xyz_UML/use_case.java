package xyz_UML;

import javax.swing.JLayeredPane;

public class use_case extends classAndCaseBase {
	public use_case(JLayeredPane canvas, int x, int y) {
		super.xmin = x;
		super.ymin = y;
		super.xmax = x + 110;
		super.ymax = y + 50;

		OvalShape oval = new OvalShape(0, 0, 110, 50);  // draw oval at (0,0) relative to the oval JPanel
		oval.setBounds(x, y, 110, 50);  // set this oval JPanel to where the mouse is
		canvas.add(oval);
		canvas.repaint();
	}
}
