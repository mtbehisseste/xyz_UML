package xyz_UML;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

public class baseGUI {
	public baseGUI() {
		JFrame frame = new JFrame("xyz");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setSize(700, 600);
		frame.setBackground(Color.darkGray);

		Container container = frame.getContentPane();
		container.setLayout(null);
		container.setBackground(null);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		JMenu editMenu = new JMenu("Edit");
		JMenuItem editMenuGroup = new JMenuItem("Group");
		JMenuItem editMenuUngroup = new JMenuItem("Ungroup");
		editMenu.add(editMenuGroup);
		editMenu.add(editMenuUngroup);
		menuBar.add(editMenu);

		String[] buttonName = { "select", "association line", "generation line",
				"composition line", "classes", "use case" };
		String[] iconName = { "select.png", "association_line.png", "generation_line.png",
				"composition_line.png", "classes.png", "use_case.png" };
		for (int i = 0; i < main.sideButtonNum; i++) {  // add buttons
			xyzButton tmpButton = new xyzButton(buttonName[i],
					new ImageIcon(String.format("resource/%s", iconName[i])),
					20, 20 + i * 90, 70, 70);
			main.buttonList.add(tmpButton);
			container.add(tmpButton);
		}

		JPanel canvas = new JPanel();
		canvas.setBounds(110, 20, 570, 520);
		canvas.setBackground(Color.white);
		container.add(canvas);
		canvas.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				if(clickAtBlank(x, y)) 
					main.allComponents.add(addInstance(main.selectedBtnName));
			}
		});

		frame.setVisible(true);
	}

	private component addInstance(String btnName) {
		System.out.println(btnName);
		switch (btnName) {
		case "select":
			return null;
		case "association line":
			return new association_line();
		case "generation line":
			return new generation_line();
		case "composition line":
			return new composition_line();
		case "classes":
			return new classes();
		case "use case":
			return new use_case();
		default:
			return null;
		}
	}
	
	private boolean clickAtBlank(int x, int y) {  // check if mouse clicks on a blank
		for(int i=0; i<main.allComponents.size(); i++) {
			if (x >= main.allComponents.get(i).xmin && 
				x <= main.allComponents.get(i).xmax && 
				y >= main.allComponents.get(i).ymin && 
				y <= main.allComponents.get(i).ymax)
				return false;
		}
		return true;
	}
}
