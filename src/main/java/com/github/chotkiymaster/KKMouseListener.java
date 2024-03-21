package com.github.chotkiymaster;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class KKMouseListener implements MouseListener {
    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.printf("Mouse clicked at (%d, %d)%n", e.getX(), e.getY());
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
