package com.stockkeeper.Views.uicomponents;

import com.stockkeeper.Models.ItemEntry;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class DeleteButton extends JLabel implements MouseListener {

    private ItemEntry itemEntry;
    private ImageIcon icon1;
    private ImageIcon icon2;
    public DeleteButton(ItemEntry itemEntry, Color background) {
        super();
        setIcon(new ImageIcon(getClass().getResource("/com/stockkeeper/images/delIcon.png")));
        setBackground(background);
        this.itemEntry = itemEntry;
        icon1 = new ImageIcon(getClass().getResource("/com/stockkeeper/images/delIcon.png"));
        icon2 = new ImageIcon(getClass().getResource("/com/stockkeeper/images/delIcon2.png"));
        addMouseListener(this);
    }

    public ItemEntry getItemEntry() {
        return itemEntry;
    }

    public void setItemEntry(ItemEntry itemEntry) {
        this.itemEntry = itemEntry;
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
