package buttons;

import javax.swing.ImageIcon;

import editor.EditorFrame;
import mode.ClassMode;

public class ClassButton extends BasicButton {

    public ClassButton(ImageIcon icon, int x, int y, int width, int height) {
        super(icon, x, y, width, height);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void buttonAction() {
        super.buttonAction();
        EditorFrame.setCurrentMode(new ClassMode());
    }
}
