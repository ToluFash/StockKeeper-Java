package com.stockkeeper.Views.uicomponents;

import com.stockkeeper.Views.labels.SCrazyLabel;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.geom.RoundRectangle2D;

public class CrazyInput extends JPanel implements FocusListener {
    private STextField sTextField;
    private SCrazyLabel sLabel;
    private Shape shape;

    public CrazyInput() {
        setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
        sTextField = new STextField(20);
        sTextField.addFocusListener(this);
        sLabel = new SCrazyLabel("    ");
        sTextField.setPreferredSize(new Dimension(100,30));
        add(sLabel);
        add(sTextField);
        setBorder(LineBorder.createGrayLineBorder());
    }
    public CrazyInput(String placeholder) {
        setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
        sTextField = new STextField(20);
        sTextField.setPlaceholder(placeholder);
        sTextField.addFocusListener(this);
        sLabel = new SCrazyLabel("    ");
        sTextField.setPreferredSize(new Dimension(320,30));
        add(sLabel);
        add(sTextField);
        setBorder(LineBorder.createGrayLineBorder());
    }
    protected void paintComponent(Graphics g) {
        g.setColor(new Color(0xFFFFFF));
        g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 0, 0);
        super.paintComponent(g);
    }
    protected void paintBorder(Graphics g) {
        Graphics2D graphics2D = (Graphics2D)g;
        graphics2D.setColor(new Color(0xBDC0C3));
        graphics2D.draw(new RoundRectangle2D.Double(getX(), getY(), getWidth()-1, getHeight()-1, 2, 2));
    }
    public boolean contains(int x, int y) {
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, 15, 15);
        }
        return shape.contains(x, y);
    }

    public STextField getsTextField() {
        return sTextField;
    }

    public SCrazyLabel getsLabel() {
        return sLabel;
    }

    public String getPlaceholder() {
        return sTextField.getPlaceholder();
    }

    public void setPlaceholder(String placeholder) {
        this.sTextField.setPlaceholder(placeholder);
    }


    @Override
    public void focusGained(FocusEvent e) {
        Graphics g = getGraphics();
        Graphics2D gr2D = (Graphics2D)g;
        gr2D.setColor(new Color(0x8CC2E3));
        BasicStroke aStroke = new BasicStroke(2.0f,
                BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
        gr2D.setStroke(aStroke);
        gr2D.drawRect(sLabel.getWidth(),getY(),sTextField.getWidth()+2,getHeight());

    }

    @Override
    public void focusLost(FocusEvent e) {

        repaint();

    }
}
