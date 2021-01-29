package com.stockkeeper.Views.uicomponents;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;

public class SButton extends JPanel implements MouseListener {

    private JLabel label;

    public SButton(String text) {
        label = new JLabel(text);
        setBorder(new LineBorder(new Color(0xffffff),1));
        add(label);
        addMouseListener(this);
    }


    public JLabel getLabel() {
        return label;
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        Color color = getBackground();
        setBackground(new Color(color.getRed() ,color.getBlue() ,color.getGreen() ));

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Color color = getBackground();
        setBackground(new Color(color.getRed() ,color.getBlue() ,color.getGreen() ));

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        Color color = getBackground();
        setBackground(new Color(color.getRed() ,color.getBlue() ,color.getGreen() ));

    }

    @Override
    public void mouseExited(MouseEvent e) {
        Color color = getBackground();
        setBackground(new Color(color.getRed() ,color.getBlue() ,color.getGreen() ));
    }
}
