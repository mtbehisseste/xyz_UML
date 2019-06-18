package mode;

import editor.Canvas;
import shape.BasicObj;
import shape.UseCaseObj;

public class UseCaseMode extends BasicMode {
    @Override
    public void clickAction(Canvas canvas, BasicObj currentClickedOnComponent, int x, int y) {
        if (currentClickedOnComponent != null)  // click on other instance
            return;

        canvas.objectComponents.add(new UseCaseObj(canvas, x, y));
    }

    @Override
    public void releaseAction(Canvas canvas, BasicObj pressedComponent, BasicObj releasedComponent, int mousePressX, int mousePressY,
            int mouseReleaseX, int mouseReleaseY) {
        clickAction(canvas, pressedComponent, mouseReleaseX, mouseReleaseY);
    }
}
