package xyz_UML;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;

public class classAndCaseBase extends component {
	protected ArrayList<JLabel> portsArray = new ArrayList<JLabel>();

	protected void showPorts(JLayeredPane canvas) {
		// order: north, east, south, west
		int[] xoffset = { (super.xmax - super.xmin) / 2 - 5,
				(super.xmax - super.xmin) - 5,
				(super.xmax - super.xmin) / 2 - 5,
				-5 };
		int[] yoffset = { -5,
				(super.ymax - super.ymin) / 2 - 5,
				(super.ymax - super.ymin) - 5,
				(super.ymax - super.ymin) / 2 - 5 };

		for (int i = 0; i < 4; i++) {
			JLabel tmpPortLabel = new JLabel();
			tmpPortLabel.setBounds(super.xmin + xoffset[i], super.ymin + yoffset[i], 10, 10);
			tmpPortLabel.setOpaque(true);
			tmpPortLabel.setBackground(Color.black);
			portsArray.add(tmpPortLabel);
			canvas.add(tmpPortLabel, new Integer(3));
		}
		canvas.repaint();
	}

	protected void hidePorts(JLayeredPane canvas) {
		for (int i = 0; i < this.portsArray.size(); i++)
			canvas.remove(this.portsArray.get(i));
		canvas.repaint();
		portsArray.clear();
	}

}
