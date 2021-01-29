package com.stockkeeper.Views.uicomponents.fancytextinput;

import javax.swing.*;
import javax.swing.text.Document;
import java.awt.*;

public class FancyInput extends JTextField {

    public FancyInput() {
    }

    public FancyInput(String text) {
        super(text);
    }

    public FancyInput(int columns) {
        super(columns);
    }

    public FancyInput(String text, int columns) {
        super(text, columns);
    }

    public FancyInput(Document doc, String text, int columns) {
        super(doc, text, columns);
    }


    protected void paintBorder(Graphics g) {
        g.setColor(new Color(0xFFFFFF));
        g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 0, 0);
    }

}
