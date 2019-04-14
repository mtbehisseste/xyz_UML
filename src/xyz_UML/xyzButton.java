package xyz_UML;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class xyzButton extends JButton {
	private String btnName;

	public xyzButton(String buttonName,
			ImageIcon icon,
			int x,
			int y,
			int width,
			int height) {
		super();
		btnName = buttonName;

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
				xyzButton.this.setBackground(Color.gray);
				main.selectedBtnName = xyzButton.this.btnName;
			}
		});
	}

//	private component addInstance(xyzButton btn) {
//		switch (btn.btnName) {
//		case "select":
//			main.selectedBtnIndex = 0;
//			return null;
//		case "association line":
//			main.selectedBtnIndex = 1;
//			return new association_line();
//		case "generation line":
//			main.selectedBtnIndex = 2;
//			return new generation_line();
//		case "composition line":
//			main.selectedBtnIndex = 3;
//			return new composition_line();
//		case "classes":
//			main.selectedBtnIndex = 4;
//			return new classes();
//		case "use case":
//			main.selectedBtnIndex = 5;
//			return new use_case();
//		default:
//			return null;
//		}
//	}

	private void resetButtonColor() {  // reset color of all buttons when one is clicked
		for (int i = 0; i < main.sideButtonNum; i++) {
			main.buttonList.get(i).setBackground(Color.white);
		}
	}
}
