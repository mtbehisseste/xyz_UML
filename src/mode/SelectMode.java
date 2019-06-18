package mode;

import editor.Canvas;
import editor.EditorFrame;
import shape.BasicObj;

public class SelectMode extends BasicMode {
    @Override
    public void clickAction(Canvas canvas, BasicObj currentClickedOnComponent, int x, int y) {
        canvas.hideAllPorts();

        if (currentClickedOnComponent == null)  // blank
            return;

        currentClickedOnComponent.select(canvas);
    }

    @Override
    public void releaseAction(Canvas canvas, BasicObj pressedComponent, BasicObj releasedComponent, int mousePressX, int mousePressY,
            int mouseReleaseX, int mouseReleaseY) {
        if (pressedComponent == null) {  // block selection
            canvas.hideAllPorts();

            // enable selection from every direction
            if (mousePressX > mouseReleaseX) {
                int tmp = mouseReleaseX;
                mouseReleaseX = mousePressX;
                mousePressX = tmp;
            }
            if (mousePressY > mouseReleaseY) {
                int tmp = mouseReleaseY;
                mouseReleaseY = mousePressY;
                mousePressY = tmp;
            }

            for (int i = 0; i < canvas.objectComponents.size(); i++) {
                if (mousePressX <= canvas.objectComponents.get(i).xmin &&
                        mousePressY <= canvas.objectComponents.get(i).ymin &&
                        mouseReleaseX >= canvas.objectComponents.get(i).xmax &&
                        mouseReleaseY >= canvas.objectComponents.get(i).ymax) {
                    canvas.objectComponents.get(i).showPorts(canvas);
                    canvas.selectedGroupComponents.add(canvas.objectComponents.get(i));  // add candidate grouping objects
                    EditorFrame.setEditMenuGroup(true);
                }
            }
            if (canvas.selectedGroupComponents.size() == 1)
                EditorFrame.setEditMenuGroup(false);  // disable grouping when only one component selected
        } else {
            if (pressedComponent == releasedComponent) {  // click
                EditorFrame.canvasReleaseAction();
                clickAction(canvas, pressedComponent, mousePressX, mousePressY);
                canvas.selectedComponent = pressedComponent;
            } else {  // moving objects
                int xmove = mouseReleaseX - mousePressX;
                int ymove = mouseReleaseY - mousePressY;
                pressedComponent.move(canvas, xmove, ymove);
                pressedComponent.paintMyComponents(canvas);
                pressedComponent.moveComponentFront(canvas);
                canvas.switchTopComponentToArraylistHead(pressedComponent);

                for (int i = 0; i < canvas.lineComponents.size(); i++)
                    canvas.lineComponents.get(i).paintMyComponents(canvas);
            }
        }
    }
}
