package buttons;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ButtonPanel extends JPanel {
    public int sideButtonNumber = 0;
    public static ArrayList<BasicButton> buttonList = new ArrayList<BasicButton>();
    private BasicButton associationBtn, classBtn, compositionBtn, generationBtn, selectBtn, useCaseBtn;

    public ButtonPanel() {
        this.setLayout(null);
        this.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        int btnWidth = 70;
        int btnHeight = 70;
        int x = 20;
        int y = 20;

        selectBtn = new SelectButton(new ImageIcon("resource/select.png"), x, y, btnWidth, btnHeight);
        buttonList.add(selectBtn);
        this.add(selectBtn);
        sideButtonNumber += 1;
        y += 90;

        associationBtn = new AssociationButton(new ImageIcon("resource/association_line.png"), x, y, btnWidth, btnHeight);
        buttonList.add(associationBtn);
        this.add(associationBtn);
        sideButtonNumber += 1;
        y += 90;

        generationBtn = new GenerationButton(new ImageIcon("resource/generation_line.png"), x, y, btnWidth, btnHeight);
        buttonList.add(generationBtn);
        this.add(generationBtn);
        sideButtonNumber += 1;
        y += 90;

        compositionBtn = new CompositionButton(new ImageIcon("resource/composition_line.png"), x, y, btnWidth, btnHeight);
        buttonList.add(compositionBtn);
        this.add(compositionBtn);
        sideButtonNumber += 1;
        y += 90;

        classBtn = new ClassButton(new ImageIcon("resource/classes.png"), x, y, btnWidth, btnHeight);
        buttonList.add(classBtn);
        this.add(classBtn);
        sideButtonNumber += 1;
        y += 90;

        useCaseBtn = new UseCaseButton(new ImageIcon("resource/use_case.png"), x, y, btnWidth, btnHeight);
        buttonList.add(useCaseBtn);
        this.add(useCaseBtn);
        sideButtonNumber += 1;
    }
}
