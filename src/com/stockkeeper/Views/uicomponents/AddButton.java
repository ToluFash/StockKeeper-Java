package com.stockkeeper.Views.uicomponents;

import com.stockkeeper.Models.ItemEntry;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class AddButton extends JLabel implements MouseListener {

    private ImageIcon icon1;
    private ImageIcon icon2;

    public AddButton() {
        super();
        setIcon(new ImageIcon(getClass().getResource("/com/stockkeeper/images/addIcon.png")));
        icon1 = new ImageIcon(getClass().getResource("/com/stockkeeper/images/addIcon.png"));
        icon2 = new ImageIcon(getClass().getResource("/com/stockkeeper/images/addIcon2.png"));
        addMouseListener(this);
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

        setIcon(icon1);
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

        setIcon(icon2);
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        setIcon(icon2);
        repaint();

    }

    @Override
    public void mouseExited(MouseEvent e) {
        setIcon(icon1);
        repaint();

    }
}
