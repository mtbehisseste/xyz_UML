package xyz_UML;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class classAndCaseBase extends component {
	protected ArrayList<JLabel> portsArray = new ArrayList<JLabel>();

	protected void showPorts(JPanel canvas) {
		// order: north, east, south, west
		int[] xoffset = { (super.xmax - super.xmin) / 2 - 5,
				(super.xmax - super.xmin),
				(super.xmax - super.xmin) / 2 - 5,
				-10 };
		int[] yoffset = { -10,
				(super.ymax - super.ymin) / 2 - 5,
				(super.ymax - super.ymin),
				(super.ymax - super.ymin) / 2 - 5 };

		for (int i = 0; i < 4; i++) {
			JLabel tmpPortLabel = new JLabel();
			tmpPortLabel.setBounds(super.xmin + xoffset[i], super.ymin + yoffset[i], 10, 10);
			tmpPortLabel.setOpaque(true);
			tmpPortLabel.setBackground(Color.black);
			portsArray.add(tmpPortLabel);
			canvas.add(tmpPortLabel);
		}
		canvas.repaint();
	}

	protected void hidePorts(JPanel canvas) {
		System.out.println(this.portsArray.size());
		for (int i = 0; i < this.portsArray.size(); i++) {
			System.out.println(i);
			canvas.remove(this.portsArray.get(i));
		}
		canvas.repaint();
		portsArray.clear();
	}

}
