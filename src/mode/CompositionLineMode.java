package mode;

import editor.Canvas;
import shape.BasicObj;
import shape.CompositionLine;
import shape.GroupObj;

public class CompositionLineMode extends BasicMode {
    @Override
    public void releaseAction(Canvas canvas, BasicObj pressedComponent, BasicObj releasedComponent,
            int mousePressX, int mousePressY, int mouseReleaseX, int mouseReleaseY) {
        if (pressedComponent == null || releasedComponent == null
                || pressedComponent instanceof GroupObj || releasedComponent instanceof GroupObj)  // press at blank or release at blank or
                                                                                                   // create line to group object
            return;
        if (releasedComponent == pressedComponent)
            return;
        else
            canvas.lineComponents.add(new CompositionLine(canvas, mousePressX, mousePressY, mouseReleaseX, mouseReleaseY, pressedComponent,
                    releasedComponent));
    }
}
