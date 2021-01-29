package com.stockkeeper.Views.uicomponents;

import javax.swing.*;
import java.awt.*;

public class STextField extends JTextField {
    private String placeholder = "";
    private Shape shape;
    public STextField(int size) {
        super(size);
        setOpaque(false);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }

    protected void paintComponent(Graphics g) {
        g.setColor(new Color(0xFFFFFF));
        g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 0, 0);
        g.setColor(new Color(0x696366));
        if (this.getText().length() < 1)
            g.drawString(placeholder,3,getHeight()-10);
        super.paintComponent(g);
    }
    protected void paintBorder(Graphics g) {
        g.setColor(new Color(0xFFFFFF));
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

}
