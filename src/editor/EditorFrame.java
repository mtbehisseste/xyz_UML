package editor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import buttons.ButtonPanel;
import mode.BasicMode;

public class EditorFrame {
    private JFrame frame = new JFrame("xyz");
    private JMenuBar menuBar = new JMenuBar();
    private JMenu fileMenu = new JMenu("File");
    private JMenu editMenu = new JMenu("Edit");
    public static ButtonPanel buttonPanel = new ButtonPanel();
    public static Canvas canvas = new Canvas();
    public static JMenuItem editMenuGroup = new JMenuItem("Group");
    public static JMenuItem editMenuUngroup = new JMenuItem("Ungroup");
    public static JMenuItem editMenuChangeName = new JMenuItem("Change Object Name");

    public EditorFrame() {
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(null);

        frame.setJMenuBar(menuBar);
        menuBar.add(fileMenu);

        editMenuGroup.setEnabled(false);
        editMenuGroup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                canvas.setGroupComponents();
            }
        });
        editMenu.add(editMenuGroup);

        editMenuUngroup.setEnabled(false);
        editMenuUngroup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                canvas.rmGroupComponents();
            }
        });
        editMenu.add(editMenuUngroup);

        editMenuChangeName.setEnabled(false);
        editMenuChangeName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                Object objectName = JOptionPane.showInputDialog(null, "Enter object name:", "Change Object Name", 0, null, null, "");
//                if (objectName != null)
//                    selectedSingleComponent.changeName(objectName.toString());
            }
        });
        editMenu.add(editMenuChangeName);
        menuBar.add(editMenu);

        int buttonPanelWidth = 20 + 70 + 20;
        int buttonPanelHeight = 20 + (70 + 20) * buttonPanel.sideButtonNumber;
        buttonPanel.setLocation(20, 20);
        buttonPanel.setSize(buttonPanelWidth, buttonPanelHeight);  // 40 is the button size, 20 is margin padding
        frame.add(buttonPanel);

        int canvasWidth = 200 + 20 + (70 + 20) * buttonPanel.sideButtonNumber;
        int canvasHeight = 20 + (70 + 20) * buttonPanel.sideButtonNumber;
        canvas.setLocation(20 + (20 + 70 + 20) + 20, 20);
        canvas.setSize(canvasWidth, canvasHeight);
        frame.add(canvas);

        frame.setSize(20 + buttonPanelWidth + 20 + canvasWidth + 20, 20 + canvasHeight + 20 + 40);
        frame.setVisible(true);
    }

    public static void canvasClickAction() {
        editMenuChangeName.setEnabled(false);
        editMenuUngroup.setEnabled(false);
    }

    public static void setCurrentMode(BasicMode m) {
        canvas.currentMode = m;
    }

    public static int getSideButtonNumber() {
        return buttonPanel.sideButtonNumber;
    }

    public static void canvasReleaseAction() {
        editMenuUngroup.setEnabled(false);
        editMenuChangeName.setEnabled(false);
    }

    public static void setEditMenuGroup(boolean b) {
        editMenuGroup.setEnabled(b);
    }
}