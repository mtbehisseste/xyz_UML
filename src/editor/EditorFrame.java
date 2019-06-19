package editor;

import javax.swing.*;

import mode.BasicMode;

public class EditorFrame {
    private JFrame frame = new JFrame("xyz");
    private static MenuBar menuBar = new MenuBar();
    public static ButtonPanel buttonPanel = new ButtonPanel();
    public static Canvas canvas = new Canvas();

    public EditorFrame() {
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(null);

        frame.setJMenuBar(menuBar);

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

    public static void setCurrentMode(BasicMode m) {
        Canvas.currentMode = m;
    }
    
    public static void menuSetGroupComponents() {
        canvas.setGroupComponents();
    }
    
    public static void menuRmGroupComponents() {
        canvas.rmGroupComponents();
    }
    
    public static void menuChangeName() {
        Object objectName = JOptionPane.showInputDialog(null, "Enter object name:", "Change Object Name", 0, null, null, "");
        if (objectName != null)
            canvas.selectedComponent.changeName(objectName.toString());
    }

    public static int getSideButtonNumber() {
        return buttonPanel.sideButtonNumber;
    }

    public static void setEditMenuGroup(boolean b) {
        menuBar.editMenuGroup.setEnabled(b);
    }
    
    public static void setEditMenuUngroup(boolean b) {
        menuBar.editMenuUngroup.setEnabled(b);
    }
    
    public static void setEditMenuChangeName(boolean b) {
        menuBar.editMenuChangeName.setEnabled(b);
    }
}