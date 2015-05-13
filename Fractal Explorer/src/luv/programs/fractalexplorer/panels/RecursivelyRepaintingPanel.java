package luv.programs.fractalexplorer.panels;

import javax.swing.JPanel;

public class RecursivelyRepaintingPanel extends JPanel {

    public void forceRecursiveRepaint() {
        this.updateUI();
    }
}
