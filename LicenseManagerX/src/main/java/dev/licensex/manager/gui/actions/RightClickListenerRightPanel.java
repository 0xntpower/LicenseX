package dev.licensex.manager.gui.actions;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RightClickListenerRightPanel extends MouseAdapter {
    public void mousePressed(MouseEvent e) {
        if (e.isPopupTrigger())
            doPop(e);
    }

    public void mouseReleased(MouseEvent e) {
        if (e.isPopupTrigger())
            doPop(e);
    }

    private void doPop(MouseEvent e) {
        RightClickProductPanelMenu menu = new RightClickProductPanelMenu();
        menu.show(e.getComponent(), e.getX(), e.getY());
    }
}
