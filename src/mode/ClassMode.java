package mode;

import editor.Canvas;
import shape.BasicObj;
import shape.ClassesObj;

public class ClassMode extends BasicMode {
    @Override
    public void clickAction(Canvas canvas, BasicObj currentClickedOnComponent, int x, int y) {
        if (currentClickedOnComponent != null)  // click on instance
            return;

        ClassesObj classObj = new ClassesObj(canvas, x, y);
        canvas.objectComponents.add(classObj);
    }

    @Override
    public void releaseAction(Canvas canvas, BasicObj pressedComponent, BasicObj releasedComponent, int mousePressX, int mousePressY,
            int mouseReleaseX, int mouseReleaseY) {
        clickAction(canvas, pressedComponent, mouseReleaseX, mouseReleaseY);
    }
}
