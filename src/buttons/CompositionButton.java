package buttons;

import javax.swing.ImageIcon;

import editor.EditorFrame;
import mode.CompositionLineMode;

public class CompositionButton extends BasicButton {

    public CompositionButton(ImageIcon icon, int x, int y, int width, int height) {
        super(icon, x, y, width, height);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void buttonAction() {
        super.buttonAction();
        EditorFrame.setCurrentMode(new CompositionLineMode());
    }

}
