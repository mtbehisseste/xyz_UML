package buttons;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import editor.ButtonPanel;

public class BasicButton extends JButton {
    public BasicButton(ImageIcon icon, int x, int y, int width, int height) {
        this.setBounds(x, y, width, height);
        this.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        this.setIcon(icon);
        this.setContentAreaFilled(true);
        this.setOpaque(true);
        this.setBackground(Color.white);

        this.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buttonAction();
            }
        });
    }

    protected void buttonAction() {
        resetButtonColor();
        BasicButton.this.setBackground(Color.lightGray);
    }

    private void resetButtonColor() {  // reset color of all buttons when one is clicked
        for (int i = 0; i < ButtonPanel.buttonList.size(); i++) {
            ButtonPanel.buttonList.get(i).setBackground(Color.white);
        }
    }
}
