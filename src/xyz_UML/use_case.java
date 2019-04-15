package xyz_UML;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class use_case extends classAndCaseBase {
	public use_case(JPanel canvas, int x, int y) {
		super.xmin = x - 55;
		super.ymin = y - 25;
		super.xmax = x + 55;
		super.ymax = y + 25;

		OvalShape oval = new OvalShape(x - 55, y - 25, 110, 50);
		oval.setBounds(0, 0, 570, 520);
		canvas.add(oval);
		canvas.repaint();
	}
}
