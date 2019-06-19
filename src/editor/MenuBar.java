package editor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBar extends JMenuBar{
    private JMenu fileMenu = new JMenu("File");
    private JMenu editMenu = new JMenu("Edit");
    
    public JMenuItem editMenuGroup = new JMenuItem("Group");
    public JMenuItem editMenuUngroup = new JMenuItem("Ungroup");
    public JMenuItem editMenuChangeName = new JMenuItem("Change Object Name");
    
    public MenuBar() {
        this.add(fileMenu);
        
        editMenuGroup.setEnabled(false);
        editMenuGroup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditorFrame.menuSetGroupComponents();
            }
        });
        editMenu.add(editMenuGroup);

        editMenuUngroup.setEnabled(false);
        editMenuUngroup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditorFrame.menuRmGroupComponents();
            }
        });
        editMenu.add(editMenuUngroup);

        editMenuChangeName.setEnabled(false);
        editMenuChangeName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditorFrame.menuChangeName();
            }
        });
        editMenu.add(editMenuChangeName);
        this.add(editMenu);
    }
}
