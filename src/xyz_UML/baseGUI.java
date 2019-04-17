package xyz_UML;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.*;

public class baseGUI {
	public static int sideButtonNum = 6;
	public static ArrayList<xyzButton> buttonList = new ArrayList<xyzButton>(sideButtonNum);
	public ArrayList<classAndCaseBase> classCaseComponents = new ArrayList<classAndCaseBase>();
	private JPanel canvas = new JPanel();
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

		canvas.setLayout(null);
		canvas.setBounds(110, 20, 570, 520);
		canvas.setBackground(Color.white);
		container.add(canvas);
		canvas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {  // create new use_case or class
				clickAction(e.getX(), e.getY());
			}

			@Override
			public void mousePressed(MouseEvent e) {  // create lines
				
			}
		});

		frame.setVisible(true);
	}

	private classAndCaseBase addClassCase(JPanel canvas, String btnName, int x, int y) {
		System.out.println(btnName);
		switch (btnName) {
//		case "select":
//			return null;
//		case "association line":
//			return new association_line();
//		case "generation line":
//			return new generation_line();
//		case "composition line":
//			return new composition_line();
		case "classes":
			return new classes(canvas, x, y);
		case "use case":
			return new use_case(canvas, x, y);
		default:
			return null;
		}
	}

	private void clickAction(int x, int y) {
		for (int i = 0; i < classCaseComponents.size(); i++) {
			if (classCaseComponents.get(i) != null &&
					x >= classCaseComponents.get(i).xmin &&
					x <= classCaseComponents.get(i).xmax &&
					y >= classCaseComponents.get(i).ymin &&
					y <= classCaseComponents.get(i).ymax) {
				hideAllPorts();
				classCaseComponents.get(i).showPorts(this.canvas);
				return;
			}
		}

		// clicked at blank
		classCaseComponents.add(addClassCase(this.canvas, selectedBtnName, x, y));
	}
	
	private void hideAllPorts() {
		for(int i=0; i<classCaseComponents.size(); i++) {
			classCaseComponents.get(i).hidePorts(this.canvas);
		}
	}
}
