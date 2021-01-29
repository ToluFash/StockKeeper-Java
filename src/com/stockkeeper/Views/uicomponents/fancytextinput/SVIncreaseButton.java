package com.stockkeeper.Views.uicomponents.fancytextinput;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SVIncreaseButton extends JButton implements MouseListener {

    Color bgColor;
    Color bgColor2;

    public SVIncreaseButton() {
        super();
        setIcon(new ImageIcon(getClass().getResource("/com/stockkeeper/images/arrowdown2.png")));
        bgColor = new Color(0xE6E9EC);
        bgColor2 = new Color(0xC1C3C6);
        setBackground(bgColor);
        setFocusPainted(false);
        setBorderPainted(false);
        addMouseListener(this);
    }

    public SVIncreaseButton(Icon icon) {
        super(icon);
        setBackground(Color.BLUE);
        setFocusPainted(false);
        setBorderPainted(false);
        addMouseListener(this);
    }

    public SVIncreaseButton(String text) {
        super(text);
        setBackground(Color.BLUE);
        setFocusPainted(false);
        setBorderPainted(false);
        addMouseListener(this);
    }

    public SVIncreaseButton(Action a) {
        super(a);
    }

    public SVIncreaseButton(String text, Icon icon) {
        super(text, icon);
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        setBackground(bgColor2);

    }

    @Override
    public void mouseExited(MouseEvent e) {
        setBackground(bgColor);

    }
}
