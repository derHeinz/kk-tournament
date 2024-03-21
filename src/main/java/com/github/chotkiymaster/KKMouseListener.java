package com.github.chotkiymaster;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class KKMouseListener implements MouseListener {
    private final Runnable doIt;

    public KKMouseListener(Runnable doIt) {
        this.doIt = doIt;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        this.doIt.run();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // do nothing
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // do nothing
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // do nothing
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // do nothing
    }
}
