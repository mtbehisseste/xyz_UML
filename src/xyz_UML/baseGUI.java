package xyz_UML;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.*;

public class baseGUI {
    public static int sideButtonNum = 6;
    public int currentLayer = 0;
    public static ArrayList<xyzButton> buttonList = new ArrayList<xyzButton>(sideButtonNum);
    public static String selectedBtnName = "";
    private ArrayList<classAndCaseBase> classCaseComponents = new ArrayList<classAndCaseBase>();
    private ArrayList<classAndCaseBase> selectedGroupComponents = new ArrayList<classAndCaseBase>();
    private classAndCaseBase selectedSingleComponent = new classAndCaseBase();
    private classAndCaseBase pressedComponent = new classAndCaseBase();
    private JLayeredPane canvas = new JLayeredPane();
    private int mousePressX, mousePressY;
    private boolean isDrawingLine = false;

    public baseGUI() {
        JFrame frame = new JFrame("xyz");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(700, 600);
        frame.getContentPane().setBackground(Color.darkGray);
        frame.setLayout(null);

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
            frame.add(tmpButton);
        }

        canvas.setLayout(null);
        canvas.setOpaque(true);
        canvas.setBounds(110, 20, 570, 520);
        canvas.setBackground(Color.white);
        frame.add(canvas);
        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {  // create new use_case or class
                clickAction(e.getX(), e.getY());
            }

            @Override
            public void mousePressed(MouseEvent e) {
                pressAction(e.getX(), e.getY());
            }

            @Override
            public void mouseReleased(MouseEvent e) {  // create lines
                releaseAction(e.getX(), e.getY());
            }
        });
//		canvas.addMouseMotionListener(new MouseAdapter() {
//			@Override
//			public void mouseDragged(MouseEvent e) {
//				draggedPoints.add(e.getPoint());
//				dragAction(e, e.getX(), e.getY());
//			}
//		});

        frame.setVisible(true);
    }

    private void clickAction(int x, int y) {
        if (selectedBtnName == "")
            return;

        if (selectedBtnName == "select") {
            classAndCaseBase currentClickedOnComponent = checkIfOnComponent(x, y);
            hideAllPorts();  // hide current showing ports
            
            if (currentClickedOnComponent == null) {  // blank
                return;
            } else {
                currentClickedOnComponent.showPorts(this.canvas);
                selectedSingleComponent = currentClickedOnComponent;
                return;
            }
        } else {  // click on blank, add a component
            classCaseComponents.add(addClassCase(this.canvas, selectedBtnName, x, y));
        }
    }

    private void pressAction(int x, int y) {
        classAndCaseBase tmpPressedComponent = checkIfOnComponent(x, y);

        if (selectedBtnName == "association line" ||
                selectedBtnName == "generation line" ||
                selectedBtnName == "composition line") {
            if (tmpPressedComponent == null)
                return;
            else
                pressedComponent = tmpPressedComponent;
        } else if (selectedBtnName == "select") {
            hideAllPorts();
            
            if (tmpPressedComponent != null)  // click on components
                return;
            // showDragRectangle
        }

        mousePressX = x;
        mousePressY = y;
        isDrawingLine = true;
    }

    private void releaseAction(int x, int y) {
        if (selectedBtnName == "association line" ||
                selectedBtnName == "generation line" ||
                selectedBtnName == "composition line") {
            if (isDrawingLine) {
                classAndCaseBase releasedComponent = checkIfOnComponent(x, y);
                if (releasedComponent == null)  // release at blank
                    return;
                if (releasedComponent == pressedComponent)
                    return;
                else {
                    new lines(this.canvas, selectedBtnName, mousePressX, mousePressY, x, y, pressedComponent,
                            releasedComponent);
                }
            }
        } else if (selectedBtnName == "select") {
            for (int i = 0; i < classCaseComponents.size(); i++) {
                if (mousePressX <= classCaseComponents.get(i).xmin &&
                        mousePressY <= classCaseComponents.get(i).ymin &&
                        x >= classCaseComponents.get(i).xmax &&
                        y >= classCaseComponents.get(i).ymax) {
                    classCaseComponents.get(i).showPorts(canvas);
                    selectedGroupComponents.add(classCaseComponents.get(i));
                }
            }
        }
        
        isDrawingLine = false;
    }

    private classAndCaseBase checkIfOnComponent(int x, int y) {  // check if (x,y) is within any component
        for (int i = 0; i < classCaseComponents.size(); i++) {
            if (x >= classCaseComponents.get(i).xmin &&
                    x <= classCaseComponents.get(i).xmax &&
                    y >= classCaseComponents.get(i).ymin &&
                    y <= classCaseComponents.get(i).ymax) {
                return classCaseComponents.get(i);
            }
        }
        return null;
    }

    private classAndCaseBase addClassCase(JLayeredPane canvas, String btnName, int x, int y) {
        switch (btnName) {
            case "classes":
                return new classes(canvas, x, y);
            case "use case":
                return new use_case(canvas, x, y);
            default:
                return null;
        }
    }
    
    private void hideAllPorts() {
        selectedSingleComponent.hidePorts(canvas);
        for(int i=0; i<selectedGroupComponents.size(); i++)
            selectedGroupComponents.get(i).hidePorts(canvas);
    }

//	private void dragAction(MouseEvent e, int x, int y) {
//		if (selectedBtnName == "association line" ||
//				selectedBtnName == "generation line" ||
//				selectedBtnName == "composition line") {
//			showDragLine(e, x, y);
//		} else if (selectedBtnName == "select") {
//			// showDragRectangle
//		}
//	}

//	private void showDragLine(MouseEvent e, int x, int y) {
//		JPanel mouseDragLine = new JPanel() {
//			@Override
//			public void paint(Graphics g) {
//				super.paint(g);
//				g.setColor(Color.black);
//				for(int i=0; i<draggedPoints.size(); i++) {					
//					g.drawOval(draggedPoints.get(i).x, draggedPoints.get(i).y, 1, 1);
//				}
//			}
//		};
//		mouseDragLine.setBounds(0, 0, 570, 520);
//		canvas.add(mouseDragLine);
//		canvas.repaint();
//	}

}
