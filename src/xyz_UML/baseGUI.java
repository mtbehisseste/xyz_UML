package xyz_UML;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.*;

public class baseGUI {
	public static int sideButtonNum = 6;
	public static ArrayList<xyzButton> buttonList = new ArrayList<xyzButton>(sideButtonNum);
	public ArrayList<component> allComponents = new ArrayList<component>();
	public static String selectedBtnName;
	
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
		for (int i = 0; i < sideButtonNum; i++) {  // add buttons
			xyzButton tmpButton = new xyzButton(buttonName[i],
					new ImageIcon(String.format("resource/%s", iconName[i])),
					20, 20 + i * 90, 70, 70);
			buttonList.add(tmpButton);
			container.add(tmpButton);
		}

		JPanel canvas = new JPanel();
		canvas.setLayout(null);
		canvas.setBounds(110, 20, 570, 520);
		canvas.setBackground(Color.white);
		container.add(canvas);
		canvas.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(clickAtBlank(e.getX(), e.getY())) 
					allComponents.add(addInstance(canvas, selectedBtnName, e.getX(), e.getY()));
			}
		});
		
		frame.setVisible(true);

	}

	private component addInstance(JPanel canvas, String btnName, int x, int y) {
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
			return new classes(canvas, x, y);
		case "use case":
			return new use_case();
		default:
			return null;
		}
	}
	
	private boolean clickAtBlank(int x, int y) {  // check if mouse clicks on a blank
		for(int i=0; i<allComponents.size(); i++) {
			if (allComponents.get(i) != null &&
				x >= allComponents.get(i).xmin && 
				x <= allComponents.get(i).xmax && 
				y >= allComponents.get(i).ymin && 
				y <= allComponents.get(i).ymax)
				return false;
		}
		return true;
	}
}
