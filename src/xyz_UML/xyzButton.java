package xyz_UML;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class xyzButton extends JButton {
	public xyzButton(String buttonName,
			ImageIcon icon,
			int x,
			int y,
			int width,
			int height) {
		super();
		this.setBounds(x, y, width, height);
		this.setIcon(icon);
		this.setPreferredSize(new Dimension(40, 40)); // size of the buttons
		this.setContentAreaFilled(true);
		this.setOpaque(true);
		this.setBorderPainted(false);
		this.setBackground(Color.white);

		this.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetButtonColor();
				xyzButton.this.setBackground(Color.lightGray);
			}
		});
	}

	public component addInstance(xyzButton btn) {
		String btnName = btn.getText();
		switch (btnName) {
		case "select":
			return null;
		case "association_line":
			return new association_line();
		case "generation_line":
			return new generation_line();
		case "composition_line":
			return new composition_line();
		case "classes":
			return new classes();
		case "use_case":
			return new use_case();
		default:
			return null;
		}
	}

	public void resetButtonColor() {
		for (int i = 0; i < main.sideButtonNum; i++) {
			main.buttonList.get(i).setBackground(Color.white);
		}
	}
}
