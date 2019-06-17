package editor;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLayeredPane;

import mode.BasicMode;
import shape.BasicObj;
import shape.GroupObj;
import shape.BasicLine;

public class Canvas extends JLayeredPane {
    public ArrayList<BasicObj> objectComponents = new ArrayList<BasicObj>();  // all objects on the canvas
    public ArrayList<BasicLine> lineComponents = new ArrayList<BasicLine>();  // all lines on the canvas
    public ArrayList<BasicObj> selectedGroupComponents = new ArrayList<BasicObj>();
    public BasicMode currentMode = null;
    public BasicObj selectedComponent;
    private ArrayList<ArrayList<BasicObj>> groupComponents = new ArrayList<ArrayList<BasicObj>>();
    private BasicObj pressedComponent;
    private BasicObj releaseComponent;
    private int mousePressX, mousePressY, mouseReleaseX, mouseReleaseY;

    public Canvas() {
        this.setLayout(null);
        this.setOpaque(true);
        this.setBackground(Color.white);
        this.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (currentMode == null)
                    return;
                pressAction(e.getX(), e.getY());
            }

            @Override
            public void mouseReleased(MouseEvent e) {  // create lines
                if (currentMode == null)
                    return;
                releaseAction(e.getX(), e.getY());
            }
        });
    }

    private void pressAction(int x, int y) {
        mousePressX = x;
        mousePressY = y;
        pressedComponent = checkIfOnComponent(x, y);  // null or the pressed component
    }

    private void releaseAction(int x, int y) {
        mouseReleaseX = x;
        mouseReleaseY = y;
        hideAllPorts();
        EditorFrame.canvasReleaseAction();

        releaseComponent = checkIfOnComponent(x, y);
        currentMode.releaseAction(this, pressedComponent, releaseComponent, mousePressX, mousePressY, mouseReleaseX, mouseReleaseY);
    }

    private BasicObj checkIfOnComponent(int x, int y) {  // check if (x,y) is within any component
        for (int i = 0; i < objectComponents.size(); i++) {
            if (objectComponents.get(i).checkIfOnComponent(x, y))
                return objectComponents.get(i);
        }
        return null;
    }

    public void hideAllPorts() {
        EditorFrame.setEditMenuGroup(false);
        if (selectedComponent != null)
            selectedComponent.hidePorts(this);
        // when unselect by dragging
        for (int i = 0; i < selectedGroupComponents.size(); i++)
            selectedGroupComponents.get(i).hidePorts(this);
        selectedGroupComponents.clear();  // reset selected group

        // when unselect by clicking at group component
        for (int i = 0; i < groupComponents.size(); i++) {
            for (int j = 0; j < groupComponents.get(i).size(); j++) {
                groupComponents.get(i).get(j).hidePorts(this);
            }
        }
    }

    public void setGroupComponents() {
        BasicObj groupObj = new GroupObj(selectedGroupComponents);
        objectComponents.add(0, groupObj);
    }

    public void rmGroupComponents() {
        for (int i = 0; i < objectComponents.size(); i++) {
            if (objectComponents.get(i).equals(selectedComponent))
                objectComponents.remove(i);
        }
    }

    public void switchTopComponentToArraylistHead(BasicObj c) {
        for (int i = 0; i < objectComponents.size(); i++) {
            if (objectComponents.get(i).equals(c)) {
                objectComponents.remove(i);
                objectComponents.add(0, c);
            }
        }
    }
}
