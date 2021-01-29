package com.stockkeeper.Views.uicomponents;

import javax.swing.*;
import java.awt.*;

public class FancyButton extends JButton {

    public FancyButton() {
    }

    public FancyButton(Icon icon) {
        super(icon);
    }

    public FancyButton(String text) {
        super(text);
    }

    public FancyButton(Action a) {
        super(a);
    }

    public FancyButton(String text, Icon icon) {
        super(text, icon);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(getX(),getY(),getWidth(),getHeight());
        g.drawString("Nuldfnhhhhhhhhhhhhhhl",getX(), getHeight());
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        g.setColor(Color.white);
        g.drawRect(getX(),getY(),getWidth(),getHeight());
        super.paintBorder(g);
    }
}
