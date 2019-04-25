package xyz_UML;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class XYZButton extends JButton {
    private String btnName;

    public XYZButton(String buttonName,
            ImageIcon icon,
            int x,
            int y,
            int width,
            int height) {
        super();
        btnName = buttonName;

        this.setBounds(x, y, width, height);
        this.setIcon(icon);
        this.setPreferredSize(new Dimension(40, 40)); // size of the buttons
        this.setContentAreaFilled(true);
        this.setOpaque(true);
        this.setBorderPainted(false);
        this.setBackground(Color.white);

        this.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetButtonColor();
                XYZButton.this.setBackground(Color.gray);
                BaseGUI.selectedBtnName = XYZButton.this.btnName;
            }
        });
    }

    private void resetButtonColor() {  // reset color of all buttons when one is clicked
        for (int i = 0; i < BaseGUI.sideButtonNum; i++) {
            BaseGUI.buttonList.get(i).setBackground(Color.white);
        }
    }
}
