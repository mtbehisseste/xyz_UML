package shape;

import java.util.ArrayList;

import editor.Canvas;
import editor.EditorFrame;

public class GroupObj extends BasicObj {
    private ArrayList<BasicObj> groupList;

    public GroupObj(ArrayList<BasicObj> objOfGroup) {
        groupList = (ArrayList<BasicObj>) objOfGroup.clone();
    }

    @Override
    public void showPorts(Canvas canvas) {
        // TODO override to do nothing QQ, need to think how to avoid this
    }

    @Override
    public void hidePorts(Canvas canvas) {
        for (int i = 0; i < groupList.size(); i++) {
            groupList.get(i).hidePorts(canvas);
        }
    }

    @Override
    public void select(Canvas canvas) {  // actions when group object is selected
        EditorFrame.setEditMenuUngroup(true);  // TODO if selecting groups using block selecting, might have problems here
        for (int i = 0; i < groupList.size(); i++) {
            groupList.get(i).moveComponentFront(canvas);
            groupList.get(i).showPorts(canvas);
        }
        canvas.switchTopComponentToArraylistHead(this);
    }

    @Override
    public void move(Canvas canvas, int xmove, int ymove) {
        for (int i = 0; i < groupList.size(); i++)
            groupList.get(i).move(canvas, xmove, ymove);
    }

    @Override
    public boolean checkIfOnComponent(int x, int y) {
        for (int i = 0; i < groupList.size(); i++) {
            if (groupList.get(i).checkIfOnComponent(x, y))
                return true;
        }
        return false;
    }

    @Override
    public void paintMyComponents(Canvas canvas) {
        for (int i = 0; i < groupList.size(); i++)
            groupList.get(i).paintMyComponents(canvas);
    }
}
