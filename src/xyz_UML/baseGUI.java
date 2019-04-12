package xyz_UML;

import java.awt.*;

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
				"composition line", "class", "use case" };
		String[] iconName = { "select.png", "association_line.png", "generation_line.png",
				"composition_line.png", "classes.png", "use_case.png" };
		for (int i = 0; i < main.sideButtonNum; i++) {  // add buttons
			xyzButton tmpButton = new xyzButton(buttonName[i],
					new ImageIcon(String.format("resource/%s", iconName[i])),
					20, 20 + i * 90, 70, 70);
			main.buttonList.add(tmpButton);
			container.add(tmpButton);
		}

		Canvas canvas = new Canvas();
		canvas.setBounds(110, 20, 570, 520);
		canvas.setBackground(Color.white);
		container.add(canvas);

		frame.setVisible(true);
	}
}
