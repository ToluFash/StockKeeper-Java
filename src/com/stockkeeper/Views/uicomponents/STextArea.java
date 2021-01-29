package com.stockkeeper.Views.uicomponents;

import com.stockkeeper.Controller.Global;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.Document;
import java.awt.*;

public class STextArea extends JTextArea {
    private String placeholder;
    public STextArea() {
    }

    public STextArea(String text) {
        super(text);
    }

    public STextArea(String text, Dimension size) {
        super(text);
        setPreferredSize(size);
        setOpaque(false);
        setBorder(new EmptyBorder(0,10,0,2));
    }

    public STextArea(int rows, int columns) {
        super(rows, columns);
    }

    public STextArea(String text, int rows, int columns) {
        super(text, rows, columns);
        setBorder(new EmptyBorder(0,5,0,5));
        setOpaque(false);
    }

    public STextArea(Document doc) {
        super(doc);
    }

    public STextArea(Document doc, String text, int rows, int columns) {
        super(doc, text, rows, columns);
        setEditable(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.WHITE);
        g2d.fillRoundRect(0,0,getWidth(),getHeight(),5,5);
        g2d.setColor(Color.LIGHT_GRAY);
        super.paintComponent(g);
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    
}
