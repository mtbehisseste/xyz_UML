package xyz_UML;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.*;

public class BaseGUI {
    public static int sideButtonNum = 6;
    public static ArrayList<XYZButton> buttonList = new ArrayList<XYZButton>(sideButtonNum);
    public static String selectedBtnName = "";

    private JFrame frame = new JFrame("xyz");
    private JLayeredPane canvas = new JLayeredPane();
    private JMenuBar menuBar = new JMenuBar();
    private JMenu fileMenu = new JMenu("File");
    private JMenu editMenu = new JMenu("Edit");
//    private JMenuItem fileMenuNew = new JMenuItem("New");
    private JMenuItem editMenuGroup = new JMenuItem("Group");
    private JMenuItem editMenuUngroup = new JMenuItem("Ungroup");
    private JMenuItem editMenuChangeName = new JMenuItem("Change Object Name");

    private ArrayList<ClassAndCaseBase> classCaseComponents = new ArrayList<ClassAndCaseBase>();
    private ArrayList<ClassAndCaseBase> selectedGroupComponents = new ArrayList<ClassAndCaseBase>();
    private ArrayList<Lines> lineComponents = new ArrayList<Lines>();
    private ArrayList<ArrayList<ClassAndCaseBase>> groupComponents = new ArrayList<ArrayList<ClassAndCaseBase>>();
    private ClassAndCaseBase selectedSingleComponent = new ClassAndCaseBase();
    private ClassAndCaseBase pressedComponent = new ClassAndCaseBase();
    private int mousePressX, mousePressY;

    public BaseGUI() {
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(700, 600);
        frame.getContentPane().setBackground(Color.darkGray);
        frame.setLayout(null);
        frame.setJMenuBar(menuBar);
        
//        fileMenuNew.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {  // remove all components
//                editMenuGroup.setEnabled(false);
//                editMenuUngroup.setEnabled(false);
//                editMenuChangeName.setEnabled(false);
//                classCaseComponents.clear();
//                selectedGroupComponents.clear();
//                lineComponents.clear();
//                groupComponents.clear();
//                selectedSingleComponent = null;
//                pressedComponent = null;
//                canvas.removeAll();
//                canvas.repaint();
//            }
//        });
//        fileMenu.add(fileMenuNew);
        menuBar.add(fileMenu);
        
        editMenuGroup.setEnabled(false);
        editMenuGroup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setGroupComponents();
            }
        });
        editMenu.add(editMenuGroup);
        editMenuUngroup.setEnabled(false);
        editMenuUngroup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rmGroupComponents();
            }
        });
        editMenu.add(editMenuUngroup);
        editMenuChangeName.setEnabled(false);
        editMenuChangeName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object objectName = JOptionPane.showInputDialog(null, "Enter object name:", "Change Object Name", 0, null, null, "");
                if (objectName != null)
                    selectedSingleComponent.changeName(objectName.toString());
            }
        });
        editMenu.add(editMenuChangeName);
        menuBar.add(editMenu);

        String[] buttonName = { "select", "association line", "generation line",
            "composition line", "classes", "use case" };
        String[] iconName = { "select.png", "association_line.png", "generation_line.png",
            "composition_line.png", "classes.png", "use_case.png" };
        for (int i = 0; i < sideButtonNum; i++) {  // add buttons
            XYZButton tmpButton = new XYZButton(buttonName[i],
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

        frame.setVisible(true);
    }

    private void clickAction(int x, int y) {
        if (selectedBtnName == "")
            return;

        editMenuChangeName.setEnabled(false);
        editMenuUngroup.setEnabled(false);
        if (selectedBtnName == "select") {
            ClassAndCaseBase currentClickedOnComponent = checkIfOnComponent(x, y);
            ArrayList<ClassAndCaseBase> currentClickedOnGroup = checkIfInGroup(currentClickedOnComponent);
            hideAllPorts();  // hide current showing ports

            if (currentClickedOnComponent == null)  // blank
                return;

            if (currentClickedOnGroup != null) {  // click on group components
                editMenuUngroup.setEnabled(true);
                for (int i = 0; i < currentClickedOnGroup.size(); i++) {
                    currentClickedOnGroup.get(i).moveComponentFront(canvas);
                    switchTopComponentToArraylistHead(currentClickedOnGroup.get(i));
                    currentClickedOnGroup.get(i).showPorts(canvas);
                    selectedGroupComponents.add(currentClickedOnGroup.get(i));
                }
            } else {  // click on single component
                editMenuChangeName.setEnabled(true);
                currentClickedOnComponent.moveComponentFront(canvas);
                switchTopComponentToArraylistHead(currentClickedOnComponent);
                currentClickedOnComponent.showPorts(this.canvas);
                selectedSingleComponent = currentClickedOnComponent;
            }
            return;
        } else {
            if (checkIfOnComponent(x, y) != null)
                return;
            classCaseComponents.add(addClassCase(this.canvas, selectedBtnName, x, y));  // click on blank, add a component
        }
    }

    private void pressAction(int x, int y) {
        mousePressX = x;
        mousePressY = y;
        pressedComponent = checkIfOnComponent(x, y);  // null or the pressed component
    }

    private void releaseAction(int x, int y) {
        hideAllPorts();
        editMenuUngroup.setEnabled(false);
        editMenuChangeName.setEnabled(false);

        if (selectedBtnName == "association line" ||
                selectedBtnName == "generation line" ||
                selectedBtnName == "composition line") {
            ClassAndCaseBase releasedComponent = checkIfOnComponent(x, y);
            if (pressedComponent == null || releasedComponent == null)  // press at blank or release at blank
                return;
            if (releasedComponent == pressedComponent)
                return;
            else {
                lineComponents.add(new Lines(this.canvas, selectedBtnName, mousePressX, mousePressY,
                        x, y, pressedComponent, releasedComponent));
            }

        } else if (selectedBtnName == "select") {
            if (pressedComponent == null) {  // selection
                // enable selection from every direction
                if (mousePressX > x) {
                    int tmp = x;
                    x = mousePressX;
                    mousePressX = tmp;
                }
                if (mousePressY > y) {
                    int tmp = y;
                    y = mousePressY;
                    mousePressY = tmp;
                }

                for (int i = 0; i < classCaseComponents.size(); i++) {
                    if (mousePressX <= classCaseComponents.get(i).xmin &&
                            mousePressY <= classCaseComponents.get(i).ymin &&
                            x >= classCaseComponents.get(i).xmax &&
                            y >= classCaseComponents.get(i).ymax) {
                        classCaseComponents.get(i).showPorts(canvas);
                        selectedGroupComponents.add(classCaseComponents.get(i));
                        editMenuGroup.setEnabled(true);
                    }
                }
                if (selectedGroupComponents.size() == 1)
                    editMenuGroup.setEnabled(false);  // disable grouping when only one component selected
            } else {  // moving components
                int xmove = x - mousePressX;
                int ymove = y - mousePressY;
                ArrayList<ClassAndCaseBase> currentPressedOnGroup = checkIfInGroup(pressedComponent);

                if (currentPressedOnGroup == null) {  // move a single component
                    pressedComponent.xmax += xmove;
                    pressedComponent.xmin += xmove;
                    pressedComponent.ymax += ymove;
                    pressedComponent.ymin += ymove;
                    pressedComponent.paintMyComponents(canvas);
                    pressedComponent.moveComponentFront(canvas);
                    switchTopComponentToArraylistHead(pressedComponent);
                    for (int i = 0; i < lineComponents.size(); i++)
                        lineComponents.get(i).paintMyComponents(canvas);
                } else {  // move group components
                    for (int i = 0; i < currentPressedOnGroup.size(); i++) {
                        currentPressedOnGroup.get(i).xmax += xmove;
                        currentPressedOnGroup.get(i).xmin += xmove;
                        currentPressedOnGroup.get(i).ymax += ymove;
                        currentPressedOnGroup.get(i).ymin += ymove;
                        currentPressedOnGroup.get(i).paintMyComponents(canvas);
                        currentPressedOnGroup.get(i).moveComponentFront(canvas);
                        switchTopComponentToArraylistHead(currentPressedOnGroup.get(i));
                    }
                    for (int i = 0; i < lineComponents.size(); i++)
                        lineComponents.get(i).paintMyComponents(canvas);
                }

            }
        }
    }

    private ClassAndCaseBase checkIfOnComponent(int x, int y) {  // check if (x,y) is within any component
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

    private ArrayList<ClassAndCaseBase> checkIfInGroup(ClassAndCaseBase c) {
        if (c == null)
            return null;

        for (int i = 0; i < groupComponents.size(); i++) {
            for (int j = 0; j < groupComponents.get(i).size(); j++) {
                if (groupComponents.get(i).get(j) == c)
                    return groupComponents.get(i);
            }
        }
        return null;
    }

    private ClassAndCaseBase addClassCase(JLayeredPane canvas, String btnName, int x, int y) {
        switch (btnName) {
            case "classes":
                return new Classes(canvas, x, y);
            case "use case":
                return new UseCase(canvas, x, y);
            default:
                return null;
        }
    }

    private void hideAllPorts() {
        editMenuGroup.setEnabled(false);
        if(selectedSingleComponent != null)
            selectedSingleComponent.hidePorts(canvas);
        // when unselect by dragging
        for (int i = 0; i < selectedGroupComponents.size(); i++)
            selectedGroupComponents.get(i).hidePorts(canvas);
        selectedGroupComponents.clear();  // reset selected group

        // when unselect by clicking at group component
        for (int i = 0; i < groupComponents.size(); i++) {
            for (int j = 0; j < groupComponents.get(i).size(); j++) {
                groupComponents.get(i).get(j).hidePorts(canvas);
            }
        }
    }

    private void setGroupComponents() {
        ArrayList<ClassAndCaseBase> tmpGroupComponents = new ArrayList<ClassAndCaseBase>();
        for (int i = 0; i < selectedGroupComponents.size(); i++)
            tmpGroupComponents.add(selectedGroupComponents.get(i));
        groupComponents.add(0, tmpGroupComponents);
    }

    private void rmGroupComponents() {
        for (int i = 0; i < groupComponents.size(); i++) {
            if (groupComponents.get(i).equals(selectedGroupComponents))
                groupComponents.remove(i);
        }
    }

    private void switchTopComponentToArraylistHead(ClassAndCaseBase c) {
        for (int i = 0; i < classCaseComponents.size(); i++) {
            if (classCaseComponents.get(i).equals(c)) {
                classCaseComponents.remove(i);
                classCaseComponents.add(0, c);
            }
        }
    }
}