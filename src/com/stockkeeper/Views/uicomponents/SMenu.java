package com.stockkeeper.Views.uicomponents;

import javax.swing.*;
import java.awt.*;

public class SMenu extends JMenu {

    private String owner;

    public SMenu() {
        setFont(new Font("Segoe UI", Font.PLAIN, 12));
    }

    public SMenu(String text) {
        super(text);
        setFont(new Font("SansSerif", Font.PLAIN, 12));
        setForeground(Color.WHITE);
    }

    public SMenu(Action a) {
        super(a);
        setFont(new Font("SansSerif", Font.PLAIN, 12));
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    protected void paintBorder(Graphics g) {
        g.fillRect(0,0,getWidth(),getHeight());
    }
}
