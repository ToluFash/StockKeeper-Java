package com.stockkeeper.Views.uicomponents;

import com.stockkeeper.Controller.Global;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.NumberFormat;

public class SwitchableTextInput  extends JPanel implements MouseListener {

    private RoundedTextInputS textInput;
    private RateSwitch label;
    private Dimension textInputDimension;
    private Dimension labelDimension;
    public Boolean isPercent;

    public SwitchableTextInput(Dimension dimension, Boolean numberFormat) {
        FlowLayout layout = new FlowLayout(FlowLayout.LEFT);
        layout.setHgap(0);
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setPreferredSize(dimension);
        setBorder(new EmptyBorder(0,0,0,0));
        NumberFormat amountFormat = NumberFormat.getNumberInstance();
        this.textInput = numberFormat ? new RoundedTextInputS(amountFormat): new RoundedTextInputS();
        this.label = new RateSwitch("%");
        textInputDimension = new Dimension((int)dimension.getWidth()-25, (int)dimension.getHeight());
        labelDimension = new Dimension(25, (int)dimension.getHeight());
        textInput.setPreferredSize(textInputDimension);
        label.setPreferredSize(labelDimension);
        isPercent = true;
        add(textInput);
        add(label);
        label.addMouseListener(this);
    }

    public Boolean  getPercent() {
        return isPercent;
    }

    public void setPercent(Boolean percent) {
        isPercent = percent;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (isPercent){
            label.setText(" F");
            label.setBg(new Color(0x46B550));
            isPercent = false;
            label.repaint();
        }
        else{
            label.setText("%");
            label.setBg(new Color(0x59B3FF));
            isPercent = true;
            label.repaint();
        }

    }

    public RoundedTextInputS getTextInput() {
        return textInput;
    }

    public void setTextInput(RoundedTextInputS textInput) {
        this.textInput = textInput;
    }

    public RateSwitch getLabel() {
        return label;
    }

    public void setLabel(RateSwitch label) {
        this.label = label;
    }

    protected MaskFormatter createFormatter(String s) {
        MaskFormatter formatter = null;
        try {
            formatter = new MaskFormatter(s);
        } catch (java.text.ParseException exc) {
            System.err.println("formatter is bad: " + exc.getMessage());
            System.exit(-1);
        }
        return formatter;
    }
    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {


    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
